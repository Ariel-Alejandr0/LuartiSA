import React, { useState } from "react";

export default function EditarTarefaButton({ cantEdit, setCantEdit, taskData }) {
  const handleOnClick = () => {
    setCantEdit((prev) => !prev);
  };
  const handleOnSave = () => {
    console.log(taskData)
  };
  return (
    <>
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
      {!cantEdit && (
        <button
          onClick={handleOnSave}
          style={{
            height: 50,
            backgroundColor: "green",
            color: "#fff",
          }}
        >
          ✔️ Salvar
        </button>
      )}
    </>
  );
}
