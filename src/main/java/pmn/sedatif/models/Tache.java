package pmn.sedatif.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Tache {

    private String name;
    private String etat;
    private String df;

    public Tache(String name, String etat, String df) {
        this.name = name;
        this.etat = etat;
        this.df = df;
    }

    public Tache() {
    }

    @XmlElement(name = "Nom")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "Etat")
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    @XmlElement(name = "DateFin")
    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "name='" + name + '\'' +
                ", etat='" + etat + '\'' +
                ", df='" + df + '\'' +
                '}';
    }
}

