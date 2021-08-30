import '../FormComponents'
import CampoForm, { ComboForm } from '../FormComponents'

const LiquidacionForm = ({ empleado, actualizarCampos, listaEmpleados, liquidarTodos, liquidarEmpleado, actualizarCamposSueldo, actualizarSueldoEmpleado, sueldoEmpleado }) => {

    const handleChange = (evt) => {
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCampos(valorAtributo);
    }
    const handleChangeSueldo = (evt) => {
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCamposSueldo(nombreAtributo, valorAtributo);
    }

    return (
        <form className='form-box'>
            <ComboForm
                label="Empleado"
                name="nombre"
                value={empleado.nombre}
                onChange={handleChange}
                opciones={listaEmpleados.map(e => { return e.nombre })}
            />
            <CampoForm
                label="Monto"
                name="monto"
                pattern="[0-9.]*"
                value={sueldoEmpleado.monto}
                onChange={handleChangeSueldo} />
            <CampoForm
                label="Porcentaje de comisión:"
                name="comision"
                pattern="[0-9.]*"
                value={sueldoEmpleado.comision}
                onChange={handleChangeSueldo}
            />
            <div className="form-buttons">
                <div className="form-button">
                    <button type="button" onClick={actualizarSueldoEmpleado}>Actualizar Sueldo</button>
                </div>
                <div className="form-button">
                    <button type="button" onClick={liquidarEmpleado}>Liquidar Empleado</button>
                </div>
                <div className="form-button">
                    <button type="button" onClick={liquidarTodos}>Liquidar a todos</button>
                </div>
            </div>
        </form>
    );
}

export default LiquidacionForm;