import React from 'react';
import {render} from 'react-dom';
import './index.css';
import App from './App';
import {positions, Provider as AlertProvider, transitions, types} from 'react-alert'
import AlertTemplate from 'react-alert-template-basic'

// optional configuration
const options = {
    // you can also just use 'bottom center'
    position: positions.BOTTOM_RIGHT,
    timeout: 3000,
    offset: '10px',
    // you can also just use 'scale'
    transition: transitions.SCALE
}

const Root = () => (
    <AlertProvider template={AlertTemplate} {...options}>
        <App/>
    </AlertProvider>
)

render(<Root/>, document.getElementById('root'))
