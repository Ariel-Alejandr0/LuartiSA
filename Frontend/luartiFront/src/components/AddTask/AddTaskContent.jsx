import React, { useEffect, useState } from "react";
import AddDevsToTask from "../AddDevsToTask";
import UserBaloon from "./UserBaloon";

export default function AddTaskContent({
  tiposDeTarefa,
  users,
  selectedUsersRef,
}) {
  const [selectedUsers, setSelectedUsers] = useState([]);
  useEffect(() => {
    selectedUsersRef.current = selectedUsers;
  }, [selectedUsers]);
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
          <label>Prazo de Entrega: </label>
          <input
            id="dataFim"
            className="swal2-input"
            type="date"
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
            style={{
              width: "100%",
              margin: 0,
              height: 40,
              borderRadius: 15,
              fontSize: "120%",
              border: "2px solid #ff6f21",
            }}
            id="tipoTarefa"
          >
            {tiposDeTarefa?.map((i) => (
              <option key={i.idTipoTarefa} value={i.idTipoTarefa}>
                {i.descTipoTarefa}
              </option>
            ))}
          </select>
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
          <div style={{ width: "100%" }}>
            <AddDevsToTask
              users={users}
              selectedUsers={selectedUsers}
              setSelectedUsers={setSelectedUsers}
            />
          </div>
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
                key={u.idPessoa}
                user={u}
                setSelectedUsers={setSelectedUsers}
              />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
