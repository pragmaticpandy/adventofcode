package pragmaticpandy.adventofcode.solvers;

import static java.lang.System.out;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pragmaticpandy.adventofcode.InputUtil;

@RequiredArgsConstructor
public class Solver2017_1 implements Solver {
    
    final int problemNum;

    public String solve(@NonNull final String input) {
		int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            final int thisInt = Character.getNumericValue(input.charAt(i));
            final int intToCompare = Character.getNumericValue(
				input.charAt((i + (getNumDigitsToLookForward(input.length()))) % input.length()));
            if (thisInt == intToCompare) {
                sum += thisInt;
            }
        }

        return String.format("%d", sum);
    }

    private int getNumDigitsToLookForward(final int inputLength) {
        if (problemNum == 1) {
            return 1;
        } else if (problemNum == 2) {
            return inputLength / 2;
        } else {
            final String message = String.format("Only problems 1 and 2 are supported, not %d.");
            out.println(message);
            throw new IllegalArgumentException(message);
        }
    }
}
