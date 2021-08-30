import React from 'react';

export const Tabla = ({encabezado, filas}) => {

    return (
        <div className="table-box">
            <table>
                <thead>
                    <tr>
                        {encabezado}
                    </tr>
                </thead>
                <tbody>
                     {filas}
                </tbody>
            </table>
        </div>
    )
}

export const EncabezadoTabla = ({children}) => {
    return (
        <th>
            {children}
        </th>
    )
}
export const FilaTabla = ({children}) => {
    return (
        <tr>
            {children}
        </tr>
    )
}
export const CeldaTabla = ({dato}) => {
    return (
        <td>
            {dato}
        </td>
    )
}
export const CeldaBotonTabla = ({titulo, action}) => {
    return (
        <td>
            <button className="table-button" onClick={() => action()}>{titulo}</button>
        </td>
    )
}

export default Tabla;