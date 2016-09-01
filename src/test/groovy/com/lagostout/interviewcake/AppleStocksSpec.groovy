package com.lagostout.interviewcake

import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import org.apache.commons.lang3.Range
import spock.lang.Unroll

class AppleStocksSpec extends Specification {

    @Unroll("#data")
    def 'finds best profit in non-random cases'() {
        expect:
        AppleStocks.findBestProfit(data.get(0) as int[]) == data.get(1)

        where:
        data << [
//                [[3, 3, 1], [0, 1, 0]],
//                [[1, 1, 3], [1, 2, 2]]
                [[8, 6, 1], [8, 6, -2]]
        ]

    }

    @Unroll("#data")
    def 'finds best profit in random cases'() {

        expect:
        AppleStocks.findBestProfit(data) == findBestProfitWithBruteForce(data)

        where:
        data << createTestCases(10, 3, Range.between(1, 10))

    }

    static private List<int[]> createTestCases(
            int caseCount = 1000,
            int durationMinutes = 24 * 60,
            Range<Integer> priceRange = Range.between(1, 100)) {
        def cases = []
        RandomDataGenerator random = new RandomDataGenerator()
        random.reSeed(1)
        caseCount.times {
            int[] prices = new int[durationMinutes]
            durationMinutes.times {
                prices[it] = random.nextInt(priceRange.minimum, priceRange.maximum)
            }
            cases.add  prices
        }
        cases
    }

    def static private findBestProfitWithBruteForce(int[] data) {
        int bestProfit = Integer.MIN_VALUE
        int profit
        def result = []
        for (int i = 0; i < data.length - 1; ++i) {
            for (int j = i+1; j < data.length; ++j) {
                profit = data[j] - data[i]
                if (profit >= bestProfit) {
                    bestProfit = profit
                    result = [data[i], data[j], bestProfit]
                }
            }
        }
        result
    }

}
