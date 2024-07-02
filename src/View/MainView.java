package View;

import Controller.MainControler;
import javax.swing.JOptionPane;
import Model.Select;
import Model.SelectResultRow;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFileChooser;

/**
 * Vista principal de la aplicacion
 * @author veron
 */
public class MainView extends javax.swing.JFrame {
    private ConnectionDialog conDialog; //pantalla de conexion
    private MainControler mainCont; //controlador de la app
    private Select select; //consulta
    
    //table models
    private DefaultTableModel dtmRes;
    private DefaultTableModel dtmColumnNotUsing;
    private DefaultTableModel dtmColumnUsing;
    private DefaultTableModel dtmWhereConditions;
    
    //variables auxiliares que nos ayudan a evitar que salten los eventos de 
    //los comboBox cuando se están actualizado
    private static boolean loadingComboBoxTables = true;
    private static boolean updatingTableList= false;
    private static boolean updatingComboBoxColumn = true;
    private static boolean updatingComboBoxOperator = true;
    
    //variales que nos ayudan a hacer la consulta
    private ArrayList<String> allDBTableColumns;
    private ArrayList<SelectResultRow> selectRes;
    private String whereColumnType;
    
    //traductor de la aplicacion
    private static LanguajeManager langMan = new LanguajeManager();
    
