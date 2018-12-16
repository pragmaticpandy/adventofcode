package pragmaticpandy.adventofcode.solvers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pragmaticpandy.adventofcode.InputUtil;

public class Solver2018_5_2Test {

    private final Solver2018_5 sut = new Solver2018_5(2);

    @Test
    public void testRealInput() {
        assertEquals(
            4952,
            Integer.parseInt(
                sut.solve(new InputUtil().getStringFromResourceFile("input-2018-5.txt").get())));
    }
}
