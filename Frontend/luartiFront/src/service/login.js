import axios from "axios";

export function requestLogin(email, senha) {
  try {
    const request = axios.post(
      "http://localhost:8070/luartiBack/LuartiSA?action=login", // adicionado http://
      {
        email: email,
        senha: senha,
      }
    );
    return request;
  } catch (e) {
    console.log(e);
  }
}
