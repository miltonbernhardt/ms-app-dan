import "../styles/Form.css";

const Label = ({value}) => {
    return (
        <div className="col-25 form-label">
            <label>{value}</label>
        </div>
    )
}

const ClientesForm = ({cliente, actualizarCliente, saveOrUpdate, clean}) => {

    const handleChange = evt => {
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.type === 'checkbox' ?
            evt.target.checked : evt.target.value;
        if (evt.target.validity.valid) actualizarCliente(nombreAtributo, valorAtributo);
    }

    return (
        <form className="form-box container">
            <div className="row">
                <Label value="Razon Social"/>
                <div className="col-75">
                    <input
                        name="razonSocial"
                        value={cliente.razonSocial}
                        onChange={handleChange}/>
                </div>
            </div>
            <div className="row">
                <Label value="Cuit"/>
                <div className="col-75">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="cuit"
                        value={cliente.cuit}
                        onChange={handleChange}/>
                </div>
            </div>
            <div className="row">
                <Label value="Email"/>
                <div className="col-75">
                    <input
                        name="mail"
                        value={cliente.mail}
                        onChange={handleChange}/>
                </div>
            </div>

            <div className="row">
                <Label value="Maximo Descubierto"/>
                <div className="col-75">
                    <input
                        type="text"
                        pattern="[0-9.]*"
                        name="maxCuentaCorriente"
                        value={cliente.maxCuentaCorriente}
                        onChange={handleChange}/>
                </div>
            </div>
            <div className="row">
                <Label value="Habilitado Online"/>
                <div className="col-75">
                    <input
                        name="habilitadoOnline"
                        type="checkbox"
                        checked={cliente.habilitadoOnline}
                        onChange={handleChange}/>
                </div>
            </div>
            <div className="row">
                <Button label="Cancelar action" action={clean}/>
                <Button label="Guardar" action={() => saveOrUpdate()}/>
            </div>
        </form>
    )
}

const Button = ({action, label}) =>
    <div className="col-50">
        <button type="button" onClick={action}>{label}</button>
    </div>

export default ClientesForm;