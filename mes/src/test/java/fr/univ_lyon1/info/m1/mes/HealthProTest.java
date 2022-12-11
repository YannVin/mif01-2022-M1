package fr.univ_lyon1.info.m1.mes;

import static org.hamcrest.Matchers.*;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheSSIDStrategy;
import fr.univ_lyon1.info.m1.mes.model.Chirurgien;
import fr.univ_lyon1.info.m1.mes.model.Dentist;
import fr.univ_lyon1.info.m1.mes.model.Generalist;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Homeopath;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;

public class HealthProTest {
    MES model = new MES();

    @Test
    /**
     * A simple test, purposely broken so that students can see what happens for
     * test failures.
     */
    public void HealthProfessionalName() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith", model);

        // When
        String name = hp.getName();

        // Then
        assertThat(name, is("Dr. Smith")); // TODO: Obviously wrong, it should be Smith
    }

    @Test
    /**
     * Test addPrescription, and demonstrate advanced Hamcrest assertions.
     */
    public void GetPrescriptionTest() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith", model);
        Patient p = model.createPatient("Alice", "20123456789012");
        p.addPrescription(hp, "Do some sport");
        RechercheSSIDStrategy str = new RechercheSSIDStrategy();

        // When
        List<Prescription> prescriptions = hp.getPrescriptions("20123456789012",str);

        // Then
        assertThat(prescriptions, hasItem(
            hasProperty("content", equalTo("Do some sport"))));
    }

    @Test
    /**
     * Not-so-relevant test, mostly another example of advanced assertion. More
     * relevant things to test: play with several Patients, check that a
     * prescription made for one patient doesn't apply to the other, etc.
     */
    public void GetNotPrescriptionTest() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith", model);
        Patient p = model.createPatient("Alice", "20123456789012");
        p.addPrescription(hp, "Eat fruits");

        // When
        RechercheSSIDStrategy str = new RechercheSSIDStrategy();
        List<Prescription> prescriptions = hp.getPrescriptions("20123456789012",str);

        // Then
        assertThat(prescriptions, not(
            hasItem(
                hasProperty("content", equalTo("Do some sport")))));
    }

    @Test
    public void HealthProfessionalSpecialiteChirurgien() {
        //Given
        HealthProfessionalBuilder chirurgienbuilder = new Chirurgien("Dr.chirurgien", model);
        HealthProfessional chirurgien = chirurgienbuilder.gethHealthProfessional();

        //When
        String specialite = chirurgien.getSpecialite();

        //Then
        assertThat(specialite, is("Chirurgien"));
    }

    @Test
    public void HealthProfessionalSpecialiteDentist() {
        //Given
        HealthProfessionalBuilder dentistbuilder = new Dentist("Dr.dentist", model);
        HealthProfessional dentist = dentistbuilder.gethHealthProfessional();

        //When
        String specialite = dentist.getSpecialite();

        //Then
        assertThat(specialite, is("Dentist"));
    }

    @Test
    public void HealthProfessionalSpecialiteGeneralist() {
        //Given
        HealthProfessionalBuilder generalistbuilder = new Generalist("Dr.Generalist", model);
        HealthProfessional generalist = generalistbuilder.gethHealthProfessional();

        //When
        String specialite = generalist.getSpecialite();

        //Then
        assertThat(specialite, is("Generalist"));
    }

    @Test
    public void HealthProfessionalSpecialiteHomeopath() {
        //Given
        HealthProfessionalBuilder homeopathbuilder = new Homeopath("Dr.Homeopath", model);
        HealthProfessional homeopath = homeopathbuilder.gethHealthProfessional();

        //When
        String specialite = homeopath.getSpecialite();

        //Then
        assertThat(specialite, is("Homeopath"));
    }

    @Test
    public void HealthProfessionalAnneeDiplome() {
        //Given
        HealthProfessionalBuilder generalistbuilder = new Generalist("Dr.generalist", model);
        generalistbuilder.builderAnneeDiplome(2015);
        HealthProfessional generalist = generalistbuilder.gethHealthProfessional();

        //When
        int AnneeDiplome = generalist.getanneeDeDiplome();

        //Then
        assertThat(AnneeDiplome, is(2015));
    }

    @Test
    public void PatientName() {
        //Given
        Patient p = model.createPatient("Patient Test", "123456789");
        
        //When
        String name = p.getName();

        //Then
        assertThat(name, is("Patient Test"));
    }

    @Test 
    public void PatientssID() {
        //Given
        Patient p = model.createPatient("Patient Test", "123456789");
        
        //When
        String ssID = p.getSSID();

        //Then
        assertThat(ssID, is("123456789"));
    }

}
