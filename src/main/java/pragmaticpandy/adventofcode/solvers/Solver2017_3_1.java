package pragmaticpandy.adventofcode.solvers;

import java.util.Set;
import com.google.common.collect.ImmutableSet;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class Solver2017_3_1 implements Solver {

    public String solve(@NonNull final String input) {
        final int target = Integer.parseInt(input);
        Ring currentRing = new Ring(0, 0, 1, 1, 1);

        // Find the concentric ring that our target is in.
        while (currentRing.getEndInclusive() < target) {
            currentRing = currentRing.nextRing();
        }

        return String.format("%d", currentRing.getDistance(target));
    }

    @RequiredArgsConstructor
    @ToString
    private static class Ring {
        private final int midpointDistanceFromOne;
        private final int cornerDistanceFromOne;
        private final int height;
        private final int startInclusive;
        @Getter private final int endInclusive;

        public Ring nextRing() {
            return new Ring(
                midpointDistanceFromOne + 1,
                cornerDistanceFromOne + 2,
                height + 2,
                endInclusive + 1,
                (4 * height) + 4 + endInclusive);
        }

        @ToString.Include
        private Set<Integer> getCorners() {
            return ImmutableSet.of(
                endInclusive,
                endInclusive - (1 * (height - 1)),
                endInclusive - (2 * (height - 1)),
                endInclusive - (3 * (height - 1)));
        }

        @ToString.Include
        private Set<Integer> getMidpoints() {
            return getCorners().stream()
                .map(corner -> corner - (height / 2))
                .collect(ImmutableSet.toImmutableSet());
        }

        public int getDistance(final int target) {
            if (getCorners().contains(target)) {
                return cornerDistanceFromOne;
            }

            if (getMidpoints().contains(target)) {
                return midpointDistanceFromOne;
            }

            /*
             * Find the minimum distance our target is from either a midpoint or corner. We compare
             * against both midpoints and corners beceause this nicely handles the spot in our ring
             * where the number line is broken.
             */
            int minDist = Integer.MAX_VALUE;
            boolean minDistIsFromCorner = false;
            
            for (final Integer corner : getCorners()) {
                final int dist = Math.abs(corner - target);
                if (dist < minDist) {
                    minDist = dist;
                    minDistIsFromCorner = true;
                }
            }

            for (final Integer midpoint : getMidpoints()) {
                final int dist = Math.abs(midpoint - target);
                if (dist < minDist) {
                    minDist = dist;
                    minDistIsFromCorner = false;
                }
            }

            if (minDistIsFromCorner) {
                return cornerDistanceFromOne - minDist;
            } else {
                return midpointDistanceFromOne + minDist;
            }
        }
    }
}
