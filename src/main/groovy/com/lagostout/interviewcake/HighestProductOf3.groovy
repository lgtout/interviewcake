package com.lagostout.interviewcake

class HighestProductOf3 {

    static int product(int[] numbers) {
        def product = 1
        int[] range = [numbers[0], numbers[1], numbers[2]]
        def updateProduct = {
            println "updateProduct"
            println "range $range"
            Arrays.sort(range)
            product = range.inject { acc, val -> acc * val }
            println "product $product"
        }
        updateProduct()
        Arrays.sort(range)
        for (int i = 3; i < numbers.length; ++i) {
            println "range $range"
            def number = numbers[i]
            println "number $number"
            def minusMinusPlus = range[0] < 0 && range[1] < 0 && range[2] >= 0
            def plusPlusPlus = range[0] >= 0 && range[1] >= 0 && range[2] >= 0
            def minusPlusPlus = range[0] < 0 && range[1] >= 0 && range[2] >= 0
            def minusMinusMinus = range[0] < 0 && range[1] < 0 && range[2] < 0
            if (minusMinusPlus) { // [-,-,+]
                println "1"
                if (number > range[2]) {
                    println "1.1"
                    range[2] = number
                } else if (number <= range[1]) {
                    println "1.2"
                    range[1] = number
                }
            } else if (plusPlusPlus) { // [+,+,+]
                println "2"
                if (number > range[0]) range[0] = number
            } else if (minusPlusPlus) { // [-,+,+]
                println "3"
                if (number < 0) range[1] = number
                else if (number > range[0]) range[0] = number
            } else if (minusMinusMinus) { // [-,-,-]
                println "4"
                if (number > range[0]) range[0] = number
            }
            updateProduct()
        }
        product
    }

}
