package com.lagostout.interviewcake

import spock.lang.Specification
import spock.lang.Unroll
import com.lagostout.interviewcake.RectangularLove.Rectangle

import static com.lagostout.interviewcake.RectangularLove.Rectangle.r

class RectangularLoveSpec extends Specification {

    @Unroll('#r1 #r2 #expected')
    def 'finds intersection rectangle'(Rectangle r1, Rectangle r2, Rectangle expected) {
        expect:
        RectangularLove.findIntersection(r1, r2) == expected
        where:
        [r1, r2, expected] << [
                // point and line
                [r(1,1,2,0), r(2,2,0,0), null], // no intersection
                [r(1,1,2,0), r(2,1,0,0), r(2,1,0,0)], // intersection
                // no intersection
                [r(2,2,1,1), r(4,4,1,1), null],
                // one or both rectangles are points
                [r(1,1,0,0), r(1,2,0,0), null], // both; no intersection
                [r(1,1,0,0), r(2,2,0,0), null], // both; no intersection
                [r(1,1,0,0), r(2,2,1,1), null], // one; no intersection
                [r(1,1,2,2), r(2,2,0,0), r(2,2,0,0)], // one; intersection
                [r(1,1,0,0), r(1,1,0,0), r(1,1,0,0)], // both; intersection
                // exact overlap
                [r(2,2,1,1), r(2,2,1,1), r(2,2,1,1)], // exact overlap
                [r(2,2,1,1), r(2,2,1,2), r(2,2,1,1)], // exact overlap on left, right, bottom
                [r(2,2,1,1), r(2,1,1,2), r(2,2,1,1)], // exact overlap on left, right, top
                [r(2,2,1,1), r(1,2,2,1), r(2,2,1,1)], // exact overlap on right, bottom, top
                [r(2,2,1,1), r(2,2,2,1), r(2,2,1,1)], // exact overlap on left, bottom, top
                [r(2,2,1,1), r(2,2,2,1), r(2,2,1,1)], // exact overlap on left, bottom, top
                // 2 corners of one rectangle contained within the other
                [r(2,2,3,3), r(3,3,3,1), r(3,3,2,1)], // other 2 corners on top side of first rectangle
                [r(2,2,3,3), r(3,3,1,3), r(3,3,1,2)], // other 2 corners on right side of first rectangle
                // 4 corners of one rectangle contained within the other
                [r(2,2,3,3), r(3,3,1,1), r(3,3,1,1)], // second rectangle completely contained in first
                // 1 corner of one rectangle contained within the other
                [r(2,2,2,2), r(1,1,2,2), r(2,2,1,1)], // top right corner of second rectangle contained
                [r(2,2,2,2), r(3,3,2,2), r(3,3,1,1)], // top left corner of second rectangle contained
                [r(2,2,2,2), r(1,3,2,2), r(2,3,1,1)], // bottom left corner of second rectangle contained
                // overlap is a single edge
                [r(1,2,1,1), r(1,1,1,1), r(1,2,1,0)], // horizontal edge
                [r(1,1,1,1), r(2,1,1,1), r(2,1,0,1)], // vertical edge
        ]
    }

}
