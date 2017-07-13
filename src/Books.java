import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Books extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtDate;
	private JTable table;

	public Books() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(23, 11, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDate = new JLabel("DATE");
		lblDate.setBounds(23, 48, 46, 14);
		contentPane.add(lblDate);
		
		txtName = new JTextField();
		txtName.setBounds(66, 8, 86, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setBounds(66, 45, 86, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name = txtName.getText();
				String date = txtDate.getText();
				
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "Debes colocar nombre");
				}else if(date.equals("")){
					JOptionPane.showMessageDialog(null, "Debes colocar fecha");
				}else{	
					Book conex = new Book();
					conex.setBook(name, date);
					getTableBooks();
				}
			}
		});
		btnAdd.setBounds(63, 87, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTableBooks();
			}
		});
		btnGet.setBounds(63, 117, 89, 23);
		contentPane.add(btnGet);
		
		table = new JTable();
		table.setBounds(177, 11, 247, 154);
		contentPane.add(table);
	}
	
	public void getTableBooks(){
		try{
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
			Book conex = new Book();
			conex.getAllBooks();
			ResultSetMetaData rsMd = conex.resultSet.getMetaData();
			int columns = rsMd.getColumnCount();
            for(int i=1; i<= columns; i++){
	            modelo.addColumn(rsMd.getColumnLabel(i));
	        }
            while(conex.resultSet.next()){
            Object[] fila = new Object[columns];
            for(int i=0; i< columns; i++){
                fila[i] = conex.resultSet.getObject(i+1);
            }
            modelo.addRow(fila);
            }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
