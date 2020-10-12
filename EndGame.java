class EndGame extends CreatureAction{
    public EndGame(String _name, Creature owner){
        super(owner);
        System.out.println(""+owner+" ending game");
    }
}