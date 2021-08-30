import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import ObrasForm from "./ObrasForm";
import ClienteObrasForm from "./ClienteObrasForm";
import "../styles/Form.css";
import "../styles/Tabla.css";
import {useEffect, useState} from "react";
import {getClientes, getObras, postObra, putObra} from '../../RestServices';
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";
import {toast} from 'react-toastify';

const clienteInicial = {
    id: null,
    razonSocial: "",
    cuit: ""
}

const obraInicial = {
    id: null,
    descripcion: "",
    latitud: "",
    longitud: "",
    direccion: "",
    superficie: "",
    tipoObra: "REFORMA",
    cliente: clienteInicial
}

const Obras = () => {
    const history = useHistory();
    const [obra, setObra] = useState(obraInicial);
    const [cliente, setCliente] = useState(clienteInicial);
    const [listaClientes, setListaClientes] = useState([]);
    const [listaObras, setListaObras] = useState([]);

    useEffect(() => {
        if (localStorage.getItem("token")) {
            fetchClientes();
            fetchObras();
        } else
            history.push(RUTAS.login)
    }, [history]);

    const fetchClientes = () => {
        getClientes().then(({data}) => {
            if (data) {
                setListaClientes(data)
                setCliente(data[0])
            }
        });
    }

    const fetchObras = () => {
        getObras().then(({data}) => {
            if (data)
                setListaObras(data)
        });
    }

    const actualizarObra = (nombreAtributo, valorAtributo) => {
        const nuevaObra = {...obra, [nombreAtributo]: valorAtributo};
        setObra(nuevaObra);
    }

    const actualizarCliente = (nombreAtributo, valorAtributo) => {
        const index = listaClientes.findIndex(c => c.[nombreAtributo] === valorAtributo);
        setCliente(listaClientes[index])
        const nuevaObra = {...obra, cliente: cliente};
        setObra(nuevaObra);
    }

    const validateObra = () => {
        return !!(obra.descripcion && obra.latitud && obra.longitud && obra.superficie);
    }

    const saveOrUpdate = (e) => {
        e.preventDefault()
        if (validateObra()) {
            obra.cliente = cliente
            !(obra.id) ? postObra(obra).then(({data, error = "No se ha podido guardar la obra correctamente"}) => {
                if (data) {
                    toast.success("La obra se ha guardado correctamente")
                    fetchObras();
                    setObra(obraInicial);
                    setCliente(clienteInicial);
                } else
                    toast.error(error)
            }) : putObra(obra).then(({data, error = "No se ha podido actualizar la obra correctamente"}) => {
                if (data) {
                    setCliente(data)
                    toast.success("La obra se ha actualizado correctamente")
                    fetchObras();
                    setObra(obraInicial);
                    setCliente(clienteInicial);
                } else
                    toast.error(error)
            });

        } else {
            console.log({cliente})
            console.log({obra})
            toast.error("Faltan indicar ciertos datos de la obra");
        }

    };

    const cleanObra = () => {
        setObra(obraInicial);
        setCliente(clienteInicial);
    }

    const filasObras = () => {
        if (listaObras) {
            console.log(listaObras)
            return listaObras.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.descripcion}/>
                    <CeldaTabla dato={e.direccion}/>
                    <CeldaTabla dato={e.superficie}/>
                    <CeldaTabla dato={e.tipoObra}/>
                    <CeldaBotonTabla titulo="Seleccionar" action={() => {
                        setObra(e);
                        setCliente(e.cliente);
                    }}/>
                </FilaTabla>
            });
        } else {
            return <></>
        }
    }

    const encabezado = ["ID Obra", "Descripcion", "Direccion", "Superficie", "Tipo de Obra", ""]
        .map((e, i) => {
            return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
        })

    return (
        <>
            <h1>Gestion de Obras</h1>
            <div className="panel-form-doble">
                <ObrasForm
                    obra={obra}
                    actualizarCampos={actualizarObra}
                    clean={cleanObra}
                    saveOrUpdate={saveOrUpdate}/>
                <ClienteObrasForm
                    cliente={cliente}
                    actualizarCampos={actualizarCliente}
                    listaClientes={listaClientes}/>
            </div>
            <div className="panel">
                <Tabla encabezado={encabezado} filas={filasObras()}/>
            </div>
        </>
    );
}

export default Obras;