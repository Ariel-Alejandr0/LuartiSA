import React from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { requestUpdateTipoTarefa } from "../service/UPDATES/UpdateTipoTarefa";
import { requestDeleteTipoTarefa } from "../service/DELETES/DeleteTipoTarefa";

export default function DeleteTipoTarefa({ id }) {
  const MySwal = withReactContent(Swal);

  const handleOnClick = () => {
    MySwal.fire({
      title: "Tem Certeza que deseja deletar este tipo de Tarefa?",
      icon: "warning",
      showCloseButton: true,
      showDenyButton: true,
      preConfirm: async () => {
        const req = await requestDeleteTipoTarefa(id);
        if (req) {
          MySwal.fire({
            text: req.message,
            preConfirm: () => {
              location.href = location.href;
            },
          });
        } else {
          MySwal.fire({
            title: "ERRO!",
            text: "Erro ao exlcuir tipo de tarefa",
            icon: "error",
            preConfirm: () => {
              location.href = location.href;
            },
          });
        }
      },
    });
  };
  return (
    <div
      onClick={handleOnClick}
      style={{
        position: "absolute",
        padding: "0.5%",
        backgroundColor: "#f00",
        borderRadius: 8,
        top: 5,
        left: "90%",
      }}
    >
      🗑️
    </div>
  );
}
