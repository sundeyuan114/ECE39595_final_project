public class Room extends Structure{
    public Room(String _name){
        System.out.println("setting room name "+ name);
    }

    public void setID(int room){
        System.out.println("setting room ID = "+id);
    }

    public void setCreature(Creature creature){
        System.out.println("setting creature in room "+ id);
    }
}