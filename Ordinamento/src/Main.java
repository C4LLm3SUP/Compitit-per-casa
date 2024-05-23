import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // Ho eseguito un paio di modifiche rispetto alla consegna originale
        // in modo che sia più veloce per l'utente verificare il funzionamento del codice
        System.out.println("Seleziona il tipo di inserimento:");
        System.out.println("1. Inserimento manuale");
        System.out.println("2. Numeri casuali");
        System.out.println("3. Numeri prefissati");
        int choice = scanner.nextInt();

        int[] array;
        switch (choice) {
            case 1:
                array = insertManual();
                break;
            case 2:
                array = insertRandom();
                break;
            case 3:
                array = new int[]{9, 4, 6, 5, 2, 10, 7, 1, 8}; // Numeri prefissati
                break;
            default:
                System.out.println("Scelta non valida. Inserimento manuale.");
                array = insertManual();
        }

        int[] sortedArray = sortEvenNumbers(array);

        System.out.print("Array originale: ");
        printArray(array);
        System.out.print("Array ordinato per numeri pari: ");
        printArray(sortedArray);

        scanner.close();
    }

    // Metodo per l'inserimento manuale degli elementi nell'array
    public static int[] insertManual() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci la lunghezza dell'array (minimo 8, massimo 10):");
        int length = scanner.nextInt();
        if (length < 8 || length > 10) {
            System.out.println("Lunghezza non valida. Inserimento manuale di 8 elementi.");
            length = 8;
        }

        int[] array = new int[length];
        System.out.println("Inserisci gli elementi:");
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        scanner.close();
        return array;
    }

    // Metodo per l'inserimento di numeri casuali nell'array
    public static int[] insertRandom() {
        Random random = new Random();
        int length = random.nextInt(8, 11); // Lunghezza casuale tra 8 e 11
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    // Metodo per ordinare i numeri pari nell'array mantenendo la posizione degli elementi dispari
    public static int[] sortEvenNumbers(int[] array) {
        int[] evens = extractEvens(array); // Estrai i numeri pari
        bubbleSort(evens); // Ordina i numeri pari

        // Reinserisci i numeri pari ordinati nelle loro posizioni originali nell'array
        int evenIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                array[i] = evens[evenIndex++];
            }
        }
        return array;
    }

    // Estrai i numeri pari dall'array originale
    public static int[] extractEvens(int[] array) {
        int evenCount = 0;
        for (int num : array) {
            if (num % 2 == 0) {
                evenCount++;
            }
        }

        int[] evens = new int[evenCount];
        int index = 0;
        for (int num : array) {
            if (num % 2 == 0) {
                evens[index++] = num;
            }
        }
        return evens;
    }

    // Implementazione del Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Metodo per la stampa di un array
    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(array[i]);
        }
        System.out.println(" ]");
    }
}
