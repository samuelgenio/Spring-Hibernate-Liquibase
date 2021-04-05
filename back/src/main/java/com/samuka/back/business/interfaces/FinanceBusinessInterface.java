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
package com.samuka.back.business.interfaces;

import com.samuka.back.entity.ContaPagar;
import com.samuka.back.exception.InternalServiceOperationException;

/**
 *
 * @author Samuka <samuelgenio28@gmail.com>
 */
public interface FinanceBusinessInterface {
    
    /**
     * Valida despesa antes de ser persistida.
     * @param conta
     * @return
     * @throws InternalServiceOperationException 
     */
    boolean validate(ContaPagar conta) throws InternalServiceOperationException;
    
    /**
     * Insere registro na base de dados.
     * @param contaPagar
     * @return 
     */
    ContaPagar insert(ContaPagar contaPagar);
    
    /**
     * Valida campos da despesa.
     * @param contaPagar 
     */
    void inputsValidate(ContaPagar contaPagar) throws InternalServiceOperationException;
}
