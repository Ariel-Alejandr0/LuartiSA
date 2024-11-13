import axios from "axios";

export async function requestUpdateUser(
  idPessoa,
  nomeCompleto,
  email,
  senha,
  status,
  papel,
  idSuperior
) {
  try {
    const request = await axios.post("/luartiBack/LuartiSA?action=updatePessoa", {
      idPessoa,
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
