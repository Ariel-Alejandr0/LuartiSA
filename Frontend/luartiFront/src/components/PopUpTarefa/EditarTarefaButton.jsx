import React, { useState } from "react";
import { requestUpdateTarefa } from "../../service/UPDATES/UpdateTarefa";
import withReactContent from "sweetalert2-react-content";
import Swal from "sweetalert2";
import { requestCreatePessoaHasTarefa } from "../../service/POSTS/CreatePessoaHasTarefa";
import { requestDeletePessoaHasTarefa } from "../../service/DELETES/DeletePessoaHasTarefa";

export default function EditarTarefaButton({
  cantEdit,
  setCantEdit,
  taskData,
  usersAtual,
  selectedUsers,
}) {
  const MySwal = withReactContent(Swal);
  const handleOnClick = () => {
    setCantEdit((prev) => !prev);
  };
  const handleOnSave = async () => {
    const usersToExcludeTask = usersAtual.filter(
      (uA) =>
        !selectedUsers.find((sltdUsrs) => sltdUsrs.idPessoa == uA.idPessoa) ||
        []
    );
    const usersToAddTask = selectedUsers.filter(
      (sltdUsrs) =>
        !usersAtual.find((uA) => sltdUsrs.idPessoa == uA.idPessoa) || []
    );
    console.log(usersToAddTask);
    console.log(usersToExcludeTask);
    try {
      const res = await requestUpdateTarefa(
        taskData.idTarefa,
        taskData.tituloTarefa,
        taskData.descTarefa,
        taskData.dataCriacao,
        taskData.prazoFinal,
        "PENDENTE",
        taskData?.tipoDaTarefa?.idTipoTarefa || taskData.tipoDaTarefa
      );
      usersToExcludeTask.forEach(async (element) => {
        await requestDeletePessoaHasTarefa(taskData.idTarefa, element.idPessoa);
      });
      usersToAddTask.forEach(async (element) => {
        await requestCreatePessoaHasTarefa(taskData.idTarefa, element.idPessoa);
      });
      MySwal.fire({
        text: res.message,
        preConfirm: () => {
          location.href = location.href;
        },
      });
    } catch (e) {
      MySwal.fire({
        icon: "error",
        text: "ocorreu um erro durante a edição da tarefa!",
        preConfirm: () => {
          location.href = location.href;
        },
      });
      console.error(e);
    }
  };
  return (
    <>
      <button
        onClick={handleOnClick}
        style={{
          height: 50,
          backgroundColor: cantEdit ? "black" : "grey",
          color: "#fff",
        }}
      >
        ✏️ Editar
      </button>
      {!cantEdit && (
        <button
          onClick={handleOnSave}
          style={{
            height: 50,
            backgroundColor: "green",
            color: "#fff",
          }}
        >
          ✔️ Salvar
        </button>
      )}
    </>
  );
}
