
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class ConvertPdf {

    public static void convertDocument(String docxFilePath, String pdfFilePath) {

        //create a Word document
        Document document = new Document(docxFilePath);

        //save the pdf document
        document.saveToFile(pdfFilePath, FileFormat.PDF);
    }

    public static byte[] convertDoc(XWPFDocument doc) {

        ByteArrayOutputStream filledWordStream = new ByteArrayOutputStream();
        InputStream wordInputStream = null;

        ByteArrayOutputStream pdfResultOutputStream = null;
        try {
           
            doc.write(filledWordStream);
            byte[] wordBytes = filledWordStream.toByteArray(); 
            
            wordInputStream = new ByteArrayInputStream(wordBytes); 
            Document document = new Document(wordInputStream);
 
            
            pdfResultOutputStream = new ByteArrayOutputStream(); 
            document.saveToStream(pdfResultOutputStream,FileFormat.PDF);
            System.out.println(pdfResultOutputStream);


            pdfResultOutputStream.writeTo(new FileOutputStream("pdfs/resul8t.pdf"));

           return pdfResultOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {


            try {
                filledWordStream.close();
                wordInputStream.close();
                pdfResultOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
//        Document document = new Document(doc);


        return new byte[0];
    }
}
