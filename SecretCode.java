package bullscows;

import java.util.ArrayList;
import java.util.Random;

public class SecretCode {
    public static final int MAX_LENGTH = 36;
    private int length;
    private int range;
    private StringBuilder secretCode;

    public SecretCode() {
        String userInput = "";
        try {
            userInput = UserInput.getInput("Input the length of the secret code:");
            length = Integer.parseInt(userInput);
            if (length < 1 || length > 36) {
                System.out.println("Error: Length of code must be greater than zero.");
                System.exit(0);
            }
            userInput = UserInput.getInput("Input the number of possible symbols in the code:");
            range = Integer.parseInt(userInput);
        } catch (Exception e) {
            System.out.printf("Error: \"%s\" isn't a valid number.%n", userInput);
            System.exit(0);
        }
        verifyLengthAndRange();
        constructCode();
        System.out.println(getStupidMessage());
    }

    private void verifyLengthAndRange() {
        if (length > MAX_LENGTH) {
            System.out.println(String.format("Error: can't generate a secret number with a length" +
                    " of %s because there aren't enough unique digits.%n", length));
            System.exit(0);
        }
        if (length > range) {
            System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
            System.exit(0);
        }
        if (range > MAX_LENGTH) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
    }

    private void constructCode() {
        ArrayList<Character> characters = new ArrayList<>();
        // Numbers
        for (int i = 48; i <= 57; i++) {
            characters.add((char) i);
        }
        // Characters
        for (int i = 97; i <= 122; i++) {
            characters.add((char) i);
        }
        // Remove characters that are outside of the range
        characters.subList(range, characters.size()).clear();
        Random random = new Random();
        secretCode = new StringBuilder();
        // Pick random characters until all slots in the secretcode are filled
        for (int i = 0; i < length; i++) {
            secretCode.append(characters.remove(random.nextInt(0, characters.size())));
        }
    }

    public String getStupidMessage() {
        StringBuilder stupidMessage = new StringBuilder();
        stupidMessage.append("The secret is prepared: ");
        for (int i = 0; i < length; i++) {
            stupidMessage.append('*');
        }
        if (range < 10) {
            stupidMessage.append(String.format(" (0-%s).", (char) (range + 47)));
        } else {
            stupidMessage.append(String.format(" (0-9, a-%s).", (char) (range + 86)));
        }
        return stupidMessage.toString();
    }

    public String getSecretCode() {
        return secretCode.toString();
    }
}
