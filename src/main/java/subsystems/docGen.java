package subsystems;

import org.checkerframework.checker.formatter.qual.InvalidFormat;
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
    private static void agregarBr(MainDocumentPart parte){
        //Variables de un solo uso
        P paragraph = fabObjetos.createP();
        R textRun = fabObjetos.createR();
        Br lBreak = fabObjetos.createBr();

        textRun.getContent().add(lBreak);

        paragraph.getContent().add(textRun);
        parte.getContent().add(paragraph);
    }

    //Inicializa el documento
    private static WordprocessingMLPackage initDoc(){
        WordprocessingMLPackage packWord = null;
        try {
            packWord = WordprocessingMLPackage.createPackage();
        } catch (Exception e) {
            System.out.println("Inicialización de documento FALLIDA");
            e.printStackTrace();
        }
        return packWord;
    }

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

    private static Ftr crearFooter(String text){
        //Definimos variables
        Ftr footer = fabObjetos.createFtr();
        P paragraph = fabObjetos.createP();

        agregarParrafoCEstilo(paragraph, text, false,false,1,false,18);

        footer.getContent().add(paragraph);
        return footer;
    }

    private static void setRelHeader(MainDocumentPart docMain, Hdr header){
        Relationship relacion;
        try {
            HeaderPart headerpart = new HeaderPart();

            SectPr propiedades = docMain.getContents().getBody().getSectPr(); //Propiedades de SECCION
            if (propiedades == null) {
                propiedades = fabObjetos.createSectPr();
                docMain.getContents().getBody().setSectPr(propiedades);
            }
            HeaderReference refHeader = fabObjetos.createHeaderReference(); refHeader.setType(HdrFtrRef.DEFAULT);

            headerpart.setJaxbElement(header);
            relacion = docMain.addTargetPart(headerpart);
            refHeader.setId(relacion.getId()); // definimos la relacion del header con el documento

            propiedades.getEGHdrFtrReferences().add(refHeader);
            System.out.println("Relación de encabezado establecida EXITOSAMENTE");
        } catch (Docx4JException e){
            e.printStackTrace();
            System.out.println("Creación de relación de encabezado FALLIDA");
        }

    }

    private static void setRelFooter(MainDocumentPart docMain, Ftr footer){
        Relationship relacion;
        try {
            FooterPart footerpart = new FooterPart();

            SectPr propiedades = docMain.getContents().getBody().getSectPr(); //Propiedades de SECCION
            if (propiedades == null) {
                propiedades = fabObjetos.createSectPr();
                docMain.getContents().getBody().setSectPr(propiedades);
            }
            FooterReference refFooter = fabObjetos.createFooterReference(); refFooter.setType(HdrFtrRef.DEFAULT);

            footerpart.setJaxbElement(footer);
            relacion = docMain.addTargetPart(footerpart);
            refFooter.setId(relacion.getId()); // definimos la relacion del header con el documento

            propiedades.getEGHdrFtrReferences().add(refFooter);
            System.out.println("Relación de pie de página establecida EXITOSAMENTE");

        } catch (Docx4JException e){
            e.printStackTrace();
            System.out.println("Creación de relación de pie de página FALLIDA");
        }

    }

    private static void guardarArchivo(WordprocessingMLPackage packWord, String nombreYTipo){
        File outputFile = new File(nombreYTipo);
        try {packWord.save(outputFile);} catch (Docx4JException e) {e.printStackTrace();}
    }

    //Generadores públicos

    public static void generarConstanciaEstudios(){
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

    public static void generarConstanciaConducta(){
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

    public static void generarConstanciaRetiro(){
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

    public static void generarLicenciaMedica(WordprocessingMLPackage doc){
        //Definiciones
        MainDocumentPart docMain = doc.getMainDocumentPart();

        P paragraph = fabObjetos.createP();
        agregarParrafoCEstilo(paragraph, """
                TEXTO:
                A favor de: {solicitante_obrero}. Sección: {seccion (si aplica)}. C.I.: {cisolicitante}
                Plantel o dependencia: {nombre_plantel} //C.E.I Arnoldo Gabaldón
                Lugar: {estado} //Monagas, Municipio: {municipio} //Maturín
                Distrito: {distrito} //Boquerón Duración de la licencia: {duracíon_solicitud} días, Motivo: {Motivo}

                {firma}   {firma}
                Docente   Director
                Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC
                Teléfono: 0291 6436911""",
                true,true,
                1,false, 24);

        docMain.getContent().add(paragraph);

        agregarBr(docMain);

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
        System.out.print("WhenHola");
    }

}
