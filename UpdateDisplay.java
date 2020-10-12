public class UpdateDisplay extends CreatureAction{
    public UpdateDisplay(String _name,Creature owner){

        super(owner);
        System.out.println(""+owner+"updating display");
    }
}