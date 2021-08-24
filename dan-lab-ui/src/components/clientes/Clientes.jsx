import ClientesForm from './ClientesForm';
import {Tabla, FilaTabla, CeldaTabla, CeldaBotonTabla, EncabezadoTabla} from '../Tabla';
import {useEffect, useState} from 'react';
import '../styles/Clientes.css';
import '../styles/Tabla.css';
import {getClientes, postCliente, putCliente} from '../../RestServices';

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
        getClientes().then(data => setListaClientes(data));
    }, []);

    const fetchClientes = () => {
        getClientes().then(data => setListaClientes(data));
    }


    const actualizarCliente = (atributo, valor) => {
        const clienteNuevo = {...cliente, [atributo]: valor};
        setCliente(clienteNuevo);
    }

    const saveOrUpdate = () => {
        (!(cliente.id)) ? postCliente(cliente).then(() => fetchClientes()) :
            putCliente(cliente).then(() => fetchClientes());
        setCliente(clienteInicial);
    };

    const filasCliente = () => {
        if (listaClientes)
            return listaClientes.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.razonSocial}/>
                    <CeldaTabla dato={e.mail}/>
                    <CeldaBotonTabla titulo="Seleccionar" accion={() => setCliente(e)}/>
                </FilaTabla>
            });
        else
            return <></>
    }

    const encabezado = ["ID Usuario", "Razon Social", "e-mail", ""].map((e, i) => {
        return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
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
                                  clean={limpiarCampos}/>
                </div>
                <div className="panelFormBusqueda">Busqueda</div>
            </div>
            <div className="panel">
                <Tabla encabezado={encabezado} filas={filasCliente()}/>
            </div>
        </div>
    )
}

export default Clientes;