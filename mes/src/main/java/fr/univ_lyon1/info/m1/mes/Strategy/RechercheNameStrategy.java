package fr.univ_lyon1.info.m1.mes.Strategy;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;

public class RechercheNameStrategy implements RechercheStrategy {

    @Override
    public Patient getPatient(final String name, final MES mes) {
        List<Patient> patients = mes.getPatients();

        for (Patient i : patients) {
            if (i.getName().equals(name)) {
                return i;
            }       
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "name";
        return res;
    }
}
