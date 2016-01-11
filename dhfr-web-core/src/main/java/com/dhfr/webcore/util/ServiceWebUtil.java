/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhfr.webcore.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Deni Husni Fahri Rizal
 * @version 1
 * @since 10 February 2014
 */
public class ServiceWebUtil {

    private static ApplicationContext appContext;

    public static ApplicationContext getApplicationContext() {
        if (appContext == null) {
            ServletContext servletContext = (ServletContext) FacesContext.
                    getCurrentInstance()
                    .getExternalContext().getContext();
            appContext = WebApplicationContextUtils.
                    getWebApplicationContext(servletContext);
        }
        return appContext;
    }

    /**
     * Methode untuk mendapatkan object service dari setiap Class atau Interface
     * yang di manage sama Spring.
     * <p>
     * <b>Contoh Code:</b>
     * <br/>SpringIO springIO=(SpringIO) ServiceUtil.getService("springIO");
     * <br/>springIO.getSomeThing();
     *
     *
     * @param beansName nama dari interface atau class yang akan di ambil object
     * nya
     * @return berupa Object sesuai dengan nama class atau interface yang di
     * panggil.
     */
    public static Object getService(String beansName) {
        return getApplicationContext().getAutowireCapableBeanFactory().getBean(beansName);

    }
    
    
}
