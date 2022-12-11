package fr.univ_lyon1.info.m1.mes.controler;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.view.PatientView;
import fr.univ_lyon1.info.m1.mes.utils.EasyClipboard;

public class ControlerPatient {
    private PatientView j;
    private final Patient p;

    public ControlerPatient(final PatientView j, final Patient p) {
        this.j = j;
        this.p = p;
    }

    public void copieSSID() {
        EasyClipboard.copy(p.getSSID());
    }
   
}
