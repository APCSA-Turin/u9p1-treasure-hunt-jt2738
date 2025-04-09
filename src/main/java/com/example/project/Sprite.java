package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x; //sets x and y instance varis
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    //setter methods
    public void setX(int newX){
        x= newX;
    }
    public void setY(int newY){
        y=newY;
    }

    //getter methods 
    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + getX() + ","+ getY() + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "["+ (size-1-getY()) + "][" + (getX()) + "]" ;
    }
    

    public void move(String direction) { //you can leave this empty
    
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
