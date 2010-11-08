/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestStatistik;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Christian
 */
public class TestStatistikServlet extends HttpServlet {
    private StandardXYItemLabelGenerator StandardXYItemLabelGenerator;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        try {
            ImageIO.write(getChart(request), "png", os);
        } finally { 
            os.close();
        }
    }

    private RenderedImage getChart(HttpServletRequest request) {
        String desiredChart = request.getParameter("chart");
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        XYSeries series = new XYSeries("Average Weight");
        series.add(20.0, 20.0);
        series.add(40.0, 25.1);
        series.add(40.0, 25.1);
        series.add(55.0, 50.0);
        series.add(70.0, 65.0);
        XYSeriesCollection xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(series);
        if (desiredChart.equals("myDesiredChart1")) {
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "XYLine Chart using JFreeChart", "Age", "Weight",
                    xyDataset, PlotOrientation.VERTICAL, true, true, false);
            XYItemRenderer renderer = new XYLineAndShapeRenderer();
            DecimalFormat decformat = new DecimalFormat("000.0##");
            StandardXYItemLabelGenerator generator = new StandardXYItemLabelGenerator();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator("{2}", df, decformat));
            renderer.setBaseItemLabelsVisible(true);
            chart.getXYPlot().setRenderer(renderer);
            DateAxis da = new DateAxis();
            da.setDateFormatOverride(df);
            da.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
            chart.getXYPlot().setDomainAxis(da);
            return chart.createBufferedImage(width, height);
        } else {
            return null;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
