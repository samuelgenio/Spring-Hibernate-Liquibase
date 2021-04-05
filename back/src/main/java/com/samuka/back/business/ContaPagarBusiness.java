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
package com.samuka.back.business;

import com.samuka.back.entity.ContaPagar;
import com.samuka.back.entity.ContaPagarRules;
import com.samuka.back.exception.InternalServiceOperationException;
import com.samuka.back.business.interfaces.FinanceBusinessInterface;
import com.samuka.back.repo.ContaPagarRepository;
import com.samuka.back.repo.ContaPagarRulesRepository;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuka <samuelgenio28@gmail.com>
 */
@Service
public class ContaPagarBusiness implements FinanceBusinessInterface {
    
    @Autowired
    private ContaPagarRepository repo;
    
    @Autowired
    private ContaPagarRulesRepository repoRules;
    
    /**
    * Valida conta a pagar garantindo que ela esteja em condições de ser
    * persistida.
    */
    @Override
    public boolean validate(ContaPagar contaPagar) throws InternalServiceOperationException {
    
        inputsValidate(contaPagar);

        if (contaPagar.getDatPagamento().after(contaPagar.getDatVencimento())) {
            long diasAtraso = contaPagar.getDatVencimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().until(
                            contaPagar.getDatPagamento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
                            ChronoUnit.DAYS);
            
            List<ContaPagarRules> rules = repoRules.findAll(Sort.by(Sort.Direction.DESC, "id"));
            
            for (ContaPagarRules rule : rules) {
                if (diasAtraso >= rule.getQtdDias()) {
                    contaPagar.setVlrCorrigidoJurosMulta(diasAtraso, rule.getPerJurosDias(), rule.getPerMulta());
                    break;
                }
            }
        }
        
        return true;
        
    }

    @Override
    public ContaPagar insert(ContaPagar contaPagar) {
        
        try {
            return repo.save(contaPagar);
        } catch (Exception e) {
            throw new InternalServiceOperationException("Falha ao inserir Despesa. " + e.getMessage());
        }
        
    }
    
    public boolean delete(ContaPagar contaPagar) {
        
        try {
            repo.delete(contaPagar);
            return true;
        } catch (Exception e) {
            throw new InternalServiceOperationException("Falha ao remover Despesa. " + e.getMessage());
        }
        
    }

    /**
     * Lista de despesas por e-mail.
     * @param email E-mail a ser filtrado.
     * @return 
     */
    public List<ContaPagar> getListByEmail(String email) {
    
        try {
            return repo.findAllByEMail(email);
        } catch (Exception e) {
            throw new InternalServiceOperationException("Falha ao obter Lista de Despesas. " + e.getMessage());
        }
        
    }

    /**
     * Valida os campos obrigatórios.
     * @param contaPagar 
     */
    @Override
    public void inputsValidate(ContaPagar contaPagar) {

        if (contaPagar.getDatPagamento() == null || contaPagar.getDatVencimento() == null) {
            throw new InternalServiceOperationException("Informe todas as datas.");
        }

        if (contaPagar.getNome() == null || contaPagar.getNome().isEmpty()) {
            throw new InternalServiceOperationException("Nome não pode ser vazio.");
        }

        if (contaPagar.getEmail() == null || contaPagar.getEmail().isEmpty()) {
            throw new InternalServiceOperationException("Email não pode ser vazio.");
        }

        if (contaPagar.getVlrOriginal() <= 0) {
            throw new InternalServiceOperationException("Valor Original deve ser maior que 0.");
        }
    
    }
    
}
