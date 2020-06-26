
import com.spire.doc.Document;
import com.spire.doc.FileFormat;

public class ConvertPdf {

    public static void main(String args[]) {

        //create a Word document
        Document document = new Document("pdfs/rezultaten33.docx");

        //save the document
        document.saveToFile("output15.pdf", FileFormat.PDF);

    }

}
