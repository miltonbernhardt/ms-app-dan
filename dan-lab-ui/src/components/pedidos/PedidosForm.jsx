import CampoForm, { ComboForm } from "../FormComponents";

const PedidosForm = ({ pedido, actualizarCampos, clean, saveOrUpdate, verDetalle, obras }) => {

    const handleChange = (evt) => {
        evt.preventDefault();
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCampos(nombreAtributo, valorAtributo);
    }

    const estadosPedido =
        ['NUEVO', 'CONFIRMADO', 'PENDIENTE', 'CANCELADO', 'ACEPTADO', 'RECHAZADO', 'EN_PREPARACION', 'ENTREGADO'];
    return (
        <form className="form-box">
            <div className="form-row">
                <h2>Pedido</h2>
            </div>
            <CampoForm
                label='Fecha:'
                name='fechaPedido'
                value={pedido.fechaPedido}
                onChange={handleChange} />
            <ComboForm
                label='Obra:'
                name='obra'
                onChange={handleChange}
                opciones={obras.map(o => { return o.id })} />
            <ComboForm
                label='Estado:'
                name='estado'
                value={pedido.estado}
                onChange={handleChange}
                opciones={estadosPedido} />

            <div className="form-buttons">
                <div className="form-button">
                    <button type="button" onClick={clean}>Limpiar</button>
                </div>
                <div className="form-button">
                    <button type="button" onClick={verDetalle}>Ver Detalle</button>
                </div>
                <div className="form-button">
                    <button type="button" onClick={saveOrUpdate}>Guardar</button>
                </div>
            </div>
        </form >
    );
}

export default PedidosForm;