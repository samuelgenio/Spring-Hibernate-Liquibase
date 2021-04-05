/*
 * Copyright 2021 Samuka <samuelgenio28@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.samuka.back.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Samuka
 */
@Entity(name = "conta_pagar")
public class ContaPagar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    
    private String nome;
    
    private double vlrOriginal;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT-3")
    private Date datVencimento;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT-3")
    private Date datPagamento;
    
    private double perMulta;
    
    private double perJurosDias;
    
    private long qtdDiasAtrado;
    
    private double vlrCorrigido;
    
    public ContaPagar() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the vlrOriginal
     */
    public double getVlrOriginal() {
        return vlrOriginal;
    }

    /**
     * @param vlrOriginal the vlrOriginal to set
     */
    public void setVlrOriginal(double vlrOriginal) {
        this.vlrOriginal = vlrOriginal;
        this.vlrCorrigido = this.vlrOriginal;
    }

    /**
     * @return the datVencimento
     */
    public Date getDatVencimento() {
        return datVencimento;
    }

    /**
     * @param datVencimento the datVencimento to set
     */
    public void setDatVencimento(Date datVencimento) {
        this.datVencimento = datVencimento;
    }

    /**
     * @return the datPagamento
     */
    public Date getDatPagamento() {
        return datPagamento;
    }

    /**
     * @param datPagamento the datPagamento to set
     */
    public void setDatPagamento(Date datPagamento) {
        this.datPagamento = datPagamento;
    }

    /**
     * @return the perMulta
     */
    public double getPerMulta() {
        return perMulta;
    }

    /**
     * @return the perJurosDias
     */
    public double getPerJurosDias() {
        return perJurosDias;
    }


    /**
     * @return the qtdDiasAtrado
     */
    public long getQtdDiasAtrado() {
        return qtdDiasAtrado;
    }

    /**
     * @return the vlrCorrigido
     */
    public double getVlrCorrigido() {
        return vlrCorrigido;
    }
    
    /**
     * @param vlrCorrigido the vlrCorrigido to set
     */
    public void setVlrCorrigido(double vlrCorrigido) {
        this.vlrCorrigido = vlrCorrigido;
    }
    
    /**
     * Atualiza o valor corrgido de acordo com os juros e multas.
     * @param qtdDiasAtrado Dias de atraso
     * @param perJurosDias % juros por dia
     * @param perMulta % multa total
     */
    public void setVlrCorrigidoJurosMulta(long qtdDiasAtrado, double perJurosDias, double perMulta) {
        this.qtdDiasAtrado = qtdDiasAtrado;
        this.perJurosDias = perJurosDias;
        this.perMulta = perMulta;
        
        this.setVlrCorrigido((vlrOriginal + (vlrOriginal * this.perMulta/100)) 
                +  ((vlrOriginal * perJurosDias/100) * qtdDiasAtrado));
    }

}
