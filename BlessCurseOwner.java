package game;
public class BlessCurseOwner extends ItemAction{
    public BlessCurseOwner(Item owner){
        super(owner);
        System.out.println(""+owner+"BlessCurse Owner constructing.");
    }
}