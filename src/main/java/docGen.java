import org.checkerframework.checker.formatter.qual.InvalidFormat;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.*;
import org.docx4j.openpackaging.exceptions.*;
import org.docx4j.openpackaging.packages.*;
import org.docx4j.openpackaging.parts.WordprocessingML.*;
import org.docx4j.wml.*;
import java.io.*;

public class docGen {

    public static final ObjectFactory fabObjetos = Context.getWmlObjectFactory();

    private static void addTextToParagraph(P paragraph, String text) {
        R run = fabObjetos.createR();
        Text textElement = fabObjetos.createText();
        textElement.setValue(text);
        run.getContent().add(textElement);
        paragraph.getContent().add(run);
    }

    public static void main(String args[]){

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
