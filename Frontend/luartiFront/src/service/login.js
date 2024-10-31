import axios from "axios";

export function doLogin(email, senha) {
  try {
    const request = axios.post(
      "localhost:8070/luartiBack/LuartiSA?action=login",
      {
        email,
        senha,
      }
    );
    return request;
  } catch (e) {
    console.log(e);
  }
}
