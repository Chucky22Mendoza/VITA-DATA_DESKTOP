/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Conexiones.Conexion;
import Procedimientos.ActualizarDoctor;
import Procedimientos.BorrarDoctor;
import Procedimientos.BorrarHospital;
import Procedimientos.BusquedaDoctor;
import Procedimientos.BusquedaHospital;
import Procedimientos.CambioDoctor;
import Procedimientos.GuardarDoctor;
import Procedimientos.GuardarHospital;
import Procedimientos.HistorialAtencion;
import Procedimientos.RegistrarCita;
import Procedimientos.mostrarDoc;
import com.sun.awt.AWTUtilities;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mendo
 */
public class Secretaria extends javax.swing.JFrame {

    /**
     * Creates new form Secretaria
     */
    private int _x, _y;
    public Secretaria() {
        Conexion _con = new Conexion();
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        Shape _form = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 27, 27);
        AWTUtilities.setWindowShape(this, _form);
        cbHospital.setModel(_con.getvalues1());
        cbPacienteHistorial.setModel(_con.getvalues2());
        cbDoctorDis.setModel(_con.getvalues3());
        cbPaciente.setModel(_con.getvalues2());
        cbDoctor.setModel(_con.getvalues4());
        fecha();        
        mostrarDoctores();
        mostrarInstituciones();
        mostrarHistorial();
        addItem();
        bloquear1();
        limpiar1();
        bloquear2();
        bloquearCita();
        limpiar2();
        bloquearHistorial();
        desbloquearHistorial();
        mostrarPacientes();
    }
    
    void fecha(){
        DateFormat _df = DateFormat.getDateInstance();
        Date _fechaActual = new Date();
        jCalendar.setDate(_fechaActual);        
        jCalendar.setEnabled(false);
    }    
    
    void mostrarDoctores(){
        try {
            DefaultTableModel _mod;
            Conexion _con = new Conexion();
            _mod = _con.MostrarDoctor();
            docTable.setModel(_mod);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void mostrarInstituciones(){
        try{    
            DefaultTableModel _mod;
            Conexion _con = new Conexion();
            _mod = _con.MostrarHospital();
            insTable.setModel(_mod);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void mostrarHistorial(){
        try {
            DefaultTableModel _mod;
            Conexion _con = new Conexion();
            _mod = _con.HistorialAtencion();
            hisTable.setModel(_mod);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void mostrarPacientes(){
        try {
            DefaultTableModel _mod;
            Conexion _con = new Conexion();
            _mod = _con.verPacientes();
            pacTable.setModel(_mod);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void addItem(){
        cbBuscar1.addItem("Cédula");
        cbBuscar1.addItem("Nombre");
        cbBuscar1.addItem("Hospital");        
        
        cbBuscar2.addItem("Nombre");
        cbBuscar2.addItem("Ciudad");
        cbBuscar2.addItem("Estado");        
        cbBuscar2.addItem("Código Postal");        
        
        cbBuscar3.addItem("Paciente");
        cbBuscar3.addItem("Doctor");
        cbBuscar3.addItem("Fecha");
        cbBuscar3.addItem("Mes y Año");
        cbBuscar3.addItem("Año");        
    }
    
    void bloquearHistorial(){
        cbDoctor.setEnabled(false);
        cbPaciente.setEnabled(false);
        calAño.setEnabled(false);
        calMes.setEnabled(false);
        calFecha.setEnabled(false);        
    }
    
    void bloquear1(){
        btnGuardar1.setEnabled(false);
        btnCancelar1.setEnabled(false);
        btnActualizar1.setEnabled(false);
        btnModificar1.setEnabled(true);        
        btnInaAct.setEnabled(true);
        txtCedula.setEnabled(false);
        txtNombre1.setEnabled(false);
        txtTelefono1.setEnabled(false);
        txtTelefono2.setEnabled(false);
        txtTelefono3.setEnabled(false);
        txtTelefono4.setEnabled(false);
        cbHospital.setEnabled(false);
        txtUsuario1.setEnabled(false);
        txtContraseña1.setEnabled(false);
        txtConfContraseña1.setEnabled(false);      
        btnNuevo1.setEnabled(true);
    }
    
    void bloquear2(){
        btnGuardar2.setEnabled(false);
        btnCancelar2.setEnabled(false);
        btnActualizar2.setEnabled(false);
        btnModificar2.setEnabled(true);        
        btnEliminar2.setEnabled(true);
        txtNombre2.setEnabled(false);
        txtCalle1.setEnabled(false);
        txtTelefonoH1.setEnabled(false);
        txtTelefonoH2.setEnabled(false);
        txtTelefonoH3.setEnabled(false);
        txtTelefonoH4.setEnabled(false);
        txtColonia1.setEnabled(false);
        txtCP1.setEnabled(false);
        txtCiudad1.setEnabled(false);
        txtEstado1.setEnabled(false);      
        btnNuevo2.setEnabled(true);
    }
    
    void bloquearCita(){
        btnNuevoCitas.setEnabled(true);
        btnModCitas.setEnabled(true);
        btnGuardarCita.setEnabled(false);
        btnCancelarCita.setEnabled(false);
        btnActualizarDoctor.setEnabled(false);
        cbPacienteHistorial.setEnabled(false);
        cbDoctorDis.setEnabled(false);      
        btnVerDoctor.setEnabled(false);
    }
    
    void desbloquearCita(){
        btnNuevoCitas.setEnabled(false);
        btnGuardarCita.setEnabled(true);
        btnCancelarCita.setEnabled(true);
        cbPacienteHistorial.setEnabled(true);
        btnModCitas.setEnabled(false);
        cbDoctorDis.setEnabled(true);                
        btnVerDoctor.setEnabled(true);
    }
    
    void desbloquearModCita(){
        btnActualizarDoctor.setEnabled(true);
        btnCancelarCita.setEnabled(true);
        cbPacienteHistorial.setEnabled(true);
        cbDoctorDis.setEnabled(true);            
        btnModCitas.setEnabled(false);
        btnVerDoctor.setEnabled(true);
        btnNuevoCitas.setEnabled(false);
    }
    
    void desbloquearNuevo1(){
        btnNuevo1.setEnabled(false);
        btnGuardar1.setEnabled(true);
        btnCancelar1.setEnabled(true);
        btnModificar1.setEnabled(false);
        btnInaAct.setEnabled(false);
        txtCedula.setEnabled(true);
        txtNombre1.setEnabled(true);
        txtTelefono1.setEnabled(true);
        txtTelefono2.setEnabled(true);
        txtTelefono3.setEnabled(true);
        txtTelefono4.setEnabled(true);
        cbHospital.setEnabled(true);
        txtUsuario1.setEnabled(true);
        txtContraseña1.setEnabled(true);
        txtConfContraseña1.setEnabled(true);        
    }
    
    void desbloquearNuevo2(){
        btnNuevo2.setEnabled(false);
        btnGuardar2.setEnabled(true);
        btnCancelar2.setEnabled(true);
        btnModificar2.setEnabled(false);
        btnEliminar2.setEnabled(false);
        txtNombre2.setEnabled(true);
        txtCalle1.setEnabled(true);
        txtTelefonoH1.setEnabled(true);
        txtTelefonoH2.setEnabled(true);
        txtTelefonoH3.setEnabled(true);
        txtTelefonoH4.setEnabled(true);
        txtColonia1.setEnabled(true);
        txtCiudad1.setEnabled(true);
        txtEstado1.setEnabled(true);
        txtCP1.setEnabled(true);        
    }
    
    void desbloquearModificar1(){
        btnNuevo1.setEnabled(false);
        btnInaAct.setEnabled(false);
        btnActualizar1.setEnabled(true);        
        btnCancelar1.setEnabled(true);
        txtCedula.setEnabled(false);
        txtNombre1.setEnabled(true);
        txtTelefono1.setEnabled(true);
        txtTelefono2.setEnabled(true);
        txtTelefono3.setEnabled(true);
        txtTelefono4.setEnabled(true);
        cbHospital.setEnabled(true);
        btnModificar1.setEnabled(false);
    }
    
    void desbloquearModificar2(){
        btnNuevo2.setEnabled(false);
        btnEliminar2.setEnabled(false);
        btnActualizar2.setEnabled(true);        
        btnCancelar2.setEnabled(true);
        txtNombre2.setEnabled(true);
        txtCalle1.setEnabled(true);
        txtTelefonoH1.setEnabled(true);
        txtTelefonoH2.setEnabled(true);
        txtTelefonoH3.setEnabled(true);
        txtTelefonoH4.setEnabled(true);
        txtColonia1.setEnabled(true);
        txtCP1.setEnabled(true);
        txtCiudad1.setEnabled(true);
        txtEstado1.setEnabled(true);
        btnModificar2.setEnabled(false);
    }
    
    void desbloquearHistorial(){
        
        String selec = cbBuscar3.getSelectedItem().toString();
        
        if(selec.equals("Paciente")){
            cbPaciente.setEnabled(true);
            cbDoctor.setEnabled(false);
            calMes.setEnabled(false);
            calAño.setEnabled(false);
            calFecha.setEnabled(false);
        }else{
            if(selec.equals("Doctor")){
                cbPaciente.setEnabled(false);            
                calMes.setEnabled(false);
                calAño.setEnabled(false);
                calFecha.setEnabled(false);
                cbDoctor.setEnabled(true);
            }else{
                if(selec.equals("Mes y Año")){
                    calMes.setEnabled(true);
                    calAño.setEnabled(true);
                    cbPaciente.setEnabled(false);
                    cbDoctor.setEnabled(false);                                        
                    calFecha.setEnabled(false);
                }else{
                    if(selec.equals("Año")){
                        calAño.setEnabled(true);
                        cbPaciente.setEnabled(false);
                        cbDoctor.setEnabled(false);
                        calMes.setEnabled(false);                        
                        calFecha.setEnabled(false);
                    }else{
                        if(selec.equals("Fecha")){
                            calFecha.setEnabled(true);
                            cbPaciente.setEnabled(false);
                            cbDoctor.setEnabled(false);
                            calMes.setEnabled(false);
                            calAño.setEnabled(false);            
                        }
                    }
                }         
            }
        }
        
    }
    
    void limpiar1(){
        txtCedula.setText("");
        txtNombre1.setText("");
        txtTelefono1.setText("");
        txtTelefono2.setText("");
        txtTelefono3.setText("");
        txtTelefono4.setText("");
        cbHospital.setSelectedIndex(0);
        txtUsuario1.setText("");
        txtContraseña1.setText("");
        txtConfContraseña1.setText("");
    }
    
    void limpiar2(){
        txtNombre2.setText("");
        txtCalle1.setText("");
        txtTelefonoH1.setText("");
        txtTelefonoH2.setText("");
        txtTelefonoH3.setText("");
        txtTelefonoH4.setText("");        
        txtCP1.setText("");
        txtColonia1.setText("");
        txtCiudad1.setText("");
        txtEstado1.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabbedPaneHeader2 = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        jPanel23 = new javax.swing.JPanel();
        tabbedSelector21 = new org.edisoncor.gui.tabbedPane.TabbedSelector2();
        jPanel21 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCedula = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtNombre1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtUsuario1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        cbHospital = new javax.swing.JComboBox<>();
        txtConfContraseña1 = new javax.swing.JPasswordField();
        txtContraseña1 = new javax.swing.JPasswordField();
        txtTelefono1 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtTelefono3 = new javax.swing.JTextField();
        txtTelefono4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnNuevo1 = new org.edisoncor.gui.button.ButtonAction();
        btnGuardar1 = new org.edisoncor.gui.button.ButtonAction();
        btnCancelar1 = new org.edisoncor.gui.button.ButtonAction();
        btnActualizar1 = new org.edisoncor.gui.button.ButtonAction();
        btnModificar1 = new org.edisoncor.gui.button.ButtonAction();
        btnInaAct = new org.edisoncor.gui.button.ButtonAction();
        txtBuscar1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        btnMostrar1 = new org.edisoncor.gui.button.ButtonAction();
        btnBuscar1 = new org.edisoncor.gui.button.ButtonAction();
        jScrollPane1 = new javax.swing.JScrollPane();
        docTable = new javax.swing.JTable();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        clockDigital2 = new org.edisoncor.gui.varios.ClockDigital();
        cbBuscar1 = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel47 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        txtTelefonoH2 = new javax.swing.JTextField();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        txtTelefonoH3 = new javax.swing.JTextField();
        txtTelefonoH4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnNuevo2 = new org.edisoncor.gui.button.ButtonAction();
        btnGuardar2 = new org.edisoncor.gui.button.ButtonAction();
        btnCancelar2 = new org.edisoncor.gui.button.ButtonAction();
        btnActualizar2 = new org.edisoncor.gui.button.ButtonAction();
        jLabel15 = new javax.swing.JLabel();
        btnModificar2 = new org.edisoncor.gui.button.ButtonAction();
        jLabel16 = new javax.swing.JLabel();
        btnEliminar2 = new org.edisoncor.gui.button.ButtonAction();
        jLabel17 = new javax.swing.JLabel();
        txtBuscar2 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        btnMostrar2 = new org.edisoncor.gui.button.ButtonAction();
        btnBuscar2 = new org.edisoncor.gui.button.ButtonAction();
        jScrollPane2 = new javax.swing.JScrollPane();
        insTable = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTelefonoH1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtNombre2 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtCalle1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtColonia1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtCP1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtCiudad1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtEstado1 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        clockDigital3 = new org.edisoncor.gui.varios.ClockDigital();
        cbBuscar2 = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel46 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        hisTable = new javax.swing.JTable();
        calMes = new com.toedter.calendar.JMonthChooser();
        calAño = new com.toedter.calendar.JYearChooser();
        calFecha = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cbPaciente = new javax.swing.JComboBox<>();
        cbDoctor = new javax.swing.JComboBox<>();
        cbBuscar3 = new javax.swing.JComboBox<>();
        btnBuscar3 = new org.edisoncor.gui.button.ButtonAction();
        btnMostrarHis = new org.edisoncor.gui.button.ButtonAction();
        clockDigital5 = new org.edisoncor.gui.varios.ClockDigital();
        jLabel28 = new javax.swing.JLabel();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel45 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jCalendar = new com.toedter.calendar.JDateChooser();
        clockDigital4 = new org.edisoncor.gui.varios.ClockDigital();
        cbPacienteHistorial = new javax.swing.JComboBox<>();
        cbDoctorDis = new javax.swing.JComboBox<>();
        desktop1 = new javax.swing.JDesktopPane();
        btnVerDoctor = new org.edisoncor.gui.button.ButtonAction();
        btnNuevoCitas = new org.edisoncor.gui.button.ButtonAction();
        btnGuardarCita = new org.edisoncor.gui.button.ButtonAction();
        btnActualizarDoctor = new org.edisoncor.gui.button.ButtonAction();
        btnCancelarCita = new org.edisoncor.gui.button.ButtonAction();
        btnModCitas = new org.edisoncor.gui.button.ButtonAction();
        jLabel29 = new javax.swing.JLabel();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pacTable = new javax.swing.JTable();
        buttonAction1 = new org.edisoncor.gui.button.ButtonAction();
        clockDigital6 = new org.edisoncor.gui.varios.ClockDigital();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        buttonIcon1 = new org.edisoncor.gui.button.ButtonIcon();
        buttonIcon2 = new org.edisoncor.gui.button.ButtonIcon();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(66, 212, 194));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        tabbedPaneHeader2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabbedPaneHeader2.setColorDeBorde(new java.awt.Color(102, 153, 255));
        tabbedPaneHeader2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jPanel23.setBackground(new java.awt.Color(204, 204, 204));

        tabbedSelector21.setBackground(new java.awt.Color(0, 255, 224));
        tabbedSelector21.setForeground(new java.awt.Color(102, 102, 102));
        tabbedSelector21.setColorBackGround(new java.awt.Color(0, 255, 224));
        tabbedSelector21.setColorDeBorde(new java.awt.Color(102, 102, 102));
        tabbedSelector21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tabbedSelector21.setSelectionColor(new java.awt.Color(0, 0, 0));

        jPanel21.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("CONTROL DE DOCTORES");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Cédula:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Teléfono:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Hospital:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Nombre de usuario:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Contraseña:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Confirmar contraseña:");

        txtCedula.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCedula.setDescripcion("#######");
        txtCedula.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCedula.setNextFocusableComponent(txtNombre1);
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtNombre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtNombre1.setDescripcion("Nombre completo");
        txtNombre1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNombre1.setNextFocusableComponent(txtTelefono1);
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });
        txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre1KeyTyped(evt);
            }
        });

        txtUsuario1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtUsuario1.setDescripcion("Nombre de usuario");
        txtUsuario1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtUsuario1.setNextFocusableComponent(txtContraseña1);
        txtUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuario1ActionPerformed(evt);
            }
        });
        txtUsuario1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuario1KeyTyped(evt);
            }
        });

        cbHospital.setBackground(new java.awt.Color(153, 153, 153));
        cbHospital.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbHospital.setForeground(new java.awt.Color(255, 255, 255));
        cbHospital.setNextFocusableComponent(txtUsuario1);

        txtConfContraseña1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtConfContraseña1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtConfContraseña1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConfContraseña1KeyTyped(evt);
            }
        });

        txtContraseña1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtContraseña1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContraseña1.setNextFocusableComponent(txtConfContraseña1);
        txtContraseña1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseña1KeyTyped(evt);
            }
        });

        txtTelefono1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefono1.setNextFocusableComponent(txtTelefono2);
        txtTelefono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefono1ActionPerformed(evt);
            }
        });
        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyTyped(evt);
            }
        });

        txtTelefono2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefono2.setNextFocusableComponent(txtTelefono3);
        txtTelefono2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono2KeyTyped(evt);
            }
        });

        txtTelefono3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefono3.setNextFocusableComponent(txtTelefono4);
        txtTelefono3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono3KeyTyped(evt);
            }
        });

        txtTelefono4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefono4.setNextFocusableComponent(cbHospital);
        txtTelefono4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono4KeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("-");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("-");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setText("-");

        btnNuevo1.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo1.setText("NUEVO");
        btnNuevo1.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        btnGuardar1.setText("GUARDAR");
        btnGuardar1.setColorDeSombra(new java.awt.Color(0, 204, 0));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        btnCancelar1.setText("CANCELAR");
        btnCancelar1.setColorDeSombra(new java.awt.Color(255, 0, 0));
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        btnActualizar1.setText("ACTUALIZAR");
        btnActualizar1.setColorDeSombra(new java.awt.Color(0, 204, 0));
        btnActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar1ActionPerformed(evt);
            }
        });

        btnModificar1.setText("MODIFICAR");
        btnModificar1.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar1ActionPerformed(evt);
            }
        });

        btnInaAct.setText("Inactivo/Activo");
        btnInaAct.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnInaAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInaActActionPerformed(evt);
            }
        });

        txtBuscar1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtBuscar1.setDescripcion("Búsqueda");
        txtBuscar1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        btnMostrar1.setText("MOSTRAR");
        btnMostrar1.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnMostrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrar1ActionPerformed(evt);
            }
        });

        btnBuscar1.setText("BUSCAR");
        btnBuscar1.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        docTable.setBackground(new java.awt.Color(255, 153, 51));
        docTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        docTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Teléfono", "Hospital", "Usuario"
            }
        ));
        docTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        docTable.setGridColor(new java.awt.Color(0, 0, 0));
        docTable.setRowHeight(25);
        docTable.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(docTable);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vitadata/corazon2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        clockDigital2.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital2.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        cbBuscar1.setBackground(new java.awt.Color(153, 153, 153));
        cbBuscar1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        cbBuscar1.setNextFocusableComponent(txtUsuario1);

        jLabel47.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 102, 102));
        jLabel47.setText("Tipo de búsqueda:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbHospital, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtConfContraseña1)
                                    .addComponent(txtContraseña1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(txtTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelefono4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnActualizar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnInaAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(1, 1, 1))))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel21Layout.createSequentialGroup()
                                            .addGap(37, 37, 37)
                                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel6)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel7)))
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(clockDigital2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                        .addComponent(btnMostrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel21Layout.createSequentialGroup()
                                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cbBuscar1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtBuscar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(192, 192, 192)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 632, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(603, 603, 603))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator9)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                                .addGap(18, 52, Short.MAX_VALUE)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel10)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(cbHospital, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtContraseña1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 51, Short.MAX_VALUE)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtConfContraseña1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(33, 33, 33))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnInaAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(clockDigital2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMostrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tabbedSelector21.addTab("Registros", jPanel21);

        jPanel22.setBackground(new java.awt.Color(204, 204, 204));

        txtTelefonoH2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefonoH2.setNextFocusableComponent(txtTelefono3);
        txtTelefonoH2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoH2KeyTyped(evt);
            }
        });

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vitadata/corazon2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        txtTelefonoH3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefonoH3.setNextFocusableComponent(txtTelefono4);
        txtTelefonoH3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoH3KeyTyped(evt);
            }
        });

        txtTelefonoH4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefonoH4.setNextFocusableComponent(cbHospital);
        txtTelefonoH4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoH4KeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("-");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setText("-");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setText("-");

        btnNuevo2.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo2.setText("NUEVO");
        btnNuevo2.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
            }
        });

        btnGuardar2.setText("GUARDAR");
        btnGuardar2.setColorDeSombra(new java.awt.Color(0, 204, 0));
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });

        btnCancelar2.setText("CANCELAR");
        btnCancelar2.setColorDeSombra(new java.awt.Color(255, 0, 0));
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar2ActionPerformed(evt);
            }
        });

        btnActualizar2.setText("ACTUALIZAR");
        btnActualizar2.setColorDeSombra(new java.awt.Color(0, 204, 0));
        btnActualizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("Teléfono:");

        btnModificar2.setText("MODIFICAR");
        btnModificar2.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("Calle:");

        btnEliminar2.setText("ELIMINAR");
        btnEliminar2.setColorDeSombra(new java.awt.Color(255, 0, 0));
        btnEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar2ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("Ciudad:");

        txtBuscar2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar2.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtBuscar2.setDescripcion("Búsqueda");
        txtBuscar2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        btnMostrar2.setText("MOSTRAR");
        btnMostrar2.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnMostrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrar2ActionPerformed(evt);
            }
        });

        btnBuscar2.setText("BUSCAR");
        btnBuscar2.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });

        insTable.setBackground(new java.awt.Color(255, 153, 51));
        insTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        insTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Calle", "Colonia", "Código Postal", "Ciudad", "Estado", "Teléfono"
            }
        ));
        insTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        insTable.setGridColor(new java.awt.Color(0, 0, 0));
        insTable.setRowHeight(25);
        insTable.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane2.setViewportView(insTable);

        jLabel20.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("REGISTRO DE INSTITUCIONES");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel22.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 102));
        jLabel22.setText("Nombre:");

        txtTelefonoH1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelefonoH1.setNextFocusableComponent(txtTelefono2);
        txtTelefonoH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoH1ActionPerformed(evt);
            }
        });
        txtTelefonoH1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoH1KeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("Estado:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("Código Postal:");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 102));
        jLabel25.setText("Colonia:");

        txtNombre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre2.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtNombre2.setDescripcion("Nombre");
        txtNombre2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNombre2.setNextFocusableComponent(txtTelefono1);
        txtNombre2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre2ActionPerformed(evt);
            }
        });
        txtNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre2KeyTyped(evt);
            }
        });

        txtCalle1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCalle1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtCalle1.setDescripcion("Calle y Número");
        txtCalle1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCalle1.setNextFocusableComponent(txtTelefono1);
        txtCalle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalle1ActionPerformed(evt);
            }
        });
        txtCalle1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCalle1KeyTyped(evt);
            }
        });

        txtColonia1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColonia1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtColonia1.setDescripcion("Colonia");
        txtColonia1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtColonia1.setNextFocusableComponent(txtTelefono1);
        txtColonia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColonia1ActionPerformed(evt);
            }
        });
        txtColonia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColonia1KeyTyped(evt);
            }
        });

        txtCP1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCP1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtCP1.setDescripcion("Código Postal");
        txtCP1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCP1.setNextFocusableComponent(txtTelefono1);
        txtCP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCP1ActionPerformed(evt);
            }
        });
        txtCP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCP1KeyTyped(evt);
            }
        });

        txtCiudad1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCiudad1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtCiudad1.setDescripcion("Ciudad");
        txtCiudad1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtCiudad1.setNextFocusableComponent(txtTelefono1);
        txtCiudad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudad1ActionPerformed(evt);
            }
        });
        txtCiudad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCiudad1KeyTyped(evt);
            }
        });

        txtEstado1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstado1.setColorDeTextoBackground(new java.awt.Color(153, 153, 153));
        txtEstado1.setDescripcion("Estado");
        txtEstado1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtEstado1.setNextFocusableComponent(txtTelefono1);
        txtEstado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstado1ActionPerformed(evt);
            }
        });
        txtEstado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstado1KeyTyped(evt);
            }
        });

        clockDigital3.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital3.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital3.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        cbBuscar2.setBackground(new java.awt.Color(153, 153, 153));
        cbBuscar2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbBuscar2.setForeground(new java.awt.Color(255, 255, 255));
        cbBuscar2.setNextFocusableComponent(txtUsuario1);

        jLabel46.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 102, 102));
        jLabel46.setText("Tipo de búsqueda:");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel16)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25)
                            .addComponent(jLabel17)
                            .addComponent(jLabel23)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(txtTelefonoH1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefonoH2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(txtTelefonoH3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefonoH4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCalle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtColonia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCiudad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstado1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(clockDigital3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnMostrar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbBuscar2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBuscar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel46)
                        .addGap(193, 193, 193)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(573, 573, 573))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator8)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(19, 19, 19)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtCalle1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(txtColonia1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtCP1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(txtCiudad1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefonoH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTelefonoH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefonoH3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefonoH4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel13)))))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnNuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clockDigital3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMostrar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(84, Short.MAX_VALUE))))
        );

        tabbedSelector21.addTab("Instituciones", jPanel22);

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        hisTable.setBackground(new java.awt.Color(255, 153, 51));
        hisTable.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        hisTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Paciente", "Doctor", "Fecha de Atención"
            }
        ));
        hisTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hisTable.setGridColor(new java.awt.Color(0, 0, 0));
        hisTable.setRowHeight(35);
        hisTable.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane3.setViewportView(hisTable);

        calMes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        calFecha.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 102));
        jLabel26.setText("Paciente:");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 102));
        jLabel27.setText("Doctores:");

        cbPaciente.setBackground(new java.awt.Color(153, 153, 153));
        cbPaciente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbPaciente.setForeground(new java.awt.Color(255, 255, 255));
        cbPaciente.setNextFocusableComponent(txtUsuario1);

        cbDoctor.setBackground(new java.awt.Color(153, 153, 153));
        cbDoctor.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbDoctor.setForeground(new java.awt.Color(255, 255, 255));
        cbDoctor.setNextFocusableComponent(txtUsuario1);

        cbBuscar3.setBackground(new java.awt.Color(153, 153, 153));
        cbBuscar3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbBuscar3.setForeground(new java.awt.Color(255, 255, 255));
        cbBuscar3.setNextFocusableComponent(txtUsuario1);
        cbBuscar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbBuscar3MouseClicked(evt);
            }
        });
        cbBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscar3ActionPerformed(evt);
            }
        });

        btnBuscar3.setText("BUSCAR");
        btnBuscar3.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar3ActionPerformed(evt);
            }
        });

        btnMostrarHis.setText("MOSTRAR");
        btnMostrarHis.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnMostrarHis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarHisActionPerformed(evt);
            }
        });

        clockDigital5.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital5.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital5.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("HISTORIAL DE ATENCIÓN DE DOCTORES");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        panelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vitadata/corazon2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        jLabel45.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 102, 102));
        jLabel45.setText("Tipo de búsqueda:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(calMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnMostrarHis, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addComponent(calAño, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(calFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel27)
                                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cbPaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cbBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(166, 166, 166)
                                                .addComponent(clockDigital5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel45)
                                        .addGap(112, 112, 112))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(510, 510, 510)
                                .addComponent(jLabel28)
                                .addGap(167, 167, 167)
                                .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 88, Short.MAX_VALUE))
                    .addComponent(jSeparator7))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clockDigital5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(cbBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(cbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(cbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(calAño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMostrarHis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        tabbedSelector21.addTab("Historial", jPanel8);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedSelector21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(tabbedSelector21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneHeader2.addTab("Doctores", jPanel23);

        jPanel24.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setText("Paciente:");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setText("Doctores disponibles:");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setText("Fecha de Atención:");

        jCalendar.setBorder(new org.edisoncor.gui.util.DropShadowBorder());
        jCalendar.setForeground(new java.awt.Color(0, 102, 102));
        jCalendar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        clockDigital4.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital4.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital4.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        cbPacienteHistorial.setBackground(new java.awt.Color(153, 153, 153));
        cbPacienteHistorial.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbPacienteHistorial.setForeground(new java.awt.Color(255, 255, 255));
        cbPacienteHistorial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item1", "item2" }));
        cbPacienteHistorial.setNextFocusableComponent(txtUsuario1);

        cbDoctorDis.setBackground(new java.awt.Color(153, 153, 153));
        cbDoctorDis.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbDoctorDis.setForeground(new java.awt.Color(255, 255, 255));
        cbDoctorDis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item1", "item2" }));
        cbDoctorDis.setNextFocusableComponent(txtUsuario1);

        desktop1.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout desktop1Layout = new javax.swing.GroupLayout(desktop1);
        desktop1.setLayout(desktop1Layout);
        desktop1Layout.setHorizontalGroup(
            desktop1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1181, Short.MAX_VALUE)
        );
        desktop1Layout.setVerticalGroup(
            desktop1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );

        btnVerDoctor.setText("VER DOCTOR");
        btnVerDoctor.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnVerDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDoctorActionPerformed(evt);
            }
        });

        btnNuevoCitas.setText("NUEVO");
        btnNuevoCitas.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnNuevoCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCitasActionPerformed(evt);
            }
        });

        btnGuardarCita.setText("GUARDAR CITA");
        btnGuardarCita.setColorDeSombra(new java.awt.Color(0, 204, 0));
        btnGuardarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCitaActionPerformed(evt);
            }
        });

        btnActualizarDoctor.setText("ACTUALIZAR DOCTOR");
        btnActualizarDoctor.setColorDeSombra(new java.awt.Color(0, 204, 0));
        btnActualizarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarDoctorActionPerformed(evt);
            }
        });

        btnCancelarCita.setText("CANCELAR");
        btnCancelarCita.setColorDeSombra(new java.awt.Color(255, 0, 0));
        btnCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCitaActionPerformed(evt);
            }
        });

        btnModCitas.setText("MODIFICAR DOCTOR");
        btnModCitas.setColorDeSombra(new java.awt.Color(51, 153, 255));
        btnModCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModCitasActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("REGISTRO DE CITAS Y ACTUALIZACIÓN DE CAMBIO DE DOCTOR DE CABECERA");
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vitadata/corazon2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage5Layout = new javax.swing.GroupLayout(panelImage5);
        panelImage5.setLayout(panelImage5Layout);
        panelImage5Layout.setHorizontalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );
        panelImage5Layout.setVerticalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(desktop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbPacienteHistorial, 0, 370, Short.MAX_VALUE)
                    .addComponent(cbDoctorDis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCalendar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(btnCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(btnVerDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnModCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnActualizarDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clockDigital4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(137, 137, 137)))
                .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(377, 377, 377))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator10)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(3, 3, 3)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(cbPacienteHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNuevoCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel24Layout.createSequentialGroup()
                                        .addComponent(btnActualizarDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnModCitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                                        .addComponent(clockDigital4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19))))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(cbDoctorDis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(desktop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabbedPaneHeader2.addTab("Citas", jPanel24);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        pacTable.setBackground(new java.awt.Color(255, 153, 51));
        pacTable.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        pacTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Paciente", "Edad", "Fecha de Nacimiento", "Doctor", "Receptor", "Numero de Pulsera"
            }
        ));
        pacTable.setGridColor(new java.awt.Color(0, 0, 0));
        pacTable.setRowHeight(30);
        pacTable.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane4.setViewportView(pacTable);

        buttonAction1.setText("ACTUALIZAR");
        buttonAction1.setColorDeSombra(new java.awt.Color(51, 153, 255));
        buttonAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction1ActionPerformed(evt);
            }
        });

        clockDigital6.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital6.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital6.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        panelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vitadata/corazon2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage4Layout = new javax.swing.GroupLayout(panelImage4);
        panelImage4.setLayout(panelImage4Layout);
        panelImage4Layout.setHorizontalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panelImage4Layout.setVerticalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        jLabel30.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("CONSULTA DE TODOS LOS PACIENTES");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator11)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 191, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(clockDigital6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(217, 217, 217)
                                .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(195, 195, 195)
                                .addComponent(panelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(200, 200, 200))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(205, 205, 205))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(607, 607, 607))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clockDigital6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tabbedPaneHeader2.addTab("Pacientes", jPanel3);

        buttonIcon1.setBackground(new java.awt.Color(255, 0, 0));
        buttonIcon1.setText("buttonIcon1");
        buttonIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIcon1ActionPerformed(evt);
            }
        });

        buttonIcon2.setBackground(new java.awt.Color(255, 255, 51));
        buttonIcon2.setText("buttonIcon1");
        buttonIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIcon2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(buttonIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tabbedPaneHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIcon1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_buttonIcon1ActionPerformed

    private void buttonIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIcon2ActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_buttonIcon2ActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        _x=evt.getX();
        _y=evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        Point _p = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(_p.x-_x,_p.y-_y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        char c = evt.getKeyChar();
        int limite = 7;
        int _ced = txtCedula.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_ced == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();
            
            if ((_ced == limite) || (c == KeyEvent.VK_TAB)){
                txtNombre1.requestFocus();
            }else{
                getToolkit().beep();
            }
            
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        char c = evt.getKeyChar();
        
        if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z' ) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE) && !String.valueOf(c).matches("[á-ú']") && (c != KeyEvent.VK_TAB)) {           
                evt.consume();                
            if(c == KeyEvent.VK_TAB){                          
                txtTelefono1.requestFocus();
            }else {
                getToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtNombre1KeyTyped

    private void txtTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel1 = txtTelefono1.getText().length();
        
        if( (c < '0' || c > '9')  && (c != KeyEvent.VK_BACK_SPACE) || (_tel1 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();                                    
            
            if( _tel1 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelefono2.requestFocus();
                txtTelefono2.setText(String.valueOf(c));
            }else {
                getToolkit().beep();
            }
            
        }
    }//GEN-LAST:event_txtTelefono1KeyTyped

    private void txtTelefono2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono2KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel2 = txtTelefono2.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel2 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();                                   
            
            if( _tel2 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelefono3.requestFocus();
                txtTelefono3.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }                        
            
        }else{
            if ((_tel2 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelefono1.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTelefono2KeyTyped

    private void txtTelefono3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono3KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel3 = txtTelefono3.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel3 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();            
            
            if( _tel3 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelefono4.requestFocus();
                txtTelefono4.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel3 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelefono2.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTelefono3KeyTyped

    private void txtTelefono4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono4KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel4 = txtTelefono4.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel4 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();
            
            if (_tel4 == limite || (c == KeyEvent.VK_TAB)){
                cbHospital.requestFocus();
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel4 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelefono3.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTelefono4KeyTyped

    private void txtUsuario1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuario1KeyTyped
        char c = evt.getKeyChar();
        
        if((c < 'a' || c > 'z') && (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) && !String.valueOf(c).matches("[._@á-ú*-]") && (c != KeyEvent.VK_TAB)) {
            evt.consume();            
            
            if(c == KeyEvent.VK_TAB){
                txtContraseña1.requestFocus();
            }else{
                getToolkit().beep();                   
            }
            
        }
    }//GEN-LAST:event_txtUsuario1KeyTyped

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void txtUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuario1ActionPerformed

    private void txtContraseña1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseña1KeyTyped
        char c = evt.getKeyChar();                
        if(c == KeyEvent.VK_TAB){
                txtConfContraseña1.requestFocus();                
            }
        
    }//GEN-LAST:event_txtContraseña1KeyTyped

    private void txtTelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono1ActionPerformed

    private void txtConfContraseña1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfContraseña1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfContraseña1KeyTyped

    private void txtTelefonoH2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoH2KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel2 = txtTelefonoH2.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel2 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();            
            
            if( _tel2 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelefonoH3.requestFocus();
                txtTelefonoH3.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel2 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelefonoH1.requestFocus();               
            }
        } 
    }//GEN-LAST:event_txtTelefonoH2KeyTyped

    private void txtTelefonoH3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoH3KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel3 = txtTelefonoH3.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel3 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();            
            
            if( _tel3 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelefonoH4.requestFocus();
                txtTelefonoH4.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel3 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelefonoH2.requestFocus();               
            }
        }
    }//GEN-LAST:event_txtTelefonoH3KeyTyped

    private void txtTelefonoH4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoH4KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel4 = txtTelefonoH4.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel4 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();                        
            getToolkit().beep();                        
        }else{
            if ((_tel4 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelefonoH3.requestFocus();               
            }
        }
    }//GEN-LAST:event_txtTelefonoH4KeyTyped

    private void txtTelefonoH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoH1ActionPerformed
        // TODO add your handling code here:}
    }//GEN-LAST:event_txtTelefonoH1ActionPerformed

    private void txtTelefonoH1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoH1KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel1 = txtTelefonoH1.getText().length();
        
        if( (c < '0' || c > '9')  && (c != KeyEvent.VK_BACK_SPACE) || (_tel1 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();            
            
            if( _tel1 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelefonoH2.requestFocus();
                txtTelefonoH2.setText(String.valueOf(c));
            }else {
                getToolkit().beep();
            }
            
        }
    }//GEN-LAST:event_txtTelefonoH1KeyTyped

    private void txtNombre2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre2ActionPerformed

    private void txtNombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre2KeyTyped
           char c = evt.getKeyChar();
        
           if(c == KeyEvent.VK_TAB){
                txtCalle1.requestFocus();
           }
    }//GEN-LAST:event_txtNombre2KeyTyped

    private void txtCalle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalle1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalle1ActionPerformed

    private void txtCalle1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCalle1KeyTyped
        char c = evt.getKeyChar();
        
           if(c == KeyEvent.VK_TAB){
                txtColonia1.requestFocus();
           }
    }//GEN-LAST:event_txtCalle1KeyTyped

    private void txtColonia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColonia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColonia1ActionPerformed

    private void txtColonia1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColonia1KeyTyped
        char c = evt.getKeyChar();
        
           if(c == KeyEvent.VK_TAB){
                txtCP1.requestFocus();
           }
    }//GEN-LAST:event_txtColonia1KeyTyped

    private void txtCP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCP1ActionPerformed

    private void txtCP1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCP1KeyTyped
        char c = evt.getKeyChar();
        
        if( (c < '0' || c > '9')  && (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_TAB)){
            evt.consume();            
            
            if(c == KeyEvent.VK_TAB){                                          
                txtCiudad1.requestFocus();
            }else {
                getToolkit().beep();
            }
            
        }
                   
    }//GEN-LAST:event_txtCP1KeyTyped

    private void txtCiudad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudad1ActionPerformed

    private void txtCiudad1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiudad1KeyTyped
        char c = evt.getKeyChar();
        
           if(c == KeyEvent.VK_TAB){
                txtEstado1.requestFocus();
           }
    }//GEN-LAST:event_txtCiudad1KeyTyped

    private void txtEstado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstado1ActionPerformed

    private void txtEstado1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstado1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstado1KeyTyped

    private void btnVerDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDoctorActionPerformed
        DatosDoctor DD = new DatosDoctor();        
        mostrarDoc _dts = new mostrarDoc();
        Conexion _con = new Conexion();
        DatosHospital DH = new DatosHospital();
        
        String selec = cbDoctorDis.getSelectedItem().toString();        
        
        ArrayList<String> lista = new ArrayList<String>();
        
        _dts.setDoc(selec);
        
        lista = _con.mosDoctor(_dts);                        
        
        DD.lblNombreDoc.setText(lista.get(0));
        DD.lblCedulaDoc.setText(lista.get(1));
        DD.lblTelefonoDoc.setText(lista.get(2));
        DD.lblHospital.setText(lista.get(3));        
        
        
        desktop1.add(DD);
        DD.show();                
    }//GEN-LAST:event_btnVerDoctorActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        desbloquearNuevo1();
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        GuardarDoctor dts = new GuardarDoctor();
        Conexion _con = new Conexion();
        
        int limite = 7;
        int lc = txtCedula.getText().length();
        int limite2 = 5;
        int lp = txtContraseña1.getText().length();
        
        String _ced = txtCedula.getText ();
        String _nom = txtNombre1.getText();
        String _tel1 = txtTelefono1.getText();
        String _tel2 = txtTelefono2.getText();
        String _tel3 = txtTelefono3.getText();
        String _tel4 = txtTelefono4.getText();
        String _tel = _tel1 + "-" + _tel2 + "-" + _tel3 + "-" + _tel4;
        String _hos = cbHospital.getSelectedItem().toString();
        String _user = txtUsuario1.getText();
        String _pass = txtContraseña1.getText();
        String _cPass = txtConfContraseña1.getText();
        
        dts.setCed(_ced);
        dts.setNombre(_nom);
        dts.setTelefono(_tel);
        dts.setHospital(_hos);
        dts.setUsuario(_user);
        dts.setContraseña(_pass);
        
        if(_ced.isEmpty() || _nom.isEmpty() || _tel1.isEmpty() || _tel2.isEmpty() || _tel3.isEmpty() || _tel4.isEmpty() || _hos.isEmpty() || _user.isEmpty() || _pass.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos", "Campos requeridos", 2);
        } else{
            if (_pass.equals(_cPass)){
                if(limite == lc){
                    if(lp >= limite2){
                        _con.insertarDoctor(dts);
                        JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "Completado",1);
                        limpiar1();
                        bloquear1();
                        mostrarDoctores();
                        cbHospital.setModel(_con.getvalues1());
                        cbPacienteHistorial.setModel(_con.getvalues2());
                        cbDoctorDis.setModel(_con.getvalues3());
                    }else{
                        JOptionPane.showMessageDialog(null, "La contraseña debe contar al menos con 5 caracteres", "Error",2);
                    }                    
                }else{
                    JOptionPane.showMessageDialog(null, "La cedula no es correcta, favor de revisarla", "Error",2);
                }             
            }else{
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error",2);
            }            
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnMostrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrar1ActionPerformed
        mostrarDoctores();
    }//GEN-LAST:event_btnMostrar1ActionPerformed

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        
        ActualizarDoctor dts = new ActualizarDoctor();
        Conexion _con = new Conexion();
        
        String _ced = txtCedula.getText();
        String _nom = txtNombre1.getText();
        String _tel1 = txtTelefono1.getText();
        String _tel2 = txtTelefono2.getText();
        String _tel3 = txtTelefono3.getText();
        String _tel4 = txtTelefono4.getText();
        String _tel = _tel1 + "-" + _tel2 + "-" + _tel3 + "-" + _tel4;
        String _hos = cbHospital.getSelectedItem().toString();              
      
        System.out.println(_ced);
        System.out.println(_nom);
        System.out.println(_tel);
        System.out.println(_hos);
        
        dts.setCed(_ced);
        dts.setNombre(_nom);
        dts.setTelefono(_tel);
        dts.setHospital(_hos);             
        
        if(_nom.isEmpty() || _tel1.isEmpty() || _tel2.isEmpty() || _tel3.isEmpty() || _tel4.isEmpty() || _hos.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos", "Campos requeridos", 2);
        } else{                            
            
                _con.actualizarDoctor(dts);
                JOptionPane.showMessageDialog(null, "Se ha Actualizado correctamente", "Completado",1);       
                limpiar1();
                bloquear1();
                mostrarDoctores();
                cbHospital.setModel(_con.getvalues1());
                cbPacienteHistorial.setModel(_con.getvalues2());
                cbDoctorDis.setModel(_con.getvalues3());
       }            
        
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void btnModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar1ActionPerformed
         int _fila = docTable.getSelectedRow();

        if ( _fila >= 0 ){
            txtCedula.setText(docTable.getValueAt(_fila, 0).toString());
            txtNombre1.setText(docTable.getValueAt(_fila, 1).toString());          
            String _tel = docTable.getValueAt(_fila, 2).toString();
            String _hos = docTable.getValueAt(_fila,3).toString();
            
            String _tel1 = _tel.substring(0,3);
            String _tel2 = _tel.substring(4,7);
            String _tel3 = _tel.substring(8,10);
            String _tel4 = _tel.substring(11,13);
            
            txtTelefono1.setText(_tel1);
            txtTelefono2.setText(_tel2);
            txtTelefono3.setText(_tel3);
            txtTelefono4.setText(_tel4);
            
            cbHospital.setSelectedItem(_hos);
            desbloquearModificar1();
        }else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada","Error", 2);
        }
        
    }//GEN-LAST:event_btnModificar1ActionPerformed

    private void btnInaActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInaActActionPerformed
        BorrarDoctor dts = new BorrarDoctor();
        Conexion _con = new Conexion();                
        
        int _fila = docTable.getSelectedRow();        
        
        if ( _fila >= 0 ){                 
            txtCedula.setText(docTable.getValueAt(_fila, 0).toString());   
            String[] options = {"Activo", "Inactivo"};
            int resp = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Titulo", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if(resp == 0){        
                    String _ced = txtCedula.getText();
                    dts.setCed(_ced);
                    _con.borrarDoctor2(dts);
                    cbHospital.setModel(_con.getvalues1());
                    cbPacienteHistorial.setModel(_con.getvalues2());
                    cbDoctorDis.setModel(_con.getvalues3());
                    limpiar1();
                    
            }else{
                if(resp == 1){                
                      String _ced = txtCedula.getText();
                      dts.setCed(_ced);
                      _con.borrarDoctor(dts);                   
                      cbHospital.setModel(_con.getvalues1());
                      cbPacienteHistorial.setModel(_con.getvalues2());
                      cbDoctorDis.setModel(_con.getvalues3());
                      limpiar1();
                      
                }
            }                                                
        }else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada","Error", 2);            
        }
    }//GEN-LAST:event_btnInaActActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        bloquear1();
        limpiar1();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        Conexion _con = new Conexion();
        BusquedaDoctor _dts = new BusquedaDoctor();
        
        String _var = cbBuscar1.getSelectedItem().toString();
        String _bus = txtBuscar1.getText();                
        
        if (_var.equals("Cédula")){
            DefaultTableModel _mod;            
            _dts.setCedula(_bus);
            _dts.setNombre("");
            _dts.setHospital("");
            _mod = _con.BusquedaDoctor(_dts);
            docTable.setModel(_mod);
            int _fila = docTable.getRowCount();
            System.out.println(_fila);
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);    
                txtBuscar1.setText("");
                cbBuscar1.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);
                txtBuscar1.setText("");
                cbBuscar1.setSelectedIndex(0);
                mostrarDoctores();
            }         
        }
        if (_var.equals("Nombre")){
            DefaultTableModel _mod;       
            _dts.setCedula("");
            _dts.setNombre(_bus);
            _dts.setHospital("");
            _mod = _con.BusquedaDoctor(_dts);
            docTable.setModel(_mod);
            int _fila = docTable.getRowCount();
            System.out.println(_fila);
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);    
                txtBuscar1.setText("");
                cbBuscar1.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);
                txtBuscar1.setText("");
                cbBuscar1.setSelectedIndex(0);
                mostrarDoctores();
            }                       
        }
        if (_var.equals("Hospital")){
            DefaultTableModel _mod;            
            _dts.setCedula("");
            _dts.setNombre("");
            _dts.setHospital(_bus);
            _mod = _con.BusquedaDoctor(_dts);
            docTable.setModel(_mod);
           int _fila = docTable.getRowCount();
            System.out.println(_fila);
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);    
                txtBuscar1.setText("");
                cbBuscar1.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);
                txtBuscar1.setText("");
                cbBuscar1.setSelectedIndex(0);
                mostrarDoctores();
            }                
        }
        
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        GuardarHospital dts = new GuardarHospital();
        Conexion _con = new Conexion();                
                
        String _nom = txtNombre2.getText();
        String _calle = txtCalle1.getText();
        String _colonia = txtColonia1.getText();
        String _cp = txtCP1.getText();
        String _ciudad = txtCiudad1.getText();        
        String _tel1 = txtTelefonoH1.getText();
        String _tel2 = txtTelefonoH2.getText();
        String _tel3 = txtTelefonoH3.getText();
        String _tel4 = txtTelefonoH4.getText();                                
        String _tel = _tel1 + "-" + _tel2 + "-" + _tel3 + "-" + _tel4;
        String _estado = txtEstado1.getText();
        
        dts.setNombre(_nom);
        dts.setCalle(_calle);
        dts.setColonia(_colonia);
        dts.setCp(_cp);
        dts.setCiudad(_ciudad);
        dts.setTelefono(_tel);
        dts.setEstado(_estado);
        
        if(_nom.isEmpty() || _calle.isEmpty() || _tel1.isEmpty() || _tel2.isEmpty() || _tel3.isEmpty() || _tel4.isEmpty() || _colonia.isEmpty() || _cp.isEmpty() || _ciudad.isEmpty() || _estado.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos", "Campos requeridos", 2);
        } else{                                    
               _con.insertarHospital(dts);
               JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "Completado",1);               
               limpiar2();
               bloquear2();
               mostrarInstituciones();                             
               cbHospital.setModel(_con.getvalues1());
               cbPacienteHistorial.setModel(_con.getvalues2());
               cbDoctorDis.setModel(_con.getvalues3());
        }
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void btnMostrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrar2ActionPerformed
        mostrarInstituciones();
    }//GEN-LAST:event_btnMostrar2ActionPerformed

    private void btnActualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar2ActionPerformed
        
        GuardarHospital dts = new GuardarHospital();
        Conexion _con = new Conexion();
        
        String _nom = txtNombre2.getText();
        String _calle = txtCalle1.getText();
        String _colonia = txtColonia1.getText();
        String _cp = txtCP1.getText();
        String _ciudad = txtCiudad1.getText();        
        String _tel1 = txtTelefonoH1.getText();
        String _tel2 = txtTelefonoH2.getText();
        String _tel3 = txtTelefonoH3.getText();
        String _tel4 = txtTelefonoH4.getText();                                
        String _tel = _tel1 + "-" + _tel2 + "-" + _tel3 + "-" + _tel4;
        String _estado = txtEstado1.getText();
        
        dts.setNombre(_nom);
        dts.setCalle(_calle);
        dts.setColonia(_colonia);
        dts.setCp(_cp);
        dts.setCiudad(_ciudad);
        dts.setTelefono(_tel);
        dts.setEstado(_estado);   
        
        if(_nom.isEmpty() || _calle.isEmpty() || _tel1.isEmpty() || _tel2.isEmpty() || _tel3.isEmpty() || _tel4.isEmpty() || _colonia.isEmpty() || _cp.isEmpty() || _ciudad.isEmpty() || _estado.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos", "Campos requeridos", 2);
        } else{                                    
               _con.modificarHospital(dts);
               JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "Completado",1);
               limpiar2();
               bloquear2();
               mostrarInstituciones();                           
               cbHospital.setModel(_con.getvalues1());
               cbPacienteHistorial.setModel(_con.getvalues2());
               cbDoctorDis.setModel(_con.getvalues3());
        }
    }//GEN-LAST:event_btnActualizar2ActionPerformed

    private void btnModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar2ActionPerformed
        int _fila = insTable.getSelectedRow();

        if ( _fila >= 0 ){
            txtNombre2.setText(insTable.getValueAt(_fila, 0).toString());
            txtCalle1.setText(insTable.getValueAt(_fila, 1).toString());                        
            txtColonia1.setText(insTable.getValueAt(_fila, 2).toString());                        
            txtCP1.setText(insTable.getValueAt(_fila, 3).toString());                        
            txtCiudad1.setText(insTable.getValueAt(_fila, 4).toString());                        
            txtEstado1.setText(insTable.getValueAt(_fila, 5).toString());                        
            String _tel = insTable.getValueAt(_fila, 6).toString();
            
            String _tel1 = _tel.substring(0,3);
            String _tel2 = _tel.substring(4,7);
            String _tel3 = _tel.substring(8,10);
            String _tel4 = _tel.substring(11,13);
            
            txtTelefonoH1.setText(_tel1);
            txtTelefonoH2.setText(_tel2);
            txtTelefonoH3.setText(_tel3);
            txtTelefonoH4.setText(_tel4);
            
            desbloquearModificar2();
        }else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada","Error", 2);
        }
    }//GEN-LAST:event_btnModificar2ActionPerformed

    private void btnEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar2ActionPerformed
        BorrarHospital dts = new BorrarHospital();
        Conexion _con = new Conexion();
        
        int _fila = insTable.getSelectedRow();
        
        if ( _fila >= 0 ){
            txtNombre2.setText(insTable.getValueAt(_fila, 0).toString());
            String _nom = txtNombre2.getText();
            dts.setNombre(_nom);
            _con.borrarHospital(dts);         
            limpiar2();
            cbHospital.setModel(_con.getvalues1());
            cbPacienteHistorial.setModel(_con.getvalues2());
            cbDoctorDis.setModel(_con.getvalues3());
            mostrarInstituciones();
        } else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada","Error", 2);
        }
    }//GEN-LAST:event_btnEliminar2ActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        desbloquearNuevo2();
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        bloquear2();
        limpiar2();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        Conexion _con = new Conexion();
        BusquedaHospital _dts = new BusquedaHospital();
        
        String _var = cbBuscar2.getSelectedItem().toString();
        String _bus = txtBuscar2.getText();                
        
        if (_var.equals("Nombre")){
            DefaultTableModel _mod;            
            _dts.setNombre(_bus);
            _dts.setCiudad("");
            _dts.setEstado("");
            _dts.setCp("");
            
            _mod = _con.busquedaHospital(_dts);
            insTable.setModel(_mod);
            int _fila = insTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);    
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
                mostrarInstituciones();
            }         
        }
        
        if (_var.equals("Ciudad")){
            DefaultTableModel _mod;            
            _dts.setNombre("");
            _dts.setCiudad(_bus);
            _dts.setEstado("");
            _dts.setCp("");
            
            _mod = _con.busquedaHospital(_dts);
            insTable.setModel(_mod);
            int _fila = insTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);    
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
                mostrarInstituciones();
            }         
        }
        
        if (_var.equals("Estado")){
            DefaultTableModel _mod;            
            _dts.setNombre("");
            _dts.setCiudad("");
            _dts.setEstado(_bus);
            _dts.setCp("");
            
            _mod = _con.busquedaHospital(_dts);
            insTable.setModel(_mod);
            int _fila = insTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);    
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
                mostrarInstituciones();
            }         
        }
        
        if (_var.equals("Código Postal")){
            DefaultTableModel _mod;            
            _dts.setNombre("");
            _dts.setCiudad("");
            _dts.setEstado("");
            _dts.setCp(_bus);
            
            _mod = _con.busquedaHospital(_dts);
            insTable.setModel(_mod);
            int _fila = insTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);    
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);
                txtBuscar2.setText("");
                cbBuscar2.setSelectedIndex(0);
                mostrarInstituciones();
            }         
        }
        
    }//GEN-LAST:event_btnBuscar2ActionPerformed

    private void btnGuardarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCitaActionPerformed
        RegistrarCita dts = new RegistrarCita();
        Conexion _con = new Conexion();
        
        String _pac = cbPacienteHistorial.getSelectedItem().toString();
        String _doc = cbDoctorDis.getSelectedItem().toString();
        
        
        Date fecha = new Date();
        DateFormat dfDateInstance = DateFormat.getDateInstance();
        String _fec = dfDateInstance.format(fecha);
        
        dts.setPaciente(_pac);
        dts.setDoctor(_doc);
        dts.setFecha(_fec);
        
        _con.registroCita(dts);
        JOptionPane.showMessageDialog(null, "Cita registrada correctamente", "Completado",1);    
        
        bloquearCita();
        cbDoctorDis.setSelectedIndex(0);
        cbPacienteHistorial.setSelectedIndex(0);
        mostrarHistorial();
    }//GEN-LAST:event_btnGuardarCitaActionPerformed

    private void btnActualizarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarDoctorActionPerformed
        CambioDoctor dts = new CambioDoctor();
        Conexion _con = new Conexion();
        
        String _pac = cbPacienteHistorial.getSelectedItem().toString();
        String _doc = cbDoctorDis.getSelectedItem().toString();        
        
        dts.setPac(_pac);
        dts.setDoc(_doc);        
        
        _con.cambioDoctor(dts);
        JOptionPane.showMessageDialog(null, "Doctor de cabecera actualizado correctamente", "Completado",1);    
        
        bloquearCita();
        cbDoctorDis.setSelectedIndex(0);
        cbPacienteHistorial.setSelectedIndex(0);
        mostrarPacientes();
    }//GEN-LAST:event_btnActualizarDoctorActionPerformed

    private void btnBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar3ActionPerformed
        Conexion _con = new Conexion();
        HistorialAtencion _dts = new HistorialAtencion();
        
        String _var = cbBuscar3.getSelectedItem().toString();
        
        
        if (_var.equals("Paciente")){            
            String _bus = cbPaciente.getSelectedItem().toString();
            
            DefaultTableModel _mod;            
            
            _dts.setPaciente(_bus);
            _dts.setDoctor("");
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha("");
            
            _mod = _con.busquedaHistorial(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                desbloquearHistorial();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                mostrarHistorial();
                desbloquearHistorial();
            }         
        }
        
        if (_var.equals("Doctor")){                        
            String _bus = cbDoctor.getSelectedItem().toString();
            
            DefaultTableModel _mod;            
            
            _dts.setPaciente("");
            _dts.setDoctor(_bus);
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha("");
            
            _mod = _con.busquedaHistorial(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                desbloquearHistorial();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                mostrarHistorial();                
                desbloquearHistorial();
            }         
        }
        
        if (_var.equals("Fecha")){                        
            String _fec = new SimpleDateFormat("dd/MM/yyyy").format(calFecha.getDate());
            
            DefaultTableModel _mod;            
            
            _dts.setPaciente("");
            _dts.setDoctor("");
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha(_fec);
            
            _mod = _con.busquedaHistorial(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                desbloquearHistorial();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                mostrarHistorial();                
                desbloquearHistorial();
            }         
        }
        
        if (_var.equals("Año")){                        
            int _fec = calAño.getYear();
            
            DefaultTableModel _mod;            
            
            _dts.setPaciente("");
            _dts.setDoctor("");
            _dts.setMes(0);
            _dts.setAño(_fec);
            _dts.setFecha("");
            
            _mod = _con.busquedaHistorial(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                desbloquearHistorial();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                mostrarHistorial();                
                desbloquearHistorial();
            }         
        }
        
        if (_var.equals("Mes y Año")){                        
            int _mes = calMes.getMonth() + 1;
            int _año = calAño.getYear();
            
            System.out.println(_mes + "/" + _año);
            DefaultTableModel _mod;            
            
            _dts.setPaciente("");
            _dts.setDoctor("");
            _dts.setMes(_mes);
            _dts.setAño(_año);
            _dts.setFecha("");
            
            _mod = _con.busquedaHistorial(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                desbloquearHistorial();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                cbBuscar3.setSelectedIndex(0);
                cbDoctor.setSelectedIndex(0);
                cbPaciente.setSelectedIndex(0);
                calFecha.setDate(null);
                calAño.setYear(2018);
                calMes.setMonth(0);
                mostrarHistorial();                
                desbloquearHistorial();
            }         
        }
    }//GEN-LAST:event_btnBuscar3ActionPerformed

    private void cbBuscar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBuscar3MouseClicked
        
    }//GEN-LAST:event_cbBuscar3MouseClicked

    private void cbBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscar3ActionPerformed
        desbloquearHistorial();
    }//GEN-LAST:event_cbBuscar3ActionPerformed

    private void btnMostrarHisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarHisActionPerformed
        mostrarHistorial();
    }//GEN-LAST:event_btnMostrarHisActionPerformed

    private void btnNuevoCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCitasActionPerformed
        desbloquearCita();
    }//GEN-LAST:event_btnNuevoCitasActionPerformed

    private void btnCancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCitaActionPerformed
        bloquearCita();
    }//GEN-LAST:event_btnCancelarCitaActionPerformed

    private void btnModCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModCitasActionPerformed
        desbloquearModCita();
    }//GEN-LAST:event_btnModCitasActionPerformed

    private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction1ActionPerformed
        mostrarPacientes();
    }//GEN-LAST:event_buttonAction1ActionPerformed

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
            java.util.logging.Logger.getLogger(Secretaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Secretaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Secretaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Secretaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Secretaria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAction btnActualizar1;
    private org.edisoncor.gui.button.ButtonAction btnActualizar2;
    private org.edisoncor.gui.button.ButtonAction btnActualizarDoctor;
    private org.edisoncor.gui.button.ButtonAction btnBuscar1;
    private org.edisoncor.gui.button.ButtonAction btnBuscar2;
    private org.edisoncor.gui.button.ButtonAction btnBuscar3;
    private org.edisoncor.gui.button.ButtonAction btnCancelar1;
    private org.edisoncor.gui.button.ButtonAction btnCancelar2;
    private org.edisoncor.gui.button.ButtonAction btnCancelarCita;
    private org.edisoncor.gui.button.ButtonAction btnEliminar2;
    private org.edisoncor.gui.button.ButtonAction btnGuardar1;
    private org.edisoncor.gui.button.ButtonAction btnGuardar2;
    private org.edisoncor.gui.button.ButtonAction btnGuardarCita;
    private org.edisoncor.gui.button.ButtonAction btnInaAct;
    private org.edisoncor.gui.button.ButtonAction btnModCitas;
    private org.edisoncor.gui.button.ButtonAction btnModificar1;
    private org.edisoncor.gui.button.ButtonAction btnModificar2;
    private org.edisoncor.gui.button.ButtonAction btnMostrar1;
    private org.edisoncor.gui.button.ButtonAction btnMostrar2;
    private org.edisoncor.gui.button.ButtonAction btnMostrarHis;
    private org.edisoncor.gui.button.ButtonAction btnNuevo1;
    private org.edisoncor.gui.button.ButtonAction btnNuevo2;
    private org.edisoncor.gui.button.ButtonAction btnNuevoCitas;
    private org.edisoncor.gui.button.ButtonAction btnVerDoctor;
    private org.edisoncor.gui.button.ButtonAction buttonAction1;
    private org.edisoncor.gui.button.ButtonIcon buttonIcon1;
    private org.edisoncor.gui.button.ButtonIcon buttonIcon2;
    private com.toedter.calendar.JYearChooser calAño;
    private com.toedter.calendar.JDateChooser calFecha;
    private com.toedter.calendar.JMonthChooser calMes;
    private javax.swing.JComboBox<String> cbBuscar1;
    private javax.swing.JComboBox<String> cbBuscar2;
    private javax.swing.JComboBox<String> cbBuscar3;
    public static javax.swing.JComboBox<String> cbDoctor;
    public static javax.swing.JComboBox<String> cbDoctorDis;
    private javax.swing.JComboBox<String> cbHospital;
    private javax.swing.JComboBox<String> cbPaciente;
    private javax.swing.JComboBox<String> cbPacienteHistorial;
    private org.edisoncor.gui.varios.ClockDigital clockDigital2;
    private org.edisoncor.gui.varios.ClockDigital clockDigital3;
    private org.edisoncor.gui.varios.ClockDigital clockDigital4;
    private org.edisoncor.gui.varios.ClockDigital clockDigital5;
    private org.edisoncor.gui.varios.ClockDigital clockDigital6;
    public static javax.swing.JDesktopPane desktop1;
    private javax.swing.JTable docTable;
    private javax.swing.JTable hisTable;
    private javax.swing.JTable insTable;
    private com.toedter.calendar.JDateChooser jCalendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable pacTable;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader tabbedPaneHeader2;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 tabbedSelector21;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtBuscar1;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtBuscar2;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtCP1;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtCalle1;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtCedula;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtCiudad1;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtColonia1;
    private javax.swing.JPasswordField txtConfContraseña1;
    private javax.swing.JPasswordField txtContraseña1;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtEstado1;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre1;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre2;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    private javax.swing.JTextField txtTelefono3;
    private javax.swing.JTextField txtTelefono4;
    private javax.swing.JTextField txtTelefonoH1;
    private javax.swing.JTextField txtTelefonoH2;
    private javax.swing.JTextField txtTelefonoH3;
    private javax.swing.JTextField txtTelefonoH4;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtUsuario1;
    // End of variables declaration//GEN-END:variables
}
