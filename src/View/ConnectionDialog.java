package View;

import Io.DBAccess;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Componente visual personalizado que genera un acceso a la base de datos 
 * que introduzca el usuario
 * @author veron
 */
public class ConnectionDialog extends JDialog {
    private javax.swing.JButton btnConnect;
    private javax.swing.JLabel txtServer;
    private javax.swing.JLabel txtPort;
    private javax.swing.JLabel txtSID;
    private javax.swing.JLabel txtUser;
    private javax.swing.JLabel txtPw;
    private View.NumericTextFile tFieldPort;
    private javax.swing.JPasswordField tFieldPw;
    private javax.swing.JTextField tFieldSID;
    private javax.swing.JTextField tFieldServer;
    private javax.swing.JTextField tFieldUser;
    
    private LanguajeManager langMan; //traductor de la app
    private DBAccess dba; //acceso a la base de datos
    
    
    /**
     * constructor de ConnectionDialog
     * @param owner parent component
     * @param modal 
     * @param langMan traductor de la app 
     */
    public ConnectionDialog(Frame owner, boolean modal, LanguajeManager langMan) {
        super(owner,modal);
        this.langMan= langMan; //traductor de la app
        initComponents();
    }
    
    /**
     * inicializacion y edicion de los componentes visuales de este componente
     */
    public void initComponents(){
        setSize(400, 400);
        setTitle(langMan.getMsg(LanguajeManager.CONNECT_DIALOG, LanguajeManager.ID_CON_DIALOG_TITLE));
        
        langMan= new LanguajeManager();
        
        tFieldPw = new javax.swing.JPasswordField();
        txtServer = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        txtPort = new javax.swing.JLabel();
        tFieldServer = new javax.swing.JTextField();
        txtSID = new javax.swing.JLabel();
        txtUser = new javax.swing.JLabel();
        txtPw = new javax.swing.JLabel();
        tFieldSID = new javax.swing.JTextField();
        tFieldUser = new javax.swing.JTextField();
        tFieldPort = new NumericTextFile();

        tFieldPw.setText("MONDIAL");

        txtServer.setText(langMan.getMsg(LanguajeManager.CONNECT_DIALOG,LanguajeManager.ID_CON_DIALOG_LABEL_SERVER));

        btnConnect.setText(langMan.getMsg(LanguajeManager.CONNECT_DIALOG,LanguajeManager.ID_CON_DIALOG_BTN_CONNECT));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        this.addWindowListener(new java.awt.event.WindowListener() {
            @Override
            public void windowOpened(WindowEvent arg0) { }

            @Override
            public void windowClosing(WindowEvent arg0) {
                windowClosingActionPerformed(arg0);
            }

            @Override
            public void windowClosed(WindowEvent arg0) { }
            @Override
            public void windowIconified(WindowEvent arg0) { }
            @Override
            public void windowDeiconified(WindowEvent arg0) { }
            @Override
            public void windowActivated(WindowEvent arg0) { }
            @Override
            public void windowDeactivated(WindowEvent arg0) { }
        });
        
        
        txtPort.setText(langMan.getMsg(LanguajeManager.CONNECT_DIALOG,LanguajeManager.ID_CON_DIALOG_LABEL_PORT));

        tFieldServer.setText("localhost");

        txtSID.setText(langMan.getMsg(LanguajeManager.CONNECT_DIALOG,LanguajeManager.ID_CON_DIALOG_LABEL_SID));

        txtUser.setText(langMan.getMsg(LanguajeManager.CONNECT_DIALOG,LanguajeManager.ID_CON_DIALOG_LABEL_USER));

        txtPw.setText(langMan.getMsg(LanguajeManager.CONNECT_DIALOG,LanguajeManager.ID_CON_DIALOG_LABEL_PW));

        tFieldSID.setText("xe");
        tFieldSID.setEnabled(false);
        
        tFieldUser.setText("MONDIAL");

        tFieldPort.setText("1521");
        
        javax.swing.GroupLayout connectionDialogLayout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(connectionDialogLayout);
        connectionDialogLayout.setHorizontalGroup(
            connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, connectionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(connectionDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConnect))
                    .addGroup(connectionDialogLayout.createSequentialGroup()
                        .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(txtSID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPort, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtServer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tFieldUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(tFieldSID, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tFieldServer)
                            .addComponent(tFieldPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tFieldPw))))
                .addGap(13, 13, 13))
        );
        connectionDialogLayout.setVerticalGroup(
            connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtServer)
                    .addComponent(tFieldServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPort)
                    .addComponent(tFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSID)
                    .addComponent(tFieldSID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser)
                    .addComponent(tFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPw)
                    .addComponent(tFieldPw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConnect)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    /**
     * Funcion para gestionar el evento pulsar el boton de conectar. Cuando le 
     * damos al boton conectar instanciamos el acceso a la base de datos con 
     * los parametros necesarios para establecer una futura conexion a la db
     * @param evt 
     */
    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if(tFieldPort.isValid()){
            String server = tFieldServer.getText().trim();
            String port = tFieldPort.getText().trim();
            String sid = tFieldSID.getText().trim();
            String user = tFieldUser.getText().trim();
            String pw = tFieldPw.getText().trim();

            dba = new DBAccess(server, port, sid, user, pw);
            this.setVisible(false);
        }
    }
    
    /**
     * Funcion para gestionar el evento cerrando ventana. si el usuario le da a
     * cerrar la vantana, cerramos la app
     * @param evt 
     */
    private void windowClosingActionPerformed(java.awt.event.WindowEvent evt) {                                           
        if(dba == null){
            System.exit(0);
        }
    }
    
    /**
     * getter del acceso a la base de datos
     * @return acceso a la base de datos
     */
    public DBAccess getDBAccess(){
        return dba;
    }
}
