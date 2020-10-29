package com.kea;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // Opretter ArrayList til at indeholde listen med rates i
        ArrayList<Rateinfo> rateinfos = new ArrayList<Rateinfo>();

        //Instantiating the URL class
        URL url = new URL("https://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=da");
        //Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
        //StringBuffer sb = new StringBuffer();
        // Tælle variabel i til at tælle med
        int i = 0;
        //  Anvendes til at holde code
        String Code = "";
        // Bruges til at indeholde desc
        String Desc = "";
        // Indeholder rate
        String Rate = "";

        while (sc.hasNext()) {
            String t = sc.next();
            //sb.append(t);
            i++;

            if (i > 12 && i < 181) {
                if (t.contains("code")) {
                    t = t.replaceAll("code=", "").replaceAll("\"", "");
                    Code = t;
                    // System.out.print(t);
                } else if (t.contains("desc")) {
                    t = t.replaceAll("desc=", "").replaceAll("\"", "").replaceAll("�", "æ");
                    if (t.contains("Euro")) {
                        //     System.out.print(" " + t);
                    } else {
                        t = t + " " + sc.next();
                        t = t.replaceAll("\"", "");
                        //     System.out.print(" " + t);
                    }
                    Desc = t;
                } else if (t.contains("rate")) {
                    t = t.replaceAll("rate=", "").replaceAll("\"", "").replaceAll(",", ".");
                    // System.out.print(" "+t);
                    Rate = t;
                    // Tilføjer den fundne data til listen.
                    rateinfos.add(new Rateinfo(Code, Desc, Double.parseDouble(Rate)));
                }
                if (t.contains("/>")) {
                    System.out.println();
                }
            }
        }
        for (int j = 0; j < rateinfos.size(); j++) {
            System.out.println("" + rateinfos.get(j));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input code name: ");

        String valutaType = scanner.next();

        for (int j = 0; j < rateinfos.size(); j++) {
            if (rateinfos.get(j).getCode().contains(valutaType)) {
                System.out.println("" + rateinfos.get(j));
                System.out.println();
            }
        }
    }
}