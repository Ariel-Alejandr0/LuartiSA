import axios from "axios";

export async function requestGetAllTiposTarefas() {
  try {
    const request = await axios.get(
      "/luartiBack/LuartiSA?action=listTipoTarefa"
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
