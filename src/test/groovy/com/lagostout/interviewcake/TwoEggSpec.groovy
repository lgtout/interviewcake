package com.lagostout.interviewcake

import spock.lang.Specification
import spock.lang.Unroll

class TwoEggSpec extends Specification {

    @Unroll("#data")
    def 'minimizes drops'() {

        expect:
        TwoEgg.fewestDrops(data[0], data[1]) == data[2]

        where:
        data << [
                // [eggCount, floorCount, expectedFewestDrops],
//                [1, 0, 0],
//                [1, 10, 10],
//                [1, 5, 5],
//                [2, 2, 2],
//                [2, 5, 3],
//                [2, 10, 5],
                [2, 100, 19]
        ]

    }

}
