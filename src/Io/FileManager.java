package Io;

import static Controller.MainControler.ID_MODE_EXPORT_SERIALIZABLE;
import Model.SelectResultRow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static Controller.MainControler.ID_MODE_EXPORT_XML;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Clase que nos ayuda a hacer los ficheros de exportacion
 * @author veron
 */
public class FileManager {
    
    /**
     * Funcion que exporta a un xml o un txt la lista pasada por parametro. El
     * resultado de la exportacion es el siguiente: 
     * 
     * Formato XML
     * <Table>
     *      <Row> 
     *          <ColumnName1>\"info\"</ColumnName1>
     *          <ColumnName2>\"info\"</ColumnName2>
     *      </Row>
     *      <Row>
     *          <ColumnName1>\"info\"</ColumnName1>
     *          <ColumnName2>\"info\"</ColumnName2>
     *      </Row>
     * </Table>
     * 
     * Formato Serializable (.txt)
     * Fichero (.txt) que contiene un ArrayList<ArrayList>. Cada elemento del 
     * array contiene un ArrayList<String>. El primer array del 
     * ArrayList<ArrayList> contiene los nombres de las columnas.
     * @param selectRes Resultado de un select
     * @param file Ubicacion donde queremos exportar
     * @param mode formato al que queremos exportar
     * @return true si ha salido todo bien
     */
    public boolean exportSelectRes(ArrayList<SelectResultRow> selectRes, 
            File file, String mode){
        boolean res = false;
        if(mode == ID_MODE_EXPORT_XML){//si estamos en modo xml
            res = expXml(selectRes, file); //hacemos la exportacion xml
        }else if (mode == ID_MODE_EXPORT_SERIALIZABLE) {//si estamos en modo txt 
            res = expSrlzble(selectRes, file);//hacemos la exportacion txt
        }
        return res;
    }
    
    /**
     * Funcion que exporta a un .txt a tavésde la serializacion de la lista 
     * pasada por parametro. El resultado de la exportacion es el siguiente: 
     * Fichero (.txt) que contiene un ArrayList<ArrayList>. Cada elemento del 
     * array contiene un ArrayList<String>. El primer array del 
     * ArrayList<ArrayList> contiene los nombres de las columnas
     * @param selectRes Objeto que vamos a serializar
     * @param file Ubicacion del fichero
     * @return true si ha salido todo bien
     */
    private boolean expSrlzble(ArrayList<SelectResultRow> selectRes, File file){
        boolean res = false;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //inicializamos los writers
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            
            //formamos la matriz
            ArrayList<ArrayList<String>> obj = new ArrayList<ArrayList<String>>();
            obj.add(SelectResultRow.getColumnNames());//la primera línea son las columna
            //añadimos los resultados de la consulta
            for (int i = 0; i <  selectRes.size(); i++) {
                obj.add(selectRes.get(i).getRow());
            }
            oos.writeObject(obj);//escrivimos la matriz de una vez
            res = true;//si todo va bien retornamos true
        } catch (IOException ex) { res = false; //si hay error retornamos false
        } finally { //al final liberamos los recursos
            try {
                if (oos!=null) { oos.close(); } 
                if (fos!=null) { fos.close(); }
            } catch (IOException ex) {}
        }
        return res;
    }
    /**
     * Funcion que exporta a un xml o un txt la lista pasada por parametro. El
     * resultado de la exportacion es el siguiente: 
     * 
     * Formato XML
     * <Table>
     *      <Row> 
     *          <ColumnName1>\"info\"</ColumnName1>
     *          <ColumnName2>\"info\"</ColumnName2>
     *      </Row>
     *      <Row>
     *          <ColumnName1>\"info\"</ColumnName1>
     *          <ColumnName2>\"info\"</ColumnName2>
     *      </Row>
     * </Table>
     * @param selectRes Objeto que vamos a serializar
     * @param file Ubicacion del fichero
     * @return true si ha salido todo bien
     */
    private boolean expXml(ArrayList<SelectResultRow> selectRes, File file) {
        boolean res = false;
        Transformer xformer;
        Source source;
        StreamResult result;
        Element root;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<String> row = new ArrayList<String>();
        ArrayList<String> columnNames = new ArrayList<String>();
        
        try {
            //instanciamos el documento que vamos a formar
            Document doc = factory.newDocumentBuilder().newDocument();
            //creamos el nodo raiz
            root = doc.createElement(SelectResultRow.getTable());
            
            //editamos el nodo raiz. recorremos la matriz
            for (int i = 0; i < selectRes.size(); i++) {
                Node rowNode = doc.createElement("Row"); //formamos el nodo registro
                columnNames = SelectResultRow.getColumnNames(); 
                row = selectRes.get(i).getRow();
                for (int j = 0; j < columnNames.size(); j++) {
                    ///ponemos por cada registro y cada columna su valor
                    Node columnn = doc.createElement(columnNames.get(j));
                    columnn.appendChild(doc.createTextNode(row.get(j)));
                    rowNode.appendChild(columnn); //añadimos columnas formados
                }
                root.appendChild(rowNode);//añadimos cada registro al nodo root
            }
            
            doc.appendChild(root);//damos contexto al nodo raiz
            
            //instanciamos el transformer
            xformer = TransformerFactory.newInstance().newTransformer();
            
            //Propiedades del fichero XML de salida
            xformer.setOutputProperty(OutputKeys.METHOD, "xml");
            xformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            
            // Definimos la Entrada y la Salida de la Transformacion
            source = new DOMSource(doc);
            result = new StreamResult(file);

            // Realizamos la Transformación mediante el metodo transform()
            xformer.transform(source, result);
            res = true; //si todo va bien retornamos true
        } catch (ParserConfigurationException ex) { res = false;
        } catch (TransformerConfigurationException ex) {res = false;
        } catch (TransformerException ex) {res = false;
        }
        return res;
    }
}
