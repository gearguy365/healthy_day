package hackathon;

/**
 *
 * @author Naushad
 * 
 */
public class MainGame {
    public static void main(String[] args) {
        GameWindow g = new GameWindow();
        Thread t = new Thread(g);
        t.start();
    }
}
