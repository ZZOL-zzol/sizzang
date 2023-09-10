package com.zzol.sizzang.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service implements FileService{

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3;

    public S3Service(AmazonS3 s3) {
        this.s3 = s3;
    }


    @Override
    public String saveFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename()+"_"+System.currentTimeMillis();

        try {
            File file1 = convertMultiPartToFile(file);
            PutObjectResult putObjectResult = s3.putObject(bucket, originalFileName, file1);
//			return putObjectResult.getContentMd5();
            return originalFileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    @Override
    public byte[] downloadFile(String filename) {
        S3Object object = s3.getObject(bucket, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteFile(String filename) {
        s3.deleteObject(bucket, filename);
        return "File deleted";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucket);

        return listObjectsV2Result.getObjectSummaries().stream().map(o->o.getKey()).collect(Collectors.toList());
    }

}
