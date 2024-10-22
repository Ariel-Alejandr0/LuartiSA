package service;

import dao.TipoTarefaDAO;
import model.TipoTarefa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TipoTarefaService {

    private TipoTarefaDAO tipoTarefaDAO;

    public TipoTarefaService(Connection connection) {
        this.tipoTarefaDAO = new TipoTarefaDAO(connection);
    }

    public void adicionarTipoTarefa(TipoTarefa tipoTarefa) throws SQLException {
        tipoTarefaDAO.adicionar(tipoTarefa);
    }

    public TipoTarefa obterTipoTarefaPorId(int id) throws SQLException {
        return tipoTarefaDAO.obterPorId(id);
    }

    public List<TipoTarefa> listarTodos() throws SQLException {
        return tipoTarefaDAO.listarTodos();
    }

    public void atualizarTipoTarefa(TipoTarefa tipoTarefa) throws SQLException {
        tipoTarefaDAO.atualizar(tipoTarefa);
    }

    public void deletarTipoTarefa(int id) throws SQLException {
        tipoTarefaDAO.deletar(id);
    }
}
