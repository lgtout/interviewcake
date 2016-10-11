package com.lagostout.interviewcake

import spock.lang.Specification
import spock.lang.Unroll

class TemperatureTrackerSpec extends Specification {

    TemperatureTracker tracker

    def setup() {
        tracker = new TemperatureTracker()
    }

    @Unroll('#temps #expected')
    def 'track temperature'(List<Integer> temps, List<Number> expected) {

        when:
        temps.each tracker.&insert

        then:
        tracker.getMax() == expected[0]
        tracker.getMin() == expected[1]
        tracker.getMean() == expected[2]
        tracker.getMode() == expected[3]

        where:
        [temps, expected] << [
                [[1], [1,1,1,1]],
                [[1,2], [2,1,1.5,2]],
                [[2,1,1,3,0,5], [5,0,2,1]],
                [[1,1,1,2,2,2,2,1,1,1], [2,1,1.4,1]]
        ]

    }

}
