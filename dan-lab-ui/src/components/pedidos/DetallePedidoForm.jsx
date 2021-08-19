import '../FormComponents'
import CampoForm, { ComboForm } from '../FormComponents';

const DetallePedidoForm = ({ detallePedido, actualizarCampos, listaProductos, saveOrUpdate }) => {

    const handleChange = (evt) => {
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCampos(nombreAtributo, valorAtributo);
    }

    return (
        <form className="formbox container">

            <CampoForm
                label='Cantidad'
                pattern='[0-9.]*'
                name='cantidad'
                value={detallePedido.cantidad}
                onChange={handleChange} />

            <CampoForm
                label='Precio'
                name='precio'
                readOnly={true}
                value={detallePedido.cantidad * detallePedido.producto.precio} />

            <ComboForm
                label='Producto'
                name='producto'
                onChange={handleChange}
                value={detallePedido.producto.nombre}
                opciones={listaProductos.map(p => { return p.nombre })} />

            <div className="row">
                <div className="col-25" />
                <div className="col-75">
                    <button className="btn-wide" type="button" onClick={saveOrUpdate}>Guardar</button>
                </div>
            </div>
        </form >
    );
}

export default DetallePedidoForm;