    //CONSTANTES PARA REDUCIR EL ESPACIO QUE OCUPAN LOS MENSAJES traducidos
    private static final String MSG_ERROR_CON  = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_CON);
    private static final String MSG_INFO_EMPTY_TABLE_LIST = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_INFO_EMPTY_TABLE_LIST);
    private static final String MSG_ERROR_WEIRD_DATA_TYPE = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_WEIRD_DATA_TYPE);
    private static final String MSG_J_OP_SELECT_FILE_LOCATION = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_J_OP_SELECT_FILE_LOCATION);
    private static final String MSG_ASK_FILE_NAME = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ASK_FILE_NAME);
    private static final String MSG_ERROR_FILE_EXIST = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_FILE_EXIST);
    private static final String MSG_ERROR_NO_FILE_NAME = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_NO_FILE_NAME);
    private static final String MSG_ERROR_FILE_NAME_EXT = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_FILE_NAME_EXT);
    private static final String MSG_CHANGE_TABLE = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_CHANGE_TABLE);
    private static final String MSG_ERROR_SELE_TABLE_NO_USING = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_NO_USING);
    private static final String MSG_ERROR_SELE_TABLE_USING = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_USING);
    private static final String MSG_SELECT_ERROR = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_SELECT_ERROR);
    private static final String MSG_NO_RES = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_NO_RES);
    private static final String MSG_ERROR_SELECT_COL_TO_DELETE = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_SELECT_COL_TO_DELETE);
    private static final String ERREOR_T_FIELD_NUM = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_ERREOR_T_FIELD_NUM);
    private static final String MSG_ERROR_EXPORT = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_ERROR_EXPORT);
    private static final String MSG_EXP_INFO = langMan.getMsg(
            LanguajeManager.MAIN_FRAME, 
            LanguajeManager.ID_MAIN_FRAME_MSG_EXP_INFO);
    
    /**
     * Constructor de la vista principal
     */
    public MainView() {
        //mostramos el dialogo de conexion
        conDialog = new ConnectionDialog(this,true, langMan);
        conDialog.show();
        //recogemos el acceso a la base de datos
        mainCont = new MainControler(conDialog.getDBAccess());
        
        //si la conexion es valida mostramos la vista principal
        if (mainCont.getDba() != null && mainCont.conectDB()){
            initComponents();
            setTitle(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_TITLE));
        }else{//en el caso contrario mostramos un mensaje de error y nos salimos
            myErrorMsg (null, MSG_ERROR_CON);
            System.exit(0);//cierr la app
        }
        
        /*Si hemos llegado hasta aquí, ya se ha ejecutado el initComponents(), 
        con lo cual ya podriamos utilizar los componentes visiales sin miedo a 
        la NullPointerException*/
        loadTableHeader();//ponemos los titulos traducidos a las tablas
        initTableModels();//inicializamos los tablemodels
        
        loadComboBoxTables();//cargamos el combobox de tablas
    }

    /**
     * Funcion auxiliar para inicializar los componentes visuales y darles 
     * atributos por defecto
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxTables = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableColumnNotUsing = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAddColumn = new javax.swing.JButton();
        btnAddAllColumns = new javax.swing.JButton();
        btnRemoveColumn = new javax.swing.JButton();
        btnRemoveAllColumns = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableColumnUsing = new javax.swing.JTable();
        btnUpdateTableComboBox = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxColumn = new javax.swing.JComboBox<>();
        comboBoxOperator = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxOperatorMultiWhere = new javax.swing.JComboBox<>();
        btnAddWhere = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableWhereConditions = new javax.swing.JTable();
        btnRemoveWhere = new javax.swing.JButton();
        tFieldValue1 = new javax.swing.JTextField();
        tFieldValue2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tAreaSelect = new javax.swing.JTextArea();
        btnExecute = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableRes = new javax.swing.JTable();
        btnExport = new javax.swing.JButton();
        btnExportInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_LABEL_TABLE));

        comboBoxTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTablesActionPerformed(evt);
            }
        });

        jScrollPane2.setEnabled(false);

        tableColumnNotUsing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableColumnNotUsing);

        jPanel1.setEnabled(false);

        btnAddColumn.setText(">");
        btnAddColumn.setEnabled(false);
        btnAddColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddColumnActionPerformed(evt);
            }
        });

        btnAddAllColumns.setText(">>");
        btnAddAllColumns.setEnabled(false);
        btnAddAllColumns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAllColumnsActionPerformed(evt);
            }
        });

        btnRemoveColumn.setText("<");
        btnRemoveColumn.setEnabled(false);
        btnRemoveColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveColumnActionPerformed(evt);
            }
        });

        btnRemoveAllColumns.setText("<<");
        btnRemoveAllColumns.setEnabled(false);
        btnRemoveAllColumns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllColumnsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(btnAddAllColumns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoveColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnRemoveAllColumns)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAddColumn)
                .addGap(4, 4, 4)
                .addComponent(btnAddAllColumns)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveColumn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveAllColumns)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jScrollPane1.setEnabled(false);

        tableColumnUsing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableColumnUsing);

        btnUpdateTableComboBox.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_BTN_UPDATE_TABLE_LIST));
        btnUpdateTableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTableComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTables, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateTableComboBox))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(comboBoxTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnUpdateTableComboBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_LABEL_COLUMN) );

        jLabel3.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_LABEL_OPERATOR));

        comboBoxColumn.setEnabled(false);
        comboBoxColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxColumnActionPerformed(evt);
            }
        });

        comboBoxOperator.setEnabled(false);
        comboBoxOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxOperatorActionPerformed(evt);
            }
        });

        jLabel4.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_LABEL_VALUE_1));

        jLabel5.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_LABEL_VALUE_2));

        comboBoxOperatorMultiWhere.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AND", "OR" }));
        comboBoxOperatorMultiWhere.setEnabled(false);

        btnAddWhere.setText("+");
        btnAddWhere.setEnabled(false);
        btnAddWhere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWhereActionPerformed(evt);
            }
        });

        jScrollPane3.setEnabled(false);

        tableWhereConditions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableWhereConditions);

        btnRemoveWhere.setText("-");
        btnRemoveWhere.setEnabled(false);
        btnRemoveWhere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveWhereActionPerformed(evt);
            }
        });

        tFieldValue1.setEnabled(false);

        tFieldValue2.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(comboBoxOperatorMultiWhere, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddWhere))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(comboBoxOperator, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tFieldValue1)
                                    .addComponent(tFieldValue2, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveWhere)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboBoxColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tFieldValue1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxOperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(tFieldValue2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxOperatorMultiWhere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddWhere))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnRemoveWhere)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setEnabled(false);

        jLabel6.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_LABEL_SELECT));

        jScrollPane4.setEnabled(false);

        tAreaSelect.setEditable(false);
        tAreaSelect.setColumns(20);
        tAreaSelect.setRows(5);
        jScrollPane4.setViewportView(tAreaSelect);

        btnExecute.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_BTN_EXECUTE));
        btnExecute.setEnabled(false);
        btnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExecute)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnExecute)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setEnabled(false);

        jScrollPane5.setEnabled(false);

        tableRes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tableRes);

        btnExport.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_BTN_EXPORT));
        btnExport.setEnabled(false);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnExportInfo.setText(langMan.getMsg(LanguajeManager.MAIN_FRAME, LanguajeManager.ID_MAIN_FRAME_BTN_EXPORT_INFO));
        btnExportInfo.setEnabled(false);
        btnExportInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportInfo))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Funcionalidad del boton actualizar la lista de tablas. se actualizan las 
     * listas de las tablas segun lo que haya en la base de datos
     * @param evt 
     */
    private void btnUpdateTableComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTableComboBoxActionPerformed
        String selected = null;
        updatingTableList = true;
        selected = comboBoxTables.getItemAt(comboBoxTables.getSelectedIndex());
        loadComboBoxTables();
        if (rootPaneCheckingEnabled) { 
            comboBoxTables.setSelectedItem(selected);
        }
        updatingTableList = false;
    }//GEN-LAST:event_btnUpdateTableComboBoxActionPerformed

    /**
     * Funcionalidad del evento cerrar la ventana. Cuando se cierra la ventana 
     * se cierra la conexion con la base de datos
     * @param evt 
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        mainCont.closeDBConnection();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Funcionalidad del evento cambio de item seleccionado del combo box de 
     * tablas. se comienza una nueva consulta
     * @param evt 
     */
    private void comboBoxTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTablesActionPerformed
        //si el evento no es una false alarma y el usuario está seguro
        if (!loadingComboBoxTables && !updatingTableList && (select == null || 
                JOptionPane.showConfirmDialog(this, MSG_CHANGE_TABLE, "Info", 
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)) {
            
            //inicializamos el select segun la tabla seleccionada
            select = new Select(comboBoxTables.getItemAt(
                    comboBoxTables.getSelectedIndex()));//a consulta muerta, consulta puesta
            //damos informacion a los resultado de que tabla estaremos utilizando
            SelectResultRow.setTable(select.getTable()); 
            
            //Reiniciamos los componentes visuales
            updatingComboBoxColumn = true;
            comboBoxColumn.removeAllItems();
            cleanTable(dtmRes);
            setEnabledAll(true);//habilitamos todo lo que hay que habilitar
            tAreaSelect.setText("");//vaciamos el text area del select
            //bloqueamos los botones de exportar
            btnExport.setEnabled(false);
            btnExportInfo.setEnabled(false);
            
            //cargamos las listas de columnas
            allDBTableColumns = mainCont.getTableColumns(select.getTable());
            loadTableInfo(dtmColumnNotUsing, false, 
                    allDBTableColumns);
            loadComboBoxColumn(); 
                 
            tAreaSelect.setText(select.getFullSelect());//mostramos el select
        }else if (select != null){ //si el usuario no queria cambiar de tabla
            //le seleccionamos la tabla que quería
            comboBoxTables.setSelectedItem(select.getTable()); 
        }
        
        loadingComboBoxTables = false;
    }//GEN-LAST:event_comboBoxTablesActionPerformed

    /**
     * Funcionalidad del boton añadir columna
     * @param evt 
     */
    private void btnAddColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddColumnActionPerformed
        int selectedRow = tableColumnNotUsing.getSelectedRow();
        if (selectedRow >-1) { //comprobamos que se aya seleccionado algo
            String column = (String) dtmColumnNotUsing.getValueAt(
                    selectedRow, 0);
            //añadimos a la tabla de columnas seleccionadas
            dtmColumnUsing.addRow(new Object[]{column}); 
            select.addColumn(column); //añadimos columna al select
            tAreaSelect.setText(select.getFullSelect()); //mostramos select actualizado
        }else{ 
            myErrorMsg (this, MSG_ERROR_SELE_TABLE_NO_USING);
        }
    }//GEN-LAST:event_btnAddColumnActionPerformed

    /**
     * Funcionalidad del boton añadir todas las columnas
     * @param evt 
     */
    private void btnAddAllColumnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAllColumnsActionPerformed
        //añadimos todas las column a las column que ya teniamos seleccionadas
        ArrayList<String> colList= select.getColumnList();
        colList.addAll(allDBTableColumns);
        select.setColumnList(colList);
        
        tAreaSelect.setText(select.getFullSelect());// mostramos select editado
        //actualizamos la tabla de columnas
        cleanTable(dtmColumnUsing);
        loadTableInfo(dtmColumnUsing, false, select.getColumnList());
    }//GEN-LAST:event_btnAddAllColumnsActionPerformed

    /**
     * Funcionalidad del boton borrar columna seleccionada
     * @param evt 
     */
    private void btnRemoveColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveColumnActionPerformed
        int selectedRow = tableColumnUsing.getSelectedRow();
        if (selectedRow > -1) {//comprobamos que se aya seleccionado algo
            //obtenemos la columna seleccionada
            String column = (String) dtmColumnUsing.getValueAt(selectedRow, 0);
            select.removeColumn(column);//la borramos
            //cargamos toda la tabla de columnas
            loadTableInfo(dtmColumnUsing, true, select.getColumnList());
            tAreaSelect.setText(select.getFullSelect()); //actualizamos el select 
        }else{
            myErrorMsg (this, MSG_ERROR_SELE_TABLE_USING);
        }
    }//GEN-LAST:event_btnRemoveColumnActionPerformed

    /**
     * Funcionalidad del boton borrar todas las columnas seleccionadas
     * @param evt 
     */
    private void btnRemoveAllColumnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllColumnsActionPerformed
        cleanTable(dtmColumnUsing);
        select.setColumnList(new ArrayList<String>());
        tAreaSelect.setText(select.getFullSelect());
    }//GEN-LAST:event_btnRemoveAllColumnsActionPerformed

    /**
     * Funcionalidad del boton ejecutar consulta
     * @param evt 
     */
    private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuteActionPerformed
        selectRes = mainCont.executeSelect(select); //hacemos la consulta
        if(selectRes==null){ //si los resultados son nulos
            myErrorMsg (this, MSG_SELECT_ERROR); //damos mensaje de error
            return; //nos salimos y damos la oportunidad de arreglar la consulta
        }
        
        if (selectRes.size()>0) { //si la tiene resultados
            tableRes = new JTable(); //inicializamos la tabla de resiltados
            //le cambiamos el modelo
            tableRes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] { },
                SelectResultRow.getColumnNames().toArray()));
            //la incrustamos donde la teniamos antes
            jScrollPane5.setViewportView(tableRes); 
            //obtenemos el nuevo modelo de la tabla
            dtmRes = (DefaultTableModel) tableRes.getModel(); 
            //insertamos los datos
            for (int i = 0; i < selectRes.size(); i++) {
                dtmRes.addRow(selectRes.get(i).getRow().toArray());
            }
        }else{ //la consulta no tiene resultados
            myInfoMsg(MSG_NO_RES);
        }
        
        /*cuando la consulta está hecha no se pueden seguir editando consultas, 
        habria que empezar una nueva*/
        setEnabledAll(false);
        tFieldValue1.setText("");
        tFieldValue2.setText("");
        
        //activamos los botones de exportar
        btnExport.setEnabled(true);
        btnExportInfo.setEnabled(true);
    }//GEN-LAST:event_btnExecuteActionPerformed

    /**
     * Funcionalidad del boton borrar where
     * @param evt 
     */
    private void btnRemoveWhereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveWhereActionPerformed
        int selectedRow = tableWhereConditions.getSelectedRow();
        if(selectedRow >- 1){
            //actualizamos la tabla de wheres
            dtmWhereConditions.removeRow(selectedRow);
            //actualizamos el select cuando cambia el contenido de la tabla
            tableRowCountChangedUpdateSelect();
        }else { 
            myErrorMsg (this, MSG_ERROR_SELECT_COL_TO_DELETE);
        }
    }//GEN-LAST:event_btnRemoveWhereActionPerformed

    /**
     * Funcionalidad del boton btnAddWhere
     * @param evt 
     */
    private void btnAddWhereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWhereActionPerformed
        //recogemos los imputs del usuario
        String column = comboBoxColumn.getItemAt(
                comboBoxColumn.getSelectedIndex());
        String operator = comboBoxOperator.getItemAt(
                comboBoxOperator.getSelectedIndex());
        String strCompare = "";
        String fullWhere = column + " " + operator;
        String operatorMultiWhere = comboBoxOperatorMultiWhere.getItemAt(
                comboBoxOperatorMultiWhere.getSelectedIndex());
        
        if (operator!=null) { //si el usuario a formado bien la consulta
            try{
                switch(whereColumnType){
                    case "TEXT" : 
                        //no estoy poniendo el caracter % ni antes ni despues para 
                        //que el usuario realmente pueda introducir lo que quiera
                        strCompare = "'"+tFieldValue1.getText()+"'";
                        fullWhere = fullWhere + " "+strCompare;
                        break;
                    case "NUM" : 
                        //intentamos recoger los numeros
                        strCompare = Integer.parseInt(tFieldValue1.getText())+"";
                        if(operator == "BETWEEN"){  //si el operador el between
                            //formamos la consulta de una forma especial
                            strCompare = strCompare + " AND " 
                                    + Integer.parseInt(tFieldValue2.getText()); 
                            fullWhere = "( " + fullWhere + " " +strCompare + " )";
                        }else { //si el operador es cualquier otro
                            //formamos la consulta tipica para los numeros
                            fullWhere = fullWhere + " "+strCompare;
                        }
                        break;
                }
                //añadimos el where a la tabla de wheres
                dtmWhereConditions.addRow(new Object[]{fullWhere, operatorMultiWhere});
                //actualizamos el select cuando cambia el contenido de la tabla
                tableRowCountChangedUpdateSelect();
            }catch(Exception ex){ //si hay un error mostramos un mensaje
                myErrorMsg (this, ERREOR_T_FIELD_NUM);
            }
        } else { //si el usuario ha formado mal la consulta mostramos un mensage de error
            myErrorMsg (this, "Error al formar el where");
        }
    }//GEN-LAST:event_btnAddWhereActionPerformed
    
    /**
     * Funcionalidad del evento cambio de item seleccionado del combo box  
     * operadores.
     * @param evt 
     */
    private void comboBoxOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxOperatorActionPerformed
        if (!updatingComboBoxOperator) { //si no es una falsa alarma
            if(comboBoxOperator.getItemAt( //si el operador es "BETWEEN"
                    comboBoxOperator.getSelectedIndex())=="BETWEEN"){
                        tFieldValue2.setEnabled(true);//activamos el 2º textfield
            } else { //si el operador no es between
                tFieldValue2.setEnabled(false); //desactivamos el 2º textfield
            }
            
            tFieldValue1.setText("");
            tFieldValue2.setText("");
        }
        updatingComboBoxOperator = false;
    }//GEN-LAST:event_comboBoxOperatorActionPerformed

    /**
     * Funcionalidad del evento cambio de item seleccionado del combo box 
     * columnas
     * @param evt 
     */
    private void comboBoxColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxColumnActionPerformed
        if(!updatingComboBoxColumn){//si no es una falsa alarma
            String column = (String) comboBoxColumn.getSelectedItem();
            whereColumnType = mainCont.getColumnTypeString(select.getTable(), column);

            updatingComboBoxOperator = true;
            //quitamos lo que haya en el combobox de operadores y el lo tFields
            comboBoxOperator.removeAllItems();
            tFieldValue1.setText("");
            tFieldValue2.setText("");
            tFieldValue2.setEnabled(false);

            //actualizamos el combobox de operadores segun el tipo de dato
            //operadores que valen para todos los tipo de dato
            loadComboBoxOperator(column);
        }
        updatingComboBoxColumn = false;
    }//GEN-LAST:event_comboBoxColumnActionPerformed

    /**
     * Funcionalidad del boton Exportar. Exporta los resultados de la consulta a 
     * xml y txt de objetos serializables
     * @param evt 
     */
    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        //declaraciones
        File file = validateExportation();
        boolean isExported = false;
        if(file != null){//si el fichero de exportacion es valido
            //intentamos crear el xml
            isExported = mainCont.exportSelectRes(selectRes, file, 
                    MainControler.ID_MODE_EXPORT_XML);
            if (!isExported) {  //si no se puede, mensaje de error
                myErrorMsg(this, MSG_ERROR_EXPORT + ".xml");
            }
            
            //intentamos crear el de objetos serializbles
            isExported = mainCont.exportSelectRes(selectRes, file, 
                    MainControler.ID_MODE_EXPORT_SERIALIZABLE);
            if (!isExported) {//si no se puede, mensaje de error
                myErrorMsg(this, MSG_ERROR_EXPORT + ".txt");
            }
        }
    }//GEN-LAST:event_btnExportActionPerformed

    /**
     * Funcionalidad del boton informacion de la exportacion. Damos al usuario 
     * informacion de los resultados de la exportacion
     * @param evt 
     */
    private void btnExportInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportInfoActionPerformed
        myInfoMsg(MSG_EXP_INFO);
    }//GEN-LAST:event_btnExportInfoActionPerformed
    
    /**
     * funcion auxiliar para poner los nombres de las columnas de las tablas 
     * traducidos
     */
    private void loadTableHeader(){
        //editamos los titulos de la tabla
        tableColumnNotUsing.getColumn(tableColumnNotUsing.getColumnName(0))
                .setHeaderValue(
                    langMan.getMsg(LanguajeManager.MAIN_FRAME, 
                    LanguajeManager.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_NOT_USING));
        tableColumnNotUsing.updateUI();
        tableColumnUsing.getColumn(tableColumnUsing.getColumnName(0))
                .setHeaderValue(
                    langMan.getMsg(LanguajeManager.MAIN_FRAME, 
                    LanguajeManager.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_USING));
        tableColumnUsing.updateUI();
        tableWhereConditions.getColumn(tableWhereConditions.getColumnName(0))
                .setHeaderValue(
                    langMan.getMsg(LanguajeManager.MAIN_FRAME, 
                    LanguajeManager.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_CONDITION));
        tableWhereConditions.getColumn(tableWhereConditions.getColumnName(1))
                .setHeaderValue(
                    langMan.getMsg(LanguajeManager.MAIN_FRAME, 
                    LanguajeManager.ID_MAIN_FRAME_TABLE_COLUMN_TITLE_OPERATOR));
        tableWhereConditions.updateUI();
    }
    
    /**
     * funcion auxiliar para inicializar los tablemodels de las tablas visuales
     */
    private void initTableModels(){
        dtmRes = (DefaultTableModel) tableRes.getModel();
        dtmColumnNotUsing =(DefaultTableModel) tableColumnNotUsing.getModel();
        dtmColumnUsing  = (DefaultTableModel) tableColumnUsing.getModel();
        dtmWhereConditions  = (DefaultTableModel) tableWhereConditions.getModel();
    }
    
    /**
     * Funcion auxiliar para cargar el combobox de tablas
     */
    private void loadComboBoxTables(){
        ArrayList<String> tableList = mainCont.getDBTableList();
        comboBoxTables.removeAllItems();
        if (tableList.size()==0) {
            /*si no hay tablas disponibles avisamos al usuario pero dejamos que 
            siga utilizando la app. La app tiene un boton de actualizar para 
            que podamos acceder a las tablas correctamente incluso si hubiera 
            una actualizacion en el servidor
            */
            myInfoMsg(MSG_INFO_EMPTY_TABLE_LIST);
        }else{
            for (int i = 0; i < tableList.size(); i++) {
                comboBoxTables.addItem(tableList.get(i));
            }
        }
    }
    
    /**
     * funcion auxiliar para cargar el combo box de columnas
     */
    private void loadComboBoxColumn(){
        updatingComboBoxColumn = true;
        for (int i = 0; i < allDBTableColumns.size(); i++) {
            comboBoxColumn.addItem(allDBTableColumns.get(i));
        }
    }
    
    /**
     * Funcion auxiliar para cargar el combobox de los operadores del where
     * @param column 
     */
    private void loadComboBoxOperator(String column){
        updatingComboBoxOperator = true;
        //operadores que valen para todos los tipos de datos
        comboBoxOperator.addItem("=");
        comboBoxOperator.addItem("<>");
        comboBoxOperator.addItem("!=");//funciona segun la version de la bd

        //operadores de cada tipo de dato haciendo la diviion de Text y num
        switch(whereColumnType){
            case "TEXT" :
            comboBoxOperator.addItem("LIKE");
            break;
            case "NUM" :
            comboBoxOperator.addItem(">");
            comboBoxOperator.addItem("<");
            comboBoxOperator.addItem(">=");
            comboBoxOperator.addItem("<=");
            comboBoxOperator.addItem("BETWEEN");
            break;
            default: //si el tipo de dato no se acepta
            myErrorMsg (this, MSG_ERROR_WEIRD_DATA_TYPE); //mensaje de error
            return;// nos salimos de la funcion
        }
    }
    
    /**
     * Funcion para cambiar la habilitacion de las tablas ColumnNotUsing, 
     * ColumnUsing, WhereConditions; los combobox; los botones AddAllColumns, 
     * AddColumn, AddWhere, Execute, ExportInfo, Export, RemoveAllColumns, 
     * RemoveColumn, RemoveWhere y los textFiles tFieldValue1 y tFieldValue2
     * @param bool 
     */
    private void setEnabledAll(boolean bool){
        //cambiamos la habilitacion de las tablas
        cleanTable(dtmColumnNotUsing);
        cleanTable(dtmColumnUsing);
        cleanTable(dtmWhereConditions);
        
        //cambiamos la habilitacion de los combobox
        updatingComboBoxOperator = true;
        comboBoxOperator.removeAllItems();
        comboBoxOperator.setEnabled(bool);
        comboBoxColumn.setEnabled(bool);
        comboBoxOperatorMultiWhere.setEnabled(bool);
        
        //cambiamos la habilitacion de los botones
        btnAddAllColumns.setEnabled(bool);
        btnAddColumn.setEnabled(bool);
        btnAddWhere.setEnabled(bool);
        btnExecute.setEnabled(bool);
        btnExportInfo.setEnabled(bool);
        btnExport.setEnabled(bool);
        btnRemoveAllColumns.setEnabled(bool);
        btnRemoveColumn.setEnabled(bool);
        btnRemoveWhere.setEnabled(bool);
        
        //cambiamos la habilitacion de los textFiles
        tFieldValue1.setEnabled(bool);
        tFieldValue2.setEnabled(bool);
    }
    
    /**
     * Funcion para aladir informacion a una tabla a partir de una lista de 
     * valores
     * @param dtm modelo de la tabla que vamos a modificar
     * @param clean true si queremos sobreescribir. false si queremos añadir al final
     * @param list lista de valores que vamos a añidir a la tabla
     */
    private void loadTableInfo(DefaultTableModel dtm, boolean clean ,
            ArrayList<String> list){
        if (clean) { cleanTable(dtm);}//limpiamos la tabla
        //recorremos la lista y la vamos colocando en la tabla
        for (int i = 0; i < list.size(); i++) {
            dtm.addRow(new Object[]{list.get(i)});
        }
    }

    /**
     * Funcion auxiliar para vaciar una tabla pasada por parametro
     * @param dtm modelo de la tabla que vamos a vaciar
     */
    private void cleanTable(DefaultTableModel dtm){
        //si la tabla no tien contenido no hay que limpiar
        if (dtm.getRowCount()>0) { 
            int length = dtm.getRowCount();
            //la recorremos y la limpiamos
            for (int i = 0; i < length; i++) {
                dtm.removeRow(0);
            }
        }
    }
    
    
    /**
     * funcion auxiliar que nos ayuda a crear un puntero valido a un fichero y 
     * controla los errores del usuario
     * @param extension la extension con la que vamos a hacer la exportacion
     * @return un puntero File valido
     */
    private File validateExportation(){
        File file = null;
        String fileName;
        
        JFileChooser fileCh=new JFileChooser(); //inicializamos el filechooser
        fileCh.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //filtro
        //preguntamos la localizacionde la exportacion
        fileCh.showDialog(this, MSG_J_OP_SELECT_FILE_LOCATION);
        file = fileCh.getSelectedFile();
        if(file != null){//si se ha dado una localizacion
            //preguntamos el nombre del archivo a exportar
            fileName = JOptionPane.showInputDialog(this, MSG_ASK_FILE_NAME);
            if (fileName !=null) {//si se ha dado un nombre de fichero
                if (fileName.length()>0){ //si se ha escrito algun nombre
                    //no permitimos que se introduzca extension
                    if ( !fileName.contains(".")) { 
                        //creamos el puntero para el fichero
                        file = new File(fileCh.getSelectedFile(), fileName);
                        if (file.exists()) {//si el fichero existe, error
                            myErrorMsg(this, MSG_ERROR_FILE_EXIST);
                            file = null;
                        }
                    } else { //si se introduce una extension, mensaje de error
                        myErrorMsg(this, MSG_ERROR_FILE_NAME_EXT);
                    }
                } else {//si no se ha escrito ningun nombre, mensage de error
                    myErrorMsg(this, MSG_ERROR_NO_FILE_NAME);
                    file = null;
                }
            }
        }
        return file;
    }
    
    /**
     * Funcion auxiliar para actualizar la lista de wheres del select
     */
    private void tableRowCountChangedUpdateSelect(){
        //si cambia el tamaño de la tabla el select estará inicializado y el 
        //boton de borrar where no dará problemas
        btnRemoveWhere.setEnabled(true);
        
        String where = "";
        ArrayList <String> whereList = new ArrayList<String>();
        for (int i = 0; i < tableWhereConditions.getRowCount(); i++) {
            where = (String) tableWhereConditions.getValueAt(i, 0);
            
            //sacamos el operador de MultiWhere
            if(i>0){//el primer where no tiene operador de MultiWhere
               where = (String) tableWhereConditions.getValueAt(i-1, 1) + " "+ where; 
            }
            whereList.add(where); //añadimos los where a la lista de where
        }
        //sustituimos los where del select por los nuevos where
        select.setWhereClauses(whereList); 
        
        //actualizamos los campos de texto
        tAreaSelect.setText(select.getFullSelect());
        tFieldValue1.setText("");
        tFieldValue2.setText("");
    }
    
    /**
     * Funcion auxiliar para mostrar un mensaje informativo mostrado por 
     * parametro
     * @param parent donde vamos a mostrar el mensaje
     * @param msg mensaje de error a mostrar
     */
    public void myErrorMsg (Component parent, String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", 
                JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Funcion auxiliar para mostrar un mensaje informativo mostrado por 
     * parametro
     * @param msg mensaje informativo a mostrar
     */
    public void myInfoMsg (String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAllColumns;
    private javax.swing.JButton btnAddColumn;
    private javax.swing.JButton btnAddWhere;
    private javax.swing.JButton btnExecute;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnExportInfo;
    private javax.swing.JButton btnRemoveAllColumns;
    private javax.swing.JButton btnRemoveColumn;
    private javax.swing.JButton btnRemoveWhere;
    private javax.swing.JButton btnUpdateTableComboBox;
    private javax.swing.JComboBox<String> comboBoxColumn;
    private javax.swing.JComboBox<String> comboBoxOperator;
    private javax.swing.JComboBox<String> comboBoxOperatorMultiWhere;
    private javax.swing.JComboBox<String> comboBoxTables;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea tAreaSelect;
    private javax.swing.JTextField tFieldValue1;
    private javax.swing.JTextField tFieldValue2;
    private javax.swing.JTable tableColumnNotUsing;
    private javax.swing.JTable tableColumnUsing;
    private javax.swing.JTable tableRes;
    private javax.swing.JTable tableWhereConditions;
    // End of variables declaration//GEN-END:variables
}
