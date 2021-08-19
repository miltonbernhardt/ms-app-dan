import React from 'react';
export const Tabla = ({ titulo, children }) => {

    return (
        <div >
            <h2>{titulo}</h2>
            <table>
                {children}
            </table>
        </div>
    )
}

export const EncabezadoTabla = ({ children }) => {
    return (
        <th>
            {children}
        </th>
    )
}
export const FilaTabla = ({ children, clave }) => {
    return (
        <tr key={clave}>
            {children}
        </tr>
    )
}
export const CeldaTabla = ({ dato }) => {
    return (
        <td className="celdaTabla">
            {dato}
        </td>
    )
}
export const CeldaBotonTabla = ({ titulo, accion }) => {
    return (
        <td className="celdaTabla">
            <button onClick={accion}>{titulo}</button>
        </td>
    )
}

export default Tabla;