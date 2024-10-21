import React, { useRef } from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

export default function AddTipoTarefa() {
  const MySwal = withReactContent(Swal);
  const createUserDataRef = useRef({});

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
              justifyContent: "center",
            }}
          >
            <label>Descrição:</label>
          </div>
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
            }}
          >
            <input id="descricao" className="swal2-input" />
          </div>
        </div>
      ),
      preConfirm: () => ({
        descricao: document.querySelector("#descricao").value,
      }),
    });
    createUserDataRef.current = objForm;
    console.log(createUserDataRef.current)

    MySwal.fire({
      title: "Sucesso!",
      icon: "success",
      text: "Desenvolvedor Cadastrado com sucesso.",
    });
  };
  return (
    <button
      onClick={handleOnClick}
      style={{
        backgroundColor: "#FF6F21",
        color: "#FFF",
        width: 240,
        height: 50,
        borderRadius: 25,
      }}
    >
      + Adicionar Tipo de Tarefa
    </button>
  );
}
