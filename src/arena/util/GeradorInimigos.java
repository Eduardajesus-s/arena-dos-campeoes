package arena.util;

import arena.personagem.Inimigo;

import java.util.Random;

public class GeradorInimigos {
    private static final String[] NOMES = {
            "Zargor, o Orc", "Morgana, a Bruxa", "Drakon, o Dragão",
            "Vex, o Goblin", "Ragnar, o Bárbaro", "Nox, o Guardião"
    };

    private static final Random RANDOM = new Random();

    private GeradorInimigos() {
    }

    public static Inimigo criarInimigo(int numeroBatalha) {
        String nome = NOMES[RANDOM.nextInt(NOMES.length)];
        int vida = 55 + RANDOM.nextInt(21) + (numeroBatalha * 8);
        int ataque = 10 + RANDOM.nextInt(6) + numeroBatalha;
        int defesa = 2 + RANDOM.nextInt(4);

        return new Inimigo(nome, vida, ataque, defesa);
    }
}
