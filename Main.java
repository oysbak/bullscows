package bullscows;

import static bullscows.UserInput.getInput;

public class Main {
    public static void main(String[] args) {
        SecretCode secretCode = new SecretCode();
        String code = secretCode.getSecretCode();
        // Play game
        System.out.println("Okay, let's start a game!");
        int turnCounter = 0;
        int bulls, cows;
        do {
            bulls = 0;
            cows = 0;
            turnCounter++;
            // Receive users input
            String s = String.format("Turn %s:", turnCounter);
            String userGuess = getInput(s);
            // Count Bulls and Cows
            for (int i = 0; i < Math.min(code.length(), userGuess.length()); i++) {
                if (code.charAt(i) == userGuess.charAt(i)) {
                    bulls++;
                } else {
                    if (code.indexOf(userGuess.charAt(i)) >= 0) {
                        cows++;
                    }
                }
            }
            // Print results
            System.out.print("Grade: ");
            if (bulls > 0 && cows > 0) {
                System.out.print(bulls + " bulls and " + cows + " cows");
            } else {
                if (bulls > 0) {
                    System.out.print(bulls + " bull(answer)");
                } else if (cows > 0) {
                    System.out.print(cows + " cow(answer)");
                } else {
                    System.out.print("None");
                }
            }
            System.out.println();
        } while (bulls != code.length());
        System.out.println("Congratulations! You guessed the secret code.");
    }
}