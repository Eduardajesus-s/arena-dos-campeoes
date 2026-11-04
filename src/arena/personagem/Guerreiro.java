package arena.personagem;

public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 120, 18, 8);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " usou Golpe Poderoso!");
        alvo.tomarDano(getAtaque() + 12);
    }
}
