package timovy_projekt_01;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * Trieda Databaza spravuje zoznam zákazníkov a operátorov
 * ,umožňuje pridávať zákazníkov a filtrovať ich podľa rôznych kritérií.
 */
public class Databaza
{
    // Zoznam zákazníkov a operátorov uložených v dynamickej kolekcii a počítadlá zákazníkov a operátorov,
    // ktoré slúžia na kontrolu csv súboru(či je daný csv súbor prázdny)
    private ArrayList<Zakaznik> zakaznici;
    private ArrayList<Operator> operatori;
    private int pocitadloZakaznikov;
    private int pocitadloOperatorov;

    /**
     * Konštruktor triedy DatabazaZakaznikov.
     * Inicializuje prázdnu databázu zákazníkov.
     * Inicializuje prázdnu databázu operátorov.
     */
    public Databaza()
    {
        zakaznici = new ArrayList<>();
        operatori = new ArrayList<>();
    }

    /**
     * Metóda, ktorá načíta zákazníkov zo súboru `zakaznici.csv.` a pridá ich do zoznamu.
     * @param nazovSuboru Súbor, ktorý načítava databázu zákazníkov
     */

    public void nacitajZakaznikov(String nazovSuboru)
    {
        try (FileReader citac = new FileReader(nazovSuboru);
             Scanner scanner = new Scanner(citac))
        {
            while (scanner.hasNextLine())
            {
                // Načítanie riadku zo subóru
                String riadokZoSuboru = scanner.nextLine();
                // Rozdelenie riadku na jednotlivé údaje oddelené čiarkou
                String[] dataOZakaznikovi = riadokZoSuboru.split(",");
                // Vytvorenie objektu Zakaznik z údajov načítaných zo súboru

                System.out.println(dataOZakaznikovi.length);

                // Podmienka, ktorá umožní aby filter zbehol úspešne aj napriek chybne zadaným
                // alebo prázdnym dátam v csv súbore
                if (dataOZakaznikovi.length < 6)
                    continue;

                else {
                    Zakaznik zakaznik = new Zakaznik
                            (dataOZakaznikovi[0], // meno
                                    dataOZakaznikovi[1], // telefónny kontakt
                                    dataOZakaznikovi[2].equals("hlas"), // aktívna služba hlas
                                    dataOZakaznikovi[3].equals("internet"), // aktívna služba internet
                                    dataOZakaznikovi[4].equals("pausal") ? Platba.PAUSAL : Platba.KREDIT, // typ platby
                                    Double.parseDouble(dataOZakaznikovi[5]) // stav účtu
                            );
                    zakaznik.vypis();
                    this.zakaznici.add(zakaznik);
                }
            }
            // Zatvorenie Scanneru
            scanner.close();
            try
            {
                // Zatvorenie FileReader
                citac.close();
            } catch (IOException e)
            {
                // Ak nastane chyba pri zatváraní súboru, vypíše sa chybová hláška
                System.out.println("Súbor " + nazovSuboru + " sa nepodarilo úspešne zatvoriť!");
            }
        } catch (IOException e)
        {
            System.out.println("Chyba pri čítaní suború " + nazovSuboru);
            e.printStackTrace();
        }
    }

    /**
     * Metóda, ktorá načíta operátorov zo súboru `operatori.csv.` a pridá ich do zoznamu.
     * @param nazovSuboru Názov CSV súboru s operátormi.
     */
    public void nacitajOperatorov(String nazovSuboru)
    {
        try (FileReader citac = new FileReader(nazovSuboru);
             Scanner scanner = new Scanner(citac))
        {
            while (scanner.hasNextLine())
            {
                // Načítanie riadku zo súboru
                String riadok = scanner.nextLine();

                String[] dataOperatora = riadok.split(",");

                // Podmienka, ktorá umožní aby filter zbehol úspešne aj napriek chybne zadaným
                // alebo prázdnym dátam v csv súbore
                if (dataOperatora.length < 3)
                    continue;
                else {
                    // CSV msa meno, kontakt, VIP (true/false)
                    String meno = dataOperatora[0];
                    String kontakt = dataOperatora[1];
                    boolean vip = dataOperatora[2].equals("VIP");
                    // Vytvorenie operátora a pridanie do zoznamu
                    Operator operator = new Operator(meno, kontakt, vip);
                    this.operatori.add(operator);
                }

            }
            // Zatvorenie Scanneru
            scanner.close();
            try
            {
                // Zatvorenie FileReader
                citac.close();
            } catch (IOException e)
            {
                // Ak nastane chyba pri zatváraní súboru, vypíše sa chybová hláška
                System.out.println("Súbor " + nazovSuboru + " sa nepodarilo úspešne zatvoriť!");
            }
        } catch (IOException e)
        {
            System.out.println("Chyba pri čítaní suború " + nazovSuboru);
            e.printStackTrace();
        }

    }

