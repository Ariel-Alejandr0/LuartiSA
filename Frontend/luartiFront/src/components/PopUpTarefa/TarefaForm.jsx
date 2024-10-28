import React, { useState } from "react";
import ExcluirTarefaButton from './ExcluirTarefaButton'
import EditarTarefaButton from './EditarTarefaButton'
import MarcarComoConcluida from './MarcarComoConcluida'

export default function TarefaForm({
  tituloTarefa,
  tipoTarefaDesc,
  prazoFinal,
}) {
  const [cantEdit, setCantEdit] = useState(true);
  const [formData, setformData] = useState({
    tituloTarefa: tituloTarefa,
    prazoFinal: new Date(prazoFinal).toISOString().slice(0, 16), // Formato correto para datetime-local
    descTarefa: "",
    devs: [],
    tipoDaTarefa: {
      id: 0,
      descricao: tipoTarefaDesc || "Front-end",
    },
  });

  const handleOnChange = (e) => {
    const { id, value } = e.target;
    setformData((prev) => ({
      ...prev,
      [id]: value, // Atualizando o campo correto
    }));
  };
  return (
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
            <strong style={{ color: "#ff6f21" }}>Título da Tarefa: </strong>
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
              value={formData.tituloTarefa}
              disabled={cantEdit}
              onChange={handleOnChange}
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
            <strong style={{ color: "#ff6f21" }}>Data de Entrega: </strong>
            <input
              type="datetime-local"
              id="prazoFinal"
              disabled={cantEdit}   
              value={formData.prazoFinal}
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                width: "100%",
                margin: 0,
                height: 40,
                borderRadius: 15,
                fontSize: "100%",
                border: "2px solid #ff6f21",
              }}
              onChange={handleOnChange}
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
            <strong style={{ color: "#ff6f21" }}>Tipo da Tarefa: </strong>
            <select
              style={{
                width: "100%",
                margin: 0,
                height: 40,
                borderRadius: 15,
                fontSize: "120%",
                border: "2px solid #ff6f21",
              }}
              disabled={cantEdit}
              value={formData.tipoDaTarefa.descricao}
              onChange={handleOnChange}
            >
              <option value={formData.tipoDaTarefa.descricao}>
                {formData.tipoDaTarefa.descricao}
              </option>
            </select>
          </div>
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
            disabled={cantEdit}
            onChange={handleOnChange}
            value={formData.descTarefa}
          />
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
            disabled={cantEdit}
            onChange={handleOnChange}
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
            <EditarTarefaButton cantEdit={cantEdit} setCantEdit={setCantEdit} />
            <ExcluirTarefaButton />
          </div>
        </div>
      </div>
    </div>
  );
}
