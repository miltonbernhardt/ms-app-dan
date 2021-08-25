import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {useState} from "react";
import Clientes from "./components/clientes/Clientes";
import Login from "./components/login/Login";
import Obras from "./components/obras/Obras"
import Productos from "./components/productos/Productos";
import Pedidos from './components/pedidos/Pedidos';
import Pagos from './components/pagos/Pagos';
import Liquidacion from './components/liquidacion/Liquidacion'
import NavBar from './components/NavBar';
import {login} from "./RestServices";

import {useAlert} from 'react-alert'

const usuarioInicial = {
    username: "dan2021",
    password: "dan2021"
}

export const RUTAS = {
    login: "/",
    clientes: "/page/clientes",
    obras: "/page/obras",
    productos: "/page/productos",
    pedidos: "/page/pedidos",
    pagos: "/page/pagos",
    liquidacion: "/page/liquidacion"
}

function App() {

    const alert = useAlert()

    const [usuario, setUsuario] = useState(usuarioInicial);

    const [logged, setLogged] = useState(false);

    const ingresar = () => {
        login(usuario).then((data) => {
            if (data && data.accessToken) {
                alert.success('Se ha logueado correctamente!')
                window.accessToken = data.accessToken;// mala práctica pero hay apuro
                //TODO redigir a un menú
                setLogged(true)
            } else {
                alert.error('Revise usuario y/o contraseña')
                setLogged(false)
            }
        })
    }

    const actualizarUsuario = ({username, password}) => {
        setUsuario({username, password});
    }

    return (
        <div className="App">
            <Router>
                <NavBar logged={logged}/>
                <Switch>
                    <Route exact path={RUTAS.login}>
                        <Login username={usuario.username} password={usuario.password} ingresar={ingresar}
                               actualizarCampos={actualizarUsuario} logged={logged}/>
                    </Route>
                    <Route path={RUTAS.clientes} component={Clientes}/>
                    <Route path={RUTAS.obras} component={Obras}/>
                    <Route path={RUTAS.productos} component={Productos}/>
                    <Route path={RUTAS.pedidos} component={Pedidos}/>
                    <Route path={RUTAS.pagos} component={Pagos}/>
                    <Route path={RUTAS.liquidacion} component={Liquidacion} name='Liquidación'/>
                </Switch>
            </Router>
        </div>
    );
}

export default App;
