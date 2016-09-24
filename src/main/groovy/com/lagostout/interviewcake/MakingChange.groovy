package com.lagostout.interviewcake

//https://www.interviewcake.com/question/java/coin

class MakingChange {

    static public int countWays(int amount, int[] denominations) {
        int[] ways = new int[amount + 1]
        ways[0] = 1
        for (int d : denominations) {
            for (int a = d; a < ways.length; ++a) {
                ways[a] += ways[a - d]
            }
        }
        ways[amount]
    }

    static public int _countWays(int amount, int[] denominations) {
        int[] ways = new int[amount + 1]
        for (int d = 0; d < denominations.length; ++d) {
            for (int a = 0; a < ways.length; ++a) {
                int w
                if (a == 0) w = 1
                else {
                    int i = a - denominations[d]
                    w = (i >= 0) ? ways[i] : 0
                    w += ways[a]
                }
                ways[a] = w
            }
        }
        ways[amount]
    }

}
