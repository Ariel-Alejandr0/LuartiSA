import React from "react";
import NavBar from "../components/navBar";

export default function MainLayout({ children }) {
  return (
    <div
      style={{
        width: "100%",
        height: "100%",
      }}
    >
      <NavBar />
      <div
        style={{
          display: "flex",
          flexDirection: 'column',
          justifyContent: "center",
          alignItems: "center",
          width: "100%",
          height: "calc(100vh - 80px)",
          paddingTop: 80,
        }}
      >
        {children}
      </div>
    </div>
  );
}
