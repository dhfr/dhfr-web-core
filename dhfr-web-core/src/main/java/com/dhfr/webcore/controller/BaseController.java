/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhfr.webcore.controller;


import com.dhfr.webcore.WebCoreConstant;
import com.dhfr.webcore.util.FacesUtil;
import com.dhfr.webcore.util.MessagesResourceUtil;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Deni Husni Fahri Rizal
 * @version 1
 * @since 10 February 2014
 */
public abstract class BaseController implements Serializable {

    protected transient Logger LOGGER = Logger.getLogger(getClass());
    protected String bahasa;
    

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    @PostConstruct
    public void initialization() {
                    LOGGER.info("Base Controlller Execute");
                    bahasa = (String) FacesUtil.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE);
                    FacesUtil.getFacesContext().getViewRoot().setLocale(new Locale(bahasa));
    }

    public String doClose() {
        return "/protected/home.htm?faces-redirect=true";
    }

    public void onDialogReturn(SelectEvent event) {
        String condition = (String) event.getObject();
        if (condition.equalsIgnoreCase(WebCoreConstant.SAVE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE).toString());
        }
        if (condition.equalsIgnoreCase(WebCoreConstant.UPDATE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                    FacesUtil.getSessionAttribute(WebCoreConstant.BAHASA_ACTIVE).toString());
        }
    }
}
