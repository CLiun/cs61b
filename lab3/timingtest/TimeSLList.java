package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int op = 10000;
        int base = 1000;
        AList<Integer> Ns = new AList<>();
        Ns.addLast(base);

        AList<Double> times = new AList<>();
        AList<Integer> ops = new AList<>();
        for (int i = 1; i < 8; i++) {
            Ns.addLast(2 * Ns.getLast());
        }

        for (int i = 0; i < 8; i++) {
            SLList<Integer> temp = new SLList<>();
            // Init SLList
            for (int j = 0; j < Ns.get(i); j++) {
                temp.addLast(j);
            }
            // Start timer
            Stopwatch sw = new Stopwatch();
            // Call getlast M times
            for (int call = 0; call < op; call++) {
                temp.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            ops.addLast(op);
        }
        printTimingTable(Ns, times, ops);
    }

}
