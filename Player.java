package game;
public class Player extends Creature{
    private int posX, posY;
    private ObjectDisplayGrid odg;
    private Item armor;
    private Item sword;
    private Dungeon dungeon;


    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Player(){
        System.out.println("Constructing player object");
    }

    public void setWeapon(Item sword){
        System.out.println("Setting weapon of player: "+sword);
    }

    public void setArmor(Item armor){
        System.out.println("Setting player armor: "+armor);
    }

    public Char getrepr(){
        return new Char('@');
    }

    // push, move pop here using methods in ODG
    public void updateMove(char move){
        int x=0 ,y = 0;
        //checkmovement valid first
        if (move == 'h'){
            x -= 1;
        }
        else if (move == 'j'){
            y += 1;
        }
        else if (move == 'k'){
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

        odg.addObjectToDisplay(new Char('@'),posX,posY);
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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
}
