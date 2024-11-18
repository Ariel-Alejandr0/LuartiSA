import React, { useEffect } from "react";
import EditarDev from "./EditarDev";

export default function Table({
  numeroMaximoDeLinhas,
  linhaDeCabecalho,
  devsList,
}) {
  const numeroDeColunas = linhaDeCabecalho.length;

  useEffect(() => {
    console.log(devsList);
  }, [devsList]);

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
          gridTemplateColumns: `repeat(${numeroDeColunas}, 1fr)`,
        }}
      >
        {linhaDeCabecalho.map((header, index) => (
          <div
            key={index}
            style={{
              fontWeight: "bold",
              backgroundColor: "#FF6F21",
              color: "#fff",
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
          gridTemplateColumns: `repeat(${numeroDeColunas}, 1fr)`,
        }}
      >
        {devsList.slice(0, numeroMaximoDeLinhas).map((dev, rowIndex) => (
          <React.Fragment key={dev.idPessoa}>
            <div style={cellStyle}>{dev.idPessoa}</div>
            <div style={cellStyle}>{dev.nomeCompleto}</div>
            <div style={cellStyle}>{dev.email}</div>
            <div style={cellStyle}>{dev.status}</div>
            <div style={cellStyle}>
              {dev.papel}{" "}
              <EditarDev
                id={dev.idPessoa}
                email={dev.email}
                nomeCompleto={dev.nomeCompleto}
                senha={dev.senha}
                status={dev.status}
                papel={dev.papel}
              />
            </div>
          </React.Fragment>
        ))}
      </div>
    </div>
  );
}

const cellStyle = {
  border: "1px solid #ccc",
  padding: 10,
  textAlign: "center",
  position: "relative",
};
