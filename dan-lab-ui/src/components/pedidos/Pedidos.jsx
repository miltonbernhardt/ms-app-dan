import {Tabla, FilaTabla, CeldaTabla, CeldaBotonTabla, EncabezadoTabla} from '../Tabla';
import PedidosForm from './PedidosForm';
import DetallePedidoForm from './DetallePedidoForm'
import '../styles/Form.css';
import {useEffect, useState} from "react";
import {
    getPedidos,
    postPedido,
    putPedido,
    postDetalle,
    putDetalle,
    deleteDetalle,
    getProductos,
    getObras
} from '../../RestServices';

import { useHistory } from "react-router-dom";
import {RUTAS} from "../../App";

const pedidoInicial = {
    id: null,
    fechaPedido: '',
    obra: '',
    estado: 'NUEVO',
    detalle: []
}

const detallePedidoInicial = {
    id: null,
    cantidad: '',
    precio: 0,
    producto: ''
}

const Pedidos = () => {
    const history = useHistory();

    const [pedido, setPedido] = useState(pedidoInicial);
    const [listaPedidos, setListaPedidos] = useState([]);

    const [detallePedido, setDetalle] = useState(detallePedidoInicial);
    const [listaDetalle, setListaDetalle] = useState([]);
    const [listaProductos, setListaProductos] = useState([]);
    const [listaObras, setListaObras] = useState([]);

    useEffect(() => {
        if (localStorage.getItem("token")){
            fetchPedidos();
            fetchProductos();
            fetchObras();
        }
        else
            history.push(RUTAS.login)

    }, [listaPedidos, listaDetalle, history]);

    const fetchPedidos = () => {
        getPedidos().then(data => {
            if (data)
                setListaPedidos(data)
        })
    }

    const fetchProductos = () => {
        getProductos().then(data => {
            if (data)
                setListaProductos(data)
        });
    }

    const fetchObras = () => {
        getObras().then(data => {
            if (data)
                setListaObras(data)
        });
    }

    const actualizarPedido = (nombreAtributo, valorAtributo) => {
        if (nombreAtributo === 'obra') {
            const index = listaObras.findIndex(o => o.id === valorAtributo);
            const nuevoPedido = {...pedido, obra: listaObras[index]};
            setPedido(nuevoPedido);
        } else {
            const nuevoPedido = {...pedido, [nombreAtributo]: valorAtributo};
            setPedido(nuevoPedido);
        }
    }

    const saveOrUpdatePedido = () => {
        !(pedido.id) ? postPedido(pedido).then(() => fetchPedidos()) :
            putPedido(pedido).then(() => fetchPedidos());
        cleanPedido();
    };

    const cleanPedido = () => {
        setPedido(pedidoInicial);
        setListaDetalle([]);
        setDetalle(detallePedidoInicial);
    }

    const actualizarDetalle = (nombreAtributo, valorAtributo) => {
        if (nombreAtributo === 'producto') {
            const index = listaProductos.findIndex(p => p.nombre === valorAtributo);
            const nuevoDetalle = {
                ...detallePedido,
                producto: listaProductos[index],
                precio: (detallePedido.cantidad ?? 0) * (listaProductos[index] ? listaProductos[index].precio : 1)
            };
            setDetalle(nuevoDetalle)
        } else {
            const nuevoDetalle = {...detallePedido, [nombreAtributo]: valorAtributo};
            setDetalle(nuevoDetalle);
        }
    }

    const saveOrUpdateDetalle = () => {
        !(detallePedido.id) ? postDetalle(detallePedido, pedido).then(() => fetchPedidos()) :
            putDetalle(detallePedido).then(() => fetchPedidos());
    };

    const filasPedidos = () => {
        if (listaPedidos){
            listaPedidos.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.fechaPedido}/>
                    <CeldaTabla dato={e.obra.id}/>
                    <CeldaTabla dato={e.estado}/>
                    <CeldaBotonTabla titulo="Seleccionar" accion={() => seleccionarPedido(e)}/>
                </FilaTabla>
            });
        }
        else
            return <></>
    }

    const encabezado = ["ID Pedido", "Fecha de Pedido", "ID Obra", "Estado"]
        .map((e, i) => {
            return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
        })

    const seleccionarPedido = (p) => {
        setPedido(p);
        setListaDetalle(p.detalle);
        setDetalle(detallePedidoInicial);
    }

    const eliminarDetalle = (d) => {
        deleteDetalle(d).then(() => fetchPedidos());
    }

    const filasDetalle = listaDetalle.map((e, i) => {
        return <FilaTabla key={i}>
            <CeldaTabla dato={e.id}/>
            <CeldaTabla dato={e.cantidad}/>
            <CeldaTabla dato={e.precio}/>
            <CeldaTabla dato={e.producto.nombre}/>
            <CeldaBotonTabla titulo="Eliminar" accion={() => eliminarDetalle(e)}/>
            <CeldaBotonTabla titulo="Seleccionar" accion={() => setDetalle(e)}/>
        </FilaTabla>
    });

    const encabezadoDetalle = ["ID Detalle", "Cantidad", "Precio", "Producto", "Eliminar", "Seleccionar"]
        .map((e, i) => {
            return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
        })

    return (
        <>
            <h1>Gestión pedidos</h1>
            <div className="panel-form-doble">
                <PedidosForm
                    pedido={pedido}
                    actualizarCampos={actualizarPedido}
                    clean={cleanPedido}
                    saveOrUpdate={saveOrUpdatePedido}
                    obras={listaObras}/>
                <DetallePedidoForm
                    detallePedido={detallePedido}
                    listaProductos={listaProductos}
                    actualizarCampos={actualizarDetalle}
                    saveOrUpdate={saveOrUpdateDetalle}/>
            </div>
            <div className="panel">
                <div><h3>Detalle</h3></div>
                <Tabla encabezado={encabezadoDetalle} filas={filasDetalle}/>
                <div><h3>Pedidos</h3></div>
                <Tabla encabezado={encabezado} filas={filasPedidos()}/>
            </div>
        </>
    );
}
export default Pedidos;