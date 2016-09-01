package com.lagostout.interviewcake

import org.apache.commons.lang3.Range
import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import spock.lang.Unroll

class FindADuplicateSpaceEditionSpec extends Specification {

    @Unroll
    def 'finds duplicate in random cases'(List<Integer> data) {

        expect:
        println data
        def dataArray = data as int[]
        FindADuplicateSpaceEdition.find(dataArray) ==
                findDuplicateWithBruteForce(dataArray)

        where:
        data << createTestCases(10, Range.between(1,3))

    }

    @Unroll
    def 'finds duplicate in non-random cases'(List<Integer> data) {

        expect:
        println data
        def dataArray = data[0] as int[]
        FindADuplicateSpaceEdition.find(dataArray) == data[1]

        where:
        data << [
//                [[1,1,2], 1]
                [[1, 2, 3, 1], 1]
        ]

    }

    static private List<List<Integer>> createTestCases(int caseCount = 1000,
                                        Range<Integer> numberRange = Range.between(1, 1000 - 1)) {
        RandomDataGenerator random = new RandomDataGenerator()
        random.reSeed(1)
        def cases = []
        caseCount.times {
            def testcase = []
            def maxNumber = random.nextInt(numberRange.minimum, numberRange.maximum)
            def caseSize = maxNumber + 1
            caseSize.times {
                testcase.add(random.nextInt(1, maxNumber))
            }
            if (!testcase.contains(maxNumber)) {
                def index = random.nextInt(0, caseSize - 1)
                testcase.set(index, maxNumber)
            }
            cases.add(testcase)
        }
        cases
    }

    static private Integer findDuplicateWithBruteForce(int[] data) {
        Set<Integer> set = new HashSet<>()
        Integer duplicate = null;
        for (int i : data) {
            if (set.contains(i)) {
                duplicate = i
            }
            set.add(i)
        }
        duplicate
    }

}
