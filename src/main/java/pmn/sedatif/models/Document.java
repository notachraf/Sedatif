package pmn.sedatif.models;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Document")
public class Document {

    private List<Chantier> chantiers;

    public Document() {
    }

    public Document(List<Chantier> chantiers) {
        this.chantiers = chantiers;
    }

    @XmlElement(name = "Chantier")
    public List<Chantier> getChantiers() {
        return chantiers;
    }

    public void setChantiers(List<Chantier> chantiers) {
        this.chantiers = chantiers;
    }

    @Override
    public String toString() {
        return "Document{" +
                "chantiers=" + chantiers +
                '}';
    }
}
