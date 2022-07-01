package de.telran.lcs;

class LcsOne {

    static String lcsOne(String a, String b) {
        return backtrack(buildDistanceArray(a, b), a, b, a.length(), b.length());
    }

    private static int[][] buildDistanceArray(String s1, String s2){
        int[][] returnArray = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 1; i <= s1.length(); i++){
            for (int j = 1; j <= s2.length(); j++){
                returnArray[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ?
                        returnArray[i-1][j-1] + 1:
                        Math.max(returnArray[i][j-1], returnArray[i-1][j]);
            }
        }
        return returnArray;
    }

    private static String backtrack(int[][] distArray, String s1, String s2, int i, int j){
        if(i * j == 0) return "";
        if(s1.charAt(i - 1) == s2.charAt(j - 1)) return backtrack(distArray, s1, s2, i-1, j-1) + s1.charAt(i - 1);
        if (distArray[i][j-1] > distArray[i-1][j]) return backtrack(distArray, s1, s2, i, j-1);
        return backtrack(distArray, s1, s2, i-1, j);
    }
}