package pmn.sedatif.models;


import javax.lang.model.element.Name;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Espace")
public class Espace {

    private String id;
    private String name;
    private String adresse;


    public Espace(String id, String name, String adresse) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
    }

    public Espace() {
    }

    @XmlElement(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @XmlElement(name = "Nom")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "Adresse")
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Espace{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
