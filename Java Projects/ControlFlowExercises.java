import java.util.Scanner;

public class ControlFlowExercises {
    public static void main(String[] args) {
        // 1
        System.out.println("---");
        System.out.println("1.");
        System.out.println("---");
        printNumberInWord(1);
        printNumberInWord(9);
        printNumberInWord(99);

        // 2
        System.out.println("---");
        System.out.println("2.");
        System.out.println("---");
        System.out.println(getDaysInMonth(1, 1400) + " days");
        System.out.println(getDaysInMonth(2, 2020) + " days");
        System.out.println(getDaysInMonth(2, -1000));

        // 3
        System.out.println("---");
        System.out.println("3.");
        System.out.println("---");
        System.out.println(sumOdd(1, 10));

        // 4
        System.out.println("---");
        System.out.println("4.");
        System.out.println("---");
        System.out.println(isPalindrome(-222));

        // 5
        System.out.println("---");
        System.out.println("5.");
        System.out.println("---");
        System.out.println(sumFirstAndLastDigit(1001));
        System.out.println(sumFirstAndLastDigit(5));

        // 6
        System.out.println("---");
        System.out.println("6.");
        System.out.println("---");
        System.out.println(getEvenDigitSum(12345678));

        // 7
        System.out.println("---");
        System.out.println("7.");
        System.out.println("---");
        System.out.println(hasSharedDigit(91, 11));

        // 8
        System.out.println("---");
        System.out.println("8.");
        System.out.println("---");
        System.out.println(hasSameLastDigit(11, 45, 95));

        // 9
        System.out.println("---");
        System.out.println("9.");
        System.out.println("---");
        printFactors(10);

        // 10
        System.out.println("---");
        System.out.println("10.");
        System.out.println("---");
        System.out.println(getGreatestCommonDivisor(20, 5));

        // 11
        System.out.println("---");
        System.out.println("11.");
        System.out.println("---");
        System.out.println(isPerfectNumber(100));

        // 12
        System.out.println("---");
        System.out.println("12.");
        System.out.println("---");
        numberToWords(12340);

        // 13
        System.out.println("---");
        System.out.println("13.");
        System.out.println("---");
        System.out.println(canPack(2, 2, 15));
        System.out.println(canPack(1, 2, 7));


        // 14
        System.out.println("---");
        System.out.println("14.");
        System.out.println("---");
        System.out.println(getLargestPrime(100));

        // 15
        System.out.println("---");
        System.out.println("15.");
        System.out.println("---");
        printSquareStar(5);

        // 16
        System.out.println("---");
        System.out.println("16.");
        System.out.println("---");
        inputThenPrintSumAndAverage();

        // 17
        System.out.println("---");
        System.out.println("17.");
        System.out.println("---");
        System.out.println(getBucketCount(3.4, 2.1, 1.5, 2));
        System.out.println(getBucketCount(7.25, 4.3, 2.35));
        System.out.println(getBucketCount(6.26, 2.2));
    }

    public static void printNumberInWord(int number){ // 1
        String word = "";
        switch(number){
            case 0:
                word = "ZERO";
                break;
            case 1:
                word = "ONE";
                break;
            case 2:
                word = "TWO";
                break;
            case 3:
                word = "THREE";
                break;
            case 4:
                word = "FOUR";
                break;
            case 5:
                word = "FIVE";
                break;
            case 6:
                word = "SIX";
                break;
            case 7:
                word = "SEVEN";
                break;
            case 8:
                word = "EIGHT";
                break;
            case 9:
                word = "NINE";
                break;
            default:
                word = "OTHER";
                break;
        }
        System.out.println(word);
    }

