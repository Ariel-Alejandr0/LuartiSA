import React from "react";

export default function Search({ setFilteredTarefas, tarefas }) {
  const handleOnChange = (e) => {
    setFilteredTarefas(
      tarefas.filter((i) => i.nomeTarefa.includes(e.target.value))
    );
  };

  return (
    <input
      onChange={(e) => handleOnChange(e)}
      placeholder="🔍 Buscar"
      style={{
        padding: 5,
        height: 34,
        width: 290,
        borderRadius: 12,
        marginRight: "2%",
      }}
    />
  );
}
