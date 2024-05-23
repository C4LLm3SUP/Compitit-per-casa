import java.util.Scanner;


import static Tools.Utility.*;


public class Main {
    private static final String PASSWORD = "password";
    private static final int MAX_CHIAMATE = 100;
    private static Chiamata[] elencoChiamate = new Chiamata[MAX_CHIAMATE];
    private static int numChiamate = 0;


    public static void main(String[] args) {
        // Inizializzazione delle variabili
        int contacontatti = 0;
        final int nContratti = 10;
        boolean uscita = false;
        Scanner tastiera = new Scanner(System.in);
        Persona[] gestore = new Persona[nContratti];
        String[] opzioni = {"Gestore telefonico", "1 -Inserimento", "2 -Visualizza", "3 - Ricerca", "4 - Modifica", "5 - Cancella", "6 - Chiamata", "7 - Ricarica", "8 - Ordinamento", "9 - Gestisci contatto nascosto", "10 - Visualizza elenco chiamate", "11 - Fine"};
        // Menu principale
        do {
            switch (menu(opzioni, tastiera)) {
                case 1:
                    // Inserimento nuovo contatto
                    if (contacontatti < nContratti) {
                        Persona NuovoContatto = LeggiContatto(true, tastiera);
                        if (!Presente(gestore, NuovoContatto, contacontatti)) {
                            gestore[contacontatti] = NuovoContatto;
                            contacontatti++;
                        } else {
                            System.out.println("Il contatto è già presente nella collezione.");
                        }
                    } else {
                        System.out.println("Non ci sono più contratti da vendere");
                    }
                    break;
                case 2:
                    // Visualizzazione dei contatti
                    if (contacontatti > 0) {
                        // Come specificato nella veriifica chiedo se vuole visualizzare anche i contatti nascosti
                        System.out.println("Vuoi visualizzare i contatti nascosti? (S/N)");
                        String scelta = tastiera.nextLine();
                        if (scelta.equalsIgnoreCase("S")) {
                            // Per visualizzare i contatti nascosti chiedo una password
                            System.out.println("Inserisci la password:");
                            String password = tastiera.nextLine();
                            if (password.equals(PASSWORD)) {
                                Visualizza(gestore, contacontatti, true);
                            } else {
                                System.out.println("Password errata.");
                            }
                        } else {
                            Visualizza(gestore, contacontatti, false);
                        }
                    } else {
                        System.out.println("Non ci sono contatti da visualizzare");
                    }
                    break;
                case 3:
                    // Ricerca di un contatto
                    if (contacontatti > 0) {
                        Persona Supporto = LeggiContatto(false, tastiera);
                        if (Presente(gestore, Supporto, contacontatti)) {
                            System.out.println("Il contatto è presente");
                        } else {
                            System.out.println("Il contatto non è presente");
                        }
                    } else {
                        System.out.println("Non ci sono contatti da visualizzare");
                    }
                    break;
                case 4:
                    // Modifica del numero di telefono di un contatto
                    if (contacontatti > 0) {
                        Persona Supporto = LeggiContatto(false, tastiera);
                        int posizione = posNumero(gestore, Supporto, contacontatti);
                        if (posizione != -1) {
                            System.out.println("Inserisci il numero di telefono");
                            gestore[posizione].numDiTelefono = tastiera.nextLine();
                        } else {
                            System.out.println("Il contatto non è presente");
                        }
                    } else {
                        System.out.println("Non ci sono contatti da visualizzare");
                    }
                    break;
                case 5:
                    // Cancellazione di un contatto
                    if (contacontatti > 0) {
                        Persona Supporto = LeggiContatto(false, tastiera);
                        int posizione = posNumero(gestore, Supporto, contacontatti);
                        if (posizione != -1) {
                            contacontatti = cancellaContatto(gestore, posizione, contacontatti);
                        } else {
                            System.out.println("Il contatto non è presente");
                        }
                    } else {
                        System.out.println("Non ci sono contatti da visualizzare");
                    }
                    break;
                case 6:
                    // Effettuazione di una chiamata
                    if (contacontatti > 0) {
                        System.out.println("Chi sta chiamando?");
                        Persona chiamante = LeggiContatto(false, tastiera);
                        System.out.println("Chi vuoi chiamare?");
                        Persona contattoChiamata = LeggiContatto(false, tastiera);


                        // Controllo se il chiamante sta cercando di chiamare se stesso
                        if (chiamante.nome.equalsIgnoreCase(contattoChiamata.nome) && chiamante.cognome.equalsIgnoreCase(contattoChiamata.cognome)) {
                            System.out.println("Non puoi chiamare te stesso!");
                            break;
                        }


                        int posizioneContatto = posNumero(gestore, contattoChiamata, contacontatti);
                        if (posizioneContatto != -1) {
                            if (gestore[posizioneContatto].nascosto) {
                                System.out.println("Inserisci la password per contatti nascosti:");
                                String password = tastiera.nextLine();
                                if (!password.equals(PASSWORD)) {
                                    System.out.println("Password errata.");
                                    break;
                                }
                            }
                            // Effettua la chiamata
                            double costoChiamata = 0.50;
                            double saldoRimanente = effettuaChiamata(gestore, posizioneContatto, costoChiamata);
                            if (saldoRimanente != -1) {
                                System.out.println("Chiamata effettuata con successo. Saldo rimanente: " + saldoRimanente);
                                // Registra il contatto chiamato
                                elencoChiamate[numChiamate++] = new Chiamata(
                                        contattoChiamata.numDiTelefono,
                                        contattoChiamata.cognome,
                                        contattoChiamata.nome,
                                        chiamante.nome, // nome del chiamante
                                        chiamante.cognome // cognome del chiamante
                                );
                            } else {
                                System.out.println("Saldo insufficiente per effettuare la telefonata.");
                            }
                        } else {
                            System.out.println("Il contatto selezionato non è presente nella lista.");
                        }
                    } else {
                        System.out.println("Non ci sono contatti disponibili per la chiamata.");
                    }
                    break;


                case 7:
                    // Ricarica del saldo di un contatto
                    if (contacontatti > 0) {
                        System.out.println("Seleziona il contatto a cui vuoi fare la ricarica:");
                        Persona contattoRicarica = LeggiContatto(false, tastiera);
                        int posizioneContatto = posNumero(gestore, contattoRicarica, contacontatti);
                        while (posizioneContatto == -1) {
                            System.out.println("Contatto non valido. Reinserisci:");
                            contattoRicarica = LeggiContatto(false, tastiera);
                            posizioneContatto = posNumero(gestore, contattoRicarica, contacontatti);
                        }
                        System.out.println("Inserisci l'importo da ricaricare:");
                        double ricarica = Double.parseDouble(tastiera.nextLine());
                        while (ricarica <= 0) {
                            System.out.println("Importo non valido. Reinserisci:");
                            ricarica = Double.parseDouble(tastiera.nextLine());
                        }
                        double saldoAggiornato = Ricarica(gestore, posizioneContatto + 1, ricarica);
                        System.out.printf("Ricarica effettuata. Il saldo rimanente è: %.2f%n", saldoAggiornato);
                    } else {
                        System.out.println("Non ci sono contatti disponibili per la ricarica.");
                    }
                    break;
                case 8:
                    // Ordinamento dei contatti
                    if (contacontatti > 0) {
                        ordinaContatti(gestore, contacontatti);
                        System.out.println("Contatti ordinati con successo in base al nome e cognome.");
                        Visualizza(gestore, contacontatti, false);
                    } else {
                        System.out.println("Non ci sono contatti da ordinare.");
                    }
                    break;
                case 9:
                    // Gestione contatti nascosti
                    if (contacontatti > 0) {
                        System.out.println("Inserisci il contatto da gestire:");
                        Persona contattoGestione = LeggiContatto(false, tastiera);
                        int posizioneContatto = posNumero(gestore, contattoGestione, contacontatti);
                        if (posizioneContatto != -1) {
                            System.out.println("1 - Rendi contatto nascosto\n2 - Rendi contatto visibile");
                            int scelta = Integer.parseInt(tastiera.nextLine());
                            if (scelta == 1) {
                                gestore[posizioneContatto].nascosto = true;
                                System.out.println("Contatto reso nascosto.");
                            } else if (scelta == 2) {
                                gestore[posizioneContatto].nascosto = false;
                                System.out.println("Contatto reso visibile.");
                            } else {
                                System.out.println("Scelta non valida.");
                            }
                        } else {
                            System.out.println("Il contatto non è presente.");
                        }
                    } else {
                        System.out.println("Non ci sono contatti da gestire.");
                    }
                    break;
                case 10:
                    // Visualizzazione dell'elenco delle chiamate
                    System.out.println("Inserisci il tuo nome:");
                    String nomeChiamante = tastiera.nextLine();
                    System.out.println("Inserisci il tuo cognome:");
                    String cognomeChiamante = tastiera.nextLine();
                    System.out.println("Inserisci la password per visualizzare le chiamate effettuate:");
                    String passwordChiamante = tastiera.nextLine();
                    boolean passwordCorretta = passwordChiamante.equals(PASSWORD);


                    if (passwordCorretta) {
                        // Se la password è corretta, visualizza tutti i contatti chiamati
                        System.out.println("Elenco chiamate effettuate:");
                        for (int i = 0; i < numChiamate; i++) {
                            if (elencoChiamate[i] != null && elencoChiamate[i].getNomeChiamante().equals(nomeChiamante) && elencoChiamate[i].getCognomeChiamante().equals(cognomeChiamante)) {
                                System.out.println("Chiamato: " + elencoChiamate[i].getCognomeChiamato() + " " + elencoChiamate[i].getNomeChiamato());
                            }
                        }
                    } else {
                        // Se la password è errata, visualizza solo i contatti chiamati visibili
                        System.out.println("Password errata. Visualizzerai solo i contatti chiamati visibili:");
                        boolean visibili = false;
                        for (int i = 0; i < numChiamate; i++) {
                            if ((!gestore[i].nascosto) && (elencoChiamate[i] != null && elencoChiamate[i].getNomeChiamante().equals(nomeChiamante) && elencoChiamate[i].getCognomeChiamante().equals(cognomeChiamante))) {
                                System.out.println("Chiamato: " + elencoChiamate[i].getCognomeChiamato() + " " + elencoChiamate[i].getNomeChiamato());
                                visibili = true;
                            }
                        }
                        if (!visibili) {
                            System.out.println("Non ci sono chiamate effettuate da contatti visibili per questo utente.");
                        }
                        /*Chiamata chiamata = trovaChiamata(elencoChiamate, nomeChiamante, cognomeChiamante, numChiamate);
                        if (chiamata != null) {
                            System.out.println("Chiamato: " + chiamata.getCognomeChiamato() + " " + chiamata.getNomeChiamato());
                        }*/


                    }
                    break;
                case 11:
                    // Uscita dal programma
                    uscita = true;
                    break;
            }
        } while (!uscita);
        System.out.println("Fine programma");
    }


