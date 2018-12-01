package pragmaticpandy.adventofcode;;

import static java.lang.System.out;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.InputStream;
import java.util.Optional;
import com.google.common.io.ByteStreams;
import lombok.NonNull;
import lombok.SneakyThrows;

public class InputUtil {

    /**
     * Returns the file contents as a trimmed String, or empty if the file isn't found.
     */
    @SneakyThrows
    public Optional<String> getStringFromResourceFile(@NonNull final String filename) {
        final InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            return Optional.empty();
        }

        return Optional.of(new String(ByteStreams.toByteArray(inputStream), UTF_8).trim());
    }
}
