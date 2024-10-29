import React, { useState } from "react";

export default function EditarTarefaButton({ cantEdit, setCantEdit }) {
  const handleOnClick = () => {
    setCantEdit(prev => !prev);
  };

  return (
    <button
      onClick={handleOnClick}
      style={{
        height: 50,
        backgroundColor: cantEdit ? "black" : "grey",
        color: "#fff",
      }}
    >
      ✏️ Editar
    </button>
  );
}
