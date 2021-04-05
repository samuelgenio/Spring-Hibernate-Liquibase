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
package com.samuka.back.controller;

import com.samuka.back.business.ContaPagarBusiness;
import com.samuka.back.entity.ContaPagar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Samuka <samuelgenio28@gmail.com>
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/contas")
public class ContaPagarController {
    
    @Autowired
    private ContaPagarBusiness business;
    
    /**
     * Obtém despesas de acordo com um e-mail.
     * @param email
     * @return
     */
    @GetMapping(value = {"/{email}"})
    public ResponseEntity get(@PathVariable(required = true) String email) {

        List<ContaPagar> list = business.getListByEmail(email);
        if (list != null) {
            return ResponseEntity.ok(list);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Insere Despesa na base de dados.
     * @param contaPagar
     * @return
     */
    @PostMapping()
    public ResponseEntity persist(@RequestBody ContaPagar contaPagar) {
        
        business.validate(contaPagar);
        ContaPagar saved = business.insert(contaPagar);
        return ResponseEntity.ok(saved);
        
    }
    
}
