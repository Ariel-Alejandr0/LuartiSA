package model;

public class Pessoa {

    private int idPessoa;
    private String nomeCompleto;
    private String email;
    private String senha;
    private Status status;  // Usando o enum Status
    private Papel papel;    // Usando o enum Papel
    private int idSuperior;

    // Enum para Status
    public enum Status {
        ATIVO,
        INATIVO,
        BLOQUEADO
    }

    // Enum para Papel
    public enum Papel {
        DESENVOLVEDOR,
        ADMIN,
        VISUALIZADOR
    }

    // Getters e Setters

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public int getIdSuperior() {
        return idSuperior;
    }

    public void setIdSuperior(int idSuperior) {
        this.idSuperior = idSuperior;
    }
}
