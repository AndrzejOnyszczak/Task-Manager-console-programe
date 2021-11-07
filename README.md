# Task-Manager-console-programe
METODA WYŚWIETLAJĄCA OPCJE DOSTĘPNE W PROGRAMIE

Pierwsza metoda ma wyświetlać dostępne w programie opcje do wykonania.

Dla nagłówka Please select an option: wybieramy kolor ConsoleColors.BLUE.

Opcje które mają być dostępne to:

    add
    remove
    list
    exit

Dla uproszczenia możemy je umieścić w tablicy typu String. Wywołaj metodę w metodzie main programu.

METODA POBIERAJĄCA DANE Z PLIKU

W metodzie main jako pierwszą wywołaj metodę, która z załączonego pliku tasks.csv wczyta dane do tablicy

static String[][] tasks;

Musisz skorzystać z odpowiedniej pętli do przetwarzania wierszy. Za pomocą metody split klasy String, możesz podzielić wiersz na poszczególne jego elementy.

Rozmiar tablicy musisz określić na podstawie ilości wierszy w pliku
Pobieranie wybranej akcji

Za pomocą klasy Scanner pobierz od użytkownika, jaka opcja programu ma zostać wywołana. W zależności od wybranej opcji wywołasz konkretną metodę. Możesz wykorzystać poniższy snippet:

switch (input) {

    case "add":

        addTask();

        break;

// other options

    default:

        System.out.println("Please select a correct option.");

}
