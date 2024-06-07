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
import AdminControlPage from "./AdminControlPage";
import TipoEventos from "./TipoEvento";
import BuscaEventosNome from "./BuscaEventoNome";
import BuscaEventosTipo from "./BuscaEventoTipo";
import VendaRifa from "./vendaRifa";
import Eventos from "./Evento";
import AgendarDoacao from './AgendarDoacao';
import BuscaDoacao from './BuscaDoacao';
import CriarRifa from './CriarRifa';
import BuscaRifa from './BuscaRifa';
import PesquisaRifa from './PesquisaRifa';

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
                <Route element={<AgendarDoacao />} path="/AgendarDoacao" />
                <Route element={<BuscaDoacao />} path="/BuscaDoacao" />
                <Route element={<CriarRifa />} path="/CriarRifa" />
                <Route element={<BuscaRifa />} path="/BuscaRifa" />
                <Route element={<PesquisaRifa />} path="/PesquisaRifa" />

                <Route element={<AdminControlPage />} path="/admin" />

                <Route element={<VendaRifa />} path="/vendaRifa" />
            </Routes> 
        </Router>
    )
}

export default routes;
