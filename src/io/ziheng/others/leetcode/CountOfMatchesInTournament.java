package io.ziheng.others.leetcode;

/**
 * LeetCode 1688. Count of Matches in Tournament
 * https://leetcode.com/problems/count-of-matches-in-tournament/
 */
public class CountOfMatchesInTournament {
    public static void main(String[] args) {
        CountOfMatchesInTournament obj = new CountOfMatchesInTournament();
        int n = 7;
        System.out.println(obj.numberOfMatches(n));
    }
    public int numberOfMatches(int n) {
        if (n <= 1) {
            return 0;
        }
        int matches = 0;
        while (n > 1) {
            switch (n % 2) {
            case 0:
                matches += n / 2;
                n = n / 2;
            case 1:
                matches += (n - 1) / 2;
                n = n / 2 + 1;
                break;
            default:
                break;
            }
        }
        return matches;
    }
}

/* EOF */
