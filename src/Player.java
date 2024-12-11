
public class Player {
    private String nome;
    private int numero;
    private int nVitoria;

    public Player(){
    }

    public Player(String nome, int numero, int nVitoria){
        this.nome = nome;
        this.numero = numero;
        this.nVitoria = nVitoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getnVitoria() {
        return nVitoria;
    }

    public void setnVitoria(int nVitoria) {
        this.nVitoria = nVitoria;
    }

    @Override
    public String toString(){
        return nome +
                "," + nVitoria;
    }
}


