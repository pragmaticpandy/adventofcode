package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_1_2Test {

    private final Solver2018_1_2 sut = new Solver2018_1_2();

    @Test
    public void testRealInput() {
        assertEquals(
            80598,
            Integer.parseInt(
                sut.solve(new InputUtil().getStringFromResourceFile("input-2018-1.txt").get())));
    }
}
