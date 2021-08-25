import {useState} from "react";
import LiquidacionForm from "./LiquidacionForm";
import '../Tabla';
import '../styles/Form.css';
import '../styles/Clientes.css';
import {CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import {
    getEmpleados,
    getLiquidaciones,
    getSueldo,
    postLiquidacionEmpleado,
    postLiquidacionTodos,
    postSueldo
} from "../../RestServices";
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";

const empleadoInicial = {
    nombre: ''
}

const sueldoInicial = {
    monto: 0,
    comision: 0
}

const Liquidacion = () => {
    const history = useHistory();
    const [listaLiquidacion, setListaLiquidacion] = useState([]);
    const [listaEmpleados, setListaEmpleados] = useState([]);
    const [empleado, setEmpleado] = useState(empleadoInicial);
    const [sueldo, setSueldo] = useState(sueldoInicial);

    const fetchEmpleados = () => {
        getEmpleados().then(data => {
            if (data)
                setListaEmpleados(data)
        });
    }

    const fetchLiquidacion = () => {
        getLiquidaciones().then(data => {
            if (data)
                setListaLiquidacion(data)
        });
    }

    const fetchSueldo = (empleado) => {
        getSueldo(empleado).then(data => {
            if (data)
                setSueldo(data)
        });
    }

    useState(() => {
        if (localStorage.getItem("token")) {
            fetchEmpleados();
            fetchLiquidacion();
        } else
            history.push(RUTAS.login)

    }, [history]);

    const liquidarEmpleado = () => {
        postLiquidacionEmpleado(empleado).then(() => fetchLiquidacion());
    }

    const liquidarTodos = () => {
        postLiquidacionTodos().then(() => fetchLiquidacion());
    }

    const actualizarEmpleado = (valorAtributo) => {
        const index = listaEmpleados.findIndex(e => e.nombre === valorAtributo);
        setEmpleado(listaEmpleados[index]);
        fetchSueldo(listaEmpleados[index]);
    }

    const actualizarCamposSueldo = (nombreAtributo, valorAtributo) => {
        const nuevoSueldo = {...sueldo, [nombreAtributo]: valorAtributo};
        setSueldo(nuevoSueldo);
    }

    const actualizarSueldoEmpleado = () => {
        console.log(sueldo);
        postSueldo(sueldo).then(() => {
            fetchSueldo()
        });
    }


    const filasLiquidacion = () => {
        if (listaLiquidacion)
            return listaLiquidacion.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={
                        listaEmpleados[listaEmpleados.findIndex(o => o.id === e.empleado.id)].nombre
                    }/>
                    <CeldaTabla dato={e.fecha}/>
                    <CeldaTabla dato={e.monto}/>
                </FilaTabla>
            });
        else
            return <></>
    }

    const encabezado = ["ID Liquidacion", "Empleado", "Fecha", "Monto"].map((e) =>
        <EncabezadoTabla>{e}</EncabezadoTabla>)

    return (
        <div className='box'>
            <div><h3>Liquidacion</h3></div>

            <div className="panelForm">
                <div className="panelFormAlta">
                    <LiquidacionForm
                        empleado={empleado}
                        listaEmpleados={listaEmpleados}
                        actualizarCampos={actualizarEmpleado}
                        actualizarCamposSueldo={actualizarCamposSueldo}
                        actualizarSueldoEmpleado={actualizarSueldoEmpleado}
                        liquidarEmpleado={liquidarEmpleado}
                        liquidarTodos={liquidarTodos}
                        sueldoEmpleado={sueldo}
                    />
                </div>
            </div>
            <div className="panelForm">
                <Tabla encabezado={encabezado} filas={filasLiquidacion}/>
            </div>
        </div>
    );
}

export default Liquidacion