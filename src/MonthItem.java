public class MonthItem { //одна запись из месячного отчета
    String itemName;
    Integer quantity;
    Integer sumOfOne;
    Boolean isExpense;

    MonthItem (String itemName, Integer quantity, Integer sumOfOne, Boolean isExpense) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
