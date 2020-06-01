import com.sun.media.sound.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class WORDDocumenttoPdfTest {

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
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("Rücksende")) {
                            text = text.replace("Rücksende", "nekuv nemski text");//your content
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("Asen")) {
                            text = text.replace("Asen", "Asen edit java Rücksende");//your content
                            r.setText(text, 0);
                        }
                    }
                }
            }

            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains("Georgi")) {
                                    text = text.replace("Georgi", "ABCD");//your content
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }

         //   doc.write(new FileOutputStream("output.docx"));

            // 2) Convert POI XWPFDocument 2 PDF with iText
            File outFile = new File( "result-from-word.pdf" );

            OutputStream out = new FileOutputStream( outFile );
            PdfOptions options = PdfOptions.create().fontEncoding( "windows-1250" );
            PdfConverter.getInstance().convert( doc, out, options );
        } finally {

        }

    }
}