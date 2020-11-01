package game;
public class Remove extends CreatureAction{
    public Remove(String _name, Creature owner){
        super(owner);
        System.out.println(""+owner+"removing "+_name);
    }

}