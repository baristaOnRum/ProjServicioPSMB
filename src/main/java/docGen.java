import org.checkerframework.checker.formatter.qual.InvalidFormat;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.*;
import org.docx4j.openpackaging.exceptions.*;
import org.docx4j.openpackaging.packages.*;
import org.docx4j.openpackaging.parts.WordprocessingML.*;
import org.docx4j.wml.*;
import java.io.*;

public class docGen {

    public static void main(String args[]){

        ObjectFactory fabObjetos = Context.getWmlObjectFactory();

        try {
            //Definimos el paquete word
            WordprocessingMLPackage packWord = WordprocessingMLPackage.createPackage();
            //Definimos la seccion principal del Documento
            MainDocumentPart mainDoc = packWord.getMainDocumentPart();
        }
        catch (InvalidFormatException e){
            e.printStackTrace();
        }
    }
}
