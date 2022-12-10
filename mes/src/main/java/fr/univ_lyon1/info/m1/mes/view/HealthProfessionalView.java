package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.Builder.HealthProfessionalBuilder;
import fr.univ_lyon1.info.m1.mes.Observer.Observer;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheAutoStategy;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheNameStrategy;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheSSIDStrategy;
import fr.univ_lyon1.info.m1.mes.Strategy.RechercheStrategy;
import fr.univ_lyon1.info.m1.mes.controler.ControlerHPro;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HealthProfessionalView implements Observer {
    private final VBox pane = new VBox();
    private HealthProfessional healthProfessional;
    private String selectedPatientSSID;
    private ControlerHPro hpv;
    private HealthProfessionalBuilder hpb;
    private final VBox prescriptions = new VBox();

    private ComboBox<RechercheStrategy> myComboBox = new ComboBox<>();
    private RechercheStrategy str = myComboBox.getSelectionModel().
                                    selectedItemProperty()
                                    .getValue();
    
    public HealthProfessionalView(final HealthProfessional hp) {
        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");
        this.healthProfessional = hp;
        this.hpv = new ControlerHPro(this, hp, hpb);
        
        final Label l = new Label(hp.getName());
        final Label l1 = new Label("Diplôme obtenu le : " + String.valueOf(hp.getanneeDeDiplome()));
        final Label l2 = new Label("Spécialité : " + hp.getSpecialite());
        final Label l3 = new Label("Research by :");

        pane.getChildren().add(l);
        if (hp.getanneeDeDiplome() != -1) {
            pane.getChildren().add(l1);
        }
        pane.getChildren().add(l2);
        pane.getChildren().add(l3);
        
        myComboBox.getItems().add(new RechercheNameStrategy());
        myComboBox.getItems().add(new RechercheSSIDStrategy());
        myComboBox.getItems().add(new RechercheAutoStategy());
        myComboBox.getSelectionModel().select(0);

        pane.getChildren().add(myComboBox);
        
        final HBox search = new HBox();
        final TextField t = new TextField();
        final Button b = new Button("Search");
        search.getChildren().addAll(t, b);
        pane.getChildren().addAll(search, prescriptions);

        final EventHandler<ActionEvent> ssHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final String text = t.getText().trim();
                str = myComboBox.getSelectionModel().selectedItemProperty().getValue();
                if (text.equals("")) {
                    return; // Do nothing
                }
                selectedPatientSSID = text;
                showPrescriptions();
                t.setText("");
                t.requestFocus();
            }
        };

        b.setOnAction(ssHandler);
        t.setOnAction(ssHandler);

        pane.getChildren().add(new Label("Prescribe"));
        final HBox addPrescription = new HBox();
        final TextField tp = new TextField();
        final Button bp = new Button("Add");
        addPrescription.getChildren().addAll(tp, bp);
        pane.getChildren().add(addPrescription);

        final EventHandler<ActionEvent> prescriptionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final String text = tp.getText().trim();
                if (text.equals("")) {
                    return; // Do nothing
                }
                tp.setText("");
                tp.requestFocus();
                hpv.prescribe(text, selectedPatientSSID, str);
            }
        };

        // TODO: someone wrote some business logic within the view :-\
        hpv.prescriptionPredefini();
        for (final String p : hpv.getpredefPrescr()) {
            final Button predefPrescrB = new Button(p);
            predefPrescrB.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    hpv.prescribe(p, selectedPatientSSID, str);
                }
            });
            pane.getChildren().add(predefPrescrB);
        }
        tp.setOnAction(prescriptionHandler);
        bp.setOnAction(prescriptionHandler);
    }

    public void showPrescriptions() {
        prescriptions.getChildren().clear();
        Patient p = healthProfessional.getPatient(selectedPatientSSID, str);
        if (p == null) {
            prescriptions.getChildren().add(new Label(
                    "Use search above to see prescriptions"));
            return;
        } 
        hpv.getPrescriptions(healthProfessional, p);
        p.register(this);
    }

    public String getSelectedPatientSSID() {
        return selectedPatientSSID;
    }

    public VBox getprescriptions() {
        return prescriptions;
    }

    public Pane asPane() {
        return pane;
    }

    @Override
    public void update() {
        this.showPrescriptions();
    }

}
