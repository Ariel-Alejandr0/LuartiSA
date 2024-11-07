import axios from "axios";

export async function requestGetPessoasGHasTarefas() {
  try {
    const request = await axios.get("/luartiBack/LuartiSA?action=listPessoaHasTarefa");
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
