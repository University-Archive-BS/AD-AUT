import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int k, n;
        System.out.print("Enter number of cities: ");
        k = input.nextInt();
        System.out.print("Enter number of months: ");
        n = input.nextInt();
        int c[][] = new int[k][n];
        for (int i = 0; i < k; i++)
        {
            System.out.print("Enter costs of operation in city " + (i + 1) + ": ");
            for (int j = 0; j < n; j++)
            {
                c[i][j] = input.nextInt();
            }
        }
        int f[][] = new int[k][k];
        System.out.println("Enter relocation costs: ");
        for (int i = 0; i < k; i++)
        {
            for (int j = 0; j < k; j++)
            {
                if (i != j)
                {
                    System.out.print("f[" + (i + 1) + "," + (j + 1) + "] = ");
                    f[i][j] = input.nextInt();
                }
                else
                {
                    f[i][j] = 0;
                }
            }
        }
        int dp[][] = new int[n][k];
        int path[][] = new int[n][k];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < k; j++)
            {
                int min = Integer.MAX_VALUE;
                if (i == 0)
                {
                    min = 0;
                }
                else
                {
                    for (int l = 0; l < k; l++)
                    {
                        if (dp[i - 1][l] + f[l][j] < min)
                        {
                            min = dp[i - 1][l] + f[l][j];
                            path[i][j] = l;
                        }
                    }
                }
                dp[i][j] = c[j][i] + min;
            }
        }
        int min = dp[n - 1][0];
        int finalCity = 0;
        for (int i = 1; i < k; i++)
        {
            if (dp[n - 1][i] < min)
            {
                min = dp[n - 1][i];
                finalCity = i;
            }
        }
        System.out.println("Minimum cost = " + min);
        int city = finalCity;
        int bestPath[] = new int[n];
        bestPath[n - 1] = finalCity;
        for (int i = 0; i < n - 1; i++)
        {
            bestPath[n - 2 - i] = path[n - 1 - i][city];
            city = path[n - 1 - i][city];
        }
        System.out.print("Order of cities : ");
        for (int i = 0; i < n - 1; i++)
        {
            System.out.print((bestPath[i] + 1) + " -> ");
        }
        System.out.println(bestPath[n - 1] + 1);
    }
}