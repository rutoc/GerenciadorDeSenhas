package br.edu.ifsp.hto;

import java.util.Scanner;

public class GerenciadorSenhas {
    private Fila filaNormal;
    private Fila filaPreferencial;
    private Fila senhasAtendidas;
    private Fila senhasDescartadas;
    private int contadorSenhas;
    private Scanner scanner;
    private int senhasNormaisAtendidas;
    private int senhasPreferenciaisAtendidas;

    public GerenciadorSenhas() {
        this.filaNormal = new Fila();
        this.filaPreferencial = new Fila();
        this.senhasAtendidas = new Fila();
        this.senhasDescartadas = new Fila();
        this.contadorSenhas = 0;
        this.scanner = new Scanner(System.in);
        this.senhasNormaisAtendidas = 0;
        this.senhasPreferenciaisAtendidas = 0;
    }

    public void iniciar() {
        System.out.println("=== Sistema de Gerenciamento de Senhas ===");

        int opcao;
        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        gerarSenhaNormal();
                        break;
                    case 2:
                        gerarSenhaPreferencial();
                        break;
                    case 3:
                        chamarSenha();
                        break;
                    case 4:
                        listarSenhasNaFila();
                        break;
                    case 5:
                        gerarRelatorio();
                        break;
                    case 6:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção de 1 a 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                opcao = 0;
            }
        } while (opcao != 6);
    }

    private void exibirMenu() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Gerar Senha Normal");
        System.out.println("2. Gerar Senha Preferencial");
        System.out.println("3. Chamar Senha");
        System.out.println("4. Listar Senhas na Fila");
        System.out.println("5. Gerar Relatório");
        System.out.println("6. Sair do Programa");
        System.out.print("Escolha uma opção: ");
    }

    private void gerarSenhaNormal() {
        contadorSenhas++;
        Senha novaSenha = new Senha(contadorSenhas, "normal");
        filaNormal.adicionar(novaSenha);
        System.out.println("\nSenha gerada: " + novaSenha);
    }

    private void gerarSenhaPreferencial() {
        contadorSenhas++;
        Senha novaSenha = new Senha(contadorSenhas, "preferencial");
        filaPreferencial.adicionar(novaSenha);
        System.out.println("\nSenha gerada: " + novaSenha);
    }

    private void chamarSenha() {
        if (!filaPreferencial.estaVazia()) {
            chamarProximaSenha(filaPreferencial, true);
        } else if (!filaNormal.estaVazia()) {
            chamarProximaSenha(filaNormal, false);
        } else {
            System.out.println("\nNão há senhas na fila para chamar.");
        }
    }

    private void chamarProximaSenha(Fila fila, boolean isPreferencial) {
        Senha senha = fila.verPrimeiro();
        System.out.println("\nChamando senha: " + senha);

        System.out.print("A senha foi atendida? (S/N): ");
        String resposta = scanner.nextLine().toUpperCase();

        while (!resposta.equals("S") && !resposta.equals("N")) {
            System.out.print("Resposta inválida! Digite S para Sim ou N para Não: ");
            resposta = scanner.nextLine().toUpperCase();
        }

        if (resposta.equals("S")) {
            senha.marcarComoAtendida();
            senhasAtendidas.adicionar(senha);
            fila.remover();

            if (isPreferencial) {
                senhasPreferenciaisAtendidas++;
            } else {
                senhasNormaisAtendidas++;
            }

            System.out.println("Senha atendida com sucesso.");
        } else {
            senha.incrementarTentativa();
            System.out.println("Senha não atendida. Tentativa " + senha.getTentativas() + "/3");

            if (senha.getTentativas() >= 3) {
                senha.marcarComoDescartada();
                senhasDescartadas.adicionar(senha);
                fila.remover();
                System.out.println("Senha descartada após 3 tentativas não atendidas.");
            } else {
                fila.remover();
                if (isPreferencial) {
                    filaPreferencial.adicionar(senha);
                } else {
                    filaNormal.adicionar(senha);
                }
                System.out.println("Senha recolocada no final da fila.");
            }
        }
    }

    private void listarSenhasNaFila() {
        System.out.println("\n=== Senhas na Fila ===");

        if (filaPreferencial.estaVazia() && filaNormal.estaVazia()) {
            System.out.println("Não há senhas na fila no momento.");
            return;
        }

        if (!filaPreferencial.estaVazia()) {
            System.out.println("\nSenhas Preferenciais:");
            System.out.println(filaPreferencial.toString());
        }

        if (!filaNormal.estaVazia()) {
            System.out.println("\nSenhas Normais:");
            System.out.println(filaNormal.toString());
        }
    }

    private void gerarRelatorio() {
        System.out.println("\n=== Relatório de Atendimento ===");

        int totalGeradas = contadorSenhas;
        int totalAtendidas = senhasAtendidas.tamanho();
        int totalDescartadas = senhasDescartadas.tamanho();
        int totalPendentes = filaNormal.tamanho() + filaPreferencial.tamanho();

        System.out.println("\nEstatísticas Gerais:");
        System.out.println("Total de senhas geradas: " + totalGeradas);
        System.out.println("Total de senhas atendidas: " + totalAtendidas);
        System.out.println("Total de senhas descartadas: " + totalDescartadas);
        System.out.println("Total de senhas pendentes: " + totalPendentes);

        if (totalAtendidas > 0) {
            long tempoTotal = calcularTempoTotalEspera();
            System.out.println("\nTempo médio de espera: " + (tempoTotal/totalAtendidas) + " minutos");
        }

        if (totalAtendidas > 0) {
            System.out.println("\nDistribuição de Atendimentos:");
            System.out.printf("Senhas preferenciais atendidas: %d (%.1f%%)\n",
                    senhasPreferenciaisAtendidas,
                    (senhasPreferenciaisAtendidas * 100.0 / totalAtendidas));
            System.out.printf("Senhas normais atendidas: %d (%.1f%%)\n",
                    senhasNormaisAtendidas,
                    (senhasNormaisAtendidas * 100.0 / totalAtendidas));
        }

        if (totalDescartadas > 0) {
            System.out.println("\nSenhas descartadas (não atendidas após 3 chamadas):");
            System.out.println(senhasDescartadas.toString());
        }
    }

    private long calcularTempoTotalEspera() {
        long tempoTotal = 0;
        Fila temp = new Fila();

        while (!senhasAtendidas.estaVazia()) {
            Senha s = senhasAtendidas.remover();
            tempoTotal += s.calcularTempoEspera();
            temp.adicionar(s);
        }

        while (!temp.estaVazia()) {
            senhasAtendidas.adicionar(temp.remover());
        }

        return tempoTotal;
    }
}