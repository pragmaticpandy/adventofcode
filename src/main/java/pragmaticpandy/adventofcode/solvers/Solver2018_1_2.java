package pragmaticpandy.adventofcode.solvers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_1_2 implements Solver {

    public String solve(@NonNull final String input) {
        final List<Integer> frequencyChanges = Stream.of(new InputUtil().splitByNewlines(input))
            .map(frequencyChangeString -> Integer.parseInt(frequencyChangeString))
            .collect(ImmutableList.toImmutableList());

        int currentFrequency = 0;
        Set<Integer> encounteredFrequencies = new HashSet<>();
        encounteredFrequencies.add(currentFrequency);
        while (true) {
            for (final int frequencyChange : frequencyChanges) {
                currentFrequency += frequencyChange;
                if (encounteredFrequencies.contains(currentFrequency)) {
                    return String.format("%d", currentFrequency);
                }

                encounteredFrequencies.add(currentFrequency);
            }
        }
    }
}
