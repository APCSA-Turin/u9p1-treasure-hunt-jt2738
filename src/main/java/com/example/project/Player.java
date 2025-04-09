package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{ //instance varis
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y); //calls to super to initialize a and y
        treasureCount=0;
        numLives=2;
        win = false;
    }


    //getter methods
    public int getTreasureCount(){ 
        return treasureCount;
    }
    public int getLives(){
        return numLives;
    }
    public boolean getWin(){
        return win;
    }

    @Override
    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
    return "Player:"+super.getRowCol(size);
    }
    

    @Override
    public String getCoords(){
        return "Player:"+ super.getCoords() ;
    }

    public void setLives(int num){
        numLives= num;
    }
  
    // size must be a variable smh
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        if ((direction.equals("w")) ) {
            setY(getY()+1); //add 1 to Y val to change y COOR (up)
        }
        if ((direction.equals("a"))) {
            setX(getX()-1); //subtract 1 to x to change x COOR (left)
            
        }
        if ((direction.equals("s"))) {
            setY(getY()-1); //subtract 1 to Y val to change y COOR (down)
            
        }
        if ((direction.equals("d"))) {
            setX(getX()+1); //add 1 to x to change x COOR (right)
            
        }
        
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
        //numTreasures is the total treasures at the beginning of the game
        if(obj instanceof Enemy){ //checks if obj in place is an enemy
            numLives--; //if enemy take away a life
            if(numLives <= 0){ //if num lives less than or == to 0, then win ==false, making person lose
                win=false;
            }
        }else if (!(obj instanceof Dot)){ 
            if (obj instanceof Trophy && treasureCount<numTreasures) { //checks if player has met tropy, but does not have all treasure
            System.out.println("Need More Tokens!"); //if not enough tokens, program tells player to collect more
            }else if (obj instanceof Trophy) { //if enough tokens, player wins
            win=true; 
            }else{
            System.out.println("Instance of treasure found");  
            treasureCount++; //increases treasure count upon player touching the treasure
            }
    }
    }

    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) {
            if (getY()<size-1) { 
                return true;
            }else{
                return false;
            }
        }
        if (direction.equals("a")) {
            if (getX()-1>=0) {
                return true;
            }else{
                return false;
            }
        }
        if (direction.equals("s")){
            if (getY()-1>=0) {
                return true;
            }else{
                return false;
            }
        }
        if (direction.equals("d")) {
            if (getX()<size-1) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}



