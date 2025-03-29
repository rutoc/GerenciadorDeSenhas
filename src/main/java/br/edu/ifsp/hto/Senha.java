package br.edu.ifsp.hto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Senha {
    private final int numero;
    private final String tipo;
    private final LocalDateTime horaChegada;
    private LocalDateTime horaAtendida;
    private int tentativas;
    private boolean atendida;
    private boolean descartada;

    public Senha(int numero, String tipo) {
        validarTipo(tipo);
        this.numero = numero;
        this.tipo = tipo;
        this.horaChegada = LocalDateTime.now();
        this.tentativas = 0;
        this.atendida = false;
        this.descartada = false;
    }

    private void validarTipo(String tipo) {
        if (!tipo.equals("normal") && !tipo.equals("preferencial")) {
            throw new IllegalArgumentException("Tipo de senha inválido: " + tipo);
        }
    }

    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public LocalDateTime getHoraChegada() { return horaChegada; }
    public LocalDateTime getHoraAtendida() { return horaAtendida; }
    public int getTentativas() { return tentativas; }
    public boolean isAtendida() { return atendida; }
    public boolean isDescartada() { return descartada; }

    public void incrementarTentativa() {
        if (atendida || descartada) {
            throw new IllegalStateException("Senha já finalizada");
        }
        tentativas++;
    }

    public void marcarComoAtendida() {
        if (descartada) {
            throw new IllegalStateException("Senha descartada não pode ser atendida");
        }
        this.atendida = true;
        this.horaAtendida = LocalDateTime.now();
    }

    public void marcarComoDescartada() {
        if (atendida) {
            throw new IllegalStateException("Senha atendida não pode ser descartada");
        }
        this.descartada = true;
    }

    public long calcularTempoEspera() {
        if (horaAtendida == null) {
            return ChronoUnit.MINUTES.between(horaChegada, LocalDateTime.now());
        }
        return ChronoUnit.MINUTES.between(horaChegada, horaAtendida);
    }

    @Override
    public String toString() {
        return String.format("%s%d (%s, tentativas: %d%s%s)",
                tipo.substring(0, 1).toUpperCase(),
                numero,
                tipo,
                tentativas,
                atendida ? ", ATENDIDA" : "",
                descartada ? ", DESCARTADA" : "");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Senha outraSenha = (Senha) obj;
        return numero == outraSenha.numero && tipo.equals(outraSenha.tipo);
    }

    @Override
    public int hashCode() {
        return 31 * numero + tipo.hashCode();
    }
}