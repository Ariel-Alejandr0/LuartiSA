import axios from "axios";

export async function requestDeleteTipoTarefa(idTipoTarefa) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=deleteTipoTarefa&id=" + idTipoTarefa
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
