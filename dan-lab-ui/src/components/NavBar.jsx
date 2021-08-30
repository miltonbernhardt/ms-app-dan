import {Link} from "react-router-dom";
import {RUTAS} from "../App";

const NavBar = ({logout}) => {

    return (
        <nav>
            <ul className="nav-links">
                <Link className="nav-links" to={RUTAS.clientes}>
                    <li>Clientes</li>
                </Link>
                <Link className="nav-links" to={RUTAS.obras}>
                    <li>Obras</li>
                </Link>
                {/*<Link className="nav-links" to={RUTAS.pagos}>*/}
                {/*    <li>Pagos</li>*/}
                {/*</Link>*/}
                <Link className="nav-links" to={RUTAS.pedidos}>
                    <li>Pedidos</li>
                </Link>
                <Link className="nav-links" to={RUTAS.productos}>
                    <li>Productos</li>
                </Link>
                <Link className="nav-links" to={RUTAS.liquidacion}>
                    <li>Liquidación</li>
                </Link>
                <Link className="nav-links" to={RUTAS.login} onClick={() => logout()}>
                    <li>Cerrar Sesión</li>
                </Link>
            </ul>
        </nav>
    );
}

export default NavBar;