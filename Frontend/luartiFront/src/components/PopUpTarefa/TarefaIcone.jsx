import React from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import EditarTarefaButton from "./EditarTarefaButton";
import ExlcuirTarefaButton from "./ExcluirTarefaButton";
import MarcarComoConcluida from "./MarcarComoConcluida";

export default function TarefaIcone({
  prazoFinal,
  tituloTarefa,
  numDevs,
  tipoTarefaDesc,
}) {
  const MySwal = withReactContent(Swal);

  const handleOnClick = async () => {
    const { value: objForm } = await MySwal.fire({
      title: " ",
      width: "70vw",
      showCloseButton: true,
      showConfirmButton: false,
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
                justifyContent: "space-between",
                alignItems: "flex-end",
                width: "100%",
              }}
            >
              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "flex-start",
                  justifyContent: "center",
                  width: "40%",
                }}
              >
                <strong style={{ color: "#ff6f21" }}>Titulo da Tarefa: </strong>
                <input
                  id="tituloTarefa"
                  style={{
                    width: "100%",
                    margin: 0,
                    height: 40,
                    borderRadius: 15,
                    fontSize: "120%",
                    border: "2px solid #ff6f21",
                  }}
                  value={tituloTarefa}
                />
              </div>
              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "flex-start",
                  justifyContent: "center",
                  width: 220,
                }}
              >
                <strong style={{ color: "#ff6f21" }}>Data De Entrega: </strong>
                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    width: "100%",
                    margin: 0,
                    height: 40,
                    borderRadius: 15,
                    fontSize: "120%",
                    border: "2px solid #ff6f21",
                  }}
                >
                  📅 {prazoFinal}
                </div>
              </div>
              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "flex-start",
                  justifyContent: "center",
                  width: 220,
                }}
              >
                <strong style={{ color: "#ff6f21" }}>Tipo Da Tarefa: </strong>
                <select
                  style={{
                    width: "100%",
                    margin: 0,
                    height: 40,
                    borderRadius: 15,
                    fontSize: "120%",
                    border: "2px solid #ff6f21",
                  }}
                >
                  <option value={tipoTarefaDesc}>{tipoTarefaDesc}</option>
                </select>
              </div>
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
              <div
                style={{
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "flex-end",
                  width: "100%",
                }}
              >
                <MarcarComoConcluida />
                <EditarTarefaButton />
                <ExlcuirTarefaButton />
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
    <div
      onClick={handleOnClick}
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "space-between",
        width: 225,
        height: 225,
        margin: "0 1%",
        backgroundColor: "#fdf8f4",
        border: "3px solid #ff6f21",
        color: "#ff6f21",
        borderRadius: 15,
      }}
    >
      <span style={{ fontWeight: "bold" }}>📅 {prazoFinal}</span>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          width: "100%",
          height: "50%",
        }}
      >
        <span style={{ fontSize: "500%", width: "50%" }}>📝</span>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between",
            width: "50%",
            height: "100%",
          }}
        >
          <div>
            <strong>{tituloTarefa}</strong>
          </div>
          <div>👨‍👦{numDevs} devs</div>
        </div>
      </div>
      <div>
        <strong>{tipoTarefaDesc}</strong>
      </div>
    </div>
  );
}
