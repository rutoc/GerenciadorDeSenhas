package br.edu.ifsp.hto;

public class ListaLigada {
    private No cabeca;
    private No cauda;
    private int tamanho;

    public ListaLigada() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionarNoInicio(Senha dado) {
        No noNovo = new No(dado);
        if (cabeca == null) {
            cabeca = noNovo;
            cauda = noNovo;
        } else {
            noNovo.proximo = cabeca;
            cabeca.anterior = noNovo;
            cabeca = noNovo;
        }
        tamanho++;
    }

    public void adicionarNoFinal(Senha dado) {
        No noNovo = new No(dado);
        if (cabeca == null) {
            cabeca = noNovo;
            cauda = noNovo;
        } else {
            noNovo.anterior = cauda;
            cauda.proximo = noNovo;
            cauda = noNovo;
        }
        tamanho++;
    }

    public Senha removerNoInicio() {
        if (cabeca == null) {
            return null;
        }

        Senha dadoRemovido = cabeca.dado;
        if (cabeca == cauda) {
            cabeca = null;
            cauda = null;
        } else {
            cabeca = cabeca.proximo;
            cabeca.anterior = null;
        }
        tamanho--;
        return dadoRemovido;
    }

    public Senha removerNoFinal() {
        if (cabeca == null) {
            return null;
        }

        Senha dadoRemovido = cauda.dado;
        if (cabeca == cauda) {
            cabeca = null;
            cauda = null;
        } else {
            cauda = cauda.anterior;
            cauda.proximo = null;
        }
        tamanho--;
        return dadoRemovido;
    }

    public boolean contem(Senha dadoBuscado) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.dado.equals(dadoBuscado)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public int tamanho() {
        return this.tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        No atual = cabeca;
        while (atual != null) {
            sb.append(atual.dado.toString());
            if (atual.proximo != null) {
                sb.append("\n");
            }
            atual = atual.proximo;
        }
        return sb.toString();
    }

    public Senha pegarPrimeiro() {
        if (cabeca == null) {
            return null;
        }
        return cabeca.dado;
    }
}