    /**
     * Metóda, ktorá náhodne pridá operátora zákazníkovi z csv zoznamu
     */
    public void priradOperatorovZakaznikom()
    {
        for (Zakaznik zakaznik : this.zakaznici)
        {
            if (!zakaznik.jeNastavenyOperator() && pocitadloOperatorov > 0)
            {
                Random random = new Random();
                // Náhodne vyber operátora zo zoznamu
                Operator nahodnyOperator = operatori.get(random.nextInt(operatori.size()));
                zakaznik.setOperator(nahodnyOperator);
                nahodnyOperator.pridajZakaznika(zakaznik);
            }
        }
    }

    /**
     * Metóda na vypísanie zákazníkov, ktorých telefónny kontakt začína číslami "09"
     */
    public void vypisZakaznikovSMobilnymKontaktom()
    {
        System.out.println("                 __  __       _     _ _ ");
        System.out.println("                |  \\/  |     | |   (_) |");
        System.out.println("                | \\  / | ___ | |__  _| |");
        System.out.println("                | |\\/| |/ _ \\| '_ \\| | |");
        System.out.println("                | |  | | (_) | |_) | | |");
        System.out.println("                |_|  |_|\\___/|_.__/|_|_|");
        System.out.println("---------------------------------------------------------");

        ArrayList<Zakaznik> zotriedenieZakaznikov = new ArrayList<>();
        for (Zakaznik zakaznik : zakaznici) // Prejde všetkých zákazníkov
        {
            //podmienka: Telefónny kontakt začína s predvoľbou '09'
            String telfonnyKontakt = zakaznik.getTelefonnyKontakt(); // Získa telefónny kontakt

            if (telfonnyKontakt.startsWith("09")) // Ak kontak začína "09"
            {
                zotriedenieZakaznikov.add(zakaznik);
            }
        }
        ArrayList<Zakaznik> zotriedene = zotriedZakaznikovAbecedne(zotriedenieZakaznikov);
        for (Zakaznik zakaznik : zotriedene)
        {
            System.out.println(zakaznik);
        }
    }

    /**
     * Metóda na vypísanie zákazníkov so záporným stavom účtu
     */
    public void vypisZakaznikovPreFakturaciu()
    {
        System.out.println("     ______    _    _                        _       ");
        System.out.println("    |  ____|  | |  | |                      (_)      ");
        System.out.println("    | |__ __ _| | _| |_ _   _ _ __ __ _  ___ _  __ _ ");
        System.out.println("    |  __/ _` | |/ / __| | | | '__/ _` |/ __| |/ _` |");
        System.out.println("    | | | (_| |   <| |_| |_| | | | (_| | (__| | (_| |");
        System.out.println("    |_|  \\__,_|_|\\_\\\\__|\\__,_|_|  \\__,_|\\___|_|\\__,_|");
        System.out.println("---------------------------------------------------------");

        ArrayList<Zakaznik> zotriedenieZakaznikov = new ArrayList<>();
        for (Zakaznik zakaznik : zakaznici) // Prejde všetkých zákazníkov
        {
            if (zakaznik.getStavUctu() < 0) // Ak je stav účtu záporný
            {
                zotriedenieZakaznikov.add(zakaznik);
            }
        }
        ArrayList<Zakaznik> zotriedene = zotriedZakaznikovAbecedne(zotriedenieZakaznikov);
        for (Zakaznik zakaznik : zotriedene)
        {
            System.out.println(zakaznik);
        }

    }

    /**
     * Metóda na vypísanie zákazníkov s aktívnou službou hlas
     */
    public void vypisZakaznikovSoSluzbouHlas()
    {
        System.out.println("                  _    _ _           ");
        System.out.println("                 | |  | | |          ");
        System.out.println("                 | |__| | | __ _ ___ ");
        System.out.println("                 |  __  | |/ _` / __|");
        System.out.println("                 | |  | | | (_| \\__ \\");
        System.out.println("                 |_|  |_|_|\\__,_|___/");
        System.out.println("---------------------------------------------------------");

        ArrayList<Zakaznik> zotriedenieZakaznikov = new ArrayList<>();
        for (Zakaznik zakaznik : zakaznici) // Prejde všetkych zákazníkov
        {
            if (zakaznik.jeAktivnaSluzbaHlas()) // Ak má zákazník aktívnu službu internet
            {
                zotriedenieZakaznikov.add(zakaznik);
            }
        }
        ArrayList<Zakaznik> zotriedene = zotriedZakaznikovAbecedne(zotriedenieZakaznikov);
        for (Zakaznik zakaznik : zotriedene)
        {
            System.out.println(zakaznik);
        }
    }

