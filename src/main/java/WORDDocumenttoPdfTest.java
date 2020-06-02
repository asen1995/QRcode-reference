import com.sun.media.sound.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

public class WORDDocumenttoPdfTest {

    private static final String NAME = "Asen";
    private static final String STREET = "street";
    private static final String ORT = "ORTNQKUV";

    //TAbLE
    private static final String Fillial = "615";
    private static final String CUSTOMERNUMBER = "9050501";
    private static final String ACCOUNTOWNER = "Asen Nikolaev";


    private static final String DateEnquiry = "07.11.2040";




    public static void main(String args[]) throws IOException,
            InvalidFormatException,
            org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        try {

            /**
             * if uploaded doc then use HWPF else if uploaded Docx file use
             * XWPFDocument
             */
            XWPFDocument doc = new XWPFDocument(
                    OPCPackage.open("template.docx"));

            updateParagraphs(doc);
            updateTable(doc);

            insertQrCodeImage(doc);

            String wordFileName = "rezultaten14.docx";
            String pdf = "rezultaten14.pdf";

            doc.write(new FileOutputStream(wordFileName));

            XWPFDocument forConvertion = new XWPFDocument(
                   OPCPackage.open(wordFileName));
            // 2) Convert POI XWPFDocument 2 PDF with iText
            File outFile = new File(pdf);

            OutputStream out = new FileOutputStream( outFile );
            PdfOptions options = PdfOptions.getDefault();
            PdfConverter.getInstance().convert( forConvertion, out, options );

        } finally {

        }

    }

    private static void insertQrCodeImage(XWPFDocument docx) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        for (XWPFParagraph p : docx.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && ( text.contains("QRCODE"))) {
                       text = text.replace("QRCODE", " ");//your content
                        r.setText(text, 0);

                        List<XWPFPicture> embeddedPicturses = r.getEmbeddedPictures();
                        FileInputStream is = new FileInputStream("qrcode.jpg");

                        r.addPicture(is, Document.PICTURE_TYPE_JPEG, "qrcode.jpg", Units.toEMU(70), Units.toEMU(70));

                        is.close();
                        List<XWPFPicture> embeddedPictures = r.getEmbeddedPictures();
                    }
                }
            }
        }
    }


    private static void updateParagraphs(XWPFDocument doc) {
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("Absender Name")) {
                        text = text.replace("Absender Name", NAME);//your content
                        r.setText(text, 0);
                    }

                    if (text != null && text.contains("Absender Straße")) {
                        text = text.replace("Absender Straße", STREET);//your content
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("Absender Ort")) {
                        text = text.replace("Absender Ort", ORT);//your content
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("dateEnquiry")) {
                        text = text.replace("dateEnquiry", DateEnquiry);//your content
                        r.setText(text, 0);
                    }
                }
            }
        }
    }

    private static void updateTable(XWPFDocument doc) {

        //the table

        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);

                            if (text != null && text.contains("Fillial")) {
                                text = text.replace("Fillial", Fillial);//your content
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("Customernumber")) {
                                text = text.replace("Customernumber", CUSTOMERNUMBER);//your content
                                r.setText(text, 0);
                            }
                            if (text != null && text.contains("accountOwner")) {
                                text = text.replace("accountOwner", ACCOUNTOWNER);//your content
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
    }
}