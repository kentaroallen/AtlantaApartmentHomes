/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Kentaro
 */
public class MusicPlayerController {

    private static MusicPlayerController mpc = null;

    protected MusicPlayerController() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                URL resource = getClass().getResource("elevatormusic.mp3");
                Media media = new Media(resource.toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
        });
        t.start();

    }

    public static MusicPlayerController getInstance() {
        if (mpc == null) {
            mpc = new MusicPlayerController();
        }
        return mpc;
    }
}
