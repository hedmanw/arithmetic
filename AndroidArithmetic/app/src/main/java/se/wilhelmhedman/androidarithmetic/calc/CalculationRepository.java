package se.wilhelmhedman.androidarithmetic.calc;

import java.util.LinkedList;
import java.util.List;

public class CalculationRepository {
    private static CalculationRepository instance = new CalculationRepository();

    private List<IArithmeticQuery> history;

    private CalculationRepository() {
        history = new LinkedList<>();
    }

    public static void addResponse(IArithmeticQuery response) {
        instance.history.add(response);
    }

    public static List<IArithmeticQuery> getHistory() {
        return instance.history;
    }
}
