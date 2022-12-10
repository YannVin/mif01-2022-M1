package fr.univ_lyon1.info.m1.mes.Strategy;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;

public class RechercheSSIDStrategy implements RechercheStrategy {
    @Override
    public Patient getPatient(final String ssID, final MES mes) {
        return mes.getregistry().get(ssID);
    }

    @Override
    public String toString() {
        String res = "SSID";
        return res;
    }
}
