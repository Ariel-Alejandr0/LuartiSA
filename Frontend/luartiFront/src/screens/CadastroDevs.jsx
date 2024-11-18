import React, { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";
import Table from "../components/Tabela";
import AddDev from "../components/AddDev";
import Search from "../components/Search";
import BloquearButton from "../components/devs/BloquearButton";
import { requestGetUsers } from "../service/GETS/GetUsers";
import { useAuth } from "../contexts/auth";

export default function CadastroDevs() {
  const headers = ["ID", "NOME", "EMAIL", "STATUS", "PAPEL"];
  const { userData } = useAuth();
  const [devsList, setDevsList] = useState([]);
  useEffect(() => {
    async function getDevs() {
      const request = await requestGetUsers();
      if (request) {
        setDevsList(request.filter((i) => i.idSuperior == userData.idPessoa));
      }
    }
    getDevs();
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
        <AddDev />
      </div>
      <div
        style={{
          flex: 1,
          width: "98%",
          paddingBottom: "2%",
          overflowY: "auto",
        }}
      >
        <Table
          numeroMaximoDeLinhas={10}
          linhaDeCabecalho={headers}
          devsList={devsList}
        />
      </div>
    </MainLayout>
  );
}
