/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eajsf.ejb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.FileUpload;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Jesus
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
//    public String uploadImagen(Part imagenFile) throws IOException {
//        final String SAVE_DIR = "uploadImages";
//        // gets absolute path of the web application
////        String appPath = request.getServletContext().getRealPath("");
//        String appPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
//        // constructs path of the directory to save uploaded file
//        String filePath = appPath + File.separator + "resources" + File.separator + "img" + File.separator + SAVE_DIR;
////        String filePathWeb = "resources/img/" + SAVE_DIR + "/";
//        // creates the save directory if it does not exists
//        File fileSaveDir = new File(filePath);
//        String fileDirUpload = filePath + File.separator + imagenFile.getName();
//        File file = new File(fileDirUpload);
//        
//        // Si la carpeta no existe la ceramos
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }
//        
//        InputStream inputStream = imagenFile.getInputStream();          
//        FileOutputStream outputStream = new FileOutputStream(file); 
//          
//        byte[] buffer = new byte[4096];          
//        int bytesRead = 0;  
//        while(true) {                          
//            bytesRead = inputStream.read(buffer);  
//            if(bytesRead > 0) {  
//                outputStream.write(buffer, 0, bytesRead);  
//            }else {  
//                break;  
//            }                         
//        }  
//        outputStream.close();  
//        inputStream.close();  
//         
//        return fileDirUpload;  
//    }
//    
//    public Map<String, String> obtenerDatosFormConImagen(HttpServletRequest request) {
//
//        Map<String, String> mapDatos = new HashMap();
//
//        final String SAVE_DIR = "uploadImages";
//
//        // Parametros del form
//        String description = null;
//        String url_image = null;
//        String id_grupo = null;
//
//        boolean isMultiPart;
//        String filePath;
//        int maxFileSize = 50 * 1024;
//        int maxMemSize = 4 * 1024;
//        File file = null;
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//
//        // gets absolute path of the web application
//        String appPath = request.getServletContext().getRealPath("");
//        // constructs path of the directory to save uploaded file
//        filePath = appPath + File.separator + "resources" + File.separator + "img" + File.separator + SAVE_DIR;
//        String filePathWeb = "resources/img/" + SAVE_DIR + "/";
//        // creates the save directory if it does not exists
//        File fileSaveDir = new File(filePath);
//
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }
//        // Check that we have a file upload request
//        isMultiPart = ServletFileUpload.isMultipartContent(request);
//        if (isMultiPart) {
//
//            // Parse the request
//            List<FileItem> items = getMultipartItems(request);
//
//            // Process the uploaded items
//            Iterator<FileItem> iter = items.iterator();
//            int offset = 0;
//            int leidos = 0;
//            while (iter.hasNext()) {
//                FileItem item = iter.next();
//
//                if (item.isFormField()) {
//                    // ProcessFormField
//                    String name = item.getFieldName();
//                    String value = item.getString();
//                    if (name.equals("description_post_grupo") || name.equals("descripcion")) {
//                        description = value;
//                    } else if (name.equals("id_grupo")) {
//                        id_grupo = value;
//                    }
//                } else {
//                    // ProcessUploadedFile
//                    try {
//                        String itemName = item.getName();
//                        if (!itemName.equals("")) {
//                            url_image = filePathWeb + item.getName();
//                            // read this file into InputStream
//                            inputStream = item.getInputStream();
//                            // write the inputStream to a FileOutputStream
//                            if (file == null) {
//                                String fileDirUpload = filePath + File.separator + item.getName();
//                                file = new File(fileDirUpload);
//                                // crea el archivo en el sistema
//                                file.createNewFile();
//                                if (file.exists()) {
//                                    outputStream = new FileOutputStream(file);
//                                }
//                            }
//
//                            int read = 0;
//                            byte[] bytes = new byte[1024];
//
//                            while ((read = inputStream.read(bytes)) != -1) {
//                                outputStream.write(bytes, offset, read);
//                                leidos += read;
//                            }
//                            offset += leidos;
//                            leidos = 0;
//                            System.out.println("Done!");
//                        } else {
//                            url_image = "";
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (inputStream != null) {
//                            try {
//                                inputStream.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            }
//            if (outputStream != null) {
//                try {
//                    // outputStream.flush();
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//        mapDatos.put("descripcion", description);
//        mapDatos.put("imagen", url_image);
//        mapDatos.put("id_grupo", id_grupo);
//
//        return mapDatos;
//    }
//    
//    public static List<FileItem> getMultipartItems(HttpServletRequest request) {
//        List<FileItem> multipartItems = new LinkedList<FileItem>();
//        if (FileUpload.isMultipartContent(new ServletRequestContext(request))) {
//            FileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            try {
//                multipartItems = upload.parseRequest(request);
//            } catch (FileUploadException fuex) {
//                System.out.println("Error parsing multi-part form data: " + fuex.getMessage());
//            }
//        }
//        return multipartItems;
//    }
    
}
