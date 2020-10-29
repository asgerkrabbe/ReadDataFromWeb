package com.kea;

public class Rateinfo {
    private String code;
    private String desc;
    private double rate;

    public String getCode() {
        return code;
    }

    /**
     * @param Code Kode der anvendes for denne valuta
     * @param Desc Beskrivelse til valuta
     * @param Rate Antal danske kr for 100 af den valuta vi omregner til
     */
    public Rateinfo(String Code, String Desc, double Rate) {
        code = Code;
        desc = Desc;
        rate = Rate;
    }

    @Override
    public String toString() {
        return "Rateinfo: " +
                "code= " + code +
                ", desc= " + desc +
                ", rate= " + rate;
    }
}
