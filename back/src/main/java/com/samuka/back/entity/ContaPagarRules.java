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

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Samuka
 */
@Entity(name = "conta_pagar_rules")
public class ContaPagarRules implements Serializable {

    //@JsonIgnore
    private @Id
    @GeneratedValue
    Long id;

    /**
     * 0 para até 3 dias;
     * 4 para superior a 3 dias
     * 6 para superior a 5 dias
     */
    private Integer qtdDias;
    
    private Double perMulta;
    
    @Column(name="perJurosDias")
    private Double perJurosDias;
    
    public ContaPagarRules() {
    }
    
    public ContaPagarRules(int qtdDias, double perMulta, double perJurosDia) {
        this.qtdDias = qtdDias;
        this.perMulta = perMulta;
        this.perJurosDias = perJurosDia;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the qtdDias
     */
    public Integer getQtdDias() {
        return qtdDias;
    }

    /**
     * @param qtdDias the qtdDias to set
     */
    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }

    /**
     * @return the perMulta
     */
    public Double getPerMulta() {
        return perMulta;
    }

    /**
     * @param perMulta the perMulta to set
     */
    public void setPerMulta(Double perMulta) {
        this.perMulta = perMulta;
    }

    /**
     * @return the perJurosDias
     */
    public Double getPerJurosDias() {
        return perJurosDias;
    }

    /**
     * @param perJurosDias the perJurosDias to set
     */
    public void setPerJurosDias(Double perJurosDias) {
        this.perJurosDias = perJurosDias;
    }

    
}
