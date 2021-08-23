package com.brikton.labapps.mscuentacorriente.service.impl;

import com.brikton.labapps.mscuentacorriente.domain.*;
import com.brikton.labapps.mscuentacorriente.dto.Obra;
import com.brikton.labapps.mscuentacorriente.dto.Pedido;
import com.brikton.labapps.mscuentacorriente.repository.ChequeRepository;
import com.brikton.labapps.mscuentacorriente.repository.EfectivoRepository;
import com.brikton.labapps.mscuentacorriente.repository.PagoRepository;
import com.brikton.labapps.mscuentacorriente.repository.TransferenciaRepository;
import com.brikton.labapps.mscuentacorriente.service.ClienteService;
import com.brikton.labapps.mscuentacorriente.service.CuentaCorrienteService;
import com.brikton.labapps.mscuentacorriente.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaCorrienteServiceImpl implements CuentaCorrienteService {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    PagoRepository pagoRepository;
    @Autowired
    ChequeRepository chequeRepository;
    @Autowired
    TransferenciaRepository transferenciaRepository;
    @Autowired
    EfectivoRepository efectivoRepository;

    @Autowired
    ClienteService clienteService;

    @Override
    @Transactional
    public Pago savePago(Pago pagoNuevo) throws Exception {
        pagoNuevo.setId(null);
        pagoNuevo.setFechaPago(Instant.now());
        if (pagoNuevo.getCliente() != null) {
            pagoNuevo.setCliente(clienteService.getClienteCorrecto(pagoNuevo.getCliente()));
        } else {
            throw new Exception("Para registrar un pago debe indicar que cliente lo realizó");
        }

        if (pagoNuevo.getCheque() != null) {
            pagoNuevo.setCheque(getChequeCorrecto(pagoNuevo));
        } else {
            if (pagoNuevo.getTransferencia() != null) {
                pagoNuevo.setTransferencia(getTransferenciaCorrecto(pagoNuevo));
            } else {
                if (pagoNuevo.getEfectivo() != null) {
                    pagoNuevo.setEfectivo(getEfectivoCorrecto(pagoNuevo));
                } else {
                    throw new Exception("Debe registrarse un método de pago.");
                }
            }
        }
        return pagoRepository.save(pagoNuevo);
    }

    private Cheque getChequeCorrecto(Pago pago) throws Exception {
        if (pago.getCheque().getBanco() == null || pago.getCheque().getBanco().equals(""))
            throw new Exception("No se declaro el banco del donde proviene el cheque.");
        if (pago.getCheque().getNroCheque() == null)
            throw new Exception("No se proveyó del número del cheque usado.");
        pago.getCheque().setId(null);
        pago.getCheque().setFechaCobro(pago.getFechaPago().plus(30, ChronoUnit.DAYS));

        return chequeRepository.save(pago.getCheque());
    }

    private Efectivo getEfectivoCorrecto(Pago pago) throws Exception {
        pago.getEfectivo().setId(null);
        if (pago.getEfectivo().getNroRecibo() == null)
            throw new Exception("No se proveyó del número de recibo del pago con efectivo.");
        return efectivoRepository.save(pago.getEfectivo());
    }

    private Transferencia getTransferenciaCorrecto(Pago pago) throws Exception {
        pago.getTransferencia().setId(null);
        if (pago.getTransferencia().getCbuOrigen() == null)
            throw new Exception("No se proveyó del cbu de origen.");
        if (pago.getTransferencia().getCbuDestino() == null)
            throw new Exception("No se proveyó del cbu de destino.");
        if (pago.getTransferencia().getCodigoTransferencia() == null)
            throw new Exception("No se proveyó del código de trasferencia.");
        return transferenciaRepository.save(pago.getTransferencia());
    }

    @Override
    public List<Pago> getDetallePagos(Cliente cliente) {
        return pagoRepository.getAllPagosCliente(cliente.getCuit());
    }

    @Override
    public List<Pedido> getFacturas(Cliente cliente) throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        Optional<List<Obra>> obras = Optional.ofNullable(clienteService.getObras(cliente));
        obras.ifPresent(listaObras -> {
            for (Obra dtoObra : listaObras) {
                List<Pedido> pedidos1 = pedidoService.getPedidos(dtoObra); //TODO ver como hacer, porque el id de obra en el modulo de pedidos no es el mismo que de usuario
                if (pedidos1.size() > 0) {
                    for (Pedido pedido : pedidos1) {
                        if (pedido.getEstadoPedido().getEstado().equals("ENTREGADO"))
                            pedidos.add(pedido);
                    }
                }
            }
        });
        return pedidos;
    }
}
