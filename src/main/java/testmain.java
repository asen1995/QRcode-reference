import com.sun.media.sound.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class testmain {

    public static void main(String args[]) throws IOException,
            InvalidFormatException,
            org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        try {

            /**
             * if uploaded doc then use HWPF else if uploaded Docx file use
             * XWPFDocument
             */
            XWPFDocument doc = new XWPFDocument(
                    OPCPackage.open("wordTest.docx"));

            updateParagraphs(doc);

          //  insertQrCodeImage(doc);

            doc.write(new FileOutputStream("wordTestresult.docx"));

        } finally {

        }

    }

    private static void updateParagraphs(XWPFDocument doc) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("Sadk")) {
                        text = text.replace("Sadk", "qrCodedaimatuk");//your content
                        r.setText(text, 0);

                        List<XWPFPicture> embeddedPicturses = r.getEmbeddedPictures();
                        FileInputStream is = new FileInputStream("qrcode.jpg");

                        r.addPicture(is, Document.PICTURE_TYPE_JPEG, "qrcode", Units.toEMU(100), Units.toEMU(100));

                        is.close();
                        List<XWPFPicture> embeddedPictures = r.getEmbeddedPictures();

                    }
                }
            }
        }
    }

}