    // Metodo per la lettura di un nuovo contatto
    // Modifica apportata rispetto alla verifica metto il saldo iniziale sempre a 10 privato e l'utente lo aumenta tramite la ricarica successivamente
    private static Persona LeggiContatto(boolean telSi, Scanner tastiera) {
        Persona contatto = new Persona();
        System.out.println("Inserisci il nome");
        contatto.nome = tastiera.nextLine();
        System.out.println("Inserisci il cognome");
        contatto.cognome = tastiera.nextLine();
        if (telSi) {
            System.out.println("Inserisci il numero di telefono");
            contatto.numDiTelefono = tastiera.nextLine();
            String[] opzioni = {"Tipo Abbonamento", "1 - Cellulare", "2 - Abitazione", "3 - Azienda"};
            int scelta;
            do {
                scelta = menu(opzioni, tastiera);
                switch (scelta) {
                    case 1:
                        contatto.tipo = Tipologia.cellulare;
                        break;
                    case 2:
                        contatto.tipo = Tipologia.abitazione;
                        break;
                    case 3:
                        contatto.tipo = Tipologia.azienda;
                        break;
                }
            } while (scelta < 1 || scelta > 3);
        }
        return contatto;
    }
    // Metodo per la visualizzazione dei contatti
    public static void Visualizza(Persona[] gestore, int contacontatti, boolean mostraNascosti) {
        for (int i = 0; i < contacontatti; i++) {
            if (!gestore[i].nascosto || mostraNascosti) {
                System.out.println(gestore[i].anagrafica());
            }
        }
    }
    // Metodo per verificare se un contatto è già presente nella lista
    public static boolean Presente(Persona[] gestore, Persona NuovoContatto, int contacontatti) {
        for (int i = 0; i < contacontatti; i++) {
            if (gestore[i].nome.equals(NuovoContatto.nome) && gestore[i].cognome.equals(NuovoContatto.cognome)) {
                return true;
            }
        }
        return false;
    }
    // Metodo per trovare la posizione di un contatto nella lista
    public static int posNumero(Persona[] gestore, Persona NuovoContatto, int contacontatti) {
        for (int i = 0; i < contacontatti; i++) {
            if (gestore[i].nome.equals(NuovoContatto.nome) && gestore[i].cognome.equals(NuovoContatto.cognome)) {
                return i;
            }
        }
        return -1;
    }
    // Metodo per cancellare un contatto dalla lista
    public static int cancellaContatto(Persona[] gestore, int posizione, int contacontatti) {
        if (posizione != gestore.length) {
            for (int i = posizione; i < contacontatti - 1; i++) {
                gestore[i] = gestore[i + 1];
            }
        }
        return --contacontatti;
    }
    // Metodo per effettuare una chiamata
    private static double effettuaChiamata(Persona[] gestore, int posizione, double costoChiamata) {
        if (gestore[posizione].saldo >= costoChiamata) {
            gestore[posizione].saldo -= costoChiamata;
            return gestore[posizione].saldo;
        }
        return -1;
    }


