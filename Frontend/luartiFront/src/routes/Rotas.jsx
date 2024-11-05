import React from "react";
import Login from "../screens/Login";
import { Navigate, Route, Routes } from "react-router-dom";
import CadastroDevs from "../screens/CadastroDevs";
import Tarefas from "../screens/Tarefas";
import CadastroTipoTarefa from "../screens/CadastroTipoTarefa";
import RequireAuth from "../components/RequerieAuth";

export default function Rotas() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route element={<RequireAuth />}>
        <Route path="/" element={<Navigate to="/tarefas" />} />
        <Route path="/cadastroDevs" element={<CadastroDevs />} />
        <Route path="/tarefas" element={<Tarefas />} />
        <Route path="/cadastroTipoTarefas" element={<CadastroTipoTarefa />} />
      </Route>
    </Routes>
  );
}
