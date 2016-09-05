/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Henike
 */
public class AtualizaHora {

    public static void start(final JLabel labelHorario) {
        Thread atualizaHora;
        atualizaHora = new Thread(() -> {
            try {
                while (true) {
                    Date date = new Date();
                    StringBuilder data = new StringBuilder();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    labelHorario.setText(data.toString() + sdf.format(date));
                    Thread.sleep(1000);
                    labelHorario.revalidate();
                }
            } catch (InterruptedException ex) {
            }
        });

        atualizaHora.start();
    }

}
