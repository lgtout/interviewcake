package com.lagostout.interviewcake

import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll

import static org.apache.commons.lang3.Range.between
import org.apache.commons.lang3.Range

class MakingChangeSpec extends Specification {

    @Unroll('#amount #denominations')
    def 'makes change - random cases'(int amount, int[] denominations) {

        expect:
        MakingChange.countWays(amount, denominations) ==
                bruteForceMakeChange(amount, denominations)

        where:
        [amount, denominations] << makeRandomTestCases()
//                makeRandomTestCases(2, between(4,4), between(2,2), between(1,2))

    }

    @Unroll('#amount #denominations')
    def 'makes change - non-random cases'(int amount, int[] denominations) {

        expect:
        MakingChange.countWays(amount, denominations) ==
                bruteForceMakeChange(amount, denominations)

        where:
        [amount, denominations] << [
                [1, [1]],
                [1, [1,2]],
                [2, [1,2]],
                [3, [1,2]],
                [4, [1,2]],
        ]

    }

    List<List> makeRandomTestCases(int testCaseCount = 100,
                            Range<Integer> amountRange = between(1, 10),
                            Range<Integer> denominationCountRange = between(1, 10),
                            Range<Integer> denominationRange = between(1, 10)) {
        def random = new RandomDataGenerator()
        random.reSeed(1)
        def testCases = []
        testCaseCount.times {
            def denominations = new HashSet<Integer>()
            def denominationCount = random.nextInt(
                    denominationCountRange.minimum,
                    denominationCountRange.maximum)
            while (true) {
                denominations.add random.nextInt(
                        denominationRange.minimum,
                        denominationRange.maximum)
                if (denominations.size() == denominationCount) {
                    def amount = random.nextInt(
                            amountRange.minimum, amountRange.maximum)
                    testCases.add([amount, denominations])
                    break
                }
            }
        }
        testCases
    }

    int bruteForceMakeChange(int amount, int[] denominations) {
        bruteForceMakeChange(amount, denominations, 0)
    }

    int bruteForceMakeChange(int amount, int[] denominations,
                             int currentDenominationIndex) {
//        println "bruteForceMakeChange " +
//                "$amount $denominations $currentDenominationIndex"
        if (amount == 0) return 1
        if (amount < 0) return 0
        if (currentDenominationIndex >= denominations.length) return 0
        int numberOfWays = 0
        int currentAmount = amount
        while (currentAmount >= 0) {
//            println "currentAmount $currentAmount"
            numberOfWays += bruteForceMakeChange(
                    currentAmount, denominations, currentDenominationIndex + 1)
//            println "bruteForceMakeChange " +
//                    "$amount $denominations $currentDenominationIndex"
            currentAmount -= denominations[currentDenominationIndex]
        }
        numberOfWays
    }

}
