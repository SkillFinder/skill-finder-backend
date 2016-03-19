package org.skillfinder.services;

import java.io.InputStream;

public interface ImageService {

    String saveImage(InputStream inputStream, String login);

    InputStream getFile(String fileID);
}
