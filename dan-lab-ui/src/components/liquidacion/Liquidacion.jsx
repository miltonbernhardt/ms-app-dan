import { useState } from "react";
import LiquidacionForm from "./LiquidacionForm";
import '../Tabla';
import '../styles/Form.css';
import '../styles/Clientes.css';
import { getEmpleados } from "../services/EmpleadoSertvice";
import { CeldaTabla, Tabla, EncabezadoTabla, FilaTabla } from '../Tabla';
import { getLiquidaciones, getSueldo, postLiquidacionEmpleado, postLiquidacionTodos, postSueldo } from "../services/LiquidacionService";

const empleadoInicial = {
    nombre: ''
}

const sueldoInicial = {
    monto: 0,
    comision: 0
}

const Liquidacion = () => {

    const [listaLiquidacion, setListaLiquidacion] = useState([]);
    const [listaEmpleados, setListaEmpleados] = useState([]);
    const [empleado, setEmpleado] = useState(empleadoInicial);
    const [sueldo, setSueldo] = useState(sueldoInicial);

    const fetchEmpleados = async () => {
        getEmpleados().then(res => {
            setListaEmpleados(res.data)
        });
    }

    const fetchLiquidacion = async () => {
        getLiquidaciones().then(res => setListaLiquidacion(res.data));
    }

    const fetchSueldo = async (empleado) => {
        getSueldo(empleado).then(res => setSueldo(res.data));
    }

    useState(() => {
        fetchEmpleados();
        fetchLiquidacion();
    }, []);


    const liquidarEmpleado = () => {
        postLiquidacionEmpleado(empleado).then(() => fetchLiquidacion());
    }

    const liquidarTodos = () => {
        postLiquidacionTodos().then(() => fetchLiquidacion());
    }

    const actualizarEmpleado = (valorAtributo) => {
        const index = listaEmpleados.findIndex(e => e.nombre == valorAtributo);
        setEmpleado(listaEmpleados[index]);
        fetchSueldo(listaEmpleados[index]);
    }

    const actualizarCamposSueldo = (nombreAtributo, valorAtributo) => {
        const nuevoSueldo = { ...sueldo, [nombreAtributo]: valorAtributo };
        setSueldo(nuevoSueldo);
    }

    const actualizarSueldoEmpleado = () => {
        console.log(sueldo);
        postSueldo(sueldo).then(() => fetchSueldo());
    }

    const filasLiquidacion = listaLiquidacion.map((e, indice) => {
        return <FilaTabla clave={indice} >
            <CeldaTabla dato={e.id} />
            <CeldaTabla dato={
                listaEmpleados[listaEmpleados.findIndex(o => o.id == e.empleado.id)].nombre
            } />
            <CeldaTabla dato={e.fecha} />
            <CeldaTabla dato={e.monto} />
        </FilaTabla>
    });

    const encabezado = ["ID Liquidacion", "Empleado", "Fecha", "Monto"]
        .map((e) => {
            return <EncabezadoTabla>{e}</EncabezadoTabla>
        })

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
                <Tabla>
                    {encabezado}
                    {filasLiquidacion}
                </Tabla>
            </div>
        </div>
    );
}

export default Liquidacion
    ;