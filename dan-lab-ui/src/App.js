import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {useEffect, useState} from "react";
import Clientes from "./components/clientes/Clientes";
import Login from "./components/login/Login";
import Obras from "./components/obras/Obras"
import Productos from "./components/productos/Productos";
import Pedidos from './components/pedidos/Pedidos';
import Pagos from './components/pagos/Pagos';
import Liquidacion from './components/liquidacion/Liquidacion'
import NavBar from './components/NavBar';
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export const RUTAS = {
    login: "/",
    clientes: "/page/clientes",
    obras: "/page/obras",
    productos: "/page/productos",
    pedidos: "/page/pedidos",
    pagos: "/page/pagos",
    liquidacion: "/page/liquidacion",
    logout: "/logout"
}

function App() {
    const [logged, setLogged] = useState(false);

    useEffect(() => {
        setLogged(localStorage.getItem("token") !== null && localStorage.getItem("token") !== undefined)
    }, []);

    const logout = () => {
        toast.success('Sesión cerrada')
        setLogged(false)
        localStorage.removeItem('token')
    }

    return (
        <>
            <ToastContainer
                position="bottom-right"
                autoClose={2500}
                hideProgressBar
                newestOnTop
                closeOnClick={false}
                rtl={false}
                pauseOnFocusLoss={false}
                draggable
                pauseOnHover={false}
            />
            <div className="App">
                <Router>
                    {logged ? <NavBar logout={logout}/> : <></>}
                    <Switch>
                        <Route exact path={RUTAS.login}>
                            <Login logged={logged} setLogged={setLogged}/>
                        </Route>
                        <Route path={RUTAS.clientes} component={Clientes}/>
                        <Route path={RUTAS.obras} component={Obras}/>
                        <Route path={RUTAS.productos} component={Productos}/>
                        <Route path={RUTAS.pedidos} component={Pedidos}/>
                        {/*<Route path={RUTAS.pagos} component={Pagos}/>*/}
                        <Route path={RUTAS.liquidacion} component={Liquidacion}/>
                    </Switch>
                </Router>
            </div>
        </>
    );
}

export default App;
