package com.inbound.text.feedMe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Consumidor {

    @Id
    private String cpf;
    @Column(name = "estaPrivado")
    private Boolean isPrivate;
    @Column(name = "estaIncompleto")
    private Boolean isIncompleted;
    @OneToOne(mappedBy = "consumidor")
    private Perfil perfil;

    public Consumidor(){

    }

    public Consumidor(String cpf, Boolean estaPrivate, Boolean estaIncompleto) {
        this.cpf = cpf;
        this.isPrivate = estaPrivate;
        this.isIncompleted = estaIncompleto;
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

    public Boolean getIsIncompleted() {
        return isIncompleted;
    }

    public void setIsIncompleted(Boolean isIncompleted) {
        this.isIncompleted = isIncompleted;
    }
}
