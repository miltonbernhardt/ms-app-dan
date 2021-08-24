import {Tabla, FilaTabla, CeldaTabla, CeldaBotonTabla, EncabezadoTabla} from '../Tabla';
import ProductosForm from './ProductosForm';
import '../styles/Form.css';
import '../styles/Clientes.css';
import {useEffect, useState} from 'react';
import {getClientes, getProductos, getUnidades, postProducto, putProducto} from '../../RestServices';

const unidadInicial = {
    descripcion: ''
}

const productoInicial = {
    nombre: '',
    descripcion: '',
    precio: 0,
    stockActual: 0,
    stockMinimo: 0,
    unidad: unidadInicial
}

const Productos = () => {

    const [producto, setProducto] = useState(productoInicial);
    const [listaProductos, setListaProductos] = useState([]);
    const [listaUnidades, setListaUnidades] = useState([]);

    useEffect(() => {
        fetchProductos();
    }, []);

    const fetchProductos = () => {
        getProductos().then(data => setListaProductos(data));
        getUnidades().then(data => setListaUnidades(data));
    }

    const actualizarProducto = (nombreAtributo, valorAtributo) => {
        if (nombreAtributo === 'unidad') valorAtributo = {...unidadInicial, descripcion: valorAtributo};
        const nuevoProducto = {...producto, [nombreAtributo]: valorAtributo};
        setProducto(nuevoProducto);
    }

    const saveOrUpdate = () => {
        !(producto.id) ? postProducto(producto).then(() => fetchProductos()) :
            putProducto(producto).then(() => fetchProductos());
        setProducto(productoInicial);
    };

    const cleanProducto = () => {
        setProducto(productoInicial);
    }

    const filasProductos = () => {
        if (listaProductos)
            return listaProductos.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.nombre}/>
                    <CeldaTabla dato={e.descripcion}/>
                    <CeldaTabla dato={e.precio}/>
                    <CeldaTabla dato={e.stockActual}/>
                    <CeldaTabla dato={e.stockMinimo}/>
                    <CeldaBotonTabla titulo="Seleccionar" accion={() => setProducto(e)}/>
                </FilaTabla>
            });
        else
            return <></>
    }

    const encabezado = ["ID Producto", "Nombre", "Descripcion", "Precio", "Stock Actual", "Stock Minimo", ""]
        .map((e) => {
            return <EncabezadoTabla>{e}</EncabezadoTabla>
        })

    return (
        <div className="box">
            <div><h1>Productos</h1></div>
            <div className="panel">
                <ProductosForm
                    producto={producto}
                    unidades={listaUnidades}
                    actualizarCampos={actualizarProducto}
                    clean={cleanProducto}
                    saveOrUpdate={saveOrUpdate}/>
            </div>
            <div className="panel">
                <Tabla>
                    {encabezado}
                    {filasProductos}
                </Tabla>
            </div>
        </div>
    );
}

export default Productos;