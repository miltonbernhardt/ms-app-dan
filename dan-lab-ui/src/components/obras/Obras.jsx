import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import ObrasForm from "./ObrasForm";
import ClienteObrasForm from "./ClienteObrasForm";
import "../styles/Form.css";
import "../styles/Tabla.css";
import {useEffect, useState} from "react";
import {getClientes, getObras, postObra, putObra} from '../../RestServices';
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";
import {useAlert} from "react-alert";

const clienteInicial = {
    id: null,
    razonSocial: "",
    cuit: ""
}

const obraInicial = {
    id: null,
    descripcion: "",
    latitud: 0,
    longitud: 0,
    direccion: "",
    superficie: 0,
    tipoObra: "",
    cliente: clienteInicial
}

const Obras = () => {
    const history = useHistory();
    const alert = useAlert();
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
        getClientes().then(data => {
            if (data)
                setListaClientes(data)
        });
    }

    const fetchObras = () => {
        getObras().then(data => {
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
        const nuevaObra = {...obra, cliente: listaClientes[index]};
        setObra(nuevaObra);
    }

    const validateObra = () => {
        return !!(obra.descripcion && obra.latitud && obra.longitud && obra.superficie && obra.tipoObra);
    }

    const saveOrUpdate = () => {
        if (validateObra()) {
            obra.cliente = cliente

            const algoObra = () => {
                fetchObras()
            }
            !(obra.id) ? postObra(obra).then(algoObra) : putObra(obra).then(algoObra);
            setObra(obraInicial);
            setCliente(clienteInicial);
        } else {
            alert.error("Faltan indicar ciertos datos de la obra");
        }

    };

    const cleanObra = () => {
        setObra(obraInicial);
        setCliente(clienteInicial);
    }

    const filasObras = () => {
        if (listaObras) {
            return listaObras.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.descripcion}/>
                    <CeldaTabla dato={e.direccion}/>
                    <CeldaTabla dato={e.superficie}/>
                    <CeldaTabla dato={e.tipoObra}/>
                    <CeldaBotonTabla titulo="Seleccionar" accion={() => {
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