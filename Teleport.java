package game;
public class Teleport extends CreatureAction{
    public Teleport(String _name,Creature owner){
        super(owner);
        System.out.println(""+owner+"Teleporting");
    }
}