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

        displayGrid.initializeDisplay(); // just changed


        for (Room r : dungeon.getRooms()) {         //walls
            int initialY = 0, initialX = 0;
            initialY += dungeon.getTopHeight();//save space for topheight
            initialX += r.getPosX();
            initialY += r.getPosY();
            for (int i = initialX;i < (initialX+r.getWidth() -1);i++){   //display room
                displayGrid.addObjectToDisplay(roomChar, i , initialY);
            }
            for (int i = initialY; i <(initialY+r.getHeight() -1);i++){  //display room
                displayGrid.addObjectToDisplay(roomChar,initialX,i);
            }

            for (int i = initialX;i < (initialX+r.getWidth()  );i++){   //display room
                displayGrid.addObjectToDisplay(roomChar, i , initialY+r.getHeight() -1);
            }
            for (int i = initialY; i <(initialY+r.getHeight() -1);i++){  //display room
                displayGrid.addObjectToDisplay(roomChar,initialX+r.getWidth() -1,i);
            }
            //floor
            for (int i = initialX + 1; i < initialX + r.getWidth()-1; i++){
                for(int j = initialY + 1; j < initialY + r.getHeight()-1; j++){
                    displayGrid.addObjectToDisplay(floorChar, i , j);
                }
            }

            //things in it
            for (Creature d : r.getCreatures()){        //creatures
                //System.out.println(""+d.getrepr().getChar()+d.getPosX()+d.getPosY()+d.getName()+" ");
                displayGrid.addObjectToDisplay(d.getrepr(), r.getPosX() + d.getPosX() ,
                        r.getPosY() + d.getPosY() + 2 );
            }

            for (Item d : r.getItems()){        //items
                //System.out.println(""+d.getrepr().getChar()+d.getPosX()+d.getPosY()+d.getName());
                displayGrid.addObjectToDisplay(d.getrepr(), r.getPosX() + d.getPosX() ,
                        r.getPosY() + d.getPosY() + 2);
            }
        }

        int tempX ;  //x location of the first point
        int tempY ;
        int currentX;
        int currentY;
        Char passageChar = new Char('#');

        for (Passage p : dungeon.getPassages()){ //traverse through every passage
            int PassageQsize = p.getsize();

            tempX = p.GetPosX(); //x location of the first point // one number already taken out
            tempY = p.GetPosY(); //y location of the first point

            for (int i = 0; i < PassageQsize - 1;i++){ // represents how many connections we need to make between points.
                currentX = p.GetPosX();
                currentY = p.GetPosY();

                // because the connection will always be a straight line, so either X or Y would remain the same between 2 points.
                if(tempX - currentX == 0){// Y changes
                    if(tempY < currentY)
                        for(int l = tempY; l <= currentY; l++ ) {
                            displayGrid.addObjectToDisplay(passageChar, tempX, l + 2);
                    }else{
                        for(int l = tempY; l >= currentY; l-- ) {
                            displayGrid.addObjectToDisplay(passageChar, tempX, l + 2);
                        }
                    }
                }else{// X changes
                    if (tempX < currentX){ //move to right
                        //System.out.println("X change tempX & currentX going right"+ tempX +" " + currentX);
                        for(int m = tempX; m <= currentX; m++){
                            displayGrid.addObjectToDisplay(passageChar, m, tempY + 2);
                        }
                    }else{ // tempX > currentX
                        //System.out.println("X change tempX & currentX going left"+ tempX +" " + currentX);
                        for(int m = tempX; m >= currentX; m--){
                            displayGrid.addObjectToDisplay(passageChar, m, tempY + 2);
                        }
                    }
                }
                //update tempX
                tempX = currentX;
                tempY = currentY;

                //System.out.println("tempX + tempY"  + tempX + ", "+ tempY);
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
        Rogue rogue = new Rogue(dungeon.getWidth(), dungeon.getGameHeight()+
                dungeon.getBottomHeight()+dungeon.getTopHeight(), dungeon);
        Thread rogueThread = new Thread(rogue);
        rogueThread.start();

        rogue.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid, dungeon.getPlayer()));
        rogue.keyStrokePrinter.start();

        rogueThread.join();
        rogue.keyStrokePrinter.join();
    }
}
