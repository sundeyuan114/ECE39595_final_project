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
//        boolean bpoll = false;


        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else if (player.getEndGameIndicator()){
                
            }
            else
            {
                player.updateIfInput();
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                if (ch == 'h' || ch == 'l' || ch == 'k'|| ch == 'j'){
                    if (player.onMonster(ch)){
                        boolean won = player.fight();
                        if (!won){
                            player.setEndGameIndicator(2);
                            break;
                        }
                    }
                    player.updateMove(ch);
                    //pop, move, push
                }
                else if (ch == 'e'){
                    player.setEndGameIndicator(1);
                }
                else if (ch == 'p'){
                    player.itemPick(ch);
                    player.packUpdate();
                }
                else if (ch == 'd'){
                    while (inputQueue.peek() == null){ }        //wait for second input
                    player.itemDrop(new Char(inputQueue.poll()));
                    player.packUpdate();
                }

//                else if (bpoll == true)
//                {
//
//                    if(!Character.isDigit(ch)) {
//                        System.out.println("This is not digit");
//                        //player.itemDrop(new Char(temp));
//                        bpoll = true;
//                    }else{
//                        System.out.println("This is digit");
//                        player.itemDrop(new Char(ch));
//                        bpoll = false;
//                    }
//                }


                else if (ch == 'i'){
                    //player.modifyInventory();
                    // we decide to auto update.
                }
                else if (ch == 'w'){
                    while (inputQueue.peek() == null){ };
                    player.chooseSetArmor(new Char(inputQueue.poll()));
                }
                else if (ch == 't'){
                    while (inputQueue.peek() == null){ };
                    player.chooseSetSword(new Char(inputQueue.poll()));
                }
                else if (ch == 'r'){
                    while (inputQueue.peek() == null){ };
                    player.tryReadScroll(new Char(inputQueue.poll()));
                }
                else if (ch == 'c'){
                    player.tryTakeOffArmor();
                }
                else if (ch == 'v'){
                    player.tryTakeOffSword();
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
