package View;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *Componente visual personalizado. Es un JTextField que marca en rojo todo el 
 * JTextField cuando el usuario ha introducido algun caracter no numerico.
 * @author veron
 */
public class NumericTextFile extends JTextField{
    private boolean isValid;
    
    /**
     * Constructor que añade directamente un listener para que compruebe 
     * continuamente lo que estamos introduciendo y cambie el color cuando haya 
     * algun texto no numerico
     */
    public NumericTextFile() {
        isValid = true;
        super.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                if (isNumeric(getText())) {
                    setBackground(Color.WHITE);
                    isValid = true;
                }else{
                    setBackground(Color.red);
                    isValid = false;
                }
            }
        });
    }
    
    /**
     * setter de texto. Comprueva que el texto sea numerico al añadir el texto
     * @param text 
     */
    @Override
    public void setText(String text){
        if (isNumeric(getText())) {
            setBackground(Color.WHITE);
            isValid = true;
        }else{
            setBackground(Color.red);
            isValid = false;
        }
        super.setText(text);
    }
    
    /**
     * Funcion auxiliar que hace la comprobacion del contenido de un texto.
     * @param text
     * @return true si todo el texto es numerico, false en caso contrario
     */
    private boolean isNumeric(String text){
        boolean res = true;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i)<'0' || text.charAt(i)>'9') {
                res=false;
                break;
            }
        }
        return res;
    }
    
    /**
     * getter de la varieble is valid
     * @return contenido de la variable isValid
     */
    public boolean isValid(){
        return this.isValid;
    }
}
