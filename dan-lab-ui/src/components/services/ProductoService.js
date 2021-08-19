import axios from 'axios';

export const DIRECCION_PRODUCTO = 'localhost';
export const PUERTO_PRODUCTO = '9001';
export const API_PRODUCTO = '/api/producto'

export const getProductos = () => {
    const res = axios.get(`http://${DIRECCION_PRODUCTO}:${PUERTO_PRODUCTO}${API_PRODUCTO}`)
        .catch(err => alert(err));
    return res;
}

export const getUnidades = () => {
    const res = axios.get(`http://${DIRECCION_PRODUCTO}:${PUERTO_PRODUCTO}${API_PRODUCTO}/unidades`)
        .catch(err => alert(err));
    return res;
}

export const postProducto = (producto) => {
    const res = axios
        .post(`http://${DIRECCION_PRODUCTO}:${PUERTO_PRODUCTO}${API_PRODUCTO}`, producto)
        .catch(err => alert(err));
    return res;
}

export const putProducto = (producto) => {
    const res = axios
        .put(`http://${DIRECCION_PRODUCTO}:${PUERTO_PRODUCTO}${API_PRODUCTO}`, producto)
        .catch(err => alert(err));
    return res;
}