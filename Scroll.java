package game;
public class Scroll extends Item{
    public Scroll(String _name){
        super.setName(_name);
    }

    public void setID(int room, int serial){
        System.out.println("Setting scroll to room"+room+", serial"+serial);
    }


}