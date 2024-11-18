import React, { useEffect, useState } from "react";

// Dados dos usuários

// Estilos em linha (podem ser movidos para CSS ou Styled-components)
const popupStyle = {
  width: "100%",
  maxHeight: 200,
  overflowY: "auto",
  justifyContent: "center",
  alignItems: "center",
  zIndex: 9999,
};

const popupContentStyle = {
  backgroundColor: "#fff",
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
  const [nonSelectedUsers, setNonSelectedUsers] = useState(users);
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
  useEffect(() => {
    setNonSelectedUsers(
      users.filter((i) => !selectedUsers.find((u) => u.idPessoa == i.idPessoa))
    );
  }, [selectedUsers]);
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
            {nonSelectedUsers.map((user) => (
              <label key={user.idPessoa} style={checkboxLabelStyle}>
                <input
                  type="checkbox"
                  checked={selectedUsers.includes(user.idPessoa)}
                  onChange={(e) => handleCheckboxChange(user, e.target.checked)}
                />
                {user.nomeCompleto}
              </label>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
