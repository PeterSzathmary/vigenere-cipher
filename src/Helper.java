import java.util.Scanner;

public class Helper
{
    public static int getAnswer(int totalOptions)
    {
        int choice;
        boolean isLooping = true;
        boolean found = false;
        do
        {
            choice = getInt();
            for (int i = 1; i <= totalOptions; i++)
            {
                if (choice == i)
                {
                    isLooping = false;
                    found = true;
                    break;
                }

            }
            if (!found)
                System.out.print("Please enter only 1 or 2. Choose again: ");
        } while (isLooping);
        return choice;
    }

    public static int getInt()
    {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt())
        {
            System.out.print("Please try again: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}
