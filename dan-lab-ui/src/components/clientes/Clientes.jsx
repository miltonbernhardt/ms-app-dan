import ClientesForm from './ClientesForm';
import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import {useEffect, useState} from 'react';
import '../styles/Tabla.css';
import {getClientes, postCliente, putCliente} from '../../RestServices';
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";
import {toast} from 'react-toastify';

const clienteInicial = {
    razonSocial: '',
    habilitadoOnline: true,
    maxCuentaCorriente: 0.0,
    mail: '',
    cuit: ''
};

const Clientes = () => {
    const history = useHistory();
    const [cliente, setCliente] = useState(clienteInicial);
    const [listaClientes, setListaClientes] = useState([]);

    const fetchClientes = () => {
        getClientes().then(({data}) => {
            if (data) {
                setListaClientes(data)
            }
        });
    }

    useEffect(() => {
        if (localStorage.getItem("token")) {
            fetchClientes()
        } else {
            history.push(RUTAS.login[2])
        }
    }, [history]);


    const actualizarCliente = (atributo, valor) => {
        const clienteNuevo = {...cliente, [atributo]: valor};
        setCliente(clienteNuevo);
    }

    const validarCliente = !!(cliente.cuit && cliente.razonSocial && cliente.mail && cliente.maxCuentaCorriente)

    const saveOrUpdate = (e) => {
        e.preventDefault()
        if (validarCliente) {
            (!(cliente.id)) ? postCliente(cliente).then(({data, error="No se ha podido guardar el cliente"}) => {
                    if (data) {
                        toast.success("El cliente se ha guardado correctamente")
                        fetchClientes();
                        setCliente(clienteInicial);
                    } else {
                        toast.error(error)
                    }
                }) :
                putCliente(cliente).then(({data, error="No se ha podido actualizar el cliente"}) => {
                    if (data) {
                        toast.success("El cliente se ha actualizado correctamente")
                        fetchClientes();
                        setCliente(clienteInicial);
                    } else {
                        toast.error(error)
                    }
                });
        } else
            toast.error("Faltan campos del cliente")

    };

    const filasCliente = () => {
        if (listaClientes)
            return listaClientes.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.razonSocial}/>
                    <CeldaTabla dato={e.mail}/>
                    <CeldaBotonTabla titulo="Seleccionar" action={() => setCliente(e)}/>
                </FilaTabla>
            });
        else
            return <></>
    }

    const encabezado = ["ID Usuario", "Razón social", "Email", ""].map((e, i) => <EncabezadoTabla
        key={i}>{e}</EncabezadoTabla>)

    const limpiarCampos = (e) => {
        e.preventDefault()
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