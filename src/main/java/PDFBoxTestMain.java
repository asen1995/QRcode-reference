import com.sun.deploy.util.StringUtils;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PDFBoxTestMain {

    public static void main(String[] args) throws IOException {

        File template = new File("Deutsch.pdf");
        PDDocument document = PDDocument.load(template);


        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        text.replace("Deckblatt","kur");
        pdfStripper.getText(document).replace("Deckblatt","kur");
        System.out.println(text);



        String outputFileName = "Deutsch-nekuvproben.pdf";
        // the encoding will need to be adapted to your circumstances
        String encoding = "ISO-8859-1";

        // Note that search and replace can be regular expressions
//        // replace all occurrences of 'Hello'
//        searchReplace("Rücksende", "nt", encoding, true, document);
//        searchReplace("für", "ert", encoding, true, document);
//        //searchReplace("Boring", "skuka", encoding, true, document);

        // Save the results and ensure that the document is properly closed
        document.save(outputFileName);
        document.close();

    }




/*

    private static void searchReplace (String search, String replace,
                                       String encoding, boolean replaceAll, PDDocument doc) throws IOException {
        PDPageTree pages = doc.getDocumentCatalog().getPages();
        for (PDPage page : pages) {
            PDFStreamParser parser = new PDFStreamParser(page);
            parser.parse();
            List tokens = parser.getTokens();
            for (int j = 0; j < tokens.size(); j++) {
                Object next = tokens.get(j);
                if (next instanceof Operator) {
                    Operator op = (Operator) next;
                    // Tj and TJ are the two operators that display strings in a PDF
                    // Tj takes one operator and that is the string to display so lets update that operator
                    if (op.getName().equals("Tj")) {
                        COSString previous = (COSString) tokens.get(j-1);
                        String string = previous.getString();
                        if (replaceAll)
                            string = string.replaceAll(search, replace);
                        else
                            string = string.replaceFirst(search, replace);
                        previous.setValue(string.getBytes());
                    } else if (op.getName().equals("TJ")) {
                        COSArray previous = (COSArray) tokens.get(j-1);
                        for (int k = 0; k < previous.size(); k++) {
                            Object arrElement = previous.getObject(k);
                            if (arrElement instanceof COSString) {
                                COSString cosString = (COSString) arrElement;
                                String string = cosString.getString();
                                if (replaceAll)
                                    string = string.replaceAll(search, replace);
                                else
                                    string = string.replaceFirst(search, replace);
                                cosString.setValue(string.getBytes());
                            }
                        }
                    }
                }
            }
            // now that the tokens are updated we will replace the page content stream.
            PDStream updatedStream = new PDStream(doc);
            OutputStream out = updatedStream.createOutputStream();
            ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
            tokenWriter.writeTokens(tokens);
            out.close();
            page.setContents(updatedStream);
        }
    }
*/

}
