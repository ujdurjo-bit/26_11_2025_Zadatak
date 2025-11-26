/*Dinko Šuliček: Napiši Java program koji omogućuje kopiranje izvorne binarne datoteke.

Tražite od korisnika putanju do originalne datoteke (npr. C:/temp/original.pdf)
Tražite od korisnika naziv destinacijske datoteke (npr. C:/temp/ kopija.pdf)
Koristeći FileInputStream i FileOutputStream, kopirajte byte po byte iz izvorne datoteke u destinacijsku
Obradite sve moguće potencijalne iznimke (putanja ne postoji, nemate prava za pisanje ili čitanje, ...)
        Po završetku, provjerite je li kopija uspješno napravljena, te ju pokušajte otvoriti kroz File Explorer.
Prouči klasu java.io.File. Pronađi metode exists() i delete(). Provjerite programski je datoteka prethodno uspješno kopirana (metoda exists) I ako je, pobrisite ju (metoda delete)*/

import java.io.*;

public class Zadatak1 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        try {
            System.out.println("Molimo unesite izvornu putanju datoteke: \n(npr. C:/temp/original.pdf)");
            String originalDest = br.readLine();

            System.out.println("Molimo unesite naziv destinacijske datoteke: \n(npr. C:/temp/kopija.pdf)");
            String kopija = br.readLine();

            br.close();

            File izvorniFile = new File(originalDest);
           /* if (!izvorniFile.exists()) {
                System.err.println("Greška: Izvorna datoteka ne postoji: " + originalDest);
                return;

            }

            if (!izvorniFile.canRead()) {
                System.err.println("Greška: Nemate dozvolu za čitanje datoteke: " + originalDest);
                return;

            }*/

            File destinacijskaDAT = new File(kopija);
            if (destinacijskaDAT.exists()) {
                System.err.println("Greška: Destinacijska datoteka već postoji: " + kopija);
                if (destinacijskaDAT.delete()) {
                    System.out.println("Stara datotetka obrisana!");
                } else {
                    System.err.println("Dogodila se greška pri brisanju!");

                }
            }

            try (FileInputStream fis = new FileInputStream(izvorniFile);
                 FileOutputStream fos = new FileOutputStream(destinacijskaDAT)) {
                int podatak;
                while ((podatak = fis.read()) != -1) {
                    fos.write(podatak);
                }

                if (destinacijskaDAT.exists()) {
                    System.out.println("Kopija uspješno napravljena: " + kopija);
                }

            } catch (FileNotFoundException e) {
                System.err.println("Greška: Datoteka nije pronađena: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.err.println("Greška: Nedozvoljeni znakovi ili prazan unos: " + e.getMessage());

            } catch (IOException e) {
                System.err.println("Greška pri kopiranju: " + e.getMessage());

            }

        } catch (IOException e) {
            System.err.println("Greška pri unosu: " + e.getMessage());
        }


    }
}

