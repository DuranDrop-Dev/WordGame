import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Turn
{
    public static int getPrizeInt()
    {
        Random randomPrizeInt = new Random();
        return randomPrizeInt.nextInt(2);
    }

    public boolean takeTurn(Players player, Hosts host)
    {
        // Declarations
        Scanner input = new Scanner(System.in);

        Physical physicalPrize = new Physical();
        Money moneyPrize = new Money();

        int prizeInteger = getPrizeInt();

        // Initial Host prompt
        try
        {
            System.out.println(host + " says:\nPlayer " + player +
                    ", please enter a letter for the game phrase!");
            StringBuilder string = new StringBuilder(input.nextLine());
            for (int i=0; i<string.length(); i++)
            {
                if (Character.isLetter(string.charAt(i)))
                {
                    Phrases.findLetters(String.valueOf(string));
                }
                else
                {
                    throw new IOException();
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Letters only, lose a turn!");
        }

        // Check input
        if (!Phrases.comparePhrase())
        {
            // Wrong Money answer
            if  (prizeInteger == 1)
            {
                player.setMoney(player.getMoney() + moneyPrize.displayWinnings(player, false));
            }
            // Wrong Physical answer
            if (prizeInteger == 0)
            {
                physicalPrize.displayWinnings(player, false);
            }
            return false;
        }
        else
        {
            // Money Winner
            if (prizeInteger == 1)
            {
                player.setMoney(player.getMoney() + moneyPrize.displayWinnings(player, true));
            }
            // Physical Winner
            if (prizeInteger == 0)
            {
                physicalPrize.displayWinnings(player,true);
            }
            return true;
        }
    }
}
