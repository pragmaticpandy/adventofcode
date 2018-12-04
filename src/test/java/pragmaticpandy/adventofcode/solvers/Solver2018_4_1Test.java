package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_4_1Test {

    private final Solver2018_4 sut = new Solver2018_4(1);

    @Test
    public void testRealInput() {
        assertEquals(
            4716,
            Integer.parseInt(sut.solve(new InputUtil().getStringFromResourceFile("input-2018-4.txt").get())));
    }
}
