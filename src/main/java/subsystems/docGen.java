package subsystems;

import org.docx4j.UnitsOfMeasurement;
import org.docx4j.dml.wordprocessingDrawing.*;
import org.docx4j.openpackaging.exceptions.*;
import org.docx4j.openpackaging.packages.*;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.*;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.*;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;


public class docGen {

    //Global variables
    public static final org.docx4j.wml.ObjectFactory fabObjetos = new org.docx4j.wml.ObjectFactory();

    //Back-end Functions
    private static void agregarBr(P paragraph){
        //Variables de un solo uso
        R textRun = fabObjetos.createR();
        Br lBreak = fabObjetos.createBr();

        textRun.getContent().add(lBreak);

        paragraph.getContent().add(textRun);
    }

    private static TblGridCol crearCol(BigInteger ancho){
        TblGridCol gridCol = fabObjetos.createTblGridCol();

        gridCol.setW(ancho);

        return gridCol;
    }

    private static void agregarLogos(WordprocessingMLPackage packWord, MainDocumentPart parteRaiz){
        String rutaLogoInst = "/iconos/logo_inst_128.png";
        String rutaLogoMin = "/minppe_225x88.png";

        //obtenemos el IS del logo del CEI
        try {InputStream isInst = docGen.class.getResourceAsStream(rutaLogoInst);
            if (isInst == null) {
                System.out.println("Archivo no encontrado: " + rutaLogoInst);
            } else {System.out.println("Recurso encontrado");}
            //obtenemos el IS del logo del MINPPE
            InputStream isMin = docGen.class.getResourceAsStream(rutaLogoMin);
            if (isMin == null) {
                System.out.println("Archivo no encontrado: " + rutaLogoMin);
            } else {System.out.println("Recurso encontrado");}
            //Guardamos ambos streams como arreglos de bytes
            byte[] arrInst = utils.leerISAByteArr(isInst);
            byte[] arrMin = utils.leerISAByteArr(isMin);

            /* DEBUG: Checkeo de arreglos
            int i = arrMin.length;
            int j = 0;
            for (j = 0; j < i; j++){
                System.out.print(arrMin[j]);
            };
            */

            isInst.close();
            isMin.close();

            // Definimos las variables del sistema para insertar los bitmaps al documento
            BinaryPartAbstractImage imgPartInst = BinaryPartAbstractImage.createImagePart(packWord, parteRaiz, arrInst);
            BinaryPartAbstractImage imgPartMin = ImagePngPart.createImagePart(packWord, parteRaiz, arrMin);


            R run1 = fabObjetos.createR();
            R run2 = fabObjetos.createR();
            P paragraph1 = fabObjetos.createP();
            P paragraph2 = fabObjetos.createP();
            Drawing drawing = fabObjetos.createDrawing();
            Drawing drawing2 = fabObjetos.createDrawing();

            /* Las medidas usadas para la imagen usan EMU (English Metric Units), para las que:
                1 pulgada: 914400 EMU
                Creamos las imágenes como objetos Inline.
             */
            int EMUpPulgada = 914400;

            Inline inline1 = imgPartInst.createImageInline(null,null,
                    0,0,false);
            Inline inline2 = imgPartMin.createImageInline(null,null,
                    1,1,false);

            //Creamos tablas para dar formato a las imágenes

            Tbl tabla = fabObjetos.createTbl();
            packWord.getMainDocumentPart().addObject(tabla);

            TblPr propTabla = fabObjetos.createTblPr();
            tabla.setTblPr(propTabla);

            CTBorder marco = fabObjetos.createCTBorder();
            marco.setColor("#FFFFFF");

            TblBorders borders = fabObjetos.createTblBorders();
            borders.setLeft(marco);
            borders.setRight(marco);
            borders.setTop(marco);
            borders.setBottom(marco);

            BigInteger totalWidth = BigInteger.valueOf(UnitsOfMeasurement.inchToTwip(6.35f));

            TblGrid grid = fabObjetos.createTblGrid();
            TblWidth width = fabObjetos.createTblWidth();
            width.setType(TblWidth.TYPE_DXA);
            width.setW(totalWidth);
            propTabla.setTblW(width);
            //CHANGE GRID WIDTH: 6 INCHES
            // añadimos 2 columnas
            grid.getGridCol().add(crearCol((totalWidth.divide(BigInteger.valueOf(2)))));
            grid.getGridCol().add(crearCol((totalWidth.divide(BigInteger.valueOf(2)))));

            tabla.setTblGrid(grid);
            //definiendo tabla
            Tr fila1 = fabObjetos.createTr();
            tabla.getContent().add(fila1);
            Tc cel1 = fabObjetos.createTc();
            Tc cel2 = fabObjetos.createTc();
            fila1.getContent().add(cel1);
            fila1.getContent().add(cel2);

            drawing.getAnchorOrInline().add(inline1);
            run1.getContent().add(drawing);
            paragraph1.getContent().add(run1);

            drawing2.getAnchorOrInline().add(inline2);
            run2.getContent().add(drawing2);
            paragraph2.getContent().add(run2);

            cel1.getContent().add(paragraph1);
            cel2.getContent().add(paragraph2);



            /*

            Añadiendo al encabezado

             */

            //run.getContent().add(drawing);
            //drawing.getAnchorOrInline().add(inline1);
            //run.getContent().add(textElement);


            //run.getContent().add(drawing2);

            System.out.println("Imagenes añadidas con éxito");


        } catch (java.io.IOException e){
            e.printStackTrace();
        } catch (java.lang.Exception e1){
            e1.printStackTrace();
        }

    }


