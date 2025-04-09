package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;
    
    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size=size;
        grid= new Sprite[size][size];
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                Dot dot = new Dot(i, j); //inserts a dot at each indv "cell" or [row][col]
                grid[i][j]=dot;
            }
        }

    }

    public void setSize(int newSize){
        size=newSize;
        
    }

 
    public Sprite[][] getGrid(){return grid;}

    public void placeSprite(Sprite s){ //place sprite in new spot
        int x= s.getX(); //gets current x and y or sprite
        int y= s.getY();
        grid[size-y-1][x]=s; 

    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction. THIS CHANGES THE ACTUAL [ROW][COL] OF THE SPRITE.
        if (direction.equals("w")) { 
            if (s.getY()!= 0) { 
                Dot dot= new Dot(s.getX(), s.getY()-1); 
                placeSprite(dot);
                placeSprite(s);
            }
        }
        if (direction.equals("a")) { 
            if (s.getX()>= 0) {
                Dot dot= new Dot(s.getX()+1, s.getY());
                placeSprite(dot);
                placeSprite(s);
                
            }
        }
        if (direction.equals("s")) {
            if (s.getY()!=size-1) {
                Dot dot= new Dot(s.getX(), s.getY()+1);
                placeSprite(dot);
                placeSprite(s);
            }
        }
        if (direction.equals("d")) {
            if (s.getX()!=size+1) {
                Dot dot= new Dot(s.getX()-1, s.getY());
                placeSprite(dot);
                placeSprite(s);
            }
        }
    }


    public void display() { //print out the current grid to the screen 
        for(Sprite[] cell : grid){ //iterates through row 
            for(Sprite indv: cell){ //then iterates through col
                if (indv instanceof Dot){ //inserts eomojis for each sprite depending on what class they are
                    System.out.print("[]");
                }
                if (indv instanceof Player) {
                    System.out.print("👻");
                }
                if (indv instanceof Enemy) {
                    System.out.print("🧌 ");
                }
                if (indv instanceof Trophy ) {
                    System.out.print("🏆");
                }
                if (indv instanceof Treasure &&!(indv instanceof Trophy)) {
                    System.out.print("🪙 ");
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ 
        for(Sprite[] cell : grid){ //iterates through row 
            for(Sprite indv: cell){ //then iterates through col
                System.out.print("🥀");
            }
            System.out.println();
        }
        System.out.println("You Lose");
        
    }

    public void win(){ //use this method to display a win 
        for(Sprite[] cell : grid){ //iterates through row 
            for(Sprite indv: cell){ //then iterates through col
                System.out.print("🌸");
            }
            System.out.println();
        }
        System.out.println("You Win");
     
    }

    

}