package Modelo;

import javax.swing.table.*;

/**
 * Created by miguel on 3/05/16.
 */
public class ModeloTabla extends DefaultTableModel {

    public ModeloTabla(Object[][] o, Object[] v){
        super(o,v);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
