import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class QRCodeGenerator {

    public static String generateQRCode(String UUID) throws IOException {
        ByteArrayOutputStream stream = QRCode
                .from(UUID)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        BufferedImage bufferedImage = ImageIO.read(bis);

        String qrCodeFilePath = "qrcode.jpg";

        File outputfile = new File(qrCodeFilePath);
        ImageIO.write(bufferedImage, "jpg", outputfile);

        return qrCodeFilePath;
    }
}
