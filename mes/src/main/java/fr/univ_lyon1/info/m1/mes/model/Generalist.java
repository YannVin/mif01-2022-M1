package fr.univ_lyon1.info.m1.mes.model;

import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;

public class Generalist extends HealthProfessionalBuilder {
    public Generalist(final String name, final MES mes) {
        setHealthProfessional(new HealthProfessional(name, mes));
        gethHealthProfessional().setspecialite("Generalist");
    }

    @Override
    public void builderspecialite() {
        gethHealthProfessional().setspecialite("generalist");
    }

    
}
