import {Link} from "react-router-dom";
import './styles/App.css'
import {RUTAS} from "../App";

const NavBar = ({logged}) => {
    return (
        <nav>
            <ul className="nav-links">
                {logged ?
                    <>
                        <Link className="nav-links" to={RUTAS.clientes}>
                            <li>Clientes</li>
                        </Link>
                        <Link className="nav-links" to={RUTAS.obras}>
                            <li>Obras</li>
                        </Link>
                        <Link className="nav-links" to={RUTAS.pagos}>
                            <li>Pagos</li>
                        </Link>
                        <Link className="nav-links" to={RUTAS.pedidos}>
                            <li>Pedidos</li>
                        </Link>
                        <Link className="nav-links" to={RUTAS.pedidos}>
                            <li>Productos</li>
                        </Link>
                        <Link className="nav-links" to={RUTAS.liquidacion}>
                            <li>Liquidación</li>
                        </Link>
                    </>
                    : <></>
                }
            </ul>
        </nav>
    );
}

export default NavBar;