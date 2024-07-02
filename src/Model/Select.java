package Model;

import java.util.ArrayList;

/**
 * Esta clase nos ayuda en el proceso de construccion de una consulta select. 
 * La consulta se hará a traves de un Statement normal porque a través de la 
 * interfaz grafica estaremos controlando las sqlInjections. Además no 
 * representa ninguna mejora precompilar ni parametrizar la consulta.
 * @author veron
 */
public class Select {
    private static final String SELECT = "SELECT";
    private ArrayList<String> columnList; 
    private static final String FROM = "\nFROM";
    private String table;
    private static final String WHERE = "\nWHERE"; 
    private ArrayList<String>  whereClauses; 
    
    private String fullSelect;
    
    /**
     * Constructor de la consulta
     * @param table siempre empezamos el select teniendo una tabla
     */
    public Select (String table) {
        this.columnList = new ArrayList <String>();
        this.table = table;
        this.whereClauses = new ArrayList <String>();
        this.fullSelect = "";
    }
    
    /**
     * Funcion para añadir columnas a la lista de columnas
     * @param column 
     */
    public void addColumn(String column){ this.columnList.add(column); }
    
    /**
     * setter de tale
     * @param table 
     */
    public void setTable(String table){ this.table = table; }

    /**
     * setter de columnList
     * @param columnList 
     */
    public void setColumnList(ArrayList<String> columnList) {
        if(columnList !=null && columnList.size()==0){
            columnList = new ArrayList<>();
        }
        this.columnList = columnList;
    }

    /**
     * setter de la lista de wheres
     * @param whereClauses 
     */
    public void setWhereClauses(ArrayList<String> whereClauses) {
        this.whereClauses = whereClauses;
    }

    /**
     * @return tabla sobre la que estamos haciendo la consulta
     */
    public String getTable() { return table; }

    /**
     * @return lista de columnas que pedimos en la consulta
     */
    public ArrayList<String> getColumnList() { return columnList; }
    
    /**
     * Eliminador de columnas de la consulta
     * @param strcolumna a eliminar
     * @return true si ha ido bien
     */
    public boolean removeColumn(String str){ return columnList.remove(str); }
    
    /**
     * Funcion para añadir wheres a la lista de wheres
     * @param whereClause 
     */
    public void addWhereClause(String whereClause){ 
        this.whereClauses.add(whereClause);
    }
    
    /**
     * Funcion para obtener el select actualizado
     * @return 
     */
    public String getFullSelect(){
        updateFullSelect();
        return this.fullSelect;
    }
    
    /**
     * Funcion auxiliar para actualizar el select en el momento en el que se 
     * pide. La consulta se forma paso a paso tal y como la escribiría a mano
     */
    private void updateFullSelect(){
        this.fullSelect = this.SELECT + " ";
        
        if (columnList != null) {
            if(columnList.size()==0){
                this.fullSelect = this.fullSelect + "* ";
            } else {
                this.fullSelect = this.fullSelect + auxCreateStringFromList(columnList);
            }
        } else {
            this.fullSelect = this.fullSelect + "* ";
        }
        this.fullSelect = this.fullSelect + FROM + " " + table;
        if(whereClauses.size()>0){
            this.fullSelect = this.fullSelect + WHERE + " ";
            for (int i = 0; i < whereClauses.size(); i++) {
                this.fullSelect = this.fullSelect + whereClauses.get(i) + " \n";
            }
        }
    }
    
    /**
     * Funcion auxiliar para formar una lista de elementos separados por comas
     * @param list
     * @return 
     */
    private String auxCreateStringFromList(ArrayList <String> list){
        String res = "";
        
        for (int i = 0; i < list.size(); i++) {
            res = res + list.get(i);
            if(i<list.size()-1){ 
                res = res + ",";
            }
            res = res + " ";
        }
        
        return res;
    }
}
