import java.util.ArrayList;

//this is an base class for all displayable object
public class Displayable implements InputObserver{

    private String CLASSID = "Displayable";
    protected boolean visible;
    protected int maxhit;
    protected int hpmove;
    protected int hp;
    protected char type;
    protected int intValue;
    protected ArrayList<Integer> posX = new ArrayList<Integer>();
    protected ArrayList<Integer> posY = new ArrayList<Integer>();
    protected int width;
    protected int height;
    protected char rep_char = ':';

    public Displayable(){
        //System.out.println("construct displayable");
    }

    public void setInvisible(){
        //System.out.println("displayable: set invisible");
        visible = false;
    }
    public void setVisible(){
        //System.out.println("displayable: set visible");
        visible = true;
    }
    public void setMaxHit(int maxHit){
        //System.out.println("displayable: set maxhit: "+maxHit);
        maxhit = maxHit;
    }
    public void setHpMove(int hpMoves){
        //System.out.println("displayable: set hp move: "+hpMoves);
        hpmove = hpMoves;
    }
    public boolean setHp(int Hp){
        //System.out.println("displayable: set hp: "+Hp);
        hp = Hp;
        return hp <= 0;
    }
    public void setType(char t){
        //System.out.println("displayable: set type: "+t);
        type = t;
    }
    public void setIntValue(int v){
        //System.out.println("displayable: set int value: "+v);
        intValue = v;

    }
    public void setPosX(int x){
        //System.out.println("displayable: set posx: "+x);
        posX.add(x);
    }
    public void setPosY(int y){
       // System.out.println("displayable: set poxy: "+y);
        posY.add(y);
    }
    public void setWidth(int x){
        //System.out.println("displayable: set width: "+x);
        width = x;
    }
    public void setHeight(int y){
        //System.out.println("displayable: set height: "+y);
        height = y;
    }

    public ArrayList<Integer> getX(){
        return posX;
    }

    public ArrayList<Integer> getY(){
        return posY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getCLASSID(){
        return CLASSID;
    }

    public char getRep_char(){
        return rep_char;
    }

    public int getHp(){
        return hp;
    }

    public int getMaxhit(){
        return maxhit;
    }

    @Override
    public boolean observerUpdate(int player_x, int player_y) {
        if(posX.get(0) == player_x && posY.get(0) == player_y) {
            //System.out.println("this object " + this + " interact with player");
            return true;
        }
        return false;
    }

}
