import React, { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";
import Search from "../components/Search";
import AddTarefa from "../components/AddTarefa";
import TarefaIcone from "../components/PopUpTarefa/TarefaIcone";
import { requestGetTarefas } from "../service/GETS/GetTarefas";
import { useAuth } from "../contexts/auth";

export default function Tarefas() {
  const [filteredTarefas, setFilteredTarefas] = useState([]);
  const [tarefas, setTarefas] = useState([]);
  const { userData } = useAuth();

  useEffect(() => {
    async function getTarefas() {
      const req = await requestGetTarefas();
      if (req) {
        setTarefas(req);
        setFilteredTarefas(req);
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
          />
        ))}
      </div>
    </MainLayout>
  );
}
