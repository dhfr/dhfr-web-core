/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhfr.webcore.util;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Deni Husni Fahri Rizal
 * @version 1
 * @since 10 February 2014
 */
@ManagedBean(name = "imageStreamer")
@ApplicationScoped
public class FacesStreamer implements Serializable {

    @ManagedProperty(value = "#{facesIO}")
    private FacesIO facesIO;

    public void setFacesIO(FacesIO facesIO) {
        this.facesIO = facesIO;
    }

    public StreamedContent getImage(String path) throws IOException {
        StreamedContent streamedContent;
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get(path);

        if (context.getRenderResponse() || id == null) {
            streamedContent = new DefaultStreamedContent();
        } else {
            streamedContent = new DefaultStreamedContent(facesIO.getInputStreamFromURL(id));
        }
        return streamedContent;
    }

    public StreamedContent getImage() throws IOException {
        StreamedContent streamedContent;
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get("path");

        if (context.getRenderResponse() || id == null) {
            streamedContent = new DefaultStreamedContent();
        } else {
            streamedContent = new DefaultStreamedContent(facesIO.getInputStreamFromURL(id));
        }
        return streamedContent;
    }
}
