package constructor;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class BootAnimation {
    private static String pathOrig = "";
    private static String pathOut = "";
    private static final int MAX_FOLDER_PHOTO = 80;

    public BootAnimation(String pathOrig, String pathOut) {
        BootAnimation.pathOrig = pathOrig;
        BootAnimation.pathOut = (pathOut.endsWith(File.separator) ? pathOut : pathOut + File.separator);
    }

    public void storyboard() throws IOException {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(pathOrig);
        grabber.start();

        ArrayList<BufferedImage> imageList = getBufferedImage(grabber);
        sendImages(imageList);
        grabber.close();
    }

    private void sendImages(ArrayList<BufferedImage> imageList) throws IOException {

        int fileCount = (int) Math.ceil( (double)imageList.size() / MAX_FOLDER_PHOTO);
        File[] files = createFiles(pathOut, fileCount);

        int photoCount = 0;
        for (int fileIndex = 0; fileIndex != fileCount; fileIndex++) {

            Files.createDirectories(files[fileIndex].toPath());
            for (int photoIndex = 0; photoIndex != 80; photoIndex++) {

                String pathName = files[fileIndex].getAbsolutePath() + File.separator + photoCount + ".jpg";
                File photo = new File(pathName);

                ImageIO.write(imageList.get(photoCount++), "jpg", photo);

            }

        }

    }

    private File[] createFiles(String path, int fileCount) {
        File[] files = new File[fileCount];
        File file;
        for (int i = 0; i != fileCount; i++) {
            file = new File(path + "path" + i);
            files[i] = file;
        }

        return files;
    }



    private ArrayList<BufferedImage> getBufferedImage(FFmpegFrameGrabber  grabber) throws FFmpegFrameGrabber.Exception {
        ArrayList<BufferedImage> imageList = new ArrayList<>();

        Frame frame;
        while ( (frame = grabber.grabImage()) != null ) {
            try (Java2DFrameConverter converter = new Java2DFrameConverter()) {
                converter.convert(frame);
            }
        }

        return imageList;
    }
}
