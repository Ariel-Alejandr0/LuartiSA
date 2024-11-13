import React from "react";
import { requestUpdateTarefa } from "../../service/UPDATES/UpdateTarefa";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

export default function MarcarComoConcluida({ taskData }) {
  const MySwal = withReactContent(Swal);
  const handleOnClick = async () => {
    console.log(taskData);
    const res = await requestUpdateTarefa(
      taskData.idTarefa,
      taskData.tituloTarefa,
      taskData.descTarefa,
      taskData.dataCriacao,
      taskData.prazoFinal,
      "CONCLUIDA",
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
    <button
      style={{ height: 50, backgroundColor: "#ff6f21", color: "#fff" }}
      onClick={handleOnClick}
    >
      Marcar Como Concluída
    </button>
  );
}
