package com.tcsquad.ilogistics.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class OSSClientUtil {

    protected static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);

    @Value("${aliyun-oss.endpoint}")
    private String endpoint;
    @Value("${aliyun-oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun-oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun-oss.bucketName}")
    private String bucketName;
    @Value("${aliyun-oss.domainUrl}")
    private String domainUrl;

    //文件存储目录
    public static String PRODUCT_IMAGE_DIR= "i-logistics-system/image/category/";

    /**
     * 上传图片
     * @param file
     * @return
     */
    public String uploadImg2Oss(MultipartFile file, String fileDir) {
        if (file.getSize() > 1024 * 1024 *20) {
            return "图片太大";//RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_TOO_MAX);
        }
        String originalFilename = file.getOriginalFilename();
        //String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        //Random random = new Random();
        //String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        String name = getFileName(originalFilename);
        logger.info("文件名："+name);
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name, fileDir);
            return name;//RestResultGenerator.createSuccessResult(name);
        } catch (Exception e) {
            return "上传失败";//RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_UPLOAD);
        }
    }

    /**
     * 上传图片获取fileUrl
     * @param inputStream
     * @param fileName
     * @return
     */
    private String uploadFile2OSS(InputStream inputStream, String fileName, String fileDir) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);

            //上传文件
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            PutObjectResult putResult = ossClient.putObject(bucketName, fileDir + fileName, inputStream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 根据URL获取文件名称
     */
    public String getFileName(String url){
        String[] arr = url.split("/");
        String lastStr = arr[arr.length - 1];
        String fileName = lastStr.split("[?]")[0];
        return fileName;
    }

    /**
     * 获取图片路径公共度
     * @param fileName 文件名称
     * @param fileDir 文件路径
     * @return
     */
    public String getPublicUrl(String fileName, String fileDir) {
        if (!StringUtils.isEmpty(fileName)) {
            String url = domainUrl + fileDir + fileName;
            return url;
        }
        return null;
    }

    /**
     * 获得url含过期时间的链接
     * @param publicUrl 公共url
     * @param minute 多久后到期, 单位分钟
     * @return
     */
    public String getExpiredUrl(String publicUrl, long minute) {
        // 设置URL过期时间
        Date expiration = new Date(new Date().getTime() + minute * 1000);
        // 生成URL
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        URL url = ossClient.generatePresignedUrl(bucketName, publicUrl, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }


    /**
     * 多图片上传
     * @param fileList 文件列表
     * @param fileDir OSS服务器内文件路径
     * @return
     */
    public String checkImageList(List<MultipartFile> fileList, String fileDir) {
        String fileUrl;
        String str;
        String photoUrl = "";
        for(int i = 0;i< fileList.size();i++){
            fileUrl = uploadImg2Oss(fileList.get(i), fileDir);
            str = getPublicUrl(fileUrl, fileDir);
            if(i == 0){
                photoUrl = str;
            }else {
                photoUrl += "," + str;
            }
        }
        return photoUrl.trim();
    }

    /**
     * 单个图片上传
     * @param file 上传图片
     * @return fileDir OSS服务器内文件路径
     */
    public String checkImage(MultipartFile file, String fileDir){
        String fileName = uploadImg2Oss(file, fileDir); //文件名称
        String str = getPublicUrl(fileName, fileDir);
        return str.trim();
    }
}
