package subsystems;

import java.io.InputStream;
import java.security.*;
import java.util.Properties;
import java.io.*;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base32;

public class utils {
    
    public static final String CONFIG_PATH = "/configuracion.properties";
            
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

    public static void guardarVariable(String nombreVar, String contenido) {
        try {
            Properties props = new Properties();

            // Cargar el archivo existente si existe
            File configFile = new File(CONFIG_PATH);
            if (configFile.exists()) {
                try (FileInputStream in = new FileInputStream(configFile)) {
                    props.load(in);
                }
            }

            // Guardar la nueva clave-valor
            props.setProperty(nombreVar, contenido);

            // Escribir al archivo
            try (FileOutputStream out = new FileOutputStream(configFile)) {
                props.store(out, "Configuración actualizada");
            }

            System.out.println("Variable guardada con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar la variable: " + e.getMessage());
        }
    }

    public static String obtenerVariable(String nombreVar) {
        try {
            Properties props = new Properties();
            File configFile = new File(CONFIG_PATH);

            if (configFile.exists()) {
                try (FileInputStream in = new FileInputStream(configFile)) {
                    props.load(in);
                    return props.getProperty(nombreVar);
                }
            } else {
                System.err.println("Archivo de configuración no encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Error al leer la variable: " + e.getMessage());
        }
        return null;
    }

    //2FA

    /**
     * MÉTODO 1: Para generar la clave secreta una sola vez durante la configuración.
     * @return La clave secreta en formato Base32.
     */


    public static String crearClaveSecreta() {
        SecureRandom random = new SecureRandom();
        byte[] bytesClave = new byte[20]; // 160 bits
        random.nextBytes(bytesClave);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytesClave).replace("=", "");
    }

    /**
     * MÉTODO 2: Para verificar el código que el usuario ingresa al iniciar sesión.
     * @param claveSecretaBase32 La clave secreta del usuario.
     * @return El código de 6 dígitos válido para el momento actual.
     */

    public static String obtenerCodigoActual(String claveSecretaBase32)
            throws NoSuchAlgorithmException, InvalidKeyException {

        byte[] claveDecodificada = new Base32().decode(claveSecretaBase32);
        // Calcula el número de intervalos de 30 segundos desde la época Unix.
        long contadorTiempo = System.currentTimeMillis() / 1000 / 30;
        byte[] bufferTiempo = ByteBuffer.allocate(8).putLong(contadorTiempo).array();

        // Calcula el hash HMAC-SHA1
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(claveDecodificada, "HmacSHA1"));
        byte[] hash = mac.doFinal(bufferTiempo);

        // Convierte el hash a un número de 6 dígitos
        int offset = hash[hash.length - 1] & 0x0F;
        int binary = ((hash[offset] & 0x7F) << 24) |
                ((hash[offset + 1] & 0xFF) << 16) |
                ((hash[offset + 2] & 0xFF) << 8) |
                (hash[offset + 3] & 0xFF);

        long codigoNumerico = binary % 1000000; // Módulo para 6 dígitos

        // Rellena con ceros a la izquierda si es necesario
        return String.format("%06d", codigoNumerico);
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