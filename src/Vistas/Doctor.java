/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Conexiones.Conexion;
import Procedimientos.ActualizarAntec;
import Procedimientos.ActualizarPaciente;
import Procedimientos.ActualizarReceptor;
import Procedimientos.BusquedaHistorial;
import Procedimientos.BusquedaPaciente;
import Procedimientos.GuardarPaciente;
import Procedimientos.MedicoQuirurgico;
import Procedimientos.Paciente;
import Procedimientos.idPaciente;
import com.sun.awt.AWTUtilities;
import conexiones.conexionDB;
import java.awt.Desktop;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.print.PrinterException;
import java.net.URI;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mendo
 */
public class Doctor extends javax.swing.JFrame {

    int _x, _y;
        
    /**
     * Creates new form Doctor
     */
    public Doctor() {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        Shape _form = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 27, 27);
        AWTUtilities.setWindowShape(this, _form);
        try {
            setIconImage(new ImageIcon(getClass().getResource("../img/corazon.png")).getImage());
        } catch (Exception e) {
        }
        Conexion _con = new Conexion();        
        Paciente _pac = new Paciente();
        cbPadecimiento.setModel(_con.getvalues5());        
        cbBuscar2.setModel(_con.getvalues6());
        cbMQ.setModel(_con.getvalues7());
        _con.mostrarPacientes(cbPaciente1);
        _con.mostrarPacientes2(cbBuscarPaciente);
        cbPaciente2.setModel(_con.getvalues8());
        addItem();        
        mostrarPacientes();
        mostrarMonitoreo();
        mostrarHistorial();
        bloquear1();
        desbloquearBusqueda();
        desbloquearBusquedaHis();
        bloquearInfo();
        iniciarInfo();
    }
    
    private class Hilo implements Runnable{

        @Override
        public void run() {
            while(true){
                try{                    
                    mostrarMonitoreo();                    
                }catch(Exception e){
                    
                }
                try {
                    Thread.sleep(300000);                                     
                } catch (InterruptedException ex) {
                    Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
    
    
    void bloquearBusquedaInfo(){
        cbPaciente1.setEnabled(false);
        btnPaciente1.setEnabled(false);
        btnCancelar4.setEnabled(true);
        btnImprimir2.setEnabled(true);
    }
    
    void iniciarInfo(){
        cbPaciente1.setEnabled(true);        
        btnPaciente1.setEnabled(true);
        btnCancelar4.setEnabled(false);
        btnImprimir2.setEnabled(false);
    }
    
    void bloquearInfo(){
        //Tutor
        txtNombre3.setEnabled(false);
        txtTelT1.setEnabled(false);
        txtTelT2.setEnabled(false);
        txtTelT3.setEnabled(false);
        txtTelT4.setEnabled(false);
        btnEditar1.setEnabled(false);
        btnCancelar2.setEnabled(false);
        btnCompletado1.setEnabled(false);
        //Doctor
        txtNombre4.setEnabled(false);
        txtTelD1.setEnabled(false);
        txtTelD2.setEnabled(false);
        txtTelD3.setEnabled(false);
        txtTelD4.setEnabled(false);
        txtHospital.setEnabled(false);
        txtUsuario.setEnabled(false);        
        //Antecedentes
        txtAnt1.setEnabled(false);
        txtAnt2.setEnabled(false);
        txtAnt3.setEnabled(false);
        calOperacion.setEnabled(false);
        cbMQ.setEnabled(false);
        btnAgregar1.setEnabled(false);
        btnCancelar3.setEnabled(false);
        btnCompletado2.setEnabled(false);
        btnPadecimiento2.setEnabled(false);
        btnMostrarMQ.setEnabled(false);
    }
    
    void limpiarInfo(){
        txtNombre3.setText("");
        txtTelT1.setText("");
        txtTelT2.setText("");
        txtTelT3.setText("");
        txtTelT4.setText("");
        txtNombre4.setText("");
        txtTelD1.setText("");
        txtTelD2.setText("");
        txtTelD3.setText("");
        txtTelD4.setText("");
        txtHospital.setText("");
        txtUsuario.setText("");
        txtAnt1.setText("");
        txtAnt2.setText("");
        txtAnt3.setText("");
        calOperacion.setDateFormatString("");
        cbMQ.setSelectedIndex(0);
    }
    
    void desbloquearInfoAnte(){
        txtAnt1.setEnabled(false);
        txtAnt2.setEnabled(false);
        txtAnt3.setEnabled(false);
        calOperacion.setEnabled(false);
        cbMQ.setEnabled(false);
        btnAgregar1.setEnabled(true);
        btnCancelar3.setEnabled(false);
        btnCompletado2.setEnabled(false);
        btnPadecimiento2.setEnabled(true);
        btnMostrarMQ.setEnabled(true);
    }
    
    void desbloquearAnte(){
        txtAnt1.setEnabled(true);
        txtAnt2.setEnabled(true);
        txtAnt3.setEnabled(true);
        calOperacion.setEnabled(true);
        cbMQ.setEnabled(true);
        btnAgregar1.setEnabled(false);
        btnCancelar3.setEnabled(true);
        btnCompletado2.setEnabled(true);
        btnPadecimiento2.setEnabled(true);
    }
    
    
    void desbloquearInfoTutor(){
        txtNombre3.setEnabled(false);
        txtTelT1.setEnabled(false);
        txtTelT2.setEnabled(false);
        txtTelT3.setEnabled(false);
        txtTelT4.setEnabled(false);
        btnEditar1.setEnabled(true);
        btnCancelar2.setEnabled(false);
        btnCompletado1.setEnabled(false);
    }
    
    void desbloquearTutor(){
        txtNombre3.setEnabled(true);
        txtTelT1.setEnabled(true);
        txtTelT2.setEnabled(true);
        txtTelT3.setEnabled(true);
        txtTelT4.setEnabled(true);
        btnEditar1.setEnabled(false);
        btnCancelar2.setEnabled(true);
        btnCompletado1.setEnabled(true);
    }
    
    void limpiarBusqueda(){
        cbBuscar2.setSelectedIndex(0);
        cbBuscar1.setSelectedIndex(0);
        spEdad1.setValue(1);
        calFecha1.setDate(null);
        calAño1.setYear(2018);
        calMes1.setMonth(0);
    }
    
    void limpiarBusqueda2(){
        cbBuscar3.setSelectedIndex(0);
        cbPaciente2.setSelectedIndex(0);        
        calFecha2.setDate(null);
        calAño2.setYear(2018);
        calMes2.setMonth(0);
    }
    
    void desbloquearBusqueda(){
        
        String selec = cbBuscar1.getSelectedItem().toString();
        
        if(selec.equals("Nombre")){
            cbBuscar2.setEnabled(true);
            spEdad1.setEnabled(false);
            calMes1.setEnabled(false);
            calAño1.setEnabled(false);
            calFecha1.setEnabled(false);
        }else{
            if(selec.equals("Edad")){
                cbBuscar2.setEnabled(false);
                spEdad1.setEnabled(true);
                calMes1.setEnabled(false);
                calAño1.setEnabled(false);
                calFecha1.setEnabled(false);
            }else{
                if(selec.equals("Mes y Año")){
                    cbBuscar2.setEnabled(false);
                    spEdad1.setEnabled(false);
                    calMes1.setEnabled(true);
                    calAño1.setEnabled(true);
                    calFecha1.setEnabled(false);
                }else{
                    if(selec.equals("Año")){
                        cbBuscar2.setEnabled(false);
                        spEdad1.setEnabled(false);
                        calMes1.setEnabled(false);
                        calAño1.setEnabled(true);
                        calFecha1.setEnabled(false);
                    }else{
                        if(selec.equals("Fecha")){
                            cbBuscar2.setEnabled(false);
                            spEdad1.setEnabled(false);
                            calMes1.setEnabled(false);
                            calAño1.setEnabled(false);
                            calFecha1.setEnabled(true);          
                        }
                    }
                }         
            }
        }
    }
    
    void desbloquearBusquedaHis(){
        
        String selec = cbBuscar3.getSelectedItem().toString();
        
        if(selec.equals("Paciente")){
            cbPaciente2.setEnabled(true);            
            calMes2.setEnabled(false);
            calAño2.setEnabled(false);
            calFecha2.setEnabled(false);
        }else{            
            if(selec.equals("Mes y Año")){
                cbPaciente2.setEnabled(false);                
                calMes2.setEnabled(true);
                calAño2.setEnabled(true);
                calFecha2.setEnabled(false);
            }else{
                if(selec.equals("Año")){
                    cbPaciente2.setEnabled(false);                    
                    calMes2.setEnabled(false);
                    calAño2.setEnabled(true);
                    calFecha2.setEnabled(false);
                }else{
                    if(selec.equals("Fecha")){
                        cbPaciente2.setEnabled(false);                        
                        calMes2.setEnabled(false);
                        calAño2.setEnabled(false);
                        calFecha2.setEnabled(true);                                  
                    }
                }         
            }
        }
    }
    
    void cbPad(){
        Conexion _con = new Conexion();        
        cbPadecimiento.setModel(_con.getvalues5());        
    }

    
    void addItem(){
        cbBuscar1.addItem("Nombre");
        cbBuscar1.addItem("Mes y Año");
        cbBuscar1.addItem("Año");                             
        cbBuscar1.addItem("Fecha");   
        cbBuscar1.addItem("Edad");  
        
        cbBuscar3.addItem("Paciente");
        cbBuscar3.addItem("Mes y Año");
        cbBuscar3.addItem("Año");                             
        cbBuscar3.addItem("Fecha");           
    }

    void mostrarPacientes(){
         try {
            DefaultTableModel _mod;
            Conexion _con = new Conexion();
            _mod = _con.verPacientes2();
            tablePaciente.setModel(_mod);                        
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void mostrarMonitoreo(){
        try {
            DefaultTableModel _mod;
            Conexion _con = new Conexion();
            _mod = _con.verMonitoreo();
            tableMonitoreo.setModel(_mod);                        
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void mostrarHistorial(){
        try {
            DefaultTableModel _mod;
            Conexion _con = new Conexion();
            _mod = _con.mosHistorial();
            hisTable.setModel(_mod);                        
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    void bloquear1(){
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnModificar.setEnabled(true);        
        btnActIna.setEnabled(true);        
        btnNuevoPad.setEnabled(false);
        txtNombre.setEnabled(false);
        txtNombre2.setEnabled(false);
        spEdad.setEnabled(false);
        spPeso.setEnabled(false);
        spEstatura.setEnabled(false);
        calNacimiento.setEnabled(false);
        txtTel1.setEnabled(false);
        txtTel2.setEnabled(false);
        txtTel3.setEnabled(false);
        txtTel4.setEnabled(false);
        cbPadecimiento.setEnabled(false);                        
        btnNuevo.setEnabled(true);
        btnAntecedentes.setEnabled(false);
    }
    
     void desbloquear1(){
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnModificar.setEnabled(false);        
        btnActIna.setEnabled(false);        
        btnNuevoPad.setEnabled(true);
        txtNombre.setEnabled(true);
        txtNombre2.setEnabled(true);
        spEdad.setEnabled(true);
        spPeso.setEnabled(true);
        spEstatura.setEnabled(true);
        calNacimiento.setEnabled(true);
        txtTel1.setEnabled(true);
        txtTel2.setEnabled(true);
        txtTel3.setEnabled(true);
        txtTel4.setEnabled(true);
        cbPadecimiento.setEnabled(true);                        
        btnNuevo.setEnabled(false);
        btnAntecedentes.setEnabled(true);
    }
    
    void desbloquearModificar1(){
        btnModificar.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActIna.setEnabled(false);
        btnActualizar.setEnabled(true);
        txtNombre.setEnabled(true);
        spEdad.setEnabled(true);
        spPeso.setEnabled(true);
        spEstatura.setEnabled(true);
        calNacimiento.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    
    void limpiar1(){
        txtNombre.setText("");
        txtNombre2.setText("");
        spEdad.setValue(1);
        spPeso.setValue(2);
        spEstatura.setValue(0);
        calNacimiento.setDateFormatString("");
        txtTel1.setText("");
        txtTel2.setText("");
        txtTel3.setText("");
        txtTel4.setText("");
        cbPadecimiento.setSelectedIndex(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        buttonIcon1 = new org.edisoncor.gui.button.ButtonIcon();
        buttonIcon2 = new org.edisoncor.gui.button.ButtonIcon();
        tabbedPaneHeader1 = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        jPanel2 = new javax.swing.JPanel();
        tabbedSelector21 = new org.edisoncor.gui.tabbedPane.TabbedSelector2();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        spPeso = new javax.swing.JSpinner();
        spEdad = new javax.swing.JSpinner();
        spEstatura = new javax.swing.JSpinner();
        calNacimiento = new com.toedter.calendar.JDateChooser();
        txtNombre2 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtTel1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTel2 = new javax.swing.JTextField();
        txtTel3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTel4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbPadecimiento = new javax.swing.JComboBox<>();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        clockDigital2 = new org.edisoncor.gui.varios.ClockDigital();
        cbBuscar1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePaciente = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAntecedentes = new org.edisoncor.gui.button.ButtonRound();
        jSeparator7 = new javax.swing.JSeparator();
        btnNuevo = new org.edisoncor.gui.button.ButtonRound();
        btnNuevoPad = new org.edisoncor.gui.button.ButtonRound();
        btnModificar = new org.edisoncor.gui.button.ButtonRound();
        btnCancelar = new org.edisoncor.gui.button.ButtonRound();
        btnGuardar = new org.edisoncor.gui.button.ButtonRound();
        btnActualizar = new org.edisoncor.gui.button.ButtonRound();
        btnActIna = new org.edisoncor.gui.button.ButtonRound();
        btnBuscar1 = new org.edisoncor.gui.button.ButtonRound();
        btnMostrar = new org.edisoncor.gui.button.ButtonRound();
        cbBuscar2 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        calFecha1 = new com.toedter.calendar.JDateChooser();
        calMes1 = new com.toedter.calendar.JMonthChooser();
        calAño1 = new com.toedter.calendar.JYearChooser();
        spEdad1 = new javax.swing.JSpinner();
        jLabel41 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        btnImprimir1 = new org.edisoncor.gui.button.ButtonRound();
        jPanel6 = new javax.swing.JPanel();
        cbPaciente1 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNombre3 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtTelT1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTelT2 = new javax.swing.JTextField();
        txtTelT3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTelT4 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnCompletado1 = new org.edisoncor.gui.button.ButtonRound();
        btnEditar1 = new org.edisoncor.gui.button.ButtonRound();
        btnCancelar2 = new org.edisoncor.gui.button.ButtonRound();
        btnPaciente1 = new org.edisoncor.gui.button.ButtonRound();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNombre4 = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtTelD1 = new javax.swing.JTextField();
        txtTelD2 = new javax.swing.JTextField();
        txtTelD3 = new javax.swing.JTextField();
        txtTelD4 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtHospital = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        txtUsuario = new org.edisoncor.gui.textField.TextFieldRoundBackground();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnt1 = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        calOperacion = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnt2 = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAnt3 = new javax.swing.JTextArea();
        btnCompletado2 = new org.edisoncor.gui.button.ButtonRound();
        btnAgregar1 = new org.edisoncor.gui.button.ButtonRound();
        btnCancelar3 = new org.edisoncor.gui.button.ButtonRound();
        jLabel30 = new javax.swing.JLabel();
        lblPulsera = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        clockDigital3 = new org.edisoncor.gui.varios.ClockDigital();
        cbMQ = new javax.swing.JComboBox<>();
        btnPadecimiento2 = new org.edisoncor.gui.button.ButtonRound();
        btnPaciente3 = new org.edisoncor.gui.button.ButtonRound();
        btnCancelar4 = new org.edisoncor.gui.button.ButtonRound();
        btnMostrarMQ = new org.edisoncor.gui.button.ButtonRound();
        btnImprimir2 = new org.edisoncor.gui.button.ButtonRound();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableMonitoreo = new javax.swing.JTable();
        btnBuscar4 = new org.edisoncor.gui.button.ButtonRound();
        cbBuscarPaciente = new javax.swing.JComboBox<>();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        panelImage6 = new org.edisoncor.gui.panel.PanelImage();
        clockDigital4 = new org.edisoncor.gui.varios.ClockDigital();
        jLabel40 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        calFecha2 = new com.toedter.calendar.JDateChooser();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        cbPaciente2 = new javax.swing.JComboBox<>();
        cbBuscar3 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        hisTable = new javax.swing.JTable();
        clockDigital5 = new org.edisoncor.gui.varios.ClockDigital();
        calMes2 = new com.toedter.calendar.JMonthChooser();
        jLabel43 = new javax.swing.JLabel();
        calAño2 = new com.toedter.calendar.JYearChooser();
        buttonRound22 = new org.edisoncor.gui.button.ButtonRound();
        buttonRound23 = new org.edisoncor.gui.button.ButtonRound();
        jLabel46 = new javax.swing.JLabel();
        btnImprimir3 = new org.edisoncor.gui.button.ButtonRound();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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

        tabbedPaneHeader1.setColorDeBorde(new java.awt.Color(102, 153, 255));
        tabbedPaneHeader1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        tabbedSelector21.setBackground(new java.awt.Color(0, 255, 224));
        tabbedSelector21.setForeground(new java.awt.Color(102, 102, 102));
        tabbedSelector21.setColorBackGround(new java.awt.Color(0, 255, 224));
        tabbedSelector21.setColorDeBorde(new java.awt.Color(102, 102, 102));
        tabbedSelector21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tabbedSelector21.setSelectionColor(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Edad:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Estatura:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Fecha de Nacimiento:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Peso:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("Teléfono del Tutor:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setText("Tutor o Encargado:");

        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setDescripcion("Nombre");
        txtNombre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        spPeso.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        spPeso.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(2.0f), Float.valueOf(2.0f), Float.valueOf(200.0f), Float.valueOf(0.01f)));

        spEdad.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        spEdad.setModel(new javax.swing.SpinnerNumberModel(1, 0, 120, 1));

        spEstatura.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        spEstatura.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(2.5f), Float.valueOf(0.01f)));

        calNacimiento.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txtNombre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre2.setDescripcion("Nombre");
        txtNombre2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre2KeyTyped(evt);
            }
        });

        txtTel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTel1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTel1KeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("-");

        txtTel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTel2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTel2KeyTyped(evt);
            }
        });

        txtTel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTel3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTel3KeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("-");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("-");

        txtTel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTel4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTel4KeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 102));
        jLabel11.setText("Padecimiento:");

        cbPadecimiento.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbPadecimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbPadecimientoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbPadecimientoMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbPadecimientoMousePressed(evt);
            }
        });
        cbPadecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPadecimientoActionPerformed(evt);
            }
        });

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

        clockDigital2.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital2.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        cbBuscar1.setBackground(new java.awt.Color(153, 153, 153));
        cbBuscar1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        cbBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscar1ActionPerformed(evt);
            }
        });

        tablePaciente.setBackground(new java.awt.Color(255, 153, 51));
        tablePaciente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tablePaciente.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null}
            },
            new String [] {
                "idPaciente", "Nombre", "Edad", "Fecha de Nacimiento", "IMC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablePaciente.setGridColor(new java.awt.Color(0, 0, 0));
        tablePaciente.setRowHeight(30);
        tablePaciente.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(tablePaciente);
        if (tablePaciente.getColumnModel().getColumnCount() > 0) {
            tablePaciente.getColumnModel().getColumn(0).setResizable(false);
            tablePaciente.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablePaciente.getColumnModel().getColumn(1).setResizable(false);
            tablePaciente.getColumnModel().getColumn(1).setPreferredWidth(100);
            tablePaciente.getColumnModel().getColumn(2).setResizable(false);
            tablePaciente.getColumnModel().getColumn(2).setPreferredWidth(30);
            tablePaciente.getColumnModel().getColumn(3).setResizable(false);
            tablePaciente.getColumnModel().getColumn(3).setPreferredWidth(100);
            tablePaciente.getColumnModel().getColumn(4).setResizable(false);
            tablePaciente.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        jLabel12.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("CONTROL DE PACIENTES");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 102));
        jLabel13.setText("Antecedentes:");

        btnAntecedentes.setBackground(new java.awt.Color(51, 153, 255));
        btnAntecedentes.setText("CLICK PARA AGREGAR");
        btnAntecedentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAntecedentesActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(51, 153, 255));
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnNuevoPad.setBackground(new java.awt.Color(51, 153, 255));
        btnNuevoPad.setText("NUEVO");
        btnNuevoPad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPadActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(51, 153, 255));
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(0, 204, 0));
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(0, 204, 0));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnActIna.setBackground(new java.awt.Color(255, 0, 0));
        btnActIna.setText("ACTIVO/INACTIVO");
        btnActIna.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnActIna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActInaActionPerformed(evt);
            }
        });

        btnBuscar1.setBackground(new java.awt.Color(255, 153, 0));
        btnBuscar1.setText("BUSCAR");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnMostrar.setBackground(new java.awt.Color(255, 153, 0));
        btnMostrar.setText("MOSTRAR");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        cbBuscar2.setBackground(new java.awt.Color(153, 153, 153));
        cbBuscar2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbBuscar2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 102, 102));
        jLabel32.setText("Nombre:");

        calFecha1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        calMes1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        spEdad1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        spEdad1.setModel(new javax.swing.SpinnerNumberModel(1, 0, 120, 1));

        jLabel41.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 102));
        jLabel41.setText("Edad:");

        jLabel44.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 102, 102));
        jLabel44.setText("Mes/Año/Fecha:");

        jLabel45.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 102, 102));
        jLabel45.setText("Tipo de búsqueda:");

        btnImprimir1.setBackground(new java.awt.Color(51, 153, 255));
        btnImprimir1.setText("IMPRIMIR");
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(593, 593, 593)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator7)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(194, 385, Short.MAX_VALUE)
                                .addComponent(calAño1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addGap(233, 233, 233)
                                            .addComponent(clockDigital2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(42, 42, 42)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel5)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel11)
                                                        .addComponent(jLabel8)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(calNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                                    .addComponent(txtNombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(txtTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtTel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtTel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel10)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtTel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel13)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnAntecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(cbPadecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevoPad, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel44)
                                            .addComponent(jLabel45))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(calMes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnActIna, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnBuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnImprimir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(panelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel41)
                                .addGap(18, 18, 18)
                                .addComponent(spEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(145, 145, 145))
                            .addComponent(jSeparator1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActIna, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(spPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtTel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(txtTel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNuevoPad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(cbPadecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(btnAntecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(calMes1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calAño1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(calFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clockDigital2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(panelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1))
                .addGap(24, 24, 24))
        );

        tabbedSelector21.addTab("Registros", jPanel3);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        cbPaciente1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbPaciente1.setToolTipText("");
        cbPaciente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaciente1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("Paciente:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 0));
        jLabel15.setText("DATOS DEL TUTOR/ENCARGADO");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("Pulsera:");

        txtNombre3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNombre3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre3KeyTyped(evt);
            }
        });

        txtTelT1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTelT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelT1KeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setText("-");

        txtTelT2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTelT2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelT2KeyTyped(evt);
            }
        });

        txtTelT3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTelT3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelT3KeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 102));
        jLabel19.setText("-");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 102));
        jLabel20.setText("-");

        txtTelT4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTelT4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelT4KeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setText("Teléfono:");

        btnCompletado1.setBackground(new java.awt.Color(0, 204, 0));
        btnCompletado1.setText("COMPLETADO");
        btnCompletado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletado1ActionPerformed(evt);
            }
        });

        btnEditar1.setBackground(new java.awt.Color(51, 153, 255));
        btnEditar1.setText("EDITAR");
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        btnCancelar2.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar2.setText("CANCELAR");
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar2ActionPerformed(evt);
            }
        });

        btnPaciente1.setBackground(new java.awt.Color(51, 153, 255));
        btnPaciente1.setText("CONSULTAR");
        btnPaciente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaciente1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("Nombre:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("Teléfono:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("Hospital:");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 102));
        jLabel25.setText("Usuario:");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 0));
        jLabel26.setText("DATOS DEL DOCTOR DE CABECERA");

        txtNombre4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtNombre4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre4KeyTyped(evt);
            }
        });

        txtTelD1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelD1ActionPerformed(evt);
            }
        });
        txtTelD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelD1KeyTyped(evt);
            }
        });

        txtTelD2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelD2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelD2KeyTyped(evt);
            }
        });

        txtTelD3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelD3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelD3KeyTyped(evt);
            }
        });

        txtTelD4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTelD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelD4KeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel27.setText("-");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel28.setText("-");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel29.setText("-");

        txtHospital.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtHospital.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHospitalKeyTyped(evt);
            }
        });

        txtUsuario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 102, 102));
        jLabel31.setText("Familiares:");

        txtAnt1.setColumns(20);
        txtAnt1.setRows(5);
        jScrollPane2.setViewportView(txtAnt1);

        jLabel33.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 102, 102));
        jLabel33.setText("Personales Patológicos:");

        txtAnt2.setColumns(20);
        txtAnt2.setRows(5);
        jScrollPane3.setViewportView(txtAnt2);

        jLabel34.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 102));
        jLabel34.setText("Fecha:");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 102));
        jLabel35.setText("No Patológicos:");

        txtAnt3.setColumns(20);
        txtAnt3.setRows(5);
        jScrollPane4.setViewportView(txtAnt3);

        btnCompletado2.setBackground(new java.awt.Color(0, 204, 0));
        btnCompletado2.setText("COMPLETADO");
        btnCompletado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletado2ActionPerformed(evt);
            }
        });

        btnAgregar1.setBackground(new java.awt.Color(51, 153, 255));
        btnAgregar1.setText("AGREGAR");
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });

        btnCancelar3.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar3.setText("CANCELAR");
        btnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar3ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 102, 102));
        jLabel30.setText("Nombre:");

        lblPulsera.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblPulsera.setForeground(new java.awt.Color(0, 0, 255));
        lblPulsera.setText("#");

        jLabel36.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setText("INFORMACIÓN Y CONSULTA DEL PACIENTE");
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel37.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 102, 102));
        jLabel37.setText("Médico Quirúrgico:");

        jLabel38.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(102, 102, 0));
        jLabel38.setText("ANTECEDENTES");

        panelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vitadata/corazon2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage5Layout = new javax.swing.GroupLayout(panelImage5);
        panelImage5.setLayout(panelImage5Layout);
        panelImage5Layout.setHorizontalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panelImage5Layout.setVerticalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        clockDigital3.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital3.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital3.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        cbMQ.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbMQ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbMQMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbMQMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbMQMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbMQMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbMQMouseReleased(evt);
            }
        });
        cbMQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMQActionPerformed(evt);
            }
        });

        btnPadecimiento2.setBackground(new java.awt.Color(51, 153, 255));
        btnPadecimiento2.setText("PADECIMIENTOS");
        btnPadecimiento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPadecimiento2ActionPerformed(evt);
            }
        });

        btnPaciente3.setBackground(new java.awt.Color(51, 153, 255));
        btnPaciente3.setText("MENSAJERÍA");
        btnPaciente3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaciente3ActionPerformed(evt);
            }
        });

        btnCancelar4.setBackground(new java.awt.Color(255, 0, 0));
        btnCancelar4.setText("BUSCAR DE NUEVO");
        btnCancelar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar4ActionPerformed(evt);
            }
        });

        btnMostrarMQ.setBackground(new java.awt.Color(255, 153, 0));
        btnMostrarMQ.setText("MOSTRAR");
        btnMostrarMQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarMQActionPerformed(evt);
            }
        });

        btnImprimir2.setBackground(new java.awt.Color(51, 153, 255));
        btnImprimir2.setText("REPORTE");
        btnImprimir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(126, 126, 126)
                                                        .addComponent(jLabel33))
                                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(178, 178, 178)
                                                        .addComponent(jLabel31)))
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(177, 177, 177)
                                                        .addComponent(jLabel37))
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(31, 31, 31)
                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addComponent(btnMostrarMQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnCompletado2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                                        .addComponent(jLabel35)
                                                                        .addGap(154, 154, 154)))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                                    .addComponent(jLabel34)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(cbMQ, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(calOperacion, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))))))))
                                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 76, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(410, 410, 410)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPulsera)
                                        .addGap(26, 26, 26)
                                        .addComponent(clockDigital3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(btnPadecimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(txtTelD1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelD2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(txtTelD3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelD4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHospital, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnImprimir2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(401, 401, 401))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(411, 411, 411)))
                        .addComponent(jLabel38)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelT1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelT2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelT3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTelT4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnCompletado1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel30)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel26)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 1397, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(491, 491, 491)
                .addComponent(jLabel36)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel33))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel37))
                                .addGap(7, 7, 7)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(calOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34))
                                .addGap(18, 18, 18)
                                .addComponent(cbMQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCompletado2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMostrarMQ, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(lblPulsera)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clockDigital3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(btnPadecimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTelT1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(txtTelT2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelT3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(txtTelT4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCompletado1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(txtTelD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)
                                .addComponent(jLabel29)
                                .addComponent(jLabel28)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHospital, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimir2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        tabbedSelector21.addTab("Información", jPanel6);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        tableMonitoreo.setBackground(new java.awt.Color(255, 153, 51));
        tableMonitoreo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tableMonitoreo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Temperatura", "Oxígeno en la Sangre", "Frecuencia Cardíaca", "Fecha y Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMonitoreo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableMonitoreo.setGridColor(new java.awt.Color(0, 0, 0));
        tableMonitoreo.setRowHeight(30);
        tableMonitoreo.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane5.setViewportView(tableMonitoreo);
        if (tableMonitoreo.getColumnModel().getColumnCount() > 0) {
            tableMonitoreo.getColumnModel().getColumn(0).setResizable(false);
            tableMonitoreo.getColumnModel().getColumn(1).setResizable(false);
            tableMonitoreo.getColumnModel().getColumn(3).setResizable(false);
        }

        btnBuscar4.setBackground(new java.awt.Color(51, 153, 255));
        btnBuscar4.setText("BUSCAR PACIENTE");
        btnBuscar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar4ActionPerformed(evt);
            }
        });

        cbBuscarPaciente.setBackground(new java.awt.Color(153, 153, 153));
        cbBuscarPaciente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbBuscarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        cbBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBuscarPacienteActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("MONITOREO DE LOS SIGNOS VITALES");
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        panelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vitadata/corazon2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage6Layout = new javax.swing.GroupLayout(panelImage6);
        panelImage6.setLayout(panelImage6Layout);
        panelImage6Layout.setHorizontalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );
        panelImage6Layout.setVerticalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        clockDigital4.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital4.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital4.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 102));
        jLabel40.setText("Nombre:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(530, 530, 530)
                .addComponent(jLabel39)
                .addContainerGap(535, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(btnBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(panelImage6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clockDigital4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel40))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jSeparator8)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelImage6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(clockDigital4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        tabbedSelector21.addTab("Monitoreo", jPanel7);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedSelector21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedSelector21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneHeader1.addTab("Pacientes", jPanel2);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

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

        calFecha2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 102, 102));
        jLabel42.setText("Tipo de Búsqueda:");

        cbPaciente2.setBackground(new java.awt.Color(153, 153, 153));
        cbPaciente2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbPaciente2.setForeground(new java.awt.Color(255, 255, 255));

        cbBuscar3.setBackground(new java.awt.Color(153, 153, 153));
        cbBuscar3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cbBuscar3.setForeground(new java.awt.Color(255, 255, 255));
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

        hisTable.setBackground(new java.awt.Color(255, 153, 51));
        hisTable.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        hisTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Paciente", "Fecha de Atención"
            }
        ));
        hisTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hisTable.setGridColor(new java.awt.Color(0, 0, 0));
        hisTable.setRowHeight(35);
        hisTable.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane6.setViewportView(hisTable);

        clockDigital5.setBackground(new java.awt.Color(0, 102, 255));
        clockDigital5.setForeground(new java.awt.Color(0, 102, 102));
        clockDigital5.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N

        calMes2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setText("HISTORIAL DE ATENCIÓN");
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        buttonRound22.setBackground(new java.awt.Color(0, 204, 0));
        buttonRound22.setText("ACTUALIZAR");
        buttonRound22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound22ActionPerformed(evt);
            }
        });

        buttonRound23.setBackground(new java.awt.Color(51, 153, 255));
        buttonRound23.setText("BUSCAR");
        buttonRound23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound23ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 102, 102));
        jLabel46.setText("Paciente:");

        btnImprimir3.setBackground(new java.awt.Color(51, 153, 255));
        btnImprimir3.setText("IMPRIMIR");
        btnImprimir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(buttonRound22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnImprimir3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(buttonRound23, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(calMes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(calAño2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(calFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(clockDigital5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(jLabel42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 701, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addGap(700, 700, 700))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clockDigital5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(calAño2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calMes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(calFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonRound23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRound22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimir3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        tabbedPaneHeader1.addTab("Historial", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(buttonIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tabbedPaneHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        int resp = JOptionPane.showConfirmDialog(null, "¿Estás seguro de Cerrar Sesión?", "¡Cerrando Sesión!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (resp == JOptionPane.YES_OPTION){
                    LogIn  _log = new LogIn();                                                          
                    _log.setVisible(true);                    
                    
                    this.dispose();                               
                }else{                    
                }                                                                                   
    }//GEN-LAST:event_buttonIcon1ActionPerformed

    private void buttonIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIcon2ActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_buttonIcon2ActionPerformed

    private void cbBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscar3ActionPerformed
        desbloquearBusquedaHis();
    }//GEN-LAST:event_cbBuscar3ActionPerformed

    private void cbBuscar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbBuscar3MouseClicked

    }//GEN-LAST:event_cbBuscar3MouseClicked

    private void cbBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscarPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBuscarPacienteActionPerformed

    private void txtTelD4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelD4KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel4 = txtTelD4.getText().length();

        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel4 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();

            if (_tel4 == limite || (c == KeyEvent.VK_TAB)){
                txtHospital.requestFocus();
            } else {
                getToolkit().beep();
            }

        }else{
            if ((_tel4 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelD3.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTelD4KeyTyped

    private void txtTelD3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelD3KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel3 = txtTelD3.getText().length();

        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel3 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();

            if( _tel3 == limite || (c == KeyEvent.VK_TAB)){
                txtTelD4.requestFocus();
                txtTelD4.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }

        }else{
            if ((_tel3 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelD2.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTelD3KeyTyped

    private void txtTelD2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelD2KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel2 = txtTelD2.getText().length();

        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel2 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();

            if( _tel2 == limite || (c == KeyEvent.VK_TAB)){
                txtTelD3.requestFocus();
                txtTelD3.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }

        }else{
            if ((_tel2 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelD1.requestFocus();
            }
        }
    }//GEN-LAST:event_txtTelD2KeyTyped

    private void txtTelD1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelD1KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel1 = txtTelD1.getText().length();

        if( (c < '0' || c > '9')  && (c != KeyEvent.VK_BACK_SPACE) || (_tel1 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();

            if( _tel1 == limite || (c == KeyEvent.VK_TAB)){
                txtTelD2.requestFocus();
                txtTelD2.setText(String.valueOf(c));
            }else {
                getToolkit().beep();
            }

        }
    }//GEN-LAST:event_txtTelD1KeyTyped

    private void txtTelD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelD1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelD1ActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        mostrarPacientes();
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        Conexion _con = new Conexion();
        BusquedaPaciente _dts = new BusquedaPaciente();
        
        String _var = cbBuscar1.getSelectedItem().toString();
        
        
        if (_var.equals("Nombre")){            
            String _bus = cbBuscar2.getSelectedItem().toString();
            
            DefaultTableModel _mod;            
            
            _dts.setNombre(_bus);
            _dts.setEdad(0);
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha("");
            
            _mod = _con.busquedaPaciente(_dts);
            tablePaciente.setModel(_mod);
            int _fila = tablePaciente.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda();
                desbloquearBusqueda();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda();
                mostrarPacientes();
                desbloquearBusqueda();
            }         
        }
        
        if (_var.equals("Edad")){                        
            String _spEdad = spEdad.getValue().toString();
            int _bus = Integer.parseInt(_spEdad);
            
            DefaultTableModel _mod;            
            
            _dts.setNombre("");
            _dts.setEdad(_bus);
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha("");
            
            _mod = _con.busquedaPaciente(_dts);
            tablePaciente.setModel(_mod);
            int _fila = tablePaciente.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda();
                desbloquearBusqueda();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda();
                mostrarPacientes();
                desbloquearBusqueda();
            } 
        }
        
        if (_var.equals("Fecha")){                        
            String _fec = new SimpleDateFormat("dd/MM/yyyy").format(calFecha1.getDate());
            
            DefaultTableModel _mod;            
            
            _dts.setNombre("");
            _dts.setEdad(0);
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha(_fec);
            
            _mod = _con.busquedaPaciente(_dts);
            tablePaciente.setModel(_mod);
            int _fila = tablePaciente.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda();
                desbloquearBusqueda();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda();
                mostrarPacientes();
                desbloquearBusqueda();
            }   
        }
        
        if (_var.equals("Año")){                        
            int _fec = calAño1.getYear();
            
            DefaultTableModel _mod;            
            
            _dts.setNombre("");
            _dts.setEdad(0);
            _dts.setMes(0);
            _dts.setAño(_fec);
            _dts.setFecha("");
            
            _mod = _con.busquedaPaciente(_dts);
            tablePaciente.setModel(_mod);
            int _fila = tablePaciente.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda();
                desbloquearBusqueda();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda();
                mostrarPacientes();
                desbloquearBusqueda();
            }
        }
        
        if (_var.equals("Mes y Año")){                        
            int _mes = calMes1.getMonth() + 1;
            int _año = calAño1.getYear();
            
            System.out.println(_mes + "/" + _año);
            DefaultTableModel _mod;            
            
            _dts.setNombre("");
            _dts.setEdad(0);
            _dts.setMes(_mes);
            _dts.setAño(_año);
            _dts.setFecha("");
            
            _mod = _con.busquedaPaciente(_dts);
            tablePaciente.setModel(_mod);
            int _fila = tablePaciente.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda();
                desbloquearBusqueda();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda();
                mostrarPacientes();
                desbloquearBusqueda();
            }        
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ActualizarPaciente dts = new ActualizarPaciente();
        Conexion _con = new Conexion();
        
        String _nom = txtNombre.getText ();
        String _spEdad = spEdad.getValue().toString();
        int _edad = Integer.parseInt(_spEdad);
        String _spPeso = spPeso.getValue().toString();
        float _peso = Float.parseFloat(_spPeso);
        String _spEstatura = spEstatura.getValue().toString();
        float _estatura = Float.parseFloat(_spEstatura);
        String _nac = new SimpleDateFormat("dd/MM/yyyy").format(calNacimiento.getDate());
        
        int fila = tablePaciente.getSelectedRow();
        
        String _idPac = tablePaciente.getValueAt(fila, 0).toString();
        
        int _id = Integer.parseInt(_idPac);
        
        dts.setId(_id);
        dts.setNombre(_nom);
        dts.setEdad(_edad);
        dts.setPeso(_peso);
        dts.setEstatura(_estatura);
        dts.setFec(_nac);        
        

        if(_nom.isEmpty() || _nac.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos", "Campos requeridos", 2);
        } else{
                        _con.actualizarPaciente(dts);
                        JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "Completado",1);                        
                        mostrarPacientes();     
                        bloquear1();
                        limpiar1();                        
                        cbBuscar2.setModel(_con.getvalues6());
                        cbPaciente1.setModel(_con.getvalues6());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed
    
    
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int _fila = tablePaciente.getSelectedRow();

        if ( _fila >= 0 ){
            txtNombre.setText(tablePaciente.getValueAt(_fila, 1).toString());                                                                                                                                 
            desbloquearModificar1();
        }else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada","Error", 2);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        _x=evt.getX();
        _y=evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        Point _p = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(_p.x-_x,_p.y-_y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char c = evt.getKeyChar();
        
        if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z' ) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE) && !String.valueOf(c).matches("[á-ú']") && (c != KeyEvent.VK_TAB)) {           
                evt.consume();                
            if(c == KeyEvent.VK_TAB){                          
                spEdad.requestFocus();
            }else {
                getToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre2KeyTyped
        char c = evt.getKeyChar();
        
        if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z' ) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE) && !String.valueOf(c).matches("[á-ú']") && (c != KeyEvent.VK_TAB)) {           
                evt.consume();                
            if(c == KeyEvent.VK_TAB){                          
                txtTel1.requestFocus();
            }else {
                getToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtNombre2KeyTyped

    private void txtTel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTel1KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel1 = txtTel1.getText().length();
        
        if( (c < '0' || c > '9')  && (c != KeyEvent.VK_BACK_SPACE) || (_tel1 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();                                    
            
            if( _tel1 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTel2.requestFocus();
                txtTel2.setText(String.valueOf(c));
            }else {
                getToolkit().beep();
            }
            
        }
    }//GEN-LAST:event_txtTel1KeyTyped

    private void txtTel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTel2KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel2 = txtTel2.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel2 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();                                   
            
            if( _tel2 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTel3.requestFocus();
                txtTel3.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }                        
            
        }else{
            if ((_tel2 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTel1.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTel2KeyTyped

    private void txtTel3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTel3KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel3 = txtTel3.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel3 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();            
            
            if( _tel3 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTel4.requestFocus();
                txtTel4.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel3 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTel2.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTel3KeyTyped

    private void txtTel4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTel4KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel4 = txtTel4.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel4 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();
            
            if (_tel4 == limite || (c == KeyEvent.VK_TAB)){
                cbPadecimiento.requestFocus();
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel4 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTel3.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTel4KeyTyped

    private void txtNombre3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre3KeyTyped
        char c = evt.getKeyChar();
        
        if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z' ) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_BACK_SPACE) && !String.valueOf(c).matches("[á-ú']") && (c != KeyEvent.VK_TAB)) {           
                evt.consume();                
            if(c == KeyEvent.VK_TAB){                          
                txtTelT1.requestFocus();
            }else {
                getToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtNombre3KeyTyped

    private void txtTelT1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelT1KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel1 = txtTelT1.getText().length();
        
        if( (c < '0' || c > '9')  && (c != KeyEvent.VK_BACK_SPACE) || (_tel1 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();                                    
            
            if( _tel1 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelT2.requestFocus();
                txtTelT2.setText(String.valueOf(c));
            }else {
                getToolkit().beep();
            }
            
        }
    }//GEN-LAST:event_txtTelT1KeyTyped

    private void txtTelT2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelT2KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel2 = txtTelT2.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel2 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();                                   
            
            if( _tel2 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelT3.requestFocus();
                txtTelT3.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }                        
            
        }else{
            if ((_tel2 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelT1.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTelT2KeyTyped

    private void txtTelT3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelT3KeyTyped
        char c = evt.getKeyChar();
        int limite = 2;
        int _tel3 = txtTelT3.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel3 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();            
            
            if( _tel3 == limite || (c == KeyEvent.VK_TAB)){                          
                txtTelT4.requestFocus();
                txtTelT4.setText(String.valueOf(c));
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel3 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                txtTelT2.requestFocus();               
            }
        }       
    }//GEN-LAST:event_txtTelT3KeyTyped

    private void txtTelT4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelT4KeyTyped
        char c = evt.getKeyChar();
        int limite = 3;
        int _tel4 = txtTelT4.getText().length();
        
        if( (c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) || (_tel4 == limite) && (c != KeyEvent.VK_TAB)){
            evt.consume();
            
            if (_tel4 == limite || (c == KeyEvent.VK_TAB)){
                cbPadecimiento.requestFocus();
            } else {
                getToolkit().beep();
            }
            
        }else{
            if ((_tel4 == 0) && (c == KeyEvent.VK_BACK_SPACE)){
                btnCompletado1.requestFocus();               
            }
        }        
    }//GEN-LAST:event_txtTelT4KeyTyped

    private void txtNombre4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre4KeyTyped
        
    }//GEN-LAST:event_txtNombre4KeyTyped

    private void txtHospitalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHospitalKeyTyped

    }//GEN-LAST:event_txtHospitalKeyTyped

    private void btnActInaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActInaActionPerformed
        idPaciente dts = new idPaciente();
        Conexion _con = new Conexion();                
        
        int _fila = tablePaciente.getSelectedRow();        
        
        if ( _fila >= 0 ){                 
            txtNombre.setText(tablePaciente.getValueAt(_fila, 1).toString());   
            String[] options = {"Activo", "Inactivo"};
            int resp = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Titulo", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if(resp == 0){        
                    String id = tablePaciente.getValueAt(_fila, 0).toString();
                    int _id = Integer.parseInt(id);
                    
                    dts.setIdPac(_id);
                    _con.borrarPaciente2(dts);                    
                    cbPaciente1.setModel(_con.getvalues6());                    
                    limpiar1();
                    
            }else{
                if(resp == 1){                
                      String id = tablePaciente.getValueAt(_fila, 0).toString();                      
                      int _id = Integer.parseInt(id);
                      
                      dts.setIdPac(_id);
                      _con.borrarPaciente1(dts);
                      cbPaciente1.setModel(_con.getvalues6());                                            
                      limpiar1();                      
                }
            }                                                
        }else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada","Error", 2);            
        }
    }//GEN-LAST:event_btnActInaActionPerformed

    private void btnPaciente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaciente3ActionPerformed
        String link = "https://app-1501208568.000webhostapp.com/";
        
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_btnPaciente3ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        GuardarPaciente dts = new GuardarPaciente();
        Conexion _con = new Conexion();
        
        String _nom = txtNombre.getText ();
        String _spEdad = spEdad.getValue().toString();
        int _edad = Integer.parseInt(_spEdad);
        String _spPeso = spPeso.getValue().toString();
        float _peso = Float.parseFloat(_spPeso);
        String _spEstatura = spEstatura.getValue().toString();
        float _estatura = Float.parseFloat(_spEstatura);
        String _nac = new SimpleDateFormat("dd/MM/yyyy").format(calNacimiento.getDate());
        
        String _rec = txtNombre2.getText();
        
        String _tel1 = txtTel1.getText();
        String _tel2 = txtTel2.getText();
        String _tel3 = txtTel3.getText();
        String _tel4 = txtTel4.getText();
        String _tel = _tel1 + "-" + _tel2 + "-" + _tel3 + "-" + _tel4;
        String _pad = cbPadecimiento.getSelectedItem().toString();
        
        dts.setNombre(_nom);
        dts.setEdad(_edad);
        dts.setPeso(_peso);
        dts.setEstatura(_estatura);
        dts.setNacimiento(_nac);
        dts.setNomTutor(_rec);
        dts.setTel(_tel);
        dts.setPadecimiento(_pad);
        

        if(_nom.isEmpty() || _nac.isEmpty() || _tel1.isEmpty() || _tel2.isEmpty() || _tel3.isEmpty() || _tel4.isEmpty() || _rec.isEmpty()){
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos", "Campos requeridos", 2);
        } else{
                        _con.insertarPaciente(dts);
                        JOptionPane.showMessageDialog(null, "Se ha registrado correctamente", "Completado",1);        
                        bloquear1();
                        limpiar1();                 
                        mostrarPacientes();                                                
                        cbBuscar2.setModel(_con.getvalues6());
                        cbPaciente1.setModel(_con.getvalues6());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoPadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPadActionPerformed
        NuevoPad NP  = new NuevoPad();        
        NP.setVisible(true);
    }//GEN-LAST:event_btnNuevoPadActionPerformed

    private void cbPadecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPadecimientoActionPerformed
            
    }//GEN-LAST:event_cbPadecimientoActionPerformed

    private void cbPadecimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbPadecimientoMouseClicked
        
    }//GEN-LAST:event_cbPadecimientoMouseClicked

    private void cbPadecimientoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbPadecimientoMousePressed
        
    }//GEN-LAST:event_cbPadecimientoMousePressed

    private void cbPadecimientoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbPadecimientoMouseEntered
        cbPad();
    }//GEN-LAST:event_cbPadecimientoMouseEntered

    private void btnAntecedentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAntecedentesActionPerformed
        AntecedentesPac AP = new AntecedentesPac();
        AP.setVisible(true);
    }//GEN-LAST:event_btnAntecedentesActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        desbloquear1();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        bloquear1();
        limpiar1();                 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBuscar1ActionPerformed
        desbloquearBusqueda();
    }//GEN-LAST:event_cbBuscar1ActionPerformed

    private void btnPadecimiento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPadecimiento2ActionPerformed
        Padecimientos Pac = new Padecimientos();
        Pac.setVisible(true);
    }//GEN-LAST:event_btnPadecimiento2ActionPerformed

    private void btnPaciente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaciente1ActionPerformed
        idPaciente dts = new idPaciente();
        Conexion _con = new Conexion();
        Padecimientos _pad = new Padecimientos();
        
        
        int _pac = cbPaciente1.getItemAt(cbPaciente1.getSelectedIndex()).getId();        
        String string = cbPaciente1.getItemAt(cbPaciente1.getSelectedIndex()).getPaciente();
        _pad._idEnviado = _pac;
        _pad._enviado = string;
        dts.setIdPac(_pac);
        
        ArrayList<String> listaRec = new ArrayList<String>();
        ArrayList<String> listaDoc = new ArrayList<String>();
        ArrayList<String> listaAnt = new ArrayList<String>();
        ArrayList<String> listaPul = new ArrayList<String>();
        
        listaRec = _con.mostrarReceptor(dts);
        listaDoc = _con.mostrarDoc(dts);
        listaAnt = _con.mostrarAnt(dts);
        listaPul = _con.mostrarPulsera(dts);
        
        txtNombre3.setText(listaRec.get(0));
        String _tel = listaRec.get(1);
        
        String _tel1 = _tel.substring(0,3);
        String _tel2 = _tel.substring(4,6);
        String _tel3 = _tel.substring(7,9);
        String _tel4 = _tel.substring(10,13);

        txtTelT1.setText(_tel1);
        txtTelT2.setText(_tel2);
        txtTelT3.setText(_tel3);
        txtTelT4.setText(_tel4);
        
        desbloquearInfoTutor();
        
        txtNombre4.setText(listaDoc.get(0));
        String _telD = listaDoc.get(1);
        
        String _telD1 = _telD.substring(0,3);
        String _telD2 = _telD.substring(4,7);
        String _telD3 = _telD.substring(8,10);
        String _telD4 = _telD.substring(11,13);

        txtTelD1.setText(_telD1);
        txtTelD2.setText(_telD2);
        txtTelD3.setText(_telD3);
        txtTelD4.setText(_telD4);
        
        txtUsuario.setText(listaDoc.get(2));
        txtHospital.setText(listaDoc.get(3));
        
        
        txtAnt1.setText(listaAnt.get(0));
        txtAnt3.setText(listaAnt.get(1));
        txtAnt2.setText(listaAnt.get(2));        
        
        desbloquearInfoAnte();
        
        lblPulsera.setText("#" + listaPul.get(0));
        
        bloquearBusquedaInfo();
    }//GEN-LAST:event_btnPaciente1ActionPerformed

    private void btnMostrarMQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarMQActionPerformed
         idPaciente dts = new idPaciente();
         Conexion _con = new Conexion();
         
         int _pac = cbPaciente1.getItemAt(cbPaciente1.getSelectedIndex()).getId();         
        
         dts.setIdPac(_pac);
         
         ArrayList<String> listaAnt = new ArrayList<String>();
         listaAnt = _con.mostrarAnt(dts);
         
         
         JOptionPane.showMessageDialog(null, "Tipo de Operación: " + listaAnt.get(3) + 
                 "\n Fecha: " + listaAnt.get(4), "Antecedentes Médico Quirúrgicos",1);
         
         
    }//GEN-LAST:event_btnMostrarMQActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        desbloquearTutor();
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        desbloquearInfoTutor();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void btnCancelar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar4ActionPerformed
        bloquearInfo();
        iniciarInfo();
        limpiarInfo();
    }//GEN-LAST:event_btnCancelar4ActionPerformed

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        desbloquearAnte();
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void btnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar3ActionPerformed
        desbloquearInfoAnte();
    }//GEN-LAST:event_btnCancelar3ActionPerformed

    private void btnCompletado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletado1ActionPerformed
         ActualizarReceptor dts = new ActualizarReceptor();
         Conexion _con = new Conexion();
         
         int _pac = cbPaciente1.getItemAt(cbPaciente1.getSelectedIndex()).getId();         
         String _nom = txtNombre3.getText();
         String _tel1 = txtTelT1.getText();
         String _tel2 = txtTelT2.getText();
         String _tel3 = txtTelT3.getText();
         String _tel4 = txtTelT4.getText();                                
         String _tel = _tel1 + "-" + _tel2 + "-" + _tel3 + "-" + _tel4;
         
         dts.setIdPac(_pac);
         dts.setNombre(_nom);
         dts.setTel(_tel);
         
         _con.actReceptor(dts);
         desbloquearInfoTutor();
    }//GEN-LAST:event_btnCompletado1ActionPerformed

    private void btnCompletado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletado2ActionPerformed
        ActualizarAntec dts = new ActualizarAntec();
        MedicoQuirurgico dts2 = new MedicoQuirurgico();
        Conexion _con = new Conexion();
        
        int _pac = cbPaciente1.getItemAt(cbPaciente1.getSelectedIndex()).getId();         
        String ant1 = txtAnt1.getText();
        String ant2 = txtAnt3.getText();
        String ant3 = txtAnt2.getText();
        String _fec = new SimpleDateFormat("dd/MM/yyyy").format(calOperacion.getDate());
        String _tipo = cbMQ.getSelectedItem().toString();
        
        dts.setIdPac(_pac);
        dts.setAnt1(ant1);
        dts.setAnt2(ant2);
        dts.setAnt3(ant3);
        
        dts2.setIdPac(_pac);
        dts2.setOpe(_tipo);
        dts2.setFecha(_fec);
        
        _con.actAntec(dts);
        _con.agregarMQ(dts2);
        desbloquearInfoAnte();
    }//GEN-LAST:event_btnCompletado2ActionPerformed

    private void cbMQMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMQMouseEntered
        
    }//GEN-LAST:event_cbMQMouseEntered

    private void cbMQMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMQMouseClicked
        
    }//GEN-LAST:event_cbMQMouseClicked

    private void cbMQMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMQMouseExited
        
    }//GEN-LAST:event_cbMQMouseExited

    private void cbMQMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMQMousePressed
        
    }//GEN-LAST:event_cbMQMousePressed

    private void cbMQMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMQMouseReleased
        
    }//GEN-LAST:event_cbMQMouseReleased

    private void cbMQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMQActionPerformed
        
    }//GEN-LAST:event_cbMQActionPerformed

    private void btnBuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar4ActionPerformed
        try{
            Conexion _con = new Conexion();
            idPaciente dts = new idPaciente();

            int _pac = cbBuscarPaciente.getItemAt(cbBuscarPaciente.getSelectedIndex()).getId();                         
            
            dts.setIdPac(_pac);
            
            DefaultTableModel _mod;            
            _mod = _con.BuscarMonitoreo(dts);
            
            tableMonitoreo.setModel(_mod);   
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }//GEN-LAST:event_btnBuscar4ActionPerformed

    private void buttonRound22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound22ActionPerformed
        mostrarHistorial();
    }//GEN-LAST:event_buttonRound22ActionPerformed

    private void buttonRound23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound23ActionPerformed
        Conexion _con = new Conexion();
        BusquedaHistorial _dts = new BusquedaHistorial();
        
        String _var = cbBuscar3.getSelectedItem().toString();
        
        
        if (_var.equals("Paciente")){            
            String _bus = cbPaciente2.getSelectedItem().toString();
            
            DefaultTableModel _mod;            
            
            _dts.setNombre(_bus);            
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha("");
            
            _mod = _con.busHistorialDoc(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();            
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda2();
                desbloquearBusquedaHis();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda2();
                mostrarHistorial();
                desbloquearBusquedaHis();
            }         
        }                
        
        if (_var.equals("Fecha")){                        
            String _fec = new SimpleDateFormat("dd/MM/yyyy").format(calFecha2.getDate());
            
            DefaultTableModel _mod;            
            
            _dts.setNombre("");            
            _dts.setMes(0);
            _dts.setAño(0);
            _dts.setFecha(_fec);
            
            _mod = _con.busHistorialDoc(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();            
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda2();
                desbloquearBusquedaHis();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda2();
                mostrarHistorial();
                desbloquearBusquedaHis();
            }   
        }
        
        if (_var.equals("Año")){                        
            int _fec = calAño2.getYear();
            
            DefaultTableModel _mod;            
            
            _dts.setNombre("");            
            _dts.setMes(0);
            _dts.setAño(_fec);
            _dts.setFecha("");
            
            _mod = _con.busHistorialDoc(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();
            System.out.println(_fila);
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda2();
                desbloquearBusquedaHis();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda2();
                mostrarHistorial();
                desbloquearBusquedaHis();
            }
        }
        
        if (_var.equals("Mes y Año")){                        
            int _mes = calMes2.getMonth() + 1;
            int _año = calAño2.getYear();
                        
            DefaultTableModel _mod;            
            
            _dts.setNombre("");            
            _dts.setMes(_mes);
            _dts.setAño(_año);
            _dts.setFecha("");
            
            _mod = _con.busHistorialDoc(_dts);
            hisTable.setModel(_mod);
            int _fila = hisTable.getRowCount();            
            
            if(_fila > 0){
                JOptionPane.showMessageDialog(null, "Busqueda realizada", "Completado",1);                    
                limpiarBusqueda2();
                desbloquearBusquedaHis();
            }else{
                JOptionPane.showMessageDialog(null, "No hay ningún resultado de la búsqueda","Error", 2);                
                limpiarBusqueda2();
                mostrarHistorial();
                desbloquearBusquedaHis();
            }        
        }
    }//GEN-LAST:event_buttonRound23ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Hilo()).start();        
    }//GEN-LAST:event_formWindowOpened

    private void cbPaciente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaciente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPaciente1ActionPerformed

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        try {
            MessageFormat header = new MessageFormat("Control de Pacientes"); //encabezado
            MessageFormat footer = new MessageFormat("VITA-DATA 2018");
            tablePaciente.print(JTable.PrintMode.FIT_WIDTH, header, footer); //imprime jtable
        } catch (PrinterException e) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    public static String _pd;
    
    private void btnImprimir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir2ActionPerformed
        Imprimir imp = new Imprimir();
        imp.setVisible(true);
        idPaciente dts = new idPaciente();
        Conexion _con = new Conexion();
         Padecimientos _pad = new Padecimientos();
         
        int _pac = cbPaciente1.getItemAt(cbPaciente1.getSelectedIndex()).getId();         
        
        dts.setIdPac(_pac);
         
        ArrayList<String> listaAnt = new ArrayList<String>();
        listaAnt = _con.mostrarAnt(dts);
        
        String telT = txtTelT1.getText() + "-" + txtTelT2.getText() + "-" + txtTelT3.getText() + "-" + txtTelT4.getText();
        String telD = txtTelD1.getText() + "-" + txtTelD2.getText() + "-" + txtTelD3.getText() + "-" + txtTelD4.getText();
        
        String titulo = "Paciente: " + cbPaciente1.getSelectedItem().toString() + "\n\nPulsera: " + lblPulsera.getText();         
        
        int _fila = _pad.tablePadecimientos.getRowCount();                         
        _pd = "            " + _pad.tablePadecimientos.getValueAt(0, 0).toString() + "\n";                  
        
        for (int i = 1; i < _fila; i++ ){            
             String pd = "            " + _pad.tablePadecimientos.getValueAt(i, 0).toString() + "\n";  
             _pd =_pd+ pd;                         
        }
        
        String info = "\n<<Datos del Tutor>> \n\n" + "Nombre: " + txtNombre3.getText() +"\n" + "Teléfono: " + telT + "\n\n"+ "<<Datos del Doctor>> \n\n" + 
                    "Nombre: " + txtNombre4.getText()+ "\n" + "Teléfono: " + telD + "\n"+ "Hospital: " + txtHospital.getText() + "\n" + "Usuario: " + txtUsuario.getText() + "\n\n" +
                    "<<Antecedentes>> \n\n"+ "Personales Patológicos: " + txtAnt1.getText() + "\n" + "No Patológicos: " + txtAnt2.getText() + "\n" + "Familiares: " + txtAnt3.getText() +
                    "\n" + "Medico Quirúrgico: " +  "\n\n" + "            Tipo de Operación: " + listaAnt.get(3) + "\n" + "            Fecha: " + listaAnt.get(4)
                    + "\n\n <<Padecimientos>> \n\n" + _pd;        
        
        imp._titulo = titulo;
        imp._info = info;        
    }//GEN-LAST:event_btnImprimir2ActionPerformed

    private void btnImprimir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir3ActionPerformed
        try {            
            MessageFormat header = new MessageFormat("Historial de citas"); //encabezado
            MessageFormat footer = new MessageFormat("VITA-DATA 2018");            
            
            hisTable.print(JTable.PrintMode.FIT_WIDTH, header, footer); //imprime jtable
        } catch (PrinterException e) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnImprimir3ActionPerformed

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
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Doctor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonRound btnActIna;
    private org.edisoncor.gui.button.ButtonRound btnActualizar;
    private org.edisoncor.gui.button.ButtonRound btnAgregar1;
    private org.edisoncor.gui.button.ButtonRound btnAntecedentes;
    private org.edisoncor.gui.button.ButtonRound btnBuscar1;
    private org.edisoncor.gui.button.ButtonRound btnBuscar4;
    private org.edisoncor.gui.button.ButtonRound btnCancelar;
    private org.edisoncor.gui.button.ButtonRound btnCancelar2;
    private org.edisoncor.gui.button.ButtonRound btnCancelar3;
    private org.edisoncor.gui.button.ButtonRound btnCancelar4;
    private org.edisoncor.gui.button.ButtonRound btnCompletado1;
    private org.edisoncor.gui.button.ButtonRound btnCompletado2;
    private org.edisoncor.gui.button.ButtonRound btnEditar1;
    private org.edisoncor.gui.button.ButtonRound btnGuardar;
    private org.edisoncor.gui.button.ButtonRound btnImprimir1;
    private org.edisoncor.gui.button.ButtonRound btnImprimir2;
    private org.edisoncor.gui.button.ButtonRound btnImprimir3;
    private org.edisoncor.gui.button.ButtonRound btnModificar;
    private org.edisoncor.gui.button.ButtonRound btnMostrar;
    private org.edisoncor.gui.button.ButtonRound btnMostrarMQ;
    private org.edisoncor.gui.button.ButtonRound btnNuevo;
    private org.edisoncor.gui.button.ButtonRound btnNuevoPad;
    private org.edisoncor.gui.button.ButtonRound btnPaciente1;
    private org.edisoncor.gui.button.ButtonRound btnPaciente3;
    private org.edisoncor.gui.button.ButtonRound btnPadecimiento2;
    private org.edisoncor.gui.button.ButtonIcon buttonIcon1;
    private org.edisoncor.gui.button.ButtonIcon buttonIcon2;
    private org.edisoncor.gui.button.ButtonRound buttonRound22;
    private org.edisoncor.gui.button.ButtonRound buttonRound23;
    private com.toedter.calendar.JYearChooser calAño1;
    private com.toedter.calendar.JYearChooser calAño2;
    private com.toedter.calendar.JDateChooser calFecha1;
    private com.toedter.calendar.JDateChooser calFecha2;
    private com.toedter.calendar.JMonthChooser calMes1;
    private com.toedter.calendar.JMonthChooser calMes2;
    private com.toedter.calendar.JDateChooser calNacimiento;
    private com.toedter.calendar.JDateChooser calOperacion;
    private javax.swing.JComboBox<String> cbBuscar1;
    private javax.swing.JComboBox<String> cbBuscar2;
    private javax.swing.JComboBox<String> cbBuscar3;
    private javax.swing.JComboBox<Paciente> cbBuscarPaciente;
    public static javax.swing.JComboBox<String> cbMQ;
    public static javax.swing.JComboBox<Paciente> cbPaciente1;
    public static javax.swing.JComboBox<Paciente> cbPaciente2;
    public static javax.swing.JComboBox<String> cbPadecimiento;
    private org.edisoncor.gui.varios.ClockDigital clockDigital2;
    private org.edisoncor.gui.varios.ClockDigital clockDigital3;
    private org.edisoncor.gui.varios.ClockDigital clockDigital4;
    private org.edisoncor.gui.varios.ClockDigital clockDigital5;
    private javax.swing.JTable hisTable;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblPulsera;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    private org.edisoncor.gui.panel.PanelImage panelImage6;
    private javax.swing.JSpinner spEdad;
    private javax.swing.JSpinner spEdad1;
    private javax.swing.JSpinner spEstatura;
    private javax.swing.JSpinner spPeso;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader tabbedPaneHeader1;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 tabbedSelector21;
    private javax.swing.JTable tableMonitoreo;
    public static javax.swing.JTable tablePaciente;
    private javax.swing.JTextArea txtAnt1;
    private javax.swing.JTextArea txtAnt2;
    private javax.swing.JTextArea txtAnt3;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtHospital;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre2;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre3;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtNombre4;
    private javax.swing.JTextField txtTel1;
    private javax.swing.JTextField txtTel2;
    private javax.swing.JTextField txtTel3;
    private javax.swing.JTextField txtTel4;
    private javax.swing.JTextField txtTelD1;
    private javax.swing.JTextField txtTelD2;
    private javax.swing.JTextField txtTelD3;
    private javax.swing.JTextField txtTelD4;
    private javax.swing.JTextField txtTelT1;
    private javax.swing.JTextField txtTelT2;
    private javax.swing.JTextField txtTelT3;
    private javax.swing.JTextField txtTelT4;
    private org.edisoncor.gui.textField.TextFieldRoundBackground txtUsuario;
    // End of variables declaration//GEN-END:variables
}
