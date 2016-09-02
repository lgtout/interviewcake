package com.lagostout.interviewcake

class AppleStocks {

    def static findBestProfit(int[] prices) {
        int minBuyPrice = prices[0]
        int possibleSellTimeIndex = 1
        int bestProfit = Integer.MIN_VALUE
        while (possibleSellTimeIndex < prices.length) {
            int possibleSellPrice = prices[possibleSellTimeIndex]
            bestProfit = Math.max(possibleSellPrice - minBuyPrice, bestProfit)
            minBuyPrice = Math.min(possibleSellPrice, minBuyPrice)
            possibleSellTimeIndex++
        }
        return bestProfit
    }

}
