package arena.personagem;

import java.util.ArrayList;
import java.util.List;

public abstract class Personagem {
    private final String nome;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defesa;
    private boolean defendendo;
    private final List<String> inventario;

    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.inventario = new ArrayList<>();
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
    }

    public void atacar(Personagem alvo) {
        alvo.tomarDano(ataque);
    }

    public void defender() {
        defendendo = true;
    }

    public void tomarDano(int dano) {
        int danoRecebido = Math.max(1, dano - defesa);

        if (defendendo) {
            danoRecebido = Math.max(1, danoRecebido / 2);
            defendendo = false;
        }

        vida = Math.max(0, vida - danoRecebido);
    }

    public boolean usarPocao() {
        if (inventario.isEmpty() || vida == vidaMaxima) {
            return false;
        }

        inventario.remove(0);
        vida = Math.min(vidaMaxima, vida + 20);
        return true;
    }

    public abstract void habilidadeEspecial(Personagem alvo);

    public void aumentarAtaque(int valor) {
        ataque += valor;
    }

    public void aumentarVidaMaxima(int valor) {
        vidaMaxima += valor;
        vida += valor;
    }

    public void recuperarVida(int valor) {
        vida = Math.min(vidaMaxima, vida + valor);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getPocoes() { return inventario.size(); }
}
