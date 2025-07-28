package subsystems;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import org.docx4j.UnitsOfMeasurement;
import org.docx4j.dml.wordprocessingDrawing.*;
import org.docx4j.openpackaging.exceptions.*;
import org.docx4j.openpackaging.packages.*;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.*;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.*;
import org.docx4j.model.table.TblFactory;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.eclipse.persistence.expressions.spatial.SpatialParameters;
import subsystems.individuos.*;


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

    private static void agregarNoBreakHyphen(P paragraph){
        R noEspacioRun = fabObjetos.createR();
        RPr runPr = fabObjetos.createRPr();
        Color color = fabObjetos.createColor();
        color.setVal("#FFFFFF");

        runPr.setColor(color);
        noEspacioRun.setRPr(runPr);

        noEspacioRun.getContent().add(fabObjetos.createRNoBreakHyphen());
        paragraph.getContent().add(noEspacioRun);

    }

    private static void agregarLogos(WordprocessingMLPackage packWord, Part parteRaiz, Hdr header){
        String rutaEncabezado = "/encabezado.png";

        //obtenemos el IS del logo del CEI
        try {InputStream isEnc = docGen.class.getResourceAsStream(rutaEncabezado);
            if (isEnc == null) {
                System.out.println("Archivo no encontrado: " + rutaEncabezado);
            } else {System.out.println("Recurso encontrado");}
            //Guardamos ambos streams como arreglos de bytes
            byte[] arrEnc = utils.leerISAByteArr(isEnc);

            isEnc.close();

            // Definimos las variables del sistema para insertar los bitmaps al documento
            BinaryPartAbstractImage imgPartEnc = BinaryPartAbstractImage.createImagePart(packWord, parteRaiz, arrEnc);

            R run = fabObjetos.createR();
            P paragraph = fabObjetos.createP();
            Drawing drawing = fabObjetos.createDrawing();

            Inline inline = imgPartEnc.createImageInline(null,null, 0,0,false);

            drawing.getAnchorOrInline().add(inline);
            run.getContent().add(drawing);
            paragraph.getContent().add(run);

            header.getContent().add(paragraph);

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

    private static void agregarFirmas(WordprocessingMLPackage packWord, String firma1, String firma2){

        //Creamos la tabla y sus propiedades
        int writableWidthTwips = packWord.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
        int cellWidthTwips = Double.valueOf(Math.floor((writableWidthTwips / 5))).intValue();
        Tbl tabla = TblFactory.createTable(2, 5, cellWidthTwips);
        TblPr tblPr = fabObjetos.createTblPr();
        TblBorders bordersTabla = fabObjetos.createTblBorders();
        CTBorder borderColor = fabObjetos.createCTBorder();

        //Definimos los bordes de la tabla
        borderColor.setColor("FFFFFF");
        bordersTabla.setLeft(borderColor);
        bordersTabla.setRight(borderColor);
        bordersTabla.setTop(borderColor);
        bordersTabla.setBottom(borderColor);

        //Aplicamos
        tblPr.setTblBorders(bordersTabla);
        tabla.setTblPr(tblPr);

        //Damos propiedades a las celdas 2 y 5
        CTBorder borderCelda = fabObjetos.createCTBorder();
        TcPr propCelda;
        TcPr propCelda2;
        TcPrInner.TcBorders bordersTc1;
        TcPrInner.TcBorders bordersTc2;
        P parrafo1 = fabObjetos.createP();
        P parrafo2 = fabObjetos.createP();

        borderCelda.setColor("000000");
        borderCelda.setVal(STBorder.THICK);
        agregarParrafoCEstilo(parrafo1,firma1,true,false,0,false,24,0);
        agregarParrafoCEstilo(parrafo2,firma2,true,false,0,false,24,0);


        //Aplicamos
        Tr fila = (Tr) tabla.getContent().get(1);

        Tc celda1 = (Tc) fila.getContent().get(1);
        Tc celda2 = (Tc) fila.getContent().get(3);

        propCelda = celda1.getTcPr();
        propCelda2 = celda2.getTcPr();
        if (propCelda == null) { propCelda = fabObjetos.createTcPr(); celda1.setTcPr(propCelda);}
        if (propCelda2 == null) { propCelda2 = fabObjetos.createTcPr(); celda2.setTcPr(propCelda2);}

        bordersTc1 = propCelda.getTcBorders();
        bordersTc2 = propCelda2.getTcBorders();
        if (bordersTc1 == null) {bordersTc1 = fabObjetos.createTcPrInnerTcBorders();
            propCelda.setTcBorders(bordersTc1);}
        if (bordersTc2 == null) {bordersTc2 = fabObjetos.createTcPrInnerTcBorders();
            propCelda2.setTcBorders(bordersTc2);}

        bordersTc1.setTop(borderCelda);
        bordersTc2.setTop(borderCelda);

        celda1.getContent().add(parrafo1);
        celda2.getContent().add(parrafo2);


        packWord.getMainDocumentPart().getContent().add(tabla);

//        if (i%2 != 0){
//            TcPr propsCell = fabObjetos.createTcPr();
//
//        }

    }

    private static void agregarParrafoCEstilo(P paragraph, String text, boolean bold,
                                              boolean italic,
                                              int underline, boolean strikethrough, int fSize, int align){
        //Definir variables de función
        HpsMeasure sSz = fabObjetos.createHpsMeasure(); sSz.setVal(BigInteger.valueOf(fSize));
        BooleanDefaultTrue sBold = fabObjetos.createBooleanDefaultTrue(); sBold.setVal(bold);
        BooleanDefaultTrue sStrike = fabObjetos.createBooleanDefaultTrue(); sStrike.setVal(strikethrough);
        BooleanDefaultTrue sItal = fabObjetos.createBooleanDefaultTrue(); sItal.setVal(italic);

        PPr paragraphP = fabObjetos.createPPr();
        Jc jc = fabObjetos.createJc();
        if (align == 0){
            jc.setVal(JcEnumeration.CENTER);
        } else if (align == 1){
            jc.setVal(JcEnumeration.LEFT);
        } else if (align == 2){
            jc.setVal(JcEnumeration.RIGHT);
        } else if (align == 3){
            jc.setVal(JcEnumeration.DISTRIBUTE);
        } else {
            jc.setVal(JcEnumeration.CENTER);
        }
        paragraphP.setJc(jc);

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
        paragraph.setPPr(paragraphP);
        paragraph.getContent().add(run);
    }

    private static Hdr crearHeader(){
        //Definimos variables
        Hdr header = fabObjetos.createHdr();
        return header;
    }

    private static Ftr crearFooter(String text1, String text2){
        //Definimos variables
        Ftr footer = fabObjetos.createFtr();
        P paragraph = fabObjetos.createP();

        agregarParrafoCEstilo(paragraph, text1, false,false,0,false,18,0);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph, text2, false,false,0,false,18,0);

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

    public static void generarInscripcionAlumno(){}

    public static void generarConstanciaEstudios(estudiante estudiante, String directora, String lapso, String finLapso, boolean maternal){

        //Creamos el documento

        WordprocessingMLPackage packWord = initDoc();

        //Definimos relaciones
        MainDocumentPart docMain = packWord.getMainDocumentPart();

        Hdr header = crearHeader();
        HeaderPart headerPart = crearHeaderPart(header);

        setRelHeader(docMain,header,headerPart);

        agregarLogos(packWord,headerPart,header);

        Ftr footer = crearFooter("Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC",
                "Teléfono: 0291 6436911");

        setRelFooter(docMain, footer);

        //Agregamos el título

        P paragraph = fabObjetos.createP();
        P paragraphTitle = fabObjetos.createP();
        agregarParrafoCEstilo(paragraphTitle, "Constancia de Estudios", true, false, 1, false, 48, 0);

        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);

        //Agregamos el texto

        agregarParrafoCEstilo(paragraph, "Quien suscribe, " + directora + ",",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, "Directora del Centro de Educación Inicial (C.E.I) \"Arnoldo Gabaldón\", que funciona" +
                " en el Ministerio del Poder Popular para el Ecosocialismo y Agua. Municipio Autónomo Maturín - Estado Monagas," +
                " hace constar que el (la) alumno (a) ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, estudiante.getNombres() + " " + estudiante.getApellidos(),false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph,", portador (a) de la Cédula Escolar Nº: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + estudiante.getCe() + " ",false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph,", nacido el ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, estudiante.getFechaNac().toString(),false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph,", en ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, estudiante.getLugarNac(),false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,"cursó el Grupo",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, String.valueOf(estudiante.getNivel()),false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);

        if (!maternal){
            agregarParrafoCEstilo(paragraph," de la etapa preescolar en este plantel en su",false,false,0,false,36,1);
        } else{
            agregarParrafoCEstilo(paragraph," de la etapa maternal en este plantel en su",false,false,0,false,36,1);
        }
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, lapso + "___",false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,"Lapso, hasta el",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, finLapso,false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph,", en esta institución; correspondiente al periodo escolar",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, estudiante.getPeriodoCurso() + ".",false,false,1,false,36,1);
        agregarBr(paragraph);
        agregarBr(paragraph);
        LocalDate fecha = LocalDate.now();
        agregarParrafoCEstilo(paragraph, "Constancia que se expide de parte interesada a los " + fecha.getDayOfMonth() + " del mes de " + fecha.getMonth() +
                " de " + fecha.getYear(),false,false,0,false,36,1);
        agregarBr(paragraph);
        agregarBr(paragraph);
        agregarBr(paragraph);

        docMain.getContent().add(paragraphTitle);
        docMain.getContent().add(paragraph);

        agregarFirmas(packWord,"Docente","Director");
        guardarArchivo(packWord,estudiante.getCe() + "Estudios.docx");

            /*TEXTO:
         , hasta el {fecha}
            en esta institución; correspondiente al periodo escolar {periodo_escolar}.

            Constancia que se expide a petición de parte interesada, a los {parte interesada}

            {firma}

            Profa. Cruzmary Yepez
            Directora (E)

            Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC
            Teléfono: 0291 6436911
             */
    }

    public static void generarConstanciaConducta(estudiante estudiante, String directora, boolean maternal){

        //Creamos el documento

        WordprocessingMLPackage packWord = initDoc();

        //Definimos relaciones
        MainDocumentPart docMain = packWord.getMainDocumentPart();

        Hdr header = crearHeader();
        HeaderPart headerPart = crearHeaderPart(header);

        setRelHeader(docMain,header,headerPart);

        agregarLogos(packWord,headerPart,header);

        Ftr footer = crearFooter("Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC",
                "Teléfono: 0291 6436911");

        setRelFooter(docMain, footer);

        //Agregamos el título

        P paragraph = fabObjetos.createP();
        P paragraphTitle = fabObjetos.createP();
        agregarParrafoCEstilo(paragraphTitle, "Constancia de Buena Conducta", true, false, 1, false, 48, 0);

        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);

        //Agregamos el texto

        agregarParrafoCEstilo(paragraph, "Quien suscribe, " + directora + ",",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, "Directora del Centro de Educación Inicial (C.E.I) \"Arnoldo Gabaldón\", que funciona" +
                " en el Ministerio del Poder Popular para el Ecosocialismo y Agua. Municipio Autónomo Maturín - Estado Monagas," +
                " hace constar que el (la) alumno (a) ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, estudiante.getNombres() + " " + estudiante.getApellidos(),false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph,", portador (a) de la Cédula Escolar Nº: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + estudiante.getCe() + " ",false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph,", natural de ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, estudiante.getLugarNac(),false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,"cursó el Grupo",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, String.valueOf(estudiante.getNivel()),false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);

        if (!maternal){
            agregarParrafoCEstilo(paragraph," de la etapa preescolar en este plantel, presentando",false,false,0,false,36,1);
        } else{
            agregarParrafoCEstilo(paragraph," de la etapa maternal en este plantel, presentando",false,false,0,false,36,1);
        }

        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,"BUENA CONDUCTA",false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);

        agregarBr(paragraph);
        agregarBr(paragraph);
        LocalDate fecha = LocalDate.now();
        agregarParrafoCEstilo(paragraph, "Constancia que se expide de parte interesada a los " + fecha.getDayOfMonth() + " del mes de " + fecha.getMonth() +
                " de " + fecha.getYear(),false,false,0,false,36,1);
        agregarBr(paragraph);
        agregarBr(paragraph);
        agregarBr(paragraph);

        docMain.getContent().add(paragraphTitle);
        docMain.getContent().add(paragraph);

        agregarFirmas(packWord,"Docente","Director");
        guardarArchivo(packWord,estudiante.getCe() + "BuenaConducta.docx");

    }

    public static void generarConstanciaRetiro(estudiante estudiante, representante repre_estudiante, String directora){

        //Creamos el documento

        WordprocessingMLPackage packWord = initDoc();

        //Definimos relaciones
        MainDocumentPart docMain = packWord.getMainDocumentPart();

        Hdr header = crearHeader();
        HeaderPart headerPart = crearHeaderPart(header);

        setRelHeader(docMain,header,headerPart);

        agregarLogos(packWord,headerPart,header);

        Ftr footer = crearFooter("Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC",
                "Teléfono: 0291 6436911");

        setRelFooter(docMain, footer);

        //Agregamos el título

        P paragraph = fabObjetos.createP();
        P paragraphTitle = fabObjetos.createP();
        agregarParrafoCEstilo(paragraphTitle, "Constancia de Retiro", true, false, 1, false, 48, 0);

        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);

        //Agregamos el texto

        agregarParrafoCEstilo(paragraph, "Quien suscribe, " + directora,false,false,0,false,36,1);
        agregarParrafoCEstilo(paragraph, ", Directora del C.E.I \"Arnoldo Gabaldón\", que funciona en las instalaciones" +
                " del Ministerio del Poder Popular para el Ecosocialismo y Agua. Municipio Maturín - Estado Monagas," +
                " hace constar que el (la) alumno",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + estudiante.getNombres() + " " + estudiante .getApellidos(),
                false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, "Portador (a) de la Cédula Escolar Nº: V.-",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + estudiante.getCe(), false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, "natural de",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + estudiante.getLugarNac(), false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, "cursó el Grupo",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + estudiante.getNivel(), false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " de la etapa Preescolar____, Maternal____ en esta institución correspondiente al periodo escolar"
                ,false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + estudiante.getPeriodoCursado(), false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph, ". Es retirado por su representante ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + repre_estudiante.getNombres() + " " + repre_estudiante.getApellidos(),
                false,false,1,false,36,1);
        agregarParrafoCEstilo(paragraph, ". C.I.:",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, " " + repre_estudiante.getCi() + ".", false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarBr(paragraph);
        LocalDate fecha = LocalDate.now();
        agregarParrafoCEstilo(paragraph, "Constancia que se expide de parte interesada a los " + fecha.getDayOfMonth() + " del mes de " + fecha.getMonth() +
                " de " + fecha.getYear(),false,false,0,false,36,1);
        agregarBr(paragraph);
        agregarBr(paragraph);
        agregarBr(paragraph);

        docMain.getContent().add(paragraphTitle);
        docMain.getContent().add(paragraph);

        agregarFirmas(packWord,"Docente","Director");
        guardarArchivo(packWord,estudiante.getCe() + "Retiro.docx");

    }

    public static void generarLicenciaMedica(trabajador nomina, String plantel, String municipio, String estado,
                                             String distrito, String motivo, int diasLicencia, LocalDate inicio, LocalDate fin){

        //Creamos el documento

        WordprocessingMLPackage packWord = initDoc();

        //Definimos relaciones
        MainDocumentPart docMain = packWord.getMainDocumentPart();

        Hdr header = crearHeader();
        HeaderPart headerPart = crearHeaderPart(header);

        setRelHeader(docMain,header,headerPart);

        agregarLogos(packWord,headerPart,header);

        Ftr footer = crearFooter("Dirección: Av. Alirio Ugarte Pelayo, sector Ambiente, sede MINEC",
                "Teléfono: 0291 6436911");

        setRelFooter(docMain, footer);

        //Agregamos el título

        P paragraph = fabObjetos.createP();
        P paragraphTitle = fabObjetos.createP();
        agregarParrafoCEstilo(paragraphTitle, "Solicitud de licencia", true, false, 1, false, 48, 0);

        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);
        agregarBr(paragraphTitle);

        //Agregamos el texto

        agregarParrafoCEstilo(paragraph, "A favor de",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph," " + nomina.getNombres() + " " + nomina.getApellidos() + ". ",false,
                false,1,false,36,1);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph,"Sección: _________",false, false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,("C.I: "),false, false,0,false,36,1);;
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph, nomina.getCi() + ". ",false,false,1,false,36,1);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph,  "Plantel o dependencia: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  plantel + ". ",false,false,1,false,36,1);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph,  "Estado: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  estado + ". ",false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  "Municipio: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  municipio + ". ",false,false,1,false,36,1);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph,  "Distrito: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  distrito + ". ",false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  "Duración de la licencia: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  diasLicencia + " días.",false,false,1,false,36,1);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph,  "Motivo: ",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  motivo + ".",false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarBr(paragraph);
        agregarBr(paragraph);
        agregarBr(paragraph);
        agregarParrafoCEstilo(paragraph,  "Desde:",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  inicio + ".",false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  "Hasta:",false,false,0,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarParrafoCEstilo(paragraph,  fin + ".",false,false,1,false,36,1);
        agregarNoBreakHyphen(paragraph);
        agregarBr(paragraph);
        agregarBr(paragraph);
        agregarBr(paragraph);

        docMain.getContent().add(paragraphTitle);
        docMain.getContent().add(paragraph);

        //Creamos la tabla de datos de suplente
        int writableWidthTwips = packWord.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
        int cellWidthTwips = Double.valueOf(Math.floor((writableWidthTwips / 2))).intValue();
        Tbl tabla = TblFactory.createTable(3, 2, cellWidthTwips);
        TcPrInner.TcBorders borderCelda;
        CTBorder borderColor = fabObjetos.createCTBorder();

            //Definimos los bordes de la tabla
        borderColor.setColor("#000000");

        for (int i = 0; i < 2; i++){
            Tr tr = (Tr) tabla.getContent().get(i);
            for (int j = 0; i < 2; i++) {
                TcPr propCelda;
                Tc tc = (Tc) tr.getContent().get(j);

                propCelda = tc.getTcPr();
                if (propCelda == null) {propCelda = fabObjetos.createTcPr(); tc.setTcPr(propCelda);}
                borderCelda = propCelda.getTcBorders();
                if (borderCelda == null) {borderCelda = fabObjetos.createTcPrInnerTcBorders(); propCelda.setTcBorders(borderCelda);}
                borderCelda.setTop(borderColor);
                borderCelda.setBottom(borderColor);
                borderCelda.setRight(borderColor);
                borderCelda.setLeft(borderColor);

            }

        }

            //Añadimos contenido
        Tr fila = (Tr) tabla.getContent().get(0);
        Tr fila3 = (Tr) tabla.getContent().get(2);

            //Por algún motivo la celda 0,0 no tiene formato; no voy a depurar esa sección del código, asi que aquí
            // está el hotfix
        Tc celda1 = (Tc) fila.getContent().get(0);
            CTBorder border1Color = fabObjetos.createCTBorder();
            border1Color.setColor("#000000");
            border1Color.setVal(STBorder.THICK);
            TcPr celda1Props = celda1.getTcPr(); if (celda1Props == null){
                celda1Props = fabObjetos.createTcPr(); celda1.setTcPr(celda1Props);
            }
            TcPrInner.TcBorders borderCelda1 = celda1Props.getTcBorders();
            if (borderCelda1 == null){
                borderCelda1 = fabObjetos.createTcPrInnerTcBorders(); celda1Props.setTcBorders(borderCelda1);
            }
            borderCelda1.setTop(border1Color);
            borderCelda1.setBottom(border1Color);
            borderCelda1.setRight(border1Color);
            borderCelda1.setLeft(border1Color);

        Tc celda2 = (Tc) fila.getContent().get(1);
        Tc celda3 = (Tc) fila3.getContent().get(0);
        P parrafo1 = fabObjetos.createP(); agregarParrafoCEstilo(parrafo1,"Nombres Y apellidos",
                true,false,0,false,18,0);
        P parrafo2 = fabObjetos.createP(); agregarParrafoCEstilo(parrafo2,"Cédula de Identidad",
                true,false,0,false,18,0);
        P parrafo3 = fabObjetos.createP(); agregarParrafoCEstilo(parrafo3,"Observación:",
                true,false,0,false,18,0);

        celda1.getContent().add(parrafo1);
        celda2.getContent().add(parrafo2);
        celda3.getContent().add(parrafo3);

        docMain.getContent().add(tabla);

        P parrafo4 = fabObjetos.createP();
        agregarBr(parrafo4);
        docMain.getContent().add(parrafo4);

        agregarFirmas(packWord,"Docente","Suplente");

        //Agregamos una tabla extra para las firmas
        int cellWidthTwipsF = Double.valueOf(Math.floor((writableWidthTwips / 5))).intValue();
        Tbl tablaF = TblFactory.createTable(2, 5, cellWidthTwipsF);
        TblPr tablaFPr = fabObjetos.createTblPr();
        TblBorders tablaFBorders = fabObjetos.createTblBorders();
        TcPr propsCeldaF;
        TcPrInner.TcBorders borderCeldaF;
        CTBorder borderColorF = fabObjetos.createCTBorder();
        CTBorder tablaColorF = fabObjetos.createCTBorder();

        tablaColorF.setColor("#FFFFFF");
        borderColorF.setColor("#000000");
        borderColorF.setVal(STBorder.THICK);
        tablaFBorders.setTop(tablaColorF);
        tablaFBorders.setBottom(tablaColorF);
        tablaFBorders.setLeft(tablaColorF);
        tablaFBorders.setRight(tablaColorF);

        tablaF.setTblPr(tablaFPr);
        tablaFPr.setTblBorders(tablaFBorders);

        // Modificamos la tabla
        Tr filaF = (Tr) tablaF.getContent().get(1);
        Tc celdaF = (Tc) filaF.getContent().get(2);

        P parrafo5; parrafo5 = fabObjetos.createP();
        agregarParrafoCEstilo(parrafo5,"Director",true,false,0,false,24,0);
        celdaF.getContent().add(parrafo5);

        propsCeldaF = celdaF.getTcPr(); if (propsCeldaF == null) {
            propsCeldaF = fabObjetos.createTcPr(); celdaF.setTcPr(propsCeldaF);}
        borderCeldaF = propsCeldaF.getTcBorders(); if (borderCeldaF == null){
            borderCeldaF = fabObjetos.createTcPrInnerTcBorders(); propsCeldaF.setTcBorders(borderCeldaF);}
        borderCeldaF.setTop(borderColorF);
        // Agregamos la tabla
        docMain.getContent().add(tablaF);

        guardarArchivo(packWord, String.valueOf(nomina.getCi()) + "Licencia.docx");

    }

}
