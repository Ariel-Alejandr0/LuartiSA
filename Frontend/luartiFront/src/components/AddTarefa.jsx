import React, { useRef } from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

export default function AddTipoTarefa() {
  const MySwal = withReactContent(Swal);
  const createUserDataRef = useRef({});

  const handleOnClick = async () => {
    const { value: objForm } = await MySwal.fire({
      title: "Cadastro De Tarefa",
      width: "70vw",
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
              justifyContent: "space-between",
              width: "100%",
            }}
          >
            <div
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
              }}
            >
              <label>Titulo da Tarefa: </label>
              <input
                id="tituloTarefa"
                className="swal2-input"
                style={{ flex: 1, margin: 0 }}
              />
            </div>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "flex-start",
                marginTop: "2%",
              }}
            >
              <label>Descrição: </label>
              <textarea
                id="descTarefa"
                className="swal2-textarea"
                style={{ height: 200, width: "100%", margin: 0 }}
              />
            </div>
            <div
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
                marginTop: "2%",
              }}
            >
              <label>Tipo da Tarefa: </label>
              <select
                id="tipoTarefa"
                className="swal2-select"
                style={{ flex: 1, margin: 0 }}
              ></select>
            </div>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "flex-start",
                justifyContent: "space-between",
                marginTop: "2%",
              }}
            >
              <label>Adicionar Desenvolvedores: </label>
              <select
                id="devs"
                className="swal2-select"
                style={{ width: "100%", margin: 0, marginTop: "1%" }}
              ></select>
              <div
                style={{
                  display: "flex",
                  flexWrap: "wrap",
                  width: "100%",
                  padding: "0.5%",
                  marginTop: "1%",
                }}
              >
                <span
                  style={{
                    padding: 5,
                    margin: 2,
                    backgroundColor: "#eee",
                    borderRadius: 15,
                    border: "1px solid black",
                  }}
                >
                  Ariel Alejandro Marcellino Silva
                  <strong>{" x "}</strong>
                </span>
              </div>
            </div>
          </div>
        </div>
      ),
      preConfirm: () => ({
        tituloTarefa: document.querySelector("#tituloTarefa").value,
        descTarefa: document.querySelector("#descTarefa").value,
        tipoTarefa: document.querySelector("#tipoTarefa").value,
        devs: document.querySelector("#devs").value,
      }),
    });
    createUserDataRef.current = objForm;

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
        width: 200,
        height: 50,
        borderRadius: 25,
      }}
    >
      + Adicionar Tarefa
    </button>
  );
}
