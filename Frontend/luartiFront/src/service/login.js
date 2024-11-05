import axios from "axios";

export async function requestLogin(email, senha) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=login", // adicionado http://
      {
        email: email,
        senha: senha,
      }
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
