import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        char[][] matrix = new char[alphabet.length()][alphabet.length()];

        fillMatrix(matrix, alphabet);
        printMatrix(matrix);

        String sWord = "utekajte idu zajatci";
        String sKey = "auticko";
        StringBuilder sSecret = new StringBuilder(); // mhata tcsa

        char[] word = sWord.toCharArray();
        char[] key = sKey.toCharArray();

        int i = 0;
        int keyIndex = 0;
        while (i < word.length)
        {
            if (word[i] == ' ')
            {
                sSecret.append(' ');
            }
            else
            {
                // TODO: do this if word[i] is a letter otherwise append non-letter character
                if (keyIndex == key.length)
                    keyIndex = 0;

                int row = findRow(key[keyIndex], matrix);
                System.out.println("row: " + row);
                int column = findColumn(word[i], matrix);
                System.out.println("column: " + column);

                sSecret.append(matrix[column][row]);


                keyIndex++;
            }

            i++;
        }

        System.out.println(sSecret);
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