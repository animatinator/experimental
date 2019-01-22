package com.animatinator.practice.apartments;

import com.animatinator.practice.apartments.data.Requirement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.animatinator.practice.apartments.data.Requirement.*;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class OptimalApartmentTest {

    @Test
    public void emptyStreet() {
        List<Requirement> requirements = Arrays.asList(SHOP, LIBRARY);

        assertEquals(-1, OptimalApartment.getLongestWalkFromOptimalApartment(requirements, new ArrayList<>()));
    }

    @Test
    public void exampleNeighbourhood() {
        List<Requirement> requirements = Arrays.asList(SHOP, LIBRARY, CHIPPY);

        List<List<Requirement>> neighbourhood = Arrays.asList(
                Collections.singletonList(CHIPPY),
                new ArrayList<>(),
                Collections.singletonList(LIBRARY),
                new ArrayList<>(),
                Collections.singletonList(SHOP),
                Collections.singletonList(LIBRARY),
                Arrays.asList(LIBRARY, TUBE_STOP),
                Arrays.asList(CHIPPY, TUBE_STOP));

        assertEquals(2, OptimalApartment.getLongestWalkFromOptimalApartment(requirements, neighbourhood));
    }

    @Test
    public void allInOne() {
        List<Requirement> requirements = Arrays.asList(SHOP, LIBRARY, CHIPPY);

        List<List<Requirement>> neighbourhood = Arrays.asList(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(LIBRARY, TUBE_STOP, CHIPPY, SHOP),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(CHIPPY, TUBE_STOP));

        assertEquals(0, OptimalApartment.getLongestWalkFromOptimalApartment(requirements, neighbourhood));
    }

    @Test
    public void noSolution() {
        List<Requirement> requirements = Arrays.asList(SHOP, LIBRARY, TUBE_STOP);

        List<List<Requirement>> neighbourhood = Arrays.asList(
                Collections.singletonList(CHIPPY),
                new ArrayList<>(),
                Collections.singletonList(LIBRARY),
                new ArrayList<>(),
                Collections.singletonList(SHOP),
                Collections.singletonList(LIBRARY),
                Collections.singletonList(LIBRARY),
                Collections.singletonList(CHIPPY));

        assertEquals(-1, OptimalApartment.getLongestWalkFromOptimalApartment(requirements, neighbourhood));
    }

    @Test
    public void spreeeeeeadOut() {
        List<Requirement> requirements = Arrays.asList(SHOP, LIBRARY, CHIPPY);

        List<List<Requirement>> neighbourhood = Arrays.asList(
                new ArrayList<>(),
                new ArrayList<>(),
                Collections.singletonList(CHIPPY),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Collections.singletonList(LIBRARY),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Collections.singletonList(SHOP),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());

        assertEquals(5, OptimalApartment.getLongestWalkFromOptimalApartment(requirements, neighbourhood));
    }
}