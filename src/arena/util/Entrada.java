package arena.util;

import java.util.Scanner;

public class Entrada {
    private final Scanner scanner;

    public Entrada() {
        scanner = new Scanner(System.in);
    }

    public String lerTexto(String mensagem) {
        String texto;

        do {
            System.out.print(mensagem);
            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("O texto não pode ficar vazio.");
            }
        } while (texto.isEmpty());

        return texto;
    }

    public int lerInteiro(String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();

            try {
                int numero = Integer.parseInt(valor);

                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }

                System.out.println("Digite um número entre " + minimo + " e " + maximo + ".");
            } catch (NumberFormatException erro) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }

    public void fechar() {
        scanner.close();
    }
}
