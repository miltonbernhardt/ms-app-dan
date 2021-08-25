import {Label} from "../FormComponents";

const ObrasForm = ({ obra, actualizarCampos, saveOrUpdate, clean }) => {

    const handleChange = (evt) => {
        evt.preventDefault();
        // console.log(evt.target.value);
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCampos(nombreAtributo, valorAtributo);
    }

    return (
        <form className="form-box container">
            <h3>Obra</h3>
            <div className="row">
                <Label value="Descripción"/>
                <div className="col-75">
                    <input name="descripcion"
                        value={obra.descripcion}
                        onChange={handleChange} />
                </div>
            </div>
            <div className="row">
                <Label value="Latitud"/>
                <div className="col-75">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="latitud"
                        value={obra.latitud}
                        onChange={handleChange} />
                </div>
            </div>
            <div className="row">
                <Label value="Longitud"/>
                <div className="col-75">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="longitud"
                        value={obra.longitud}
                        onChange={handleChange} />
                </div>
            </div>

            <div className="row">
                <Label value="Dirección"/>
                <div className="col-75">
                    <input
                        name="direccion"
                        value={obra.direccion}
                        onChange={handleChange} />
                </div>
            </div>

            <div className="row">
                <Label value="Superficie"/>
                <div className="col-75">
                    <input
                        type="text"
                        pattern="[0-9]*"
                        name="superficie"
                        value={obra.superficie}
                        onChange={handleChange} />
                </div>
            </div>

            <div className="row">
                <Label value="Tipo de Obra"/>
                <div className="col-75">
                    <select name="tipoObra" value={obra.tipoObra} onChange={handleChange} >
                        <option value="REFORMA">Reforma</option>
                        <option value="CASA">Casa</option>
                        <option value="EDIFICIO">Edificio</option>
                        <option value="VIAL">Vial</option>

                    </select>
                </div>
            </div>

            <div className="row">
                <div className="col-50 ">
                    <button type="button" onClick={clean}>Limpiar</button>
                </div>
                <div className="col-50">
                    <button type="button" onClick={saveOrUpdate}>Guardar</button>
                </div>
            </div>
        </form >
    );
}

export default ObrasForm;