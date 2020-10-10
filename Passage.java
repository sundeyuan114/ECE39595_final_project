public class Passage extends Structure{
    public Passage(){
        System.out.println("Constructing passage");
    }

    public void setName(String _name){
        System.out.println("Setting passage name to be "+name);
    }

    public void setID(int room1,int room2){
        System.out.println("Setting passage room 1 = "+room1+", passage room 2 = "+room2);
    }
}