/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Statistik;

import ControllerStatistik.ControllerStatistik;
import ControllerStatistik.ControllerStatistikHall;
import ControllerStatistik.ControllerStatistikRoom;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Christian
 * Menggambar chart
 */
public class StatistikServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ParseException {
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        try {
            ImageIO.write(getChart(request), "png", os);
        } finally {
            os.close();
        }
    }

    private RenderedImage getChart(HttpServletRequest request) throws ParseException {
        //Inisialisasi: variabel dari parameter
        String desiredChartType = request.getParameter("type");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null; Date toDate = null;
        if (request.getParameter("from") != null)
            fromDate = sdf.parse(request.getParameter("from"));
        if (request.getParameter("to") != null)
            toDate = sdf.parse(request.getParameter("to"));
        int width = 550; // lebar gambar
        int height = 300; // tinggi gambar
        //Penggambaran chart sesuai permintaan
        ControllerStatistik cstat;
        JFreeChart chart;
        if (fromDate != null && toDate != null) {
            return null; // not implemented
        } else {
            if (desiredChartType.equals("room")) {
                cstat = new ControllerStatistikRoom();
                chart = cstat.buatStatistik();
                return chart.createBufferedImage(width, height);
            } else if (desiredChartType.equals("hall")) {
                cstat = new ControllerStatistikHall();
                chart = cstat.buatStatistik();
                return chart.createBufferedImage(width, height);
            } else {
                return null;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(StatistikServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(StatistikServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
