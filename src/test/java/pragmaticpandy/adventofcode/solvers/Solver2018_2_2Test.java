package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_2_2Test {

    private final Solver2018_2_2 sut = new Solver2018_2_2();

    @Test
    public void testRealInput() {
        assertEquals(
            "lnfqdscwjyteorambzuchrgpx",
            sut.solve(new InputUtil().getStringFromResourceFile("input-2018-2.txt").get()));
    }
}
