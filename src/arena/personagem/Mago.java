package arena.personagem;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 85, 25, 3);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " lançou uma Bola de Fogo!");
        alvo.tomarDano(getAtaque() + 15);
    }
}
