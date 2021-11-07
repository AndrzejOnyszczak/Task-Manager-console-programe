package pl.coderslab;

import jdk.swing.interop.SwingInterOpUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static final String FILENAME = "tasks.csv";
    static String[][] tasks;

    public static void main(String[] args) {
        tasks = transferDataToTab(FILENAME);
        optionInfo(OPTIONS);
//        for (String[] option : tasks) {
//            System.out.println(Arrays.toString(option));
//        }
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            switch (input) {
                case "exit":
                    saveFile(FILENAME, tasks);
                    System.out.println(ConsoleColors.RED + " Goodbye!");
                    System.exit(0);
                    break;
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask(tasks, getNumber());
                    System.out.println("Success!, value deleted!");
                    break;
                case "list":
                    showListTab(tasks);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
            optionInfo(OPTIONS);
        }
    }


    public static void optionInfo(String[] tab) {

        System.out.println(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
    }

    public static String[][] transferDataToTab(String fileName) {
        Path directory = Paths.get(fileName);
        if (!Files.exists(directory)) {
            System.out.println("There is no file.");
            System.exit(0);
        }
        String[][] tab = null;
        try {
            List<String> newList = Files.readAllLines(directory);
            tab = new String[newList.size()][newList.get(0).split(",").length];

            for (int i = 0; i < newList.size(); i++) {
                String[] addTab = newList.get(i).split(",");
                for (int j = 0; j < addTab.length; j++) {
                    tab[i][j] = addTab[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return tab;
    }

    public static void showListTab(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Add task description");
        String decription = scan.nextLine();
        System.out.println("Add task due date, format date-month-day");
        String dueDate = scan.nextLine();
        System.out.println("Task important true or false");
        String isImportant = scan.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = decription;
        tasks[tasks.length - 1][1] = dueDate;
        tasks[tasks.length - 1][2] = isImportant;

    }
    // metoda sprawdzajaca czy wartość da się poprawnie przekształcić do postaci numerycznej
    // oraz czy jest większa lub równa zero

    public static boolean isNumberGraterEqZero(String input) {
        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    //pobieranie wartości od użytkownika za pomocą klasy Scanner z jednoczesnym sprawdzaniem
    // czy jest ona poprawna.
    public static int getNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Select number to remove from the list.");

        String number = scan.nextLine();
        while (!isNumberGraterEqZero(number)) {
            System.out.println("Incorrect argument. Please put number greater or equal 0");
            scan.nextLine();
        }
        return Integer.parseInt(number);
    }

    // metoda ktora usuwa dane zadanie z listy, sprawdza czy index nie jest większy od rozmiaru tablicy
    private static void removeTask(String[][] tab, int index) {
        try {
            if (index < tab.length) {
                tasks = ArrayUtils.remove(tab, index);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element not exist in tab");
        }
    }

    public static void saveFile(String fileName, String[][] tab) {
        Path directory = Paths.get(fileName);
//przekształcamy tablicę dwuwymiarową na jednowymiarową - tak by możliwy był zapis poszczególnych lini
        String[] lines = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
            try {
                Files.write(directory, Arrays.asList(lines));
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }

    }

}
