package subsystems;

import org.docx4j.jaxb.Context;
import org.docx4j.model.properties.run.Underline;
import org.docx4j.openpackaging.exceptions.*;
import org.docx4j.openpackaging.packages.*;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.*;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.*;

import java.io.File;
import java.math.BigInteger;

public class docGen {
    //Global variables
    public static final ObjectFactory fabObjetos = Context.getWmlObjectFactory();


    //Back-end Functions

    private static void agregarParrafoCEstilo(P paragraph, String text, boolean bold,
                                                   boolean italic,
                                                   int underline, boolean strikethrough, int fSize){
        //Definir variables de función
        HpsMeasure sSz = fabObjetos.createHpsMeasure(); sSz.setVal(BigInteger.valueOf(fSize));
        BooleanDefaultTrue sBold = fabObjetos.createBooleanDefaultTrue(); sBold.setVal(bold);
        BooleanDefaultTrue sStrike = fabObjetos.createBooleanDefaultTrue(); sStrike.setVal(strikethrough);
        BooleanDefaultTrue sItal = fabObjetos.createBooleanDefaultTrue(); sItal.setVal(italic);

        System.out.print("Creando párrafo\n");
        R run = fabObjetos.createR();
        System.out.print("Creando estilo\n");
        RPr style = fabObjetos.createRPr();
        System.out.print("Definiendo estilo\n");
        style.setB(sBold);
        style.setDstrike(sStrike);
        style.setI(sItal);
        style.setSz(sSz);
        U ul = fabObjetos.createU();
        if (underline == 1){
            ul.setVal(UnderlineEnumeration.SINGLE);
            } else if (underline == 2){
                ul.setVal(UnderlineEnumeration.THICK);
            } else {
        ul.setVal(UnderlineEnumeration.NONE);
    }
        style.setU(ul);

        Text textElement = fabObjetos.createText();
        textElement.setValue(text);
        System.out.print("Añadiendo texto.\n");
        run.getContent().add(textElement);
        System.out.print("Aplicando estilo.\n");
        run.setRPr(style);
        System.out.print("Añadiendo párrafo.\n");
        paragraph.getContent().add(run);
    }

    private static void agregarParrafo(P paragraph, String text) {
        System.out.print("Añadiendo párrafo.\n");
        R run = fabObjetos.createR();
        System.out.print("Run creada.\n");
        Text textElement = fabObjetos.createText();
        textElement.setValue(text);
        System.out.print("Text creado.\n");
        run.getContent().add(textElement);
        System.out.print("Text añadido.\n");
        paragraph.getContent().add(run);
        System.out.print("Párrafo añadido exiosamente.\n");
    }

    private static Hdr crearHeader(String text){
        //Definimos variables
        Hdr header = fabObjetos.createHdr();
        P paragraph = fabObjetos.createP();

        agregarParrafoCEstilo(paragraph, text, false,false,1,false,18);


        header.getContent().add(paragraph);
        return header;
    }

    //Generators


    private static void generarConstanciaEstudios(){
        /*TEXTO:
        Quien suscribe, {Directora} (E) del C.E.I "Arnoldo Gabaldón", que funciona dentro de las
        instalaciones del Ministerio del Poder Popular para el Ecosocialismo y Agua,
        Municipio Autónomo Maturín - Estado Monagas, hace constancia que el alumno {estudiante}
        ; portador de la Cédula Escolar Nº: V.-{CE}, nacido el {fecha_nac}, en {lug_nac}; cursó el {grado},
        {grado}, de la etapa Preescolar {etapa}, *Maternal* {nombre_maternal}, en su {fecha?}, hasta el {fecha}
        en esta institución; correspondiente al periodo escolar {periodo_escolar}.

        Constancia que se expide a petición de parte interesada, a los {parte interesada}

        {firma}

        Profa. Cruzmary Yepez
        Directora (E)

        Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC
        Teléfono: 0291 6436911
         */
    }

