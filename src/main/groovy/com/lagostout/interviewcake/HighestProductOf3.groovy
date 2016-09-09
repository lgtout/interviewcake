// https://www.interviewcake.com/question/java/highest-product-of-3

package com.lagostout.interviewcake

class HighestProductOf3 {

    static int product(int[] numbers) {
        def product
        int[] range = [numbers[0], numbers[1], numbers[2]]
        List<Integer> highest3Positive = range.findAll { it >= 0 }
        highest3Positive = highest3Positive.sort()
        println "highest3Positive $highest3Positive"
        def lowest2Positive = highest3Positive.collect().take(2)
        def highest3Negative = range.findAll { it < 0 }
        highest3Negative = highest3Negative.sort()
        def lowest2Negative = highest3Negative.collect().take(2)
        for (int i = 3; i < numbers.length; ++i) {
            def number = numbers[i]
            if (number >= 0) {
                if (highest3Positive.size() == 3 && number > highest3Positive.first()) {
                    highest3Positive.set(0, number)
                } else if (highest3Positive.size() <= 2) {
                    highest3Positive.add(number)
                }
                highest3Positive.sort()
                println "highest3Positive $highest3Positive"
                if (lowest2Positive.size() == 2 && number < lowest2Positive.last()) {
                    lowest2Positive.set(1, number)
                } else if (lowest2Positive.size() <= 1) {
                    lowest2Positive.add(number)
                }
                lowest2Positive.sort()
            } else {
                println "number $number"
                println "highest3Negative.size() == 3 && number > highest3Negative.first() " +
                        "${highest3Negative.size() == 3 && number > highest3Negative.first()}"
                if (highest3Negative.size() == 3 && number > highest3Negative.first()) {
                    highest3Negative.set(0, number)
                    println "highest3Negative $highest3Negative"
                } else if (highest3Negative.size() <= 2) {
                    highest3Negative.add(number)
                }
                highest3Negative.sort()
                if (lowest2Negative.size() == 2 && number < lowest2Negative.last()) {
                    lowest2Negative.set(1, number)
                } else if (lowest2Negative.size() <= 1) {
                    lowest2Negative.add(number)
                }
                lowest2Negative.sort()
            }
        }

        def canUsePositivePositivePositive = highest3Positive.size() == 3
        def positivePositivePositiveProduct = calculateProduct(highest3Positive)

        def negativePositivePositive =  lowest2Positive.collect() + highest3Negative.takeRight(1)
        def canUseNegativePositivePositive = negativePositivePositive.size() == 3
        println "negativePositivePositive $negativePositivePositive ${negativePositivePositive.getClass()}"
        def negativePositivePositiveProduct = calculateProduct(negativePositivePositive as List<Integer>)

        def negativeNegativeNegative = highest3Negative.collect()
        def canUseNegativeNegativeNegative = negativeNegativeNegative.size() == 3
        def negativeNegativeNegativeProduct = calculateProduct(negativeNegativeNegative as List<Integer>)

        def negativeNegativePositive = lowest2Negative.collect() + highest3Positive.takeRight(1)
        def canUseNegativeNegativePositive = negativeNegativePositive.size() == 3
        def negativeNegativePositiveProduct = calculateProduct(negativeNegativePositive as List<Integer>)

        product = canUsePositivePositivePositive ? positivePositivePositiveProduct :
                canUseNegativeNegativeNegative ? negativeNegativeNegativeProduct :
                        canUseNegativeNegativePositive ? negativeNegativePositiveProduct :
                                canUseNegativePositivePositive ? negativePositivePositiveProduct : 0

        println "positivePositivePositive $highest3Positive"
        println "negativePositivePositive $negativePositivePositive"
        println "negativeNegativePositive $negativeNegativePositive"
        println "negativeNegativeNegative $negativeNegativeNegative"

        if (canUseNegativeNegativeNegative)
            product = Math.max(product, negativeNegativeNegativeProduct)
        if (canUsePositivePositivePositive)
            product = Math.max(product, positivePositivePositiveProduct)
        if (canUseNegativePositivePositive)
            product = Math.max(product, negativePositivePositiveProduct)
        if (canUseNegativeNegativePositive)
            product = Math.max(product, negativeNegativePositiveProduct)

        product
    }

    static Integer calculateProduct(List<Integer> list) {
        println "list $list"
        (list.size() != 3) ? null : list.inject { acc, val -> acc * val }
    }

}
