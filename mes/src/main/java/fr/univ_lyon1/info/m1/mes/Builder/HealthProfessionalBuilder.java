package fr.univ_lyon1.info.m1.mes.Builder;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;

public abstract class HealthProfessionalBuilder {
    private HealthProfessional healthProfessional;

    public HealthProfessional gethHealthProfessional() {
        return healthProfessional;
    }
    
    public HealthProfessional createNewHP(final String name, final MES mes) {
        healthProfessional = new HealthProfessional(name, mes);
        return healthProfessional;
    }

    public void builderAnneeDiplome(final int annee) {
        healthProfessional.setanneDeDiplome(annee);
    }

    public void setHealthProfessional(final HealthProfessional hp) {
        this.healthProfessional = hp;
    }

    public abstract void builderspecialite();
}
