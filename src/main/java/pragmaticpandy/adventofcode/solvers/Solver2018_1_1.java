package pragmaticpandy.adventofcode.solvers;

import lombok.NonNull;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_1_1 implements Solver {

    public String solve(@NonNull final String input) {
        int sum = 0;
        for (final String variationString : new InputUtil().splitByNewlines(input)) {
            sum += Integer.parseInt(variationString);
        }

        return String.format("%d", sum);
    }
}
