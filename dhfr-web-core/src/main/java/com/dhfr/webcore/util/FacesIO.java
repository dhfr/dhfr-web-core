/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhfr.webcore.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 *
 * @author Deni Husni Fahri Rizal
 * @version 1
 * @since 10 February 2014
 */
public class FacesIO implements ResourceLoaderAware, InitializingBean {

    private String pathUpload;
    private Integer bufferSize;
    private ResourceLoader resourceLoader;
    private Resource resource;

    public Integer getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }

    public String getPathUpload() {
        return pathUpload;
    }

    public void setPathUpload(String pathUpload) {
        this.pathUpload = pathUpload;
    }

    /**
     *
     * <p>
     * For Transfer File in Upload technic
     * <p>
     * The Date format using default active locale.</p>
     *
     * @param uploadedFile is primefaces UploadedFile 
     * @throws java.io.IOException 
     */
    public void transferFile(UploadedFile uploadedFile) throws IOException {
        File fileHasTransfer = new File(pathUpload, uploadedFile.getFileName());
        InputStream inputStream;
        try (OutputStream outputStream = new FileOutputStream(fileHasTransfer)) {
            byte[] buffer = new byte[bufferSize];
            int bulk;
            inputStream = uploadedFile.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                outputStream.write(buffer, 0, bulk);
                outputStream.flush();
            }
        }
        inputStream.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public InputStream getInputStreamFromName(String namaFile) throws IOException {
        resource = resourceLoader.getResource("file:" + pathUpload + namaFile);
        return resource.getInputStream();
    }

    public InputStream getInputStreamFromURL(String url) throws IOException {
        resource = resourceLoader.getResource("file:" + url);
        return resource.getInputStream();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (pathUpload.equalsIgnoreCase("") || pathUpload == null) {
            throw new Exception("Mr. DHFR say :Property of pathUpload can't be null. "
                    + "Please see and config web_core.properties");
        }

        if (bufferSize == null || bufferSize == 0) {
            throw new Exception("Mr. DHFR say :Property of bufferSize can't be null or zero. "
                    + "Please see and config web_core.properties");

        }
//        if (resourceLoader == null) {
//            throw new Exception("Mr. DHFR say :Property of resourceLoader can't be null "
//                    + "Please see and spring web config or config web_core.properties");
//
//        }
    }
}
