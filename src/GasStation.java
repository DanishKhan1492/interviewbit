import java.util.Arrays;
import java.util.List;

public class GasStation {
    public static int canCompleteCircuit(final List<Integer> tank, final List<Integer> gas) {
        int start = 0;
        int totalGasUsed = 0;
        int totalGasFound = 0;
        int gasRequiredBefore = 0;
        int gasFoundBefore = 0;

        for (int i = 0; i < tank.size(); i++) {
            totalGasUsed += gas.get(i);
            totalGasFound += tank.get(i);
            if (totalGasFound - totalGasUsed < 0) {
                gasRequiredBefore += totalGasUsed;
                gasFoundBefore += totalGasFound;
                totalGasUsed = 0;
                totalGasFound = 0;
                start = i + 1;
            }
        }

        int wholeGas = totalGasFound + gasFoundBefore;
        int gasRequired = totalGasUsed + gasRequiredBefore;
        if ((wholeGas - gasRequired) >= 0) return start;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(
                Arrays.asList(1, 2),
                Arrays.asList(2, 1)));
    }
}
