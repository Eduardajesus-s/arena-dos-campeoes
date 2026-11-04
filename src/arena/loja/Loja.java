package arena.loja;

import arena.personagem.Personagem;
import arena.util.Entrada;

public class Loja {
    private Loja() {
    }

    public static int abrir(Entrada entrada, Personagem heroi, int moedas) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n============== LOJA DA ARENA ==============");
            System.out.println("Moedas disponíveis: " + moedas);
            System.out.println("1 - Melhorar ataque (+3) - 30 moedas");
            System.out.println("2 - Melhorar vida (+20) - 30 moedas");
            System.out.println("3 - Recuperar 20 de vida - 15 moedas");
            System.out.println("0 - Sair da loja");

            int opcao = entrada.lerInteiro("Escolha: ", 0, 3);

            switch (opcao) {
                case 1 -> {
                    if (moedas >= 30) {
                        heroi.aumentarAtaque(3);
                        moedas -= 30;
                        System.out.println("Ataque melhorado!");
                    } else {
                        System.out.println("Moedas insuficientes.");
                    }
                }
                case 2 -> {
                    if (moedas >= 30) {
                        heroi.aumentarVidaMaxima(20);
                        moedas -= 30;
                        System.out.println("Melhoria de vida comprada!");
                    } else {
                        System.out.println("Moedas insuficientes.");
                    }
                }
                case 3 -> {
                    if (moedas >= 15) {
                        heroi.recuperarVida(20);
                        moedas -= 15;
                        System.out.println("Você recuperou vida.");
                    } else {
                        System.out.println("Moedas insuficientes.");
                    }
                }
                case 0 -> continuar = false;
                default -> System.out.println("Opção inválida.");
            }
        }

        return moedas;
    }
}
