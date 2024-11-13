import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";
import TarefaForm from "./TarefaForm";
import { useAuth } from "../../contexts/auth";

export default function TarefaIcone({
  idTarefa,
  idTipoTarefa,
  tiposDeTarefa,
  prazoFinal,
  dataCriacao,
  tituloTarefa,
  numDevs,
  descTarefa,
  users,
  usersHasTarefas,
}) {
  const MySwal = withReactContent(Swal);
  const { userData } = useAuth();
  console.log(prazoFinal);
  const handleOnClick = async () => {
    MySwal.fire({
      title: " ",
      width: "70vw",
      showCloseButton: true,
      showConfirmButton: false,
      html: (
        <TarefaForm
          key={idTarefa}
          idTarefa={idTarefa}
          prazoFinal={prazoFinal}
          tituloTarefa={tituloTarefa}
          userData={userData}
          descTarefa={descTarefa}
          users={users}
          usersHasTarefas={usersHasTarefas}
          idTipoTarefa={idTipoTarefa}
          tiposDeTarefa={tiposDeTarefa}
          dataCriacao={dataCriacao}
        />
      ),
    });
  };
  function formatDate(dateStr) {
    const date = new Date(dateStr);

    return new Intl.DateTimeFormat("pt-BR", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
      timeZone: "UTC",
    }).format(date);
  }
  return (
    <div
      onClick={handleOnClick}
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "space-between",
        width: 225,
        height: 225,
        margin: "0 1%",
        backgroundColor: "#fdf8f4",
        border: "3px solid #ff6f21",
        color: "#ff6f21",
        borderRadius: 15,
      }}
    >
      <span style={{ fontWeight: "bold" }}>📅 {formatDate(prazoFinal)}</span>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          width: "100%",
          height: "50%",
        }}
      >
        <span style={{ fontSize: "500%", width: "50%" }}>📝</span>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between",
            width: "50%",
            height: "100%",
          }}
        >
          <div>
            <strong>{tituloTarefa}</strong>
          </div>
          <div>👨‍👦{numDevs} devs</div>
        </div>
      </div>
      <div>
        <strong>
          {
            tiposDeTarefa?.find((i) => i.idTipoTarefa == idTipoTarefa)
              .descTipoTarefa
          }
        </strong>
      </div>
    </div>
  );
}
