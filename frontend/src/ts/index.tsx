import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './components/App';

import '../css/styles.css'
import {Provider} from "react-redux";
import store from "./redux/store";

let root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);

root.render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>
);