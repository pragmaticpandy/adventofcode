package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_5_1Test {

    private final Solver2018_5 sut = new Solver2018_5(1);

    @Test
    public void testRealInput() {
        assertEquals(
            9462,
            Integer.parseInt(
                sut.solve(new InputUtil().getStringFromResourceFile("input-2018-5.txt").get())));
    }
}
