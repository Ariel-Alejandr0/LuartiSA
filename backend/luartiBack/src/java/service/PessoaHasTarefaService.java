package service;

import dao.PessoaHasTarefaDAO;
import model.PessoaHasTarefa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PessoaHasTarefaService {

    private PessoaHasTarefaDAO pessoaHasTarefaDAO;

    public PessoaHasTarefaService(Connection connection) {
        this.pessoaHasTarefaDAO = new PessoaHasTarefaDAO(connection);
    }

    public void adicionarPessoaHasTarefa(PessoaHasTarefa pessoaHasTarefa) throws SQLException {
        if (!pessoaHasTarefaDAO.existe(pessoaHasTarefa.getIdPessoa(), pessoaHasTarefa.getIdTarefa())) {
            pessoaHasTarefaDAO.adicionar(pessoaHasTarefa);
        } else {
            throw new SQLException("A relação entre a pessoa e a tarefa já existe.");
        }
    }

    public List<PessoaHasTarefa> listarTodos() throws SQLException {
        return pessoaHasTarefaDAO.listarTodos();
    }

    public void deletarPessoaHasTarefa(int idPessoa, int idTarefa) throws SQLException {
        if (pessoaHasTarefaDAO.existe(idPessoa, idTarefa)) {
            pessoaHasTarefaDAO.deletar(idPessoa, idTarefa);
        } else {
            throw new SQLException("A relação entre a pessoa e a tarefa não existe.");
        }
    }
}
