import { createContext, useContext, useEffect, useState } from "react";
import { requestLogin } from "../service/login";
import { useNavigate } from "react-router-dom";

export const AuthCtx = createContext(null);

export const AuthProvider = ({ children }) => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [userData, setUserData] = useState({});
  const navigate = useNavigate();
  const doLogin = async (email, senha) => {
    const request = await requestLogin(email, senha);
    if (!request.error) {
      localStorage.setItem("email", email);
      localStorage.setItem("senha", senha);
      setLoggedIn(true);
      setUserData(request);
      console.log(request);
      navigate('/tarefas')
    } else {
      alert(request.error)
    }
  };

  useEffect(() => {
    const email = localStorage.getItem("email");
    const senha = localStorage.getItem("senha");
    if (email && senha) {
      doLogin(email, senha);
    }
    //doLogin("tiago.alex@example.com, "senha123");
  }, []);

  return (
    <AuthCtx.Provider value={{ loggedIn, userData, setUserData, doLogin }}>
      {children}
    </AuthCtx.Provider>
  );
};

export const useAuth = () => useContext(AuthCtx);
