import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthReports { //объект для хранения всех месячных отчетов
    public ArrayList<MonthData> allMonthReport = new ArrayList<>();

    public Boolean addMonth (String path) { // загрузка одного месяца
        List<String> lines = readFileContents(path); //считываем в коллекцию файл, в коллекции каждый элемент это строка из загруженного файла

        if (!lines.isEmpty()) { // проверка что удалось получить данные из скачиваемого файла
            System.out.println("Скачан файл " + Path.of(path));
        } else {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            System.out.println("Ожидаемый путь " + Path.of(path));
            return false;
        }

        MonthData monthData = new MonthData();

        for (int i = 1; i < lines.size(); i++) {
            String[] item = lines.get(i).split(","); //получил массив из строк, которые представляют собой отдельные значения в загруженном файле

            //создаем запись месячного отчета
            MonthItem monthItem = new MonthItem(item[0],
                                                Integer.parseInt(item[2]),
                                                Integer.parseInt(item[3]),
                                                Boolean.parseBoolean(item[1]));

            monthData.rows.add(monthItem); //добавляем запись (строку) в месячный отчет
        }
        allMonthReport.add(monthData);
        return true;
    }

    public void addAllMonth(String basePath) { //добавление всех отчетов
        for (int i = 1; i<=3; i++) {
            String path = basePath + "m.20210" + i + ".csv";

            //проверка что загрузка удалась, если нет, то прерывааем цикл загрузки
            Boolean result = addMonth(path);
            if (result == false) {
                break;
            }
        }
    }

    List <String> readFileContents (String path) { //вызывая метод по переданному пути в ответ получаю коллекцию строк
        try {
            return Files.readAllLines(Path.of(path)); //получаем коллекцию строк, строки разделены в исходнике переводом каретки
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public void printAllMonth() {
        if (allMonthReport.isEmpty()) {
            System.out.println("Нет загруженных отчетов за месяц");
        } else {
            System.out.println("Категория записи;  Количество;  Цена за единицу;  Признак расхода");
            for (int j = 0; j < allMonthReport.size(); j++) {
                System.out.println("Месяц " + (j+1));
                for (int i = 0; i < allMonthReport.get(j).rows.size(); i++) {
                    System.out.print  (allMonthReport.get(j).rows.get(i).itemName + ";  ");
                    System.out.print  (allMonthReport.get(j).rows.get(i).quantity + ";  ");
                    System.out.print  (allMonthReport.get(j).rows.get(i).sumOfOne + ";  ");
                    System.out.println(allMonthReport.get(j).rows.get(i).isExpense);
                }
                System.out.println();
            }
        }
    }

    public HashMap<Integer,Integer> getMonthExpences() {
        //мапа хранит номер месяца + сумма
        HashMap<Integer,Integer> monthExpences = new HashMap<>();
        for (int i = 0; i < allMonthReport.size(); i++) {
            int index = i+1;
            int amount = allMonthReport.get(i).totalMonthExpences();
            monthExpences.put(index,amount);
        }
        return monthExpences;
    }

    public HashMap<Integer,Integer> getMonthIncome() {
        //мапа хранит номер месяца + сумма
        HashMap<Integer,Integer> monthIncome = new HashMap<>();
        for (int i = 0; i < allMonthReport.size(); i++) {
            int index = i+1;
            int amount = allMonthReport.get(i).totalMonthIncome();
            monthIncome.put(index,amount);
        }
        return monthIncome;
    }


}
