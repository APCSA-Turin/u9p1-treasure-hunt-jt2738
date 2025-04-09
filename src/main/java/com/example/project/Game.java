package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 
    private String lvl;

    public Game(int size){ //the constructor should call initialize() and play()
        this.size=size; //sets parameter size as this size
        this.grid= new Grid(size); //makes a grid given the size
    }

    

    
    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        String direction="";
        while(!(direction.equals("q"))){ //checks input from user
            System.out.println("Enter the direction: ");
            direction= scanner.nextLine();
            Sprite[][] gridArr= grid.getGrid();
            Object object = new Dot(size - 1 - player.getY(),player.getX());
            if (direction.equals("w")) { //checks if player wants to move up
                object = gridArr[size-2-player.getY()][player.getX()];
            }else if (direction.equals("a")) { //checks if player wants to move to the left
                object = gridArr[size - 1 - player.getY()][player.getX()-1];
            }else if (direction.equals("d")) {//checks if player wants to move to the right 
                object = gridArr[size - 1 - player.getY()][player.getX()+1];
            }else if (direction.equals("s")) {//checks if the player wants to move down
                object = gridArr[size - player.getY()][player.getX()];
            }

            player.interact(size, direction, treasures.length, object);
            player.move(direction);
            grid.placeSprite(player, direction);

            if (player.getLives()==0) { //after each move, checks if lives equal 0...
                grid.gameover();
                break;
            }
            if (player.getWin()==true) {//...and also checks if player won w/ get win method
                grid.win();
                break;
            }
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Lives: " + player.getLives());
            System.out.println("Treasure Collected: " + player.getTreasureCount());
            grid.display(); //displays grid after every turn to show state of grid
            // try {
            //     Thread.sleep(100); // Wait for 1/10 seconds
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            // clearScreen(); // Clear the screen at the beggining of the while loop
            }
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Press a to play again, press q to quit");
            String playAgain="";
            playAgain= scanner.nextLine();
            if (playAgain.equals("a")) {
                initialize();
                play();
            }
            if (playAgain.equals("q")) {
                System.out.println("Thanks for playing!");
            }
            
     
    }

    public void initialize(){
        Scanner scanner1= new Scanner(System.in);
        System.out.println("Please type in a valid level: easy, medium, or hard?");
        lvl= scanner1.nextLine();
        player= new Player(0, 0);
        grid.placeSprite(player);
        if (lvl.equals("easy")) { //easy lvl: 1 enemy, 1 treasure
            Enemy enemy1=new Enemy(3, 4);
            grid.placeSprite(enemy1);
            
            Treasure treasure1= new Treasure(8, 5);
            treasures= new Treasure[] {treasure1};
            grid.placeSprite(treasure1);
            
            trophy= new Trophy(0, 9);
            grid.placeSprite(trophy);
        }else if (lvl.equals("medium")) { //medium lvl: 2 enemies, 3 treasures
            
            Enemy enemy1= new Enemy(7, 1);
            grid.placeSprite(enemy1);
            Enemy enemy2= new Enemy(5, 7);
            grid.placeSprite(enemy2);
            enemies= new Enemy[]{enemy1,enemy2};
            Treasure treasure1= new Treasure(4, 7);
            
            grid.placeSprite(treasure1);
            Treasure treasure2= new Treasure(8, 5);
            grid.placeSprite(treasure2);
            Treasure treasure3= new Treasure(9, 8);
            grid.placeSprite(treasure3);
            treasures= new Treasure[] {treasure1, treasure2, treasure3};
            trophy= new Trophy(0, 9);
            grid.placeSprite(trophy);
        }else{
            //hard lvl: 6 enemies, 1 treasure, num lives=1
            player.setLives(1);
            
            Enemy enemy1= new Enemy(6, 1); 
            grid.placeSprite(enemy1);
            Enemy enemy2= new Enemy(0, 7);
            grid.placeSprite(enemy2);
            Enemy enemy3= new Enemy(7, 6);
            grid.placeSprite(enemy3);
            Enemy enemy4= new Enemy(4, 3);
            grid.placeSprite(enemy4);
            Enemy enemy5= new Enemy(2, 5);
            grid.placeSprite(enemy5);
            
            Treasure treasure1= new Treasure(7, 7);
            grid.placeSprite(treasure1);
            treasures= new Treasure[] {treasure1};

            trophy= new Trophy(4, 5);
            grid.placeSprite(trophy);

        }
        grid.display();
    }

    public static void main(String[] args) {
        Game game= new Game(10);
        game.initialize();
        game.play();
    }
}