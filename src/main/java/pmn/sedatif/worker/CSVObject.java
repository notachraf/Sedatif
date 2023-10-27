package pmn.sedatif.worker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVObject {

    private String nom;
    private String adresse;
    private String dateFinTravaux;

    public CSVObject(String name, String adresse, String df) {
        this.nom = name;
        this.adresse = adresse;
        this.dateFinTravaux = df;
    }

    public CSVObject(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public static String convertToCsvString(List<CSVObject> data, boolean df) throws IOException {
        StringWriter writer = new StringWriter();
        CsvBeanWriter csvBeanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

        // Write the header
        String[] header;

        if(df) {
            header = new String[]{"Nom", "Adresse", "DateFinTravaux"}; // csv file fields
        } else {
            header = new String[]{"Nom", "Adresse"}; // csv file fields
        }

        csvBeanWriter.writeHeader(header);

        // Write the data
        for (CSVObject item : data) {
            csvBeanWriter.write(item, header);
        }

        csvBeanWriter.close();
        return writer.toString();
    }

    public CSVObject() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateFinTravaux() {
        return dateFinTravaux;
    }

    public void setDateFinTravaux(String dateFinTravaux) {
        this.dateFinTravaux = dateFinTravaux;
    }

    @Override
    public String toString() {
        return "CSVObject{" +
                "name='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", df='" + dateFinTravaux + '\'' +
                '}';
    }
}
