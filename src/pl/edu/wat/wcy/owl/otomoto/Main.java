package pl.edu.wat.wcy.owl.otomoto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello OWL World");
        try {
            Document doc = Jsoup.connect("http://otomoto.pl/oferta/bmw-seria-3-328i-m-performance-fv-nivette-ID4oBKOb.html#5c1b2e7f18").get();
            System.out.println("Title of website: "+doc.title());

            Elements elems = doc.getElementsByClass("params-list");
            Map<String,String> paramMap= new HashMap<>();
            Element e = elems.first();
            for (Element ee : e.children()) {
                paramMap.put(ee.child(0).text(),ee.child(1).text());
            }

            for (String key : paramMap.keySet()){
                System.out.println(key+": "+paramMap.get(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
