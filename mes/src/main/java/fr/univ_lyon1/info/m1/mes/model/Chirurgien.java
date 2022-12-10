package fr.univ_lyon1.info.m1.mes.model;

import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;

public class Chirurgien extends HealthProfessionalBuilder {
    public Chirurgien(final String name, final MES mes) {
        
        //HealthProfessional hp = new HealthProfessional(name, mes);

        //healthProfessional.setHealthProfessional(hp);

        setHealthProfessional(new HealthProfessional(name, mes));
        gethHealthProfessional().setspecialite("Chirurgien");
    }

    @Override
    public void builderspecialite() {
        gethHealthProfessional().setspecialite("chirurgien");
    }

    
}
