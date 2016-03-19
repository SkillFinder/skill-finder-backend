package org.skillfinder.services;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
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
    public String saveImage(InputStream inputStream, String login) {
        GridFSFile fsFile = gridFsTemplate.store(inputStream,new BasicDBObject("owner",login));
        return fsFile.getId().toString();
    }

    @Override
    public InputStream getFile(String fileID) {
        GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileID)));
        return gridFSDBFile.getInputStream();
    }

}
