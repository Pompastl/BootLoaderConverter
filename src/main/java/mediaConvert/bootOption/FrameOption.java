package mediaConvert.bootOption;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FrameOption extends BootAnimationOption {
    private static final int MAX_FOLDER_PHOTO_COUNT = 80;
    private static File OUT_FRAME_PATH;
    private static FFmpegFrameGrabber grabber;
    private static File[] frameDirectories;


    public FrameOption(File videoFile, File outFramePath) throws IOException {
        super(videoFile);
        OUT_FRAME_PATH = new File(outFramePath.getAbsolutePath() + File.separator + "bootanimation");
        Files.createDirectories(Path.of(OUT_FRAME_PATH.getAbsolutePath()));
    }

    public static void storyboard() throws Exception {
        startGrabber();

        int fileCount = (int) Math.ceil( (double)grabber.getLengthInVideoFrames() / MAX_FOLDER_PHOTO_COUNT);
        frameDirectories = setFiles(fileCount);

        for (int fileIndex = 0; fileIndex != frameDirectories.length; fileIndex++) {
            Files.createDirectories(Path.of(frameDirectories[fileIndex].getAbsolutePath()));

            for (int photoIndex = 0; photoIndex <= 80; photoIndex++) {
                BufferedImage image = new Java2DFrameConverter().convert(grabber.grabImage());
                if (image == null)
                    break;

                File photo = new File(frameDirectories[fileIndex] + File.separator + "boot_"
                        + String.format("%03d", grabber.getFrameNumber()) + ".jpg");
                ImageIO.write(image, "jpg", photo);
            }

        }

        grabber.close();
    }


    public File getOUT_FRAME_PATH() {
        return OUT_FRAME_PATH;
    }

    public File[] getFrameDirectories() {
        return frameDirectories;
    }

    private static void startGrabber() throws Exception {
        grabber = new FFmpegFrameGrabber(videoFile);
        grabber.start();
    }

    private static File[] setFiles(int fileCount) {
        File[] files = new File[fileCount];

        for (int i = 0; i != fileCount; i++)
            files[i] = new File(OUT_FRAME_PATH.getAbsolutePath() + File.separator + "path" + i);

        return files;
    }

}
