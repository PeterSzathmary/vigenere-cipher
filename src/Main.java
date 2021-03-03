public class Main
{
    public static void main(String[] args)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        char[][] matrix = new char[alphabet.length()][alphabet.length()];

        fillMatrix(matrix, alphabet);
        //printMatrix(matrix);

        String encrypt = "Abrakadabra, to je kuzlo pre Voldemorta!";
        String key = "rokfort";

        StringBuilder encrypted = vigEncrypt(encrypt.toCharArray(), key.toCharArray(), matrix);
        System.out.println(encrypted);

        String decrypt = "Rpbfyrwrpbf, hf cv yeezf iis Ftzuxdcbyo!";
        String decrypted = vigDecrypt(decrypt.toCharArray(), key.toCharArray(), matrix, alphabet.toCharArray());
        System.out.println(decrypted);
    }

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
}