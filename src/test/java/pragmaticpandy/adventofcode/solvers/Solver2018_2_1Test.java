package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_2_1Test {

    private final Solver2018_2_1 sut = new Solver2018_2_1();

    @Test
    public void testRealInput() {
        assertEquals(
            7936,
            Integer.parseInt(
                sut.solve(new InputUtil().getStringFromResourceFile("input-2018-2.txt").get())));
    }
}
