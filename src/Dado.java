import java.util.Random;

public class Dado {
    private int faces;

    public Dado(int faces){
        this.faces = faces;
    }

    public int getFaces() {
        return faces;
    }

    public void setFaces(int faces) {
        this.faces = faces;
    }

    public int jogarDados(){
        Random random = new Random();
        return random.nextInt(6)+1;
    }

}
