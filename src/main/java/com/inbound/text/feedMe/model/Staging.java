package com.inbound.text.feedMe.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staging {

    @Id
    private String cpf;
    private Boolean isValidCpf;
    private Boolean isPrivate;
    private Boolean isIncompleto;
    private String dataUltimaCompra;
    private Double ticketMedio;
    private Double ticketUltimaCompra;
    private String lojaMaisFrequente;
    private Boolean isValidLojaMaisFrequente;
    private String lojaUltimaCompra;
    private Boolean isValidLojaUltimaCompra;

    public Boolean getValidCpf() {
        return isValidCpf;
    }

    public void setValidCpf(Boolean validCpf) {
        isValidCpf = validCpf;
    }

    public Boolean getValidLojaMaisFrequente() {
        return isValidLojaMaisFrequente;
    }

    public void setValidLojaMaisFrequente(Boolean validLojaMaisFrequente) {
        isValidLojaMaisFrequente = validLojaMaisFrequente;
    }

    public Boolean getValidLojaUltimaCompra() {
        return isValidLojaUltimaCompra;
    }

    public void setValidLojaUltimaCompra(Boolean validLojaUltimaCompra) {
        isValidLojaUltimaCompra = validLojaUltimaCompra;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean getIsIncompleto() {
        return isIncompleto;
    }

    public void setIsIncompleto(Boolean isIncompleto) {
        this.isIncompleto = isIncompleto;
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
