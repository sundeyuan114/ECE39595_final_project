package game;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Stack;



public class DungeonXMLHandler extends DefaultHandler {

    private static final int DEBUG = 1;
    private static final String CLASSID = "DungeonXMLHandler";
    private StringBuilder data = null;

    //其实在我们的算法里面是不需要这个arraylist的,但是为了给他的getdungeon传回去.
    private ArrayList<Displayable> displayables = new ArrayList<Displayable>();//getdungeon要把这个return回去
    private Stack<Displayable> stack = new Stack<Displayable>(); //每一层级里面都有不同的posX,posY,width,之类的

    //我刚开始写的时候没有这个,但是写着写着发现XML里面有一些CreatureAction之类的东西,
    //他们没法放在displayable的stack里,但又是个Object,所以也没办法像处理一些数值一样用一个boolean啥啥来当开关,
    //所以只好再建一个Stack存所有的Action.
    private Stack<Action> stackA = new Stack<Action>();

    //所以我们implement stack 每一新的层级就是新的stack.
    //Dungeon是最外层,Rooms 和 Passage是第一层, 再往里. etc.

    private Dungeon dungeonBeingParsed = null;//Dungeon -> {Rooms, Passages}

    private Room roomBeingParsed = null;//Rooms -> {room}
    private Monster monsterBeingParsed = null;
    private Player playerBeingParsed = null;
    private CreatureAction creatureActionBeingParsed = null;
    private Scroll scrollBeingParsed = null;
    private Sword swordBeingParsed = null;
    private Armor armorBeingParsed = null;
    private ItemAction itemActionBeingParsed = null;

    //private Passages passagesBeingParsed = null;
    private Passage passageBeingParsed = null;

    //因为我们上面用了stack所以我们其实并不需要每一个boolean都设出来.
    //如果是object的话,会被我们直接push到stack里面.
    //如果是<posX> 这样的话,我们需要把数值直接发送到stack里面目前object的参数上
    //所以对这种数值我们是需要用boolean来做一个开关的.

    private boolean bvisible = false;
    private boolean binvisible = false;
    private boolean bposX = false;
    private boolean bposY = false;
    private boolean bwidth = false;
    private boolean bheight = false;

    private boolean btype = false;
    private boolean bhp = false;
    private boolean bmaxhit = false;
    private boolean bhpMoves = false;

    private boolean bactionmessage = false;
    private boolean bactionCharValue = false;
    private boolean bactionIntValue = false;
    private boolean bitemIntValue = false;

