import core.RePlayer;
import core.RePlayerImpl;
import models.Track;

import java.util.Date;

public class Main {
    public static void main(String[] args) {


        RePlayer rePlayer = new RePlayerImpl();
        int count = 1000000;

        for (int i = 0; i < count; i++) {
            Track track = new Track(i + "", "Title" + i, "Artist" + i, i * 100, i * 10);

            rePlayer.addTrack(track, "randomAlbum");
        }

        Date start = new Date();
        for (int i = 0; i < count; i++) {
            rePlayer.removeTrack("Title" + i, "randomAlbum");
        }

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());
    }
}