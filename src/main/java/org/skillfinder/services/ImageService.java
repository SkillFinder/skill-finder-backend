package org.skillfinder.services;

import java.io.InputStream;

public interface ImageService {

    void saveImage(InputStream inputStream, String fileName);

    InputStream getFile(String fileID);
}
