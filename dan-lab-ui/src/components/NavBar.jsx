import { Link } from "react-router-dom";
import './styles/App.css'

const NavBar = () => {
    return (
        <nav>
            <ul className="nav-links">
                <Link className="nav-links" to='/page/clientes'>
                    <li>Clientes</li>
                </Link>
                <Link className="nav-links" to='/page/obras'>
                    <li>Obras</li>
                </Link>
                <Link className="nav-links" to='/page/pagos'>
                    <li>Pagos</li>
                </Link>
                <Link className="nav-links" to='/page/pedidos'>
                    <li>Pedidos</li>
                </Link>
                <Link className="nav-links" to='/page/productos'>
                    <li>Productos</li>
                </Link>
                <Link className="nav-links" to='/page/liquidacion'>
                    <li>Liquidación</li>
                </Link>
                <Link className="nav-links" to='/'>
                    <li>Login</li>
                </Link>
            </ul>
        </nav>
    );
}

export default NavBar;