package timovy_projekt_01;

/**
 * Trieda Zakaznik predstavuje zákazníka s atributmi, ktoré popisuju jeho vlastnosti a služby
 */
public class Zakaznik
{
    // Inštančné premenné
    private final String meno;
    private final String telefonnyKontakt;
    private final boolean aktivnaSluzbaHlas;
    private final boolean aktivnaSluzbaInternet;
    private final Platba platba;
    private final double stavUctu;
    private Operator operator;

    /**
     * Konštruktor triedy Zakaznik
     * Inicializuje všetky atributy zákazníka na základe poskytnutých vstupov.
     *
     * @param meno                Krstné meno a priezvisko zákazníka
     * @param telefonnyKontakt    Telefónne číslo zákazníka
     * @param aktivnaSluzbaHlas   Informacia, či má zákazník aktívnu službu hlas
     * @param aktivnaSluzbaInternet Informacia, či má zákazník aktívnu službu internet
     * @param platba              Typ platby zákazníka
     * @param stavUctu            Stav účtu zákazníka
     */
    public Zakaznik(String meno, String telefonnyKontakt, boolean aktivnaSluzbaHlas, boolean aktivnaSluzbaInternet, Platba platba, double stavUctu)
    {
        this.meno = meno;
        this.telefonnyKontakt = telefonnyKontakt;
        this.aktivnaSluzbaHlas = aktivnaSluzbaHlas;
        this.aktivnaSluzbaInternet = aktivnaSluzbaInternet;
        this.platba = platba;
        this.stavUctu = stavUctu;
        this.operator = null;
    }

    /**
     * Metóda, ktorá vypíše údaje každého zákazníka v poradí na konkrétnom csv súbore
     */
    public void vypis()
    {
        // Výpis údajov o zákazníkovi na konzolu
        System.out.println("Meno zákazníka: " + this.meno);
        System.out.println("Telefónny kontakt: " + this.telefonnyKontakt);
        System.out.println("Má aktivovanú službu hlas: " + this.aktivnaSluzbaHlas);
        System.out.println("Má aktivovanú službu internet: " + this.aktivnaSluzbaInternet);
        System.out.println("Typ platby: " + this.platba);
        System.out.println("Stav účtu: " + this.stavUctu);
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Getter na získanie mena zákazníka
     * @return Meno zákazníka
     */
    public String getMeno()
    {
        return meno;
    }

    /**
     * Getter na získanie telefónneho kontaktu zákazníka
     * @return Telefónny kontakt zákazníka
     */
    public String getTelefonnyKontakt()
    {
        return telefonnyKontakt;
    }

    /**
     * Getter na zistenie, či má zákazník aktívnu službu hlas
     * @return True, ak je služba hlas aktívna, inak false
     */
    public boolean jeAktivnaSluzbaHlas()
    {
        return aktivnaSluzbaHlas;
    }
    /**
     * Getter na zistenie, či má zákazník aktívnu službu intenet
     * @return True, ak je služba internet aktívna, inak false
     */
    public boolean jeAktivnaSluzbaInternet()
    {
        return aktivnaSluzbaInternet;
    }

    /**
     * Getter na získanie stavu účtu zákazníka
     * @return Stav účtu zákazníka
     */
    public double getStavUctu()
    {
        return stavUctu;
    }

    /**
     * Metóda, ktorá vracia hodnotu true,
     * ak je zákazníkovi pridelený operátor, v opačnom prípade vracia false
     * @return True v prípade prideleného operátora, false ak nie je priradený
     */
    public boolean jeNastavenyOperator()
    {
        if (this.operator == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Metóda, ktorá nastavuje zákazníkovi konkrétneho operátora
     * @param operator Operátor priradzovaný zákazníkovi
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * Override metódy, ktorá vypisuje údaje o zákazníkovi v reťazcovom formáte
     * @return Údaje zákazníka v reťazcovej podobe
     */
    @Override
    public String toString() {
        return String.format("%-30s%-15s%10.2f", meno, telefonnyKontakt, stavUctu);
    }
}
