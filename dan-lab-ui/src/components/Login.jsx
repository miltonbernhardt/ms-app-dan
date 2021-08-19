import "./styles/Form.css";

const Login = ({ usuario, ingresar, actualizarCampos }) => {

    const handleChange = (evt) => {
        evt.preventDefault();
        // console.log(evt.target.value);
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        actualizarCampos(nombreAtributo, valorAtributo);
    }

    return (
        <div className="container">
            <h1 className="form-step"> Login </h1>
            <form >
                <div className="row">
                    <div className="col-25 form-label">
                        <label>Nombre de usuario</label>
                    </div>
                    <div className="col-75">
                        <input
                            name="nombre"
                            type="text"
                            placeholder="usuario"
                            value={usuario.nombre}
                            onChange={handleChange} />
                    </div>
                </div>
                <div className="row">
                    <div className="col-25 form-label">
                        <label>Contraseña</label>
                    </div>
                    <div className="col-75">
                        <input
                            name="password"
                            type="password"
                            placeholder="Contraseña"
                            value={usuario.password}
                            onChange={handleChange} />
                    </div>
                </div>
            </form>
            <div className="row">
                <div className="col-25" />
                <div className="col-75">
                    <button className="btn-wide" onClick={ingresar}>Ingresar</button>
                </div>
            </div>
        </div>
    );
}

export default Login;