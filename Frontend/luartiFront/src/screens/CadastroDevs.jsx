import React from "react";
import MainLayout from "../layouts/MainLayout";
import Table from "../components/Tabela";

export default function CadastroDevs() {
  const headers = ["Coluna 1", "Coluna 2", "Coluna 3"];

  return (
    <MainLayout>
      <div>CadastroDevs</div>
      <Table
        numeroMaximoDeLinhas={5}
        linhaDeCabecalho={headers}  
      />
    </MainLayout>
  );
}
