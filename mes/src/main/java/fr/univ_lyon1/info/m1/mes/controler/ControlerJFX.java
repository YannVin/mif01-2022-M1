package fr.univ_lyon1.info.m1.mes.controler;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.view.JfxView;
import javafx.scene.control.TextField;

public class ControlerJFX {
    private MES m;
    private JfxView j;
    
    public ControlerJFX(final MES m, final JfxView j) {
        this.m = m;
        this.j = j;
    }

    public void ajoutPatient(final TextField name, final TextField ssid) {
       final String txtname = name.getText().trim();
       final String txtssid = ssid.getText().trim();
       m.createPatient(txtname, txtssid);
       j.createPatientsWidget(); 
    }
}
