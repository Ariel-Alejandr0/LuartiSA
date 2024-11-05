import axios from "axios";

export async function requestGetTarefas() {
  try {
    const request = await axios.get("/luartiBack/LuartiSA?action=listTarefa");
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
