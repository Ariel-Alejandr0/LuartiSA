import React from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

export default function BloquearButton({ bloqueado }) {
  const MySwal = withReactContent(Swal);
  const handleOnClick = () => {
    MySwal.fire({
      title: `Tem ceterteza que deseja ${
        bloqueado ? "Desbloquear" : "Bloquear"
      } o usuário?`,
      icon: "warning",
      confirmButtonText: "Sim!",
      showDenyButton: true,
      denyButtonText: "Não!",
      showCloseButton: true,
    });
  };
  return (
    <button
      onClick={handleOnClick}
      style={{
        width: "auto",
        padding: "0 5px",
        height: 50,
        backgroundColor: "black",
        color: "white",
        borderRadius: 12,
      }}
    >
      {bloqueado ? "Desbloquear" : "🚫 Bloquear"}
    </button>
  );
}
