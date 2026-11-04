package arena.personagem;

public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome, 100, 21, 5);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        System.out.println(getNome() + " disparou uma Flecha Precisa!");
        alvo.tomarDano(getAtaque() + 10);
    }
}
