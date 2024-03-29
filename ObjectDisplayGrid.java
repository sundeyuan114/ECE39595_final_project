package game;
import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.lang.*;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    private Stack<Char>[][] objectGrid = null;

    private List<InputObserver> inputObservers = null;

    private static int height;
    private static int width;

    @SuppressWarnings("unchecked")
    public ObjectDisplayGrid(int _width, int _height) {
        width = _width;
        height = _height;
        System.out.println("Calling from ObjectDisplayGrid constructor width = "+width +"height = "+height);
        terminal = new AsciiPanel(width, height);
        objectGrid =  new Stack[width][height];
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                objectGrid[i][j] = new Stack<Char>();
            }
        }

        //initializeDisplay();

        super.add(terminal);
        super.setSize(width * 9, height * 16);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }
    public void addSentenceToDisplay (String str, int x, int y){
        for(int i = 0; i < str.length(); i++){
            char temp = str.charAt(i);
            this.addObjectToDisplay(new Char(temp),x+i,y);
        }
    }
    // picks up item
    public Char getStandingBlock(int x, int y){
        Char temp = null;
        temp = objectGrid[x][y].pop();
        Char ret = objectGrid[x][y].pop();
        objectGrid[x][y].push(temp);
        return ret;
    }
    // drop item
    public void dropOnBlock(Char item, int x,int y){
        Char temp = null;
        temp = objectGrid[x][y].pop();
        if (temp.getChar() != '@'){
            System.out.println("Bug in drop on block");
        }
        objectGrid[x][y].push(item);
        objectGrid[x][y].push(temp);
    }
    public List<Integer> getRandomAccessiblePos (){
        System.out.println("Entered getRandomAccessiblePos");
        Random rand = new Random(); //instance of random class
        int upperbound = 1;        
        //Char dian = new Char('.');
        //Char jiahao = new Char('+');
        //Char jinghao = new Char('#');
        ArrayList<Integer> posX = new ArrayList<> ();
        ArrayList<Integer> posY = new ArrayList<> ();
        //int int_random = rand.nextInt(upperbound); 
        System.out.println("getwidth+getheight "+objectGrid.length + "  " + objectGrid[0].length);

        for (int i = 0; i< objectGrid.length; i++){
            for (int j = 0; j < objectGrid[0].length; j++){
                System.out.println(objectGrid[i][j].peek().getChar());
                if (objectGrid[i][j].peek().getChar() == '.' 
                || objectGrid[i][j].peek().getChar() == '+'
                || objectGrid[i][j].peek().getChar() == '#' ) {
                    upperbound ++;
                    posX.add(i);
                    posY.add(j);
                    System.out.println("case FOUND");
                }
            }
        }
        System.out.println(upperbound);
        int int_random = rand.nextInt(upperbound); 
        List <Integer> ret = new ArrayList<> ();
        ret.add(posX.get(int_random));
        ret.add(posY.get(int_random));

        return ret;
    }


    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        Char ch = new Char(' ');
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }
        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Char ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y].push(ch);
                writeToTerminal(x, y);
            }
        }
    }

    //pop from x,y and display next on stack writeToTerminal(x, y);
    public void removeObjectToDisplay(int x, int y){
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
//                System.out.println(objectGrid[x][y].size());
//                System.out.println("(" + objectGrid[x][y].peek().getChar() + ")");
                objectGrid[x][y].pop();

                writeToTerminal(x, y);
            }
        }
    }

    public void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].peek().getChar();   //just add a peek in between :D
        terminal.write(ch, x, y);
        terminal.repaint();
    }


    public void setTopMessageHeight(int topHeight){
        System.out.println("Setting top message height to be "+topHeight);
    }

//    public void receiveChar(Char ch){
//        addObjectToDisplay(ch.getChar(),,);
//    }

}
