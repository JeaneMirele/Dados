import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class APP {
        public static void main(String[] args) throws Exception {

                Game game = new Game();
                Scanner sc = new Scanner(System.in);
                int escolha;

                do {
                        game.rankingTopFive("Vencedores.txt");
                        System.out.println("--JOGO DOS DADOS--");
                        game.verificaJogadores();
                        game.CriandoPlayer();

                        Dado dado1 = new Dado(6);
                        Dado dado2 = new Dado(6);

                        int resultado1 = dado1.jogarDados();
                        int resultado2 = dado2.jogarDados();

                        int soma = game.somarResultados(resultado1, resultado2);

                        System.out.println("Numero sorteado: " + soma);

                        game.Vencedor(soma);

                        System.out.println("Para jogar novamente digite 1, para sair 0");
                        escolha = sc.nextInt();
                        sc.nextLine();


                } while (escolha != 0);
        }

        public static void atualizarArquivoVencedores(String arquivo, Player vencedor) {
                File file = new File(arquivo);
                if (!file.exists()) {
                        try {
                                file.createNewFile();
                        } catch (IOException e) {
                                System.out.println("Erro ao criar o arquivo: " + e.getMessage());
                        }
                }

                Map<String, Integer> registros = new HashMap<>();


                try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                                String[] dados = linha.split(",");
                                if (dados.length == 2) {
                                        String nome = dados[0];
                                        int vitorias = Integer.parseInt(dados[1]);
                                        registros.put(nome, vitorias);
                                }

                        }
                } catch (IOException e) {
                        System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                }

                registros.put(vencedor.getNome(), registros.getOrDefault(vencedor.getNome(), 0) + 1);


                try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
                        for (Map.Entry<String, Integer> entry : registros.entrySet()) {
                                bw.write(entry.getKey() + "," + entry.getValue());
                                bw.newLine();
                        }
                } catch (IOException e) {
                        System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
                }

        }
}
