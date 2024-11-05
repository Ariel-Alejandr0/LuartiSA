import React, { useEffect, useState } from "react";
import MainLayout from "../layouts/MainLayout";
import Search from "../components/Search";
import AddTarefa from "../components/AddTarefa";
import TarefaIcone from "../components/PopUpTarefa/TarefaIcone";
import { requestGetTarefas } from "../service/GETS/GetTarefas";

export default function Tarefas() {
  const [filteredTarefas, setFilteredTarefas] = useState([]);
  const [tarefas, setTarefas] = useState([]);
  useEffect(() => {
    async function getTarefas() {
      const req = await requestGetTarefas();
      if (req) {
        setTarefas(req);
      }
    }
    getTarefas();
  }, []);
  useEffect(() => {
    setFilteredTarefas(tarefas);
  }, [tarefas]);
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
        <Search setFilteredTarefas={setFilteredTarefas} />
        <AddTarefa />
      </div>
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
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
          />
        ))}
      </div>
    </MainLayout>
  );
}
