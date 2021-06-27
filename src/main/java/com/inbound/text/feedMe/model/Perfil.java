package com.inbound.text.feedMe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Perfil {

    @Id
    @GeneratedValue
    private Long id;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Consumidor consumidor;
    private String dataUltimaCompra;
    private Double ticketMedio;
    private Double ticketUltimaCompra;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Loja lojaMaisFrequente;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Loja lojaUltimaCompra;

    public Perfil(){

    }
    public Perfil(Long id, Consumidor consumidor, String dataUltimaCompra, Double ticketMedio, Double ticketUltimaCompra, Loja lojaMaisFrequente, Loja lojaUltimaCompra) {
        this.id = id;
        this.consumidor = consumidor;
        this.dataUltimaCompra = dataUltimaCompra;
        this.ticketMedio = ticketMedio;
        this.ticketUltimaCompra = ticketUltimaCompra;
        this.lojaMaisFrequente = lojaMaisFrequente;
        this.lojaUltimaCompra = lojaUltimaCompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
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

    public Loja getLojaMaisFrequente() {
        return lojaMaisFrequente;
    }

    public void setLojaMaisFrequente(Loja lojaMaisFrequente) {
        this.lojaMaisFrequente = lojaMaisFrequente;
    }

    public Loja getLojaUltimaCompra() {
        return lojaUltimaCompra;
    }

    public void setLojaUltimaCompra(Loja lojaUltimaCompra) {
        this.lojaUltimaCompra = lojaUltimaCompra;
    }
}
