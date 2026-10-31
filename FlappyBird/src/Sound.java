import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private Clip bgmClip;
    private  Clip clipsfx;

    //mainin sond efek
    public void playSE(String soundPath) {
        try {
            // stop se buat restart biar ga nambrak lagunya
            stopSE();
            URL url = getClass().getResource(soundPath);
            if (url != null) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                clipsfx = AudioSystem.getClip();
                clipsfx.open(ais);
                clipsfx.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ngeloop backgrounf music
    public void playBGM(String soundPath) {
        try {
            stopBGM(); //stop pas nabrak (oas kalah)

            URL url = getClass().getResource(soundPath);
            if (url != null) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                bgmClip = AudioSystem.getClip();
                bgmClip.open(ais);
                bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
                bgmClip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // stop bg music
    public void stopBGM() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
            bgmClip.close();
        }
    }

    public void stopSE() {
        if (clipsfx != null && clipsfx.isRunning()) {
            clipsfx.stop();
            clipsfx.close();
        }
    }
}