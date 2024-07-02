package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * clase que da dos opciones de idioma al usuario, en inglés y español. 
 * @author veron
 */

public class LanguajeManager {
    private String currentLang;//idioma que vamos a utilizar
    //variable para darle in numero distinto a cada id
    private static int autoIncremental = -1;
    
    //CONSTANTES
    public static final int CONNECT_DIALOG = getId();
    public static final int MAIN_FRAME = getId();
    public static final int PREFERENCES_DIALOG = getId();
    public static final int CON_ERROR_DIALOG = getId();
    
    public static final int ID_MAIN_FRAME_TITLE = getId();
    public static final int ID_MAIN_FRAME_LABEL_TABLE = getId();
    public static final int ID_MAIN_FRAME_LABEL_COLUMN = getId();
    public static final int ID_MAIN_FRAME_LABEL_OPERATOR = getId();
    public static final int ID_MAIN_FRAME_LABEL_VALUE_1 = getId();
    public static final int ID_MAIN_FRAME_LABEL_VALUE_2 = getId();
    public static final int ID_MAIN_FRAME_LABEL_SELECT = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_NOT_USING = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_USING = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_CONDITION = getId();
    public static final int ID_MAIN_FRAME_TABLE_COLUMN_TITLE_OPERATOR = getId();
    public static final int ID_MAIN_FRAME_BTN_EXECUTE = getId();
    public static final int ID_MAIN_FRAME_BTN_EXPORT = getId();
    public static final int ID_MAIN_FRAME_BTN_EXPORT_INFO = getId();
    public static final int ID_MAIN_FRAME_BTN_UPDATE_TABLE_LIST = getId();
    
    public static final int ID_MAIN_FRAME_MSG_ERROR_CON = getId();
    public static final int ID_MAIN_FRAME_MSG_INFO_EMPTY_TABLE_LIST = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_WEIRD_DATA_TYPE= getId();
    public static final int ID_MAIN_FRAME_MSG_J_OP_SELECT_FILE_LOCATION= getId();
    public static final int ID_MAIN_FRAME_MSG_ASK_FILE_NAME = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_FILE_EXIST = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_NO_FILE_NAME = getId();
    
            
    public static final int ID_MAIN_FRAME_MSG_CHANGE_TABLE = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_NO_USING = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_USING = getId();
    public static final int ID_MAIN_FRAME_MSG_SELECT_ERROR = getId();
    public static final int ID_MAIN_FRAME_MSG_NO_RES = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_SELECT_COL_TO_DELETE = getId();
    public static final int ID_MAIN_FRAME_ERREOR_T_FIELD_NUM = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_EXPORT = getId();
    public static final int ID_MAIN_FRAME_MSG_EXP_INFO = getId();
    public static final int ID_MAIN_FRAME_MSG_ERROR_FILE_NAME_EXT = getId();
    
    public static final int ID_CON_DIALOG_TITLE = getId();
    public static final int ID_CON_DIALOG_LABEL_SERVER = getId();
    public static final int ID_CON_DIALOG_LABEL_PORT = getId();
    public static final int ID_CON_DIALOG_LABEL_SID = getId();
    public static final int ID_CON_DIALOG_LABEL_USER = getId();
    public static final int ID_CON_DIALOG_LABEL_PW = getId();
    public static final int ID_CON_DIALOG_BTN_CONNECT = getId();
    
    public static final String LANG_ESPANIOL = "español";
    public static final String LANG_ENGLISH = "English";
    
    /**
     * constructor de el gestor de idiomas. Detecta el idioma del ordenador y 
     * hace la traduccion de los Strings usados en la aplicacion
     */
    public LanguajeManager() {
        //obtenemos el idioma del ordenador
        currentLang = Locale.getDefault().getDisplayLanguage();
    }

    /**
     * constructor del gestor de idiomas a partir de un idioma pasado por parametro
     * @param lang 
     */
    public LanguajeManager(String lang) {
        currentLang= lang;
    }
    
