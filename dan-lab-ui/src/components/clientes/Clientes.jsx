import ClientesForm from './ClientesForm';
import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import {useEffect, useState} from 'react';
import '../styles/Tabla.css';
import {getClientes, postCliente, putCliente} from '../../RestServices';
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";

const clienteInicial = {
    razonSocial: '',
    habilitadoOnline: true,
    maxCuentaCorriente: 0.0,
    mail: '',
    cuit: ''
};

const Clientes = () => {
    const [cliente, setCliente] = useState(clienteInicial);
    const [listaClientes, setListaClientes] = useState([]);
    const history = useHistory();

    useEffect(() => {
        if (localStorage.getItem("token")) {
            getClientes().then(data => {
                if (data) {
                    setListaClientes(data)
                }
            });
        } else {
            history.push(RUTAS.login[2])
        }
    }, [history]);

    const fetchClientes = () => {
        getClientes().then(data => {
            if (data) {
                setListaClientes(data)
            }
        });
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

    const encabezado = ["ID Usuario", "Razón social", "Email", ""].map((e, i) => <EncabezadoTabla key={i}>{e}</EncabezadoTabla>)

    const limpiarCampos = () => {
        setCliente(clienteInicial);
    }

    return (
        <>
            <div><h1>Gestión de clientes</h1></div>
            <ClientesForm cliente={cliente}
                          actualizarCliente={actualizarCliente}
                          saveOrUpdate={saveOrUpdate}
                          clean={limpiarCampos}/>
            <Tabla encabezado={encabezado} filas={filasCliente()}/>
        </>
    )
}

export default Clientes;