package com.lagostout.interviewcake

import org.apache.commons.math3.random.RandomDataGenerator
import spock.lang.Specification
import com.lagostout.interviewcake.MergingMeetingTimes.Meeting
import spock.lang.Unroll

import static org.apache.commons.lang3.Range.between;
import org.apache.commons.lang3.Range;

class MergingMeetingTimesSpec extends Specification {

    @Unroll("#data")
    def 'merges meeting times - random'() {

        expect:
        MergingMeetingTimes.merge(data) == bruteForceMergeMeetingTimes(data)

        where:
        data << generateRandomMeetingTimes(50, between(1,3), between(-3,3))
//        data << generateRandomMeetingTimes()

    }

    @Unroll("#data")
    def 'merges meeting times - non-random'() {

        expect:
        MergingMeetingTimes.merge(toMeetings(data.get(0))) ==
                toMeetings(data.get(1))
//                bruteForceMergeMeetingTimes(toMeetings(data.get(0)))

        where:
        data << [
                [[[3, 4], [3, 4]], [[3, 4]]],
//                [[[1, 2], [2, 3]], [[1, 3]]],
//                [[[1, 5], [2, 3]], [[1, 5]]],
//                [[[1, 10], [2, 6], [3, 5], [7, 9]], [[1, 10]]]
        ]

    }

    List<Meeting> toMeetings(List<List<Integer>> meetings) {
        meetings.collect {
            new Meeting(it[0] as int, it[1] as int)
        }
    }

    List<List<Meeting>> generateRandomMeetingTimes(
            int testCaseCount = 10,
            Range<Integer> meetingCountRange = between(1,5),
            Range<Integer> timeRange = between(-1000, 1000)) {
        def testCases = []
        def random = new RandomDataGenerator()
        random.reSeed(1)
        testCaseCount.times {
            def meetings = []
            def meetingCount = random.nextInt(meetingCountRange.minimum,
                    meetingCountRange.maximum)
            meetingCount.times {
                def start = random.nextInt(timeRange.minimum, timeRange.maximum - 1)
                def end = random.nextInt(start + 1, timeRange.maximum)
                def meeting = new Meeting(start, end)
                meetings.add meeting
            }
            testCases.add meetings
        }
        testCases
    }

    List<Meeting> bruteForceMergeMeetingTimes(List<Meeting> mt) {
        def meetingTimes = mt.collect()
        def mergedMeetings = []
        def meetingsAlreadyMerged = new HashSet<Meeting>()
        while (true) {
            def didMerge = false
            for (int i = 0; i < meetingTimes.size(); ++i) {
                Meeting meeting1 = meetingTimes[i]
                for (int j = i + 1; j < meetingTimes.size(); ++j) {
                    Meeting meeting2 = meetingTimes[j]
                    if ((meeting1.startTime >= meeting2.startTime &&
                            meeting1.startTime <= meeting2.endTime) ||
                            (meeting2.startTime >= meeting1.startTime &&
                                    meeting2.startTime <= meeting1.endTime)) {
                        Meeting mergedMeeting = new Meeting(
                                Math.min(meeting1.startTime, meeting2.startTime),
                                Math.max(meeting1.endTime, meeting2.endTime));
                        mergedMeetings.add mergedMeeting
                        meetingsAlreadyMerged.addAll([meeting1, meeting2])
                        didMerge = true
                    }
                }
            }
            if (!didMerge) break
            meetingTimes.removeAll meetingsAlreadyMerged
            meetingTimes.addAll mergedMeetings
            meetingsAlreadyMerged.clear()
        }
        return meetingTimes.sort()
    }

}
