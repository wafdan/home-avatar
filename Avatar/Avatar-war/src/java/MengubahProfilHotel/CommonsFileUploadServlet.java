/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MengubahProfilHotel;

import Debug.Debug;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CommonsFileUploadServlet extends HttpServlet {

    private static final String TMP_DIR_PATH = "c:\\tmp";
    private File tmpDir;
    private static final String DESTINATION_DIR_PATH = "/images";
    private File destinationDir;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String currentdir = System.getProperty("user.dir");
        Debug.debug("Current directory : " + currentdir);
        tmpDir = new File(TMP_DIR_PATH);
        if (!tmpDir.isDirectory()) {
            //throw new ServletException(TMP_DIR_PATH + " is not a directory");
        }
        String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
        destinationDir = new File(realPath);
        if (!destinationDir.isDirectory()) {
            destinationDir.mkdir();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.println("<h1>Servlet File Upload Example using Commons File Upload</h1>");
        out.println();

        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        /*
         *Set the size threshold, above which content will be stored on disk.
         */
        fileItemFactory.setSizeThreshold(1 * 1024 * 1024); //1 MB
		/*
         * Set the temporary directory to store the uploaded files of size above threshold.
         */
        fileItemFactory.setRepository(tmpDir);

        ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
        try {
            /*
             * Parse the request
             */
            List items = uploadHandler.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                /*
                 * Handle Form Fields.
                 */
                if (item.isFormField()) {
                    out.println("File Name = " + item.getFieldName() + ", Value = " + item.getString());
                } else {
                    //Handle Uploaded files.
                    out.println("Field Name = " + item.getFieldName()
                            + ", File Name = " + item.getName()
                            + ", Content type = " + item.getContentType()
                            + ", File Size = " + item.getSize());
                    /*
                     * Write file to the ultimate location.
                     */
                    String namaFile = item.getName();
                    int indexTitik = namaFile.lastIndexOf(".");
                    String ekstensi = namaFile.substring(indexTitik + 1);
                    String namaFileBaru="logo."+ekstensi;
                    Debug.debug("Nama file logo : "+namaFileBaru);
                    File file = new File(destinationDir, namaFileBaru);
                    item.write(file);
                }
                out.close();
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(CommonsFileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommonsFileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
