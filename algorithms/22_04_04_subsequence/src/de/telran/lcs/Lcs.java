package de.telran.lcs;

public class Lcs {
    public static void main(String[] args) {
        System.out.println(lcs("tzubk","rtzkqxö"));
    }
        static String lcs(String a, String b) {
            int[][] matrix = new int[a.length() + 1][b.length() + 1];
            for(int i=1; i <= a.length(); i++){
                for(int j=1; j <= b.length(); j++){
                    if(a.charAt(i-1) == b.charAt(j-1)){
                        matrix[i][j] = matrix[i-1][j-1] + 1;
                    }else{
                        matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                    }
                }
            }

            StringBuilder seq = new StringBuilder();
            int i, j;
            i = a.length();
            j = b.length();
            while(i > 0 && j > 0){
                if(a.charAt(i-1) == b.charAt(j-1)){
                    seq.append(b.charAt(j-1));
                    i--;
                    j--;
                }else{
                    if(matrix[i-1][j] > matrix[i][j-1]){
                        i--;
                    }else
                        j--;
                }
            }
            String out = seq.reverse().toString();
            return out; // do it!
        }
    }
