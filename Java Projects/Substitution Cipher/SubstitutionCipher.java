import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class SubstitutionCipher {

    private Scanner scanner;
    private Random random;
    private char character;
    private char[] letters;
    private ArrayList<Character> list;
    private ArrayList<Character> encryptionList;

    public SubstitutionCipher() {
        scanner = new Scanner(System.in);
        random = new Random();
        character = ' ';
        list = new ArrayList();
        encryptionList = new ArrayList();

        newKey();
        getAnswer();

    }

    private void getAnswer() {
        while(true) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("*** *** *** *** ***  SubStitution Cipher  *** *** *** *** *** ***");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("N - new key | G - get key | E - encrypt | D - decrypt | Q - quit ");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Choose a option (N, G, E, D or Q):");
            char answer = Character.toUpperCase(scanner.nextLine().charAt(0));
            // get first letter of user response to determine what action to take!

            switch (answer) { // execute user desired action
                case 'N' -> newKey();
                case 'G' -> getKey();
                case 'E' -> encrypt();
                case 'D' -> decrypt();
                case 'Q' -> quit();
                default -> System.out.println("Please choose a valid option (N, G, E, D or Q)");
            }

        }

    }

    private void newKey() {
        character = ' '; // reset character
        list.clear(); // clear old key
        encryptionList.clear();

        for(int i = 32; i < 127; i++) { //  Ascii 32 = " " to 127 = ~
            list.add(Character.valueOf(character)); // list of keyboard letters/symbols
            character++;
        }
        encryptionList = new ArrayList(list); // copy array
        Collections.shuffle(encryptionList); // randomise array to determine the encryption algorithm!
        System.out.println("A new Key has been generated!*");
    }

    private void getKey() {
        System.out.println("Key: ");
        for (Character c : list) {
            System.out.print(c);
        }
        System.out.println();
        for (Character c : encryptionList) {
            System.out.print(c);
        }
        System.out.println();
    }

    private void encrypt() {
        System.out.println("Enter a message to encrypt: ");
        String message = scanner.nextLine();
        letters = message.toCharArray(); // convert string to CharArray (split each letter into characters!

        for(int i = 0; i < letters.length; i++) { // list to loop through character letters array
            for(int j = 0; j < list.size(); j++) { // list to loop through list, if match is found swap for encrypted list letter
                if(letters[i] == list.get(j)) {
                    letters[i] = encryptionList.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted Message:");
        for(char c : letters){
            System.out.print(c);
        }
        System.out.println();
    }

    private void decrypt() {
        System.out.println("Enter a message to Decrypt (hint: copy your Encrypted message if you have created one!): ");
        String message = scanner.nextLine();
        letters = message.toCharArray();

        for(int i = 0; i < letters.length; i++) {
            for(int j = 0; j < encryptionList.size(); j++) { // if match is found swap for original list letter
                if(letters[i] == encryptionList.get(j)) {
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Decrypted Message:");
        for(char c : letters){
            System.out.print(c);
        }
        System.out.println();
    }

    private void quit() {
        System.out.println("... Program closing down");
        System.exit(0);
    }
}
