package com.kodcu;

import org.owasp.validator.html.*;

import java.io.File;
import java.util.List;

/**
 * Created by usta on 12.07.2014.
 */
public class App {

    public static void main(String[] args) throws PolicyException, ScanException {

        String input = "<script> alert('Merhaba Dünya..'); </script>"+
                "<b>Merhaba Uranüs</b>"+
                "<div onload=\"alert('Merhaba Neptün..')\">Merhaba Platon</div>";

        Policy policy = Policy // Sanitize kuralı
                .getInstance(App.class.getResourceAsStream("/antisamy-slashdot-1.4.4.xml"));

        AntiSamy sanitizer = new AntiSamy(policy);

        CleanResults scanned = sanitizer.scan(input);

        int errors = scanned.getNumberOfErrors(); // Kural ihlali sayısı
        List<String> errorMsg = scanned.getErrorMessages(); // İhlal nedenleri

        String sanitized = scanned.getCleanHTML(); // Temizlenmiş çıktı

        System.out.println("Temiz çıktı: "+sanitized);
        System.out.println("İhlal sayısı: "+errors);
        System.out.println("İhlal nedenleri: "+errorMsg);

    }
}
