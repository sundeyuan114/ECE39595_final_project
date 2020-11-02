package game;
public class Creature extends Displayable{
    protected char type = '@';
    protected String  name = "creature";
    public Creature(){
        System.out.println("Constructing creature");
    }

    @Override
    public void setHp(int h){
        System.out.println("Setting creature Hp to "+h);
    }

    @Override
    public void setHpMove(int hpm){
        System.out.println("Setting creature Hp moves to "+hpm);
    }

    public void setDeathAction(CreatureAction da){
        System.out.println("Setting death action to " + da);
    }

    public void setHitAction(CreatureAction ha){
        System.out.println("Setting hit action"+ha);
    }

    public Char getrepr(){
        System.out.println("type = " +type);
        return new Char(type);
    }

    @Override
    public void setType(char t) {
        type = t;
    }

    public String getName(){
        return name;
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    @Override
    public int getPosX() {
        return super.getPosX();
    }
}