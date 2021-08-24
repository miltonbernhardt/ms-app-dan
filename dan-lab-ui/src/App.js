import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { useState } from "react";
import Clientes from "./components/clientes/Clientes";
import Login from "./components/Login";
import Obras from "./components/obras/Obras"
import Productos from "./components/productos/Productos";
import Pedidos from './components/pedidos/Pedidos';
import Pagos from './components/pagos/Pagos';
import Liquidacion from './components/liquidacion/Liquidacion'
import NavBar from './components/NavBar';

const usuarioInicial = {
  id: 103,
  nombre: "dan2021",
  password: "dan2021",
  autenticado: false
}

function App() {

  const [usuario, setUsuario] = useState(usuarioInicial);

  const ingresar = () => {
    const nuevoUsuario = { ...usuario, autenticado: true };
    setUsuario(nuevoUsuario);
  }

  const actualizarUsuario = (nombreAtributo, valorAtributo) => {
    const nuevoUsuario = { ...usuario, [nombreAtributo]: valorAtributo };
    console.log(nuevoUsuario);
    setUsuario(nuevoUsuario);
    console.log(usuario);
  }

  return (
    <div className="App">
      <Router>
        <NavBar />
        <Switch>
          <Route exact path="/"  >
            <Login usuario={usuario} ingresar={ingresar} actualizarCampos={actualizarUsuario} />
          </Route>
          <Route path="/page/clientes" component={Clientes} />
          <Route path="/page/obras" component={Obras} />
          <Route path="/page/productos" component={Productos} />
          <Route path="/page/pedidos" component={Pedidos} />
          <Route path="/page/pagos" component={Pagos} />
          <Route path="/page/liquidacion" component={Liquidacion} name='Liquidación' />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
