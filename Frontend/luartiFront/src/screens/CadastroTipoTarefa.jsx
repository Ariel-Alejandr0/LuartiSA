import React, { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";
import Search from "../components/Search";
import Table from "../components/Tabela";
import AddTipoTarefa from "../components/AddTipoTarefa";
import { requestGetAllTiposTarefas } from "../service/GETS/GetAllTipoTarefa";
import TableTipoTarefa from "../components/TabelaTipoTarefa";

export default function CadastroTipoTarefa() {
  const headers = ["ID", "Descrição"];
  const [tiposDeTarefa, setTiposDeTarefa] = useState([])

  useEffect(() => {
    async function getTypesTask() {
      const request = await requestGetAllTiposTarefas();
      if (request) {
        setTiposDeTarefa(request);
      }
    }
    getTypesTask()
  }, []);

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
        <TableTipoTarefa numeroMaximoDeLinhas={10} linhaDeCabecalho={headers} typesList={tiposDeTarefa}/>
      </div>
    </MainLayout>
  );
}