    private static void agregarFirmas(int caso){

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

    private static Hdr crearHeader(){
        //Definimos variables
        Hdr header = fabObjetos.createHdr();
        return header;
    }

    private static void insertarHeader(String text1, String text2, String text3,
                                       String text4, String text5, MainDocumentPart header){
        P paragraph = fabObjetos.createP();


        agregarParrafoCEstilo(paragraph, text1, false,false,1,false,18);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph, text2, false,false,1,false,18);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph, text3, false,false,1,false,18);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph, text4, false,false,1,false,18);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph, text5, false,false,1,false,18);

        header.getContent().add(paragraph);
    }

    private static Ftr crearFooter(String text1, String text2){
        //Definimos variables
        Ftr footer = fabObjetos.createFtr();
        P paragraph = fabObjetos.createP();

        agregarParrafoCEstilo(paragraph, text1, false,false,1,false,18);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph, text2, false,false,1,false,18);

        footer.getContent().add(paragraph);
        return footer;
    }

    private static HeaderPart crearHeaderPart(Hdr header){

            HeaderPart headerPart = null;

            try { headerPart = new HeaderPart();
                return headerPart;
                } catch (InvalidFormatException e){
                e.printStackTrace();
            }
            return headerPart;
        }

    private static void setRelHeader(MainDocumentPart docMain, Hdr header, HeaderPart headerPart){
        Relationship relacion;
        try {

            SectPr propiedades = docMain.getContents().getBody().getSectPr(); //Propiedades de SECCION
            if (propiedades == null) {
                propiedades = fabObjetos.createSectPr();
                docMain.getContents().getBody().setSectPr(propiedades);
            }
            HeaderReference refHeader = fabObjetos.createHeaderReference(); refHeader.setType(HdrFtrRef.DEFAULT);

            headerPart.setJaxbElement(header);
            relacion = docMain.addTargetPart(headerPart);
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

    public static void generarLicenciaMedica(){
        WordprocessingMLPackage packWord = initDoc();

        //Definiciones
        MainDocumentPart docMain = packWord.getMainDocumentPart();

        agregarLogos(packWord,docMain);

        insertarHeader("Ministerio del poder popular para la educación",
                "Distrito escolar 8-B Sector 02","Maturin, Estado Monagas",
                "Código DEA O D02231608, Código Estadístico: 16100",
                "Código Administrativo: 004170601", docMain);

        Ftr footer = crearFooter("Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC",
                "Teléfono: 0291 6436911");

        setRelFooter(docMain, footer);

        P paragraph = fabObjetos.createP();
        agregarParrafoCEstilo(paragraph, """
                    TEXTO:
                    A favor de: {solicitante_obrero}. Sección: {seccion (si aplica)}. C.I.: {cisolicitante}
                    Plantel o dependencia: {nombre_plantel} //C.E.I Arnoldo Gabaldón
                    Lugar: {estado} //Monagas, Municipio: {municipio} //Maturín
                    Distrito: {distrito} //Boquerón Duración de la licencia: {duracíon_solicitud} días, Motivo: {Motivo}
    
                    {firma}   {firma}
                    Docente   Director""",
                true,true,
                1,false, 24);

        docMain.getContent().add(paragraph);

        guardarArchivo(packWord, "pruebafase2.docx");

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
        generarLicenciaMedica();
    }

}
