package arena.ranking;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Ranking {
    private final Map<String, Integer> pontuacoes;

    public Ranking() {
        pontuacoes = new HashMap<>();
    }

    public void registrar(String nome, int pontos) {
        pontuacoes.merge(nome, pontos, Math::max);
    }

    public void exibir() {
        System.out.println("\n============== RANKING ==============");

        if (pontuacoes.isEmpty()) {
            System.out.println("Ainda não há pontuações registradas.");
            return;
        }

        pontuacoes.entrySet().stream()
                .sorted(Comparator.comparingInt(
                        (Map.Entry<String, Integer> entrada) -> entrada.getValue()
                ).reversed())
                .forEach(entrada -> System.out.println(
                        entrada.getKey() + " - " + entrada.getValue() + " pontos"
                ));
    }
}
