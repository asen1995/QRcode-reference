import com.sun.media.sound.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WORDDocumenttoPdfTest {

    private static final String NAME = "Vili";
    private static final String STREET = "street";
    private static final String ORT = "nq";

    //TAbLE
    private static final String Fillial = "615";
    private static final String CUSTOMERNUMBER = "9050501";
    private static final String ACCOUNTOWNER = "asen Nikolaev";


    private static final String UUID = "dbed9ce7-a200-4271-b1ca-095384581asen";

    private static final String DateEnquiry = "07.11.2050";

    public static void main(String args[]) throws IOException,
            org.apache.poi.openxml4j.exceptions.InvalidFormatException {

            //GET TEMPLATE DOCUMENT
            XWPFDocument doc = new XWPFDocument(
                    OPCPackage.open("otmail.docx"));

            updateParagraphs(doc);
            updateTable(doc);

            //GENERATE QR CODE AND INSERT IT IN DOCUMENT
            insertQrCodeImage(doc,UUID);

            //CONVERT to PDF
            byte[] bytes = ConvertPdf.convertDoc(doc);
    }

    private static void insertQrCodeImage(XWPFDocument docx, String UUID) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        for (XWPFParagraph p : docx.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && ( text.contains("QRCODE"))) {
                       text = text.replace("QRCODE", " ");//your content
                        r.setText(text, 0);

                        ByteArrayInputStream byteArrayInputStream = QRCodeGenerator.generateQRCode(UUID);
                        String filename = "QrCode.jpg";
                        r.addPicture(byteArrayInputStream, Document.PICTURE_TYPE_JPEG, filename, Units.toEMU(70), Units.toEMU(70));

                        byteArrayInputStream.close();
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
                    if (text != null && text.contains("name")) {
                        text = text.replace("name", NAME);//your content
                        r.setText(text, 0);
                        continue;
                    }

                    if (text != null && text.contains("Straße")) {
                        text = text.replace("Straße", STREET);//your content
                        r.setText(text, 0);
                        continue;
                    }
                    if (text != null && text.contains("Ort")) {
                        text = text.replace("Ort", ORT);//your content
                        r.setText(text, 0);
                        continue;
                    }
                    if (text != null && text.contains("UUID")) {
                        text = text.replace("UUID", UUID);//your content
                        r.setText(text, 0);
                        continue;
                    }
                    if (text != null && text.contains("dateEnquiry")) {
                        text = text.replace("dateEnquiry", DateEnquiry);//your content
                        r.setText(text, 0);
                        continue;
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
                                continue;
                            }
                            if (text != null && text.contains("Customernumber")) {
                                text = text.replace("Customernumber", CUSTOMERNUMBER);//your content
                                r.setText(text, 0);
                                continue;
                            }
                            if (text != null && text.contains("accountOwner")) {
                                text = text.replace("accountOwner", ACCOUNTOWNER);//your content
                                r.setText(text, 0);
                                continue;
                            }
                        }
                    }
                }
            }
        }
    }
}