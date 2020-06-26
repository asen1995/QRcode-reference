
import com.spire.doc.Document;
import com.spire.doc.FileFormat;

public class ConvertPdf {

    public static void convertDocument(String docxFilePath, String pdfFilePath) {

        //create a Word document
        Document document = new Document(docxFilePath);

        //save the pdf document
        document.saveToFile(pdfFilePath, FileFormat.PDF);

    }

}
