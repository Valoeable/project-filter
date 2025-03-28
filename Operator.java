package timovy_projekt_01;

import java.util.ArrayList;

/**
 * Trieda Operator predstavuje operátora, ktorý sa náhodne priradí zákazníkovi zo zoznamu v csv súbore
 */
public class Operator
{
    private final String meno;
    private final String telefonnyKontakt;
    private final boolean vip;
    private ArrayList<Zakaznik> zakaznici;

    /**
     * Konštruktor, ktorý vytvorí objekt s preddefinovaným menom, telefónnym kontaktom a priradením,
     * akých zákazníkov majú na starosti
     * @param meno Meno operátora
     * @param telefonnyKontakt Telefónny kontakt na daného operátora
     * @param spravujeVIP Rozhoduje o zodpovednosti za daný druh zákazníka
     */
    public Operator(String meno, String telefonnyKontakt, boolean spravujeVIP)
    {
        this.meno = meno;
        this.telefonnyKontakt = telefonnyKontakt;
        this.vip = spravujeVIP;
        this.zakaznici = new ArrayList<>();
    }

    /**
     * Metóda , ktorá vracia true, ak má daný operátor priradených VIP zákazníkov,
     * ak nie, vracia hodnotu false
     * @return True alebo false podľa zodpovednosti operátora
     */
    public boolean jeVip()
    {
        return vip;
    }

    /**
     * Metóda, ktorá pridáva operátorovi daného zákazníka
     * @param zakaznik Zákazník, ktorý bdue priradený konkrétnemu operátorovi
     */
    public void pridajZakaznika(Zakaznik zakaznik)
    {
        this.zakaznici.add(zakaznik);
    }

    /**
     * Metóda, ktorá vypíše 1 zákazníka
     */
    public void vypisZakaznikov()
    {
        for (Zakaznik zakaznik : zakaznici)
        {
            System.out.println(zakaznik);
        }
    }

    /**
     * Override metódy, ktorá vypisuje údaje o operátorovi v reťazcovej podobe
     * @return Údaje operátora v reťazcovej podobe
     */
    @Override
    public String toString()
    {
        String zodpovednost = jeVip() ? "VIP Operátor" : "Basic Operátor";
        return String.format("%-30s%-17s%s", meno, telefonnyKontakt, zodpovednost);
    }
}
