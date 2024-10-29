package Backend.BackendLuarti.src.main.webapp.code;

public class Pessoa {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdSuperior() {
        return idSuperior;
    }

    public void setIdSuperior(int idSuperior) {
        this.idSuperior = idSuperior;
    }

    private int idPessoa;
    private String nomeCompleto;
    private String email;
    private  String senha;
    private String status;
    private int idSuperior;



}
