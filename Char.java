package game;
public class Char extends Displayable{

    public static final String CLASSID = "Char";
    private final char displayChar;

    public Char(char c) {
        displayChar = c;
    }
    
    public char getChar( ) {
        return displayChar;
    }
}
