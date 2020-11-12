import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Stack;

public class dungeonXMLHandler extends DefaultHandler {

    private static final int DEBUG = 1;
    private static final String CLASSID = "dungeonXMLHandler";

    private StringBuilder data = null;
    private ArrayList<Displayable> displayables = new ArrayList<Displayable>();
    private Stack<Displayable> stack = new Stack<Displayable>();
    private Stack<Action> ac_stack = new Stack<Action>();

    private Passage passageBeingParsed = null;
    private Room roomBeingParsed = null;
    private Monster monsterBeingParsed = null;
    private Player playerBeingParsed = null;
    private Dungeon dungeonBeingParsed = null;
    private ObjectDisplayGrid gridBeingParsed = null;
    private CreatureAction creatureActionBeingParsed = null;
    private Scroll scrollBeingParsed = null;
    private Armor armorBeingParsed = null;
    private Sword swordBeingParsed = null;
    private ItemAction itemActionBeingParsed = null;

    private boolean bRooms = false;
    private boolean bPassages = false;
    private boolean bposx = false;
    private boolean bposy = false;
    private boolean bwidth = false;
    private boolean btype = false;
    private boolean bhp = false;
    private boolean bmaxhit = false;
    private boolean bmonster = false;
    private boolean bplayer = false;
    private boolean bheight = false;
    private boolean bhpmoves = false;
    private boolean bAcm = false;
    private boolean bvisible = false;
    private boolean bacIntValue = false;
    private boolean bacCharValue = false;
    private boolean bItemAction =false;
    private boolean bItemIntValue = false;
    //default constructor
    public dungeonXMLHandler(){

    }
    //getter, return arraylist of dungeons
    public ArrayList<Displayable> getDungeons(){
        return displayables;
    }

    public Dungeon getDungeonBeingParsed(){
        return dungeonBeingParsed;
    }

