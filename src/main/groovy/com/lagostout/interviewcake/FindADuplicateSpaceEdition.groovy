package com.lagostout.interviewcake

public class FindADuplicateSpaceEdition {

    static Integer find(int[] numbers) {
        find(numbers, 0, numbers.length - 1)
    }

    static Integer find(int[] numbers,
                        int firstIndex,
                        int lastIndex) {
        if (firstIndex == lastIndex) return firstIndex
        int middleNumberIndex =
                firstIndex + (firstIndex + lastIndex)/2
        int leftCount = 0
        for (int i = firstIndex; i <= lastIndex; ++i) {
            if (numbers[i] <= middleNumberIndex) ++leftCount
            if (leftCount > middleNumberIndex) {
                lastIndex = middleNumberIndex
            } else {
                numbers = numbers[(middleNumberIndex + 1)..lastIndex]
                firstIndex = middleNumberIndex + 1
            }
        }
        return find(numbers, firstIndex, lastIndex)
    }

}
