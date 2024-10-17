import React from "react";

export default function Table({ numeroMaximoDeLinhas, linhaDeCabecalho }) {
  const numeroDeColunas = linhaDeCabecalho.length;

  // Geração de linhas de conteúdo vazias como exemplo
  const linhas = Array.from({ length: numeroMaximoDeLinhas }, (_, rowIndex) => (
    <React.Fragment key={rowIndex}>
      {Array.from({ length: numeroDeColunas }, (_, colIndex) => (
        <div
          key={colIndex}
          style={{ border: "1px solid #ccc", padding: 10, textAlign: "center" }}
        >
          {/* Aqui você pode adicionar o conteúdo das células */}
          Conteúdo {rowIndex + 1}-{colIndex + 1}
        </div>
      ))}
    </React.Fragment>
  ));

  return (
    <div
      style={{
        display: "grid",
        gridTemplateRows: "auto 1fr",
      }}
    >
      {/* Cabeçalho da tabela */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns:
            "repeat(" +
            numeroDeColunas +
            ", 1fr)" /* Substitua o 3 pelo número dinâmico de colunas */,
        }}
      >
        {linhaDeCabecalho.map((header, index) => (
          <div
            key={index}
            style={{
              fontWeight: "bold",
              backgroundColor: "#FF6F21",
              color: '#fff',
              border: "1px solid #ccc",
              padding: 10,
              textAlign: "center",
            }}
          >
            {header}
          </div>
        ))}
      </div>

      {/* Linhas de dados */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns:
            "repeat(" +
            numeroDeColunas +
            ", 1fr)" /* Substitua o 3 pelo número dinâmico de colunas */,
        }}
      >
        {linhas}
      </div>
    </div>
  );
}
