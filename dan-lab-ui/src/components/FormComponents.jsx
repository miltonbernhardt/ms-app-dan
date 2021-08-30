import React from "react";

export const CampoForm = ({value, onChange, label, name, pattern, readOnly}) => {
    return (
        <div className="form-row">
            <Label value={label}/>
            <div className="form-input">
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
        opciones.map((option, index) => <option key={index} value={option}>{option}</option>)
        : <></>

    return <div className="form-row">
        <Label value={label}/>
        <div className="form-input">
            <select
                name={name}
                value={value}
                onChange={onChange}>
                {options}
            </select>
        </div>
    </div>
}

export const Label = ({value}) => <label className="form-label">{value}</label>

export const Button = ({action, label}) =>
    <div className="form-button">
        <button onClick={(e) => action(e)}>{label}</button>
    </div>


export default CampoForm;