package Io;

import Model.SelectResultRow;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que gestiona el acceso a la base de datos
 * @author veron
 */
public class DBAccess {
    private Connection con ;
    private String server, port, sid, user, pw;
    private ResultSet resSet;
    private DatabaseMetaData dbMeta;
    private ResultSetMetaData rsMeta;
    
    /**
     * Constructor
     * @param server String con el nombre del servidor
     * @param port String con el nombre de puerto
     * @param sid String con el nombre de SID
     * @param user String con el usuario
     * @param pw String con la contraseña
     */
    public DBAccess(String server, String port, String sid, String user, String pw) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //cargamos Driver
            this.server = server;
            this.port = port;
            this.sid = sid;
            this.user = user;
            this.pw = pw;
        } catch (ClassNotFoundException ex) {}
        
    }
    
    /**
     * Funcion para hacer la conexion con la base de datos
     * @return true si la conexion a sido exitosa
     */
    public boolean connect(){
        boolean res=true; 
        try{
            con = DriverManager.getConnection (
                    "jdbc:oracle:thin:@"+ server.trim()+":"+ port.trim()+":"+
                            sid.trim(), user.trim(), pw.trim());
            dbMeta = con.getMetaData();
        } catch (SQLException ex) { res = false;}
        return res;
    }
    
    /**
     * Funcion para obtener la lista de tablas
     * @return la lista de tablas o null si ha habido un error
     */
    public ArrayList<String> getTableList(){
        ArrayList<String> res = new ArrayList<String>();
        try {
            //preguntamos todas las tablas del usuario
            resSet = dbMeta.getTables(null, user, "%", new String[]{"TABLE"});
            while (resSet.next()) { //las añadimos a  lista que vamos a retornar
                res.add(resSet.getString("TABLE_NAME"));
            }
        } catch (SQLException ex) {res=null; }
        return res;
    }
    
    /**
     * Funcion que ejecuta un Select y devuelve una lista de registros
     * @param select select a ejecutar 
     * @return Lista de registros que ha retornado la base de datos
     */
    public ArrayList<SelectResultRow> executeSelect(String select){
        ArrayList<SelectResultRow> res = new ArrayList<SelectResultRow>();
        Statement stm = null;
        String columnName = "";
        SelectResultRow.restart(); //reiniciamos los datos de los registros
        try {
            stm = con.createStatement();//creamos statement
            resSet = stm.executeQuery(select); //lanzamos la consulta
            rsMeta = resSet.getMetaData(); //recogemos los metadatos del resultset
            while(resSet.next()){ //nos paseamos por el resultset
                //formamos la matriz de resultados de la consulta
                ArrayList<String> row = new ArrayList<String>();
                for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
                    row.add(auxGetColumnContent(i));
                }
                res.add(new SelectResultRow(row));
            }
            
            //guardamos la lista de nombre de la consulta, que luego nos va bien
            for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
                SelectResultRow.addColmnName(rsMeta.getColumnName(i));
            }
            if(stm!=null){ stm.close(); }
        } catch (SQLException ex) {res = null;  //si algo sale mal retornamos null
        }finally{if(stm!=null){try{stm.close();}catch(SQLException ex){}}}//liberamos recursos
        return res;
    }
    
    /**
     * Funcion auxiliar para recoger el contenido de un resultset. El resultset
     * contiene los resultados de una consulta
     * @param id identificador de columna
     * @return contenido de una celda de un resultado de una consulta
     */
    private String auxGetColumnContent(int id){
        String res = null;
        try {
            res = resSet.getString(id);//intentamos pillar el string
            if (res == null) { //si no se puede, itentamos ver si es un objeto
                Object obj = resSet.getObject(id);
                if(obj != null){
                    //la informacion de los objetos se pierde por el camino
                    res = "sqlObject";
                }
            }
        } catch (SQLException ex) { res="ERROR"; } //controlamos los errores
        
        //si todavía no hemos consequido recuperar la celda
        if (res == null) { res = "null"; } 
        return res;
    }
    
    /**
     * funcion para obtener la lista de tablas de la db
     * @param table nombre de la tabla
     * @return la lista de tablas de la db
     */
    public ArrayList<String> getTableColumns(String table){
        ArrayList<String> res = new ArrayList<String>();
        try {
            resSet = dbMeta.getColumns(null, user, table, "%");
            while(resSet.next()){
                res.add(resSet.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {}
        return res;
    }
    
    /**
     *  Funcion para obtener el tipo de una columna
     * @param table
     * @param column
     * @return tipo de la clase Types
     */
    public int getColumnType(String table, String column){
        int res = 0;
        try {
            resSet = dbMeta.getColumns(null, user, table, column);
            resSet.next();
            res= resSet.getInt("DATA_TYPE");
        } catch (SQLException ex) {
        } finally { //liberamos recursos
            if (resSet !=null) {  
                try { resSet.close(); } catch (SQLException ex) {}
            }
        }
        return res;
    }
    
    /**
     * Funcion para cerrar la conexion con la base de datos
     */
    public void closeConnection(){
        try { if(con != null && !con.isClosed()){ con.close(); }
        } catch (SQLException ex) {
        }  finally { //liberamos recursos
            if (resSet !=null) {  
                try { resSet.close(); } catch (SQLException ex) {}
            }
        }
    }
}
