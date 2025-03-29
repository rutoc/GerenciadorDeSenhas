package br.edu.ifsp.hto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SenhaTest {

    @Test
    void criarSenha_normal_deveInicializarCorretamente() {
        Senha senha = new Senha(1, "normal");

        assertEquals(1, senha.getNumero());
        assertEquals("normal", senha.getTipo());
        assertEquals(0, senha.getTentativas());
        assertFalse(senha.isAtendida());
        assertFalse(senha.isDescartada());
        assertNotNull(senha.getHoraChegada());
        assertNull(senha.getHoraAtendida());
    }

    @Test
    void incrementarTentativa_deveAumentarContador() {
        Senha senha = new Senha(1, "normal");
        senha.incrementarTentativa();
        assertEquals(1, senha.getTentativas());
    }

    @Test
    void marcarComoAtendida_deveMarcarEdefinirHoraAtendimento() {
        Senha senha = new Senha(1, "normal");
        senha.marcarComoAtendida();

        assertTrue(senha.isAtendida());
        assertNotNull(senha.getHoraAtendida());
    }

    @Test
    void marcarComoDescartada_deveMarcarCorretamente() {
        Senha senha = new Senha(1, "normal");
        senha.marcarComoDescartada();

        assertTrue(senha.isDescartada());
    }

    @Test
    void criarSenha_tipoInvalido_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Senha(1, "invalido");
        });
    }

    @Test
    void incrementarTentativa_senhaAtendida_deveLancarExcecao() {
        Senha senha = new Senha(1, "normal");
        senha.marcarComoAtendida();

        assertThrows(IllegalStateException.class, () -> {
            senha.incrementarTentativa();
        });
    }
}