public class Player extends Creature{
    public Player(){
        System.out.println("Constructing player object");
    }

    public void setWeapon(Item sword){
        System.out.println("Setting weapon of player: "+sword);
    }

    public void setArmor(Item armor){
        System.out.println("Setting player armor: "+armor);
    }
}