    public static boolean isLeapYear(int year){ // 2
        if(year >= 1 && year <= 9999){
            if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
                return true;
            }
        }
        return false;
    }

    public static int getDaysInMonth(int month, int year){ // 2
        if(month < 1 || month > 12 || year < 1 || year > 9999){
            return -1;
        }else{ // 1 = January -> 12 = December
            boolean isLeap = isLeapYear(year);
            int days = 0;
            switch(month){
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    days = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    days = 30;
                    break;
                case 2:
                    days = 28;
                    break;
                default:
                    days = -1;
                    break;
            };
            if (isLeap && days == 28){
                days = 29;
            }
            return days;
        }
    }

    public static boolean isOdd(int number){ // 3
        if(number > 0 && number % 2 != 0){
            return true;
        }
        return false;
    }

    public static int sumOdd(int start, int end){ // 3
        int sum = 0;
        if(end >= start && start > 0) {
            for (int i = start; i <= end; i++) {
                if (isOdd(i)) {
                    sum += i;
                }
            }
            return sum;
        }
        return -1;
    }

    public static boolean isPalindrome(int number){ // 4
        int num = number;
        int reverse = 0;

        while(num != 0){
            reverse *= 10;
            reverse += (num%10);
            num /= 10;
        }

        if(reverse == number || (-reverse == number)){
            return true;
        }
        return false;

    }

    public static int sumFirstAndLastDigit(int number){ // 5
        int num = number;
        int first = 0;
        int last = 0;

        if(number < 0){
            return -1;
        }


        boolean isFirst = false;
        while(!isFirst){
            if(num < 10){
                first = num;
                break;
            }
            first = (num % 10);
            num /= 10;
            isFirst = true;
        }
        while(num > 9){
            num /= 10;
        }
        last = num;
        return (first + last);
    }

    public static int getEvenDigitSum(int number){ // 6
        int num = number;
        int total = 0;
        if(number < 0){
            return -1;
        }

        while(num != 0){
            if((num % 10) % 2 == 0){
                total += (num % 10);
            }
            num /= 10;
        }
        return total;
    }

    public static boolean hasSharedDigit(int num1, int num2){ // 7

        if(num1 < 10 || num1 > 99 || num2 < 10 || num2 > 99){
            return false;
        }

        int num1First = 0, num1Last = 0, num2First = 0, num2Last = 0;

        while(num1 > 9){
            num1First = (num1 % 10);
            num2First = (num2 % 10);
            num1 /= 10;
            num2 /= 10;
        }

        num1Last = num1;
        num2Last = num2;

        if(num1First == num2First || num1First == num2Last || num1Last == num2Last){
            return true;
        }
        return false;
    }

    public static boolean hasSameLastDigit(int num1, int num2, int num3){ // 8
        if (!isValid(num1) || !isValid(num2) || !isValid(num3)){
            return false;
        }
        int num1Last = (num1 % 10);
        int num2Last = (num2 % 10);
        int num3Last = (num3 % 10);

        if(num1Last == num2Last || num1Last == num3Last || num2Last == num3Last){
            return true;
        }
        return false;

    }

    public static boolean isValid(int number){ // 8
        if(number <= 1000 && number >= 10){
            return true;
        }
        return false;
    }

    public static void printFactors(int number){ // 9
        if(number < 1){
            System.out.println("Invalid Value");
        }
        for(int i = 1; i <= number; i++){
            if(number % i == 0){
                System.out.println(i);
            }
        }
    }

    public static int getGreatestCommonDivisor(int first, int second){ // 10
        if(first < 10 || second < 10){
            return -1;
        }

        int greatestCommonDivisor = 0;
        int i = 0;

        if(first > second){
            i = second;
        }else{
            i = first;
        }

        while (i != 0){
            if(first % i == 0 && second % i == 0){
                greatestCommonDivisor = i;
                break;
            }
            i--;
        }
        return greatestCommonDivisor;
    }

    public static boolean isPerfectNumber(int number){ // 11
        if(number < 1){
            return false;
        }

        int total = 0;
        for(int i = 1; i < number; i++){
            if(number % i == 0){
                total += i;
            }
        }
        return (number == total);
    }

    public static void numberToWords(int number){ // 12
        int numberReverse = reverse(number);
        int count = getDigitCount(numberReverse);
        int currentNum = 0;
        if(number < 0){
            System.out.println("Invalid Value");
        }
        String word = "";
        do{
            currentNum = (numberReverse % 10);
            numberReverse /= 10;
            switch(currentNum){
                case 0:
                    word = "Zero";
                    break;
                case 1:
                    word = "One";
                    break;
                case 2:
                    word = "Two";
                    break;
                case 3:
                    word = "Three";
                    break;
                case 4:
                    word = "Four";
                    break;
                case 5:
                    word = "Five";
                    break;
                case 6:
                    word = "Six";
                    break;
                case 7:
                    word = "Seven";
                    break;
                case 8:
                    word = "Eight";
                    break;
                case 9:
                    word = "Nine";
                    break;
                default:
                    break;
            }
            System.out.println(word);

        }while(numberReverse != 0);

        while(count < getDigitCount(number)){
            System.out.println("Zero");
            count++;
        }
    }

    public static int reverse(int number){ // 12
        String numberString = "";

        if(number < 0){
            numberString += "-";
            number = number * -1;
        }

        do{
            numberString += (number % 10);
            number /= 10;
        }while(number != 0);
        return Integer.parseInt(numberString);
    }

    public static int getDigitCount(int number){ // 12
        int count = 0;
        if(number < 0){
            return -1;
        }
        do{
            count++;
            number /= 10;
        }while(number != 0);

        return count;
    }

    public static boolean canPack(int bigCount, int smallCount, int goal){ // 13
        if(bigCount < 0 || smallCount < 0 || goal < 0){
            return false;
        }
        // big = 5, small = 1
        if(goal != 0){
            while(bigCount > 0 && goal >= 5){
                goal -= 5;
                bigCount -= 1;
            }
            while(smallCount > 0 && goal >= 1){
                goal -= 1;
                smallCount -= 1;
            }
        }

        if (goal == 0){
            return true;
        }
        return false;
    }

    public static int getLargestPrime(int number){ // 14
        if(number <= 1 ){
            return -1;
        }

        for(int i = number-1; i > 1; i--){
            if(number % i == 0){
                int j = 2;
                int count = 0;
                while(j < i){
                    if(i % j  == 0){
                        count++;
                        break;
                    }
                    j++;
                }
                if(count == 0){
                    return i;
                }
            }
        }
        return number;
    }

    public static void printSquareStar(int number){ // 15
        if(number < 5){
            System.out.println("Invalid Value");
        }else {

            for (int row = 1; row <= number; row++) {
                String pattern = "";
                for (int column = 1; column <= number; column++) {

                    if (row == 1 || column == 1 || row == number || column == number) {
                        pattern += "*";
                    } else if (row == column) {
                        pattern += "*";
                    } else if (column == (number - row + 1)) {
                        pattern += "*";
                    } else {
                        pattern += " ";
                    }
                }
                System.out.println(pattern);
            }
        }
    }

    public static void inputThenPrintSumAndAverage(){ // 16
        Scanner scan = new Scanner(System.in);
        int sum = 0;
        double count = 0;
        while(true) {
            try {
                System.out.println("PLease enter a whole number: ");
                int userInput = Integer.parseInt(scan.nextLine());
                sum += userInput;
                count++;
            } catch (NumberFormatException error) {
                break;
            }
        }
        long avg = Math.round(sum/count);
        System.out.println("SUM = " + sum + " AVG = " + avg);
    }

    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets){ // 17
        if(width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0){
            return -1;
        }
        int bucketCount = 0;
        double wallArea = width * height;
        double homePaintArea = areaPerBucket * extraBuckets;
        wallArea -= homePaintArea;
        while(wallArea > 0){
            wallArea -= areaPerBucket;
            bucketCount++;
        }
        return bucketCount;

    }

    public static int getBucketCount(double width, double height, double areaPerBucket){ // 17
        if(width <= 0 || height <= 0 || areaPerBucket <= 0){
            return -1;
        }

        int bucketCount = 0;
        double wallArea = width * height;
        while(wallArea > 0){
            wallArea -= areaPerBucket;
            bucketCount++;
        }
        return bucketCount;

    }

    public static int getBucketCount(double area, double areaPerBucket){ // 17
        if(area <= 0 || areaPerBucket <= 0){
            return -1;
        }

        int bucketCount = 0;
        while(area > 0){
            area -= areaPerBucket;
            bucketCount++;
        }
        return bucketCount;
    }
}
