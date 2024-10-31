package service;

import dao.PessoaDAO;
import model.Pessoa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PessoaService {

    private PessoaDAO pessoaDAO;

    public PessoaService(Connection connection) {
        this.pessoaDAO = new PessoaDAO(connection);
    }

    public void createPessoa(Pessoa pessoa) throws SQLException {
        pessoaDAO.save(pessoa);
    }

    public List<Pessoa> getAllPessoas() throws SQLException {
        return pessoaDAO.findAll();
    }

    public Pessoa getPessoaById(int id) throws SQLException {
        return pessoaDAO.findById(id);
    }

    public void updatePessoa(Pessoa pessoa) throws SQLException {
        pessoaDAO.update(pessoa);
    }

    public void deletePessoa(int id) throws SQLException {
        pessoaDAO.delete(id);
    }
    
    public Pessoa autenticar(String email, String senha) throws SQLException{
        return pessoaDAO.autenticar(email, senha);
    }
}
