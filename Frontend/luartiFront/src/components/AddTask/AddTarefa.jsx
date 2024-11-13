import React, { useRef, useState } from "react";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import { requestCreateTarefa } from "../../service/POSTS/CreateTarefa";
import { formatDate } from "../../functions/formatDate";
import AddTaskContent from "./AddTaskContent";

export default function AddTipoTarefa({ tiposDeTarefa, users }) {
  const MySwal = withReactContent(Swal);
  console.log(JSON.stringify(users));

  const handleOnClick = async () => {
    await MySwal.fire({
      title: "Cadastro De Tarefa",
      width: "70vw",
      showCloseButton: true,
      confirmButtonText: "Cadastrar",
      html: <AddTaskContent tiposDeTarefa={tiposDeTarefa} users={users} />,
      preConfirm: async () => {
        const objForm = {
          nomeTarefa: document.querySelector("#tituloTarefa").value,
          descTarefa: document.querySelector("#descTarefa").value,
          tipoTarefa: document.querySelector("#tipoTarefa").value,
          dataFim: document.querySelector("#dataFim").value,
          devs: document.querySelector("#devs").value,
        };

        const req = await requestCreateTarefa(
          objForm.nomeTarefa,
          objForm.descTarefa,
          formatDate(new Date()),
          formatDate(objForm.dataFim),
          "PENDENTE",
          objForm.tipoTarefa
        ); //esperando bingull retornar o id da Tarefa para adicionar desenvolvedores à tarefa
        //const req2 = await requestCreatePessoaHasTarefa()
        if (req) {
          MySwal.fire({
            title: "Sucesso!",
            icon: "success",
            text: "Tarefa Cadastrada com sucesso.",
          });
        }
      },
    });
  };

  return (
    <button
      onClick={handleOnClick}
      style={{
        backgroundColor: "#FF6F21",
        color: "#FFF",
        width: 200,
        height: 50,
        borderRadius: 25,
      }}
    >
      + Adicionar Tarefa
    </button>
  );
}
