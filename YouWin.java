package game;
public class YouWin extends CreatureAction{
    public YouWin(String name, Creature owner){
        super(owner);
        System.out.println(""+owner+"wins");
    }
}