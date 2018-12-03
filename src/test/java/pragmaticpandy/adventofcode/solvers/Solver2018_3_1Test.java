package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_3_1Test {

    private final Solver2018_3 sut = new Solver2018_3(1);

    @Test
    public void testRealInput() {
        assertEquals(
            104241,
            Integer.parseInt(sut.solve(new InputUtil().getStringFromResourceFile("input-2018-3.txt").get())));
    }
}
