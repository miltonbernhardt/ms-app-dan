export const CampoForm = ({value, onChange, label, name, pattern, readOnly}) => {
    return (
        <div className="row">
            <Label value={label}/>
            <div className="col-75">
                <input name={name}
                       pattern={pattern}
                       value={value}
                       onChange={onChange}
                       readOnly={readOnly}/>
            </div>
        </div>
    );
}

export const ComboForm = ({opciones, value, onChange, label, name}) => {

    const options = opciones ?
        opciones.map((o, index) => <option key={index} value={o}>{o}</option>)
        : <></>

    return <div className="row">
        <Label value={label}/>
        <div className="col-75">
            <select
                name={name}
                value={value}
                onChange={onChange}>
                {options}
            </select>
        </div>
    </div>
}

export const Label = ({value}) => <div className="col-25 form-label">
    <label>{value}</label>
</div>


export default CampoForm;