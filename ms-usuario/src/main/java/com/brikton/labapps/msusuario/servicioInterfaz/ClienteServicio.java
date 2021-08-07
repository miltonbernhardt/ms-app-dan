package com.brikton.labapps.msusuario.servicioInterfaz;

import com.brikton.labapps.msusuario.domain.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteServicio {
	List<Cliente> listarClientes();

	Optional<Cliente> buscarClientePorId(Integer id) throws Exception;

	Optional<Cliente> buscarClientePorCuit(String cuit) throws Exception;

	Optional<Cliente> buscarClientePorRazonSocial(String razonSocial) throws Exception;

	Cliente guardarCliente(Cliente c) throws Exception;

	void bajaCliente(Integer id) throws Exception;
}
