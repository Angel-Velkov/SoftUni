package springdata.exercises.usersystem.services.interfaces;

import springdata.exercises.usersystem.models.gallery.Album;
import springdata.exercises.usersystem.models.gallery.Picture;

public interface AlbumService {
    void registerAlbum(Album album);

    void addPictureToAlbum(Picture picture, long albumId);
}
