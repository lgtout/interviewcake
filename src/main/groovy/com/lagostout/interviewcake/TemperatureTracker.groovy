package com.lagostout.interviewcake

import static org.apache.commons.lang3.Range.between
import org.apache.commons.lang3.Range

// https://www.interviewcake.com/question/java/temperature-tracker

class TemperatureTracker {

    Range<Integer> tempRange = between(0,110)
    int min = tempRange.maximum + 1
    int max = tempRange.minimum - 1
    int[] frequency = new int[tempRange.maximum + 1]
    int mode
    int count
    int sum

    public void insert(int temp) {
        max = Math.max(temp, max)
        min = Math.min(temp, min)
        frequency[temp] += 1
        mode = frequency[temp] >= frequency[mode] ? temp : mode
        count++
        sum += temp
    }

    public int getMax() {
        max
    }

    public int getMin() {
        min
    }

    public double getMean() {
        (sum as double) / count
    }

    public int getMode() {
        mode
    }

}
