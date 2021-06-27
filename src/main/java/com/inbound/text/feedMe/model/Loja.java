package com.inbound.text.feedMe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Loja {

    @Id
    private String cnpj;
    @OneToOne(mappedBy = "lojaMaisFrequente")
    private Perfil lojaMaisFrequente;
    @OneToOne(mappedBy = "lojaUltimaCompra")
    private Perfil lojaUltimaCompra;

    public Loja (){

    }

    public Loja(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
