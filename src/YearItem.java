public class YearItem { //класс для хранения одной строки годового отчета
    Integer monthNum;
    Integer amount;
    Boolean isExpense;

    YearItem (Integer monthNum, Integer amount, Boolean isExpense) {
        this.monthNum = monthNum;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
