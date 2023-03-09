/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import server.helperClass;
import server.mysql;

/**
 *
 * @author DELL
 */
public class menu extends javax.swing.JFrame {

    private final mysql db;
    private final Statement stm;
    private ResultSet res;
    private final HashMap<String, Integer> idKategori;
    public static int nn = 0;
    int dragxmouse;
    int dragymouse;
    /**
     * Creates new form addMenu
     *
     * @throws java.sql.SQLException
     */
    public menu() throws SQLException {
        this.setUndecorated(true);
        initComponents();
        this.setBackground(new Color(0,0,0,0));

        this.db = new mysql();
        this.idKategori = new HashMap<String, Integer>();
        this.stm = mysql.getConnection().createStatement();
        
        setTitle("TAMBAH MENU RESTORAN");
        setPreferredSize(new Dimension(5000, 800));
        setResizable(false);
        setLocationRelativeTo(null);
 
        this.fillMenuList();
        this.daftarKategori();

        headerTitle.setVisible(false);
        nameAlert.setVisible(false);
        priceAlert.setVisible(false);
        editBtn.setVisible(false);
        delBtn.setVisible(false);
        cancelBtn.setVisible(false);
        editID.setVisible(false);
        avPanel.setVisible(false);
        
        JTextField tf = inputHarga;
        
        tf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent ke) {
            String value = tf.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.' || ((int) ke.getKeyChar() == 8)) {
               tf.setEditable(true);
               priceAlert.setVisible(false);
               if(menu.nn > 0) {
                    priceAlert.setVisible(true);
                    if((int) ke.getKeyChar() != 8) {
                        priceAlert.setText(value +", seperti itu misalnya...");
                    }
                    menu.nn = 0;
               }
            } else {
               tf.setEditable(false);
               priceAlert.setVisible(true);
               priceAlert.setText("Anda hanya bisa memasukkan angka dan titik, atau menghapus");
               menu.nn = menu.nn + 1;
            }
         }
      });
        
        pack();
    }

    private void fillMenuList() {
        try {
            DefaultTableModel list = (DefaultTableModel) menuLists.getModel();

            String getMenuList = "SELECT m.*, c.categoryName FROM foods m JOIN foodCategory c ON c.categoryID = m.foodCategory ORDER BY m.name ASC";
            this.res = this.stm.executeQuery(getMenuList);

            if (this.res.next()) {
                do {
                    String foodID = this.res.getString("foodID");
                    String name = this.res.getString("name");
                    String cat = this.res.getString("categoryName");
                    Double price = this.res.getDouble("price");
                    boolean Status = this.res.getBoolean("isAvailable");

                    String status = (Status) ? "Tersedia" : "Tidak tersedia";
                    String harga = helperClass.formatRupiah(price);

                    list.addRow(new Object[]{foodID, name, cat, harga, status});
                } while (this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void daftarKategori() {
        try {
            String getKategori = "SELECT * FROM `foodCategory` ORDER BY `categoryName` ASC";
            this.res = stm.executeQuery(getKategori);
            if (this.res.next()) {
                do {
                    int id = this.res.getInt("categoryID");
                    String item = this.res.getString("categoryName");
                    daftarKategori.addItem(item);
                    //.addItem(item);

                    this.idKategori.put(item, id);
                } while (this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTable() {
        try {
            DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
            list.setRowCount(0);

            String getMenuList = "SELECT m.*, c.categoryName FROM foods m JOIN foodCategory c ON c.categoryID = m.foodCategory ORDER BY m.name ASC";
            this.res = this.stm.executeQuery(getMenuList);

            if (this.res.next()) {
                do {
                    String foodID = this.res.getString("foodID");
                    String name = this.res.getString("name");
                    String cat = this.res.getString("categoryName");
                    Double price = this.res.getDouble("price");
                    boolean Status = this.res.getBoolean("isAvailable");

                    String status = (Status) ? "Tersedia" : "Tidak tersedia";
                    String harga = helperClass.formatRupiah(price);

                    list.addRow(new Object[]{foodID, name, cat, harga, status});
                } while (this.res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
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

        userbtn = new javax.swing.JButton();
        menubtn = new javax.swing.JButton();
        pilihanbtn = new javax.swing.JButton();
        status = new javax.swing.JButton();
        menuPanel = new javax.swing.JScrollPane();
        menuLists = new javax.swing.JTable();
        addPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        daftarKategori = new javax.swing.JComboBox();
        inputNama = new javax.swing.JTextField();
        inputHarga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        avPanel = new javax.swing.JPanel();
        isAvailable = new javax.swing.JRadioButton();
        editBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        delBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        addBG = new javax.swing.JLabel();
        headerTitle = new javax.swing.JLabel();
        editID = new javax.swing.JLabel();
        nameAlert = new javax.swing.JLabel();
        priceAlert = new javax.swing.JLabel();
        onscreen = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tambah Menu");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/userbtn1.png"))); // NOI18N
        userbtn.setBorderPainted(false);
        userbtn.setContentAreaFilled(false);
        userbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userbtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                userbtnMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userbtnMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userbtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userbtnMouseEntered(evt);
            }
        });
        getContentPane().add(userbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 270, -1));

        menubtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/menubtn1.png"))); // NOI18N
        menubtn.setBorderPainted(false);
        menubtn.setContentAreaFilled(false);
        menubtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menubtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menubtnMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menubtnMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menubtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menubtnMouseEntered(evt);
            }
        });
        menubtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubtnActionPerformed(evt);
            }
        });
        getContentPane().add(menubtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 270, -1));

        pilihanbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/pilihanbtn1.png"))); // NOI18N
        pilihanbtn.setBorderPainted(false);
        pilihanbtn.setContentAreaFilled(false);
        pilihanbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pilihanbtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pilihanbtnMouseEntered(evt);
            }
        });
        getContentPane().add(pilihanbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 270, -1));

        status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/status1.png"))); // NOI18N
        status.setBorderPainted(false);
        status.setContentAreaFilled(false);
        status.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                statusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                statusMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statusMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                statusMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                statusMouseEntered(evt);
            }
        });
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 270, -1));

        menuLists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Kategori", "Harga", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuLists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuListsMouseClicked(evt);
            }
        });
        menuPanel.setViewportView(menuLists);

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 470, 390));

        addPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 14)); // NOI18N
        jLabel1.setText("TAMBAH MENU BARU");
        addPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 160, 20));

        jLabel2.setText("Nama:");
        addPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel7.setText(" Harga:");
        addPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        daftarKategori.setModel(new javax.swing.DefaultComboBoxModel());
        addPanel.add(daftarKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 203, -1));

        inputNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNamaActionPerformed(evt);
            }
        });
        addPanel.add(inputNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 200, -1));

        inputHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputHargaActionPerformed(evt);
            }
        });
        addPanel.add(inputHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 170, -1));

        jLabel8.setText("Rp.");
        addPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel6.setText("Kategori:");
        addPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 52, -1));

        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/tambahuser1.png"))); // NOI18N
        addBtn.setBorderPainted(false);
        addBtn.setContentAreaFilled(false);
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addBtnMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addBtnMouseEntered(evt);
            }
        });
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        addPanel.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        avPanel.setBackground(new java.awt.Color(246, 243, 255));
        avPanel.setToolTipText("");

        isAvailable.setBackground(new java.awt.Color(246, 243, 255));
        isAvailable.setText("Tersedia");

        javax.swing.GroupLayout avPanelLayout = new javax.swing.GroupLayout(avPanel);
        avPanel.setLayout(avPanelLayout);
        avPanelLayout.setHorizontalGroup(
            avPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(isAvailable)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        avPanelLayout.setVerticalGroup(
            avPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avPanelLayout.createSequentialGroup()
                .addComponent(isAvailable)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        addPanel.add(avPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 310, 40));

        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/edituser1.png"))); // NOI18N
        editBtn.setBorderPainted(false);
        editBtn.setContentAreaFilled(false);
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editBtnMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editBtnMouseEntered(evt);
            }
        });
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        addPanel.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, -1, -1));

        jLabel3.setText("Status:");
        addPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        delBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/deleteuser1.png"))); // NOI18N
        delBtn.setBorderPainted(false);
        delBtn.setContentAreaFilled(false);
        delBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        delBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                delBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                delBtnMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                delBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delBtnMouseEntered(evt);
            }
        });
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });
        addPanel.add(delBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/batal1.png"))); // NOI18N
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelBtnMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelBtnMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelBtnMouseEntered(evt);
            }
        });
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        addPanel.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        addBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/kotak.png"))); // NOI18N
        addPanel.add(addBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, -20, -1, 450));

        headerTitle.setText("{{msg}}");
        addPanel.add(headerTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        editID.setText("{{editID}}");
        addPanel.add(editID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        nameAlert.setText("{{namaMsg}}");
        addPanel.add(nameAlert, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, 20));

        priceAlert.setText("{{hargaMsg}}");
        addPanel.add(priceAlert, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, 20));

        getContentPane().add(addPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, 330, 400));

        onscreen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                onscreenMouseDragged(evt);
            }
        });
        onscreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onscreenMousePressed(evt);
            }
        });
        getContentPane().add(onscreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1170, 780));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin/images/daftarmenu.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        try {
            String nama = inputNama.getText();
            String harga = inputHarga.getText();
            Object kategori = daftarKategori.getSelectedItem();
            int id = this.idKategori.get((String) kategori);
            
            int vHarga = Integer.parseInt(harga);

            if (nama.equals("")) {
                inputNama.requestFocus();
                nameAlert.setText("Masukkan nama menu!");
                nameAlert.setVisible(true);
            } else {
                nameAlert.setText("");
                nameAlert.setVisible(false);
            }
            if (harga.equals("")) {
                priceAlert.setText("Masukkan harga!");
                priceAlert.setVisible(true);
            } else {
                priceAlert.setText("");
                priceAlert.setVisible(false);
            }
            
            if (vHarga <= 0) {
                priceAlert.setText("MASUKKAN HARGA DENGAN BENAR !!!");
                priceAlert.setVisible(true);
            } else {
                priceAlert.setText("");
                priceAlert.setVisible(false);
            }

            if (!nama.equals("") && !harga.equals("") && vHarga > 0) {
                String tambahMenu = "INSERT INTO `foods` VALUES(NULL, '" + nama + "', '" + harga + "', 1, " + id + ")";
                int tambah = this.stm.executeUpdate(tambahMenu);

                if (tambah > 0) {
                    inputNama.setText("");
                    inputHarga.setText("");

                    headerTitle.setText("Menu baru ditambahkan!");
                    this.updateTable();
                    daftarKategori.setSelectedIndex(0);
                } else {
                    headerTitle.setText("Terjadi kesalahan saat menambahkan menu. Silahkan ulangi kembali.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void menuListsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListsMouseClicked
        menu.nn = 0;
        try {
            DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
            int sel = menuLists.getSelectedRow();
            String id = list.getValueAt(sel, 0).toString();
            int ID = Integer.parseInt(id);

            addBtn.setVisible(false);
            editBtn.setVisible(true);
            delBtn.setVisible(true);
            cancelBtn.setVisible(true);
            avPanel.setVisible(true);

            String getData = "SELECT f.*, c.categoryName FROM foods f JOIN foodCategory c ON c.categoryID = f.foodCategory WHERE foodID = '" + ID + "'";
            this.res = this.stm.executeQuery(getData);
            if (this.res.next()) {
                String nama = this.res.getString("name");
                String Harga = this.res.getString("price");
                String harga = Harga.substring(0, Harga.length() - 3);
                String kategori = this.res.getString("categoryName");
                boolean isAv = this.res.getBoolean("isAvailable");

                inputNama.setText(nama);
                inputHarga.setText(harga);
                editID.setText(String.valueOf(ID));
                isAvailable.setSelected(isAv);

                daftarKategori.setSelectedItem(kategori);
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuListsMouseClicked

    private void inputHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputHargaActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
        int doHapus = JOptionPane.showConfirmDialog(addPanel, (Object) "Yakin ingin menghapus menu ini?", "Hapus menu", 0);

        if (doHapus == 0) {
            try {
                String ID = editID.getText();
                String Hapus = "DELETE FROM `foods` WHERE `foodID` = '" + ID + "'";
                int hapus = this.stm.executeUpdate(Hapus);

                if (hapus > 0) {
                    headerTitle.setText("Menu berhasil dihapus");
                    headerTitle.setVisible(true);

                    inputHarga.setText("");
                    inputNama.setText("");
                    daftarKategori.setSelectedIndex(0);

                    this.updateTable();
                    avPanel.setVisible(false);
                    addBtn.setVisible(true);
                    delBtn.setVisible(false);
                    editBtn.setVisible(false);
                    cancelBtn.setVisible(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_delBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        DefaultTableModel list = (DefaultTableModel) menuLists.getModel();
        String ID = editID.getText();

        String nama = inputNama.getText();
        String harga = inputHarga.getText();
        String kategori = daftarKategori.getSelectedItem().toString();
        int idKategori = this.idKategori.get(kategori);
        boolean isAv = isAvailable.isSelected();
        int avv = (isAv) ? 1 : 0;
        int vHarga = Integer.parseInt(harga);
        if (nama.equals("")) {
            inputNama.requestFocus();
            nameAlert.setText("Masukkan nama menu!");
            nameAlert.setVisible(true);
        } else {
            nameAlert.setText("");
            nameAlert.setVisible(false);
        }
        if (harga.equals("")) {
            inputHarga.requestFocus();
            priceAlert.setText("Masukkan harga!");
            priceAlert.setVisible(true);
        } else {
            priceAlert.setText("");
            priceAlert.setVisible(false);
        }
        if (vHarga <= 0) {
                priceAlert.setText("MASUKKAN HARGA DENGAN BENAR !!!");
                priceAlert.setVisible(true);
            } else {
                priceAlert.setText("");
                priceAlert.setVisible(false);
            }

        if (!nama.equals("") && !harga.equals("")) {
            try {
                String update = "UPDATE `foods` SET `name` = '" + nama + "', `price` = '" + harga + "', `foodCategory` = '" + idKategori + "', `isAvailable` = '" + avv + "' WHERE `foodID` = '" + ID + "'";
                int updateMenu = this.stm.executeUpdate(update);

                if (updateMenu > 0) {
                    headerTitle.setText("Menu diperbarui!");
                    headerTitle.setVisible(true);

                    this.updateTable();
                } else {
                    headerTitle.setText("Gagal memperbarui menu. Harap ulangi kembali");
                    headerTitle.setVisible(true);
                }

                inputNama.setText("");
                inputHarga.setText("");
                daftarKategori.setSelectedIndex(0);
                avPanel.setVisible(false);
                addBtn.setVisible(true);
                editBtn.setVisible(false);
                delBtn.setVisible(false);
                cancelBtn.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        addBtn.setVisible(true);
        editBtn.setVisible(false);
        delBtn.setVisible(false);
        cancelBtn.setVisible(false);
        avPanel.setVisible(false);
        
        inputHarga.setText("");
        inputNama.setText("");
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void inputNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNamaActionPerformed

    private void pilihanbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn2.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMouseEntered

    private void pilihanbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn1.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMouseExited

    private void pilihanbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn2.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMouseReleased

    private void pilihanbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/pilihanbtn1.png"));
        pilihanbtn.setIcon(b);
    }//GEN-LAST:event_pilihanbtnMousePressed

    private void userbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn2.png"));
        userbtn.setIcon(b);
    }//GEN-LAST:event_userbtnMouseEntered

    private void userbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn1.png"));
        userbtn.setIcon(b);
    }//GEN-LAST:event_userbtnMouseExited

    private void userbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtnMouseClicked
        try {
            new datausers().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_userbtnMouseClicked

    private void userbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn2.png"));
        userbtn.setIcon(b);
    }//GEN-LAST:event_userbtnMouseReleased

    private void userbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/userbtn1.png"));
        userbtn.setIcon(b);
    }//GEN-LAST:event_userbtnMousePressed

    private void menubtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubtnActionPerformed

    }//GEN-LAST:event_menubtnActionPerformed

    private void menubtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn2.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMouseEntered

    private void menubtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn1.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMouseExited

    private void menubtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseClicked
        try {
            new menu().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menubtnMouseClicked

    private void menubtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn2.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMouseReleased

    private void menubtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menubtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/menubtn1.png"));
        menubtn.setIcon(b);
    }//GEN-LAST:event_menubtnMousePressed

    private void pilihanbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pilihanbtnMouseClicked
        try {
            new pilihan().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pilihanbtnMouseClicked

    private void onscreenMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onscreenMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - dragxmouse,y-dragymouse);
        System.out.println(x+","+y);
    }//GEN-LAST:event_onscreenMouseDragged

    private void onscreenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onscreenMousePressed
        dragxmouse = evt.getX();
        dragymouse = evt.getY();
    }//GEN-LAST:event_onscreenMousePressed

    private void cancelBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/batal2.png"));
        cancelBtn.setIcon(b);
    }//GEN-LAST:event_cancelBtnMouseEntered

    private void cancelBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/batal1.png"));
        cancelBtn.setIcon(b);
    }//GEN-LAST:event_cancelBtnMouseExited

    private void cancelBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/batal1.png"));
        cancelBtn.setIcon(b);
    }//GEN-LAST:event_cancelBtnMousePressed

    private void cancelBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/batal2.png"));
        cancelBtn.setIcon(b);
    }//GEN-LAST:event_cancelBtnMouseReleased

    private void delBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/deleteuser2.png"));
        delBtn.setIcon(b);
    }//GEN-LAST:event_delBtnMouseEntered

    private void delBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/deleteuser1.png"));
        delBtn.setIcon(b);
    }//GEN-LAST:event_delBtnMouseExited

    private void delBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/deleteuser1.png"));
        delBtn.setIcon(b);
    }//GEN-LAST:event_delBtnMousePressed

    private void delBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delBtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/deleteuser2.png"));
        delBtn.setIcon(b);
    }//GEN-LAST:event_delBtnMouseReleased

    private void editBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/edituser2.png"));
        editBtn.setIcon(b);
    }//GEN-LAST:event_editBtnMouseEntered

    private void editBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/edituser1.png"));
        editBtn.setIcon(b);
    }//GEN-LAST:event_editBtnMouseExited

    private void editBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/edituser1.png"));
        editBtn.setIcon(b);
    }//GEN-LAST:event_editBtnMousePressed

    private void editBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/edituser2.png"));
        editBtn.setIcon(b);
    }//GEN-LAST:event_editBtnMouseReleased

    private void addBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/tambahuser2.png"));
        addBtn.setIcon(b);
    }//GEN-LAST:event_addBtnMouseEntered

    private void addBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/tambahuser1.png"));
        addBtn.setIcon(b);
    }//GEN-LAST:event_addBtnMouseExited

    private void addBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/tambahuser1.png"));
        addBtn.setIcon(b);
    }//GEN-LAST:event_addBtnMousePressed

    private void addBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/tambahuser2.png"));
        addBtn.setIcon(b);
    }//GEN-LAST:event_addBtnMouseReleased

    private void statusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMousePressed
        ImageIcon b = new ImageIcon(getClass().getResource("images/status1.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMousePressed

    private void statusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseReleased
        ImageIcon b = new ImageIcon(getClass().getResource("images/status2.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMouseReleased

    private void statusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseClicked
        try {
            new daftarPesanan().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_statusMouseClicked

    private void statusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseExited
        ImageIcon b = new ImageIcon(getClass().getResource("images/status1.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMouseExited

    private void statusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusMouseEntered
        ImageIcon b = new ImageIcon(getClass().getResource("images/status2.png"));
        status.setIcon(b);
    }//GEN-LAST:event_statusMouseEntered

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new menu().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBG;
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel addPanel;
    private javax.swing.JPanel avPanel;
    private javax.swing.JLabel bg;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox daftarKategori;
    private javax.swing.JButton delBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel editID;
    private javax.swing.JLabel headerTitle;
    private javax.swing.JTextField inputHarga;
    private javax.swing.JTextField inputNama;
    private javax.swing.JRadioButton isAvailable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTable menuLists;
    private javax.swing.JScrollPane menuPanel;
    private javax.swing.JButton menubtn;
    private javax.swing.JLabel nameAlert;
    private javax.swing.JLabel onscreen;
    private javax.swing.JButton pilihanbtn;
    private javax.swing.JLabel priceAlert;
    private javax.swing.JButton status;
    private javax.swing.JButton userbtn;
    // End of variables declaration//GEN-END:variables
}
