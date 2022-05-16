package com.codename1.uikit.pheonixui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.uikit.pheonixui.model.Product;
import com.codename1.uikit.pheonixui.service.ProductService;

import java.util.ArrayList;

public class BoPostsPage extends BaseForm {
    public BoPostsPage() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    EncodedImage enc;
    private double nbr_feedback = 45;
    private double nbr_reclamation = 29;
    Image imgs;
    ImageViewer imgv;
    private ArrayList<Product> posts = new ArrayList<>();
    ArrayList<Product> products = ProductService.getInstance().getProducts();

    public BoPostsPage(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        // Generate the values
        double[] values = new double[]{12, 14, 11, 10, 19};
        installSidemenu(resourceObjectInstance);


        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(50);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Products Total", values), renderer);


        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        addComponent(c);
    }


    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(30);
        renderer.setLegendTextSize(30);
        renderer.setLabelsColor(0x000000);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param title the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (Product product : products) {
            series.add(product.getName(), Double.parseDouble(product.getQuant()));
        }

        return series;
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.FlowLayout());
        setTitle("Statistics");
        setName("Statistics");
    }// </editor-fold>
}
