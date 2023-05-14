package games;


public class ImportClass {

    public static void printMenu(){
        System.out.println("1. Menu options\n2. Change position\n3. Explore artifacts\n4. Buy and upgrade weapons\n5. Open Map\n6. Show stats\n7. Should attack opponent\n8. Attack opponent\n9. Exit game");
    }
    public static boolean isPerfect(int number){
        int sumDigits = 1;
        for(int i = 2; i <= number / 2; i++){
            if(number % i == 0){
                sumDigits += i;
            }
        }
        return sumDigits == number;
    }
    public static boolean isPrime(int number){
        if(number < 2){
            return false;
        }
        for(int i = 2; i <= number / 2; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    public static boolean isOddDigitSum(int number){
        int cloneCode = number;
        int digitSum = 0;
        while (cloneCode != 0){
            digitSum += (cloneCode % 10);
            cloneCode /= 10;
        }
        return (digitSum % 3 == 0 && number % 2 == 0);
    }



}