    public ObjectDisplayGrid getGridBeingParsed(){
        return gridBeingParsed;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        if(DEBUG > 1){
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if(qName.equalsIgnoreCase("Dungeon")){
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            dungeonBeingParsed = new Dungeon(name, width, gameHeight);
            gridBeingParsed = new ObjectDisplayGrid(width, topHeight, gameHeight, bottomHeight);
        }else if(qName.equalsIgnoreCase("Rooms")){
            bRooms = true;//this is probably useless
        }else if(qName.equalsIgnoreCase("Passages")){
            bPassages = true;//this is also useless
        }
        else if(qName.equalsIgnoreCase("Room")){
            int roomID = Integer.parseInt(attributes.getValue("room"));
            roomBeingParsed = new Room(roomID);
            dungeonBeingParsed.addRoom(roomBeingParsed);
            stack.push(roomBeingParsed);
            displayables.add(roomBeingParsed);
        }else if(qName.equalsIgnoreCase("Passage")){
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            passageBeingParsed = new Passage();
            passageBeingParsed.setID(room1,room2);
            dungeonBeingParsed.addPassage(passageBeingParsed);
            displayables.add(passageBeingParsed);
            stack.push(passageBeingParsed);
        }
        else if(qName.equalsIgnoreCase("posX")){
            bposx =true;
        }else if(qName.equalsIgnoreCase("posY")){
            bposy = true;
        }else if(qName.equalsIgnoreCase("type")){
            btype = true;
        }else if(qName.equalsIgnoreCase("hp")){
            bhp = true;
        }else if(qName.equalsIgnoreCase("height")){
            bheight = true;
        }else if (qName.equalsIgnoreCase("maxhit")){
            bmaxhit = true;
        }else if(qName.equalsIgnoreCase("hpMoves")){
            bhpmoves = true;
        }else if(qName.equalsIgnoreCase("width")){
            bwidth = true;
        }else if(qName.equalsIgnoreCase("visible")){
            bvisible = true;
        }else if(qName.equalsIgnoreCase("actionintValue")){
            bacIntValue = true;
        }else if(qName.equalsIgnoreCase("actionCharValue")){
            bacCharValue = true;
        }else if(qName.equalsIgnoreCase("ItemIntValue")){
            bItemIntValue = true;
        }
        else if(qName.equalsIgnoreCase("Monster")){
            bmonster = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            monsterBeingParsed = new Monster();
            monsterBeingParsed.setID(room, serial);
            monsterBeingParsed.setName(name);
            dungeonBeingParsed.addCreature(monsterBeingParsed);
            roomBeingParsed.setCreature(monsterBeingParsed);
            displayables.add(monsterBeingParsed);
            stack.push(monsterBeingParsed);
        }else if(qName.equalsIgnoreCase("Player")){
            bplayer = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            playerBeingParsed = new Player(name,room,serial);
            dungeonBeingParsed.addCreature(playerBeingParsed);
            //roomBeingParsed.setCreature(playerBeingParsed);
            displayables.add(playerBeingParsed);
            stack.push(playerBeingParsed);
        }else if(qName.equalsIgnoreCase("Scroll")){
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            scrollBeingParsed = new Scroll(name);
            scrollBeingParsed.setID(room, serial);
            if(stack.size() > 1){
                scrollBeingParsed.setOwner((Creature) stack.peek());
            }
            dungeonBeingParsed.addItem(scrollBeingParsed);
            displayables.add(scrollBeingParsed);
            stack.push(scrollBeingParsed);
        }else if(qName.equalsIgnoreCase("Sword")){
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            swordBeingParsed = new Sword(name);
            if(stack.size() > 1){
                swordBeingParsed.setOwner((Creature) stack.peek());
                playerBeingParsed.setWeapon(swordBeingParsed);
            }else{
                displayables.add(swordBeingParsed);
            }
            swordBeingParsed.setID(room, serial);
            dungeonBeingParsed.addItem(swordBeingParsed);
            stack.push(swordBeingParsed);
        }else if(qName.equalsIgnoreCase("Armor")){
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            armorBeingParsed = new Armor(name);
            armorBeingParsed.setID(room, serial);
            if(stack.size() > 1){
                armorBeingParsed.setOwner((Creature) stack.peek());
                playerBeingParsed.setArmor(armorBeingParsed);
            }else{
                displayables.add(armorBeingParsed);
            }
            dungeonBeingParsed.addItem(armorBeingParsed);
            stack.push(armorBeingParsed);
        }else if(qName.equalsIgnoreCase("CreatureAction")){
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            if(stack.size() > 1){
                creatureActionBeingParsed = new CreatureAction((Creature) stack.peek());
            }
            creatureActionBeingParsed.setType(type);
            creatureActionBeingParsed.setName(name);
            ac_stack.push(creatureActionBeingParsed);
        }else if(qName.equalsIgnoreCase("ItemAction")){
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            itemActionBeingParsed = new ItemAction((Item) stack.peek());
            itemActionBeingParsed.setType(type);
            itemActionBeingParsed.setName(name);
            ac_stack.push(itemActionBeingParsed);
        }else if(qName.equalsIgnoreCase("actionMessage")){
            bAcm = true;
        }
        else{
            System.out.println("Unknown qname: " + qName);
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        Displayable currentparsed = (!stack.empty())?stack.peek():null;
        Action currentAction = (!ac_stack.empty())?ac_stack.peek():null;

        if(bposx){
            int x = Integer.parseInt(data.toString());
            //System.out.println("bposx");
            if(roomBeingParsed != null && roomBeingParsed.getX().size()>0){
                x = x + roomBeingParsed.getX().get(0);
            }
            currentparsed.setPosX(x);
            bposx = false;
        }else if(bposy){
            int y = Integer.parseInt(data.toString());
            if(roomBeingParsed != null && roomBeingParsed.getY().size()>0){
                y = y+roomBeingParsed.getY().get(0);
            }
            //System.out.println("bposy");
            currentparsed.setPosY(y);
            bposy = false;
        }else if(btype){
            String temp = data.toString();
            char type = temp.charAt(0);
            currentparsed.setType(type);
            //System.out.println("btype");
            btype = false;
        }else if(bwidth){
            int width = Integer.parseInt(data.toString());
            currentparsed.setWidth(width);
            //System.out.println("bwidth");
            bwidth = false;
        }else if(bheight){
            int height = Integer.parseInt(data.toString());
            currentparsed.setHeight(height);
            //System.out.println("bheight");
            bheight = false;
        }else if(bvisible){
            currentparsed.setVisible();
            bvisible = false;
        }else if(bhp){
            int hp = Integer.parseInt(data.toString());
            currentparsed.setHp(hp);
            //System.out.println("bhp");
            bhp = false;
        }else if(bmaxhit){
            int maxhit = Integer.parseInt(data.toString());
            currentparsed.setMaxHit(maxhit);
            //System.out.println("bmaxhit");
            bmaxhit = false;
        }else if(bhpmoves){
            int hpmoves = Integer.parseInt(data.toString());
            currentparsed.setHpMove(hpmoves);
            //System.out.println("bhpmoves");
            bhpmoves = false;
        }else if(bAcm){
            String message = data.toString();
            currentAction.setMessage(message);
            bAcm = false;
        }else if(bacIntValue){
            int value = Integer.parseInt(data.toString());
            currentAction.setIntValue(value);
            bacIntValue = false;
        }else if(bacCharValue){
            char value = data.charAt(0);
            currentAction.setCharValue(value);
            bacCharValue = false;
        }else if(bItemIntValue){
            int value = Integer.parseInt(data.toString());
            currentparsed.setIntValue(value);
            bItemIntValue = false;
        }

        if(qName.equalsIgnoreCase("Monster")){
            monsterBeingParsed = null;
            stack.pop();
        }else if(qName.equalsIgnoreCase("Player")){
            playerBeingParsed = null;
            stack.pop();
        }else if(qName.equalsIgnoreCase("Room")){
            roomBeingParsed = null;
            stack.pop();
        }else if(qName.equalsIgnoreCase("Passage")){
            passageBeingParsed = null;
            stack.pop();
        }else if(qName.equalsIgnoreCase("CreatureAction")){
            creatureActionBeingParsed = null;
            ac_stack.pop();
        }else if(qName.equalsIgnoreCase("ItemAction")){
            itemActionBeingParsed = null;
            ac_stack.pop();
        }else if(qName.equalsIgnoreCase("Scroll")){
            scrollBeingParsed = null;
            stack.pop();
        }else if(qName.equalsIgnoreCase("Armor")){
            armorBeingParsed = null;
            stack.pop();
        }else if(qName.equalsIgnoreCase("Sword")){
            swordBeingParsed = null;
            stack.pop();
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }

}
