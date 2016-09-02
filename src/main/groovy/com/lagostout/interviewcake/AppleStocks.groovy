package com.lagostout.interviewcake

class AppleStocks {

    def static findBestProfit(int[] prices) {
        int lastPossibleBuyTimeIndex = 0
        int minBuyTimeIndex = lastPossibleBuyTimeIndex
        int possibleSellTimeIndex = 1
        int bestProfit = Integer.MIN_VALUE
        int possibleProfit
        int bestSellTimeIndex = -1
        int bestBuyTimeIndex = -1
        while (possibleSellTimeIndex < prices.length) {
            possibleProfit = prices[possibleSellTimeIndex] - prices[minBuyTimeIndex]
            if (possibleProfit >= bestProfit) {
                bestProfit = possibleProfit
                bestBuyTimeIndex = minBuyTimeIndex
                bestSellTimeIndex = possibleSellTimeIndex
            }
            possibleSellTimeIndex++
            lastPossibleBuyTimeIndex++
            if (prices[lastPossibleBuyTimeIndex] < prices[minBuyTimeIndex]) {
                minBuyTimeIndex = lastPossibleBuyTimeIndex
            }
        }
        return [prices[bestBuyTimeIndex], prices[bestSellTimeIndex], bestProfit]
    }

}
