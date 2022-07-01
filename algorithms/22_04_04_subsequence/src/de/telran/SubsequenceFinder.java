package de.telran;

public class SubsequenceFinder {
    /*
Есть две последовательности символов. Назоем подпоследовательностью такую последовательность символов, что между ее символами можно вставить другие символы, чтобы получить первую. Например, "hlo" будет подпоследовательностью "hello".
является ли одна последовательность подпоследовательностью другой. public boolean isSubsequence(String sequence, String pattern)
 */
    public boolean isSubsequence(String text, String pattern) {
        return isSubsequence(text, pattern, text.length() - 1, pattern.length() - 1);
    }

    private boolean isSubsequence(String text, String pattern, int indexText, int indexPattern) {
        if (indexPattern < 0)
            return true;
        if (indexText < indexPattern)
            return false;

        if (text.charAt(indexText) == pattern.charAt(indexPattern))
            return isSubsequence(text, pattern, indexText - 1, indexPattern - 1);

        return isSubsequence(text, pattern, indexText - 1, indexPattern);
    }
    /*найти длину наибольшей общей подпоследовательности двух строк.
    public int getLongestSharedSubsequence(String s1, String s2). (hello, hovard) -> 2
     */
    public static int lengthOfLongestCommonSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] lengths = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    lengths[i][j] = lengths[i-1][j-1] + 1;
                } else {
                    lengths[i][j] = Math.max(lengths[i-1][j], lengths[i][j-1]);
                }
            }
        }
        return lengths[m][n];
    }
}