    // Metodo per ricaricare il saldo di un contatto
    public static double Ricarica(Persona[] gestore, int scelta, double ricarica) {
        return gestore[scelta - 1].saldo += ricarica;
    }
    // Metodo per ordinare i contatti in base al nome e cognome
    public static void ordinaContatti(Persona[] gestore, int contacontatti) {
        for (int i = 0; i < contacontatti - 1; i++) {
            for (int j = 0; j < contacontatti - i - 1; j++) {
                if ((gestore[j].nome + gestore[j].cognome).compareToIgnoreCase(gestore[j + 1].nome + gestore[j + 1].cognome) > 0) {
                    Persona temp = gestore[j];
                    gestore[j] = gestore[j + 1];
                    gestore[j + 1] = temp;
                }
            }
        }
    }
    // Metodo per gestire la visualizzazione dei contatti nascosti
    // Questa parte è stata modificata rispetto alla verifica rendedomi conto che era giusto il metodo di pensiero usato ma il funzionamento era da rivedere
    // Quindi ho modificato quella parte confrontando la password nel main
    // Qui al posto di ritornare true o false ritorna l'oggetto della chiamata visualizzandolo se non risulterà nullo nel main col case 10
    private static Chiamata trovaChiamata(Chiamata[] elencoChiamate, String nomeChiamante, String cognomeChiamante, int numChiamate) {
        for (int i = 0; i < numChiamate; i++) {
            if (elencoChiamate[i] != null && elencoChiamate[i].getNomeChiamante().equals(nomeChiamante) && elencoChiamate[i].getCognomeChiamante().equals(cognomeChiamante)) {
                return elencoChiamate[i]; // Restituisci l'oggetto Chiamata trovato
            }
        }
        return null; // Se la chiamata non viene trovata, restituisci null
    }

}


