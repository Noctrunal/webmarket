package com.webmarket.application.util;

import com.webmarket.application.dto.ProductDTO;
import com.webmarket.application.model.Product;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:amazon-s3.properties")
public class ProductUtil {
    @Autowired
    private Environment environment;

    public static List<ProductDTO> getFilteredByPrice(List<Product> products, double startPrice, double endPrice) {
        return products.stream().filter(
                product -> (FilterUtil.isBetweenPrices(startPrice, endPrice, product.getPrice()))).map(
                product -> new ProductDTO(
                        product.getId(),
                        product.getReleaseYear(),
                        product.getBrand(),
                        product.getType(),
                        product.getDescription(),
                        product.getAmount(),
                        product.getPrice()))
                .collect(Collectors.toList()
        );
    }

    public static List<ProductDTO> getFilteredByYears(List<Product> products, int start, int end) {
        return products.stream().filter(
                product -> FilterUtil.isBetweenYears(
                        start, end, product.getReleaseYear())).map(
                product -> new ProductDTO(
                        product.getId(),
                        product.getReleaseYear(),
                        product.getBrand(),
                        product.getType(),
                        product.getDescription(),
                        product.getAmount(),
                        product.getPrice()))
                .collect(Collectors.toList()
        );
    }

    public String saveImageAndGetUrl(String fileName, MultipartFile image) {
        try {
            AWSCredentials awsCredentials = new AWSCredentials(environment.getProperty("AWSAccessKeyId"), environment.getProperty("AWSSecretKey"));
            S3Service s3Service = new RestS3Service(awsCredentials);
            S3Bucket imageBucket = s3Service.getBucket("webmarketelectronic");
            S3Object imageObject = new S3Object(fileName);
            imageObject.setDataInputStream(new ByteArrayInputStream(image.getBytes()));
            imageObject.setContentLength(image.getBytes().length);
            imageObject.setContentType("image/jpeg");

            AccessControlList accessControlList = new AccessControlList();
            accessControlList.setOwner(imageBucket.getOwner());
            accessControlList.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
            imageObject.setAcl(accessControlList);

            s3Service.putObject(imageBucket, imageObject);

        } catch (S3ServiceException | IOException e) {
            e.printStackTrace();
        }
        return "//s3.amazonaws.com/webmarketelectronic/" + fileName;
    }

    public void deleteImage(String key) {
        try {
            AWSCredentials awsCredentials = new AWSCredentials(environment.getProperty("AWSAccessKeyId"), environment.getProperty("AWSSecretKey"));
            S3Service s3Service = new RestS3Service(awsCredentials);
            S3Bucket imageBucket = s3Service.getBucket("webmarketelectronic");
            s3Service.deleteObject(imageBucket, key);
        } catch (S3ServiceException e) {
            e.printStackTrace();
        }
    }

    public static Product createFromTo(ProductDTO productDTO) {
        return new Product(
                null,
                productDTO.getReleaseYear(),
                productDTO.getBrand(),
                productDTO.getType(),
                productDTO.getPrice(),
                productDTO.getAmount(),
                productDTO.getDescription()
        );
    }

    public static Product updateFromTo(ProductDTO productDTO) {
        Product product = new Product(
                productDTO.getId(),
                productDTO.getReleaseYear(),
                productDTO.getBrand(),
                productDTO.getType(),
                productDTO.getPrice(),
                productDTO.getAmount(),
                productDTO.getDescription()
        );
        product.setImageUrl(productDTO.getImageUrl());
        return product;
    }
}
