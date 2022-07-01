package de.teleran;


public class UnboundedKnapsack
{
    // function returns maximum value for capacity
    private static int max(int i, int j)
    {
        return (i > j) ? i : j;
    }


    protected static int unboudedKnapsack(int W, int n,
                                           int[] val, int[] wt)
    {

        // dp[i] is going to store maximum value
        // with knapsack capacity i.
        int dp[] = new int[W + 1];

        // Fill dp[] using above recursive formula
        for(int i = 0; i <= W; i++){
            for(int j = 0; j < n; j++){
                if(wt[j] <= i){
                    dp[i] = max(dp[i], dp[i - wt[j]] +
                            val[j]);
                }
            }
        }
        return dp[W];
    }

}

