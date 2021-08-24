import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import ObrasForm from "./ObrasForm";
import ClienteObrasForm from "./ClienteObrasForm";
import "../styles/Form.css";
import "../styles/Tabla.css";
import {useEffect, useState} from "react";
import {getClientes, getObras, postObra, putObra} from '../../RestServices';

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

    const [obra, setObra] = useState(obraInicial);
    const [cliente, setCliente] = useState(clienteInicial);
    const [listaClientes, setListaClientes] = useState([]);
    const [listaObras, setListaObras] = useState([]);

    useEffect(() => {
        fetchClientes();
        fetchObras();
    }, []);

    const fetchClientes = () => {
        getClientes().then(data => setListaClientes(data));
    }

    const fetchObras = () => {
        getObras().then(data => setListaObras(data));
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

    const saveOrUpdate = () => {
        !(obra.id) ? postObra(obra).then(() => fetchObras()) :
            putObra(obra).then(() => fetchObras());
        setObra(obraInicial);
        setCliente(clienteInicial);
    };

    const cleanObra = () => {
        setObra(obraInicial);
        setCliente(clienteInicial);
    }

    const filasObras = listaObras.map((e, i) => {
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

    const encabezado = ["ID Obra", "Descripcion", "Direccion", "Superficie", "Tipo de Obra", ""]
        .map((e) => {
            return <EncabezadoTabla>{e}</EncabezadoTabla>
        })

    return (
        <div className="box">
            <div><h1>Gestion de Obras</h1></div>
            <div className="panelForm">
                <div className="panelFormAlta">

                    <ObrasForm
                        obra={obra}
                        actualizarCampos={actualizarObra}
                        clean={cleanObra}
                        saveOrUpdate={saveOrUpdate}/>
                </div>

                <div className="panelFormBusqueda">

                    <ClienteObrasForm
                        cliente={cliente}
                        actualizarCampos={actualizarCliente}
                        listaClientes={listaClientes}/>
                </div>
            </div>
            <div className="panel">
                <Tabla filas={filasObras} encabezado={encabezado}/>
            </div>
        </div>
    );
}

export default Obras;