# BootLoaderConverter
Simple program to create BootAnimation from video
## Code example
```java
import mediaConvert.BootAnimation;
import mediaConvert.bootOption.FrameOption;

import java.io.File;

public class Main {
    private static final File ORIGINAL_VIDEO = new File("");
    private static final File BOOT_ANIMATION = new File("");
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 1920;
    private static final int FPS = 30;
    public static void main(String[] args) throws Exception {
        FrameOption option = new FrameOption(ORIGINAL_VIDEO, BOOT_ANIMATION);
        option.setVideoOption(WIDTH,HEIGHT, FPS);

        BootAnimation animation = new BootAnimation(option);
        animation.createAnimation();
    }
}

```
# Video demonstration
[<img src="https://img.youtube.com/vi/jHcv2LWsOeA/maxresdefault.jpg" width="50%">](https://youtu.be/jHcv2LWsOeA)



   


## Libraries
JavaCV
https://github.com/bytedeco/javacv
