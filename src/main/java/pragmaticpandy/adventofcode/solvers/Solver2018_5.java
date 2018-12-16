package pragmaticpandy.adventofcode.solvers;

import static java.lang.Character.toLowerCase;
import java.util.ArrayDeque;
import java.util.Deque;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pragmaticpandy.adventofcode.InputUtil;

@RequiredArgsConstructor
public class Solver2018_5 implements Solver {

    private final int problemNum;

    public String solve(@NonNull final String input) {
        if (problemNum == 1) {
            return String.format("%d", getSizeAfterReacting(input));
        }

        return String.format(
            "%d",
            input.chars()

                // The above gives us an IntStream, so first convert to Stream<Character>
                .mapToObj(i -> (char)i)

                // For each unit, get the reacted size when all units of its type are omitted.
                .map(c -> toLowerCase(c))
                .distinct()
                .mapToInt(
                    c -> getSizeAfterReacting(
                        input.chars()
                            .mapToObj(i -> (char)i)
                            .filter(ch -> toLowerCase(ch) != c)
                            .collect(
                                StringBuilder::new,
                                StringBuilder::appendCodePoint,
                                StringBuilder::append)
                            .toString()))

                // And finally, return the smallest of these.
                .min()
                .getAsInt());
    }

    private int getSizeAfterReacting(@NonNull final String input) {
        final Deque<Character> leftStack = new ArrayDeque<>();
        final Deque<Character> rightStack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            leftStack.addFirst(input.charAt(i));
        }

        while (!leftStack.isEmpty()) {
            if (rightStack.isEmpty()) {
                rightStack.addFirst(leftStack.removeFirst());
            }

            final char leftChar = leftStack.peekFirst();
            final char rightChar = rightStack.peekFirst();
            if (rightChar != leftChar && toLowerCase(rightChar) == toLowerCase(leftChar)) {
                rightStack.removeFirst();
                leftStack.removeFirst();
            } else {
                rightStack.addFirst(leftStack.removeFirst());
            }
        }

        return rightStack.size();
    }
}
