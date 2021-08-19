import "../styles/Form.css";

const ClientesForm = ({ cliente, actualizarCliente, saveOrUpdate, clean }) => {

    const handleChange = evt => {
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.type === 'checkbox' ?
            evt.target.checked : evt.target.value;
        if (evt.target.validity.valid) actualizarCliente(nombreAtributo, valorAtributo);
    }

    return (
        <form className="form-box container">
            <div className="row">
                <div className="col-25 form-label">
                    <label>Razon Social</label>
                </div>
                <div className="col-75">
                    <input
                        name="razonSocial"
                        value={cliente.razonSocial}
                        onChange={handleChange} />
                </div>
            </div>
            <div className="row">
                <div className="col-25 form-label">
                    <label >Cuit</label>
                </div>
                <div className="col-75">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="cuit"
                        value={cliente.cuit}
                        onChange={handleChange} />
                </div>
            </div>
            <div className="row">
                <div className="col-25 form-label">
                    <label>Email</label>
                </div>
                <div className="col-75">
                    <input
                        name="mail"
                        value={cliente.mail}
                        onChange={handleChange} />
                </div>
            </div>

            <div className="row">
                <div className="col-25 form-label">
                    <label>Maximo Descubierto</label>
                </div>
                <div className="col-75">
                    <input
                        type="text"
                        pattern="[0-9.]*"
                        name="maxCuentaCorriente"
                        value={cliente.maxCuentaCorriente}
                        onChange={handleChange} />
                </div>
            </div>
            <div className="row">
                <div className="col-25 form-label">
                    <label>Habilitado Online</label>
                </div>
                <div className="col-75">
                    <input
                        name="habilitadoOnline"
                        type="checkbox"
                        checked={cliente.habilitadoOnline}
                        onChange={handleChange} />
                </div>
            </div>
            <div className="row">
                <div className="col-50 ">
                    <button type="button" onClick={clean}>Cancelar</button>
                </div>
                <div className="col-50">
                    <button type="button" onClick={saveOrUpdate}>Guardar</button>
                </div>
            </div>
        </form >
    )
}
export default ClientesForm;