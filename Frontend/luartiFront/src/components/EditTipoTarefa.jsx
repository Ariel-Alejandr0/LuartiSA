import React from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { requestUpdateTipoTarefa } from "../service/UPDATES/UpdateTipoTarefa";

export default function EditTipoTarefa({ id, desc }) {
  const MySwal = withReactContent(Swal);

  const handleOnClick = () => {
    MySwal.fire({
      title: "EDITAR TIPO DE TAREFA",
      showCloseButton: true,
      showCancelButton: true,
      html: (
        <div
          style={{ display: "flex", width: "100%", flexDirection: "column" }}
        >
          <div>
            <label>id: </label>
            <input className="swal2-input" value={id} disabled={true} />
          </div>
          <div>
            <label>Descricao: </label>
            <input
              className="swal2-input"
              placeholder={desc}
              id="description"
            />
          </div>
        </div>
      ),
      preConfirm: async () => {
        const description = document.querySelector("#description").value;
        const req = await requestUpdateTipoTarefa(id, description);
        if (req) {
          MySwal.fire({
            title: "SUCESSO!",
            icon: "success",
            preConfirm: () => {
              location.href = location.href;
            },
          });
        } else {
          MySwal.fire({
            title: "ERRO!",
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
        backgroundColor: "#000",
        borderRadius: 8,
        top: 5,
        left: "80%",
      }}
    >
      ✏️
    </div>
  );
}
