package fr.univ_lyon1.info.m1.mes.controler;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheStrategy;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.view.HealthProfessionalView;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControlerHPro {
    private HealthProfessionalView hpv;
    private HealthProfessional hp;
    private HealthProfessionalBuilder hpb;

    public ControlerHPro(final HealthProfessionalView hpv, 
                        final HealthProfessional hp,
                        final HealthProfessionalBuilder hpb) {
        this.hpv = hpv;
        this.hp = hp;
        this.hpb = hpb;
    }

    private List<String> predefPrescr = new ArrayList<>();

    public void prescriptionPredefini() {
        predefPrescr.add("Paracetamol");
    }

    public void prescribe(final String prescription,
                            final String selectedPatientSSID,
                            final RechercheStrategy str) {
        if (hpv.getSelectedPatientSSID() == null) {
            EasyAlert.alert("Please select a patient first");
            return;
        }
        hp.getPatient(selectedPatientSSID, str).addPrescription(hp, prescription);
        hpv.showPrescriptions();
    }

    public void getPrescriptions(final HealthProfessional hp, final Patient p) {
        hpv.getprescriptions().getChildren().add(new Label(
            "Prescriptions for " + p.getName()));

        for (final Prescription pr : p.getPrescriptions(hp)) {
            final HBox pView = new HBox();
            final Label content = new Label(
                    "- " + pr.getContent());
            final Button removeBtn = new Button("x");
            removeBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    p.removePrescription(pr);
                    pView.getChildren().remove(content);
                    pView.getChildren().remove(removeBtn);
                }
    
            });
            pView.getChildren().addAll(content, removeBtn);
            hpv.getprescriptions().getChildren().add(pView);
        }
    }
    
    public List<String> getpredefPrescr() {
        return predefPrescr;
    }

}
