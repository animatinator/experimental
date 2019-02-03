package com.animatinator.practice.meetings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MinimumRoomsForScheduleTest {
    @Test
    public void noRooms() {
        assertEquals(0, MinimumRoomsForSchedule.minimumRoomsRequired(new ArrayList<>()));
    }

    @Test
    public void oneRoom() {
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting(3, 17));

        assertEquals(1, MinimumRoomsForSchedule.minimumRoomsRequired(meetings));
    }

    @Test
    public void someRooms() {
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting(3, 17));
        meetings.add(new Meeting(5, 17));
        meetings.add(new Meeting(9, 16));
        meetings.add(new Meeting(12, 15));

        assertEquals(4, MinimumRoomsForSchedule.minimumRoomsRequired(meetings));
    }

    @Test
    public void someRooms_maximumTwo() {
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting(3, 17));
        meetings.add(new Meeting(15, 21));
        meetings.add(new Meeting(18, 27));
        meetings.add(new Meeting(26, 28));

        assertEquals(2, MinimumRoomsForSchedule.minimumRoomsRequired(meetings));
    }

    @Test
    public void someRoome_maximumTwo_startsAndEndsAlign() {
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting(3, 17));
        meetings.add(new Meeting(15, 21));
        meetings.add(new Meeting(17, 27));
        meetings.add(new Meeting(21, 28));

        assertEquals(2, MinimumRoomsForSchedule.minimumRoomsRequired(meetings));
    }
}