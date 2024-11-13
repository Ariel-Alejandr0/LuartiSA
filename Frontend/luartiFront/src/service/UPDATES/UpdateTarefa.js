import axios from "axios";

export async function requestUpdateTarefa(
  idTarefa,
  nomeTarefa,
  descTarefa,
  dataCriacao,
  dataFim,
  status,
  idTipoTarefa
) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=updateTarefa",
      {
        idTarefa,
        nomeTarefa,
        descTarefa,
        dataCriacao,
        dataFim,
        status,
        idTipoTarefa,
      }
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
