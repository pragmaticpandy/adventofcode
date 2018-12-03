package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_3_2Test {

    private final Solver2018_3 sut = new Solver2018_3(2);

    @Test
    public void testRealInput() {
        assertEquals(
            806,
            Integer.parseInt(sut.solve(new InputUtil().getStringFromResourceFile("input-2018-3.txt").get())));
    }
}
