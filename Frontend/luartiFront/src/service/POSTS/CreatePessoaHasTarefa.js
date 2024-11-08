import axios from "axios";

export async function requestCreatePessoaHasTarefa(idTarefa, idPessoa) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=addPessoaHasTarefa",
      {
        idTarefa,
        idPessoa,
      }
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
