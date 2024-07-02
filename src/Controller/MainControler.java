package Controller;

import Io.DBAccess;
import Io.FileManager;
import Model.Select;
import Model.SelectResultRow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador principal de la aplicacion
 * @author veron
 */
public class MainControler {
    private DBAccess dba;
    
    public static final String ID_MODE_EXPORT_SERIALIZABLE = ".txt";
    public static final String ID_MODE_EXPORT_XML = ".xml";

    /**
     * Constructor del controlador
     * @param dba acceso a la base de datos
     */
    public MainControler(DBAccess dba) { this.dba = dba; }

    /**
     * Geter del acceso a la base de datos
     * @return el acceso a la base de datos
     */
    public DBAccess getDba() { return dba; }
    
    /**
     * Funcion para hacer la conexion con la base de datos
     * @return true si la conexion a sido exitosa
     */
    public boolean conectDB(){ return dba.connect(); }
    
    /**
     * Funcion para cerrar la conexion con la base de datos
     */
    public void closeDBConnection(){ dba.closeConnection(); }
    
    /**
     * Funcion que ejecuta un Select y devuelve una lista de registros
     * @param select select a ejecutar 
     * @return Lista de registros que ha retornado la base de datos
     */
    public ArrayList<SelectResultRow> executeSelect(Select select){
        return dba.executeSelect(select.getFullSelect());
    }
    
    /**
     * Funcion para obtener la lista de tablas
     * @return la lista de tablas o null si ha habido un error
     */
    public ArrayList<String> getDBTableList(){ return dba.getTableList(); }
    
    /**
     * funcion para obtener la lista de tablas de la db
     * @param table nombre de la tabla
     * @return la lista de tablas de la db
     */
    public ArrayList<String> getTableColumns(String table){
        ArrayList<String> res = null;
        res = dba.getTableColumns(table);
        return res;
    }
    
    /**
     *  Funcion para obtener el tipo de una columna. No vamos a trabajar con 
     * los tipos: 
     * ARRAY, BINARY, BIT, BLOB, CLOB, DATALINK, DISTINCT, JAVA_OBJECT,
     * LONGNVARCHAR, LONGVARBINARY, LONGVARCHAR, NCLOB, NULL, OTHER, REF, 
     * REF_CURSOR, ROWID, SQLXML, STRUCT, TIME, TIMESTAMP, 
     * TIMESTAMP_WITH_TIMEZONE, VARBINARY, BOOLEAN 
     * @param table
     * @param column
     * @return tipo generico. tres opciones "NUM", "TEXT" y mensaje de error
     */
    public String getColumnTypeString(String table, String column){
        String res = "";
        //hacemos 2 grupos de tipos: "NUM", "TEXT"
        switch(dba.getColumnType(table, column)){
            case Types.BIGINT : res = "NUM" ; break;
            case Types.CHAR : res = "TEXT" ; break;
            case Types.DATE : res = "NUM"; break;
            case Types.DECIMAL : res = "NUM" ; break;
            case Types.DOUBLE : res ="NUM" ; break;
            case Types.FLOAT : res = "NUM" ; break;
            case Types.INTEGER : res = "NUM"; break;
            case Types.NCHAR : res = "TEXT" ; break;
            case Types.NUMERIC : res = "NUM"; break;
            case Types.NVARCHAR : res = "TEXT"; break;
            case Types.REAL : res = "NUM"; break;
            case Types.SMALLINT : res = "NUM"; break;
            case Types.TINYINT: res = "NUM"; break;
            case Types.VARCHAR : res = "TEXT"; break;
            default: res = "notFound"; 
        }
            return res;
    }
    
    /**
     * Funcion que exporta a un xml o un txt la lista pasada por parametro.
     * @param selectRes lista a exportar
     * @param file File donde vamos a exportar
     * @param mode extension a exportar
     * @return true si todo a salido bien
     */
    public boolean exportSelectRes(ArrayList<SelectResultRow> selectRes, 
            File file, String mode) {
            file = new File(file.getPath() + mode);
        return new FileManager().exportSelectRes(selectRes, file, mode);
    }
}
