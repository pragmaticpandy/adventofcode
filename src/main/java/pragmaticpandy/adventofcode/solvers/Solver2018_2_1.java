package pragmaticpandy.adventofcode.solvers;

import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_2_1 implements Solver {

    public String solve(@NonNull final String input) {
        int sumWithCharThatAppearsTwice = 0;
        int sumWithCharThatAppearsThrice = 0;
        for (final String id : new InputUtil().splitByNewlines(input)) {
            Map<Character, Integer> numOfAppearancesByChar = new HashMap<>();
            for (int index = 0; index < id.length(); index++) {
                final Character character = id.charAt(index);
                if (numOfAppearancesByChar.containsKey(character)) {
                    numOfAppearancesByChar.put(character, numOfAppearancesByChar.get(character) + 1);
                } else {
                    numOfAppearancesByChar.put(character, 1);
                }
            }

            if (numOfAppearancesByChar.values().stream().anyMatch(integer -> integer == 2)) {
                sumWithCharThatAppearsTwice += 1;
            }

            if (numOfAppearancesByChar.values().stream().anyMatch(integer -> integer == 3)) {
                sumWithCharThatAppearsThrice += 1;
            }
        }

        return String.format("%d", sumWithCharThatAppearsTwice * sumWithCharThatAppearsThrice);
    }
}
