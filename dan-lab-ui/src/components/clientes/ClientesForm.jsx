import "../styles/Form.css";
import {Button, Label} from "../FormComponents";

const ClientesForm = ({cliente, actualizarCliente, saveOrUpdate, clean}) => {

    const handleChange = evt => {
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.type === 'checkbox' ?
            evt.target.checked : evt.target.value;
        if (evt.target.validity.valid) actualizarCliente(nombreAtributo, valorAtributo);
    }

    return (
        <div className="panel-form-simple">
            <form className="form-box">
                <div className="form-row">
                    <Label value="Razón Social:"/>
                    <div className="form-input">
                        <input
                            name="razonSocial"
                            value={cliente.razonSocial}
                            onChange={handleChange}/>
                    </div>
                </div>
                <div className="form-row">
                    <Label value="Cuit:"/>
                    <div className="form-input">
                        <input
                            type="text"
                            pattern="[0-9]*"
                            name="cuit"
                            value={cliente.cuit}
                            onChange={handleChange}/>
                    </div>
                </div>
                <div className="form-row">
                    <Label value="Email:"/>
                    <div className="form-input">
                        <input
                            name="mail"
                            value={cliente.mail}
                            onChange={handleChange}/>
                    </div>
                </div>

                <div className="form-row">
                    <Label value="Máximo descubierto:"/>
                    <div className="form-input">
                        <input
                            type="text"
                            pattern="[0-9.]*"
                            name="maxCuentaCorriente"
                            value={cliente.maxCuentaCorriente}
                            onChange={handleChange}/>
                    </div>
                </div>
                <div className="form-row">
                    <Label value="Habilitado online:"/>
                    <div className="form-input" >
                        <input
                            name="habilitadoOnline"
                            type="checkbox"
                            checked={cliente.habilitadoOnline}
                            onChange={handleChange}/>
                    </div>
                </div>
                <div className="form-buttons">
                    <Button label="Guardar / Actualizar" action={saveOrUpdate}/>
                    <Button label="Limpiar" action={clean}/>
                </div>
            </form>
        </div>
    )
}


export default ClientesForm;