    public ArrayList<Displayable> getDisplayables() {
        return displayables;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }
        if (qName.equalsIgnoreCase("Dungeon")) {
            String name = attributes.getValue("name");
            int wid = Integer.parseInt(attributes.getValue("width"));
            int topHei = Integer.parseInt(attributes.getValue("topHeight"));
            int gameHei = Integer.parseInt(attributes.getValue("gameHeight"));
            int bottomHei = Integer.parseInt(attributes.getValue("bottomHeight"));

            //new一个新的dungeon object出来
            dungeonBeingParsed = new Dungeon(name, wid, topHei, gameHei, bottomHei);
            /*
            这是midkiff trace stack的结果,里面没有dungeon.他说把所有的displayable object 都print出来,Dungeon不在displayable里面
            所以我们直接把dungeon加入stack， （到底要不要加到arraylist里面返回去睡醒以后再说,想加也加不进去啊..arraylist<displayable>）
            里面也没法放进去一个dungeon object.

            https://piazza.com/class/kdrp9fnvtvkwo?cid=276

            src.Room@2d8e6db6
            src.Monster@23ab930d
            src.Armor@4534b60d
            src.Monster@3fa77460
            src.Armor@619a5dff
            src.Player@1ed6993a
            src.Room@7e32c033
            src.Monster@7ab2bfe1
            src.Armor@497470ed
            src.Monster@63c12fb0
            src.Armor@b1a58a3
             */
            //stack.push(dungeonBeingParsed);

            //理论上来说step1 没有办法把Dungeon传出去
            //我们的这个所以push不push没有啥太大意义,反正最后那这一串数据都会丢失的.
            //为了以后方便提前把格式写好了.
        } else if (qName.equalsIgnoreCase("Rooms")) {
            //add rooms to dungeon
        } else if (qName.equalsIgnoreCase("Room")) {
            String iD = attributes.getValue("room");
            roomBeingParsed = new Room(iD);
            dungeonBeingParsed.addRoom(roomBeingParsed);

            stack.push(roomBeingParsed);
        }  else if (qName.equalsIgnoreCase("Passages")) {

        } else if (qName.equalsIgnoreCase("Passage")){
            String room1 = attributes.getValue("room1");
            String room2 = attributes.getValue("room2");
            passageBeingParsed = new Passage();
            passageBeingParsed.setID(Integer.parseInt(room1),Integer.parseInt(room2));
            dungeonBeingParsed.addPassage(passageBeingParsed);

            stack.push(passageBeingParsed);
        }
        else if (qName.equalsIgnoreCase("posX")) {
            bposX = true;
        } else if (qName.equalsIgnoreCase("posY")) {
            bposY = true;
        } else if (qName.equalsIgnoreCase("width")) {
            bwidth = true;
        } else if (qName.equalsIgnoreCase("height")) {
            bheight = true;
        } else if (qName.equalsIgnoreCase("type")) {
            btype = true;
        } else if (qName.equalsIgnoreCase("hp")) {
            bhp = true;
        } else if (qName.equalsIgnoreCase("maxhit")) {
            bmaxhit = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            bhpMoves = true;
        } else if (qName.equalsIgnoreCase("actionMessage")) {
            bactionmessage = true;
        } else if (qName.equalsIgnoreCase("actionIntValue")) {
            bactionIntValue = true;
        } else if (qName.equalsIgnoreCase("actionCharValue")) {
            bactionCharValue = true;
        } else if (qName.equalsIgnoreCase("visible")) {
            bvisible = true;
        } else if (qName.equalsIgnoreCase("invisible")) {
            binvisible = true;
        } else if (qName.equalsIgnoreCase("ItemIntValue")) {
            bitemIntValue = true;
        }  else if (qName.equalsIgnoreCase("Monster")) {
            String name = attributes.getValue("name");
            int roomNumb = Integer.parseInt(attributes.getValue("room"));
            int serialNumb = Integer.parseInt(attributes.getValue("serial"));
            monsterBeingParsed = new Monster();
            monsterBeingParsed.setName(name);
            monsterBeingParsed.setID(roomNumb, serialNumb);
            monsterBeingParsed.SetRoom(roomBeingParsed);
            monsterBeingParsed.setrepr(name);

            //把这怪物趁着reference还在 加到他该在的位置里面去
            dungeonBeingParsed.addCreature(monsterBeingParsed);
            roomBeingParsed.setCreature(monsterBeingParsed);

            stack.push(monsterBeingParsed);
        } else if (qName.equalsIgnoreCase("Player")) {
            String name = attributes.getValue("name");
            int roomNumb = Integer.parseInt(attributes.getValue("room"));
            int serialNumb = Integer.parseInt(attributes.getValue("serial"));
            playerBeingParsed = new Player();

            //把这Player趁着reference还在 加到他该在的位置里面去
            if (dungeonBeingParsed != null){
                dungeonBeingParsed.addCreature(playerBeingParsed);
                dungeonBeingParsed.setPlayer(playerBeingParsed);
                // set player to dungeon,
                // set dungeon to player
                playerBeingParsed.setDungeon(dungeonBeingParsed);
            }
            if (roomBeingParsed != null){
                roomBeingParsed.setCreature(playerBeingParsed);
                playerBeingParsed.SetRoom(roomBeingParsed);
            }


            stack.push(playerBeingParsed);
        } else if (qName.equalsIgnoreCase("CreatureAction")) {
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            // 这里的下面stack.peak直接传过去是displayable,手动assign成为Creature再传.
            creatureActionBeingParsed = new CreatureAction((Creature) stack.peek());

            //PDF上面没有这两个东西,但是如果不写的话 <CreatureAction name="EndGame" type="death"> 就没了.
            creatureActionBeingParsed.setType(type);
            creatureActionBeingParsed.setName(name);

            // Let creature being parsed have reference to this creatureAction
            stack.peek().setCreatureAction(creatureActionBeingParsed);

            //stack是存<Displayable>的, StackA是存Action的,注意.
            stackA.push(creatureActionBeingParsed);
        } else if (qName.equalsIgnoreCase("ItemAction")){
            //<ItemAction name="BlessArmor" type="item">

            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            itemActionBeingParsed = new ItemAction((Item)stack.peek());
            itemActionBeingParsed.setName(name);
            itemActionBeingParsed.setType(type);

            stackA.push(itemActionBeingParsed);
        }
        else if (qName.equalsIgnoreCase("Scroll")){
            String name = attributes.getValue("name");
            int roomNumb = Integer.parseInt(attributes.getValue("room"));
            int serialNumb = Integer.parseInt(attributes.getValue("serial"));
            scrollBeingParsed = new Scroll(name);

            scrollBeingParsed.SetRoom(roomBeingParsed);
            dungeonBeingParsed.addItem(scrollBeingParsed);
            scrollBeingParsed.setID(roomNumb,serialNumb);
            roomBeingParsed.setItem(scrollBeingParsed);
            stack.push(scrollBeingParsed);
        } else if (qName.equalsIgnoreCase("Sword")){
            swordBeingParsed = new Sword(attributes.getValue("name"));
            dungeonBeingParsed.addItem(swordBeingParsed);
            swordBeingParsed.SetRoom(roomBeingParsed);
            swordBeingParsed.setID(Integer.parseInt(attributes.getValue("room")),Integer.parseInt(attributes.getValue("serial")));
            roomBeingParsed.setItem(swordBeingParsed);
            stack.push(swordBeingParsed);
        } else if (qName.equalsIgnoreCase("Armor")){
            armorBeingParsed = new Armor(attributes.getValue("name"));
            dungeonBeingParsed.addItem(armorBeingParsed);
            armorBeingParsed.SetRoom(roomBeingParsed);
            armorBeingParsed.setID(Integer.parseInt(attributes.getValue("room")),Integer.parseInt(attributes.getValue("serial")));
            roomBeingParsed.setItem(armorBeingParsed);
            stack.push(armorBeingParsed);
        } else {
            // BUG
            System.out.println("Unknown qname: " + qName);
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Displayable currentBeingParsed = null;
        if (!(stack.empty())) {
            currentBeingParsed = stack.peek();
        } //如果stack是空的当前parse指针为null,如果stack非空,指针指向当年最外层的stack,也就是stack.peek()
        //currentBeingParsed 指向当前正在parse的displayable object.

        //data.toString是两个Qname中间的话
        //<actionMessage>You have defeated the Troll!</actionMessage>
        // data.tostring() is equivalent to You have defeated the Troll!
        if (bposX) {
            int x = Integer.parseInt(data.toString());
            currentBeingParsed.SetPosX(x);
            bposX = false;
        } else if (bposY) {
            int y = Integer.parseInt(data.toString());
            currentBeingParsed.SetPosY(y);
            bposY = false;
        } else if (bwidth) {
            int width = Integer.parseInt(data.toString());
            currentBeingParsed.SetWidth(width);
            bwidth = false;
        } else if (bheight) {
            int height = Integer.parseInt(data.toString());
            currentBeingParsed.SetHeight(height);
            bheight = false;
        } else if (btype) {
            String typeString = data.toString();
            char type = typeString.charAt(0);
            System.out.println("btype = " + type);
            currentBeingParsed.setType(type);
            btype = false;
        } else if (bhp) {
            int hp = Integer.parseInt(data.toString());
            currentBeingParsed.setHp(hp);
            bhp = false;
        } else if (bmaxhit) {
            int maxhit = Integer.parseInt(data.toString());
            currentBeingParsed.setMaxHit(maxhit);
            bmaxhit = false;
        } else if (bhpMoves) {
            int hpMoves = Integer.parseInt(data.toString());
            currentBeingParsed.setHpMove(hpMoves);
            bhpMoves = false;
        } else if (bitemIntValue){
            int intVal = Integer.parseInt(data.toString());
            currentBeingParsed.setIntValue(intVal);
            bitemIntValue = false;
        } else if (bactionmessage) {
            String ActionMessage = data.toString();
            Action tempAction = stackA.peek();
            tempAction.setMessage(ActionMessage);
            bactionmessage = false;
        } else if (bactionIntValue) {
            int intValue = Integer.parseInt(data.toString());
            Action tempAction = stackA.peek();
            tempAction.setIntValue(intValue);
            bactionIntValue = false;
        } else if (bactionCharValue){
            char charVal = data.toString().charAt(0);
            Action tempAction = stackA.peek();
            tempAction.setCharValue(charVal);
            bactionCharValue = false;
        }
        else if (bvisible) {
            currentBeingParsed.setVisible();
            bvisible = false;
        } else if(binvisible) {
            currentBeingParsed.setInvisible();
            binvisible = false;
        } else if (qName.equalsIgnoreCase("Monster")){
            monsterBeingParsed = null;
            stack.pop();
        } else if(qName.equalsIgnoreCase("Room")) {
            roomBeingParsed = null;
            stack.pop();
        } else if (qName.equalsIgnoreCase("Passages")){

        } else if (qName.equalsIgnoreCase("Passage")) {
            passageBeingParsed = null;
            stack.pop();
        } else if(qName.equalsIgnoreCase("Player")) {
            playerBeingParsed = null;
            stack.pop();
        } else if(qName.equalsIgnoreCase("CreatureAction")){
            stackA.pop();
        } else if (qName.equalsIgnoreCase("ItemAction")){
            currentBeingParsed.setItemAction((ItemAction)stackA.pop());
            System.out.println("ItemActionParsetoItem " + currentBeingParsed);
            System.out.println("ItemFields " + currentBeingParsed.getItemAction());
            System.out.println("currentbp.class = "+currentBeingParsed.getClass().getName());
        }else if(qName.equalsIgnoreCase("Rooms")){
            //do nothing.
        } else if (qName.equalsIgnoreCase("Armor")) {
            stack.pop();
        } else if (qName.equalsIgnoreCase("Sword")) {
            stack.pop();
        //} else if (qName.equalsIgnoreCase("Sword")){
         //   stack.pop();
        } else if (qName.equalsIgnoreCase("Scroll")){
            stack.pop();
        } else if(qName.equalsIgnoreCase("Dungeon")) {
            //code能跑到这说明XML文件现在已经走到尾了,</Dungeon>, 现在Stack 和 StackA不应该有任何的Stack存在.
            assert(stack.empty());
            assert(stackA.empty());
        }
    }
    public Dungeon getDungeon(){
        return dungeonBeingParsed;
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
