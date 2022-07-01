package de.teleran;

public class UnlimitedKnapsack {

    int getMaximumKnapsackValue(int w, int weight[], int values[], int n)

    {
        // Initialize 2D array
        //используем только 2 строки : одну текущую строку и одну предыдущую строку.

        int array[][] = new int[2][w + 1];



        for (int i = 0; i < n; i++)  //n-количество предметов

        {

            for (int j = 0; j <= w; j++) //внутренний цикл for с индексом, изменяющимся от 0 до W.

            {

                // If i is even then use 1th row

                if(i % 2 == 0)

                {

                    // Exclude weight

                    if(weight[i] > j)

                    {

                        array[0][j] = array[1][j];

                    }

                    else

                    {

                        array[0][j] = Math.max(array[1][j], array[1][j-weight[i]] + values[i]);

                    }

                }

                else    // If i is odd, then use 0th row

                {

                    // exclude weight

                    if(weight[i] > j)

                    {

                        array[1][j] = array[0][j];

                    }

                    else

                    {

                        array[1][j] = Math.max(array[0][j], array[0][j-weight[i]] + values[i]);

                    }

                }

            }

        }



        if(n % 2 == 0)

        {

            return array[1][w];

        }

        else

        {

            return array[0][w];

        }

    }

}

