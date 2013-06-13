package lsystem;

/**
 * @author Alex Pinkney
 * @since 15-Mar-2011, 16:34:53
 */
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class LSystemSettings extends javax.swing.JFrame {

    private LSystemDisplay lsysdisp;
    private ArrayList<LSystemRuleset> rulesets;

    /**
     * Creates new form LSystemSettings
     */
    public LSystemSettings(ArrayList<LSystemRuleset> rulesets) {
        this.rulesets = rulesets;
        initComponents();
        populateList();
        refreshRuleArea();
        lsysdisp = new LSystemDisplay(rulesets.get(fileList.getSelectedIndex()), (Integer) (iterations.getValue()));
    }

    public final void populateList() {

        DefaultListModel m = ((DefaultListModel) (fileList.getModel()));
        m.clear();
        for (LSystemRuleset rs : rulesets) {
            m.addElement(rs.NAME);
        }
        fileList.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        iterations = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        ruleTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LSystem Settings");

        jLabel1.setText("Input File");

        fileList.setModel(new DefaultListModel());
        fileList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fileListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(fileList);

        jLabel2.setText("Iterations");

        iterations.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        iterations.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                iterationsStateChanged(evt);
            }
        });

        ruleTextArea.setColumns(20);
        ruleTextArea.setEditable(false);
        ruleTextArea.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        ruleTextArea.setRows(5);
        jScrollPane2.setViewportView(ruleTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(iterations, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(79, 79, 79))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(iterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iterationsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_iterationsStateChanged
        if (lsysdisp != null) {
            int n = (Integer) (iterations.getValue());
            LSystemRuleset rs = rulesets.get(fileList.getSelectedIndex());
            rs.setLastViewedIt(n);
            lsysdisp.setIterations(n);
            refreshRuleArea();
        }
    }//GEN-LAST:event_iterationsStateChanged

    private void fileListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_fileListValueChanged
        if (lsysdisp != null) {
            LSystemRuleset rs = rulesets.get(fileList.getSelectedIndex());
            lsysdisp.setIterations(rs.getLastViewedIt());
            iterations.setValue(new Integer(rs.getLastViewedIt()));
            lsysdisp.setRuleset(rs);
            refreshRuleArea();
        }
    }//GEN-LAST:event_fileListValueChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList fileList;
    private javax.swing.JSpinner iterations;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea ruleTextArea;
    // End of variables declaration//GEN-END:variables

    public void setRulesets(ArrayList<LSystemRuleset> rulesets) {
        this.rulesets = rulesets;
    }

    private void refreshRuleArea() {
        LSystemRuleset r = rulesets.get(fileList.getSelectedIndex());
        ruleTextArea.setText(r.getRuleText());
    }
}
