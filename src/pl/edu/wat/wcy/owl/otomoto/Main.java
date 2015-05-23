package pl.edu.wat.wcy.owl.otomoto;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello OWL World");
        try {
            Document doc = Jsoup.connect("http://otomoto.pl/oferta/peugeot-308-okazja-warto-kupic-zapraszamy-ID4paWUL.html").get();
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

            String uri = "file:/"+System.getProperty("user.dir")+"protege/"+"otomoto.owl";
            uri = "file:/C:/workspace/mgr/owlotomoto/protege/otomoto.owl";
            String fileUri = "C:/workspace/mgr/owlotomoto/protege/otomoto.owl";
            System.out.println(uri);
            JenaOWLModel owlModel = ProtegeOWL.createJenaOWLModelFromURI(uri);

            for (Object obj : owlModel.getOWLIndividuals()){
                OWLIndividual individual = (OWLIndividual) obj;
                System.out.println(individual.getName());
            }

            File f = new File(fileUri);
            OWLNamedClass car = owlModel.getOWLNamedClass("Osobowy");
            System.out.println(car.getNamespace()+" *_* "+car.getName()+" *_* "+car.getLocalName());
            OWLIndividual thisCar = car.createOWLIndividual("Testowy pojazd");
            thisCar.setComment("Testowy test");
            owlModel.save(f.toURI());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (OntologyLoadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
