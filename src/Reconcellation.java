import java.util.HashMap;

public class Reconcellation { // отдельный класс для сервки, поскольку мне надо передать два объекта

    public void reconcellation(MonthReports allMonthReport, YearReport yearReport) {
        if (yearReport.yearReport.isEmpty()) { //проверка что загружен годовой отчет
            System.out.println("Годовой отчет не загружен!");
            return;
        }
        if (allMonthReport.allMonthReport.isEmpty()) { //проверка что загружены месячные отчеты
            System.out.println("Нет загруженных отчетов за месяц");
            return;
        }

        byte errorCount = 0; // счетчик ошибок

        //чтобы потом не запускать расчеты трат и доходов каждый раз в цикле и на проверках
        //создаю отджельные переменные для них
        HashMap<Integer,Integer> monthExpences = allMonthReport.getMonthExpences();
        HashMap<Integer,Integer> monthIncome = allMonthReport.getMonthIncome();
        HashMap<Integer,Integer> yearExpences = yearReport.getYearExpences();
        HashMap<Integer,Integer> yearIncome = yearReport.getYearIncome();

        for (int i = 1; i <= monthExpences.size(); i++) {
            Boolean isExpenseEqual = (monthExpences.get(i).equals(yearExpences.get(i))); //сравниваем расходы за месяц
            Boolean isIncomeEqual = (monthIncome.get(i).equals(yearIncome.get(i))); //сравниваем доходы за месяц
            if (!isExpenseEqual) {
                System.out.println("Обнаружено расхождение по расходам в месяце №" + i);
                errorCount++;
            }
            if (!isIncomeEqual) {
                System.out.println("Обнаружено расхождение по доходам в месяце №" + i);
                errorCount++;
            }
        }
        if (errorCount == 0) {
            System.out.println("Расхождений в данных нет");
        }
    }
}

