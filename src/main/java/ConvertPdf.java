import com.sun.media.sound.InvalidFormatException;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;

import java.io.IOException;

public class ConvertPdf {

    public static void main(String args[]) {

        //create a Word document
        Document document = new Document("pdfs/rezultaten33.docx");

        //save the document
        document.saveToFile("output15.pdf", FileFormat.PDF);

//        String wordFileName = "pdfs/rezultaten33.docx";
//        String pdf = "pdfs/rezultaten35.pdf";
//
//        XWPFDocument forConvertion = new XWPFDocument(
//                OPCPackage.open(wordFileName));
//        // 2) Convert POI XWPFDocument 2 PDF with iText
//        File outFile = new File(pdf);
//
//        OutputStream out = new FileOutputStream( outFile );
//        PdfOptions options = PdfOptions.getDefault();
//        PdfConverter.getInstance().convert( forConvertion, out, options );



    }


}
