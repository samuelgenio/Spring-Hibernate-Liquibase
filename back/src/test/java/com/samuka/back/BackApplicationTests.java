package com.samuka.back;

import com.samuka.back.business.ContaPagarBusiness;
import com.samuka.back.entity.ContaPagar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Simples classe para testar processos internos.
 * Foram executados os testes diretamente acessando os métodos.
 * Não foi implementado MockMvc.
 * @author Samuka <samuelgenio28@gmail.com>
 */
@SpringBootTest
class BackApplicationTests {

    @Autowired
    private ContaPagarBusiness business;
    
    /**
    * Teste de despesa sem juros e multa.
    */
    @Test
    @DisplayName("Valida Despesa sem atraso.")
    void testContaPagarBusinessEquivalente() {
    
        ContaPagar contaPagar = getObject();
        
        business.validate(contaPagar);
        
        Assertions.assertEquals(contaPagar.getVlrOriginal(), contaPagar.getVlrCorrigido(), "Vencimento igual Pagamento");
    
    }
    
    /**
    * Teste de despesa com 1 dias de juros e multa.
    */
    @Test
    @DisplayName("Valida Despesa com atraso de 1 dia.")
    void testContaPagarBusinessUmDiaAtraso() {
    
        ContaPagar contaPagar = getObject();
            
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(1);
        contaPagar.setDatPagamento(Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()));
        
        business.validate(contaPagar);
        
        Assertions.assertEquals(102.1, contaPagar.getVlrCorrigido(), "Vencimento Juros e Multa de 1 dia");
    
    }
    
    /**
    * Teste de despesa com 4 dias de juros e multa.
    */
    @Test
    @DisplayName("Valida Despesa com atraso de 4 dias.")
    void testContaPagarBusiness4DiasAtraso() {
    
        ContaPagar contaPagar = getObject();
        
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(4);
        contaPagar.setDatPagamento(Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()));
            
        business.validate(contaPagar);
        
        Assertions.assertEquals(103.8, contaPagar.getVlrCorrigido(), "Vencimento Juros e Multa de 4 dias");
    
    }

    /**
    * Teste de despesa com 10 dias de juros e multa.
    */
    @Test
    @DisplayName("Valida Despesa com atraso de 10 dias.")
    void testContaPagarBusiness10DiasAtraso() {
    
        ContaPagar contaPagar = getObject();
        
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(10);
        contaPagar.setDatPagamento(Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()));
            
        business.validate(contaPagar);
        
        Assertions.assertEquals(109.0, contaPagar.getVlrCorrigido(), "Vencimento Juros e Multa de 10 dias");
    
    }
    
    /**
    * Teste de inserção e remoção de despesa.
    */
    @Test
    @DisplayName("Valida inserção e remoção de despesa.")
    void testInsertDelete() {
    
        ContaPagar contaPagar = getObject();
        
        business.validate(contaPagar);
        
        ContaPagar result = business.insert(contaPagar);
        
        Assertions.assertNotNull(result);
        
        Assertions.assertEquals(business.delete(contaPagar), true);
    
    }
    
    private ContaPagar getObject() {
    
        ContaPagar contaPagar = new ContaPagar();
        
        LocalDate localDate = LocalDate.now();

        contaPagar.setNome("Test1");
        contaPagar.setEmail("test@email.com");
        contaPagar.setVlrOriginal(100.00);
        contaPagar.setDatVencimento(Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        contaPagar.setDatPagamento(Date.from(localDate.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()));
        
        return contaPagar;
    }

}
