package mediaConvert.bootOption;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FrameOption extends BootAnimationOption {
    private final File OUT_FRAME_PATH;
    private static final int MAX_FOLDER_PHOTO = 80;
    private static FFmpegFrameGrabber grabber;
    private static File[] frameDirectories;


    public FrameOption(File videoFile, File outFramePath) {
        super(videoFile);
        this.OUT_FRAME_PATH = outFramePath;

        if (!outFramePath.isDirectory())
            throw new IllegalArgumentException("outFramePath must be a directory");

    }

    public void storyboard() throws Exception {
        startGrabber();

        int fileCount = (int) Math.ceil( (double)grabber.getLengthInVideoFrames() / MAX_FOLDER_PHOTO);
        frameDirectories = setFiles(OUT_FRAME_PATH, fileCount);


        int frameRead = 0;
        final int frameAvailable = grabber.getLengthInVideoFrames();
        for (int fileIndex = 0; fileIndex != frameDirectories.length; fileIndex++) {
            Files.createDirectories(Path.of(frameDirectories[fileIndex].getAbsolutePath()));


            for (int photoIndex = 0; photoIndex != 80 && frameAvailable != frameRead; photoIndex++) {

                BufferedImage image = new Java2DFrameConverter().convert(grabber.grabImage());
                File photo = new File(frameDirectories[fileIndex] + File.separator +
                        String.format("%06d", grabber.getFrameNumber()) + ".jpg");
                ImageIO.write(image, "jpg", photo);
                frameRead++;
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

    private void startGrabber() throws Exception {
        grabber = new FFmpegFrameGrabber(VIDEO_FILE);
        grabber.start();
    }

    private static File[] setFiles(File file, int fileCount) {
        File[] files = new File[fileCount];

        for (int i = 0; i != fileCount; i++)
            files[i] = new File(file.getAbsolutePath() + File.separator + "path" + i);



        return files;
    }


}
