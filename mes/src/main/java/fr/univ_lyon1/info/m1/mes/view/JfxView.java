package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.Observer.Observer;
import fr.univ_lyon1.info.m1.mes.controler.ControlerJFX;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import fr.univ_lyon1.info.m1.mes.model.MES;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JfxView implements Observer {
    private Pane createpatients = new HBox();
    private Pane affichagepatients = new VBox();
    private Pane healthPro = new VBox();
    
    private final MES mes;
    private final ControlerJFX cjfx;
    /**
     * Create the main view of the application.
     */
    public JfxView(final MES mes, final Stage stage, final int width, final int height) {
        this.mes = mes;
        this.cjfx = new ControlerJFX(mes, this);

        mes.register(this);
        
        // Name of window
        stage.setTitle("Mon Espace Santé");

        //Style
        createpatients.setStyle("-fx-border-color: gray;\n"
                                + "-fx-border-insets: 3;\n"
                                + "-fx-alignment : center;"
                                + "-fx-background-color: #0c419a;");

        //Emplacement affichage
        final VBox root = new VBox(10);
        final HBox root2 = new HBox(10);
        
        //root.setStyle("-fx-background-color: #ffffff;");
        
        createPatientsWidget();
        root.getChildren().addAll(createpatients);
        
        root.setStyle("-fx-background-color: #a6def4;");
        affichagepatient();
        root2.getChildren().add(affichagepatients);

        createHPWidget();
        root2.getChildren().add(healthPro);

        root.getChildren().add(root2);
        HBox.setHgrow(affichagepatients, Priority.SOMETIMES);
        HBox.setHgrow(healthPro, Priority.ALWAYS);

        
        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    private Pane createHPWidget() {
        for (HealthProfessional p : mes.getHealthProfessional()) {
            HealthProfessionalView hpv = new HealthProfessionalView(p);
            healthPro.getChildren().add(hpv.asPane());
        }
        return healthPro;
    }

    public void createPatientsWidget() {
        createpatients.getChildren().clear();
        Label titre = new Label("MON ESPACE SANTÉ");
        final Label l = new Label("Add new patient :");
        
        final Label nameL = new Label("Name:");
        final TextField nameT = new TextField();
        final Label ssIDL = new Label("ssID:  ");
        final TextField ssIDT = new TextField();
        final Button newP = new Button("Add");
       
        VBox ajoutpatientwidget = new VBox(5);
        Pane boxname = new HBox(nameL, nameT);
        Pane boxssid = new HBox(ssIDL, ssIDT);
        Pane boxbutton = new HBox(newP);

        l.setStyle("-fx-text-fill: #ffffff;");
        nameL.setStyle("-fx-text-fill: #ffffff;");
        ssIDL.setStyle("-fx-text-fill: #ffffff;");
        newP.setStyle("-fx-background-color: #ffffff");
        
        //boxbutton.setStyle("-fx-alignment: center;");

        ajoutpatientwidget.getChildren().addAll(titre, l, boxname, boxssid, boxbutton);

        titre.setStyle("-fx-alignment : center;"
                    + "-fx-text-fill: #ffffff;"
                    + "-fx-font-size : 25px");
                    
        boxname.setStyle("-fx-alignment : center;");
        boxssid.setStyle("-fx-alignment : center;");
        boxbutton.setStyle("-fx-alignment : center;");

        createpatients.getChildren().addAll(ajoutpatientwidget);

        newP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                cjfx.ajoutPatient(nameT, ssIDT);
            }        
        });
    }

    public void affichagepatient() {
        affichagepatients.getChildren().clear();        
            for (Patient p : mes.getPatients()) {
            final PatientView hpv = new PatientView(p);
            affichagepatients.getChildren().add(hpv.asPane());
        }
    }

    @Override
    public void update() {
        affichagepatient();
    }

    
}
