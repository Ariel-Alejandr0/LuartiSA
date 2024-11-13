import React, { useState } from "react";

// Dados dos usuários

// Estilos em linha (podem ser movidos para CSS ou Styled-components)
const popupStyle = {
  width: "100%",
  maxHeight: 200,
  overflowY: "auto",
  backgroundColor: "rgba(0, 0, 0, 0.5)",
  justifyContent: "center",
  alignItems: "center",
  zIndex: 9999,
};

const popupContentStyle = {
  backgroundColor: "#fff",
  padding: "20px",
  borderRadius: "8px",
  width: "100%",
  position: "relative",
};

const closeBtnStyle = {
  position: "absolute",
  top: "10px",
  right: "10px",
  fontSize: "24px",
  cursor: "pointer",
};

const checkboxLabelStyle = {
  display: "block",
  marginBottom: "10px",
};

const balloonsContainerStyle = {
  marginTop: "20px",
  display: "flex",
  flexDirection: "column",
};

const closeBalloonStyle = {
  marginLeft: "10px",
  cursor: "pointer",
};

export default function AddDevsToTask({
  users,
  selectedUsers,
  setSelectedUsers,
}) {
  // Estado para gerenciar os checkboxes

  // Função para lidar com o evento de seleção de checkbox
  const handleCheckboxChange = (user, isChecked) => {
    setSelectedUsers((prevState) => {
      if (isChecked) {
        return [...prevState, user]; // Adiciona o ID do usuário selecionado
      } else {
        return prevState.filter((u) => u.idPessoa !== user.idPessoa); // Remove o ID do usuário desmarcado
      }
    });
  };

  // Função para remover um usuário da lista de balões
  const handleRemoveBalloon = (user) => {
    setSelectedUsers((prevState) =>
      prevState.filter((u) => u.idPessoa !== user.idPessoa)
    );
  };

  return (
    <div style={{ position: "relative" }}>
      {/* Botão para abrir o popup */}
      <button
        onClick={() =>
          (document.getElementById("popup").style.display = "block")
        }
        style={{
          width: "100%",
        }}
      >
        Adicionar Usuários
      </button>

      {/* Popup */}
      <div id="popup" style={popupStyle}>
        <div style={popupContentStyle}>
          <span
            style={closeBtnStyle}
            onClick={() =>
              (document.getElementById("popup").style.display = "none")
            }
          >
            &times;
          </span>
          <div style={{ width: "100%" }}>
            {users.map((user) => (
              <label key={user.idPessoa} style={checkboxLabelStyle}>
                <input
                  type="checkbox"
                  checked={selectedUsers.includes(user.idPessoa)}
                  onChange={(e) =>
                    handleCheckboxChange(user, e.target.checked)
                  }
                />
                {user.nomeCompleto}
              </label>
            ))}
          </div>

          {/* <div style={balloonsContainerStyle}>
            {selectedUsers.map((userId) => {
              const user = users.find((u) => u.idPessoa === userId);
              return (
                <div key={user.idPessoa} style={balloonStyle}>
                  {user.nomeCompleto}
                  <span
                    style={closeBalloonStyle}
                    onClick={() => handleRemoveBalloon(user.idPessoa)}
                  >
                    X
                  </span>
                </div>
              );
            })}
          </div> */}
        </div>
      </div>
    </div>
  );
}
