import axios from "axios";

export async function requestDeletePessoaHasTarefa(idTarefa, idPessoa) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=deletePessoaHasTarefa",
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
