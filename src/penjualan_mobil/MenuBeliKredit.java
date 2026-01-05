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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class MenuBeliKredit extends javax.swing.JFrame {

    private DefaultTableModel model = null;
    private PreparedStatement stat;
    private ResultSet rs;
    Koneksi k = new Koneksi();
    private String pathKTP = "";
    private String pathSlip = "";

    /**
     * Creates new form MenuDataMobil
     */
    public MenuBeliKredit() {
        initComponents();
        k.connect();
        Tampiltabel();
        ComboMobil();
        ComboPembeli();
        ComboPaket();
    }

    class kredit extends MenuBeliKredit {

        String kode_kredit, ktp, kode_paket, kode_mobil, tanggal_kredit;
        int bayar_kredit, tenor, totalcicil;

        public kredit() {
            this.kode_kredit = textkode.getText();
            String combo1 = cbktp.getSelectedItem().toString();
            this.ktp = combo1.split(":")[0];

            String combo2 = cbkdmobil.getSelectedItem().toString();
            String[] arr1 = combo2.split(":");
            this.kode_mobil = arr1[0];  // "M001"                  // "Silver"
            int harga = Integer.parseInt(arr1[4]);  // 200000000
            String KomboPaket = cbkpaket.getSelectedItem().toString();
            String[] arrpaket = KomboPaket.split(":");
            this.kode_paket = arrpaket[0];
            double uang_muka = Double.parseDouble(arrpaket[1]);
            double tenor = Double.parseDouble(arrpaket[2]);
            double bunga_cicilan = Double.parseDouble(arrpaket[3]);

            double dp = harga * uang_muka / 100;
            double pinjaman = harga - dp;
            this.totalcicil = (int) (pinjaman + (pinjaman * bunga_cicilan * tenor / 12) / 100);
            this.bayar_kredit = (int) (this.totalcicil / tenor);

            this.tenor = (int) tenor;

            try {
                Date date = datetanggal.getDate();
                DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
                this.tanggal_kredit = df.format(date);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tanggal Harus Dimasukkan" + e.getMessage());
            }

        }
    }

    public void ComboMobil() {
        try {
            this.stat = k.getCon().prepareStatement("Select * from mobil");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                cbkdmobil.addItem(rs.getString("kode_mobil") + ":"
                        + rs.getString("merk") + ":"
                        + rs.getString("type") + ":"
                        + rs.getString("warna") + ":"
                        + rs.getString("harga"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ComboPembeli() {
        try {
            this.stat = k.getCon().prepareStatement("Select * from pembeli");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                cbktp.addItem(rs.getString("ktp") + ":"
                        + rs.getString("nama_pembeli")
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ComboPaket() {
        try {
            this.stat = k.getCon().prepareStatement("Select * from paket");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                cbkpaket.addItem(rs.getString("kode_paket") + ":"
                        + rs.getInt("uang_muka") + ":"
                        + rs.getInt("tenor") + ":"
                        + rs.getInt("bunga_cicilan"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void Tampiltabel() {
        model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("Nomor KTP Pembeli");
        model.addColumn("Kode Paket");
        model.addColumn("Kode Mobil");
        model.addColumn("Tanggal Beli");
        model.addColumn("Cicilan Perbulan");
        model.addColumn("Tenor");
        model.addColumn("Total Cicilan");
        model.addColumn("Path KTP");
        model.addColumn("Path Slip Gaji");
        tblkredit.setModel(model);
        try {
            this.stat = k.getCon().prepareStatement("Select * from kredit");
            this.rs = this.stat.executeQuery();
            while (rs.next()) {
                Object[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getString(9),
                    rs.getString(10)};
                model.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        textkode.setText("");
        txtbayar.setText("");
    }

    private String simpanFile(String asalPath, String prefix) {
        try {
            if (asalPath == null || asalPath.isEmpty()) {
                return null;
            }
            java.io.File asal = new java.io.File(asalPath);
            String namaFile = prefix + "_" + System.currentTimeMillis() + "_" + asal.getName();

            java.io.File folder = new java.io.File("uploads");
            if (!folder.exists()) {
                folder.mkdir();
            }
            java.io.File tujuan = new java.io.File(folder, namaFile);
            java.nio.file.Files.copy(asal.toPath(), tujuan.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            return tujuan.getPath();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan file" + e.getMessage());
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnkembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblslip = new javax.swing.JLabel();
        lblktp = new javax.swing.JLabel();
        textkode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkredit = new javax.swing.JTable();
        btntambah = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        txtbayar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbktp = new javax.swing.JComboBox<>();
        cbkdmobil = new javax.swing.JComboBox<>();
        cbkpaket = new javax.swing.JComboBox<>();
        datetanggal = new com.toedter.calendar.JDateChooser();
        btngaji = new javax.swing.JButton();
        btnktp = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnkembali.setText("Kembali");
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, -1, -1));

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaksi Beli Kredit");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 200, 23));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID_Beli");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 57, 77, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("KTP Pembeli");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 85, 77, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Mobil");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 111, 77, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Paket Kredit");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 137, 77, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tanggal Pembelian");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 172, -1, -1));

        lblslip.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblslip.setForeground(new java.awt.Color(255, 255, 255));
        lblslip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblslip.setText("Foto Slip Gaji");
        getContentPane().add(lblslip, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 290, 170));

        lblktp.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblktp.setForeground(new java.awt.Color(255, 255, 255));
        lblktp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblktp.setText("Foto KTP");
        getContentPane().add(lblktp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 320, 170));

        textkode.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textkodeActionPerformed(evt);
            }
        });
        getContentPane().add(textkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 50, 420, -1));

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
        tblkredit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkreditMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblkredit);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 640, 190));

        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        getContentPane().add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 190, -1));

        btnubah.setText("Ubah");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });
        getContentPane().add(btnubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 220, -1));

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, 170, -1));

        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });
        getContentPane().add(txtbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 210, 420, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bayar (RP)");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 213, -1, -1));

        getContentPane().add(cbktp, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 80, 420, -1));

        getContentPane().add(cbkdmobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 110, 420, -1));

        cbkpaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkpaketActionPerformed(evt);
            }
        });
        getContentPane().add(cbkpaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 140, 420, -1));
        getContentPane().add(datetanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 170, 420, -1));

        btngaji.setText("Pilih Slip Gaji");
        btngaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngajiActionPerformed(evt);
            }
        });
        getContentPane().add(btngaji, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 290, 20));

        btnktp.setText("Pilih KTP");
        btnktp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnktpActionPerformed(evt);
            }
        });
        getContentPane().add(btnktp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 320, 20));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/penjualan_mobil/neon-background2.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textkodeActionPerformed

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbayarActionPerformed

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        new MenuUtama(1).show();
        this.dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void cbkpaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkpaketActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkpaketActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        try {
            String ktpPath = simpanFile(pathKTP, "ktp");
            String slipPath = simpanFile(pathSlip, "slip");
            kredit c = new kredit();
            this.stat = k.getCon().prepareStatement("insert into kredit values(?,?,?,?,?,?,?,?,?,?)");
            stat.setString(1, c.kode_kredit);
            stat.setString(2, c.ktp);
            stat.setString(3, c.kode_paket);
            stat.setString(4, c.kode_mobil);
            stat.setString(5, c.tanggal_kredit);
            stat.setInt(6, c.bayar_kredit);
            stat.setInt(7, c.tenor);
            stat.setInt(8, c.totalcicil);
            stat.setString(9, ktpPath);
            stat.setString(10, slipPath);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cicilan Perbulannya= Rp." + c.bayar_kredit
                    + "\nTenornya Sebanyak" + c.tenor + "\nTotal Cicilannya Sebanyak= Rp." + c.totalcicil);
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        try {
            String ktpPath = (pathKTP.isEmpty()) ? null : simpanFile(pathKTP, "ktp");
            String slipPath = (pathSlip.isEmpty()) ? null : simpanFile(pathSlip, "slip");
            kredit c = new kredit();
            this.stat = k.getCon().prepareStatement("update kredit set ktp=?,"
                    + "kode_paket=?,kode_mobil=?, tanggal_kredit=?,bayar_kredit=?,tenor=?,totalcicil=?,gbrktp=?,gbrslip=? where kode_kredit=?");
            stat.setString(1, c.ktp);
            stat.setString(2, c.kode_paket);
            stat.setString(3, c.kode_mobil);
            stat.setString(4, c.tanggal_kredit);
            stat.setInt(5, c.bayar_kredit);
            stat.setInt(6, c.tenor);
            stat.setInt(7, c.totalcicil);
            stat.setString(8, ktpPath);
            stat.setString(9, slipPath);
            stat.setString(10, c.kode_kredit);
            stat.executeUpdate();
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnubahActionPerformed

    private void tblkreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkreditMouseClicked
        int row = tblkredit.getSelectedRow();
        textkode.setText(model.getValueAt(row, 0).toString());

        String ktp = model.getValueAt(row, 1).toString();
        for (int i = 0; i < cbktp.getItemCount(); i++) {
            if (cbktp.getItemAt(i).startsWith(ktp + ":")) {
                cbktp.setSelectedIndex(i);
                break;
            }
        }
        String kode_mobil = model.getValueAt(row, 2).toString();
        for (int i = 0; i < cbkdmobil.getItemCount(); i++) {
            if (cbkdmobil.getItemAt(i).startsWith(kode_mobil + ":")) {
                cbkdmobil.setSelectedIndex(i);
                break;
            }
        }
        String kode_paket = model.getValueAt(row, 3).toString();
        for (int i = 0; i < cbkpaket.getItemCount(); i++) {
            if (cbkpaket.getItemAt(i).startsWith(kode_paket + ":")) {
                cbkpaket.setSelectedIndex(i);
                break;
            }
        }

        try {
            String tanggal = model.getValueAt(row, 4).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            Date date = sdf.parse(tanggal);
            datetanggal.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String pathKTP = model.getValueAt(row, 8).toString();
        if (pathKTP != null && !pathKTP.isEmpty()) {
            ImageIcon iconKTP = new ImageIcon(new ImageIcon(pathKTP).getImage()
                    .getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
            lblktp.setIcon(iconKTP);
        } else {
            lblktp.setIcon(null);
        }
        String pathSlip = model.getValueAt(row, 9).toString();
        if (pathSlip != null && !pathSlip.isEmpty()) {
            ImageIcon iconSlip = new ImageIcon(new ImageIcon(pathSlip).getImage()
                    .getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
            lblslip.setIcon(iconSlip);
        } else {
            lblslip.setIcon(null);
        }
    }//GEN-LAST:event_tblkreditMouseClicked

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        try {
            kredit c = new kredit();
            this.stat = k.getCon().prepareStatement("delete from kredit where kode_kredit=?");
            stat.setString(1, textkode.getText());
            stat.executeUpdate();
            Tampiltabel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btngajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngajiActionPerformed
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
            pathSlip = file.getAbsolutePath();
            lblslip.setText(file.getName());
        }
    }//GEN-LAST:event_btngajiActionPerformed

    private void btnktpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnktpActionPerformed
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
            pathKTP = file.getAbsolutePath();
            lblktp.setText(file.getName());
        }
    }//GEN-LAST:event_btnktpActionPerformed

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
            java.util.logging.Logger.getLogger(MenuBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuBeliKredit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btngaji;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnktp;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btnubah;
    private javax.swing.JComboBox<String> cbkdmobil;
    private javax.swing.JComboBox<String> cbkpaket;
    private javax.swing.JComboBox<String> cbktp;
    private com.toedter.calendar.JDateChooser datetanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblktp;
    private javax.swing.JLabel lblslip;
    private javax.swing.JTable tblkredit;
    private javax.swing.JTextField textkode;
    private javax.swing.JTextField txtbayar;
    // End of variables declaration//GEN-END:variables
}
