package games;
import java.lang.Math;
import java.util.Scanner;

public class PlayerStatus {
    Scanner sc = new Scanner(System.in);

    private final String nickname;
    private int score;
    private int lives;
    private int health;
    private String weaponInHand;
    private double positionX;
    private double positionY;
    private static String gameName;

    static void setGameName(String nameGame){
        gameName = nameGame;
    }
    public static String getGameName(){
        return gameName;
    }
    //getter setter score + lives + health
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
    public void setLives(int lives){
        this.lives = lives;
    }
    public int getLives(){
        return this.lives;
    }
    public boolean isAlive(){
        return this.lives > 0;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
    // Constructori pentru clasa PlayerStatus.........................................................1
    public PlayerStatus(String nickname, String weaponInHand, int health, int score, double positionX, double positionY){
        this.nickname = nickname;
        this.weaponInHand = weaponInHand;
        this.health = health;
        this.score = score;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public PlayerStatus(String nickname){
        this.nickname = nickname;
    }
    public PlayerStatus(String nickname, int lives){
        this(nickname);
        this.lives = lives;
    }
    public PlayerStatus(String nickname, int lives, int score){
        this(nickname, lives);
        this.score = score;
    }
    public PlayerStatus(String nickname, int lives, int score, String weaponInHand){
        this(nickname, lives, score);
        this.weaponInHand = weaponInHand;
    }
    //default are 25% hp si 2 extra vieti
    public PlayerStatus(){
        this.nickname = "Player 1";
        this.lives = 2;
    }
    public String getNickname(){

        return this.nickname; //nu e nevoie de THIS. e o problema "this" in getter?
    }

    public void showAllStats(){
        System.out.println("Player   : " + this.nickname + "\nHealth   : " + this.health + "\nLives    : " + this.lives + "\nScore    : " + this.score + "\nEquipped : " + this.weaponInHand);
    }

    public String getWeaponInHand(PlayerStatus opponent){
        return opponent.weaponInHand;
    }


    public boolean setWeaponInHand(String weaponInHand){//.......................................
        switch (weaponInHand) {
            case "knife":
                if (this.score >= 1000) {
                    this.weaponInHand = "knife";
                    setScore(getScore() - 1000);
                    return true;
                } else {
                    System.out.println("Not enough funds for a knife");
                    return false;
                }
            case "ak47":
                if (this.score >= 20000) {
                    this.weaponInHand = "ak47";
                    setScore(getScore() - 20000);
                    return true;
                } else {
                    System.out.println("Not enough funds for an ak47");
                    return false;
                }
            case "sniper":
                if (this.score >= 10000) {
                    this.weaponInHand = "sniper";
                    setScore(getScore() - 10000);
                    return true;
                } else {
                    System.out.println("Not enough funds for a sniper");
                    return false;
                }
            default:
                System.out.println("No " + weaponInHand + " in store");
                return false;
        }
    }

    public void setPosition(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public double getPositionX(PlayerStatus opponent){
        return opponent.positionX;
    }
    public double getPositionY(PlayerStatus opponent){
        return opponent.positionY;
    }
    public void printPosition(){
        System.out.println("Current position: OX = " + getPositionX(this) + "m   OY = " + getPositionY(this) + "m");
    }

    public void changePosition(double positionX, double positionY){//.................................
        while(positionX > 3000 || positionX < 0 || positionY > 3000 || positionY < 0){
            System.out.println("Your choice exceeds the map of 3000x3000 m\n\tTry other values");
            positionX = sc.nextInt();
            positionY = sc.nextInt();
            this.positionX = positionX;
            this.positionY = positionY;
        }
        setPosition(positionX, positionY);
        System.out.println("Success!\nCurrent position: OX = " + getPositionX(this) + "m   OY = " + getPositionY(this) + "m");
    }
    public double getRelativeDistance(PlayerStatus opponent){
        return Math.sqrt(Math.pow((this.positionX - opponent.positionX), 2) + Math.pow((this.positionY - opponent.positionY), 2));
    }
    public double getWinProbability(PlayerStatus opponent){
        return (3d * opponent.health + opponent.score / 1000d) / 4;
    }
    public void printComparison(PlayerStatus opponent){
        System.out.println("         " + this.nickname + "    " + opponent.nickname);
        System.out.println("Weapon   " + this.weaponInHand + "     " + opponent.weaponInHand);
        System.out.println("Score    " + this.score + "    " + opponent.score);
        System.out.print("Distance ");
        System.out.format("%.2f", getRelativeDistance(opponent));
        System.out.println(" m\n");
    }
    public boolean shouldAttackOpponent(PlayerStatus opponent){
        String combinedStrings = this.weaponInHand + opponent.weaponInHand;
            if(getWeaponInHand(this).equals(getWeaponInHand(opponent))){////////////////////
                System.out.println(this.nickname + "'s win chance: " + getWinProbability(this) + "    " + opponent.nickname + "'s win chance: " + getWinProbability(opponent));
                return getWinProbability(this) > getWinProbability((opponent)); ///////////////////////////////

            }else if(getRelativeDistance(opponent) > 1000){
                return combinedStrings.equals("sniperak47") || combinedStrings.equals("sniperknife") || combinedStrings.equals("ak47knife");
            }else {
                return combinedStrings.equals("ak47sniper") || combinedStrings.equals("ak47knife") || combinedStrings.equals("sniperknife");
            }
    }

    public void searchArtifacts(int artifactCode){//.......................................
        if(ImportClass.isPerfect(artifactCode)){
            setScore(getScore() + 5000);
            setLives(getLives() + 1);
            setHealth(100);
        }else if(ImportClass.isPrime(artifactCode)){
            setScore(getScore() + 1000);
            setLives(getLives() + 2);
            if(this.health + 25 > 100){
                setHealth(100);
            }else{
                setHealth(getHealth() + 25);
            }
        }else if(ImportClass.isOddDigitSum(artifactCode)){
            setScore(getScore() - 3000);
            if(this.health - 25 <= 0){
                setHealth(100);
                setLives(getLives() - 1);
            }else{
                setHealth(getHealth() - 25);
            }
        }else if(artifactCode > 0 && artifactCode < 999){
            setScore(getScore() + artifactCode);
        }else{
            System.out.println("Invalid Number");
        }
    }
    public int menuOption(PlayerStatus opponent){
        int menuEntry = 0;
        do{
            if(!isAlive()){
                System.out.println("You have zero lifes left\n\tGAME OVER");
                break;
            }
            System.out.println("Choose a menu entry");
            menuEntry = sc.nextInt();
            if (menuEntry == 1){
                ImportClass.printMenu();
            }else if(menuEntry == 2){
                System.out.println("Enter new values for oX and oY");
                changePosition(sc.nextDouble(), sc.nextDouble());
            }else if(menuEntry == 3){
                System.out.println("Insert an artifact Code :");
                searchArtifacts(sc.nextInt());
            }else if(menuEntry == 4){
                sc.nextLine();
                System.out.println("Enter your preferred weapon type\nChoose from \"knife\", \"ak47\" and \"sniper\"");
                if(setWeaponInHand(sc.nextLine())){
                    System.out.println("Weapon purchase complete");
                }else{
                    System.out.println("Transaction rejected");
                }
            }else if(menuEntry == 5){
                printPosition();
                System.out.print("Distance to target: ");
                System.out.format("%.2f", getRelativeDistance(opponent));
                System.out.println(" m\n");
                showMap(opponent);
            }else if(menuEntry == 6){
                showAllStats();
            }else if(menuEntry == 7){
                printComparison(opponent);
                if(shouldAttackOpponent(opponent)){
                    System.out.println("Attack target");
                }else{
                    System.out.println("No shame in bravely running away");
                }
            }else if(menuEntry == 8){
                if(shouldAttackOpponent(opponent)){
                    System.out.println("VICTORY");
                }else{
                    System.out.println("DEFEAT");
                }
                break;
            }else if (menuEntry >= 10){
                System.out.println("Invalild Option");
            }
        } while(menuEntry != 9);
        return menuEntry;
    }

    public void showMap(PlayerStatus opponent){
        for(int i = 0; i <= 3000; i += 300){
            for(int j = 0; j <= 3000; j += 300){
                if((getPositionX(this) > j - 300 && getPositionY(this) > i - 300) && (getPositionX(this) <= j) && getPositionY(this) <= i){
                    System.out.print("(ง •̀_•́)ง");
                }else if((getPositionX(opponent) > j - 300 && getPositionY(opponent) > i - 300) && (getPositionX(opponent) <= j) && getPositionY(opponent) <= i){
                    System.out.print("ผ(•̀_•́ผ)");
                }else{
                    System.out.print(".      ");
                }
            }
            System.out.println("\n");
        }
    }
}
