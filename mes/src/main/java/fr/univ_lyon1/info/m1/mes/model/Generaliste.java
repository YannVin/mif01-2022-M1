package fr.univ_lyon1.info.m1.mes.model;

import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;

public class Generaliste extends HealthProfessionalBuilder {
    public Generaliste(final String name, final MES mes) {
        setHealthProfessional(new HealthProfessional(name, mes));
        gethHealthProfessional().setspecialite("Generaliste");
    }

    @Override
    public void builderspecialite() {
        gethHealthProfessional().setspecialite("generaliste");
    }

    
}
