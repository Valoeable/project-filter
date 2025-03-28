package timovy_projekt_01;

public class Tester
{
    /**
     * Metóda, ktorá slúži na načítanie zákazníkov a operátorov z príslušných csv súborov
     * @param nazovSuboruZakaznikov Názov csv súboru v ktorých sa nachádza zoznam zákazníkov
     * @param nazovSuboruOperatorov Názov csv súboru v ktorých sa nachádza zoznam operátorov
     * @return Databáza načítaných zákazníkov a operátorov
     */
    private static Databaza nacitajVstup(String nazovSuboruZakaznikov, String nazovSuboruOperatorov)
    {
        Databaza databaza = new Databaza();
        databaza.nacitajZakaznikov(nazovSuboruZakaznikov);
        databaza.nacitajOperatorov(nazovSuboruOperatorov);
        return databaza;
      }

    public static void main(String[] args)
    {
        // Podmienka, ktorá ukončí program, ak bude zadaných viac alebo menej
        // argumentov a konfigurácii testera
        if (args.length != 3)
        {
            System.out.println("Zadal si zlý argument.");
            System.out.println("Správny argument je názov súboru a typ filtra. Príklad: zakaznici.csv operatori.csv f" );
            System.exit(1);
        }
        String zakazniciSubor = args[0];
        String operatoriSubor = args[1];
        String filter = args[2];

        // Načítanie zákazníkov a operátorov z csv súboru a kontrola "prázdnosti" súborov
        System.out.println("---------------------------------------------------------");
        Databaza databaza = nacitajVstup(zakazniciSubor, operatoriSubor);
        databaza.spocitajZakaznikov();
        databaza.spocitajOperatorov();
        databaza.priradOperatorovZakaznikom();
        if (databaza.getPocitadloZakaznikov() == 0)
        {
            System.out.println("Súbor " + zakazniciSubor + " je prázdny.");
            System.out.println("---------------------------------------------------------");
        } else if (databaza.getPocitadloOperatorov() == 0)
        {
            System.out.println("Súbor " + operatoriSubor + " je prázdny.");
            System.out.println("---------------------------------------------------------");
        } else
        {
            System.out.println("                 ______ _ _ _            ");
            System.out.println("                |  ____(_) | |           ");
            System.out.println("                | |__   _| | |_ ___ _ __ ");
            System.out.println("                |  __| | | | __/ _ \\ '__|");
            System.out.println("                | |    | | | ||  __/ |   ");
            System.out.println("                |_|    |_|_|\\__\\___|_|   ");

            // Použitie príkazového riadka na výber konkrétneho filtra
            switch (filter) // args[2] obsahuje tretí argument z príkazoveho riadka
            {
                case "f" ->
                    // Výpís zákazníkov so záporným stavom účtu
                        databaza.vypisZakaznikovPreFakturaciu();
                case "m" ->
                    // Výpís zákazníkov s mobilným kontaktom
                        databaza.vypisZakaznikovSMobilnymKontaktom();
                case "h" ->
                    // Výpís zákazníkov so službou hlas
                        databaza.vypisZakaznikovSoSluzbouHlas();
                case "i" ->
                    // Výpís zákazníkov so službou internet
                        databaza.vypisZakaznikovSoSluzbouInternet();
                case "o" ->
                    // Výpís VIP operátorov
                        databaza.vypisVipZakaznikov();
                default ->
                {
                    System.out.println("Neznámy typ filtra");
                    System.out.println("Správne typy filtra sú: f, m, h, i, o.");
                }
            }
        }
    }
}
