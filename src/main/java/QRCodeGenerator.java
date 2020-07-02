import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRCodeGenerator {

    public static ByteArrayInputStream generateQRCode(String UUID) throws IOException {
        ByteArrayOutputStream stream = QRCode
                .from(UUID)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
        return bis;
    }
}
