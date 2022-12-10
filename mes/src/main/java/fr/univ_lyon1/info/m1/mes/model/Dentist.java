package fr.univ_lyon1.info.m1.mes.model;

import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;

public class Dentist extends HealthProfessionalBuilder {
    public Dentist(final String name, final MES mes) {
        setHealthProfessional(new HealthProfessional(name, mes));
        gethHealthProfessional().setspecialite("Dentiste");
    }

    @Override
    public void builderspecialite() {
        gethHealthProfessional().setspecialite("dentist");
    }

}
