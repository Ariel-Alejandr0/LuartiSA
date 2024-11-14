import React, { useEffect, useState } from "react";
import ExcluirTarefaButton from "./ExcluirTarefaButton";
import EditarTarefaButton from "./EditarTarefaButton";
import MarcarComoConcluida from "./MarcarComoConcluida";
import PersonIcon from "./PersonIcon";
import { formatDate } from "../../functions/formatDate";
import AddDevsToTask from "../AddDevsToTask";
import UserBaloon from "../AddTask/UserBaloon";

export default function TarefaForm({
  idTarefa,
  tituloTarefa,
  prazoFinal,
  dataCriacao,
  userData,
  descTarefa,
  users,
  usersHasTarefas,
  idTipoTarefa,
  tiposDeTarefa,
}) {
  const formatarData = (dateStr) => {
    return dateStr.split("T")[0];
  };
  useEffect(() => {
    console.log(
      users?.filter((usr) =>
        usersHasTarefas.some(
          (UHT) => UHT.idPessoa == usr.idPessoa && UHT.idTarefa == idTarefa
        )
      )
    );
  }, []);
  const [cantEdit, setCantEdit] = useState(true);
  const [selectedUsers, setSelectedUsers] = useState(
    users?.filter((usr) =>
      usersHasTarefas.some(
        (UHT) => UHT.idPessoa == usr.idPessoa && UHT.idTarefa == idTarefa
      )
    )
  );
  const [formData, setformData] = useState({
    idTarefa: idTarefa,
    tituloTarefa: tituloTarefa,
    prazoFinal: new Date(prazoFinal).toISOString().slice(0, 16).split("T")[0], // Formato correto para datetime-local
    dataCriacao: formatDate(dataCriacao),
    descTarefa: descTarefa,
    devs: [],
    tipoDaTarefa: tiposDeTarefa.find((i) => i.idTipoTarefa == idTipoTarefa),
  });

  const handleOnChange = (e) => {
    const { id, value } = e.target;
    setformData((prev) => ({
      ...prev,
      [id]: id === "prazoFinal" ? formatarData(value) : value, // Atualizando o campo correto
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
              type="date"
              id="prazoFinal"
              disabled={cantEdit}
              value={formatarData(formData.prazoFinal)}
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
              value={formData.tipoDaTarefa}
              onChange={handleOnChange}
              id="tipoDaTarefa"
            >
              {tiposDeTarefa?.map((i) => (
                <option key={i.idTipoTarefa} value={i.idTipoTarefa}>
                  {i.descTipoTarefa}
                </option>
              ))}
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
          {userData?.papel === "ADMIN" && !cantEdit && (
            <div style={{ width: "100%" }}>
              <AddDevsToTask
                users={users}
                selectedUsers={selectedUsers}
                setSelectedUsers={setSelectedUsers}
              />
            </div>
          )}
          <div
            style={{
              display: "flex",
              flexWrap: "wrap",
              width: "100%",
              padding: "0.5%",
              marginTop: "1%",
            }}
          >
            {selectedUsers.map((u) => (
              <UserBaloon
                setSelectedUsers={setSelectedUsers}
                user={u}
                cantEdit={cantEdit}
              />
            ))}
          </div>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "flex-end",
              width: "100%",
            }}
          >
            <MarcarComoConcluida taskData={formData} />
            {userData?.papel === "ADMIN" && (
              <>
                <EditarTarefaButton
                  cantEdit={cantEdit}
                  setCantEdit={setCantEdit}
                  taskData={formData}
                  usersAtual={users?.filter((usr) =>
                    usersHasTarefas.some(
                      (UHT) =>
                        UHT.idPessoa == usr.idPessoa && UHT.idTarefa == idTarefa
                    )
                  )}
                  selectedUsers={selectedUsers}
                />
                <ExcluirTarefaButton idTarefa={idTarefa} />
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
