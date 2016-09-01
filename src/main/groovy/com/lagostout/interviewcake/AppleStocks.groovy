package com.lagostout.interviewcake

class AppleStocks {

    def static findBestProfit(int[] prices) {
        int lastTime = prices.length - 1
        int bestSellTime = lastTime
        int bestBuyTime = lastTime - 1
        int possibleBuyTime = bestBuyTime - 1
        int bestProfit = prices[bestSellTime] - prices[bestBuyTime]
        while (possibleBuyTime >= 0) {
            if (prices[bestSellTime] - prices[possibleBuyTime] >= bestProfit) {
                bestBuyTime = possibleBuyTime
                bestProfit = prices[bestSellTime] - prices[bestBuyTime]
                int possibleSellTime = bestSellTime - 1
                while (possibleSellTime > bestBuyTime) {
                    if (prices[possibleSellTime] - prices[bestBuyTime] > bestProfit) {
                        bestSellTime = possibleSellTime
                        bestProfit = prices[bestSellTime] - prices[bestBuyTime]
                    }
                    --possibleSellTime
                }
            }
            --possibleBuyTime
        }
        return [prices[bestBuyTime], prices[bestSellTime], bestProfit]
    }

}
