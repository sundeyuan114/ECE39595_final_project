package game;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Player player;

    public KeyStrokePrinter(ObjectDisplayGrid grid, Player player1) {
        inputQueue = new ConcurrentLinkedQueue<>();
        player1.setODG(grid);
        displayGrid = grid;
        player = player1;
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private void rest() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean processInput() {        //Process user input
        char ch;

        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                if (ch == 'w' || ch == 'a' || ch == 's'|| ch == 'd'){
                    player.updateMove(ch);
                    //pop, move, push
                }
                else {
                    System.out.println("character " + ch + " entered on the keyboard");
                }
            }
        }
        return true;
    }


    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput());
        }
    }
}
