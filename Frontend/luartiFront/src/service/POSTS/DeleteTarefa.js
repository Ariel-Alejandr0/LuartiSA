import axios from "axios";

export async function requestCreateUser(idTarefa) {
  try {
    const request = await axios.post(
      "/luartiBack/LuartiSA?action=/luartiBack/LuartiSA?action=deleteTarefa&id=" +
        idTarefa
    );
    return request.data;
  } catch (e) {
    console.log(e);
    return false;
  }
}
