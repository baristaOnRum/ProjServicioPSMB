package subsystems;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class utils {
    

            
    public static byte[] leerISAByteArr(InputStream is){

        byte finalData[];

        try (is) {
            finalData = is.readAllBytes();
            return finalData;
        } catch (java.io.IOException e){
            e.printStackTrace();
        }

        finalData = new byte[0];

        return finalData;

/*
        ByteArrayInputStream buffer = new ByteArrayInputStream(new byte[0]);

        byte[] data = new byte[4096];
        byte[] finalData;
        int eneLeido;

        try {
            while ((eneLeido = is.read(data, 0, data.length)) != -1){
                buffer.readNBytes(data, 0, eneLeido);
            }

            buffer.close();

        } catch (java.io.IOException e){
            System.out.println("Excepción ocurrida en clase: " + e.getClass() + "\nDetalles:");
            e.printStackTrace();
        }

        finalData = buffer.readAllBytes();
        return finalData;
*/

    }

}