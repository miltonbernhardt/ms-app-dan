import { Link } from "react-router-dom";
import './styles/App.css'

const NavBar = () => {
    return (
        <nav>
            <ul className="nav-links">
                <Link className="nav-links" to='/clientes'>
                    <li>Clientes</li>
                </Link>
                <Link className="nav-links" to='/obras'>
                    <li>Obras</li>
                </Link>
                <Link className="nav-links" to='/pagos'>
                    <li>Pagos</li>
                </Link>
                <Link className="nav-links" to='/pedidos'>
                    <li>Pedidos</li>
                </Link>
                <Link className="nav-links" to='/productos'>
                    <li>Productos</li>
                </Link>
                <Link className="nav-links" to='/liquidacion'>
                    <li>Liquidacion</li>
                </Link>
                <Link className="nav-links" to='/'>
                    <li>Login</li>
                </Link>
            </ul>
        </nav>
    );
}

export default NavBar;