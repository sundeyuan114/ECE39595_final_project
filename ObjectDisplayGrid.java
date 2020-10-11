package src;
public class ObjectDisplayGrid{
    public ObjectDisplayGrid getObjectDisplayGrid(int gameHeight, int width, int topHeight){
        System.out.println("Getting Object Display Grid of "+gameHeight+"x"+width+", top height = "+topHeight);
        return this;
    }
    public void setTopMessageHeight(int topHeight){
        System.out.println("Setting top message height to be "+topHeight);
    }
}