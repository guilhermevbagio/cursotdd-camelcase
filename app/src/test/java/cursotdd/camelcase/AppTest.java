package cursotdd.camelcase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

class AppTest {

    App quebraCamelCase;
    @BeforeEach
    void setUp() {
        quebraCamelCase = new App();
    }
    @Test void nome() {
        List<String> expected = List.of("nome");
        List<String> actual = quebraCamelCase.quebraCamelCase("nome");
        assertEquals(expected, actual);
    }

    @Test void nome2() {
        List<String> expected = List.of("nome");
        List<String> actual = quebraCamelCase.quebraCamelCase("Nome");
        assertEquals(expected, actual);
    }

    @Test void nomeComposto(){
        List<String> expected = List.of("nome", "composto");
        List<String> actual = quebraCamelCase.quebraCamelCase("nomeComposto");
        assertEquals(expected, actual);
    }

    @Test void nomeComposto2(){
        List<String> expected = List.of("nome", "composto");
        List<String> actual = quebraCamelCase.quebraCamelCase("NomeComposto");
        assertEquals(expected, actual);
    }

    @Test void cpf(){
        List<String> expected = List.of("CPF");
        List<String> actual = quebraCamelCase.quebraCamelCase("CPF");
        assertEquals(expected, actual);
    }

    @Test void numerocpf(){
        List<String> expected = List.of("numero", "CPF");
        List<String> actual = quebraCamelCase.quebraCamelCase("numeroCPF");
        assertEquals(expected, actual);
    }

    @Test void numerocpf2(){
        List<String> expected = List.of("numero", "CPF", "contribuinte");
        List<String> actual = quebraCamelCase.quebraCamelCase("numeroCPFContribuinte");
        assertEquals(expected, actual);
    }

    @Test void recupera10Primeiros(){
        List<String> expected = List.of("recupera", "10", "primeiros");
        List<String> actual = quebraCamelCase.quebraCamelCase("recupera10Primeiros");
        assertEquals(expected, actual);
    }

    @Test void dezPrimeiros(){
        assertThrows(StartsWithNumberException.class, () -> quebraCamelCase.quebraCamelCase("10Primeiros"));
    }

    @Test void specialChar(){
        assertThrows(SpecialCharException.class, () -> quebraCamelCase.quebraCamelCase("nome#Composto"));
    }
}
