import {Label} from "../FormComponents";

const ObrasForm = ({obra, actualizarCampos, saveOrUpdate, clean}) => {

    const handleChange = (evt) => {
        evt.preventDefault();
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCampos(nombreAtributo, valorAtributo);
    }

    return (
        <form className="form-box">
            <h3>Obra</h3>
            <div className="form-row">
                <Label value="Descripción:"/>
                <div className="form-input">
                    <input name="descripcion"
                           value={obra.descripcion}
                           onChange={handleChange}/>
                </div>
            </div>
            <div className="form-row">
                <Label value="Latitud:"/>
                <div className="form-input">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="latitud"
                        value={obra.latitud}
                        onChange={handleChange}/>
                </div>
            </div>
            <div className="form-row">
                <Label value="Longitud:"/>
                <div className="form-input">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="longitud"
                        value={obra.longitud}
                        onChange={handleChange}/>
                </div>
            </div>

            <div className="form-row">
                <Label value="Dirección:"/>
                <div className="form-input">
                    <input
                        name="direccion"
                        value={obra.direccion}
                        onChange={handleChange}/>
                </div>
            </div>

            <div className="form-row">
                <Label value="Superficie:"/>
                <div className="form-input">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="superficie"
                        value={obra.superficie}
                        onChange={handleChange}/>
                </div>
            </div>

            <div className="form-row">
                <Label value="Tipo de obra:"/>
                <div className="form-input">
                    <select name="tipoObra" value={obra.tipoObra} onChange={handleChange}>
                        <option value="REFORMA">Reforma</option>
                        <option value="CASA">Casa</option>
                        <option value="EDIFICIO">Edificio</option>
                        <option value="VIAL">Vial</option>
                    </select>
                </div>
            </div>

            <div className="form-buttons">
                <div className="form-button">
                    <button type="button" onClick={(e) => saveOrUpdate(e)}>Guardar</button>
                </div>
                <div className="form-button">
                    <button type="button" onClick={clean}>Limpiar</button>
                </div>
            </div>
        </form>
    );
}

export default ObrasForm;