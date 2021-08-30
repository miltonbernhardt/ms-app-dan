import {ComboForm} from "../FormComponents";

const ClienteObrasForm = ({cliente, actualizarCampos, listaClientes}) => {

    const handleChange = (evt) => {
        const nombreAtributo = evt.target.name;
        const valorAtributo = evt.target.value;
        if (evt.target.validity.valid) actualizarCampos(nombreAtributo, valorAtributo);
    }

    return (
        <form className="form-box">
            <h3>Cliente</h3>

            <ComboForm
                label='Razon Social'
                placeholder='Razon Social'
                name='razonSocial'
                value={cliente.razonSocial}
                onChange={handleChange}
                opciones={listaClientes.map(c => {
                    return c.razonSocial
                })}/>

            <ComboForm
                label='CUIT'
                placeholder='CUIT'
                name='cuit'
                value={cliente.cuit}
                onChange={handleChange}
                opciones={listaClientes.map(c => {
                    return c.cuit
                })}/>

        </form>
    );
}

export default ClienteObrasForm;