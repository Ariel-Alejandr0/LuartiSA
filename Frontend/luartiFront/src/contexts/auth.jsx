import { createContext, useContext, useEffect, useState } from "react";
import { requestLogin } from "../service/login";

export const AuthCtx = createContext(null);

export const AuthProvider = ({ children }) => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [userData, setUserData] = useState({});
  const doLogin = async (email, senha) => {
    const request = await requestLogin(email, senha);
    if (request) {
      console.log(request);
    }
  };

  useEffect(() => {
    doLogin("tiago.alex@example.com", "senha123");
  }, []);
  
  return (
    <AuthCtx.Provider value={{ loggedIn, userData, setUserData }}>
      {children}
    </AuthCtx.Provider>
  );
};

export const useAuth = () => useContext(AuthCtx);
