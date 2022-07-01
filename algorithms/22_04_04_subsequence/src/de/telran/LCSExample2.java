package de.telran;

import java.util.Scanner;

public class LCSExample2 {

    // create getLengthOfLCS() method that returns length of the LCS
    public static int getLengthOfLCS(char[] str1, char[] str2, int p, int q)
    {
        //check the length of the sequence
        if (p == 0 || q == 0)
            return 0;

        // when the last character of both the sequences are equal
        if (str1[p - 1] == str2[q - 1])
            return 1 + getLengthOfLCS(str1, str2, p - 1, q - 1);
        else
            return maxValue(getLengthOfLCS(str1, str2, p, q - 1), getLengthOfLCS(str1, str2, p - 1, q));
    }

    // create maxValue() method that returns the length of the sequence having maximum length from both of them
    static int maxValue(int length1, int length2)
    {
        if (length1 > length2)
            return length1;
        else
            return length2;
    }

    //main() method starts
    public static void main(String[] args)
    {
        String string1, string2;

        //create Scanner class object to get sequence from the user
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream.
        System.out.print("Enter first sequence: ");
        string1 = sc.nextLine(); //reads string.

        System.out.print("Enter second sequence: ");
        string2 = sc.nextLine(); //reads string.

        char[] str1 = string1.toCharArray();
        char[] str2 = string2.toCharArray();

        int p = string1.length();
        int q = string2.length();

        System.out.println("Length of the Longest Common Subsequence is: "+ getLengthOfLCS(str1, str2, p, q));
    }
}