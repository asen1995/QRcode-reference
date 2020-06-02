import com.sun.media.sound.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

public class testmain {

    public static void main(String args[]) throws IOException,
            InvalidFormatException,
            org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        String wordFileName = "wordTestresult.docx";
        String pdf = "wordTestresult.pdf";


        XWPFDocument forConvertion = new XWPFDocument(
                OPCPackage.open(wordFileName));
        // 2) Convert POI XWPFDocument 2 PDF with iText
        File outFile = new File(pdf);

        OutputStream out = new FileOutputStream( outFile );
        PdfOptions options = PdfOptions.getDefault();
        PdfConverter.getInstance().convert( forConvertion, out, options );


    }


}
