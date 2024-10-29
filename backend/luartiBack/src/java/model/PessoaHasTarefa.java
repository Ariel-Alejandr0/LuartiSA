
package model;


public class PessoaHasTarefa {

    private int idPessoa;
    private int idTarefa;

    // Construtor vazio
    public PessoaHasTarefa() {
    }
 
    // Construtor com parâmetros
    public PessoaHasTarefa(int idPessoa, int idTarefa) {
        this.idPessoa = idPessoa;
        this.idTarefa = idTarefa;
    }

    // Getters e Setters
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    // Método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "PessoaHasTarefa{" +
                "idPessoa=" + idPessoa +
                ", idTarefa=" + idTarefa +
                '}';
    }
}
