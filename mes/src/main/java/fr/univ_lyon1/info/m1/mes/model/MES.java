package fr.univ_lyon1.info.m1.mes.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;
import fr.univ_lyon1.info.m1.mes.Observer.Subject;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheNameStrategy;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheStrategy;

public class MES extends Subject {
    private final List<HealthProfessional> healthProfessionals = new ArrayList<>();
    private Map<String, Patient> registry = new HashMap<>();

    public Map<String, Patient> getregistry() {
        return registry;
    }
    public Patient getPatient(final String info, final RechercheStrategy methode) {
        return methode.getPatient(info, this);
    }

    public Patient createPatient(final String name, final String ssID) {

        final Patient p = new Patient(name, ssID);
        registry.put(ssID, p);
        notifyObservers();
        return p;
    }

    public void addHealthProfessional(final HealthProfessional hp) {
        healthProfessionals.add(hp);
    };
    
    public List<Patient> getPatients() {
        return new ArrayList<>(registry.values());
    }

    public HealthProfessional getHealthProfessionalByName(final String hpName) {
        for (HealthProfessional h : healthProfessionals) {
            if (h.getName().equals(hpName)) {
                return h;
            }
        }
        throw new IllegalArgumentException("Pas de " + hpName);
    }

    public List<HealthProfessional> getHealthProfessional() {
        return healthProfessionals;
    }

    /**
     * Read preconfig.yml, create list of Patient, HealthPRofessional, Prescription.
     */
    public void createExampleConfiguration() throws FileNotFoundException {
        File file = new File("src/main/java/fr/univ_lyon1/info/m1/mes/Ressources/Preconfig.yml");
        InputStream inputStream = new FileInputStream(file);
        Yaml yaml = new Yaml();
        Map<String, List<Object>> data = yaml.load(inputStream);

        List<Object> listPatient = data.get("Patient");
        List<Object> listHP = data.get("HealthProfessional");
        List<Object> listPresc = data.get("Prescription");
        
        for (int i = 0; i < listPatient.size(); i++) {
            Map<String, String> patient = (Map<String, String>) listPatient.get(i);
            String patientName = String.valueOf(patient.get("name"));
            String patientSSID = String.valueOf(patient.get("ssID"));
            createPatient(patientName, patientSSID);
        }

        for (int i = 0; i < listHP.size(); i++) {
            Map<String, String> hp = (Map<String, String>) listHP.get(i);
            String hpName = String.valueOf(hp.get("name"));
            String hpSpecialite = String.valueOf(hp.get("specialite"));

            HealthProfessionalBuilder hpb;
            if (hpSpecialite.equals("dentist")) {
                hpb = new Dentist(hpName, this);
            } else if (hpSpecialite.equals("Homeopath")) {
                hpb = new Homeopath(hpName, this);
            } else if (hpSpecialite.equals("chirurgien")) {
                hpb = new Chirurgien(hpName, this);
            } else {
                hpb = new Generalist(hpName, this);
            }
            if (hp.get("annee") != null) {
                Integer hpAnnee = Integer.valueOf(String.valueOf(hp.get("annee")));
                hpb.builderAnneeDiplome(hpAnnee);
            }

        }

        for (int i = 0; i < listPresc.size(); i++) {
            Map<String, String> prescription = (Map<String, String>) listPresc.get(i);
            String prescriptionPatientName = String.valueOf(prescription.get("namepatient"));
            String prescriptionNameHP = String.valueOf(prescription.get("namehp"));
            String prescriptionContent = String.valueOf(prescription.get("content"));
            RechercheStrategy rnstrat = new RechercheNameStrategy();
            Patient patient = getPatient(prescriptionPatientName, rnstrat);
            try {
                HealthProfessional hp = getHealthProfessionalByName(prescriptionNameHP);
                patient.addPrescription(hp, prescriptionContent);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
