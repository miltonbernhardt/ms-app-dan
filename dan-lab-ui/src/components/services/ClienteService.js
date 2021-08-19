import axios from 'axios';

export const DIRECCION_CLIENTE = 'localhost';
export const PUERTO_CLIENTE = '9000';
export const API_CLIENTE = '/api/cliente'

export const getClientes = () => {
    const res = axios.get(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_CLIENTE}`)
        .catch(err => alert(err));
    return res;
}

export const postCliente = (cliente) => {
    const res = axios
        .post(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_CLIENTE}`, cliente)
        .catch(err => alert(err));
    return res;
}

export const putCliente = (cliente) => {
    const res = axios
        .put(`http://${DIRECCION_CLIENTE}:${PUERTO_CLIENTE}${API_CLIENTE}`, cliente)
        .catch(err => alert(err));
    return res;
}