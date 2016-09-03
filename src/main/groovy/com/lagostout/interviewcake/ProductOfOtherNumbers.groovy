package com.lagostout.interviewcake

class ProductOfOtherNumbers {

    static int[] calculateProducts(int[] numbers) {
        def products = new int[numbers.length]
        products[0] = 1
        def lastIndex = numbers.length - 1
        1.upto(lastIndex) {
            products[it] = numbers[it-1] * products[it-1]
        }
        def reverseProduct = 1
        def secondToLastIndex = lastIndex - 1
        (secondToLastIndex).downto(0) {
            reverseProduct *= numbers[it+1]
            products[it] *= reverseProduct
        }
        products
    }

}