    /**
     * Metóda na vypísanie zákazníkov s aktívnou službou internet
     */
    public void vypisZakaznikovSoSluzbouInternet()
    {
        System.out.println("         _____       _                       _   ");
        System.out.println("        |_   _|     | |                     | |  ");
        System.out.println("          | |  _ __ | |_ ___ _ __ _ __   ___| |_ ");
        System.out.println("          | | | '_ \\| __/ _ \\ '__| '_ \\ / _ \\ __|");
        System.out.println("         _| |_| | | | ||  __/ |  | | | |  __/ |_ ");
        System.out.println("        |_____|_| |_|\\__\\___|_|  |_| |_|\\___|\\__|");
        System.out.println("---------------------------------------------------------");

        ArrayList<Zakaznik> zotriedenieZakaznikov = new ArrayList<>();
        for (Zakaznik zakaznik : zakaznici) // Prejde všetkých zákazníkov
        {
            if (zakaznik.jeAktivnaSluzbaInternet()) // Ak má zákazník aktívnu službu internet
            {
                zotriedenieZakaznikov.add(zakaznik);
            }
        }
        ArrayList<Zakaznik> zotriedene = zotriedZakaznikovAbecedne(zotriedenieZakaznikov);
        for (Zakaznik zakaznik : zotriedene)
        {
            System.out.println(zakaznik);
        }

    }

    /**
     * Metóda, ktorá vypíše zoznam všetkých VIP operátorov a za nimi zoznam všetkých zákazníkov,
     * ktorí sú priradený danému VIP operátorovi
     */
    public void vypisVipZakaznikov()
    {
        System.out.println("                 __      _______ _____  ");
        System.out.println("                 \\ \\    / /_   _|  __ \\ ");
        System.out.println("                  \\ \\  / /  | | | |__) |");
        System.out.println("                   \\ \\/ /   | | |  ___/ ");
        System.out.println("                    \\   /   _| |_| |     ");
        System.out.println("                     \\ /   |_____|_|     ");
        System.out.println("---------------------------------------------------------");
        for (Operator operator : operatori) // Prejde všetkých zákazníkov
        {
            if (operator.jeVip()) // Ak má operátor na starosti VIP zákazníkov
            {
                System.out.println(operator);
                System.out.println("------------------------------------------------------------");
                operator.vypisZakaznikov();
                System.out.println("------------------------------------------------------------");
            }
        }
    }

    /**
     * Metóda, ktorá zoberie zoznam zákazníkov a zoradí ich abecedne podľa ich krstného mena
     * @param zakazniciNaZotriedenie Zoznam zákazníkov, ktorý je potrebné abecedne zoradiť
     * @return Abecedne zotriedený zoznam zákazníkov
     */
    public static ArrayList<Zakaznik> zotriedZakaznikovAbecedne(ArrayList<Zakaznik> zakazniciNaZotriedenie)
    {
        zakazniciNaZotriedenie.sort(Comparator.comparing(Zakaznik::getMeno));
        return zakazniciNaZotriedenie;
    }

    /**
     * Metóda, ktorá spočíta počet zákazníkov v csv súbore
     */
    public void spocitajZakaznikov()
    {
        for (Zakaznik zakaznik : zakaznici)
        {
            pocitadloZakaznikov++;
        }
    }

    /**
     * Metóda, ktorá spočíta počet operátorov v csv súbore
     */
    public void spocitajOperatorov()
    {
        for (Operator operator : operatori)
        {
            pocitadloOperatorov++;
        }
    }

    /**
     * Vracia počet zákazníkov v csv súbore
     * @return Počet zákazníkov
     */
    public int getPocitadloZakaznikov()
    {
        return pocitadloZakaznikov;
    }

    /**
     * Vracia počet operátorov v csv súbore
     * @return Počet operátorov
     */
    public int getPocitadloOperatorov()
    {
        return pocitadloOperatorov;
    }
}
