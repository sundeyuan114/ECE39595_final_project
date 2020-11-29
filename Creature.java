package game;
public class Creature extends Displayable {
    protected int maxHit;
    protected int hp;
    protected char type = ' ';
    protected String name = "creature";
    protected int hpMove;

    public Creature() {
        System.out.println("Constructing creature");
    }

    @Override
    public void setHp(int h) {
        hp = h;
    }

    @Override
    public void setHpMove(int hpm) {
        hpMove = hpm;
    }

    @Override
    public void setMaxHit(int mHit) {
        this.maxHit = mHit;
    }


    public int getHp() {
        return hp;
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

    @Override
    public void setCreatureAction(CreatureAction CAction) {
        this.CAction = CAction;
    }
    @Override
    public CreatureAction getCreatureAction(){
        return this.CAction;
    }
}