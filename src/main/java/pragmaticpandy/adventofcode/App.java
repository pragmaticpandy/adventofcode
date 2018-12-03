package pragmaticpandy.adventofcode;

import static java.lang.System.out;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.Map;
import java.util.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.ByteStreams;
import pragmaticpandy.adventofcode.solvers.*;

public class App {

    private static final Map<String, Solver> solversByName = ImmutableMap.<String, Solver>builder()
        .put("2017-1-1", new Solver2017_1(1))
        .put("2017-1-2", new Solver2017_1(2))
        .put("2017-2-1", new Solver2017_2_1())
        .put("2017-2-2", new Solver2017_2_2())
        .put("2017-3-1", new Solver2017_3_1())
        .put("2017-3-2", new Solver2017_3_2())
        .put("2018-1-1", new Solver2018_1_1())
        .put("2018-1-2", new Solver2018_1_2())
        .build();

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            out.println("No arguments! I need a problem to solve, for example, '2017-2-1'");
            return;
        }

        final Solver solver = solversByName.get(args[0]);
        if (solver == null) {
            out.println(String.format("No solver exists for %s.", args[0]));
            return;
        }

        final String inputFilename = String.format(
            "input-%s.txt", args[0].substring(0, args[0].length() - 2));

        final Optional<String> input = new InputUtil().getStringFromResourceFile(inputFilename);
        if (!input.isPresent()) {
            out.println(String.format("Input %s not found!", inputFilename));
            return;
        }

        out.println("Result: " + solver.solve(input.get()));
    }
}
