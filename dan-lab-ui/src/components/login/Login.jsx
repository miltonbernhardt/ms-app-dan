import "../styles/Form.css";
import {Label} from "../FormComponents";
import {useState} from "react";

const Login = ({username, password, ingresar, actualizarCampos, logged}) => {
    const [user, setUsername] = useState(username);
    const [pass, setPassword] = useState(password);

    const changePassword = (evt) => {
        setPassword(evt.target.value)
        actualizarCampos({username: user, password: pass});
    }

    const changeUsername = (evt) => {
        setUsername(evt.target.value)
        actualizarCampos({username: user, password: pass});
    }

    if (logged){
        return <h1>Bienvenido!</h1>
    }

    return (
        <div className="container">
            <h1 className="form-step"> Login </h1>
            <form>
                <div className="row">
                    <Label value="Nombre de usuario"/>
                    <div className="col-75">
                        <input
                            name="nombre"
                            type="text"
                            placeholder="usuario"
                            value={user}
                            onChange={changeUsername}/>
                    </div>
                </div>
                <div className="row">
                    <Label value="Contraseña"/>
                    <div className="col-75">
                        <input
                            name="password"
                            type="password"
                            placeholder="Contraseña"
                            value={pass}
                            onChange={changePassword}/>
                    </div>
                </div>
            </form>
            <div className="row">
                <Label/>
                <div className="col-75">
                    <button className="btn-wide" onClick={ingresar}>Ingresar</button>
                </div>
            </div>
        </div>
    );
}

export default Login;