import React from "react";
import MainLayout from "../layouts/MainLayout";
import Search from "../components/Search";
import Table from "../components/Tabela";
import AddTipoTarefa from "../components/AddTipoTarefa";

export default function CadastroTipoTarefa() {
  const headers = ["ID", "Descrição"];

  return (
    <MainLayout>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          width: "98%",
          height: "8%",
        }}
      >
        <Search />
        <AddTipoTarefa />
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
