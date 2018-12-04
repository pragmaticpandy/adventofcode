package pragmaticpandy.adventofcode.solvers;

import static java.util.Map.Entry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pragmaticpandy.adventofcode.InputUtil;

@RequiredArgsConstructor
public class Solver2018_4 implements Solver {

    private final int problemNum;

    public String solve(@NonNull final String input) {
        final List<ShiftRecord> records = Stream.of(new InputUtil().splitByNewlines(input))
            .map(row -> new ShiftRecord(row))
            .sorted()
            .collect(ImmutableList.toImmutableList());

        int minuteAsleep = -1;
        int currentGuard = -1;
        final Map<Integer, Integer> minsAsleepByGuard = new HashMap<>();
        final Map<Integer, Map<Integer, Integer>> numMinsAsleepByMinuteByGuard = new HashMap<>();
        for (final ShiftRecord record : records) {
            if (record.getEvent().equals(Event.BEGINS)) {
                currentGuard = record.getGuardId().get();
            } else if (record.getEvent().equals(Event.FALLS_ASLEEP)) {
                if (record.getHour() != 0) {
                    minuteAsleep = 0;
                } else {
                    minuteAsleep = record.getMinute();
                }
            } else {
                final Integer previousMinsAsleep = minsAsleepByGuard.get(currentGuard);
                final int numMinutesAsleep = record.getMinute() - minuteAsleep + 1;
                if (previousMinsAsleep == null) {
                    minsAsleepByGuard.put(currentGuard, numMinutesAsleep);
                    final Map<Integer, Integer> numMinsAsleepByMin = new HashMap<>();
                    for (int i = minuteAsleep; i < record.getMinute(); i++) {
                        numMinsAsleepByMin.put(i, 1);
                    }

                    numMinsAsleepByMinuteByGuard.put(currentGuard, numMinsAsleepByMin);
                } else {
                    minsAsleepByGuard.put(currentGuard, numMinutesAsleep + previousMinsAsleep);
                    final Map<Integer, Integer> numMinsAsleepByMin = numMinsAsleepByMinuteByGuard.get(
                        currentGuard);

                    for (int i = minuteAsleep; i < record.getMinute(); i++) {
                        final Integer previousMinsAsleepAtThisMin = numMinsAsleepByMin.get(i);
                        if (previousMinsAsleepAtThisMin == null) {
                            numMinsAsleepByMin.put(i, 1);
                        } else {
                            numMinsAsleepByMin.put(i, previousMinsAsleepAtThisMin + 1);
                        }
                    }
                }
            }
        }

        if (problemNum == 1) {
            int maxMinsAsleep = 0;
            int bestRestedGuard = -1;
            for (Entry<Integer, Integer> entry : minsAsleepByGuard.entrySet()) {
                if (entry.getValue() > maxMinsAsleep) {
                    maxMinsAsleep = entry.getValue();
                    bestRestedGuard = entry.getKey();
                }
            }

            final SleepiestMin sleepiestMin = new SleepiestMin(numMinsAsleepByMinuteByGuard.get(bestRestedGuard));
            return String.format("%d", sleepiestMin.getMinute() * bestRestedGuard);
        }

        int sleepiestGuardId = -1;
        int sleepiestMinOfHour = -1;
        int maxTimesAsleep = -1;
        for (Entry<Integer, Integer> entry : minsAsleepByGuard.entrySet()) {
            final SleepiestMin sleepiestMin = new SleepiestMin(numMinsAsleepByMinuteByGuard.get(entry.getKey()));
            if (sleepiestMin.getNumTimesAsleepAtMin() > maxTimesAsleep) {
                sleepiestGuardId = entry.getKey();
                sleepiestMinOfHour = sleepiestMin.getMinute();
                maxTimesAsleep = sleepiestMin.getNumTimesAsleepAtMin();
            }
        }

        return String.format("%d", sleepiestGuardId * sleepiestMinOfHour);
    }

    private static class SleepiestMin {
        @Getter private final int minute;
        @Getter private final int numTimesAsleepAtMin;

        public SleepiestMin(@NonNull final Map<Integer, Integer> numTimesAsleepByMin) {
            int mostSleepyMinute = -1;
            int maxTimesAsleepAtMin = -1;
            for (Entry<Integer, Integer> entry : numTimesAsleepByMin.entrySet()) {
                if (entry.getValue() > maxTimesAsleepAtMin) {
                    maxTimesAsleepAtMin = entry.getValue();
                    mostSleepyMinute = entry.getKey();
                }
            }

            this.minute = mostSleepyMinute;
            this.numTimesAsleepAtMin = maxTimesAsleepAtMin;
        }
    }

    @ToString
    private static class ShiftRecord implements Comparable<ShiftRecord> {
        private final String date;
        @Getter private final int hour;
        @Getter private final int minute;
        @Getter private final Event event;
        @Getter private final Optional<Integer> guardId;

        /**
         * Parses a shift record of these forms:
         * [1518-10-31 23:51] Guard #3023 begins shift
         * [1518-11-01 00:01] falls asleep
         * [1518-11-01 00:47] wakes up
         */
        public ShiftRecord(@NonNull final String serializedShiftRecord) {
            final String[] dateAndText = serializedShiftRecord.split("]");
            final String dateTime = dateAndText[0];
            final String[] dateAndTime = dateTime.split(" ");
            this.date = dateAndTime[0];
            this.hour = Integer.parseInt(dateAndTime[1].split(":")[0]);
            this.minute = Integer.parseInt(dateAndTime[1].split(":")[1]);
            final String text = dateAndText[1];
            if (text.equals(" falls asleep")) {
                this.event = Event.FALLS_ASLEEP;
                this.guardId = Optional.empty();
            } else if (text.equals(" wakes up")) {
                this.event = Event.WAKES_UP;
                this.guardId = Optional.empty();
            } else {
                this.event = Event.BEGINS;
                this.guardId = Optional.of(Integer.parseInt(text.split("#")[1].split(" ")[0]));
            }
        }

        @Override
        public int compareTo(@NonNull final ShiftRecord other) {
            if (date.compareTo(other.date) != 0) {
                return date.compareTo(other.date);
            }

            if (((Integer)hour).compareTo(other.hour) != 0) {
                return ((Integer)hour).compareTo(other.hour);
            }

            return ((Integer)minute).compareTo(other.minute);
        }
    }

    private static enum Event {
        BEGINS,
        FALLS_ASLEEP,
        WAKES_UP;
    }
}
