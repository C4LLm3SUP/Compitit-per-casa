import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Specifica i percorsi dei due file
        String filePath1 = "C:/Users/Francesco/Desktop/Esericizi/Gestore_Telefonico_/Gestore_Telefonico_.iml/";
        String filePath2 = "C:/Users/Francesco/Desktop/Esericizi/Ordinamento/Ordinametno.iml/";

        // Crea oggetti File per ciascun percorso
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        // Verifica l'esistenza e il tipo del primo file
        System.out.println("Verifica del primo file:");
        checkFile(file1);

        // Verifica l'esistenza e il tipo del secondo file
        System.out.println("Verifica del secondo file:");
        checkFile(file2);
    }

    // Metodo per verificare l'esistenza e il tipo di un file
    public static void checkFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("Il file esiste ed è un file.");
            } else if (file.isDirectory()) {
                System.out.println("Il file esiste ed è una directory.");
            }
        } else {
            System.out.println("Il file non esiste.");
        }
    }
}
