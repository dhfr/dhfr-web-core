/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhfr.webcore.util;

import java.io.Serializable;

/**
 *
 * @author Deni Husni FR
 */
public abstract class SearchParameter implements Serializable {

    protected String keyParam;
    protected String parameter;

    public String getKeyParam() {
        return keyParam;
    }

    public void setKeyParam(String keyParam) {
        this.keyParam = keyParam;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    
    
}
