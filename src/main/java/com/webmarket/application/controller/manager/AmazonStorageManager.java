package com.webmarket.application.controller.manager;

import com.webmarket.application.controller.exception.AmazonStorageManagerException;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.model.StorageOwner;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Configuration
@PropertySource("classpath:amazon-s3.properties")
public class AmazonStorageManager {
    @Autowired
    private Environment environment;

    private S3Service getService() {
        AWSCredentials awsCredentials = new AWSCredentials(environment.getProperty("AWSAccessKeyId"), environment.getProperty("AWSSecretKey"));
        return new RestS3Service(awsCredentials);
    }

    private S3Bucket getBucket(S3Service s3Service) throws S3ServiceException {
        return s3Service.getBucket(environment.getProperty("AWSBucketName"));
    }

    private S3Object getObject(String fileName) {
        return new S3Object(fileName);
    }

    private AccessControlList getControlList(StorageOwner owner) {
        AccessControlList accessControlList = new AccessControlList();
        accessControlList.setOwner(owner);
        accessControlList.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
        return accessControlList;
    }

    private String getUrl(String fileName) {
        return environment.getProperty("AWSUrl") + "/" + environment.getProperty("AWSBucketName") + "/" + fileName;
    }

    public String save(String fileName, MultipartFile image) {
        try {
            S3Service s3Service = getService();
            S3Bucket imageBucket = getBucket(s3Service);
            S3Object imageObject = getObject(fileName);
            AccessControlList accessControlList = getControlList(imageBucket.getOwner());
            imageObject.setAcl(accessControlList);

            imageObject.setDataInputStream(new ByteArrayInputStream(image.getBytes()));
            imageObject.setContentLength(image.getBytes().length);
            imageObject.setContentType("image/jpeg");

            s3Service.putObject(imageBucket, imageObject);

        } catch (Exception e) {
            throw new AmazonStorageManagerException("Connection to AWS failed");
        }

        return getUrl(fileName);
    }

    public void delete(String key) {
        try {
            S3Service s3Service = getService();
            S3Bucket imageBucket = getBucket(s3Service);
            s3Service.deleteObject(imageBucket, key);
        } catch (Exception e) {
            throw new AmazonStorageManagerException("Connection to AWS failed");
        }
    }
}
