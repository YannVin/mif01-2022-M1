package fr.univ_lyon1.info.m1.mes.Strategy;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;

public interface RechercheStrategy {
    Patient getPatient(String info, MES mes);
    String toString();
}
