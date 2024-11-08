import axios from "axios";

export async function requestCreateTipoTarefa(
  nomeTarefa,
  descTarefa,
  dataCriacao,
  dataFim,
  status,
  idTipoTarefa
) {
  try {
    const request = await axios.post("/luartiBack/LuartiSA?action=addPessoa", {
      nomeTarefa,
      descTarefa,
      dataCriacao,
      dataFim,
      status,
      idTipoTarefa,
    });
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
