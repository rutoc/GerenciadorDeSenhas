package br.edu.ifsp.hto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FilaTest {

    @Test
    void adicionarERemover_deveManterOrdemFIFO() {
        Fila fila = new Fila();
        Senha senha1 = new Senha(1, "normal");
        Senha senha2 = new Senha(2, "normal");

        fila.adicionar(senha1);
        fila.adicionar(senha2);

        assertEquals(senha1, fila.remover());
        assertEquals(senha2, fila.remover());
    }

    @Test
    void estaVazia_filaVazia_deveRetornarTrue() {
        Fila fila = new Fila();
        assertTrue(fila.estaVazia());
    }

    @Test
    void estaVazia_filaComElementos_deveRetornarFalse() {
        Fila fila = new Fila();
        fila.adicionar(new Senha(1, "normal"));
        assertFalse(fila.estaVazia());
    }

    @Test
    void verPrimeiro_deveRetornarPrimeiroSemRemover() {
        Fila fila = new Fila();
        Senha senha = new Senha(1, "normal");
        fila.adicionar(senha);

        assertEquals(senha, fila.verPrimeiro());
        assertEquals(1, fila.tamanho());
    }

    @Test
    void contem_senhaExistente_deveRetornarTrue() {
        Fila fila = new Fila();
        Senha senha = new Senha(1, "normal");
        fila.adicionar(senha);

        assertTrue(fila.contem(senha));
    }

    @Test
    void remover_filaVazia_deveLancarExcecao() {
        Fila fila = new Fila();
        assertThrows(IllegalStateException.class, () -> {
            fila.remover();
        });
    }

    @Test
    void adicionar_senhaNula_deveLancarExcecao() {
        Fila fila = new Fila();
        assertThrows(IllegalArgumentException.class, () -> {
            fila.adicionar(null);
        });
    }
}