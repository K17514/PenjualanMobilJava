/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_mobil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LaporanJualKredit extends javax.swing.JFrame {

    private DefaultTableModel model = null;
    private PreparedStatement stat;
    private ResultSet rs;
    Koneksi k = new Koneksi();

    /**
     * Creates new form LaporanJualCash
     */
    public LaporanJualKredit() {
        initComponents();
        k.connect();
        dateperiode.setEnabled(true);
        dateawal.setEnabled(false);
        dateakhir.setEnabled(false);
    }

    public void tampildata() {
        try {
            String pilihan = cbpilihan.getSelectedItem().toString();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "SELECT k.kode_kredit,p.nama_pembeli,"
                    + "CONCAT (m.merk,'',m.type) AS mobil,"
                    + "k.tanggal_kredit,k.bayar_kredit,k.tenor,k.totalcicil "
                    + "FROM kredit k "
                    + "JOIN pembeli p ON k.ktp=p.ktp "
                    + "JOIN mobil m ON k.kode_mobil=m.kode_mobil ";

            if ("Harian".equals(pilihan)) {
                Date date = dateperiode.getDate();
                String tgl = df.format(date);
                sql += "WHERE k.tanggal_kredit='" + tgl + "'";
            } else if ("Mingguan".equals(pilihan)) {
                dateperiode.setEnabled(false);
                Date awal = dateawal.getDate();
                Date akhir = dateakhir.getDate();
                String tglawal = df.format(awal);
                String tglakhir = df.format(akhir);
                sql += " WHERE k.tanggal_kredit BETWEEN '" + tglawal + "' AND '" + tglakhir + "'";
            } else if ("Bulanan".equals(pilihan)) {
                Date date = dateperiode.getDate();
                DateFormat bln = new SimpleDateFormat("MM");
                DateFormat thn = new SimpleDateFormat("yyyy");
                String bulan = bln.format(date);
                String tahun = thn.format(date);
                sql += "WHERE MONTH(k.tanggal_kredit)=" + bulan + " AND YEAR (k.tanggal_kredit)= " + tahun;
            } else if ("Tahunan".equals(pilihan)) {
                Date date = dateperiode.getDate();
                DateFormat thn = new SimpleDateFormat("yyyy");
                String tahun = thn.format(date);
                sql += "WHERE YEAR(k.tanggal_kredit)=" + tahun;
            }

            stat = k.getCon().prepareStatement(sql);
            rs = stat.executeQuery();
            model = new DefaultTableModel(new String[]{
                "Kode Kredit", "Nama Pembeli", "Merk dan Tipe Mobil", "Tanggal", "Cicilan Perbulan", "Tenor", "Total Cicil"
            }, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("kode_kredit"),
                    rs.getString("nama_pembeli"),
                    rs.getString("mobil"),
                    rs.getDate("tanggal_kredit"),
                    rs.getDouble("bayar_kredit"),
                    rs.getDouble("tenor"),
                    rs.getDouble("totalcicil"),});
            }
            tblkredit.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error menampilkan data!" + e.getMessage());
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkredit = new javax.swing.JTable();
        cbpilihan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateperiode = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dateawal = new com.toedter.calendar.JDateChooser();
        dateakhir = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btntampil = new javax.swing.JButton();
        btnkembali = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Laporan Penjualan Mobil Secara Kredit");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 25, 469, 27));

        tblkredit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblkredit);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 900, 276));

        cbpilihan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Harian", "Mingguan", "Bulanan", "Tahunan" }));
        cbpilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbpilihanActionPerformed(evt);
            }
        });
        getContentPane().add(cbpilihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 126, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pilihan Laporan:");
        jLabel2.setAutoscrolls(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 120, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Periode:");
        jLabel3.setAutoscrolls(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 64, 20));
        getContentPane().add(dateperiode, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 145, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pilihan Laporan:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, -1, 20));
        getContentPane().add(dateawal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 248, 20));
        getContentPane().add(dateakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 245, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tanggal Awal");
        jLabel5.setAutoscrolls(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tanggal Akhir");
        jLabel6.setAutoscrolls(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, -1, 20));

        btntampil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btntampil.setText("Tampilkan Laporan");
        btntampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntampilActionPerformed(evt);
            }
        });
        getContentPane().add(btntampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 222, 40));

        btnkembali.setText("Kembali");
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_mobil/cdc202810c45351747f46f91408291c6_4953853260851452841 (1).jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbpilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbpilihanActionPerformed
        String pilihan = cbpilihan.getSelectedItem().toString();
        if ("Harian".equals(pilihan) || "Bulanan".equals(pilihan) || "Tahunan".equals(pilihan)) {
            dateperiode.setEnabled(true);
            dateawal.setEnabled(false);
            dateakhir.setEnabled(false);
        } else if ("Mingguan".equals(pilihan)) {
            dateperiode.setEnabled(false);
            dateawal.setEnabled(true);
            dateakhir.setEnabled(true);
        }
    }//GEN-LAST:event_cbpilihanActionPerformed

    private void btntampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntampilActionPerformed
        tampildata();
    }//GEN-LAST:event_btntampilActionPerformed

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        new MenuUtama(2).show();
        this.dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LaporanJualKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanJualKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanJualKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanJualKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanJualKredit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btntampil;
    private javax.swing.JComboBox<String> cbpilihan;
    private com.toedter.calendar.JDateChooser dateakhir;
    private com.toedter.calendar.JDateChooser dateawal;
    private com.toedter.calendar.JDateChooser dateperiode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblkredit;
    // End of variables declaration//GEN-END:variables
}
