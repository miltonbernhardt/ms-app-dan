import ClientesForm from './ClientesForm';
import { Tabla, FilaTabla, CeldaTabla, CeldaBotonTabla, EncabezadoTabla } from '../Tabla';
import { useEffect, useState } from 'react';
import '../styles/Clientes.css';
import '../styles/Tabla.css';
import '../services/ClienteService';
import { getClientes, postCliente, putCliente } from '../services/ClienteService';

const clienteInicial = {
    razonSocial: '-',
    habilitadoOnline: true,
    maxCuentaCorriente: 0.0,
    mail: '',
    cuit: ''
};

const Clientes = () => {

    const [cliente, setCliente] = useState(clienteInicial);
    const [listaClientes, setListaClientes] = useState([]);

    useEffect(() => {
        fetchClientes();
    }, []);

    const fetchClientes = async () => {
        getClientes().then(res => setListaClientes(res.data));
    }


    const actualizarCliente = (atributo, valor) => {
        const clienteNuevo = { ...cliente, [atributo]: valor };
        setCliente(clienteNuevo);
    }

    const saveOrUpdate = () => {
        (!(cliente.id)) ? postCliente(cliente).then(() => fetchClientes()) :
            putCliente(cliente).then(() => fetchClientes());
        setCliente(clienteInicial);
    };

    const filasCliente = listaClientes.map((e, indice) => {
        return <FilaTabla clave={e.indice} >
            <CeldaTabla dato={e.id} />
            <CeldaTabla dato={e.razonSocial} />
            <CeldaTabla dato={e.mail} />
            <CeldaBotonTabla titulo="Seleccionar" accion={() => setCliente(e)} />
        </FilaTabla>
    });

    const encabezado = ["ID Usuario", "Razon Social", "e-mail", ""].map((e) => {
        return <EncabezadoTabla>{e}</EncabezadoTabla>
    })

    const limpiarCampos = () => {
        setCliente(clienteInicial);
    }

    return (
        <div className="box">
            <div><h1>Gestion de clientes</h1></div>
            <div className="panelForm">
                <div className="panelFormAlta">
                    <ClientesForm cliente={cliente}
                        actualizarCliente={actualizarCliente}
                        saveOrUpdate={saveOrUpdate}
                        clean={limpiarCampos} />
                </div>
                <div className="panelFormBusqueda">Busqueda</div>
            </div>
            <div className="panel">
                <Tabla >
                    {encabezado}
                    {filasCliente}
                </Tabla>
            </div>
        </div>
    )
}

export default Clientes;