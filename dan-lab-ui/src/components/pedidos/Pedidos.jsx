import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla';
import PedidosForm from './PedidosForm';
import DetallePedidoForm from './DetallePedidoForm'
import '../styles/Form.css';
import {useEffect, useState} from "react";
import {
    deleteDetalle,
    getObras,
    getPedidos,
    getProductos,
    postDetalle,
    postPedido,
    putDetalle,
    putPedido
} from '../../RestServices';

import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";
import {toast} from "react-toastify";

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
    precio: '',
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
        if (localStorage.getItem("token")) {
            getPedidos().then(({data}) => {
                if (data)
                    setListaPedidos(data)
            })
            fetchProductos();
            fetchObras();
        } else
            history.push(RUTAS.login)

    }, [history]);

    const fetchPedidos = () => {
        getPedidos().then(({data}) => {
            if (data) {
                setListaPedidos(data)
                setListaDetalle(data.detalle)
                setDetalle(data.detalle ? data.detalle[0] : detallePedidoInicial)
            }
        })
    }

    const fetchProductos = () => {
        getProductos().then(({data}) => {
            if (data) {
                setListaProductos(data)
                detallePedido.producto = data[0]
                setDetalle(detallePedido)
            }
        });
    }

    const fetchObras = () => {
        getObras().then(({data}) => {
            if (data) {
                setListaObras(data)
                pedido.obra = data[0]
            }
        });
    }

    const actualizarPedido = (nombreAtributo, valorAtributo) => {
        if (nombreAtributo === 'obra') {
            const index = listaObras.findIndex(o => o.id == valorAtributo);
            const nuevoPedido = {...pedido, obra: listaObras[index]};
            setPedido(nuevoPedido);

        } else {
            const nuevoPedido = {...pedido, [nombreAtributo]: valorAtributo};
            setPedido(nuevoPedido);
        }
    }

    const validarPedido = !!(pedido.obra && pedido.estado && pedido.detalle && pedido.fechaPedido);

    const savePedido = () => {
        postPedido(pedido).then(({data, error = "No se ha podido guardar el pedido"}) => {
            if (data) {
                toast.success("El pedido se ha guardado correctamente")
                fetchPedidos();
                setPedido(pedidoInicial);
            } else {
                toast.error(error)
            }
        })
    }

    const saveOrUpdatePedido = (e) => {
        e.preventDefault()
        if (validarPedido) {
            (!(pedido.id)) ? savePedido() : putPedido(pedido).then(({data, error = "No se ha podido actualizar el pedido"}) => {
                console.log({data,  error})
                if (data) {
                    toast.success("El pedido se ha actualizado correctamente")
                    fetchPedidos();
                    setPedido(pedidoInicial);
                } else {
                    toast.error(error)
                }
            });
        } else {
            console.log(pedido)
            toast.error("Faltan campos del pedido")
        }

    };

    const cleanPedido = () => {
        setPedido(pedidoInicial);
        setListaDetalle([]);
        setDetalle(detallePedidoInicial);
    }

    const actualizarDetalle = (nombreAtributo, valorAtributo) => {
        console.log({nombreAtributo, valorAtributo})
        if (nombreAtributo === 'producto') {
            const index = listaProductos.findIndex(p => p.nombre === valorAtributo);
            detallePedido.producto = listaProductos[index]
            detallePedido.precio = (detallePedido.cantidad ?? 0) * (listaProductos[index] ? listaProductos[index].precio : 1)
            setDetalle(detallePedido)
        } else {
            const nuevoDetalle = {...detallePedido, [nombreAtributo]: valorAtributo};
            nuevoDetalle.precio = (detallePedido.cantidad ?? 1) * (detallePedido.producto.precio ?? 1)
            setDetalle(nuevoDetalle);
        }
    }

    const validarDetalle = !!(detallePedido.producto && detallePedido.precio >= 0 && detallePedido.cantidad);

    const updateDetalle = (e) => {
        e.preventDefault()
        if (pedido.id) {
            if (validarDetalle) {
                (!!(detallePedido.id)) ?
                    putDetalle(detallePedido, pedido)
                        .then(({data, error = "No se ha podido actualizar el detalle del pedido"}) => {
                            if (data) {
                                toast.success("El detalle del pedido se ha actualizado correctamente")
                                fetchPedidos();
                                setDetalle(detallePedidoInicial);
                            } else {
                                toast.error(error)
                            }
                        })
                    : postDetalle(detallePedido, pedido)
                        .then(({data, error = "No se ha podido guardar el detalle del pedido"}) => {
                            if (data) {
                                toast.success("El detalle del pedido se ha guardado correctamente")
                                fetchPedidos();
                                setDetalle(detallePedidoInicial);
                            } else {
                                toast.error(error)
                            }
                        });
            } else {
                console.log(detallePedido)
                toast.error("Faltan campos del detalle del pedido")
            }
        } else
            toast.error("Debe seleccionar un pedido al cual actualizarle el detalle")
    };


    const seleccionarPedido = (p) => {
        setPedido(p);
        setListaDetalle(p.detalle);
        setDetalle(detallePedidoInicial);
    }

    const eliminarDetalle = (detallePedido) => deleteDetalle(detallePedido)
        .then(({data, error = "No se ha podido eliminar el detalle del pedido"}) => {
            if (data) {
                toast.success("El detalle del pedido se ha eliminado correctamente")
                fetchPedidos();
                setDetalle(detallePedidoInicial);
            } else {
                toast.error(error)
            }
        })

    const encabezado = ["ID Pedido", "Fecha de Pedido", "ID Obra", "Estado", ""].map((e, i) => {
        return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
    })

    const encabezadoDetalle = ["ID Detalle", "Cantidad", "Precio", "Producto", "", ""].map((e, i) => {
        return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
    })

    const filasPedidos = () => {
        if (listaPedidos) {
            return listaPedidos.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.fechaPedido}/>
                    <CeldaTabla dato={e.obra.id}/>
                    <CeldaTabla dato={e.estado}/>
                    <CeldaBotonTabla titulo="Seleccionar" action={() => seleccionarPedido(e)}/>
                </FilaTabla>
            });
        } else
            return <></>
    }

    const filasDetalle = () => {
        if (listaDetalle) {
            return listaDetalle.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.cantidad}/>
                    <CeldaTabla dato={e.precio}/>
                    <CeldaTabla dato={e.producto.nombre}/>
                    <CeldaBotonTabla titulo="Seleccionar" action={() => setDetalle(e)}/>
                    <CeldaBotonTabla titulo="Eliminar" action={() => eliminarDetalle(e)}/>
                </FilaTabla>
            });
        }
        return <></>
    }


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
                    hayPedido={pedido && pedido.id}
                    detallePedido={detallePedido}
                    listaProductos={listaProductos}
                    actualizarCampos={actualizarDetalle}
                    update={updateDetalle}/>
            </div>
            <div className="table-double">
                <div className="table-div">
                    <h3>Pedidos</h3>
                    <Tabla encabezado={encabezado} filas={filasPedidos()}/>
                </div>
                <div className="table-div">
                    <h3>Detalle</h3>
                    <Tabla encabezado={encabezadoDetalle} filas={filasDetalle()}/>
                </div>
            </div>
        </>
    );
}
export default Pedidos;