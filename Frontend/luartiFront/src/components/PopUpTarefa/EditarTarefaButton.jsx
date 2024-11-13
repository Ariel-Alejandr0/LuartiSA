import React, { useState } from "react";
import { requestUpdateTarefa } from "../../service/UPDATES/UpdateTarefa";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

export default function EditarTarefaButton({
  cantEdit,
  setCantEdit,
  taskData,
}) {
  const MySwal = withReactContent(Swal);
  const handleOnClick = () => {
    setCantEdit((prev) => !prev);
  };
  const handleOnSave = async () => {
    console.log(taskData);
    const res = await requestUpdateTarefa(
      taskData.idTarefa,
      taskData.tituloTarefa,
      taskData.descTarefa,
      taskData.dataCriacao,
      taskData.prazoFinal,
      "PENDENTE",
      taskData?.tipoDaTarefa?.idTipoTarefa || taskData.tipoDaTarefa
    );
    MySwal.fire({
      text: res.message,
      preConfirm: () => {
        location.href = location.href;
      },
    });
  };
  return (
    <>
      <button
        onClick={handleOnClick}
        style={{
          height: 50,
          backgroundColor: cantEdit ? "black" : "grey",
          color: "#fff",
        }}
      >
        ✏️ Editar
      </button>
      {!cantEdit && (
        <button
          onClick={handleOnSave}
          style={{
            height: 50,
            backgroundColor: "green",
            color: "#fff",
          }}
        >
          ✔️ Salvar
        </button>
      )}
    </>
  );
}
