package subsystems;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.*;

public class utils {
    

            
    public static byte[] leerISAByteArr(InputStream is){

        byte finalData[];

        try (is) {
            finalData = is.readAllBytes();
            return finalData;
        } catch (java.io.IOException e){
            e.printStackTrace();
        }

        finalData = null;

        return finalData;
    }

    public static String generarHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());

            // Convertir a hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash", e);
        }
    }

}
/* IMPORTANT: Ref. Template
class customCellRenderer implements TableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        return (Component) value;
    }
}
 */