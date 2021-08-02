package com.brikton.labapps.msusuario.servicioImplementacion;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.repositorios.ClienteRepositorio;
import com.brikton.labapps.msusuario.servicioInterfaz.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImplementacion implements ClienteServicio {

    @Autowired
    ClienteRepositorio repositorio;

    @Override
    public List<Cliente> listarClientes() {
        return this.repositorio.findByFechaBajaIsNull();
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Integer id) throws Exception {
        Optional<Cliente> c = this.repositorio.findById(id);
        if (c.isEmpty() || c.get().getFechaBaja() != null)
            throw new Exception("No existen clientes con el id: " + id);
        return c;
    }

    @Override
    public Optional<Cliente> buscarClientePorCuit(String cuit) throws Exception {
        List<Cliente> clientes = this.repositorio.findByCuit(cuit);

        if (clientes.size() > 0) {
            return Optional.ofNullable(clientes.get(0));
        }
        throw new Exception("No existen clientes con el número de CUIT: " + cuit);
    }

    @Override
    public Optional<Cliente> buscarClientePorRazonSocial(String razonSocial) throws Exception {
        Optional<Cliente> c = this.repositorio.findByRazonSocial(razonSocial);
        if (c.isEmpty() || c.get().getFechaBaja() != null)
            throw new Exception("No existen clientes con la razón social " + razonSocial);
        return c;
    }

    @Override
    @Transactional
    public Cliente guardarCliente(Cliente c) throws Exception {
        if (c.getId() != null && this.buscarClientePorId(c.getId()).isPresent()) {
            //Si tiene un id actualiza
            return this.repositorio.save(c);
        } else {
            Optional<Cliente> clienteAntiguo;

            try {
                clienteAntiguo = this.buscarClientePorCuit(c.getCuit());
            } catch (Exception e) {
                clienteAntiguo = Optional.empty();
            }

            if (clienteAntiguo.isPresent()) {
                //si ya existe un cliente con el mismo cuit, actualiza
                c.setId(clienteAntiguo.get().getId());

            } else {
                Usuario u = new Usuario();
                u.setUser(c.getMail());
                u.setPassword("1234");
            }
            return this.repositorio.save(c);
        }
    }

    @Override
    @Transactional
    public void bajaCliente(Integer id) throws Exception {
        // TODO: validar que no haya realizado pedidos
        Optional<Cliente> c = this.buscarClientePorId(id);
        if (c.isEmpty()) {
            throw new Exception("No existen clientes con el id: " + id);
        }
        c.get().setFechaBaja(new Date());
        this.repositorio.save(c.get());
    }
}

