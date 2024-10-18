import React from "react";
import MainLayout from "../layouts/MainLayout";
import Table from "../components/Tabela";
import AddDev from "../components/AddDev";
import Search from "../components/Search";

export default function CadastroDevs() {
  const headers = ["ID", "NOME", "EMAIL", "SENHA", "ATIVO", "BLOQUEADO"];

  return (
    <MainLayout>
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          width: "98%",
          height: "8%",
        }}
      >
        <Search/>
        <AddDev/>
      </div>
      <div
        style={{
          flex: 1,
          width: "98%",
          paddingBottom: "2%",
          overflowY: "auto",
        }}
      >
        <Table numeroMaximoDeLinhas={10} linhaDeCabecalho={headers} />
      </div>
    </MainLayout>
  );
}
