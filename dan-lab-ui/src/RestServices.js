import axios from 'axios';

const RAIZ_URL = `http://localhost`;

const PORT_GATEWAY = '8181'
const PORT_USUARIO = '9000'
const PORT_PRODUCTO = '9001'
const PORT_PEDIDO = '9002'
const PORT_LIQUIDACION = '9004'

const API_PRODUCTO = 'producto'
const API_PEDIDO = 'pedido'
const API_OBRA = 'obra'
const API_DETALLE_PEDIDO = 'detallepedido'
const API_LIQUIDACION = 'liquidacion'
const API_CLIENTE = 'cliente'
const API_EMPLEADO = 'empleado'

const headers = {
    'Access-Control-Allow-Origin': "*",
    'Access-Control-Allow-Methods': "GET, POST, PATCH, PUT, DELETE, OPTIONS",
    'Access-Control-Allow-Headers': "Origin, X-Requested-With, Content-Type, Accept"
}

// --- USER METHODS ---
export const login = async ({username, password}) => {
    const URL = `${RAIZ_URL}:8181/login`;
    try {
        const response = await axios.post(URL, {username, password}, {headers})
        const {data: dataResponse, status} = response
        if (typeof dataResponse === 'string' || status !== 200) {
            if (status === 404) {
                localStorage.removeItem("token")
            }
        } else {
            return dataResponse
        }
    } catch (error) {
        const message = `${error.response ? error.message : error}`;
        console.log("Error en el login: ", {method: 'login', URL, message})
    }
}
export const register = async (user) => POST(`${API_OBRA}`, user);

// --- OBRA METHODS ---
export const getObras = async () => GET(PORT_USUARIO, `${API_OBRA}`);
export const postObra = async (obra) => POST(PORT_USUARIO, `${API_OBRA}`, obra);
export const putObra = async (obra) => PUT(PORT_USUARIO, `${API_OBRA}/${obra.id}`, obra);

// --- PEDIDO METHODS ---
export const getPedidos = async () => GET(PORT_PEDIDO, `${API_PEDIDO}`);
export const postPedido = async (pedido) => POST(PORT_PEDIDO, `${API_PEDIDO}`, pedido);
export const putPedido = async (pedido) =>  PUT(PORT_PEDIDO, `${API_PEDIDO}/?id=${pedido.id}&nuevoEstado=${pedido.estado}`);


// --- DETALLE PEDIDO METHODS ---
export const postDetalle = async (detallePedido, pedido) => POST(PORT_PEDIDO, `${API_DETALLE_PEDIDO}/?idPedido=${pedido.id}`, detallePedido);
export const putDetalle = async (detallePedido) => PUT(PORT_PEDIDO, `${API_DETALLE_PEDIDO}`, detallePedido);
export const deleteDetalle = async (detallePedido) => DELETE(PORT_PEDIDO, `${API_DETALLE_PEDIDO}/id?idDetalle=${detallePedido.id}`);

// --- PRODUCTO METHODS ---
export const getProductos = async () => GET(PORT_PRODUCTO, `${API_PRODUCTO}`);
export const getUnidades = () => GET(PORT_PRODUCTO, `${API_PRODUCTO}/unidades`);
export const postProducto = async (producto) => POST(PORT_PRODUCTO, `${API_PRODUCTO}`, producto);
export const putProducto = async (producto) => PUT(PORT_PRODUCTO, `${API_PRODUCTO}`, producto);

// --- LIQUIDACION METHODS ---
export const getLiquidaciones = async () => GET(PORT_LIQUIDACION, `${API_LIQUIDACION}`);
export const postLiquidacionTodos = async () => POST(PORT_LIQUIDACION, `${API_LIQUIDACION}/todos`);
export const postLiquidacionEmpleado = async (empleado) => POST(PORT_LIQUIDACION, `${API_LIQUIDACION}/empleado?idEmpleado=${empleado.id}`);
export const postSueldo = async (sueldo) => POST(PORT_LIQUIDACION, `${API_LIQUIDACION}/sueldo`, sueldo);
export const getSueldo = async (empleado) => GET(PORT_LIQUIDACION, `${API_LIQUIDACION}/empleado/sueldo?idEmpleado=${empleado.id}`);

