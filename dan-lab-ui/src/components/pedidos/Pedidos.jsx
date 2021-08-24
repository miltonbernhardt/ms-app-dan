import {Tabla, FilaTabla, CeldaTabla, CeldaBotonTabla, EncabezadoTabla} from '../Tabla';
import PedidosForm from './PedidosForm';
import DetallePedidoForm from './DetallePedidoForm'
import '../styles/Form.css';
import '../styles/Clientes.css';
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

    const [pedido, setPedido] = useState(pedidoInicial);
    const [listaPedidos, setListaPedidos] = useState([]);

    const [detallePedido, setDetalle] = useState(detallePedidoInicial);
    const [listaDetalle, setListaDetalle] = useState([]);
    const [listaProductos, setListaProductos] = useState([]);
    const [listaObras, setListaObras] = useState([]);

    useEffect(() => {
        fetchPedidos();
        fetchProductos();
        fetchObras();
    }, [listaPedidos, listaDetalle]);

    const fetchPedidos = () => {
        getPedidos().then(data => setListaPedidos(data))
    }

    const fetchProductos = () => {
        getProductos().then(data => setListaProductos(data));
    }

    const fetchObras = () => {
        getObras().then(data => setListaObras(data));
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
                precio: detallePedido.cantidad * listaProductos[index].precio
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

    const filasPedidos = listaPedidos.map((e, i) => {
        return <FilaTabla key={i}>
            <CeldaTabla dato={e.id}/>
            <CeldaTabla dato={e.fechaPedido}/>
            <CeldaTabla dato={e.obra.id}/>
            <CeldaTabla dato={e.estado}/>
            <CeldaBotonTabla titulo="Seleccionar" accion={() => seleccionarPedido(e)}/>
        </FilaTabla>
    });

    const encabezado = ["ID Pedido", "Fecha de Pedido", "ID Obra", "Estado", ""]
        .map((e) => {
            return <EncabezadoTabla>{e}</EncabezadoTabla>
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

    const encabezadoDetalle = ["ID Detalle", "Cantidad", "Precio", "Producto", "", ""]
        .map((e) => {
            return <EncabezadoTabla>{e}</EncabezadoTabla>
        })

    return (
        <div className="box">
            <div><h1>Pedidos</h1></div>
            <div className="panelForm">
                <div className="panelFormAlta">
                    <PedidosForm
                        pedido={pedido}
                        actualizarCampos={actualizarPedido}
                        clean={cleanPedido}
                        saveOrUpdate={saveOrUpdatePedido}
                        obras={listaObras}/>
                </div>
                <div className="panelFormAlta">
                    <DetallePedidoForm
                        detallePedido={detallePedido}
                        listaProductos={listaProductos}
                        actualizarCampos={actualizarDetalle}
                        saveOrUpdate={saveOrUpdateDetalle}/>
                </div>
            </div>
            <div className="panel">
                <div><h3>Detalle</h3></div>
                <Tabla>
                    {encabezadoDetalle}
                    {filasDetalle}
                </Tabla>
                <div><h3>Pedidos</h3></div>
                <Tabla encabezado={encabezado} filas={filasPedidos}/>
            </div>
        </div>
    );
}
export default Pedidos;