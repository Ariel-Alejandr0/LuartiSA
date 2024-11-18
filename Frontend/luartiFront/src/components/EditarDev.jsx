import React, { useRef, useState } from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import MioloEditDev from "./MioloEditDev";
import { useAuth } from "../contexts/auth";
import { requestUpdateUser } from "../service/UPDATES/UpdatePessoa";

export default function EditarDev({ id, senha, email, nomeCompleto, status, papel }) {
  const { userData } = useAuth();
  const MySwal = withReactContent(Swal);
  const initialState = {
    id,
    senha,
    email,
    nomeCompleto,
    status,
    papel,
    idSuperior: userData.idPessoa,
  };
  const formDataRef = useRef(initialState);
  const handleOnClick = () => {
    MySwal.fire({
      title: "Editar Dev",
      showCancelButton: true,
      showCloseButton: true,
      html: (
        <MioloEditDev formInitial={initialState} formDataRef={formDataRef} />
      ),
      preConfirm: async () => {
        const data = formDataRef.current;
        const req = await requestUpdateUser(
          data.id,
          data.nomeCompleto,
          data.email,
          data.senha,
          data.status,
          data.papel,
          data.idSuperior
        );
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
