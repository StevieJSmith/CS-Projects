public class Main {

    public static void main(String[] args) {

        Email emailNo1 = new Email("Steven", "Smith");
        emailNo1.setSecondaryEmail("sjsmith@gmail.com");
        emailNo1.setInboxCapacity(250);

        System.out.println(emailNo1.getEmailInfo());


    }

}
