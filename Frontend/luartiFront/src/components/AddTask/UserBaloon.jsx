import React from "react";
import { useAuth } from "../../contexts/auth";

export default function UserBaloon({ user, setSelectedUsers, cantEdit }) {
  const handleOnClick = () => {
    setSelectedUsers((prev) => prev.filter((i) => i.idPessoa != user.idPessoa));
  };
  return (
    <span
      style={{
        padding: 5,
        margin: 2,
        backgroundColor: "#eee",
        borderRadius: 15,
        border: "1px solid black",
      }}
    >
      {user.nomeCompleto}
      {!cantEdit && <strong onClick={handleOnClick}>{" x "}</strong>}
    </span>
  );
}
