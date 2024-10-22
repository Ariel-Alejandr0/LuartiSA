import React from "react";
import MainLayout from "../layouts/MainLayout";
import Search from "../components/Search";
import AddTarefa from "../components/AddTarefa";
import TarefaIcone from "../components/PopUpTarefa/TarefaIcone";

export default function Tarefas() {
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
        <TarefaIcone
          prazoFinal={"22/10/1978 23:59"}
          tituloTarefa={"Titulo da tarefapipipi popopo"}
          numDevs={5}
          tipoTarefaDesc={"FRONTEND"}
        />
        <TarefaIcone
          prazoFinal={"22/10/1978 23:59"}
          tituloTarefa={"Dar cachorro pra água"}
          numDevs={5}
          tipoTarefaDesc={"FRONTEND"}
        />
        <TarefaIcone
          prazoFinal={"22/10/1978 23:59"}
          tituloTarefa={"Jogar o lixeiro no lixo"}
          numDevs={5}
          tipoTarefaDesc={"FRONTEND"}
        />
      </div>
    </MainLayout>
  );
}
