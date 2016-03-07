package org.skillfinder.services;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public void saveImage(InputStream inputStream, String fileName) {
        gridFsTemplate.store(inputStream, fileName);
    }

    @Override
    public InputStream getFile(String fileID) {
        GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileID)));
        return gridFSDBFile.getInputStream();
    }

}
