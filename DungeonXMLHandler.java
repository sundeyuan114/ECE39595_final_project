import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class DungeonXMLHandler extends DefaultHandler{
    private static final int DEBUG = 1;
    private static final String CLASSID = "DungeonXMLHandler";
    private StringBuilder data = null;

    List<String> Rooms = new ArrayList<String>( );

    //最顶层是dungeon. 以下是分层

    //第一层级
    private Passages passagesBeingParsed = null;
    private Rooms roomsBeingParsed = null;

    //第二层级
    private Monster monsterBeingParsed = null;
    private Player playerBeingParsed = null;

    //第三层级
    private CreatureAction CreatureActionBeingParsed = null;

    //第四层级
    private actionMessage actionMessageBeingParsed = null;

    private boolean visible = false;
    private boolean posX = false;
    private boolean posY = false;
    private boolean width = false;
    private boolean height = false;
    private boolean type = false;
    private boolean hp = false;
    private boolean maxhit = false;
    private boolean hpMoves = false;
    private boolean CreatureAction = false;
    private boolean actionMessage = false;

    public List getStudents() {
        return dungeons;
    }

    public DungeonXMLHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if (qName.equalsIgnoreCase("Dungeon")) {
            //dungeons.add(getDungeon(attributes.getValue("name"),Integer.parseInt(attributes.getValue("width")),Integer.parseInt(attributes.getValue("gameHeight"))));
        }else if (qName.equalsIgnoreCase("Rooms")){
            //do nothing
        }else if (qName.equalsIgnoreCase("room")){
            //do nothing
        }else if (qName.equalsIgnoreCase("Passages")){

        }else if (qName.equalsIgnoreCase("Passage")){

        /////////////////////////////////////////////////////////
        }else if (qName.equalsIgnoreCase("visible")){

        }else if (qName.equalsIgnoreCase("PosX")){

        }else if (qName.equalsIgnoreCase("PosY")){

        }else if (qName.equalsIgnoreCase("width")){

        }else if (qName.equalsIgnoreCase("height")){

        }else if (qName.equalsIgnoreCase("Monster")){

        }else if (qName.equalsIgnoreCase("type")){

        }else if (qName.equalsIgnoreCase("hp")){

        }else if (qName.equalsIgnoreCase("maxhit")){

        }else if (qName.equalsIgnoreCase("CreatureAction")){

        }else if (qName.equalsIgnoreCase("actionmessage")){

        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (visible) {

        }else if (posX) {


        }else if (posY) {


        }else if (width) {


        }else if (height) {


        }else if (type) {


        }else if (hp) {


        }else if (maxhit) {


        }else if (hpMoves) {


        }else if (CreatureAction) {


        }else if (actionMessage) {


        }

        if (qName.equalsIgnoreCase("Dungeon")) {
            //dungeons.add(getDungeon(attributes.getValue("name"),Integer.parseInt(attributes.getValue("width")),Integer.parseInt(attributes.getValue("gameHeight"))));
        }else if (qName.equalsIgnoreCase("Rooms")){
            //do nothing
        }else if (qName.equalsIgnoreCase("room")){
            //do nothing
        }else if (qName.equalsIgnoreCase("Passages")){

        }else if (qName.equalsIgnoreCase("Passage")){

            /////////////////////////////////////////////////////////
        }else if (qName.equalsIgnoreCase("visible")){

        }else if (qName.equalsIgnoreCase("PosX")){

        }else if (qName.equalsIgnoreCase("PosY")){

        }else if (qName.equalsIgnoreCase("width")){

        }else if (qName.equalsIgnoreCase("height")){

        }else if (qName.equalsIgnoreCase("Monster")){

        }else if (qName.equalsIgnoreCase("type")){

        }else if (qName.equalsIgnoreCase("hp")){

        }else if (qName.equalsIgnoreCase("maxhit")){

        }else if (qName.equalsIgnoreCase("CreatureAction")){

        }else if (qName.equalsIgnoreCase("actionmessage")){

        }
    }
}
