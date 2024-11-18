import React, { useEffect } from "react";
import EditTipoTarefa from "./EditTipoTarefa";
import DeleteTipoTarefa from "./DeleteTipoTarefa";

export default function TableTipoTarefa({
  numeroMaximoDeLinhas,
  linhaDeCabecalho,
  typesList,
}) {
  const numeroDeColunas = linhaDeCabecalho.length;

  useEffect(() => {
    console.log(typesList);
  }, [typesList]);

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
        {typesList.slice(0, numeroMaximoDeLinhas).map((typeTask, rowIndex) => (
          <React.Fragment key={typeTask.idTipoTarefa}>
            <div style={cellStyle}>{typeTask.idTipoTarefa}</div>
            <div style={cellStyle}>
              {typeTask.descTipoTarefa}
              <EditTipoTarefa
                id={typeTask.idTipoTarefa}
                desc={typeTask.descTipoTarefa}
              />
              <DeleteTipoTarefa id={typeTask.idTipoTarefa} />
            </div>
          </React.Fragment>
        ))}
      </div>
    </div>
  );
}

const cellStyle = {
  position: "relative",
  border: "1px solid #ccc",
  padding: 10,
  textAlign: "center",
};
