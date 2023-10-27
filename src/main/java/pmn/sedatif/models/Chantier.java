package pmn.sedatif.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Chantier")
public class Chantier {

    private List<Espace> espaces;
    private List<Tache> taches;

    public Chantier(List<Espace> espaces, List<Tache> taches) {
        this.espaces = espaces;
        this.taches = taches;
    }

    public Chantier() {
    }

    @XmlElement(name = "Espace")
    public List<Espace> getEspaces() {
        return espaces;
    }

    public void setEspaces(List<Espace> espaces) {
        this.espaces = espaces;
    }

    @XmlElement(name = "Tache")
    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    @Override
    public String toString() {
        return "Chantier{" +
                "espaces=" + espaces +
                ", taches=" + taches +
                '}';
    }
}