// --- CLIENTE METHODS ---
export const getClientes = async () => GET(PORT_USUARIO, `${API_CLIENTE}`);
export const postCliente = async (cliente) => POST(PORT_USUARIO, `${API_CLIENTE}`, cliente);
export const putCliente = async (cliente) => PUT(PORT_USUARIO, `${API_CLIENTE}`, cliente);

// --- EMPLEADO METHODS ---
export const getEmpleados = async () => GET(PORT_USUARIO, `${API_EMPLEADO}`);
export const postEmpleado = async (empleado) => POST(PORT_USUARIO, `${API_EMPLEADO}`, empleado);
export const putEmpleado = async (empleado) => PUT(PORT_USUARIO, `${API_EMPLEADO}`, empleado);

const getNewHeader = () => {
    const token = localStorage.getItem('token');
    return {...headers, Authorization: `Bearer ${token}`}
}

// --- GENERAL METHODS ---
export const POST = async (port, postfixUrl, data) => {
    const URL = `${RAIZ_URL}:${port}/api/${postfixUrl}`;

    const method = `POST ${URL}`;

    console.log({request: method, data})
    try {
        const response = await axios.post(URL, data, {headers: getNewHeader()})
        const {data: dataResponse, status} = response
        if (status !== 200) {
            if (status === 401) {
                localStorage.removeItem("token")
                localStorage.removeItem("username")
            }
            throw new Error(dataResponse)
        } else {
            console.log({response: method, dataResponse})
            return {data: dataResponse}
        }
    } catch (error) {
        const message = `${error.response ? error.response.data : error}`;
        console.error({method, message, error: error.response})
        return {error: message}
    }
}

export const PUT = async (port, postfixUrl, data) => {
    console.log({postfixUrl})
    const URL = `${RAIZ_URL}:${port}/api/${postfixUrl}`;

    const method = `PUT ${URL}`
    console.log({request: method, data})

    try {
        const response = await axios.put(URL, data, {headers: getNewHeader()})
        const {data: dataResponse, status} = response
        if (status !== 200) {
            if (status === 401) {
                localStorage.removeItem("token")
                localStorage.removeItem("username")
            }
            throw new Error(dataResponse)
        } else {
            console.log({response: method, dataResponse})
            return {data: dataResponse}
        }
    } catch (error) {
        const message = `${error.response ? error.response.data : error}`;
        console.error({method, URL, message, error: error.response})
        return {error: message}
    }
}

export const GET = async (port, postfixUrl) => {
    const URL = `${RAIZ_URL}:${port}/api/${postfixUrl}`;

    const method = `GET ${URL}`

    console.log({request: method})

    try {
        const response = await axios.get(URL, {headers: getNewHeader()})

        const {data: dataResponse, status} = response

        if (status !== 200) {
            if (status === 401) {
                localStorage.removeItem("token")
                localStorage.removeItem("username")
            }
            throw new Error(dataResponse)
        } else {
            console.log({response: method, dataResponse})
            return {data: dataResponse}
        }
    } catch (error) {
        const message = `${error.response ? error.response.data : error}`;
        console.error({method, URL, message, error: error.response})
        return {error: message}
    }
}

export const DELETE = async (port, postfixUrl) => {
    const URL = `${RAIZ_URL}:${port}/api/${postfixUrl}`;
    const method = `DELETE ${URL}`
    console.log({request: method})

    try {
        const response = await axios.delete(URL, {headers: getNewHeader()})

        const {data: dataResponse, status} = response

        if (status !== 200) {
            if (status === 401) {
                localStorage.removeItem("token")
                localStorage.removeItem("username")
            }
            throw new Error(dataResponse)
        } else {
            console.log({response: method, dataResponse})
            return {data: dataResponse}
        }
    } catch (error) {
        const message = `${error.response ? error.response.data : error}`;
        console.error({method, URL, message, error: error.response})
        return {error: message}
    }
}