package game;
public class DropPack extends CreatureAction{
    public DropPack(String _name,Creature owner){
        super(owner);
        System.out.println(""+owner+" drops"+_name);
    }
}