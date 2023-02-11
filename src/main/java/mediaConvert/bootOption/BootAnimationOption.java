package mediaConvert.bootOption;

import java.io.File;

public class BootAnimationOption {
    protected final File VIDEO_FILE;
    protected static int x = -1;
    protected static int y = -1;
    protected static int fps = -1;

    public BootAnimationOption(File videoFile) {
        String videoName = videoFile.getName();
        if (!(videoName.endsWith("mp4") || videoName.endsWith("mov"))) {
            throw new IllegalArgumentException("File is not a video or file is not exist");
        }
         this.VIDEO_FILE = videoFile;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x <= 0)
            throw new IllegalArgumentException("x must be greater than 0");

        BootAnimationOption.x = x;
    }

    public void setVideoOption(int x, int y, int fps) {
        setX(x);
        setY(y);
        setFps(fps);
    }

    public int getY() {
        return y;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        if (fps <= 0)
            throw new IllegalArgumentException("fps must be greater than 0");

        BootAnimationOption.fps = fps;
    }

    public void setY(int y) {
        if (y <= 0)
            throw new IllegalArgumentException("y must be greater than 0");

        BootAnimationOption.y = y;
    }
}
