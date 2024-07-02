/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 * Funcion para dar forma a cada registro de una tabla con un numero indefinido
 * de columnas
 * @author veron
 */
public class SelectResultRow {
    private static ArrayList<String> colmnNames = new ArrayList<String>();
    private static String Table = "";
    
    private ArrayList<String> row;
    
    /**
     * Constructor de un registro
     * @param row 
     */
    public SelectResultRow(ArrayList<String> row) { this.row = row; }
    
    /**
     * Funcion para añadir nombre de columna a la lista de columnas
     * @param name 
     */
    public static void addColmnName(String name) { colmnNames.add(name); }
    
    /**
     * getter de la lista de nombres de columnas
     * @return lista de nombres de columnas
     */
    public static ArrayList<String> getColumnNames() { return colmnNames; }

    /**
     * getter de cada registro
     * @return registro
     */
    public ArrayList<String> getRow() { return row; }

    /**
     * getter de la lista de nombres de columna
     * @return lista de nombres de colunas
     */
    public static ArrayList<String> getColmnNames() { return colmnNames; }

    /**
     * setter de la lista de los nombres de columnas
     * @param colmnNames 
     */
    public static void setColmnNames(ArrayList<String> colmnNames) {
        SelectResultRow.colmnNames = colmnNames;
    }

    /**
     * getter de la tabla de donde recogemos los registros
     * @return tabla de donde recogemos los registros
     */
    public static String getTable() { return Table; }

    /**
     * setter del nombre de la tabla de donde recogemos los registros
     * @param Table 
     */
    public static void setTable(String Table) { SelectResultRow.Table = Table; }
    
    /**
     * Funcion para reiniciar el array list de nombres de columnas
     */
    public static void restart(){ 
        colmnNames = new ArrayList<String>(); }
}
