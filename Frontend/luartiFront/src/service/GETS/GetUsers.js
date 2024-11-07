import axios from "axios";

export async function requestGetUsers() {
  try {
    const request = await axios.get("/luartiBack/LuartiSA?action=listPessoa");
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
