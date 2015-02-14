package simulator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import simulator.*;

public class ValueTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JFrame parent;
	JTable table;
	JTextArea area;

	List<Signal> signals;

	public ValueTablePanel(JFrame guiFrame) {
		super(new GridLayout(2, 0));
		this.parent = guiFrame;

		table = new JTable(new MyTableModel());
		table.getSelectionModel().addListSelectionListener(
				new MyListSelectionListener());

		table.setPreferredScrollableViewportSize(new Dimension(50, 70));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);

		area = new JTextArea();
		area.setEditable(false);
		area.setBackground(Color.magenta);
		add(area);

		signals = new LinkedList<Signal>();
	}

	public void addSignal(Signal signal) {
		signals.add(signal);
	}

	public void addSignals(List<Signal> signals) {
		for(Signal signal : signals){
			if(signal.getName().startsWith("CLK")){
				this.signals.add(signal);
			}
		}
	}

	public void removeSignal(Signal signal) {
		signals.remove(signal);
	}

	public void removeSignals(List<Signal> signals) {
		this.signals.remove(signals);
	}

	class MyListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			int viewRow = table.getSelectedRow();
			if (viewRow >= 0) {
				area.setText(signals.get(viewRow).toString());
			}
		}
	}

	class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;

		private String[] columnNames = { "Signal", "Value" };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return signals.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			if (col == 0) {
				return signals.get(row).getName();
			} else {
				return signals.get(row).getValue().getBooleanValue();
			}
		}

		public Class<?> getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			if (col < 1) {
				return false;
			} else {
				return true;
			}
		}

		public void setValueAt(Object value, int row, int col) {
			try {
				signals.get(row).getValue().setBooleanValue((Boolean) value);
				area.setText(signals.get(row).toString());
				fireTableCellUpdated(row, col);
				parent.repaint();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
