import React, { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";
import Search from "../components/Search";
import AddTarefa from "../components/AddTarefa";
import TarefaIcone from "../components/PopUpTarefa/TarefaIcone";
import { requestGetTarefas } from "../service/GETS/GetTarefas";
import { useAuth } from "../contexts/auth";
import { requestGetUsers } from "../service/GETS/GetUsers";
import { requestGetPessoasGHasTarefas } from "../service/GETS/GetPessoaHasTarefa";

export default function Tarefas() {
  const { userData } = useAuth();
  const [filteredTarefas, setFilteredTarefas] = useState([]);
  const [tarefas, setTarefas] = useState([]);
  const [users, setUsers] = useState([]);
  const [usersHasTarefas, setUsersHasTarefas] = useState([]);

  useEffect(() => {
    async function getTarefas() {
      const reqTarefas = await requestGetTarefas();
      const reqUsers = await requestGetUsers();
      const reqUsersHasTarefas = await requestGetPessoasGHasTarefas();
      if (reqTarefas && reqUsers && reqUsersHasTarefas) {
        setUsers(reqUsers);
        setUsersHasTarefas(reqUsersHasTarefas);
        setTarefas(reqTarefas);
        setFilteredTarefas(reqTarefas);
      }
    }
    getTarefas();
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
        <Search setFilteredTarefas={setFilteredTarefas} tarefas={tarefas} />
        {userData?.papel == "ADMIN" && <AddTarefa />}
      </div>
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "center",
          alignItems: "flex-start",
          flex: 1,
          width: "100%",
          overflowY: "auto",
        }}
      >
        {filteredTarefas.map((i) => (
          <TarefaIcone
            key={i.idTarefa}
            prazoFinal={i.dataFim}
            numDevs={5}
            tituloTarefa={i.nomeTarefa}
            tipoTarefaDesc={i.idTipoTarefa}
            descTarefa={i.descTarefa}
            users={users}
            usersHasTarefas={usersHasTarefas}
            idTarefa={i.idTarefa}
          />
        ))}
      </div>
    </MainLayout>
  );
}
