/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Henike
 */
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

/**
 * Component JPercentField
 *
 * @author Henike
 */
public class JPercentField extends JFormattedTextField {

    private static final long serialVersionUID = -5712106034509737967L;
    private static final SimpleAttributeSet nullAttribute = new SimpleAttributeSet();

    /**
     * Creates a new instance of JPercentField
     */
    public JPercentField() {
        this.setHorizontalAlignment(JTextField.LEFT);
        this.setDocument(new MoneyFieldDocument());
        this.addFocusListener(new MoneyFieldFocusListener());
        this.setText("0.00");
        this.addCaretListener(new CaretListenerImpl());
    }

    private final class MoneyFieldFocusListener extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            selectAll();
        }
    }

    private final class MoneyFieldDocument extends PlainDocument {

        /**
         *
         */
        private static final long serialVersionUID = -3802846632709128803L;

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            String original = getText(0, getLength());

            // Permite apenas digitar até 16 caracteres (9.999.999.999,99)
            //if (original.length() < 16) {
            // Modificado para permitir apenas até 7 caracteres(9999,99)
            if (original.length() < 7) {
                StringBuilder mascarado = new StringBuilder();
                if (a != nullAttribute) {
                    //limpa o campo  
                    remove(-1, getLength());

                    mascarado.append((original + str).replaceAll("[^0-9]", ""));
                    for (int i = 0; i < mascarado.length(); i++) {
                        if (!Character.isDigit(mascarado.charAt(i))) {
                            mascarado.deleteCharAt(i);
                        }
                    }
                    Long number = new Long(mascarado.toString());

                    mascarado.replace(0, mascarado.length(), number.toString());

                    if (mascarado.length() < 3) {
                        if (mascarado.length() == 1) {
                            mascarado.insert(0, "0");
                            mascarado.insert(0, ".");
                            mascarado.insert(0, "0");
                        } else if (mascarado.length() == 2) {
                            mascarado.insert(0, ".");
                            mascarado.insert(0, "0");
                        }
                    } else {
                        mascarado.insert(mascarado.length() - 2, ".");
                    }

                    //if (mascarado.length() > 6) {
                    //mascarado.insert(mascarado.length() - 6, '.');
                    //if (mascarado.length() > 10) {
                    //mascarado.insert(mascarado.length() - 10, '.');
                    //if (mascarado.length() > 14) {
                    //mascarado.insert(mascarado.length() - 14, '.');
                    //}
                    //}
                    //}
                    super.insertString(0, mascarado.toString(), a);
                } else if (original.length() == 0) {
                    super.insertString(0, "0.00", a);
                }
            }
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            if (len == getLength()) {
                super.remove(0, len);
                if (offs != -1) {
                    insertString(0, "", nullAttribute);
                }
            } else {
                String original = getText(0, getLength()).substring(0, offs) + getText(0, getLength()).substring(offs + len);
                super.remove(0, getLength());
                insertString(0, original, null);
            }
        }
    }

    private class CaretListenerImpl implements CaretListener {

        public CaretListenerImpl() {
        }

        @Override
        public void caretUpdate(CaretEvent e) {
            if (e.getDot() != getText().length()) {
                getCaret().setDot(getText().length());
            }
        }
    }
}
