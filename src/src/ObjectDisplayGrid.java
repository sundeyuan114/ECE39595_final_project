
import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject, Runnable{

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    private Char[][] objectGrid = null;

    private List<Displayable> inputObservers = null;
    private ArrayList<Room> Rooms = new ArrayList<Room>();
    private ArrayList<Passage> Passages = new ArrayList<Passage>();
    public Queue<Character> inputQueue = new ConcurrentLinkedQueue<Character>();

    private static int gameHeight;
    private static int width;
    private static int topHeight;
    private static int bottomHeight;
    private static int height;

    private Player player;

    public boolean working = true;
    boolean player_dead = false;

    public ObjectDisplayGrid(int _width,int _topHeight,int _gameHeight, int _bottomheight) {
        width = _width;
        gameHeight = _gameHeight;
        topHeight = _topHeight;
        bottomHeight = _bottomheight;
        height = gameHeight + topHeight + bottomHeight;

        terminal = new AsciiPanel(width, height);

        objectGrid = new Char[width][height];

        initializeDisplay();

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

    public Player getPlayer(){ return player;}

    public int getHeight(){
        return topHeight + gameHeight + bottomHeight;
    }

    public int getWidth(){
        return width;
    }

    public void setTopMessageHeight(int _topHeight) {
        topHeight = _topHeight;
    }

    @Override
    public void registerInputObserver(Displayable observer) {//keystrokeprinter
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
        KeyEvent keypress = e;
        char ch = keypress.getKeyChar();
        inputQueue.add(ch);
        //notifyPlayer(keypress.getKeyChar());

    }

    void notifyPlayer(char ch) {//observer is player
        int x = player.getX().get(0);
        int y = player.getY().get(0) + topHeight;


        try{
            if(ch == 'h' && objectGrid[x-1][y].getChar() == 'X'){
                return;
            }else if (ch == 'l' && objectGrid[x+1][y].getChar() == 'X'){
                return;
            }else if(ch == 'j' && objectGrid[x][y-1].getChar() == 'X') {
                return;
            }else if(ch =='k' && objectGrid[x][y+1].getChar() == 'X') {
                return;
            }
        }catch (NullPointerException e){
            return;
        }




        System.out.println("player move");
        player.playerUpdate(ch);
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //without player, this detech all displayable to check for overlapping with player
    //if overlap, then check for interact
    void notifyObservers(int x, int y) throws Exception{
        String message;
        Boolean isDead;
        for(Displayable disp:inputObservers){
            boolean meet = disp.observerUpdate(x,y);
            if(meet){
                if(disp.getCLASSID().equals("Monster")){
                    //boolean isDead = false;
                    int player_maxhit = player.getMaxhit();
                    int monster_maxhit = disp.getMaxhit();
                    while(true){
                        int player_hit = ThreadLocalRandom.current().nextInt(0, player_maxhit + 1);
                        int monster_hit = ThreadLocalRandom.current().nextInt(0, monster_maxhit + 1);
                        isDead = disp.setHp(disp.getHp() - player_hit); // player hit monster
                        message = "You did " + player_hit + " damage to enemy";
                        bottomMessageSet(message);
                        sleep(500);
                        if(isDead){
                            message = "You defeated the enemy";
                            bottomMessageSet(message);
                            inputObservers.remove(disp);
                            break;
                        }
                        isDead = player.setHp(player.getHp() - monster_hit);
                        message = "Enemy did " + monster_hit + " damage to you";
                        TopMessageSet();
                        bottomMessageSet(message);
                        sleep(500);
                        if(isDead){
                            message ="You are dead, Game Over";
                            bottomMessageSet(message);
                            System.out.println(message);
                            player_dead = true;
                            break;
                        }
                    }
                }
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

    public final void initializeDisplay() {//for debug

        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void add(Displayable disp){
        ArrayList<Integer> posX = disp.getX();
        ArrayList<Integer> posY = disp.getY();
        String class_type = disp.getCLASSID();
        System.out.println(disp.getRep_char());
        if(class_type.equals("Room")){
            width = disp.getWidth();
            height = disp.getHeight();
            Rooms.add((Room) disp);
            //System.out.println(width);
            //System.out.println(height);
            int x = posX.get(0);
            int y = posY.get(0);
            y = y + topHeight;
            Char ch = new Char('X');
            for(int i = x; i<= x+width-1;i++){
                for(int j = y; j <= y+height-1; j++){
                    addObjectToDisplay(new Char('.'), i, j);
                }
            }
            for(int i = x; i <= x+width-1; i++){
                addObjectToDisplay(ch,i,y);
                addObjectToDisplay(ch,i,y+height-1);
            }
            for(int i = y; i <= y+height-1; i++){
                addObjectToDisplay(ch,x,i);
                addObjectToDisplay(ch,x+width-1,i);
            }
        }else if(class_type.equals("Passage")){
            int size = posX.size();
            Passages.add((Passage) disp);
            int new_X, new_Y;
            int old_X = 0, old_Y = 0;
            for(int i = 0; i < size; i++){
                new_X = posX.get(i);
                new_Y = posY.get(i);
                new_Y += topHeight;

                if(i != 0){
                    int k = Math.min(new_X, old_X);//smaller
                    int j = Math.max(new_X, old_X);//larger
                    for(;k<=j;k++){
                        addObjectToDisplay(new Char('#'),k,old_Y);
                    }
                    k = Math.min(new_Y, old_Y);//smaller
                    j = Math.max(new_Y, old_Y);//larger
                    for(;k<=j;k++){
                        addObjectToDisplay(new Char('#'),new_X,k);
                    }
                }
                old_X = new_X;
                old_Y = new_Y;
            }
            addObjectToDisplay(new Char('+'),posX.get(0),posY.get(0)+topHeight);
            addObjectToDisplay(new Char('+'),posX.get(size-1),posY.get(size-1)+topHeight);
        }else if(class_type.equals("Player")){
            player = (Player) disp;
            //inputObservers.add(disp);
            /*if(inputObservers.isEmpty()){
                inputObservers.add((Player) disp);
            }*/
            char c = disp.getRep_char();
            addObjectToDisplay(new Char(c), posX.get(0), posY.get(0) + topHeight);
        }
        else{
            inputObservers.add(disp);
            char c = disp.getRep_char();
            if(posX.size()>0){
                addObjectToDisplay(new Char(c), posX.get(0), posY.get(0) + topHeight);
                //System.out.println(posX.get(0));
                //System.out.println(posY.get(0));
            }

        }
    }

    public void addObjectToDisplay(Char ch, int x, int y) {
        //System.out.println(objectGrid[0].length);
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }

    private void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].getChar();
        terminal.write(ch, x, y);
        //terminal.repaint();
    }

    public void refresh(){
        for(Room room:Rooms) {//refresh room
            width = room.getWidth();
            height = room.getHeight();
            //System.out.println(width);
            //System.out.println(height);
            int x = room.getX().get(0);
            int y = room.getY().get(0);
            y +=  topHeight;
            Char ch = new Char('X');
            for (int i = x; i <= x + width - 1; i++) {
                for (int j = y; j <= y + height - 1; j++) {
                    addObjectToDisplay(new Char('.'), i, j);
                }
            }
            for (int i = x; i <= x + width - 1; i++) {
                addObjectToDisplay(ch, i, y);
                addObjectToDisplay(ch, i, y + height - 1);
            }
            for (int i = y; i <= y + height - 1; i++) {
                addObjectToDisplay(ch, x, i);
                addObjectToDisplay(ch, x + width - 1, i);
            }
        }

        for(Passage passage:Passages){
            int size = passage.getX().size();
            int new_X, new_Y;
            int old_X = 0, old_Y = 0;
            for(int i = 0; i < size; i++){
                new_X = passage.getX().get(i);
                new_Y = passage.getY().get(i);
                new_Y += topHeight;

                if(i != 0){
                    int k = Math.min(new_X, old_X);//smaller
                    int j = Math.max(new_X, old_X);//larger
                    for(;k<=j;k++){
                        addObjectToDisplay(new Char('#'),k,old_Y);
                    }
                    k = Math.min(new_Y, old_Y);//smaller
                    j = Math.max(new_Y, old_Y);//larger
                    for(;k<=j;k++){
                        addObjectToDisplay(new Char('#'),new_X,k);
                    }
                }
                old_X = new_X;
                old_Y = new_Y;
            }
            addObjectToDisplay(new Char('+'),passage.getX().get(0),passage.getY().get(0)+topHeight);
            addObjectToDisplay(new Char('+'),passage.getX().get(size-1),passage.getY().get(size-1)+topHeight);
        }


        for(Displayable observers:inputObservers){//not room, not passage, all creature or item
            int x = observers.getX().get(0);
            int y = observers.getY().get(0);
            y += topHeight;
            char ch = observers.getRep_char();
            addObjectToDisplay(new Char(ch), x, y);
        }

        addObjectToDisplay(new Char(player.getRep_char()), player.getX().get(0), player.getY().get(0) + topHeight);
        //String message = "HP: " + player.getHp()+ "  Core: ";
        TopMessageSet();//set top message


    }

    public void TopMessageSet(){
        String input = "HP: " + player.getHp()+ "  Score: ";
        for(int i = 0; i < input.length();i++){
            addObjectToDisplay(new Char(input.charAt(i)), i, 0);

        }
        terminal.repaint();
    }

    public void bottomMessageSet(String input){
        for(int i = 0; i < width; i++){
            addObjectToDisplay(new Char(' '), i,topHeight + gameHeight + 1);
        }
        for(int i = 0; i < input.length();i++){
            addObjectToDisplay(new Char(input.charAt(i)), i, topHeight + gameHeight + 1);
        }

        terminal.repaint();
    }

    public String getBottomMessage(char c){


        return null;
    }

    public boolean processInput() throws Exception {

        char ch;

        boolean processing = true;


        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (ch == 'h' || ch == 'j' || ch == 'k' || ch == 'l') {
                    //System.out.println("got an X, ending input checking");
                    notifyPlayer(ch);
                    refresh();
                    notifyObservers(player.getX().get(0), player.getY().get(0));
                } else if (ch == 'p') {//pick up item
                    Stack<Item> item_stack = new Stack<Item>();
                    for(Displayable disp : inputObservers){//get all item in right pos in item_stack
                        if(disp.getX().get(0) == player.getX().get(0) && disp.getY().get(0) == player.getY().get(0))
                        {
                            //System.out.println("Player: " + player.getX() + " " + player.getY());
                            //System.out.println("Item: " + disp.getX() + " " + disp.getY());
                            if(disp.getCLASSID().equals("Sword") || disp.getCLASSID().equals("Armor")){
                                item_stack.add((Item) disp);
                            }
                        }
                    }
                    Item item = item_stack.pop();
                    if (item.getCLASSID().equals("Sword")) {
                        player.setWeapon(item);
                    } else {
                        player.setArmor(item);
                    }
                    inputObservers.remove(item);
                    bottomMessageSet("Picked up "+item.getName());
                    refresh();

                } else if(ch == 'd'){
                    while(inputQueue.isEmpty()){

                    }
                    int index = Character.getNumericValue(inputQueue.poll());
                    try{
                        Item temp = player.drop(index);
                        if(temp.getY().size() <= 0){
                            temp.setPosX(0);
                            temp.setPosY(0);
                        }
                        temp.getX().set(0, player.getX().get(0));
                        temp.getY().set(0, player.getY().get(0));
                        inputObservers.add(temp);
                        bottomMessageSet("Dropped item: " + temp.getName());
                    }catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                        bottomMessageSet("Invalid input");
                    }


                } else if(ch =='i'){ // show inventory
                    String message = "Pack: ";
                    ArrayList<Item> items = player.getItems();
                    int i = 0;
                    for(Item item: items){
                        message = message + i + ":" + item.getName() + " ";
                        i++;
                    }
                    bottomMessageSet(message);
                }
                else {
                    //System.out.println("character " + ch + " entered on the keyboard");
                    System.out.println("notify other to handel message display");
                }
            }
        }
        //refresh();
        terminal.repaint();
        if(player_dead){
            return false;
        }
        return true;
    }

    @Override
    public void run(){
        /*while (working) {
            sleep(30);
            try {
                working = processInput( );
            } catch (Exception e) {

            }


        }*/
        Keylistener keyL = new Keylistener(this);
        Thread inputHandler = new Thread(keyL);
        inputHandler.run();
        try {
            inputHandler.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
