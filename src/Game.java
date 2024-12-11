import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Game {
    private int qtdJogadores;
    private List<Player> players = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public Game(){}

    public Game(int qtdJogadores){
    this.qtdJogadores = qtdJogadores;
    }

    public int getQtdJogadores() {
        return qtdJogadores;
    }

    public void setQtdJogadores(int qtdJogadores) {
        this.qtdJogadores = qtdJogadores;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void verificaJogadores(){
        do{
            System.out.println("Quantos jogadores irão apostar?");
            setQtdJogadores(sc.nextInt());
            sc.nextLine();

            if(qtdJogadores < 1 || qtdJogadores > 11) {
                System.out.println("Número de jogadores inválido, máx 11, min 1, digite novamente:");
            }

        }while(qtdJogadores < 1 || qtdJogadores > 11);
    }

    public List<Player> CriandoPlayer() {

        HashSet<Integer> numeroDuplicado = new HashSet<>();

        for (int i = 1; i <= qtdJogadores; i++) {

            Player player = new Player();
            System.out.println("Jogador " + i + "- " + "Nome: ");
            player.setNome(sc.nextLine());

            int numero;
            do{
                System.out.println("Jogador " + i + "- " + "Valor: ");
                numero = sc.nextInt();
                sc.nextLine();

                if (numero < 1 || numero > 12) {
                    System.out.println("Número fora do intervalo! Escolha um valor entre 1 e 12.");

                }else if(numeroDuplicado.contains(numero)){
                    System.out.println("Esse valor já foi escolhido, escolha outro!");
                }
            }while(numero < 1 || numero > 12 || numeroDuplicado.contains(numero));

            player.setNumero(numero);
            numeroDuplicado.add(numero);
            players.add(player);
        }
        return players;
    }

    public int somarResultados(int resultado1, int resultado2){
        return resultado1+resultado2;
    }

    public Player Vencedor(int soma) {
        Player vencedor = null;

        for (Player player : players) {
            if (player.getNumero() == soma) {
                player.setnVitoria(player.getnVitoria() + 1);
                vencedor = player;
            }
        }

        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor);
            APP.atualizarArquivoVencedores("Vencedores.txt", vencedor);
            return vencedor;
        } else {
            Player maquina = new Player("Máquina", -1, 1);
            System.out.println("A máquina venceu!");
            APP.atualizarArquivoVencedores("Vencedores.txt", maquina);
            return maquina;
        }
    }

    public void rankingTopFive(String arquivo){
        System.out.println("RANKING");

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


        registros.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .forEach(entry -> {
                    System.out.println(entry.getKey() + " - Vitórias: " + entry.getValue());
                });

        }
}
