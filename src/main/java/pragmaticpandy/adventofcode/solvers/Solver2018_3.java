package pragmaticpandy.adventofcode.solvers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableSet;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pragmaticpandy.adventofcode.InputUtil;

@RequiredArgsConstructor
public class Solver2018_3 implements Solver {

    private static final InputUtil inputUtil = new InputUtil();

    private final int problemNum;

    public String solve(@NonNull final String input) {
        final Set<Claim> claims = Stream.of(inputUtil.splitByNewlines(input))
            .map(claimString -> new Claim(claimString))
            .collect(ImmutableSet.toImmutableSet());

        final Set<Point> encounteredPoints = new HashSet<>();
        final Set<Point> conflictingPoints = new HashSet<>();
        for (final Claim claim : claims) {
            for (final Point point : claim.getContainedPoints()) {
                if (encounteredPoints.contains(point)) {
                    conflictingPoints.add(point);
                } else {
                    encounteredPoints.add(point);
                }
            }
        }

        if (problemNum == 1) {
            return String.format("%d", conflictingPoints.size());
        }

        for (final Claim claim : claims) {
            if (claim.getContainedPoints().stream().anyMatch(point -> conflictingPoints.contains(point))) {
                continue;
            }

            return claim.getId();
        }

        throw new IllegalArgumentException("No claim exists that doesn't conflict with another.");
    }

    @ToString
    private static class Claim {
        @Getter private final String id;
        private final int leftMargin;
        private final int topMargin;
        private final int width;
        private final int height;

        /**
         * Parses a claim of the form: '#1 @ 912,277: 27x20'.
         */
        public Claim(@NonNull final String claimString) {
            final String[] tokens = inputUtil.splitByWhitespace(claimString);
            final String poundId = tokens[0];
            final String marginsColon = tokens[2];
            final String size = tokens[3];
            this.id = poundId.substring(1, poundId.length());
            final String[] margins = marginsColon.substring(0, marginsColon.length() - 1).split(",");
            this.leftMargin = Integer.parseInt(margins[0]);
            this.topMargin = Integer.parseInt(margins[1]);
            final String[] sizes = size.split("x");
            this.width = Integer.parseInt(sizes[0]);
            this.height = Integer.parseInt(sizes[1]);
        }

        public Set<Point> getContainedPoints() {
            final ImmutableSet.Builder<Point> resultBuilder = ImmutableSet.builder();
            for (int x = leftMargin; x < leftMargin + width; x++) {
                for (int y = topMargin; y < topMargin + height; y++) {
                    resultBuilder.add(new Point(x, y));
                }
            }

            return resultBuilder.build();
        }
    }

    @RequiredArgsConstructor
    @EqualsAndHashCode
    private static class Point {
        private final int x;
        private final int y;
    }
}
