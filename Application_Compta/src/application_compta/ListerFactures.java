package application_compta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import library_compta.Convert;
import library_compta.Crypto;
import library_compta.Facture;
import library_compta.ProtocoleBISAMAP;


public class ListerFactures extends javax.swing.JPanel
{
    public ListerFactures()
    {
        initComponents();
        ErreurL.setVisible(false);
        FacturesjList.setModel(new DefaultListModel());
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitreLabel = new javax.swing.JLabel();
        IdSocieteL = new javax.swing.JLabel();
        IdSocieteTF = new javax.swing.JTextField();
        Date1L = new javax.swing.JLabel();
        DateMinTF = new javax.swing.JTextField();
        Date2L = new javax.swing.JLabel();
        DateMaxTF = new javax.swing.JTextField();
        ListerButton = new javax.swing.JButton();
        MenuButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        FacturesjList = new javax.swing.JList();
        ErreurL = new javax.swing.JLabel();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("FACTURES :");

        IdSocieteL.setText("Id société :");

        Date1L.setText("Date comprise entre");

        Date2L.setText("et");

        ListerButton.setText("Lister");
        ListerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListerButtonActionPerformed(evt);
            }
        });

        MenuButton.setText("Retour au menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        FacturesjList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(FacturesjList);

        ErreurL.setForeground(new java.awt.Color(255, 0, 0));
        ErreurL.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(IdSocieteL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IdSocieteTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Date1L)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateMinTF, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date2L)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateMaxTF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(ListerButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(MenuButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TitreLabel)
                        .addGap(18, 18, 18)
                        .addComponent(ErreurL)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitreLabel)
                    .addComponent(ErreurL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IdSocieteL)
                    .addComponent(IdSocieteTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Date1L)
                    .addComponent(DateMinTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Date2L)
                    .addComponent(DateMaxTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListerButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        ApplicationCompta a = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        a.ChangePanel("Menu");
    }//GEN-LAST:event_MenuButtonActionPerformed

    
    private void ListerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListerButtonActionPerformed
        try
        {
            ErreurL.setVisible(false);
            
            String idSociete = IdSocieteTF.getText();
            if (idSociete.isEmpty())
            {
                ErreurL.setText("Entrer un id de société !");
                ErreurL.setVisible(true);
                return;
            }

            String dateMin = DateMinTF.getText();
            String dateMax = DateMaxTF.getText();
            if (dateMin.isEmpty() || dateMax.isEmpty())
            {
                ErreurL.setText("Entrer un interval de temps (format YYYY/MM) !");
                ErreurL.setVisible(true);
                return;
            }

            String msg = idSociete + "#" + dateMin + "#" + dateMax;
            String toSign = ProtocoleBISAMAP.LIST_BILLS + msg;
            byte[] signature = Crypto.CreateSignature(toSign.getBytes(), "KSAppCompta.p12", "azerty", "azerty", "AppCompta");

            Utility.SendMsg(ProtocoleBISAMAP.LIST_BILLS, msg);
            Utility.dos.writeInt(signature.length); 
            Utility.dos.write(signature);
            Utility.dos.flush();

            String reponse = Utility.ReceiveMsg();
            String[] parts = reponse.split("#");

            if (parts[0].equals("OUI"))
            {
                try
                {
                    int longueur = Utility.dis.readInt();
                    byte[] listToDecrypt = new byte[longueur];
                    Utility.dis.readFully(listToDecrypt);

                    ApplicationCompta a = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
                    byte[] listDecryptee = Crypto.SymDecrypt(a.CleSecreteChiffrement, listToDecrypt);
                    
                    DefaultListModel dlm = (DefaultListModel)FacturesjList.getModel();

                    List<Facture> listFactures = new ArrayList<Facture>();
                    listFactures = (List<Facture>)Convert.ByteArrayToObject(listDecryptee);

                    for(Facture facture : listFactures)
                    {
                        dlm.addElement(facture);
                    }
                }
                catch (IOException ex)
                {
                    System.err.println("ListerFactures : IOException : ListerButtonActionPerformed : " + ex.getMessage());
                }
            }
            else
            {
                ErreurL.setText(parts[1]);
                ErreurL.setVisible(true);
            }
        }
        catch (IOException ex)
        {
            System.err.println("ListerFactures : IOException : ListerButtonActionPerformed : " + ex.getMessage());
        }
    }//GEN-LAST:event_ListerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date1L;
    private javax.swing.JLabel Date2L;
    private javax.swing.JTextField DateMaxTF;
    private javax.swing.JTextField DateMinTF;
    private javax.swing.JLabel ErreurL;
    private javax.swing.JList FacturesjList;
    private javax.swing.JLabel IdSocieteL;
    private javax.swing.JTextField IdSocieteTF;
    private javax.swing.JButton ListerButton;
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel TitreLabel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
