package com.animatinator.practice.meetings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MinimumRoomsForSchedule {
    static int minimumRoomsRequired(List<Meeting> schedule) {
        List<Meeting> sortedByStart = new ArrayList<>(schedule);
        sortedByStart.sort(Comparator.comparingInt(m -> m.startTime));
        List<Meeting> sortedByEnd = new ArrayList<>(schedule);
        sortedByEnd.sort(Comparator.comparingInt(m -> m.endTime));

        int st = 0;
        int et = 0;
        int roomsOccupied = 0;
        int maxRooms = 0;

        while (st < schedule.size() && et < schedule.size()) {
            if (sortedByStart.get(st).startTime < sortedByEnd.get(et).endTime) {
                st++;
                roomsOccupied++;

                maxRooms = Math.max(maxRooms, roomsOccupied);
            } else {
                et++;
                roomsOccupied--;
            }
        }

        return maxRooms;
    }
}
