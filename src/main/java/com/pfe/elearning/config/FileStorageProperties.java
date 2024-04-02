package com.pfe.elearning.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadImgUsersDir;

    public String getUploadImgUsersDir() {
        return uploadImgUsersDir;
    }

    public void setUploadImgUsersDir(String uploadImgUsersDir) {
        this.uploadImgUsersDir = uploadImgUsersDir;
    }
}
