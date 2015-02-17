package simulator.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import simulator.MultipleDigitalValue;
import simulator.digitalcomponents.MemModule;

public class Mem extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField address;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public Mem() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);

		address = new JTextField();
		panel.add(address);
		address.setColumns(10);

		JButton btnGo = new JButton("GO");
		getRootPane().setDefaultButton(btnGo);
		btnGo.addActionListener(goAction);
		panel.add(btnGo);

		String[] columnNames = { "Address", "Value" };

		Object[][] data = new Object[MemModule.bytes.size()][2];

		for (int i = 0; i < MemModule.bytes.size(); i++) {
			data[i][0] = Integer.toHexString(i);
			data[i][1] = Integer.toHexString(MemModule.bytes.get(i)
					.getUIntValue());
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout());

		table = new JTable(data, columnNames);
		table.getModel().addTableModelListener(tableModeListener);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		panel_1.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Close");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(closeAction);
			}
		}
	}

	ActionListener closeAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};

	ActionListener goAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int i = Integer.parseInt(address.getText(),16);
			table.getSelectionModel().setSelectionInterval(i, i);
			table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
		}
	};
	
	TableModelListener tableModeListener = new TableModelListener() {
		
		@Override
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
	        TableModel model = (TableModel)e.getSource();
	        String addr = (String) model.getValueAt(row, 0);
	        String val = (String) model.getValueAt(row, 1);
	        MultipleDigitalValue mdv = MemModule.bytes.get(Integer.parseInt(addr,16));
	        mdv.setUIntValue(Integer.parseInt(val, 16));
	        JOptionPane.showMessageDialog(null,"Value on address " + addr + " is now set to " + val);
		}
	};

}
