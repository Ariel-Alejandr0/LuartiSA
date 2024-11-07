import React from "react";

export default function PersonIcon({ personObj }) {
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
      {personObj?.nomeCompleto}
      <strong>{" x "}</strong>
    </span>
  );
}
