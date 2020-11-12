import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Rogue implements Runnable {

    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread ODG;
    //private Thread keyStrokePrinter;
    //private static final int WIDTH = 5;
    //private static final int HEIGHT = 10;

    public Rogue(ObjectDisplayGrid _displayGrid) {
        displayGrid = _displayGrid;
    }

    @Override
    public void run() {
        //displayGrid.fireUp();
        //for(int i = 0; i < 100000; i++){

        displayGrid.initializeDisplay();
        //}
        /*while (true){
            displayGrid.initializeDisplay();
        }*/


        /*int WIDTH = displayGrid.getWidth();
        int HEIGHT = displayGrid.getHeight();
        for (int step = 1; step < WIDTH / 2; step *= 2) {
            for (int i = 0; i < WIDTH; i += step) {
                for (int j = 0; j < HEIGHT; j += step) {
                    displayGrid.addObjectToDisplay(new Char('X'), i, j);
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            displayGrid.initializeDisplay();
        }*/
    }

    public static void main(String[] args) {
        String fileName = null;
        switch(args.length){
            case 1:
                fileName = "src/xmlFiles/" + args[0];
                break;
            default:
                System.out.println("java Test <xmlfilename>");
                return;
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try{
            // just copy this
            SAXParser saxParser = saxParserFactory.newSAXParser();
            // just copy this
            dungeonXMLHandler handler = new dungeonXMLHandler();
            // just copy this.  This will parse the xml file given by fileName
            saxParser.parse(new File(fileName), handler);
            ArrayList<Displayable> displayables = handler.getDungeons();


            ObjectDisplayGrid displayGrid = handler.getGridBeingParsed();
            Dungeon dungeon = handler.getDungeonBeingParsed();
            for(Displayable disp : displayables){
                //System.out.println(disp);
                displayGrid.add(disp);
            }
            displayGrid.refresh();

            Rogue test = new Rogue(displayGrid);
            Thread testThread = new Thread(test);
            test.ODG = new Thread(displayGrid);
            testThread.start();
            test.ODG.start();


            test.ODG.join();
            testThread.join();




        } catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
