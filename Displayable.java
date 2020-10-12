public class Displayable{

    public Displayable(){
        System.out.println("Constructing Displayable");
    }

    public void setInvisible(){
        System.out.println("Setting invisible ");
    }

    public void setVisible(){
        System.out.println("Setting visible");
    }

    public void setMaxHit(int maxHit){
        System.out.println("setting maxhit = "+ maxHit);
    }

    public void setHpMove(int hpMoves){
        System.out.println("Setting hpmoves " + hpMoves);
    }

    public void setHp(int Hp){
        System.out.println("Setting Hp "+Hp);
    }

    public void setType(char t){
        System.out.println("Setting type = "+ t);
    }

    public void setIntValue(int v){
        System.out.println("Setting int value "+ v);
    }

    public void SetPosX(int x){
        System.out.println("Setting PosX = "+x);
    }

    public void SetPosY(int y ){
        System.out.println("Setting PosY ="+y);
    }

    public void SetWidth(int x){
        System.out.println("Setting width = "+ x);
    }

    public  void SetHeight(int y){
        System.out.println("Setting height = "+y);
    }

}