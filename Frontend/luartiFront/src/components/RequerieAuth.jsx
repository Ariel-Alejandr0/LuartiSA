import React, { useEffect } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/auth";
import MainLayout from "../layouts/MainLayout";

export default function RequireAuth() {
  const { loggedIn } = useAuth();
  const navigate = useNavigate();
  useEffect(() => {
    if (!loggedIn) {
      navigate("/login");
    } else {
      navigate("/tarefas");
    }
  }, [loggedIn]);

  return loggedIn ? <Outlet /> : null;
}
