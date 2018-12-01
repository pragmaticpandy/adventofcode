package pragmaticpandy.adventofcode.solvers;

import lombok.NonNull;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2017_2_2 implements Solver {

    public String solve(@NonNull final String input) {
        final String rows[] = input.split("\\r?\\n");
        int sum = 0;
        for (final String row : rows) {
            final String stringValues[] = row.split("\\s+");
            for (int i = 0; i < stringValues.length; i++) {
                for (int j = 0; j < stringValues.length; j++) {
                    if (j == i) {
                        continue;
                    }

                    final int dividend = Integer.parseInt(stringValues[i]);
                    final int divisor = Integer.parseInt(stringValues[j]);

                    if (divisor == 0) {
                        continue;
                    }

                    if (dividend % divisor == 0) {
                        sum += dividend / divisor;
                    }
                }
            }
        }

        return String.format("%d", sum);
    }
}
