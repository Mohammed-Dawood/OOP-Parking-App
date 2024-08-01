package se.lexicon.dao.sequencer;

public class ReservationIdSequencer {
    private static int sequencer = 0;

    public static String nextId() {
        return String.format("RES-%04d", ++sequencer);
    }

    public static void reset() {
        sequencer = 0;
    }
}
