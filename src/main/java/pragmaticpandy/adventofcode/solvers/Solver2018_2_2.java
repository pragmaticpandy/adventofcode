package pragmaticpandy.adventofcode.solvers;

import lombok.NonNull;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_2_2 implements Solver {

    public String solve(@NonNull final String input) {
        final String[] ids = new InputUtil().splitByNewlines(input);
        for (int indexOfId1 = 0; indexOfId1 < ids.length - 1; indexOfId1++) {
            for (int indexOfId2 = indexOfId1 + 1; indexOfId2 < ids.length; indexOfId2++) {
                final String id1 = ids[indexOfId1];
                final String id2 = ids[indexOfId2];
                if (id1.length() != id2.length()) {
                    continue;
                }

                for (int indexToRemove = 0; indexToRemove < id1.length(); indexToRemove++) {
                    final String id1prime = id1.substring(0, indexToRemove)
                        + id1.substring(indexToRemove + 1, id1.length());

                    final String id2prime = id2.substring(0, indexToRemove)
                        + id2.substring(indexToRemove + 1, id2.length());

                    if (id1prime.equals(id2prime)) {
                        return id1prime;
                    }
                }
            }
        }

        throw new IllegalArgumentException("No solution exists!");
    }
}
