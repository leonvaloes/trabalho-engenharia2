import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import Home from "./Home";
import Login from './login';
import Register from './register';
import Parametrizacao from './parametrizacao';
import Alterar from './alterar';
import Produtos from './Produtos'
import BuscaProdNome from "./BuscaProdNome";
import BuscaProdEstoque from "./BuscaProdEstoque";
import TipoProdutos from "./TipoProdutos";
import AdminControlPage from "./AdminControlPage"; // Importe a página AdminControlPage
import Eventos from "./Evento";
import TipoEventos from "./TipoEvento";
import BuscaEventosNome from "./BuscaEventoNome";
import BuscaEventosTipo from "./BuscaEventoTipo";

function routes(){
    return(
        <Router>
            <Routes>
                <Route element={<Home />} path="/" exact />
                <Route element={<Login />} path="/login" />
                <Route element={<Register />} path="/register" />
                <Route element={<Parametrizacao />} path="/parametrizacao" />
                <Route element={<Alterar />} path="/alterar" />
                <Route element={<Produtos />} path="/produtos" />
                <Route element={<BuscaProdNome />} path="/BuscaProdNome" />
                <Route element={<BuscaProdEstoque />} path="/BuscaProdEstoque" />
                <Route element={<TipoProdutos />} path="/TipoProdutos" />
                <Route element={<AdminControlPage />} path="/admin" /> {/* Adicione a rota para AdminControlPage */}
                <Route element={<Eventos />} path="/Evento" />
                <Route element={<TipoEventos />} path="/TipoEventos" />
                <Route element={<BuscaEventosNome />} path="/BuscaEventoNome" />
                <Route element={<BuscaEventosTipo />} path="/BuscaEventoTipo" />
            </Routes> 
        </Router>
    )
}

export default routes;
