package com.lagostout.interviewcake

class ProductOfOtherNumbers {

    static int[] calculateProducts(int[] numbers) {
        def products = new int[numbers.length]
//        if (numbers.length == 0) return []
        def lastIndex = numbers.length - 1
        int product = 1
        0.upto(lastIndex) {
            products[it] = product
            product = numbers[it] * product
        }
        product = 1
        (lastIndex).downto(0) {
            products[it] = product * products[it]
            product *= numbers[it]
        }
        products
    }

}
