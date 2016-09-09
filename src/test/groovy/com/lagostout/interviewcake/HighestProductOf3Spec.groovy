package com.lagostout.interviewcake

import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll
import org.apache.commons.lang3.Range
import static org.apache.commons.lang3.Range.between

class HighestProductOf3Spec extends Specification {

    @Unroll("#data")
    def "calculates highest product of 3 from non-random input" (int[] data) {

        expect:
        HighestProductOf3.product(data) == bruteForceHighestProduct(data)

        where:
        data << [
                [10, -10, 2, 8, 5, -5, 2, -6, 1],
//                 [5, 1, 6]
        ]

    }

    @Unroll("#data")
    def "calculates highest product of 3 from random input" (int[] data) {

        expect:
        HighestProductOf3.product(data) == bruteForceHighestProduct(data)

        where:
        data << createRandomTestCases(100, between(-100, 100))

    }

    def createRandomTestCases(
            int testCaseCount = 1000,
            Range<Integer> numberRange = between(-100, 100),
            Range<Integer> numberCountRange = between(3, 100)
    ) {
        def testCases = []
        RandomDataGenerator random = new RandomDataGenerator()
        random.reSeed(1)
        testCaseCount.times {
            def numberCount = random.nextInt(
                    numberCountRange.minimum, numberCountRange.maximum)
            def numbers = []
            numberCount.times {
                numbers.add random.nextInt(numberRange.minimum, numberRange.maximum)
            }
            testCases.add numbers
        }
        println testCases
        return testCases
    }

    def bruteForceHighestProduct(int[] numbers) {
        def product = null
        for (int i = 0; i < numbers.length; ++i) {
            for (int j = i+1; j < numbers.length; ++j) {
                for (int k = j+1; k < numbers.length; ++k) {
                    def possibleProduct = numbers[i] * numbers[j] * numbers[k]
                    if (product == null || product < possibleProduct) {
                        def factors = [numbers[i], numbers[j], numbers[k]].sort()
                        println "factors $factors"
                        product = possibleProduct
                    }
                }
            }
        }
        product
    }

}
