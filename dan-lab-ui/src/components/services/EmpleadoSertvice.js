import axios from 'axios';

export const DIRECCION_CLIENTE = 'localhost';
export const PUERTO_CLIENTE = '9000';
export const API_EMPLEADO = '/api/empleado'

export const getEmpleados = () => {
    const res = axios.get(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_EMPLEADO}`)
        .catch(err => alert(err));
    return res;
}

export const postEmpleado = (empleado) => {
    const res = axios
        .post(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_EMPLEADO}`, empleado)
        .catch(err => alert(err));
    return res;
}

export const putEmpleado = (empleado) => {
    const res = axios
        .put(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_EMPLEADO}`, empleado)
        .catch(err => alert(err));
    return res;
}