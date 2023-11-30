package org.algorithm.greedy;

import java.sql.Array;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 一个会议室能够安排的最多的会议
public class MeetingArrangement {
    static class Meeting{
        Time start;
        Time end;

        public Meeting(Time start, Time end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {

    }

    public static Meeting[] meetingCount(Meeting[] meetings) {
        List<Meeting> ms = new ArrayList<>();
        Meeting cur = null;
        List<Meeting> sorted = Arrays.stream(meetings).sorted(Comparator.comparing(o -> o.end)).collect(Collectors.toList());
        for (Meeting meeting: sorted) {
            if (cur == null) {
                cur = meeting;
                ms.add(meeting);
                continue;
            }
            if (meeting.start.compareTo(cur.end) > 0){
                ms.add(meeting);
                cur = meeting;
            }
        }
        return ms.toArray(Meeting[]::new);
    }
}
