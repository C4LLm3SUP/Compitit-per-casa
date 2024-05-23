// enumerazione per il tipo di contatto
enum Tipologia {
    cellulare, abitazione, azienda;
}
// Classe che rappresenta una persona con informazioni di contatto
public class Persona {
    public String nome;
    public String cognome;
    public String numDiTelefono;
    public Tipologia tipo;
    public double saldo;
    public boolean nascosto;

    // Classe che rappresenta una persona con informazioni di contatto
    public Persona() {
        this.saldo = 10;
        this.nascosto = false;
    }

    public String anagrafica() {
        return String.format("Nome: %s Cognome: %s Telefono: %s Tipo: %s Saldo: %.2f Nascosto: %s%n", nome, cognome, numDiTelefono, tipo, saldo, nascosto ? "Sì" : "No");
    }
}
// Classe che rappresenta una chiamata telefonica
class Chiamata {
    // Campi dati della chiamata
    private String numeroChiamato;
    private String cognomeChiamato;
    private String nomeChiamato;
    private String nomeChiamante; // Aggiunta del campo nomeChiamante
    private String cognomeChiamante; // Aggiunta del campo cognomeChiamante
    // Campi dati della chiamata
    public Chiamata(String numeroChiamato, String cognomeChiamato, String nomeChiamato, String nomeChiamante, String cognomeChiamante) {
        this.numeroChiamato = numeroChiamato;
        this.cognomeChiamato = cognomeChiamato;
        this.nomeChiamato = nomeChiamato;
        this.nomeChiamante = nomeChiamante; // Inizializzazione del campo nomeChiamante
        this.cognomeChiamante = cognomeChiamante; // Inizializzazione del campo cognomeChiamante
    }
    // Metodi getter per ottenere i dettagli della chiamata
    public String getNumeroChiamato() {
        return numeroChiamato;
    }

    public String getCognomeChiamato() {
        return cognomeChiamato;
    }

    public String getNomeChiamato() {
        return nomeChiamato;
    }

    public String getNomeChiamante() {
        return nomeChiamante;
    }

    public String getCognomeChiamante() {
        return cognomeChiamante;
    }
    // Metodo toString per ottenere una rappresentazione testuale della chiamata
    public String toString() {
        return "Chiamato: " + numeroChiamato;
    }
}
