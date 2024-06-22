import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    // Define character sets
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    // SecureRandom for cryptographically secure random numbers
    private static final SecureRandom random = new SecureRandom();

    // Method to generate a random password
    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeDigits, boolean includeSpecialCharacters) {
        StringBuilder characterSet = new StringBuilder();
        StringBuilder password = new StringBuilder(length);

        // Append selected character sets
        if (includeUppercase) {
            characterSet.append(UPPERCASE);
            password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        if (includeLowercase) {
            characterSet.append(LOWERCASE);
            password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        if (includeDigits) {
            characterSet.append(DIGITS);
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        if (includeSpecialCharacters) {
            characterSet.append(SPECIAL_CHARACTERS);
            password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
        }

        // Check if at least one character set is selected
        if (characterSet.length() == 0) {
            throw new IllegalArgumentException("At least one character set must be selected");
        }

        // Fill the remaining length with random characters from the selected sets
        for (int i = password.length(); i < length; i++) {
            password.append(characterSet.charAt(random.nextInt(characterSet.length())));
        }

        // Shuffle the characters to ensure randomness
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }

        return new String(passwordArray);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the desired password length: ");
            int length = scanner.nextInt();

            System.out.print("Include uppercase letters? (yes/no): ");
            boolean includeUppercase = scanner.next().equalsIgnoreCase("yes");

            System.out.print("Include lowercase letters? (yes/no): ");
            boolean includeLowercase = scanner.next().equalsIgnoreCase("yes");

            System.out.print("Include digits? (yes/no): ");
            boolean includeDigits = scanner.next().equalsIgnoreCase("yes");

            System.out.print("Include special characters? (yes/no): ");
            boolean includeSpecialCharacters = scanner.next().equalsIgnoreCase("yes");

            try {
                String password = generatePassword(length, includeUppercase, includeLowercase, includeDigits, includeSpecialCharacters);
                System.out.println("Generated Password: " + password);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


