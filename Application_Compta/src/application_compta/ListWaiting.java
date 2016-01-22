package application_compta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import library_compta.Convert;
import library_compta.Crypto;
import library_compta.Facture;
import library_compta.ProtocoleBISAMAP;


public class ListWaiting extends javax.swing.JPanel
{
    private String filtre;
    
    public ListWaiting()
    {
        initComponents();
        
        IdSocieteTF.setVisible(false);
        FiltreCB.addItem("Toutes");
        FiltreCB.addItem("Emises depuis plus d'un mois");
        FiltreCB.addItem("D'une société données");
        FiltreCB.setSelectedItem("Toutes");
        ErreurL.setVisible(false);
        FacturesjList.setModel(new DefaultListModel());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitreLabel = new javax.swing.JLabel();
        FiltreCB = new javax.swing.JComboBox();
        MenuButton = new javax.swing.JButton();
        AfficherL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FacturesjList = new javax.swing.JList();
        IdSocieteTF = new javax.swing.JTextField();
        ListerButton = new javax.swing.JButton();
        ErreurL = new javax.swing.JLabel();

        TitreLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TitreLabel.setForeground(new java.awt.Color(0, 0, 255));
        TitreLabel.setText("FACTURES NON PAYEES :");

        FiltreCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FiltreCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltreCBActionPerformed(evt);
            }
        });

        MenuButton.setText("Retour au menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        AfficherL.setText("Afficher :");

        FacturesjList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(FacturesjList);

        IdSocieteTF.setText("Id de la société");

        ListerButton.setText("Lister");
        ListerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListerButtonActionPerformed(evt);
            }
        });

        ErreurL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ErreurL.setForeground(new java.awt.Color(255, 0, 0));
        ErreurL.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ErreurL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MenuButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TitreLabel)
                        .addGap(18, 18, 18)
                        .addComponent(AfficherL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FiltreCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(IdSocieteTF, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ListerButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitreLabel)
                    .addComponent(FiltreCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AfficherL)
                    .addComponent(IdSocieteTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListerButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MenuButton)
                    .addComponent(ErreurL))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        ApplicationCompta a = (ApplicationCompta)SwingUtilities.getWindowAncestor(this);
        a.ChangePanel("Menu");
    }//GEN-LAST:event_MenuButtonActionPerformed

    
    private void FiltreCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltreCBActionPerformed
        IdSocieteTF.setVisible(false);
        
        filtre = FiltreCB.getSelectedItem().toString();
        
        if(filtre.equals("Emises depuis plus d'un mois"))
        {
            IdSocieteTF.setVisible(true);
        }
    }//GEN-LAST:event_FiltreCBActionPerformed

    
    private void ListerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListerButtonActionPerformed
        try
        {
            String msg;

            if(filtre.equals("Toutes"))
                msg = "1";
            else if(filtre.equals("Emises depuis plus d'un mois"))
                msg = "2";
            else // Société
            {
                String idSociete = IdSocieteTF.getText();
                if(idSociete.isEmpty())
                {
                    ErreurL.setText("Entrer un id de société !");
                    ErreurL.setVisible(true);
                    return;
                }

                msg = "3" + IdSocieteTF.getText();
            }

            String toSign = ProtocoleBISAMAP.LIST_WAITING + msg;
            byte[] signature = Crypto.CreateSignature(toSign.getBytes(), "KSAppCompta.p12", "azerty", "azerty", "AppCompta");

            Utility.SendMsg(ProtocoleBISAMAP.LIST_WAITING, msg);
            Utility.dos.writeInt(signature.length); 
            Utility.dos.write(signature);
            Utility.dos.flush();

            String reponse = Utility.ReceiveMsg();
            String[] parts = reponse.split("#");

            if (parts[0].equals("OUI"))
            {
                int longueur = Utility.dis.readInt();
                byte[] list = new byte[longueur];
                Utility.dis.readFully(list);
                
                List<Facture> listFactures = new ArrayList<Facture>();
                listFactures = (List<Facture>)Convert.ByteArrayToObject(list);
                
                DefaultListModel dlm = (DefaultListModel)FacturesjList.getModel();
                for(Facture facture : listFactures)
                {
                    dlm.addElement(facture);
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
            System.err.println("ListWaiting : IOException : ListerButtonActionPerformed : " + ex.getMessage());
        }
        
    }//GEN-LAST:event_ListerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AfficherL;
    private javax.swing.JLabel ErreurL;
    private javax.swing.JList FacturesjList;
    private javax.swing.JComboBox FiltreCB;
    private javax.swing.JTextField IdSocieteTF;
    private javax.swing.JButton ListerButton;
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel TitreLabel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
