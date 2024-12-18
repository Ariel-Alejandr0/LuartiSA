import axios from "axios";

export async function requestCreateTipoTarefa(idTipoTarefa, descTipoTarefa) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=addTipoTarefa",
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
