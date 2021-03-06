import {useState} from "react";
import LiquidacionForm from "./LiquidacionForm";
import '../Tabla';
import '../styles/Form.css';
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
import {toast} from "react-toastify";

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
        getEmpleados().then(({data}) => {
            if (data) {
                setListaEmpleados(data)
                setEmpleado(data[0])
            }
        });
    }

    const fetchLiquidacion = () => {
        getLiquidaciones().then(({data}) => {
            if (data)
                setListaLiquidacion(data)
        });
    }

    const fetchSueldo = (empleado) => {
        if (empleado && empleado.id) {
            getSueldo(empleado).then(({data}) => {
                if (data) {
                    setSueldo(data)
                    toast.success("Se ha actualizado el sueldo correctamente al monto de " + data.monto + " y comisión " + data.comision)
                }
            });
        }
    }

    useState(() => {
        if (localStorage.getItem("token")) {
            fetchEmpleados();
            fetchLiquidacion();
        } else
            history.push(RUTAS.login)

    }, [history]);

    const liquidarEmpleado = () => {
        if (empleado && empleado.id) {
            postLiquidacionEmpleado(empleado).then(() => fetchLiquidacion());
        }
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
                        (listaEmpleados) ? listaEmpleados[listaEmpleados.findIndex(o => o.id == e.empleado.id)] ? listaEmpleados[listaEmpleados.findIndex(o => o.id == e.empleado.id)].nombre : "-" : "-"

                    }/>
                    <CeldaTabla dato={e.fecha}/>
                    <CeldaTabla dato={e.monto}/>
                </FilaTabla>
            });
        else
            return <></>
    }

    const encabezado = ["ID Liquidación", "Empleado", "Fecha", "Monto"].map((e, i) =>
        <EncabezadoTabla key={i}>{e}</EncabezadoTabla>)

    return (
        <>
            <h1>Gestión de liquidaciones</h1>

            <div className="panel-form-simple">
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
            <Tabla encabezado={encabezado} filas={filasLiquidacion()}/>
        </>
    );
}

export default Liquidacion