import {useEffect, useState} from 'react';
import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla'
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";

const Pagos = () => {
    const history = useHistory();

    const [listaPedidos, setListaPedidos] = useState([]);

    useEffect(() => {
        if (!localStorage.getItem("token")) {
            history.push(RUTAS.login)
        }
    }, [history]);

    const abonarPedido = () => {
        console.log("Abonar pedido listo")
    }

    const filasPedidos = () => {
        if (listaPedidos) {
            return listaPedidos.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.fechaPedido}/>
                    <CeldaTabla dato={e.obra}/>
                    <CeldaTabla dato={e.estado}/>
                    <CeldaBotonTabla titulo="Abonar" accion={() => abonarPedido(e)}/>
                </FilaTabla>
            });
        } else return <></>
    }

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