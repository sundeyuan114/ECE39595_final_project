package game;
import java.util.*;

//import sun.security.util.Length;
public class Player extends Creature{
    //private int posX, posY;
    private ObjectDisplayGrid odg;
    private Item armor;
    private Item sword;
    private Dungeon dungeon;
    private Char repr = new Char('@');
    private Room room;
    private ArrayList<Item> pack = new ArrayList<Item>();
    private Monster monsterFight = null;
    private int CurrentUpdateMove = 0;
    private boolean bEndGame = false;
    private int hallucinateValue = 0;

    String s = "+])?@THS";
    
    public Character chooseFromRandomList (){
        Random random = new Random();
        int index = random.nextInt(s.length() );
        return s.charAt(index);
    }

    public void setEndGameIndicator(int a){
        if ( a == 1) // pressed E 
        {   for (int i = 0; i < dungeon.getBottomHeight() ; i++)
            {
                odg.addSentenceToDisplay(fillSpaceTillEnd("", dungeon.getWidth()), 0, dungeon.getTopHeight()+dungeon.getGameHeight()+i);
            }
                odg.addSentenceToDisplay(fillSpaceTillEnd("E key is pressed, Game Over.", dungeon.getWidth()), 0, dungeon.getTopHeight()+dungeon.getGameHeight());
                bEndGame = true;
        } else if ( a == 2)
        { // player is dead
            for (int i = 0; i < dungeon.getBottomHeight() ; i++)
            {
                odg.addSentenceToDisplay(fillSpaceTillEnd("", dungeon.getWidth()), 0, dungeon.getTopHeight()+dungeon.getGameHeight()+i);
            }
                odg.addSentenceToDisplay(fillSpaceTillEnd("Player is dead, Game Over.", dungeon.getWidth()), 0, dungeon.getTopHeight()+dungeon.getGameHeight());
                bEndGame = true;
                System.out.println("Changing player to '+', ending game with death actions");
                this.ChangeDisplayType();//=====================
        }   
    }
    public boolean getEndGameIndicator (){
        return bEndGame;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
    public void dropPack (){
        Char num= new Char('1'); // ++++++
        itemDrop(num);
    }
    public void emptyPack (){
        while(pack.size() != 0){
            dropPack();
        }
    }
    public void endGame (){

    }
    public void chooseSetArmor (Char ind){
        int indNumb = Character.getNumericValue(ind.getChar());
        if(pack.get(indNumb - 1).getName() != "Armor"){
            return;
        }
        if(armor != null){
            pack.add(armor);
        }
        armor = pack.get(indNumb - 1);
        pack.remove(indNumb - 1);
        packUpdate();
    }
    public void chooseSetSword(Char ind){
        int indNumb = Character.getNumericValue(ind.getChar());
        if(pack.get(indNumb - 1).getName() != "Sword"){
            return;
        }
        if(sword != null){
            pack.add(sword);
        }
        sword = pack.get(indNumb - 1);
        pack.remove(indNumb - 1);
        packUpdate();
    }
    public void tryTakeOffArmor(){
        if (armor != null){
            pack.add(armor);
            armor = null;
            packUpdate();
            System.out.println("tryTakeOffArmor "+pack.size());
        }else{
            odg.addSentenceToDisplay(fillSpaceTillEnd("No armor Equipped, Cannot take off Armor.", 
            dungeon.getWidth()-6), 6, dungeon.getTopHeight()+dungeon.getGameHeight()+dungeon.getBottomHeight()-1);
        }
    }

    public void tryTakeOffSword(){
        if (sword != null){
            pack.add(sword);
            sword = null;
            packUpdate();
            System.out.println("tryTakeOffSword "+pack.size());
        }else{
            odg.addSentenceToDisplay(fillSpaceTillEnd("No Sword Equipped, Cannot take off Armor.", 
            dungeon.getWidth()-6), 6, dungeon.getTopHeight()+dungeon.getGameHeight()+dungeon.getBottomHeight()-1);
        }
    }
    
    public String fillSpaceTillEnd (String str, int maxLen){
        String ret = str;
        for (int i = 0; i < maxLen - str.length(); i++){
            ret += " ";
        }
        return ret;
    }
    public void tryReadScroll (Char ind){
        int indNumb = Character.getNumericValue(ind.getChar());
        if(pack.get(indNumb - 1).getName() != "Scroll"){
            return;
        }
        if (pack.get(indNumb - 1).getName() == "Scroll");{
            Item scrol = pack.get(indNumb - 1);
            System.out.println(scrol.getName());
            odg.addSentenceToDisplay(scrol.getItemAction().getMessage(),
                    6, dungeon.getTopHeight()+dungeon.getGameHeight()+dungeon.getBottomHeight()-1);
            if (scrol.getItemAction().getCharValue() == 'a'){
                if (armor == null){
                    odg.addSentenceToDisplay(fillSpaceTillEnd("scroll of cursing does nothing because armor not equipped",
                dungeon.getWidth() - 6), 6, dungeon.getTopHeight()+dungeon.getGameHeight()+dungeon.getBottomHeight()-1);}
                else{
                    armor.setIntValue( armor.getIntValue() + scrol.getItemAction().getIntValue()  );
                    odg.addSentenceToDisplay(fillSpaceTillEnd("Armor int value has been added by"
                    + String.valueOf(scrol.getItemAction().getIntValue()), dungeon.getWidth() - 6) ,6, 2+dungeon.getGameHeight()+3);
                }
                pack.remove(indNumb -1);
                packUpdate();
            }
            System.out.println("+++"+scrol.getItemAction().getName());
            if (scrol.getItemAction().getName().equalsIgnoreCase("Hallucinate")){
                System.out.println("Debug Hallucinate +++++++++++++++++++++++++++++++++++++");
                hallucinateValue = scrol.getItemAction().getIntValue()+1;
                pack.remove(indNumb -1);
                System.out.print(pack);
                packUpdate();
            }
        }
    }
    public Player(){
        System.out.println("Constructing player object");
    }

    public void updateIfInput(){
        odg.addSentenceToDisplay("Remaining Hp:"+dungeon.getPlayer().getHp()+"    Score:0       ",0, 0);
    }


    public void setWeapon(Item sword)
    {
        this.sword = sword;
        System.out.println("Setting weapon of player: "+sword);
    }

    public void setArmor(Item armor){
        this.armor = armor;
        System.out.println("Setting player armor: "+armor);
    }

    public Char getrepr(){
        return repr;
    }
//
    public void packUpdate(){
        String strblank = "";
        int initX  = 7;
        int count = 0;
//        if (pack.get(0) == null){
//            System.out.println("null pointer");
//        }
        odg.addSentenceToDisplay(fillSpaceTillEnd("", dungeon.getWidth()-6), 6,dungeon.getTopHeight()+
        dungeon.getGameHeight()+1);
        for(Item i : pack){
            count++;
            System.out.println("pack here"+i.getName());
            String str = count+": "+((i.getName() != "Scroll")?i.getIntValue():"") +" " +i.getName()+"";
            odg.addSentenceToDisplay(str,initX,dungeon.getTopHeight()+dungeon.getGameHeight() + 1);
            initX += (str.length()+1);
        }
        for (int i = 0; i<dungeon.getWidth()-initX;i++){
            strblank += " ";
        }
        odg.addSentenceToDisplay(strblank,initX,dungeon.getTopHeight()+dungeon.getGameHeight() + 1);
    }

    public void itemDrop(Char num){
   
        System.out.println("inside ItemDrop"+num.getChar());
        int numb = Character.getNumericValue(num.getChar());
        if(numb > pack.size()){
            return;
        }
        Room currentRoom = null;
        currentRoom = dungeon.findCurrentRoom(this.getPosX(), this.getPosY());
        Item temp = pack.get(numb - 1);
        pack.remove(numb - 1);
        currentRoom.dropCurrentItem(temp);
        System.out.println("Item xy before = "+temp.getPosX()+temp.getPosY());
        temp.SetPosX(this.getPosX());
        temp.SetPosY(this.getPosY()-2);
        System.out.println("Item xy = "+temp.getPosX()+temp.getPosY());
        odg.dropOnBlock(temp.getrepr(),this.getPosX(),this.getPosY());
//        for(int i = 0; i < numb; i++){
//            Item temp = pack.poll();
//            currentRoom.dropCurrentItem(temp);
//            odg.dropOnBlock(temp.getrepr(),this.getPosX(),this.getPosY());
//        }
    }

    public void itemPick(char interact)
    {
        if(interact == 'p') {           //redundant
            Char stepOnBlock = odg.getStandingBlock(this.getPosX(), this.getPosY());
            System.out.println("StepOnBlock "+stepOnBlock.getChar());
            Room currentRoom = null;
            System.out.println("player x y"+posX+" "+posY);
            currentRoom = dungeon.findCurrentRoom(this.getPosX(), this.getPosY());

            if (currentRoom == null){
                System.out.println("Room null again");
            }
            System.out.println("Room x,y"+currentRoom.getPosX()+" "+currentRoom.getPosY());

            Item mostRecentItem = currentRoom.getRecentItem(stepOnBlock, this.getPosX(), this.getPosY());
            pack.add(mostRecentItem);
            if (mostRecentItem == null){
                System.out.println("Item null");
            }
        }
    }

    //     push, move pop here using methods in ODG
    public void updateMove(char move){
        int x=0 ,y = 0;
        //checkmovement valid first
        if (move == 'h'){
            x -= 1;
        }
        else if (move == 'k'){
            y += 1;
        }
        else if (move == 'j'){
            y -= 1;
        }
        else if (move == 'l'){
            x += 1;
        }
        if (!dungeon.validateMove(posX+x,posY+y)){
            return;
        }
        CurrentUpdateMove ++;
        if(CurrentUpdateMove == hpMove){
            CurrentUpdateMove = 0;
            hp ++;
        }
        if(hallucinateValue > 0){
            hallucinateValue--;
            
            for(Room r : dungeon.getRooms()){
                System.out.println(r.getWidth() + " " + r.getHeight());
                System.out.println(r.getPosX() + " " + r.getPosY());
                for (int i = 0; i < r.getWidth() ; i ++){
                    for (int j = 0; j < r.getHeight() ; j ++){
                        Char a = new Char(chooseFromRandomList());
                        odg.addObjectToDisplay(a,r.getPosX() + i,r.getPosY() + j + 2);
                    }
                }
            }
            for(Passage p : dungeon.getPassages()){
                int tempX = p.getArrayX().get(0);
                int tempY = p.getArrayY().get(0);
                for (int i = 1; i < p.getsize() - 1; i++){
                    int currentX = p.getArrayX().get(i);
                    int currentY = p.getArrayY().get(i);
                    //connect
                    for(int j = tempX; j <= currentX ; j++ ){
                        Char a = new Char (chooseFromRandomList());
                        odg.addObjectToDisplay(a, j, tempY+2);
                    }
                    for(int k = tempY; k <= currentY ; k++ ){
                        Char a = new Char (chooseFromRandomList());
                        odg.addObjectToDisplay(a, tempX, k+2);                       
                    }
                    //
                    tempX = currentX;
                    tempY = currentY;
                }
            }
            if(hallucinateValue == 0){
                odg.initializeDisplay();

                //
                Char roomChar = new Char('X');
                Char floorChar = new Char('.');
        
                odg.initializeDisplay(); // just changed
        
        
                odg.addSentenceToDisplay("Remaining Hp:"+dungeon.getPlayer().getHp()+"    Score:0",0, 0);
                odg.addSentenceToDisplay("Pack :", 0, dungeon.getTopHeight()+dungeon.getGameHeight() + 1);
                odg.addSentenceToDisplay("Info :", 0, dungeon.getTopHeight()+dungeon.getGameHeight() +1+dungeon.getBottomHeight()/2);
        
        
        
                for (Room r : dungeon.getRooms()) {         //walls
                    int initialY = 0, initialX = 0;
                    initialY += dungeon.getTopHeight();//save space for topheight
                    initialX += r.getPosX();
                    initialY += r.getPosY();
                    for (int i = initialX;i < (initialX+r.getWidth() -1);i++){   //display room
                        odg.addObjectToDisplay(roomChar, i , initialY);
                    }
                    for (int i = initialY; i <(initialY+r.getHeight() -1);i++){  //display room
                        odg.addObjectToDisplay(roomChar,initialX,i);
                    }
        
                    for (int i = initialX;i < (initialX+r.getWidth()  );i++){   //display room
                        odg.addObjectToDisplay(roomChar, i , initialY+r.getHeight() -1);
                    }
                    for (int i = initialY; i <(initialY+r.getHeight() -1);i++){  //display room
                        odg.addObjectToDisplay(roomChar,initialX+r.getWidth() -1,i);
                    }
                    //floor
                    for (int i = initialX + 1; i < initialX + r.getWidth()-1; i++){
                        for(int j = initialY + 1; j < initialY + r.getHeight()-1; j++){
                            odg.addObjectToDisplay(floorChar, i , j);
                        }
                    }
        
                    //things in it
                    for (Creature d : r.getCreatures()){        //creatures
                        odg.addObjectToDisplay(d.getrepr(), d.getPosX() , d.getPosY());
                    }
                    System.out.println("HEREEE");
                    for (Item d : r.getItems()){        //items
                        odg.addObjectToDisplay(d.getrepr(), d.getPosX() , d.getPosY());
                        System.out.println("HERE"+d.getrepr());
                    }
                }
                //

            }
        }

        odg.removeObjectToDisplay(posX,posY);
        posY += y;
        posX += x;

        odg.addObjectToDisplay(repr,posX,posY);

    }

    public void SetPosX(int posX) {
        this.posX = posX + room.getPosX();
    }

    public void SetPosY(int posY) {
        this.posY = posY + room.getPosY() + 2;
    }
    public void SetRoom(Room _room){
        room = _room;
    }

    public void setODG(ObjectDisplayGrid odg) {
        this.odg = odg;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Item getArmor() {
        return armor;
    }
    public Item getSword(){
        return sword;
    }

    @Override
    public String getName(){
        String name = "player";
        return name;
    }

    public boolean fight (){
        Random rand = new Random();
        boolean won = false;
        int totalDamageToMonster = 0;
        int totalDamageToPlayer = 0;
        boolean bdrop = false;

        while (!(this.getHp() <= 0 || monsterFight.getHp() <= 0)){
            System.out.println("player hp" + hp + "Monster hp" + monsterFight.getHp());
            int myAttack = rand.nextInt(sword == null ? maxHit + 1: maxHit +sword.getIntValue() + 1); // SWORD damage calculate here
            System.out.println("myAttack =" + myAttack);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            if (bdrop == false && this.CAction.getName().equalsIgnoreCase("droppack")){//===============================
                //drop first item, and display actionMessage associated with the CreatureAction
                dropPack();
                packUpdate();
                odg.addSentenceToDisplay("Info: "+CAction.getMessage(), 0,
                        dungeon.getTopHeight()+dungeon.getGameHeight() +1+dungeon.getBottomHeight()/2);
                bdrop = true;
            }

            int monsterAttack = rand.nextInt(monsterFight.maxHit + 1);
            monsterFight.setHp(monsterFight.getHp() - myAttack);
            totalDamageToMonster += myAttack;
            if(monsterFight.CAction != null){

            if(monsterFight.CAction.getName().equalsIgnoreCase("Teleport")){
                // if creature dies here, we kill it that's it.
                if(monsterFight.getHp() <= 0){
                    room.removeCreature(monsterFight);
                    dungeon.removeCreature(monsterFight);
                    odg.removeObjectToDisplay(monsterFight.getPosX(), monsterFight.getPosY());
                }else{ // we need to tp the creature to another location.
                    List<Integer> monsterToGo = odg.getRandomAccessiblePos();
                    // remove monster from current location.
                    // add monster to new location
                    // perhaps change room.
                    odg.removeObjectToDisplay(monsterFight.getPosX(), monsterFight.getPosY());
                    odg.addObjectToDisplay(monsterFight.getrepr(), monsterToGo.get(0), monsterToGo.get(1));
                    // above is on canvas, so no need to -2. every coordinate is absolute to odg.
                    monsterFight.SetPosX(monsterToGo.get(0));
                    monsterFight.SetPosY(monsterToGo.get(1) - 2);
                    monsterFight.SetRoom(dungeon.findCurrentRoom(monsterToGo.get(0), monsterToGo.get(1)));
                    
                    break;
                }
            }
        }
            if(monsterFight.getHp() > 0){
                this.setHp(this.getHp() - (monsterAttack - ((armor == null)?0:armor.getIntValue()))); //Armor reduce damage calculate here
                totalDamageToPlayer += (monsterAttack - ((armor == null)?0:armor.getIntValue()));
            }
        }

        System.out.println("player hp" + hp);
        if(this.getHp() > 0) {
            //System.out.println("player hp" + hp);
            won = true;
        }
        if(monsterFight.getHp() <= 0){
            room.removeCreature(monsterFight);
            dungeon.removeCreature(monsterFight);
            odg.removeObjectToDisplay(monsterFight.getPosX(), monsterFight.getPosY());
        }
        if(this.getHp() <= 0){
            room.removeCreature(this);
            dungeon.removeCreature(this);
            odg.removeObjectToDisplay(this.getPosX(), this.getPosY());
            // in step4 THERE WILL BE A DEATH MESSAGE REGARDING PLAYER

        }

        this.updateInfo(totalDamageToMonster, totalDamageToPlayer, won);
        return won;
    }


    public void updateInfo (int damagetoM, int damagetoP, boolean won){

        String status = (won)? "You Won":"You lose";
        odg.addSentenceToDisplay("Info : Damage to Monster = "+damagetoM+"; Damage to player = "+damagetoP+"" +
                "; "+status+"       ", 0, dungeon.getTopHeight()+dungeon.getGameHeight() +1+dungeon.getBottomHeight()/2);
        if (!won){
            this.updateIfInput();
        }
    }
    public boolean onMonster(char ch){
        int x=0 ,y = 0;
        if (ch == 'h'){
            x -= 1;
        }
        else if (ch == 'k'){
            y += 1;
        }
        else if (ch == 'j'){
            y -= 1;
        }
        else if (ch == 'l'){
            x += 1;
        }
        room = this.dungeon.findCurrentRoom(posX+x,posY+y);

        if (room == null){
            return false;
        }

        for (Creature mons: room.getCreatures()){
            if (mons.getPosX() == posX+x && mons.getPosY()==posY+y){
                monsterFight = (Monster)mons;
                return true;
            }
        }

        return false;
    }
    //======================================================================
    public void ChangeDisplayType()
    {
        this.repr = new Char('+');
        this.odg.addObjectToDisplay(repr,this.getPosX(),this.getPosY());
        this.odg.writeToTerminal(this.getPosX(),this.getPosY());
    }
}