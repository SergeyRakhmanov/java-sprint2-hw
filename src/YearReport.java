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

        if (!lines.isEmpty()) { // проверка что удалось получить данные из скачиваемого файла
            System.out.println("Скачан файл " + Path.of(path));
        } else {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            System.out.println("Ожидаемый путь " + Path.of(path));
            return;
        }

        for (int i = 1; i < lines.size(); i++) {
            String[] item = lines.get(i).split(","); //получил массив из строк, которые представляют собой отдельные значения в загруженном файле

            //создаем запись месячного отчета
            YearItem yearItem = new YearItem(Integer.parseInt(item[0]),
                                             Integer.parseInt(item[1]),
                                             Boolean.parseBoolean(item[2]));

            yearReport.add(yearItem); //добавляем запись (строку) в годовой отчет
        }
    }

    List<String> readFileContents (String path) { //вызывая метод по переданному пути в ответ получаю коллекцию строк
        try {
            return Files.readAllLines(Path.of(path)); //получаем коллекцию строк, строки разделены в исходнике переводом каретки
        } catch (IOException e) {
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
