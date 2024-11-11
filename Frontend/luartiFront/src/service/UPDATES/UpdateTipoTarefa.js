import axios from "axios";

export async function requestUpdateTipoTarefa(idTipoTarefa, descTipoTarefa) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=updateTipoTarefa",
      {
        idTipoTarefa,
        descTipoTarefa,
      }
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
