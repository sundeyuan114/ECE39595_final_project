package game;
import org.xml.sax.SAXException;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
public class Rogue implements Runnable {

    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private static Dungeon dungeon = null;          //one and only in each xml

    public Rogue(int width, int height,Dungeon _dungeon) {
        dungeon = _dungeon;
        displayGrid = new ObjectDisplayGrid(width, height);
    } // now we have the reference of dungeon in this scope

    @Override
    public void run() {
        // wee need to initialize all the rooms to the statics first
        // therefore it should be in the beginning of the for loop
        Char roomChar = new Char('X');
        Char floorChar = new Char('.');
        int initialY = 0,initialX = 0;
        displayGrid.initializeDisplay();

        for (Room r : dungeon.getRooms()) {
            initialY += dungeon.getTopHeight();     //save space for topheight
            for (int i = initialX;i < (initialX+dungeon.getWidth());i++){   //display room
                displayGrid.addObjectToDisplay(roomChar, i , initialY);
            }
            for (int i = initialY; i <(initialY+dungeon.getGameHeight());i++){  //display room
                displayGrid.addObjectToDisplay(roomChar,initialX,i);
            }

            for (int i = initialX;i < (initialX+dungeon.getWidth());i++){   //display room
                displayGrid.addObjectToDisplay(roomChar, i , initialY+dungeon.getGameHeight());
            }
            for (int i = initialY; i <(initialY+dungeon.getGameHeight());i++){  //display room
                displayGrid.addObjectToDisplay(roomChar,initialX+dungeon.getWidth() ,i);
            }
            for (int i = initialX + 1; i < initialX + dungeon.getWidth()-1; i++){
                for(int j = initialY + 1; j < initialY + dungeon.getGameHeight()-1; j++){
                    displayGrid.addObjectToDisplay(floorChar, i , j);
                }
            }


            for (Creature d : r.getCreatures()){        //creatures
                displayGrid.addObjectToDisplay(d.getrepr(), d.getPosX() ,
                        d.getPosY());
            }
            for (Item d : r.getItems()){        //items
                displayGrid.addObjectToDisplay(d.getrepr(), d.getPosX() ,
                        d.getPosY());
            }
        }

        int tempX ;  //x location of the first point
        int tempY ;
        int currentX;
        int currentY;
        Char passageChar = new Char('#');

        for (Passage p : dungeon.getPassages()){ //traverse through every passage
            tempX = p.GetPosX(); //x location of the first point
            tempY = p.GetPosY(); //y location of the first point
            for (int i = 0; i < p.getsize() - 1;i++){ // represents how many connections we need to make between points.
                currentX = p.GetPosX();
                currentY = p.GetPosY();

                // because the connection will always be a straight line, so either X or Y would remain the same between 2 points.
                if(tempX - currentX == 0){// Y changes
                    if(tempY < currentY)
                        for(int l = tempY; l <= currentY; l++ ) {
                            displayGrid.addObjectToDisplay(passageChar, tempX, l);
                    }else{
                        for(int l = tempY; l >= currentY; l-- ) {
                            displayGrid.addObjectToDisplay(passageChar, tempX, l);
                        }
                    }
                }else {// X changes
                    if (tempX < currentX){
                        for(int m = tempX; m <= currentX; m++){
                            displayGrid.addObjectToDisplay(passageChar, m, tempY);
                        }
                    }else{ // tempX > currentX
                        for(int m = tempX; m >= currentX; m --){
                            displayGrid.addObjectToDisplay(passageChar, m, tempY);
                        }
                    }
                }
                //update tempX
                tempX = currentX;
                tempY = currentY;
            }
        } // We finish initialize everything so far at this point. However, User input receive & player movement should
        // be accomplish below.



//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace(System.err);
//            }

    }

    public static void main(String[] args) throws Exception {
        String fileName = null;
        switch (args.length) {
            case 1:
                fileName = "xmlFiles/" + args[0];
                break;
            default:
                System.out.println("usage: java game.Rogue <xmlfilename>");
                return;
        }
        DungeonXMLHandler handler = null;
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            handler = new DungeonXMLHandler();
            saxParser.parse(new File(fileName), handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }

        assert handler != null;
        Dungeon dungeon = handler.getDungeon();      //getter function
        Rogue rogue = new Rogue(WIDTH, HEIGHT, dungeon);
        Thread rogueThread = new Thread(rogue);
        rogueThread.start();

        rogue.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid, dungeon.getPlayer()));
        rogue.keyStrokePrinter.start();

        rogueThread.join();
        rogue.keyStrokePrinter.join();
    }
}
