package arena.personagem;

public class Inimigo extends Personagem {
    public Inimigo(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        atacar(alvo);
    }
}
