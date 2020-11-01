package game;
public class Structure extends Displayable{
    public boolean checkMove(int x, int y){
        System.out.println("public class Structure being called instead of room or passage");
        return false;
    }
}