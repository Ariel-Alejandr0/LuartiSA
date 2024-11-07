import axios from "axios";

export async function requestGetTarefas(idTipoTarefa) {
  try {
    const request = await axios.get(
      "/luartiBack/LuartiSA?action=getTipoTarefa&id=" + idTipoTarefa
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
