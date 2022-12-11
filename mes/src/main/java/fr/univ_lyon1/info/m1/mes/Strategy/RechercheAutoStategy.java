package fr.univ_lyon1.info.m1.mes.Strategy;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;

public class RechercheAutoStategy implements RechercheStrategy {

    @Override
    public Patient getPatient(final String name, final MES mes) {
        List<Patient> patients = mes.getPatients();
        List<Patient> result = new ArrayList<>();
        for (Patient i : patients) {
            if (i.getName().contains(name)) {
                result.add(i);
            }       
        }
        if (result.size() == 1) {
            return result.get(0);
        } else {
            EasyAlert.alert("Développer plus votre recherche");
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "Auto";
        return res;
    }

}
