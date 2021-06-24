package com.inbound.text.feedMe.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staging {

    @Id
    private String cpf;
    private Boolean estaPrivate;
    private Boolean estaIncompleto;
    private String dataUltimaCompra;
    private Double ticketMedio;
    private Double ticketUltimaCompra;
    private String lojaMaisFrequente;
    private String lojaUltimaCompra;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getEstaPrivate() {
        return estaPrivate;
    }

    public void setEstaPrivate(Boolean estaPrivate) {
        this.estaPrivate = estaPrivate;
    }

    public Boolean getEstaIncompleto() {
        return estaIncompleto;
    }

    public void setEstaIncompleto(Boolean estaIncompleto) {
        this.estaIncompleto = estaIncompleto;
    }

    public String getDataUltimaCompra() {
        return dataUltimaCompra;
    }

    public void setDataUltimaCompra(String dataUltimaCompra) {
        this.dataUltimaCompra = dataUltimaCompra;
    }

    public Double getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(Double ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public Double getTicketUltimaCompra() {
        return ticketUltimaCompra;
    }

    public void setTicketUltimaCompra(Double ticketUltimaCompra) {
        this.ticketUltimaCompra = ticketUltimaCompra;
    }

    public String getLojaMaisFrequente() {
        return lojaMaisFrequente;
    }

    public void setLojaMaisFrequente(String lojaMaisFrequente) {
        this.lojaMaisFrequente = lojaMaisFrequente;
    }

    public String getLojaUltimaCompra() {
        return lojaUltimaCompra;
    }

    public void setLojaUltimaCompra(String lojaUltimaCompra) {
        this.lojaUltimaCompra = lojaUltimaCompra;
    }
}
