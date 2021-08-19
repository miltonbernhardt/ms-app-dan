import axios from 'axios';

export const DIRECCION_CLIENTE = 'localhost';
export const PUERTO_CLIENTE = '9000';
export const API_OBRA = '/api/obra'

export const getObras = () => {
    const res = axios.get(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_OBRA}`)
        .catch(err => alert(err));
    return res;
}

export const postObra = (obra) => {
    const res = axios
        .post(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_OBRA}`, obra)
        .catch(err => alert(err));
    return res;
}

export const putObra = (obra) => {
    const res = axios
        .put(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_OBRA}/${obra.id}`, obra)
        .catch(err => alert(err));
    return res;
}