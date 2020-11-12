
import java.util.Queue;


public class Keylistener implements Runnable {

    private Queue<Character> inputQueue = null;
    private ObjectDisplayGrid ODG = null;

    public Keylistener(ObjectDisplayGrid _ODG){
        ODG = _ODG;
        inputQueue = ODG.inputQueue;

    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(ODG.working){
            sleep(30);
            try {
                ODG.processInput();
            } catch (Exception e) {
            }
        }
    }
}
