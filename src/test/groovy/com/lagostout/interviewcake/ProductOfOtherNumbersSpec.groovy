package com.lagostout.interviewcake

import org.apache.commons.lang3.Range
import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll

class ProductOfOtherNumbersSpec extends Specification {

    @Unroll("#data")
    def 'calculates product of other numbers from random input' () {

        expect:
        ProductOfOtherNumbers.calculateProducts(data as int[]) ==
                calculateProductWithBruteForce(data as int[])

        where:
        data << generateTestCases(10, Range.between(0,2), Range.between(1,10))
//        data << generateTestCases()

    }

    def generateTestCases(testCaseCount = 100,
                          Range<Integer> numberCountRange = Range.between(0, 100),
                          Range<Integer> numberRange = Range.between(-100, 100)) {
        def random = new RandomDataGenerator()
        random.reSeed(1)
        def testCases = []
        testCaseCount.times {
            def numbers = []
            def numberCount = random.nextInt(
                    numberCountRange.minimum,
                    numberCountRange.maximum)
            numberCount.times {
                def number = random.nextInt(
                        numberRange.minimum, numberRange.maximum)
                numbers.add number
            }
            testCases.add numbers
        }
        testCases
    }

    int[] calculateProductWithBruteForce(int[] numbers) {
        def products = []
        if (numbers.length == 0) return []
        if (numbers.length == 1) return [1]
        def indices = 0..numbers.length-1
        indices.each {
            def filteredIndices = indices.collect()
            filteredIndices.remove(it)
            println filteredIndices
            def product = filteredIndices.inject (1) {
                acc, val ->
                    acc * numbers[val] }
            products.add product
        }
        products
    }

}

