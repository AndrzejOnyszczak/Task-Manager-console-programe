package pl.coderslab;

public class TaskManager {
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static final String FILETASKS = "tasks.csv";
    static String[][] tasks;

    public static void main(String[] args) {
        optionInfo(OPTIONS);

    }

    public static void optionInfo(String[] tab) {

        System.out.println(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
    }



}
