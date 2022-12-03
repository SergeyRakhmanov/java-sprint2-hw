import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearReport { //объект для хранения годового отчета
    public ArrayList<YearItem> yearReport = new ArrayList<>();

    public void addYear(String basePath) { //загрузка годового отчета
        String path = basePath + "y.2021.csv";
        List<String> lines = readFileContents(path); //считываем в коллекцию файл, в коллекции каждый элемент это строка из загруженного файла

        for (int i = 1; i < lines.size(); i++) {
            String[] item = lines.get(i).split(","); //получил массив из строк, которые представляют собой отдельные значения в загруженном файле

            //преобразуем полученный массив из строк в отдельные переменные для создания записи месячнеого отчета
            Integer monthNum = Integer.parseInt(item[0]);
            Integer amount = Integer.parseInt(item[1]);
            Boolean isExpense = Boolean.parseBoolean(item[2]);
            YearItem yearItem = new YearItem(monthNum, amount, isExpense); //создаем запись месячного отчета
            yearReport.add(yearItem); //добавляем запись (строку) в годовой отчет
        }
    }

    List<String> readFileContents (String path) { //вызывая метод по переданному пути в ответ получаю коллекцию строк
        try {
            return Files.readAllLines(Path.of(path)); //получаем коллекцию строк, строки разделены в исходнике переводом каретки
        } catch (IOException e) {
            //to do подумать над тем чтобы ошибка была в зависимости от того месячный или годовой файл читаем
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }

    public void printYear() {
        if (yearReport.isEmpty()) {
            System.out.println("Годовой отчет не загружен!");
        } else {
            System.out.println("Месяц №;  Сумма;  Признак расхода");
            for (int j = 0; j < yearReport.size(); j++) {
                System.out.print  ("Месяц " + yearReport.get(j).monthNum + ";  ");
                System.out.print  (yearReport.get(j).amount + ";  ");
                System.out.println(yearReport.get(j).isExpense);
            }
        }
    }

    public HashMap<Integer,Integer> getYearExpences() {
        HashMap<Integer,Integer> yearExpences = new HashMap<>();
        for (int i = 0; i < yearReport.size(); i++) {
            if (yearReport.get(i).isExpense) { //если расход
                int index = yearReport.get(i).monthNum;
                int amount = yearReport.get(i).amount;
                yearExpences.put(index,amount);
            }
        }
        return yearExpences;
    }

    public HashMap<Integer,Integer> getYearIncome() {
        HashMap<Integer,Integer> yearIncome = new HashMap<>();
        for (int i = 0; i < yearReport.size(); i++) {
            if (!yearReport.get(i).isExpense) { //если НЕ расход
                int index = yearReport.get(i).monthNum;
                int amount = yearReport.get(i).amount;
                yearIncome.put(index,amount);
            }
        }
        return yearIncome;
    }

}
