package com.lagostout.interviewcake

class TwoEgg {

    static int fewestDrops(int eggCount, int floorCount) {
        println "fewestDrops(eggCount $eggCount floorCount $floorCount)"
        int result
        if (eggCount == 1) {
            result = floorCount
        } else {
            int nextDropCount
            int dropCount = floorCount + 1
            int floorGroupCount = 1
            int floorGroupSize = floorCount / floorGroupCount
            while (true) {
                println "floorGroupCount $floorGroupCount"
                println "floorGroupSize $floorGroupSize"
//                nextDropCount = floorGroupCount +
//                        fewestDrops(eggCount - 1, floorGroupSize - 1)
                nextDropCount = floorGroupCount +
                        fewestDrops(eggCount - 1,
                                floorCount - floorGroupCount)
                if (nextDropCount > dropCount) {
                    result = dropCount
                    break
                }
                println "nextDropCount $nextDropCount"
                println "dropCount $dropCount"
                floorGroupCount++
                floorGroupSize = floorCount / floorGroupCount
                dropCount = nextDropCount
            }
        }
        println "result $result"
        return result
    }

}
