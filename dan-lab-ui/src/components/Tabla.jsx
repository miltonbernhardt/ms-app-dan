import React from 'react';

export const Tabla = ({titulo = '', encabezado, filas}) => {

    return (
        <div>
            <h2>{titulo}</h2>
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
        <td className="celdaTabla">
            {dato}
        </td>
    )
}
export const CeldaBotonTabla = ({titulo, accion}) => {
    return (
        <td className="celdaTabla">
            <button onClick={accion}>{titulo}</button>
        </td>
    )
}

export default Tabla;