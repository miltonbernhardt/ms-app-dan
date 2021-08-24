import {useState} from 'react';
import {Tabla, EncabezadoTabla, FilaTabla, CeldaTabla, CeldaBotonTabla} from '../Tabla'

const Pagos = () => {

    const [listaPedidos, setListaPedidos] = useState([]);

    const abonarPedido = () => {
        console.log("Abonar pedido listo")
    }

    const filasPedidos = listaPedidos.map((e, i) => {
        return <FilaTabla key={i}>
            <CeldaTabla dato={e.id}/>
            <CeldaTabla dato={e.fechaPedido}/>
            <CeldaTabla dato={e.obra}/>
            <CeldaTabla dato={e.estado}/>
            <CeldaBotonTabla titulo="Abonar" accion={() => abonarPedido(e)}/>
        </FilaTabla>
    });

    const encabezado = ["ID Pedido", "Fecha de Pedido", "ID Obra", "Estado", ""]
        .map((e) => {
            return <EncabezadoTabla>{e}</EncabezadoTabla>
        })

    return (
        <div className="box">
            <div><h1>Pagos</h1></div>
            <div className="panel">
                <div><h3>Pedidos</h3></div>
                <Tabla encabezado={encabezado} filas={filasPedidos}/>
            </div>
        </div>);
}

export default Pagos;