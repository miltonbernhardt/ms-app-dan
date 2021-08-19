import Select from "react-select";

export const CampoForm = ({ value, onChange, label, name, pattern, readOnly }) => {
    return (
        <div className="row">
            <div className="col-25 form-label">
                <label>{label}</label>
            </div>
            <div className="col-75">
                <input name={name}
                    pattern={pattern}
                    value={value}
                    onChange={onChange}
                    readOnly={readOnly} />
            </div>
        </div>
    );
}

export const ComboForm = ({ opciones, value, onChange, label, name }) => {
    return <div className="row">
        <div className="col-25 form-label">
            <label >{label}</label>
        </div>
        <div className="col-75">
            <select
                name={name}
                value={value}
                onChange={onChange} >
                {opciones.map((o, index) => {
                    return <option key={index} value={o} >{o}</option>
                })}
            </select>
        </div>
    </div>
}

export const ReactComboForm = ({ opciones, value, onChange, label, name, placeholder }) => {
    return <div className="row">
        <div className="col-25 form-label">
            <label >{label}</label>
        </div>
        <div className="col-75">
            <Select
                placeholder={placeholder}
                name={name}
                value={value}
                onChange={onChange}
                options={opciones} />
        </div>
    </div>
}

export default CampoForm;