package bullscows;

import java.util.Scanner;

public class UserInput {
    static Scanner scanner = new Scanner(System.in);

    public static String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
