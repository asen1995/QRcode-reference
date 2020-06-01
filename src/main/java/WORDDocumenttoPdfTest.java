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
                    OPCPackage.open("template.docx"));

            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("Absender Name")) {
                            text = text.replace("Absender Name", "Absender Asen");//your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Absender Straße")) {
                            text = text.replace("Absender Straße", "Absender Ulica");//your content
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("Absender Ort")) {
                            text = text.replace("Absender Ort", "Absender mqsto");//your content
                            r.setText(text, 0);
                        }
                    }
                }
            }

            //the table
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains("xxx")) {
                                    text = text.replace("xxx", "615");//your content
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }

            doc.write(new FileOutputStream("output.docx"));

            //convertion
            XWPFDocument forConvertion = new XWPFDocument(
                    OPCPackage.open("output.docx"));

            // 2) Convert POI XWPFDocument 2 PDF with iText
            File outFile = new File( "tempwla4te-from-word3.pdf" );

            OutputStream out = new FileOutputStream( outFile );
            PdfOptions options = PdfOptions.getDefault();
            PdfConverter.getInstance().convert( forConvertion, out, options );
        } finally {

        }

    }
}