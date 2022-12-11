package fr.univ_lyon1.info.m1.mes.model;

import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;

public class Homeopath extends HealthProfessionalBuilder {
    public Homeopath(final String name, final MES mes) {
        setHealthProfessional(new HealthProfessional(name, mes));
        gethHealthProfessional().setspecialite("Homeopath");
    }
    
    @Override
    public void builderspecialite() {
        gethHealthProfessional().setspecialite("Homeopath");
    }
}
