import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner((System.in));

        displayMenu();
        int choice;
        choice = Integer.parseInt((scanner.nextLine()));

        switch (choice) {
            case 1:
                System.out.println("MySQL");
                MySQLCRUD.sqlMenu();
                Scanner scanner1 = new Scanner((System.in));
                int choice1;
                choice1 = Integer.parseInt((scanner1.nextLine()));



                break;

            case 2:
                System.out.println("MongoDB");
                MongoCRUD.mongoMenu();
                Scanner scanner2 = new Scanner((System.in));
                int choice2;
                choice2= Integer.parseInt((scanner2.nextLine()));

        }
    }


    private static void displayMenu() {

        System.out.println("1. MySQL");
        System.out.println("2. MongoDB");
        System.out.println("3. Redis");
        System.out.println("4. Blockchain");
        System.out.println("Which option do you wish to perform? ");

    }
}