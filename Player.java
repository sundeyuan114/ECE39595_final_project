package game;
public class Player extends Creature{
    protected int posX, posY;
    private ObjectDisplayGrid odg;
    private Item armor;
    private Item sword;
    private Dungeon dungeon;
    private Char repr = new Char('@');


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

    // push, move pop here using methods in ODG
    public void updateMove(char move){
        int x=0 ,y = 0;
        //checkmovement valid first
        if (move == 'a'){
            x -= 1;
        }
        else if (move == 's'){
            y += 1;
        }
        else if (move == 'w'){
            y -= 1;
        }
        else if (move == 'd'){
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
        System.out.println("Setting player x to be "+posX);
        this.posX = posX;
    }

    public void SetPosY(int posY) {
        this.posY = posY;
        System.out.println("Setting player y to be "+posY);
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
