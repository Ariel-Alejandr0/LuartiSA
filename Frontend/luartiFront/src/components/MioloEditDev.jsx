import React, { useEffect, useState } from "react";

export default function MioloEditDev({ formInitial, formDataRef }) {
  const [formData, setFormData] = useState(formInitial);
  const handleOnChange = (e) => {
    const { id, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [id]: value,
    }));
  };

  useEffect(() => {
    formDataRef.current = formData;
  }, [formData]);
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
          justifyContent: "space-around",
        }}
      >
        <label>Nome Completo:</label>
        <label>Email:</label>
        <label>Senha:</label>
        <label>Status:</label>
      </div>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-between",
        }}
      >
        <input
          id="nomeCompleto"
          className="swal2-input"
          value={formData.nomeCompleto}
          onChange={handleOnChange}
        />
        <input
          id="email"
          className="swal2-input"
          value={formData.email}
          onChange={handleOnChange}
        />
        <input
          id="senha"
          className="swal2-input"
          value={formData.senha}
          onChange={handleOnChange}
        />
        <select style={{ margin: "6% 11%" }} onChange={handleOnChange}>
          <option value={"ATIVO"}>ATIVO</option>
          <option value={"INATIVO"}>INATIVO</option>
          <option value={"BLOQUEADO"}>BLOQUEADO</option>
        </select>
      </div>
    </div>
  );
}
