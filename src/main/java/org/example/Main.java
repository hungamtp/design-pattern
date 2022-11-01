package org.example;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String réult = "Đi đĩ đì đi đâu con lâu mơ nói";
        String keyword = "di";
        System.out.println(StringUtils.stripAccents("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ"));
        System.out.println(Normalizer.isNormalized("āăąēîïĩíĝġńñšŝśûůŷ", Normalizer.Form.NFKD));
    }
}