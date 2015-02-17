package simulator.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.xy.*;

import simulator.*;

public class GraphicsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static GraphicsPanel thisPanel;

	JFrame parent;

	List<Signal> signals;
	Simulator simulator;
	Map<Signal, XYSeries> xyseries;

	public GraphicsPanel(JFrame guiFrame, List<Signal> signals,
			Simulator simulator) {
		super(new GridLayout(1, 0));
		thisPanel = this;

		this.parent = guiFrame;
		List<Signal> tempList = new LinkedList<Signal>(signals);
		this.signals = new LinkedList<Signal>();
		for(Signal s : tempList){
			if(s.getName().startsWith("CLK")){
				this.signals.add(s);
			}
		}
		xyseries = new HashMap<Signal, XYSeries>();
		this.simulator = simulator;

		JPanel panel = new JPanel();
		JScrollPane jsp = new JScrollPane(panel);
		panel.setPreferredSize(new Dimension(2000,0));

		JFreeChart[] jfreecharts = createCharts();
		panel.setLayout(new GridLayout(jfreecharts.length,1));

		for (JFreeChart jfreechart : jfreecharts) {
			ChartPanel chartpanel = new ChartPanel(jfreechart);
			chartpanel.setPreferredSize(new Dimension(0,100));
			JScrollPane chartsp = new JScrollPane(chartpanel);
			panel.add(chartsp);
		}
		// jsp.setPreferredSize(new Dimension(700, jfreecharts.length * 80));

		this.add(jsp);

	}

	public void addSignal(Signal signal) {
		signals.add(signal);
	}

	public void addSignals(List<Signal> signals) {
		this.signals.addAll(signals);
	}

	public void removeSignal(Signal signal) {
		signals.remove(signal);
	}

	public void removeSignals(List<Signal> signals) {
		this.signals.remove(signals);
	}

	@Override
	public void paint(Graphics g) {
		updateSignals();

		super.paint(g);
	}

	public void updateSignals() {
		for (Signal subject : signals) {
			if (subject == null)
				continue;
			long mtime = simulator.getCurrentTime();
			double val = subject.getValue().getIntValue();

			xyseries.get(subject).add(mtime, val);
		}
	}

	private JFreeChart createChart(XYDataset xydataset, String name) {
		JFreeChart jfreechart;

		jfreechart = ChartFactory.createXYLineChart(name, null, null,
				xydataset, PlotOrientation.VERTICAL, false, false, false);

		plotSettings(jfreechart);
		return jfreechart;
	}

	private void plotSettings(JFreeChart chart) {
		chart.setBackgroundPaint(Color.WHITE);
		// TextTitle t = chart.getTitle();
		// t.setPaint(new Color(0x000080));

		XYPlot xyplot = chart.getXYPlot();
		// xyplot.setBackgroundPaint(Color.lightGray);
		// xyplot.setDomainGridlinePaint(Color.white);
		// xyplot.setRangeGridlinePaint(Color.white);

		NumberAxis axis = (NumberAxis) xyplot.getRangeAxis();
		axis.setAutoRange(false);
		axis.setRange(0, 1);
		TickUnits units = (TickUnits) NumberAxis.createIntegerTickUnits();
		axis.setStandardTickUnits(units);
		// axis.setUpperMargin(0.1);

		axis = (NumberAxis) xyplot.getDomainAxis();
		// axis.setAutoRange(true);
		// axis.setAutoRangeMinimumSize(50);
		units = (TickUnits) NumberAxis.createIntegerTickUnits();
		axis.setStandardTickUnits(units);

		// XYItemRenderer renderer = new StandardXYItemRenderer();
		XYStepRenderer renderer = new XYStepRenderer();
		// renderer.setSeriesPaint(0, new Color(0x8B008B));
		renderer.setStroke(new BasicStroke(2.0F));

		XYToolTipGenerator ttg = new StandardXYToolTipGenerator();
		renderer.setToolTipGenerator(ttg);
		renderer.setDefaultEntityRadius(100);

		xyplot.setRenderer(renderer);

		// ValueAxis domain = xyplot.getDomainAxis();
		// domain.setAutoRange(true);
		// domain.setLowerMargin(0.0);
		// domain.setUpperMargin(0.0);
		// domain.setTickLabelsVisible(true);

	}

	private JFreeChart[] createCharts() {
		JFreeChart[] charts = new JFreeChart[signals.size()];

		for (int i = 0; i < signals.size(); i++) {
			Signal s = signals.get(i);
			charts[i] = createChart(s);
		}

		return charts;

	}

	protected JFreeChart createChart(Signal s) {
		String name = s.getName();
		XYSeries xyserie = new XYSeries(name);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyserie);
		xyseries.put(s, xyserie);
		return createChart(xyseriescollection, name);
	}
	
	

}
