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
  id: null,
  nombre: "",
  password: "",
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
          <Route path="/clientes" component={Clientes} />
          <Route path="/obras" component={Obras} />
          <Route path="/productos" component={Productos} />
          <Route path="/pedidos" component={Pedidos} />
          <Route path="/pagos" component={Pagos} />
          <Route path="/liquidacion" component={Liquidacion} name='Liquidacion' />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
