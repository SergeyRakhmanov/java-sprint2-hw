import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Reconcellation reconcelate = new Reconcellation();
        MonthReports monthReports = new MonthReports(); //новый объект всех месячных отчетов
        YearReport yearReport = new YearReport(); //новый объект всех месячных отчетов

        String basePath = "resources/"; //базовый путь к отчетам

        printMenu();
        String command = scanner.nextLine();

        while (true) {//"бесконечный" цикл, завершается на вводе exit или 0

            if (command.equals("1")) { //считать все месячные отчеты
                monthReports.addAllMonth(basePath);
                printMenu();
                command = scanner.nextLine();
            }


            else if (command.equals("2")) { // считать все годовые отчеты
                yearReport.addYear(basePath);
                printMenu();
                command = scanner.nextLine();
            }


            else if (command.equals("3")) { // сверить отчеты
                reconcelate.reconcellation(monthReports,yearReport);
                printMenu();
                command = scanner.nextLine();
            }


            else if (command.equals("4")) { // вывести информацию обо всех месячных отчетах
                monthReports.printAllMonth();
                printMenu();
                command = scanner.nextLine();
            }


            else if (command.equals("5")) { // вывести информацию о годовом отчете
                yearReport.printYear();
                printMenu();
                command = scanner.nextLine();
            }


            else if (command.equals("exit")||command.equals("0")) { // выход
                System.out.println("Завершаем работу");
                return;
            }


            else {
                System.out.println("Неизвестная команда, пожалуйста, повторите ввод");
                printMenu();
                command = scanner.nextLine();
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите пункт меню: ");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("   Для выхода из программы введите exit или 0");
    }
}

