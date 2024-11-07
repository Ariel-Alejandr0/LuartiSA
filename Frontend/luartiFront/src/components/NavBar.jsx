import React from "react";
import { NavLink } from "react-router-dom";
import { useAuth } from "../contexts/auth";

export default function NavBar() {
  const { userData } = useAuth();

  return (
    <div
      style={{
        position: "fixed",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        width: "100vw",
        height: 80,
        backgroundColor: "#5E4B8D",
      }}
    >
      <img src="/logoLuarti.png" height={"100%"} />
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          height: "50%",
          width: "40%",
        }}
      >
        <NavLink to={"/tarefas"} style={{ color: "#fff" }}>
          Tarefas
        </NavLink>
        {userData?.papel == "ADMIN" && (
          <>
            <NavLink to={"/cadastroDevs"} style={{ color: "#fff" }}>
              Cadastro de Desenvolvedores
            </NavLink>

            <NavLink to={"/cadastroTipoTarefas"} style={{ color: "#fff" }}>
              Cadastro de Tipo de Tarefas
            </NavLink>
          </>
        )}
      </div>
    </div>
  );
}
