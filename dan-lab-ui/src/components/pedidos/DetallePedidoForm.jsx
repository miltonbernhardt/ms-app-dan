import '../FormComponents'
import CampoForm, {ComboForm} from '../FormComponents';

const DetallePedidoForm = ({detallePedido, actualizarCampos, listaProductos, update, hayPedido}) => {

    const handleChange = (evt) => {
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCampos(nombreAtributo, valorAtributo);
    }

    return (
        <form className="form-box">
            <div className="form-row">
                <h2>Detalle pedido</h2>
            </div>
            <CampoForm
                label='Cantidad:'
                pattern='[0-9.]*'
                name='cantidad'
                value={detallePedido.cantidad}
                onChange={handleChange}/>

            <CampoForm
                label='Precio:'
                name='precio'
                readOnly={true}
                value={(detallePedido.cantidad ?? 0) * (detallePedido.producto?.precio ?? 1)}/>

            <ComboForm
                label='Producto:'
                name='producto'
                onChange={handleChange}
                value={detallePedido.producto?.nombre}
                opciones={(listaProductos && listaProductos.length) ? listaProductos.map(p => {
                    return p.nombre
                }):[]}/>

            {
                hayPedido ?  <div className="form-buttons">
                    <div className="form-button">
                        <button className="btn-wide" type="button" onClick={update}>Guardar</button>
                    </div>
                </div>
                    : <></>
            }

        </form>
    );
}

export default DetallePedidoForm;