package arena;

import arena.batalha.Batalha;
import arena.personagem.Arqueiro;
import arena.personagem.Guerreiro;
import arena.personagem.Mago;
import arena.personagem.Personagem;
import arena.ranking.Ranking;
import arena.util.Entrada;
import arena.util.GeradorInimigos;


public class Main {
    private static final int TOTAL_BATALHAS = 5;

    public static void main(String[] args) {
        Entrada entrada = new Entrada();
        Ranking ranking = new Ranking();
        boolean executando = true;

        exibirTitulo();

        while (executando) {
            System.out.println("\n1 - Começar uma aventura");
            System.out.println("2 - Ver ranking");
            System.out.println("0 - Sair");

            int opcao = entrada.lerInteiro("Escolha: ", 0, 2);

            switch (opcao) {
                case 1 -> iniciarJogo(entrada, ranking);
                case 2 -> ranking.exibir();
                case 0 -> {
                    executando = false;
                    System.out.println("Até a próxima, campeão!");
                }
                default -> System.out.println("Opção inválida.");
            }
        }

        entrada.fechar();
    }

    private static void iniciarJogo(Entrada entrada, Ranking ranking) {
        System.out.println("\n=== CRIAÇÃO DO HERÓI ===");
        String nome = entrada.lerTexto("Digite o nome do seu herói: ");
        Personagem heroi = escolherPersonagem(entrada, nome);

        int pontuacao = 0;
        int moedas = 0;
        int batalhasVencidas = 0;

        System.out.println("\nPrepare-se, " + heroi.getNome() + "! Sua aventura começa agora.");

        for (int numeroBatalha = 1;
             numeroBatalha <= TOTAL_BATALHAS && heroi.estaVivo();
             numeroBatalha++) {

            Personagem inimigo = GeradorInimigos.criarInimigo(numeroBatalha);
            System.out.println("\n========================================");
            System.out.println("BATALHA " + numeroBatalha + " DE " + TOTAL_BATALHAS);
            System.out.println("Um inimigo apareceu: " + inimigo.getNome());
            System.out.println("========================================");

            Batalha batalha = new Batalha(entrada, heroi, inimigo);
            boolean venceu = batalha.iniciar();

            if (!venceu) {
                break;
            }

            batalhasVencidas++;
            pontuacao += 10;
            moedas += 20;

            System.out.println("\nVitória! Você recebeu 10 pontos e 20 moedas.");
            System.out.println("Pontuação: " + pontuacao + " | Moedas: " + moedas);

            if (numeroBatalha < TOTAL_BATALHAS && heroi.estaVivo()) {
                System.out.println("\nVocê terá acesso à loja antes da próxima batalha.");
                moedas = arena.loja.Loja.abrir(entrada, heroi, moedas);
            }
        }

        fimDeJogo(heroi, pontuacao, moedas, batalhasVencidas, ranking);
    }

    private static void fimDeJogo(Personagem heroi, int pontuacao, int moedas,
                                  int batalhasVencidas, Ranking ranking) {
        System.out.println("\n============== FIM DA AVENTURA ==============");
        System.out.println("Herói: " + heroi.getNome());
        System.out.println("Batalhas vencidas: " + batalhasVencidas + "/" + TOTAL_BATALHAS);
        System.out.println("Vida restante: " + heroi.getVida() + "/" + heroi.getVidaMaxima());
        System.out.println("Pontuação final: " + pontuacao);
        System.out.println("Moedas restantes: " + moedas);

        if (heroi.estaVivo() && batalhasVencidas == TOTAL_BATALHAS) {
            System.out.println("\nPARABÉNS! Você venceu todas as batalhas e se tornou um campeão!");
        } else {
            System.out.println("\nSua jornada terminou. Continue treinando para vencer a arena!");
        }

        ranking.registrar(heroi.getNome(), pontuacao);
        ranking.exibir();
    }

    private static void exibirTitulo() {
        System.out.println("""
                
                ========================================
                       ARENA DOS CAMPEÕES
                         BATALHA EM TURNOS
                ========================================
                """);
    }

    private static Personagem escolherPersonagem(Entrada entrada, String nome) {
        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro: muita vida e defesa. Especial: Golpe Poderoso.");
        System.out.println("2 - Mago: ataque forte, mas menos vida. Especial: Bola de Fogo.");
        System.out.println("3 - Arqueiro: equilibrado e preciso. Especial: Flecha Precisa.");

        int escolha = entrada.lerInteiro("Classe: ", 1, 3);

        return switch (escolha) {
            case 1 -> new Guerreiro(nome);
            case 2 -> new Mago(nome);
            default -> new Arqueiro(nome);
        };
    }

}
