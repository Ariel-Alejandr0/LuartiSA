import React, { useRef } from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

export default function AddDev() {
  const MySwal = withReactContent(Swal);
  const createUserDataRef = useRef({}) 


  const handleOnClick = async () => {
    const { value: objForm } = await MySwal.fire({
      title: "Cadastro De Desenvolvedor",
      width: "40vw",
      showCloseButton: true,
      confirmButtonText: "Cadastrar",
      html: (
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            width: "100%",
          }}
        >
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-around",
            }}
          >
            <label>Nome Completo:</label>
            <label>Email:</label>
            <label>Senha:</label>
          </div>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-between",
            }}
          >
            <input id="nomeCompleto" className="swal2-input" />
            <input id="email" className="swal2-input" />
            <input id="senha" className="swal2-input" />
          </div>
        </div>
      ),
      preConfirm: () => ({
        nomeCompleto: document.querySelector("#nomeCompleto").value,
        email: document.querySelector("#email").value,
        senha: document.querySelector("#senha").value,
      }),
    });
    createUserDataRef.current = objForm
  };

  return (
    <button
      onClick={handleOnClick}
      style={{
        backgroundColor: "#FF6F21",
        color: "#FFF",
        width: 200,
        height: 50,
        borderRadius: 25,
      }}
    >
      + Adicionar Dev
    </button>
  );
}
