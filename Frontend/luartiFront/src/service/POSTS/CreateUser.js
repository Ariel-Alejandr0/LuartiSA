import axios from "axios";

export async function requestCreateUser(
  nomeCompleto,
  email,
  senha,
  status,
  papel,
  idSuperior
) {
  try {
    const request = await axios.post("/luartiBack/LuartiSA?action=addPessoa", {
      nomeCompleto,
      email,
      senha,
      status,
      papel,
      idSuperior,
    });
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
