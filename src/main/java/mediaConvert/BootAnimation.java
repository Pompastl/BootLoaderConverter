package mediaConvert;

import mediaConvert.bootOption.FrameOption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BootAnimation {
    private static FrameOption frameOption;
    private static File desc;
    public BootAnimation(FrameOption frameOption) {
        BootAnimation.frameOption = frameOption;
        desc = new File(frameOption.getOUT_FRAME_PATH().getAbsolutePath() + File.separator + "desc.txt");
    }
    public void createAnimation() throws IOException {
        writeDesc();
    }
    private static void writeDesc() throws IOException {
        FileWriter writer = new FileWriter(desc);
        String sizeLine = frameOption.getX() + " " + frameOption.getY() + " " + frameOption.getFps() + "\n";
        StringBuilder pathLine = new StringBuilder();

        for (int i = 0; i != frameOption.getFrameDirectories().length; i++) {
            pathLine.append("p " + "1 " + "0 " + frameOption.getFrameDirectories()[i].getName() + "\n");
        }

        writer.write(sizeLine + pathLine);
        writer.close();
    }

}
