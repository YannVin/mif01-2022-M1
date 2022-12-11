package fr.univ_lyon1.info.m1.mes.model;

import java.util.List;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheStrategy;

public class HealthProfessional {
    private final String name;
    private final MES mes;
    private int anneeDeDiplome;
    private String specialite;

    public HealthProfessional(final String name, final MES mes) {
        this.name = name;
        this.mes = mes;
        mes.addHealthProfessional(this);
        this.anneeDeDiplome = -1;
        this.specialite = "Indefeni";
    }

    public String getName() {
        return name;
    }

    public Patient getPatient(final String ssID, final RechercheStrategy str) {
        return mes.getPatient(ssID, str);
    }

    public List<Prescription> getPrescriptions(final String ssID, final RechercheStrategy str) {
        return mes.getPatient(ssID, str).getPrescriptions(this);
    }

    public Integer getanneeDeDiplome() {
        return anneeDeDiplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setanneDeDiplome(final int annee) {
        this.anneeDeDiplome = annee;
    }

    public void setspecialite(final String specialite) {
        this.specialite = specialite;
    }
}
