import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("+-------------------------------------------------+");
        System.out.println("|     You can run this program from CLI too!!     |");
        System.out.println("|        java Main [-e/-d] [key] [phrase]         |");
        System.out.println("+-------------------------------------------------+");

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        char[][] matrix = new char[alphabet.length()][alphabet.length()];

        fillMatrix(matrix, alphabet);

        if (args.length == 0)
        {
//            String encrypt = "Abrakadabra, to je kuzlo pre Voldemorta!";
//            String key = "rokfort";
//
//            StringBuilder encrypted = vigEncrypt(encrypt.toCharArray(), key.toCharArray(), matrix);
//            System.out.println(encrypted);
//
//            String decrypt = "Rpbfyrwrpbf, hf cv yeezf iis Ftzuxdcbyo!";
//            String decrypted = vigDecrypt(decrypt.toCharArray(), key.toCharArray(), matrix, alphabet.toCharArray());
//            System.out.println(decrypted);

            boolean isRunning = true;
            while (isRunning)
            {
                isRunning = run(matrix);
            }
        }

        else
        {
            if (args[0].equals("-e"))
            {
                System.out.println(vigEncrypt(args[2].toCharArray(), args[1].toCharArray(), matrix));
            }
            else if (args[0].equals("-d"))
            {
                System.out.println(vigDecrypt(args[2].toCharArray(), args[1].toCharArray(), matrix, alphabet.toCharArray()));
            }
        }
    }

    //#region all okay here
    private static String vigDecrypt(char[] encryptedTextArr, char[] key, char[][] matrix, char[] alphabet)
    {
        StringBuilder decryptedText = new StringBuilder();

        int i = 0;
        int keyIndex = 0;
        while (i < encryptedTextArr.length)
        {
            if (((int) encryptedTextArr[i] >= 65 && (int) encryptedTextArr[i] <= 90) || ((int) encryptedTextArr[i] >= 97 && (int) encryptedTextArr[i] <= 122))
            {
                if (((int) encryptedTextArr[i] >= 65 && (int) encryptedTextArr[i] <= 90))
                    encryptedTextArr[i] = (char) (encryptedTextArr[i] + 32);

                if (keyIndex == key.length)
                    keyIndex = 0;

                int row = findRow(key[keyIndex], matrix); // 0 1 2
                int column = getIndex(encryptedTextArr[i], row, matrix);

                decryptedText.append(alphabet[column]);

                keyIndex++;
            }
            else
            {
                decryptedText.append(encryptedTextArr[i]);
            }

            i++;
        }

        return decryptedText.toString();
    }

    private static int getIndex(char c, int row, char[][] matrix)
    {
        int index = -1;

        for (int i = 0; i < matrix[row].length; i++)
        {
            if (matrix[row][i] == c)
            {
                index = i;
                break;
            }
        }

        return index;
    }

    private static StringBuilder vigEncrypt(char[] sentenceArr, char[] key, char[][] matrix)
    {
        StringBuilder secret = new StringBuilder();

        int i = 0;
        int keyIndex = 0;
        while (i < sentenceArr.length)
        {
            if (((int) sentenceArr[i] >= 65 && (int) sentenceArr[i] <= 90) || ((int) sentenceArr[i] >= 97 && (int) sentenceArr[i] <= 122))
            {
                if (((int) sentenceArr[i] >= 65 && (int) sentenceArr[i] <= 90))
                    sentenceArr[i] = (char) (sentenceArr[i] + 32);

                if (keyIndex == key.length)
                    keyIndex = 0;

                int row = findRow(key[keyIndex], matrix);
                int column = findColumn(sentenceArr[i], matrix);

                secret.append(matrix[column][row]);

                keyIndex++;
            }
            else
            {
                secret.append(sentenceArr[i]);
            }

            i++;
        }

        return secret;
    }

    private static int findColumn(char c, char[][] matrix)
    {
        int column = -1;

        for (int i = 0; i < matrix[0].length; i++)
        {
            if (matrix[0][i] == c)
            {
                column = i;
                break;
            }
        }

        return column;
    }

    /**
     * Find out the row from the given character.
     *
     * @param c      Character.
     * @param matrix The matrix where we want to find our row.
     * @return Index of given character.
     */
    private static int findRow(char c, char[][] matrix)
    {
        int row = -1;
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (matrix[i][0] == c)
                {
                    row = i;
                    break;
                }
            }
        }
        return row;
    }

    /**
     * Print the Vigenere matrix.
     *
     * @param matrix The matrix to be printed.
     */
    private static void printMatrix(char[][] matrix)
    {
        boolean once = true;
        for (int i = 0; i < matrix.length; i++)
        {
            if (once)
            {
                String str = new String(matrix[i]);
                System.out.println("          " + str.replace("", " "));
                once = false;
            }
            System.out.print(((i + 1) < 10 ? (" " + (i + 1)) : (i + 1)) + " -> " + (matrix[0][i]) + " == ");
            for (int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Fill the Vigenere matrix.
     * <p>
     * a b c d e f g h i j k l m n o p q r s t u v w x y z
     * b c d e f g h i j k l m n o p q r s t u v w x y z a
     * c d e f g h i j k l m n o p q r s t u v w x y z a b
     * </p>
     * ...
     *
     * @param matrix   Empty 2D char array.
     * @param alphabet An alphabet from which the matrix will be filled.
     */
    private static void fillMatrix(char[][] matrix, String alphabet)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            String orig = alphabet;
            String s1 = alphabet.substring(0, i);
            orig = orig.substring(i);
            String n1 = orig + s1;
            for (int j = 0; j < n1.toCharArray().length; j++)
            {
                matrix[i][j] = n1.charAt(j);
            }
        }
    }
    //#endregion

    private static void showMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1. encryption");
        System.out.println("2. decryption");
        System.out.print("Choose now: ");
    }

    private static boolean again(boolean isRunning)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to go again? [y/n]: ");
        char again;
        do
        {
            again = scanner.next().charAt(0);
            if (again != 'y' && again != 'n')
                System.out.print("Please try again: ");
        } while (again != 'y' && again != 'n');

        if (again == 'n')
        {
            isRunning = false;
        }
        return isRunning;
    }

    private static boolean run(char[][] matrix)
    {
        System.out.println("We are going to run only encryption part indefinitely!");
        System.out.println("Menu with decryption will come later...\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your key: ");
        String key = scanner.nextLine();
        System.out.println("Your key is: " + key);
        System.out.println("\nPress 0 to exit the infinite loop.\n");

        while (true)
        {
            System.out.print("Choose your phrase for encryption: ");
            String phrase = scanner.nextLine();
            if (phrase.equals("0"))
            {
                break;
            }
            else
                System.out.println(vigEncrypt(phrase.toCharArray(), key.toCharArray(), matrix));
        }

        return false;
    }
}