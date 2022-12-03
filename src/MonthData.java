import java.util.ArrayList;

public class MonthData { //хранение данных за один месяц
    ArrayList<MonthItem> rows = new ArrayList<>();

    public Integer totalMonthExpences() { //подссчет расходов за месяц
        Integer sum = 0; //сразу ставим 0 чтобы не было null
        for (int i = 0; i < rows.size(); i++) { //по всему массиву
            if (rows.get(i).isExpense) { //проверяем что расход
                sum += (rows.get(i).sumOfOne * rows.get(i).quantity);
            }
        }
        return sum;
    }

    public Integer totalMonthIncome() { //подсчет доходов за месяц
        Integer sum = 0; //сразу ставим 0 чтобы не было null
        for (int i = 0; i < rows.size(); i++) { //по всему массиву
            if (!rows.get(i).isExpense) { //проверяем что НЕрасход
                sum += (rows.get(i).sumOfOne * rows.get(i).quantity);
            }
        }
        return sum;
    }
}
