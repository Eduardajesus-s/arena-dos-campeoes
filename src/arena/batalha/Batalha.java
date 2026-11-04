package arena.batalha;

import arena.personagem.Personagem;
import arena.util.Entrada;

import java.util.Random;

public class Batalha {
    private final Entrada entrada;
    private final Personagem heroi;
    private final Personagem inimigo;
    private final Random random;

    public Batalha(Entrada entrada, Personagem heroi, Personagem inimigo) {
        this.entrada = entrada;
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.random = new Random();
    }

    public boolean iniciar() {
        int turno = 1;

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n--------------- TURNO " + turno + " ---------------");
            exibirStatus();

            turnoDoJogador();
            if (!inimigo.estaVivo()) {
                break;
            }

            turnoDoInimigo();
            turno++;
        }

        if (heroi.estaVivo()) {
            System.out.println("\n" + inimigo.getNome() + " foi derrotado!");
            return true;
        }

        System.out.println("\n" + heroi.getNome() + " foi derrotado por " + inimigo.getNome() + ".");
        return false;
    }

    private void exibirStatus() {
        System.out.println(heroi.getNome() + " - Vida: " + heroi.getVida()
                + "/" + heroi.getVidaMaxima() + " | Poções: " + heroi.getPocoes());
        System.out.println(inimigo.getNome() + " - Vida: " + inimigo.getVida()
                + "/" + inimigo.getVidaMaxima());
    }

    private void turnoDoJogador() {
        boolean acaoConcluida = false;

        while (!acaoConcluida) {
            System.out.println("\nEscolha sua ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Defender");
            System.out.println("3 - Usar poção (" + heroi.getPocoes() + " restantes)");
            System.out.println("4 - Habilidade especial");

            int opcao = entrada.lerInteiro("Ação: ", 1, 4);

            switch (opcao) {
                case 1 -> {
                    heroi.atacar(inimigo);
                    System.out.println("Você atacou " + inimigo.getNome() + ".");
                    acaoConcluida = true;
                }
                case 2 -> {
                    heroi.defender();
                    System.out.println("Você se preparou para receber menos dano no próximo ataque.");
                    acaoConcluida = true;
                }
                case 3 -> {
                    if (heroi.usarPocao()) {
                        System.out.println("Você recuperou até 20 pontos de vida.");
                        acaoConcluida = true;
                    } else {
                        System.out.println("Não foi possível usar a poção. Talvez não tenha mais poções ou sua vida esteja cheia.");
                    }
                }
                case 4 -> {
                    heroi.habilidadeEspecial(inimigo);
                    acaoConcluida = true;
                }
                default -> System.out.println("Ação inválida.");
            }
        }
    }

    private void turnoDoInimigo() {
        int acao = random.nextInt(100);

        if (acao < 75) {
            inimigo.atacar(heroi);
            System.out.println(inimigo.getNome() + " atacou você!");
        } else {
            inimigo.defender();
            System.out.println(inimigo.getNome() + " ficou na defensiva.");
        }
    }
}
