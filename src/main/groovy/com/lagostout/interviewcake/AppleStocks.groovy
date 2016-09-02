package com.lagostout.interviewcake

class AppleStocks {

    def static findBestProfit(int[] prices) {
        int lastTime = prices.length - 1
        int bestSellTime = lastTime
        int bestBuyTime = lastTime - 1
        int possibleBuyTime = bestBuyTime - 1
        int bestProfit = prices[bestSellTime] - prices[bestBuyTime]
        while (possibleBuyTime >= 0) {
            int profitIfImprovingBuyTime = prices[bestSellTime] - prices[possibleBuyTime]
            int profitIfImprovingBuyAndSellTimes = prices[bestBuyTime] - prices[possibleBuyTime]
            int possibleProfit = Math.max(profitIfImprovingBuyTime, profitIfImprovingBuyAndSellTimes)
            if (possibleProfit > bestProfit) {
                if (profitIfImprovingBuyAndSellTimes > profitIfImprovingBuyTime) {
                    bestSellTime = bestBuyTime
                }
                bestBuyTime = possibleBuyTime
                bestProfit = possibleProfit
            }
            --possibleBuyTime
        }
        return [prices[bestBuyTime], prices[bestSellTime], bestProfit]
    }

}
