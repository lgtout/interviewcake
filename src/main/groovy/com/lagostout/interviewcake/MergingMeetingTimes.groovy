package com.lagostout.interviewcake

class MergingMeetingTimes {

    static public class Meeting implements Comparable<Meeting> {

        int startTime;
        int endTime;

        public Meeting(int startTime, int endTime) {
            // number of 30 min blocks past 9:00 am
            this.startTime = startTime;
            this.endTime   = endTime;
        }

        public String toString() {
            return String.format("(%d, %d)", startTime, endTime);
        }

        @Override
        int compareTo(Meeting o) {
            return startTime - o.startTime
        }

    }

    static public List<Meeting> merge(List<Meeting> meetings) {
        List<Meeting> mergedMeetings = []
        meetings = meetings.sort()
        mergedMeetings.add meetings.first()
        println "meetings $meetings"
        for (int i = 1; i < meetings.size(); ++i) {
            def meeting = meetings.get(i)
            Meeting mergedMeeting = mergedMeetings.last()
            if (meeting == mergedMeeting) continue
            if (meeting.startTime <= mergedMeeting.endTime) {
                mergedMeeting = new Meeting(
                        mergedMeeting.startTime,
                        Math.max(mergedMeeting.endTime, meeting.endTime))
                mergedMeetings.set(mergedMeetings.size() - 1, mergedMeeting)
            } else {
                mergedMeetings.add(meeting)
            }
        }
        mergedMeetings
    }

}
