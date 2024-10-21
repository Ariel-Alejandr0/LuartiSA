import React from "react";

export default function TarefaIcone({
  prazoFinal,
  tituloTarefa,
  numDevs,
  tipoTarefaDesc,
}) {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: 'space-between',
        width: 225,
        height: 225,
        margin: '0 1%',
        backgroundColor: "#FFF",
        border: "3px solid #ff6f21",
        color: "#ff6f21",
        borderRadius: 15,
      }}
    >
      <span style={{ fontWeight: "bold" }}>📅 {prazoFinal}</span>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          width: "100%",
          height: "50%",
        }}
      >
        <span style={{ fontSize: "500%", width: "50%" }}>📝</span>
        <div
          style={{
            diplay: "flex",
            flexDirection: "column",
            justifyContent: "space-between",
            width: "50%",
            height: "100%",
          }}
        >
          <div>
            <strong>{tituloTarefa}</strong>
          </div>
          <div>👨‍👦{numDevs} devs</div>
        </div>
      </div>
      <div><strong>{tipoTarefaDesc}</strong></div>
    </div>
  );
}
