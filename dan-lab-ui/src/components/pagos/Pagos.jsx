import {useEffect, useState} from 'react';
import {CeldaBotonTabla, CeldaTabla, EncabezadoTabla, FilaTabla, Tabla} from '../Tabla'
import {useHistory} from "react-router-dom";
import {RUTAS} from "../../App";
import {getPedidos} from "../../RestServices";

const Pagos = () => {
    const history = useHistory();

    const [listaPedidos, setListaPedidos] = useState([]);

    const abonarPedido = () => {
        console.log("Abonar pedido listo")
    }

    const fetchPedidos = () => {
        // getPedidos().then(({data}) => {
        //     if (data) {
        //         setListaPedidos(data)
        //     }
        // });
    }

    useEffect(() => {
        if (localStorage.getItem("token")) {
            getPedidos().then(() => fetchPedidos());
        } else
            history.push(RUTAS.login)
    }, [history]);



    const filasPedidos = () => {
        if (listaPedidos) {
            return listaPedidos.map((e, i) => {
                return <FilaTabla key={i}>
                    <CeldaTabla dato={e.id}/>
                    <CeldaTabla dato={e.fechaPedido}/>
                    <CeldaTabla dato={e.obra}/>
                    <CeldaTabla dato={e.estado}/>
                    <CeldaBotonTabla titulo="Abonar" action={() => abonarPedido(e)}/>
                </FilaTabla>
            });
        } else return <></>
    }

    const encabezado = ["ID Pedido", "Fecha de Pedido", "ID Obra", "Estado", ""]
        .map((e, i) => {
            return <EncabezadoTabla key={i}>{e}</EncabezadoTabla>
        })

    return (
        <>
            <h1>Gestión de pagos</h1>
            <div className="panel">
                <h2>Pedidos</h2>
                <Tabla encabezado={encabezado} filas={filasPedidos()}/>
            </div>
        </>);
}

export default Pagos;