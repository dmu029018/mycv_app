/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mati
 */
public class ViewUtils 
{
    public static final String PROJECT_ROOT = "/mycv_app";
    public static final String CSS_ROOT = PROJECT_ROOT + "/assets/css/";
    public static final String JS_ROOT = PROJECT_ROOT + "/assets/js/";
    public static final String IMG_ROOT = PROJECT_ROOT + "/assets/img/";
    
    /**
     * Devuelve una etiqueta link que carga una hoja de estilos con el nombre indicada.
     * El directorio base donde buscará es determinado por la constante CSS_ROOT.
     * Para acceder a un subdirectorio, pasar: subdirectorio/nombredelarchivo
     * @param stylesheet Nombre del archivo css a cargar
     * @return Etiqueta link. Imprimir en la vista deseada.
     */
    public static String createStylesheetLinkTag(String stylesheet)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"")
                .append(CSS_ROOT)
                .append(stylesheet)
                .append(".css\"/>");
        
        return sb.toString();
    }
    
    public static void setStylesheets(HttpServletRequest request, String... stylesheets)
    {
        request.setAttribute("_stylesheets", stylesheets);
    }
    
    /**
     * Devuelve una cadena de links con todas las hojas de estilos guardadas en una request.
     */
    public static String printStylesheets(HttpServletRequest request)
    {
        StringBuilder sb = new StringBuilder();
        if(request.getAttribute("_stylesheets") != null)
        {
            for(String stylesheet: (String[]) request.getAttribute("_stylesheets")){
                sb.append(createStylesheetLinkTag(stylesheet));
            }
        }
        return sb.toString();
    }
    
    /**
     * Devuelve una etiqueta script que carga un archivo javascripy con el nombre indicado.
     * El directorio base donde buscará es determinado por la constante JS_ROOT.
     * Para acceder a un subdirectorio, pasar: subdirectorio/nombredelarchivo
     * @param stylesheet Nombre del archivo js a cargar
     * @return Etiqueta script. Imprimir en la vista deseada.
     */
    public static String createScriptTag(String script)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<script id=\"")
                .append(script)
                .append("-script\" type=\"text/javascript\" src=\"")
                .append(JS_ROOT)
                .append(script)
                .append(".js\"></script>");
        
        return sb.toString();
    }
    
    public static void setScripts(HttpServletRequest request, String... scripts)
    {
        request.setAttribute("_scripts", scripts);
    }
    
    /**
     * Devuelve una cadena de links con todas las hojas de estilos guardadas en una request.
     */
    public static String embedScripts(HttpServletRequest request)
    {
        StringBuilder sb = new StringBuilder();
        if(request.getAttribute("_scripts") != null)
        {
            for(String script: (String[]) request.getAttribute("_scripts")){
                sb.append(createScriptTag(script));
            }
        }
        return sb.toString();
    }
    
    public static void setNotificationError(HttpServletRequest request, String message)
    {
        request.setAttribute("notification-error", message);
    }
    
    public static void setNotificationSuccess(HttpServletRequest request, String message)
    {
        request.setAttribute("notification-success", message);
    }
    
    public static void setNotificationWarning(HttpServletRequest request, String message)
    {
        request.setAttribute("notification-warning", message);
    }
}
