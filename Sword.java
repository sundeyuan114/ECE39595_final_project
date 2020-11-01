package game;
public class Sword extends Item{
    public Sword(String _name){
        super.setName(_name);
    }

    public void setID(int room,int serial){
        System.out.println("Setting sword room"+room+ ", serial "+serial);
    }
}