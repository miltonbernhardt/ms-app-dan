import axios from 'axios';

export const DIRECCION_PEDIDO = 'localhost';
export const PUERTO_PEDIDO = '9002';
export const API_PEDIDO = '/api/pedido'
export const API_DETALLE_PEDIDO = '/api/detallepedido'

export const getPedidos = () => {
    const res = axios.get(`http://${DIRECCION_PEDIDO}:${PUERTO_PEDIDO}${API_PEDIDO}`)
        .catch(err => alert(err));
    return res;
}

export const postPedido = (pedido) => {
    const res = axios
        .post(`http://${DIRECCION_PEDIDO}:${PUERTO_PEDIDO}${API_PEDIDO}`, pedido)
        .catch(err => alert(err));
    return res;
}

export const putPedido = (pedido) => {
    const res = axios
        .put(`http://${DIRECCION_PEDIDO}:${PUERTO_PEDIDO}${API_PEDIDO}/${pedido.id}`, pedido)
        .catch(err => alert(err));
    return res;
}

export const postDetalle = (detallePedido, pedido) => {
    const res = axios
        .post(`http://${DIRECCION_PEDIDO}:${PUERTO_PEDIDO}${API_DETALLE_PEDIDO}/?idPedido=${pedido.id}`, detallePedido)
        .catch(err => alert(err));
    return res;
}

export const putDetalle = (detallePedido) => {
    const res = axios
        .put(`http://${DIRECCION_PEDIDO}:${PUERTO_PEDIDO}${API_DETALLE_PEDIDO}`, detallePedido)
        .catch(err => alert(err));
    return res;
}

export const deleteDetalle = (detallePedido) => {
    const res = axios
        .delete(`http://${DIRECCION_PEDIDO}:${PUERTO_PEDIDO}${API_DETALLE_PEDIDO}/id?idDetalle=${detallePedido.id}`)
        .catch(err => alert(err));
    return res;
}