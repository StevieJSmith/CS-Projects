import java.util.Scanner;

public class Email {

    private String firstName;
    private String lastName;
    private String password;
    private int passwordLength = 8;
    private String department;
    private int inboxCapacity = 200;
    private String email;
    private String secondaryEmail;
    private String emailSuffix = ".ac.uk";

    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("---");
        System.out.println("-> Email for " + firstName + " " + lastName + " was created!");
        System.out.println("---");

        this.department = setDepartment(firstName, lastName);
        this.password = generatePassword(passwordLength);
        this.email = getEmail();
    }

    private String setDepartment(String firstName, String lastName) {
        System.out.println("enter department of Professor: ");
        System.out.println("---");
        System.out.println("1 - Pharmaceutical Science");
        System.out.println("2 - Computer Science");
        System.out.println("3 - Sports Science");
        System.out.println("0 - None");
        System.out.println("---");
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt(); // user input

        String profDepartment = switch(choice){
            case 1 -> {yield ("Pharmaceutical Science");}
            case 2 -> {yield "Computer Science";}
            case 3 -> {yield "Sports Science";}
            case 0 -> {yield "None";}
            default -> {yield "";}
        };
        System.out.println("---");
        System.out.println("-> Professor " + firstName + " " + lastName + " Assigned department: " + profDepartment);
        return profDepartment;
    }

    private String generatePassword(int length) { // create a password
        String passwordCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!?%$£*&"; // valid characters

        char[] password = new char[length]; // character array for storing password
        for(int i = 0; i < length; i++) { // loop through and randomly get index numbers for characters positions
            int randomNumber = (int)(Math.random() * passwordCharacters.length());
            password[i] = passwordCharacters.charAt(randomNumber);
        }

        String newPassword = String.valueOf(password);
        System.out.println("-> Professor's new password: " + newPassword);
        return newPassword;
    }

    private String getEmail() { // retrieve email
        String letter1 = String.valueOf(firstName.charAt(0)).toLowerCase(); // first letters
        String letter2 = String.valueOf(lastName.charAt(0)).toLowerCase();
        String numbers = "";
        for(int i = 0; i < 5; i++) { // 5 random numbers
            numbers += (int)(Math.random() * 9);
        }


        String newEmail = letter1 + letter2 + numbers + "@" + department.replace(" ", "") + emailSuffix;
        System.out.println("-> Professor's new Email: " + newEmail);
        System.out.println("---");
        return newEmail;

    }

    public void setInboxCapacity(int capacity) { // increase/decrease inbox capacity
        this.inboxCapacity = capacity;
    }

    public void setSecondaryEmail(String secondaryEmail) { // set an additional email
        this.secondaryEmail = secondaryEmail;
    }

    public void changePassword(String password) { // option to change password
        this.password = password;
    }

    public String getSecondaryEmail() { // retrieve Secondary email
        return secondaryEmail;
    }

    public String getEmailInfo() {
        return "Name: " + firstName + " " + lastName + "\n" +
                "Department: " + department + "\n" +
                "Email: " + email + "\n" +
                "Inbox Capacity: " + inboxCapacity + "mb";
    }

}
