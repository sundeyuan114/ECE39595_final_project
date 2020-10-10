public class Creature extends Displayable{
    public Creature(){
        System.out.println("Constructing creature");
    }

    @Override
    public void setHp(int h){
        System.out.println("Setting Hp to "+h);
    }

    @Override
    public void setHpMoves(int hpm){
        System.out.println("Setting Hp moves to "+hpm);
    }

    public void setDeathAction(CreatureAction da){
        System.out.println("Setting death action to " + da);
    }

    public void setHitAction(CreatureAction ha){
        System.out.println("Setting hit action"+ha);
    }

}