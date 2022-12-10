package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.Observer.Observer;
import fr.univ_lyon1.info.m1.mes.controler.ControlerPatient;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PatientView implements Observer {
    private final Pane pane = new VBox();
    private final Patient patient;
    private final ControlerPatient cpv; //MOI
    private Pane prescriptionPane = new VBox();

    public PatientView(final Patient p) {
        this.patient = p;
        this.cpv = new ControlerPatient(this, p); //MOI

        p.register(this);
        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");

        final Label l = new Label(p.getName());
        final Button bSSID = new Button("COPY"); //📋


        bSSID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                cpv.copieSSID();
            }
        });

        Button bReload = new Button("RELOAD"); //🗘
        bReload.setOnAction(ActionEvent -> showPrescriptions());
        final HBox nameBox = new HBox();
        nameBox.getChildren().addAll(l, bSSID, bReload);
        pane.getChildren().addAll(nameBox, prescriptionPane);
        showPrescriptions();
    }

    public void showPrescriptions() {
        prescriptionPane.getChildren().clear();
        prescriptionPane.getChildren().add(new Label("Prescriptions:\n"));
        for (final Prescription pr : patient.getPrescriptions()) {
            prescriptionPane.getChildren().add(new Label("- From "
                    + pr.getHealthProfessional().getName()
                    + ": " + pr.getContent()));
        }
    }

    public Pane asPane() {
        return pane;
    }

    @Override
    public void update() {
        showPrescriptions();
    }

}
