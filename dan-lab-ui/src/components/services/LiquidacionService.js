import axios from 'axios';

export const DIRECCION_LIQUIDACION = 'localhost';
export const PUERTO_LIQUIDACION = '9004';
export const API_LIQUIDACION = '/api/liquidacion'

export const getLiquidaciones = () => {
    const res = axios.get(`http://${DIRECCION_LIQUIDACION}:${PUERTO_LIQUIDACION}${API_LIQUIDACION}`)
        .catch(err => alert(err));
    return res;
}

export const postLiquidacionTodos = () => {
    const res = axios
        .post(`http://${DIRECCION_LIQUIDACION}:${PUERTO_LIQUIDACION}${API_LIQUIDACION}/todos`)
        .catch(err => alert(err));
    return res;
}

export const postLiquidacionEmpleado = (empleado) => {
    const res = axios
        .post(`http://${DIRECCION_LIQUIDACION}:${PUERTO_LIQUIDACION}${API_LIQUIDACION}/empleado?idEmpleado=${empleado.id}`)
        .catch(err => alert(err));
    return res;
}

export const postSueldo = (sueldo) => {
    const res = axios
        .post(`http://${DIRECCION_LIQUIDACION}:${PUERTO_LIQUIDACION}${API_LIQUIDACION}/empleado/sueldo`, sueldo)
        .catch(err => alert(err));
    return res;
}

export const getSueldo = (empleado) => {
    const res = axios
        .get(`http://${DIRECCION_LIQUIDACION}:${PUERTO_LIQUIDACION}${API_LIQUIDACION}/empleado/sueldo?idEmpleado=${empleado.id}`)
        .catch(err => alert(err));
    return res;
}
