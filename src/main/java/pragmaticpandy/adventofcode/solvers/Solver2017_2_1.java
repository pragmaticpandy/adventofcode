package pragmaticpandy.adventofcode.solvers;

import lombok.NonNull;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2017_2_1 implements Solver {

    public String solve(@NonNull final String input) {
        final String rows[] = input.split("\\r?\\n");
        int sum = 0;
        for (final String row : rows) {
            int rowMax = Integer.MIN_VALUE;
            int rowMin = Integer.MAX_VALUE;
            final String stringValues[] = row.split("\\s+");
            for (final String stringValue : stringValues) {
                final int value = Integer.parseInt(stringValue);
                if (value > rowMax) {
                    rowMax = value;
                }

                if (value < rowMin) {
                    rowMin = value;
                }
            }

            sum += rowMax - rowMin;
        }

        return String.format("%d", sum);
    }
}