    private static void generarConstanciaConducta(){
        /*
        TEXTO:
        Quien suscribe, {Directora/Profesora} del Centro de Educación Inicial [(C.E.I)] "Arnoldo Gabaldón", que funciona
        en el Ministerio del Poder Popular para el Ecosocialismo y Agua. Municipio Autónomo Maturín - Estado Monagas,
        hace constar que el (la) alumno (a) {alumno} Portador (a) de la Cédula Escolar Nº: V.-{CE} natural de {lugar_nac/nacionalidad}
        cursó el {grado} Grupo de la etapa preescolar en este plantel, presentando *_BUENA CONDUCTA_*.

        Constancia que se expide de parte interesada a los {dias} del mes {mes} de {año}.

        {firma}   {firma}
        Docente Directora (E)
        Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC
        Teléfono: 0291 6436911
         */
    }

    private static void generarConstanciaRetiro(){
        /*
        TEXTO:
        Quien suscribe, {Directora}, Directora (E) del C.E.I "Arnoldo Gabaldón", que funciona
        en las instalaciones del Ministerio del Poder Popular para el Ecosocialismo y Agua. Municipio Maturín - Estado Monagas,
        hace constar que el (la) alumno (a) {alumno} Portador (a) de la Cédula Escolar Nº: V.-{CE} natural de {lugar_nac}
        cursó el {grado} Grupo de la etapa preescolar en esta institución correspondiente al periodo escolar {periodo_escolar}.
        Es retirado por su representante {nom_representante}. C.I.: {cirepresentante} alegando motivos de {motivo}.

        Constancia que se expide de parte interesada a los {dias} del mes {mes} de {año}.

        {firma}   {firma}
        Docente   Director
        Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC
        Teléfono: 0291 6436911
         */
    }

    private static void generarLicenciaMedica(MainDocumentPart doc){
        P paragraph = fabObjetos.createP();
        agregarParrafoCEstilo(paragraph, """
                Quien suscribe, {Directora}, Directora (E) del C.E.I "Arnoldo Gabaldón", que funciona
                en las instalaciones del Ministerio del Poder Popular para el Ecosocialismo y Agua. Municipio Maturín - Estado Monagas,
                hace constar que el (la) alumno (a) {alumno} Portador (a) de la Cédula Escolar Nº: V.-{CE} natural de {lugar_nac}
                cursó el {grado} Grupo de la etapa preescolar en esta institución correspondiente al periodo escolar {periodo_escolar}.
                Es retirado por su representante {nom_representante}. C.I.: {cirepresentante} alegando motivos de {motivo}.
                
                Constancia que se expide de parte interesada a los {dias} del mes {mes} de {año}.
                
                {firma}   {firma}
                Docente   Director
                Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC
                Teléfono: 0291 6436911""",
                true,true,
                1,false, 24);
        agregarParrafo(paragraph, "\nhola");
        doc.getContent().add(paragraph);

        /*
        TEXTO:
        A favor de: {solicitante_obrero}. Sección: {seccion (si aplica)}. C.I.: {cisolicitante}
        Plantel o dependencia: {nombre_plantel} //C.E.I Arnoldo Gabaldón
        Lugar: {estado} //Monagas, Municipio: {municipio} //Maturín
        Distrito: {distrito} //Boquerón Duración de la licencia: {duracíon_solicitud} días, Motivo: {Motivo}

        Desde {fecha_inic}, hasta {fecha_fin}.

        *_DATOS DEL SUPLENTE_*
        __________________________________________
        |Nombres y Apellidos | Cédula de Identidad|
        |                    |                    |
        |Observación:                             |
        |                                         |
        |_________________________________________|
            {firma}              {firma}
        Docente/Obrero          Suplente

        {firma}
        Director
         */
    }
    public static void main(String args[]){

        //montar documento

        try {
            //Definimos el paquete word
            WordprocessingMLPackage packWord = WordprocessingMLPackage.createPackage();
            //Definimos variables
            Relationship relacion;


            //Definimos la seccion principal del Documento
            MainDocumentPart mainDoc = packWord.getMainDocumentPart();;
            //Añadimos contenido
            generarLicenciaMedica(mainDoc);

            //TODO: Añadimos encabezado y footer

            //Encabezado
            HeaderPart headerDoc = new HeaderPart();
            Hdr header = crearHeader("Socorro");

            headerDoc.setJaxbElement(header);


            //Exportamos un archivo
            File outputFile = new File("textdoc.docx");
            packWord.save(outputFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //crear documento
    }
}
