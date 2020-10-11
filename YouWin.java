package src;
public class YouWin extends CreatureAction{
    public YouWin(String name, Creature owner){
        System.out.println(""+owner+"wins");
    }
}