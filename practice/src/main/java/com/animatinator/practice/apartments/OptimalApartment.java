package com.animatinator.practice.apartments;

import com.animatinator.practice.apartments.data.Requirement;

import java.util.*;

class OptimalApartment {
    /**
     * Sweet linear-time solution (messy though due to time constraints D:).
     */
    static int getLongestWalkFromOptimalApartment(List<Requirement> requirements, List<List<Requirement>> buildings) {
        if (buildings.size() == 0) {
            return -1;
        }

        Map<Requirement, Integer> windowCoverage = new HashMap<>();
        int l = 0, r = 0;
        int bestResult = Integer.MAX_VALUE;

        for (Requirement requirement : buildings.get(0)) {
            windowCoverage.put(requirement, 1);
        }

        while (r < buildings.size()) {
            if (windowMeetsRequirements(windowCoverage, requirements)) {
                int longestWalkHere = ((r - l) + 1) / 2;
                bestResult = Math.min(bestResult, longestWalkHere);

                // Time to close the window in from the left.
                for (Requirement requirement : buildings.get(l)) {
                    if (windowCoverage.containsKey(requirement)) {
                        int current = windowCoverage.get(requirement);
                        windowCoverage.put(requirement, current - 1);
                    }
                }

                l++;
            } else {
                r++;
                if (r >= buildings.size()) break;

                for (Requirement requirement : buildings.get(r)) {
                    if (windowCoverage.containsKey(requirement)) {
                        int current = windowCoverage.get(requirement);
                        windowCoverage.put(requirement, current + 1);
                    } else {
                        windowCoverage.put(requirement, 1);
                    }
                }
            }
        }

        if (bestResult > buildings.size()) {
            return -1;
        }
        return bestResult;
    }

    private static boolean windowMeetsRequirements(Map<Requirement, Integer> windowCoverage, List<Requirement> requirements){
        for (Requirement requirement : requirements) {
            if (!windowCoverage.containsKey(requirement) || windowCoverage.get(requirement) < 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Terribly inefficient solution, just giving it a quick go for practice.
     */
    static int getLongestWalkFromOptimalApartment_bruteForce(List<Requirement> requirements, List<List<Requirement>> buildings) {
        int best = Integer.MAX_VALUE;

        for (int r = 0; r < buildings.size(); r++) {
            for (int l = 0; l <= r; l++) {
                Set<Requirement> requirementsForChecking = new HashSet<>(requirements);
                for (int b = l; b <= r; b++) {
                    for (Requirement req : buildings.get(b)) {
                        requirementsForChecking.remove(req);
                    }
                }

                if (requirementsForChecking.isEmpty()) {
                    int maxWalkHere = (r - l + 1) / 2;
                    best = Math.min(best, maxWalkHere);
                }
            }
        }

        if (best > buildings.size()) {
            return -1;
        }
        return best;
    }
}
