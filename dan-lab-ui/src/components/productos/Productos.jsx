import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import ProductosForm from './ProductosForm';
import '../styles/Form.css';
import {useEffect, useState} from 'react';
import {getProductos, getUnidades, postProducto, putProducto} from '../../RestServices';
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";
import {toast} from "react-toastify";

const unidadInicial = {
    descripcion: ''
}

const productoInicial = {
    nombre: '',
    descripcion: '',
    precio: '',
    stockActual: '',
    stockMinimo: '',
    unidad: unidadInicial
}

const Productos = () => {
    const history = useHistory();
    const [producto, setProducto] = useState(productoInicial);
    const [listaProductos, setListaProductos] = useState([]);
    const [listaUnidades, setListaUnidades] = useState([]);

    useEffect(() => {
        if (localStorage.getItem("token")) {
            fetchProductos();
        } else
            history.push(RUTAS.login)
    }, [history]);

    const fetchProductos = () => {
        getProductos().then(({data}) => {
            if (data)
                setListaProductos(data)
        });
        getUnidades().then(({data}) => {
            if (data)
                setListaUnidades(data)
        });
    }

    const actualizarProducto = (nombreAtributo, valorAtributo) => {
        if (nombreAtributo === 'unidad') valorAtributo = {...unidadInicial, descripcion: valorAtributo};
        const nuevoProducto = {...producto, [nombreAtributo]: valorAtributo};
        setProducto(nuevoProducto);
    }

    const validarProducto = !!(producto.nombre && producto.precio && producto.descripcion && producto.stockActual && producto.stockMinimo && producto.unidad);

    const saveOrUpdate = (e) => {
        e.preventDefault()

        if (validarProducto) {
            (!(producto.id)) ? postProducto(producto).then(({data, error = "No se ha podido guardar el producto"}) => {
                    if (data) {
                        toast.success("El producto se ha guardado correctamente")
                        fetchProductos();
                        setProducto(productoInicial);
                    } else {
                        toast.error(error)
                    }
                }) :
                putProducto(producto).then(({data, error = "No se ha podido actualizar el producto"}) => {
                    if (data) {
                        toast.success("El producto se ha actualizado correctamente")
                        fetchProductos();
                        setProducto(productoInicial);
                    } else {
                        toast.error(error)
                    }
                });
        } else
            toast.error("Faltan campos del producto")
    };

    const cleanProducto = () => {
        setProducto(productoInicial);
    }

    const filasProductos = () => {
        if (listaProductos && listaProductos.length > 0)
            return listaProductos.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.nombre}/>
                    <CeldaTabla dato={e.descripcion}/>
                    <CeldaTabla dato={e.precio}/>
                    <CeldaTabla dato={e.stockActual}/>
                    <CeldaTabla dato={e.stockMinimo}/>
                    <CeldaBotonTabla titulo="Seleccionar" action={() => setProducto(e)}/>
                </FilaTabla>
            });
        else
            return <></>
    }

    const encabezado = ["ID Producto", "Nombre", "Descripcion", "Precio", "Stock Actual", "Stock Minimo", ""]
        .map((e, i) => {
            return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
        })

    return (
        <>
            <h1>Gestión productos</h1>
            <div className="panel-form-simple">
                <ProductosForm
                    producto={producto}
                    unidades={listaUnidades}
                    actualizarCampos={actualizarProducto}
                    clean={cleanProducto}
                    saveOrUpdate={saveOrUpdate}/>
            </div>

            <div className="panel">
                <Tabla encabezado={encabezado} filas={filasProductos()}/>
            </div>
        </>
    );
}

export default Productos;