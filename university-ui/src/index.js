import React from 'react';
import ReactDOM from 'react-dom/client';
import axios from "axios";
import Container from "./component/Container";

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.timeout = 10000;
axios.defaults.headers = {
  'Content-Type': 'application/json'
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Container />
  </React.StrictMode>
);
