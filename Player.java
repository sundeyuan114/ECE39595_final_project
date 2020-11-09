package game;
import java.util.*;
public class Player extends Creature{
    //private int posX, posY;
    private ObjectDisplayGrid odg;
    private Item armor;
    private Item sword;
    private Dungeon dungeon;
    private Char repr = new Char('@');
    private Room room;
    private Queue<Item> pack = new Queue<Item>();

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Player(){
        System.out.println("Constructing player object");
    }

    public void setWeapon(Item sword)
    {
        this.sword = sword;
        System.out.println("Setting weapon of player: "+sword);
    }

    public void setArmor(Item armor){
        this.armor = armor;
        System.out.println("Setting player armor: "+armor);
    }

    public Char getrepr(){
        return repr;
    }
//
//     push, move pop here using methods in ODG
    public void itemDrop(Char num){
        int numb = Character.getNumericValue(num);
        Room currentRoom = null;
        currentRoom = Dungeon.findCurrentRoom(this.getPosX(), this.getPosY());

        for(int i = 0; i < numb; i++){
            Item temp = pack.poll();
            currentRoom.dropCurrentItem(temp);
            odg[this.getPosX()][this.getPosY()].push(temp);

        }
    }
}
    public void itemPick(Char interact)
    {
        if(interact.equals('p')) {
            Char stepOnBlock = odg.getStandingBlock(this.getPosX(), this.getPosY());
            Room currentRoom = null;
            currentRoom = Dungeon.findCurrentRoom(this.getPosX(), this.getPosY());
            Item mostRecentItem = currentRoom.getRecentItem(stepOnBlock, this.getPosX(), this.getPosY());
            pack.add(mostRecentItem);
        }
    }
    public void updateMove(char move){
        int x=0 ,y = 0;
        //checkmovement valid first
        if (move == 'h'){
            x -= 1;
        }
        else if (move == 'k'){
            y += 1;
        }
        else if (move == 'j'){
            y -= 1;
        }
        else if (move == 'l'){
            x += 1;
        }
        if (!dungeon.validateMove(posX+x,posY+y)){
            return;
        }
        odg.removeObjectToDisplay(posX,posY);
        posY += y;
        posX += x;

        odg.addObjectToDisplay(repr,posX,posY);
    }

    public void SetPosX(int posX) {
        this.posX = posX + room.getPosX();
    }

    public void SetPosY(int posY) {
        this.posY = posY + room.getPosY() + 2;
    }
    public void SetRoom(Room _room){
        room = _room;
    }

    public void setODG(ObjectDisplayGrid odg) {
        this.odg = odg;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Item getArmor() {
        return armor;
    }
    public Item getSword(){
        return sword;
    }

    @Override
    public String getName(){
        String name = "player";
        return name;
    }
}
