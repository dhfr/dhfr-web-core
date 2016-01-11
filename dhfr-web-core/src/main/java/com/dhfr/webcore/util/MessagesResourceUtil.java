/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhfr.webcore.util;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Deni Husni Fahri Rizal
 * @version 1
 * @since 10 February 2014
 */
 
public class MessagesResourceUtil {

    public static void setMessages(FacesMessage.Severity severity, String titleErros, String key, String locale) {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(locale));
        FacesMessage msg = new FacesMessage(severity, messages.getString(titleErros), messages.getString(key));
        FacesUtil.getFacesContext().addMessage(null, msg);
    }

    public static void setMessagesFromException(FacesMessage.Severity severity, String titleErros, String contentError, String locale) {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(locale));
        FacesMessage msg = new FacesMessage(severity, messages.getString(titleErros), contentError);
        FacesUtil.getFacesContext().addMessage(null, msg);
    }

    public static void setMessagesFlas(FacesMessage.Severity severity, String titleErros, String key, String locale) {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(locale));
        FacesMessage msg = new FacesMessage(severity, messages.getString(titleErros), messages.getString(key));
        FacesUtil.getFacesContext().addMessage(null, msg);
        FacesUtil.getExternalContext().getFlash().setKeepMessages(true);
    }
}