    /**
     * funcion auxiliar para darle un id diferente a cada identificador de 
     * String
     * @return un identificador de String
     */
    private static int getId() {
        autoIncremental++;
        return autoIncremental;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en español
     * @return lista de mensajes en español
     */
    private String getSpanishMsg(int frameId, int id){
        String res = "Msg not found";
        
        if(CONNECT_DIALOG == frameId){
            res = getSpanishConnectionMsg(id);
        }else if(MAIN_FRAME == frameId){
            res = getSpanishMainMsg(id);
        }
        
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en español de la 
     * pantalla de conexion
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getSpanishConnectionMsg(int id){
        String res = "Msg not found";
        
        if(ID_CON_DIALOG_BTN_CONNECT == id){
            res = "Conectar";
        }else if(ID_CON_DIALOG_LABEL_PORT == id){
            res = "Puerto: ";
        }else if(ID_CON_DIALOG_LABEL_PW == id){
            res = "Contraseña: ";
        }else if(ID_CON_DIALOG_LABEL_SERVER == id){
            res = "Servidor: ";
        }else if(ID_CON_DIALOG_LABEL_SID == id){
            res = "SID: ";
        }else if(ID_CON_DIALOG_LABEL_USER == id){
            res = "Usuario: ";
        }else if(ID_CON_DIALOG_TITLE == id){
            res = "Prametros de conexion";
        }
        
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en español de la 
     * pantalla principal
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getSpanishMainMsg(int id){
        String res = "Msg not found";
        if(ID_MAIN_FRAME_TITLE == id){
            res = "Tema 02 Practica 01 AD_METADATA";
        } else if(ID_MAIN_FRAME_BTN_EXECUTE == id){
            res = "Ejecutar";
        } else if(ID_MAIN_FRAME_BTN_EXPORT == id){
            res = "Exportar";
        }  else if(ID_MAIN_FRAME_BTN_EXPORT_INFO == id){
            res = "Información";
        } else if(ID_MAIN_FRAME_LABEL_COLUMN == id){
            res = "Campo: ";
        } else if(ID_MAIN_FRAME_LABEL_OPERATOR == id){
            res = "Operador: ";
        } else if(ID_MAIN_FRAME_LABEL_SELECT == id){
            res = "Sentencia Select: ";
        } else if(ID_MAIN_FRAME_LABEL_TABLE == id){
            res = "Tabla: ";
        } else if(ID_MAIN_FRAME_LABEL_VALUE_1 == id){
            res = "Valor 1: ";
        } else if(ID_MAIN_FRAME_LABEL_VALUE_2 == id){
            res = "Valor 2: ";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_NOT_USING == id){
            res = "Campos";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_USING == id){
            res = "Campos seleccionados";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_CONDITION == id){
            res = "Condición";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_OPERATOR == id){
            res = "Operador";
        } else if (ID_MAIN_FRAME_BTN_UPDATE_TABLE_LIST == id){
            res = "Actualizar lista de tablas";
        } else if (ID_MAIN_FRAME_MSG_ERROR_CON == id){
            res = "No se ha establecido una conexión valida con el "
                    + "servidor";
        } else if (ID_MAIN_FRAME_MSG_INFO_EMPTY_TABLE_LIST == id){
            res = "No hay tablas disponibles aún";
        } else if (ID_MAIN_FRAME_MSG_ERROR_WEIRD_DATA_TYPE == id){
            res = "El tipo de dato de la columna es demasiado extraño, no "
                    + "podemos trabajar con él";
        } else if (ID_MAIN_FRAME_MSG_J_OP_SELECT_FILE_LOCATION == id){
            res = "seleccione la localizacion";
        } else if (ID_MAIN_FRAME_MSG_ASK_FILE_NAME == id){
            res = "introduzca el nombre del archivo";
        } else if (ID_MAIN_FRAME_MSG_ERROR_FILE_EXIST == id){
            res = "El fichero introducido existe";
        } else if (ID_MAIN_FRAME_MSG_ERROR_NO_FILE_NAME == id){
            res = "No se ha introducido ningun nombre";
        } else if (ID_MAIN_FRAME_MSG_CHANGE_TABLE == id){
            res = "Si cambias de tabla perderás toda la cosulta";
        } else if (ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_NO_USING == id){
            res = "No ha seleccionado nada en la tabla Campos";
        } else if (ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_USING == id){
            res = "No ha seleccionado nada en la tabla Campos seleccionados";
        } else if (ID_MAIN_FRAME_MSG_SELECT_ERROR == id){
            res = "Error en la consulta";
        } else if (ID_MAIN_FRAME_MSG_NO_RES == id){
            res = "No hay resultados";
        } else if (ID_MAIN_FRAME_MSG_ERROR_SELECT_COL_TO_DELETE == id){
            res = "Para borrar una columna hay que seleccionar una columna";
        } else if (ID_MAIN_FRAME_ERREOR_T_FIELD_NUM == id){
            res = "Los campos de texto deben contener numeros";
        } else if (ID_MAIN_FRAME_MSG_ERROR_EXPORT == id){
            res = "Error al exportar el fichero ";
        } else if (ID_MAIN_FRAME_MSG_EXP_INFO == id){
            res = "Formatos de los ficheros exportados: "
                + "\n"
                + "\n   Formato XML"
                + "\n"
                + "\n   <Table>"
                + "\n       <Row>"
                + "\n          <ColumnName1>\"info\"</ColumnName1>"
                + "\n           <ColumnName2>\"info\"</ColumnName2>"
                + "\n       </Row>"
                + "\n      <Row>"
                + "\n           <ColumnName1>\"info\"</ColumnName1>"
                + "\n           <ColumnName2>\"info\"</ColumnName2>"
                + "\n       </Row>"
                + "\n   </Table>"
                + "\n"
                + "\n   Formato Serializable (.txt)"
                + "\n"
                + "\n   Fichero (.txt) que contiene un ArrayList<ArrayList>."
                + "\n   Cada elemento del array contiene un ArrayList<String>."
                + "\n   El primer array del ArrayList<ArrayList> contiene los "
                + "\n   nombres de las columnas";
        } else if (ID_MAIN_FRAME_MSG_ERROR_FILE_NAME_EXT == id){
            res = "No introduzca extension";
        } 
        
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en inglés
     * @return lista de mensajes en inglés
     */
    private String getEnglishMsg(int frameId, int id){
        String res = "Msg not found";
        if(CONNECT_DIALOG == frameId){
            res = getEnglishConnectionMsg(id);
        }else if(MAIN_FRAME == frameId){
            res = getEnglishMainMsg(id);
        }
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en inglés de la 
     * pantalla de conexion
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getEnglishConnectionMsg(int id){
        String res = "Msg not found";
        if(ID_CON_DIALOG_BTN_CONNECT == id){
            res = "Connect";
        }else if(ID_CON_DIALOG_LABEL_PORT == id){
            res = "Port: ";
        }else if(ID_CON_DIALOG_LABEL_PW == id){
            res = "Password: ";
        }else if(ID_CON_DIALOG_LABEL_SERVER == id){
            res = "Server: ";
        }else if(ID_CON_DIALOG_LABEL_SID == id){
            res = "SID: ";
        }else if(ID_CON_DIALOG_LABEL_USER == id){
            res = "User: ";
        }else if(ID_CON_DIALOG_TITLE == id){
            res = "Connection Parameters";
        }
        return res;
    }
    
    /**
     * funcion auxiliar para obtener la lista de mensajes en inlges de la 
     * pantalla principal
     * @param id del mensaje
     * @return mensaje correspondiente al id
     */
    private String getEnglishMainMsg(int id){
        String res = "Msg not found";
        if(ID_MAIN_FRAME_TITLE == id){
            res = "Veronica Sanchez Justicia";
        } else if(ID_MAIN_FRAME_BTN_EXECUTE == id){
            res = "Execute";
        } else if(ID_MAIN_FRAME_BTN_EXPORT == id){
            res = "Export";
        }  else if(ID_MAIN_FRAME_BTN_EXPORT_INFO == id){
            res = "Info";
        } else if(ID_MAIN_FRAME_LABEL_COLUMN == id){
            res = "Column: ";
        } else if(ID_MAIN_FRAME_LABEL_OPERATOR == id){
            res = "Operator: ";
        } else if(ID_MAIN_FRAME_LABEL_SELECT == id){
            res = "Select: ";
        } else if(ID_MAIN_FRAME_LABEL_TABLE == id){
            res = "Table: ";
        } else if(ID_MAIN_FRAME_LABEL_VALUE_1 == id){
            res = "Value 1: ";
        } else if(ID_MAIN_FRAME_LABEL_VALUE_2 == id){
            res = "Value 2: ";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_NOT_USING == id){
            res = "Column";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_COLUMN_USING == id){
            res = "Selected Column";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_CONDITION == id){
            res = "Where";
        } else if(ID_MAIN_FRAME_TABLE_COLUMN_TITLE_OPERATOR == id){
            res = "Operator";
        } else if (ID_MAIN_FRAME_BTN_UPDATE_TABLE_LIST == id){
            res = "Update table list";
        } else if (ID_MAIN_FRAME_MSG_ERROR_CON == id){
            res = "Connection error";
        } else if (ID_MAIN_FRAME_MSG_INFO_EMPTY_TABLE_LIST == id){
            res = "The table list is empty";
        } else if (ID_MAIN_FRAME_MSG_ERROR_WEIRD_DATA_TYPE == id){
            res = "Weird column selected data type";
        } else if (ID_MAIN_FRAME_MSG_J_OP_SELECT_FILE_LOCATION == id){
            res = "Select URL";
        } else if (ID_MAIN_FRAME_MSG_ASK_FILE_NAME == id){
            res = "Set file name: ";
        } else if (ID_MAIN_FRAME_MSG_ERROR_FILE_EXIST == id){
            res = "That file already exist";
        } else if (ID_MAIN_FRAME_MSG_ERROR_NO_FILE_NAME == id){
            res = "Error name field was empty";
        } else if (ID_MAIN_FRAME_MSG_CHANGE_TABLE == id){
            res = "This action will delete all the select sentence";
        } else if (ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_NO_USING == id){
            res = "No Column table row selected";
        } else if (ID_MAIN_FRAME_MSG_ERROR_SELE_TABLE_USING == id){
            res = "No Selected Column table row selected";
        } else if (ID_MAIN_FRAME_MSG_SELECT_ERROR == id){
            res = "Select error";
        } else if (ID_MAIN_FRAME_MSG_NO_RES == id){
            res = "No result";
        } else if (ID_MAIN_FRAME_MSG_ERROR_SELECT_COL_TO_DELETE == id){
            res = "Select the row to delete";
        } else if (ID_MAIN_FRAME_ERREOR_T_FIELD_NUM == id){
            res = "Fields must be numeric";
        } else if (ID_MAIN_FRAME_MSG_ERROR_EXPORT == id){
            res = "Export error at ";
        } else if (ID_MAIN_FRAME_MSG_EXP_INFO == id){
            res = " Exported file format:  "
                + "\n"
                + "\n   XML format" 
                + "\n"
                + "\n   <Table>"
                + "\n       <Row>"
                + "\n          <ColumnName1>\"info\"</ColumnName1>"
                + "\n           <ColumnName2>\"info\"</ColumnName2>"
                + "\n       </Row>"
                + "\n      <Row>"
                + "\n           <ColumnName1>\"info\"</ColumnName1>"
                + "\n           <ColumnName2>\"info\"</ColumnName2>"
                + "\n       </Row>"
                + "\n   </Table>"
                + "\n"
                + "\n   Serializable format(.txt)"
                + "\n"
                + "\n   File contains an ArrayList<ArrayList>."
                + "\n   Each array element have an ArrayList<String>."
                + "\n   1º array from ArrayList<ArrayList> contains column "
                + "\n   name list";
        } else if (ID_MAIN_FRAME_MSG_ERROR_FILE_NAME_EXT == id){
            res = "Do not insert extension";
        } 
        
        return res;
    }
    
    /**
     * Funcio que devuelve un mensaje según el identificador introducido por el usuario
     * @param frameId identificador de la pantalla
     * @param id el identificador del mensaje
     * @return Un String con el mensaje requerido o "Msg not found" si se a 
     * pedido un id que no existe.
     */
    public String getMsg(int frameId, int id){
        String res="Msg not found";
        //damos el ingles como opción por defecto
        switch(currentLang){
            case "español":
                res = getSpanishMsg(frameId, id);
                break;
            default:
                res = getEnglishMsg(frameId, id);
        }
        return res;
    }
}