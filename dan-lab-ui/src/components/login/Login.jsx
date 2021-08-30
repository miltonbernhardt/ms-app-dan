import "../styles/Form.css";
import {Label} from "../FormComponents";
import {useState} from "react";
import {login} from "../../RestServices";
import {toast} from 'react-toastify';

const Login = ({logged, setLogged}) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const ingresar = () => {
        if (!logged) {
            login({username, password}).then((data) => {
                if (data && data.accessToken) {
                    setLogged(true)
                    localStorage.setItem('token', data.accessToken);
                    localStorage.setItem('username', username);
                    toast.success('Se ha logueado correctamente!')
                } else {
                    toast.error('Revise usuario y/o contraseña')
                    setLogged(false)
                    localStorage.removeItem('token');
                    localStorage.removeItem('username');
                }
            })
        }
    }

    function handleEnter(event) {
        if (event.key === 'Enter') {
            const form = event.target.form;
            const index = Array.prototype.indexOf.call(form, event.target);
            form.elements[index + 1].focus();
            event.preventDefault();
        }
    }

    if (logged) {
        return <h1>Bienvenido {localStorage.getItem("username")}!</h1>
    } else {
        return (
            <div className="container">
                <h1 className="form-step"> Login </h1>
                <form id='formLogin'>
                    <div className="col-75">
                        <Label value="Nombre de usuario"/>
                        <div className="col-75">
                            <input
                                name="nombre"
                                type="text"
                                placeholder="Username (dan2021)"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                onKeyPress={e => handleEnter(e)}/>
                        </div>
                    </div>
                    <div className="col-75">
                        <Label value="Contraseña"/>
                        <div className="col-75">
                            <input
                                name="password"
                                type="password"
                                placeholder="Password (dan2021)"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                onKeyPress={event => {
                                    if (event.key === 'Enter') {
                                        ingresar()
                                    }
                                }}/>
                        </div>
                    </div>
                </form>
                <div className="col-75 button-login">
                    <div>
                        <button className="btn-wide" onClick={ingresar}>Ingresar</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;