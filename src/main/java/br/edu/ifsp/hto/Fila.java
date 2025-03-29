package br.edu.ifsp.hto;

public class Fila {
    private final ListaLigada fila;

    public Fila() {
        this.fila = new ListaLigada();
    }

    public void adicionar(Senha senha) {
        if (senha == null) {
            throw new IllegalArgumentException("Senha não pode ser nula");
        }
        fila.adicionarNoFinal(senha);
    }

    public Senha remover() {
        if (estaVazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        return fila.removerNoInicio();
    }

    public boolean contem(Senha senha) {
        return fila.contem(senha);
    }

    public int tamanho() {
        return fila.tamanho();
    }

    public boolean estaVazia() {
        return fila.estaVazia();
    }

    public Senha verPrimeiro() {
        if (estaVazia()) {
            throw new IllegalStateException("Fila vazia");
        }
        return fila.pegarPrimeiro();
    }

    @Override
    public String toString() {
        return fila.toString();
    }
}