import React from "react";
import { requestDeleteTarefa } from "../../service/DELETES/DeleteTarefa";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";

export default function ExcluirTarefaButton({ idTarefa }) {
  const MySwal = withReactContent(Swal);

  const handleOnClick = async () => {
    const req = await requestDeleteTarefa(idTarefa);
    if (req) {
      MySwal.fire({
        icon: "success",
        title: "Sucesso",
        text: "Tarefa excluída com sucesso!",
        preConfirm: () => {
          location.href = location.href;
        },
      });
    }
  };
  return (
    <button
      onClick={handleOnClick}
      style={{ height: 50, backgroundColor: "#cc241d", color: "#fff" }}
    >
      🗑️ Excluir
    </button>
  );
}
