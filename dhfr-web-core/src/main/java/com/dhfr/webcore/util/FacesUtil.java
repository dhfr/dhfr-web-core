/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhfr.webcore.util;

import java.util.Map;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 * @author Deni Husni Fahri Rizal
 * @version 1
 * @since 10 February 2014
 */
public class FacesUtil {

    public static String getRequestParameter(String name) {
        return (String) getExternalContext()
                .getRequestParameterMap().get(name);
    }
// JSF general ----------------------------------------------------------------------------------------------------

    /**
     * Returns the current faces context.
     * <p>
     * <i>Note that whenever you absolutely need this method to perform a
     * general task, you might want to consider to submit a feature request to
     * OmniFaces in order to add a new utility method which performs exactly
     * this general task.</i>
     *
     * @return The current faces context.
     * @see FacesContext#getCurrentInstance()
     */
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        ExternalContext externalContext;
        FacesContext fc = getFacesContext();
        if (fc != null) {
            externalContext = fc.getExternalContext();
        } else {
            externalContext = null;
        }
        return externalContext;
    }

    public static RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }

    public static void addCallbackParam(String prm, Object obj) {
        RequestContext rc = getRequestContext();
        if (rc != null) {
            rc.addCallbackParam(prm, obj);
        }
    }

    public static Application getApplication() {
        Application application;
        FacesContext fc = getFacesContext();
        if (fc != null) {
            application = fc.getApplication();
        } else {
            application = null;
        }
        return application;
    }

    public static ExpressionFactory getExpressionFactory() {
        ExpressionFactory expressionFactory;
        Application a = getApplication();
        if (a != null) {
            expressionFactory = a.getExpressionFactory();
        } else {
            expressionFactory = null;
        }
        return expressionFactory;
    }

    public static ELContext getELContext() {
        ELContext eLContext;
        FacesContext fc = getFacesContext();
        if (fc != null) {
            eLContext = fc.getELContext();
        } else {
            eLContext = null;
        }
        return eLContext;
    }

    public static MethodExpression createMethodExpression(String expression) {
        MethodExpression methodExpression;
        ExpressionFactory ef = getExpressionFactory();
        if (ef != null) {
            methodExpression = ef.createMethodExpression(getELContext(),
                    expression, null, new Class[]{ActionEvent.class});
        } else {
            methodExpression = null;
        }
        return methodExpression;
    }

    public static HttpSession getSession(boolean create) {
        ExternalContext ec = getExternalContext();
        HttpSession hs = null;
        if (ec != null) {
            Object obj = ec.getSession(create);
            if (obj != null) {
                hs = (HttpSession) obj;
            }
        }
        return hs;
    }

    public static HttpServletRequest getRequest() {
        ExternalContext ec = getExternalContext();
        HttpServletRequest hsr = null;
        if (ec != null) {
            Object obj = ec.getRequest();
            if (obj != null) {
                hsr = (HttpServletRequest) obj;
            }
        }
        return hsr;
    }

    public static HttpServletResponse getResponse() {
        ExternalContext ec = getExternalContext();
        HttpServletResponse hsr = null;
        if (ec != null) {
            Object obj = ec.getRequest();
            if (obj != null) {
                hsr = (HttpServletResponse) obj;
            }
        }
        return hsr;
    }

    public static Object getSessionAttribute(String key) {
        Object ob;
        HttpSession hs = getSession(true);
        if (hs != null) {
            ob = hs.getAttribute(key);
        } else {
            ob = null;
        }
        return ob;
    }

    public static void setSessionAttribute(String key, Object object) {
        HttpSession hs = getSession(true);
        if (hs != null) {
            hs.setAttribute(key, object);
        }
    }

    public static ServletContext getServletContext() {
        ExternalContext ec = getExternalContext();
        ServletContext sc = null;
        if (ec != null) {
            Object obj = ec.getContext();
            if (obj != null) {
                sc = (ServletContext) obj;
            }
        }
        return sc;
    }

    public static boolean putApplicationMap(String key, Object value) {
        boolean succeed = false;
        Map<String, Object> am = getApplicationMap();
        if (am != null) {
            am.put(key, value);
            succeed = true;
        }
        return succeed;
    }

    public static Object takeApplicationMap(String key) {
        Object object;
        Map<String, Object> am = getApplicationMap();
        if (am != null) {
            object = am.get(key);
        } else {
            object = null;
        }
        return object;
    }

    private static Map<String, Object> getApplicationMap() {
        Map<String, Object> am;
        ExternalContext ec = getExternalContext();
        if (ec != null) {
            am = ec.getApplicationMap();
        } else {
            am = null;
        }
        return am;
    }

}
