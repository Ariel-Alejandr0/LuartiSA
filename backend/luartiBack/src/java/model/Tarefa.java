package model;

import java.util.Date;

public class Tarefa {
    private int idTarefa;
    private String nomeTarefa;
    private String descTarefa;
    private Date dataCriacao;
    private Date dataFim;
    private Status status; // Aqui usamos o enum Status
    private int idTipoTarefa;
    
    public enum Status {
    CONCLUIDA, 
    INICIADA, 
    PENDENTE, 
    EM_ANDAMENTO};

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescTarefa() {
        return descTarefa;
    }

    public void setDescTarefa(String descTarefa) {
        this.descTarefa = descTarefa;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getIdTipoTarefa() {
        return idTipoTarefa;
    }

    public void setIdTipoTarefa(int idTipoTarefa) {
        this.idTipoTarefa = idTipoTarefa;
    }
}
