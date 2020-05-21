
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Base2 extends JPanel implements ActionListener, ChangeListener, ItemListener {
	private JComboBox<String> especialidad, lista; 
	private JLabel uno;
	private JTextField dos; 
	private JButton ir, e1,b3;
	private String a, b, c, bcod; 
	private String busqueda= null;
	private int pulsa=0, valor;

	public static float dinerocirugia;
	private ArrayList<JLabel> numero, contenido; 
	private ArrayList<JPanel> lisres;
	public Base2() {
		lisres=new ArrayList<>();
		numero = new ArrayList<>();
		contenido= new ArrayList<>();
		setLayout(new GridLayout(0,1,0,0));
		JPanel pa1= new JPanel();
		add(pa1);
		pa1.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
		
		especialidad = new JComboBox<String>(); 
		pa1.add(especialidad);
		especialidad.addItem("Seleccionar especialidad quirurgica");
		especialidad.addItem("Cirugia Vascular"); 		especialidad.addItem("Cirugia Cardiovascular");
		especialidad.addItem("Cirugia Pediatrica");		especialidad.addItem("Cirugia General y del Aparato Digestivo");
		especialidad.addItem("Cirugia Maxilofacial");
		especialidad.addItem("Cirugia Plastica");
		especialidad.addItem("Cirugia Toracica");
		especialidad.addItem("Dermatologia");
		especialidad.addItem("Ginecologia y Obstetricia");
		especialidad.addItem("Neurocirugia");
		especialidad.addItem("Oftalmologia");
		especialidad.addItem("ORL");
		especialidad.addItem("Traumatologia");
		especialidad.addItem("Urologia");
		especialidad.addItemListener(this);
		
		uno = new JLabel("Busqueda por palabras:");
		pa1.add(uno);
		dos= new JTextField(30); 
		pa1.add(dos);
		ir = new JButton("Buscar");
		pa1.add(ir);
		ir.addActionListener(this);	
		
		JPanel pa2= new JPanel();
		add(pa2);
		pa2.setLayout(new FlowLayout(FlowLayout.LEFT,40,20));

		lista = new JComboBox<String>(); 		
		pa2.add(lista);
		e1 = new JButton ("Grupo quirurgico");		
		pa2.add(e1);
		e1.addActionListener(this);
		
		b3= new JButton("Borrar listado");
		pa2.add(b3);
		b3.addActionListener(this);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== especialidad){
			a = (String)especialidad.getSelectedItem();
		}
		if(e.getSource()==lista){
			String prueb1=(String)lista.getSelectedItem();
			c = prueb1;
		}			
		
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if(a =="Cirugia Vascular"){
				busqueda = "SELECT * FROM 'Cirugia Vascular' WHERE Nombre LIKE ?";
			}
			if(a =="Cirugia Cardiovascular"){
				busqueda = "SELECT * FROM 'Cirugia Cardiovascular' WHERE Nombre LIKE ?";
			}
			if(a =="Cirugia General y del Aparato Digestivo"){
				busqueda = "SELECT * FROM 'Cirugia General y Aparato Digestivo' WHERE Nombre LIKE ?";
			}
			if(a =="Cirugia Maxilofacial"){
				busqueda = "SELECT * FROM 'Cirugia Maxilofacial' WHERE Nombre LIKE ?";
			}
			if(a =="Cirugia Pediatrica"){
				busqueda = "SELECT * FROM 'Cirugia Pediatrica' WHERE Nombre LIKE ?";
			}
			if(a =="Cirugia Plastica"){
				busqueda = "SELECT * FROM 'Cirugia Plastica' WHERE Nombre LIKE ?";
			}
			if(a =="Cirugia Toracica"){
				busqueda = "SELECT * FROM 'Cirugia Toracica' WHERE Nombre LIKE ?";
			}
			if(a =="Dermatologia"){
				busqueda = "SELECT * FROM 'Dermatologia' WHERE Nombre LIKE ?";
			}
			if(a =="Ginecologia y Obstetricia"){
				busqueda = "SELECT * FROM 'Ginecologia' WHERE Nombre LIKE ?";
			}
			if(a =="Neurocirugia"){
				busqueda = "SELECT * FROM 'Neurocirugia' WHERE Nombre LIKE ?";
			}
			if(a =="Oftalmologia"){
				busqueda = "SELECT * FROM 'Oftalmologia' WHERE Nombre LIKE ?";
			}
			if(a =="ORL"){
				busqueda = "SELECT * FROM 'ORL' WHERE Nombre LIKE ?";
			}
			if(a =="Traumatologia"){
				busqueda = "SELECT * FROM 'Traumatologia' WHERE Nombre LIKE ?";
			}
			if(a =="Urologia"){
				busqueda = "SELECT * FROM 'Urologia' WHERE Nombre LIKE ?";
			}
			
			if(e.getSource()==ir){
				lista.removeAllItems(); 
				b = dos.getText();
				b = b.toUpperCase();
				bcod = b;
				try (Connection union = this.conectar();
						PreparedStatement ps  = union.prepareStatement(busqueda)){
							ps.setString(1, "%"+bcod+"%");
							ResultSet respuesta = ps.executeQuery();
							while (respuesta.next()) {
								String valor = respuesta.getString("Nombre");
								String valor2= valor;
								lista.addItem(valor2);	            }
							lista.addItemListener(this);
				
				}catch (SQLException f) {
				     System.out.println(f.getMessage());
				 }
			}
			
			if(e.getSource()==b3){
				valor=pulsa;
				for(int i=0; i<valor; i++){
					remove(lisres.get(i));
				}
				especialidad.setSelectedIndex(0);
				lista.removeAllItems();
				pulsa=0;
				dos.setText("");
				revalidate();
				
			}	
			if(e.getSource()==e1){
				
				try (Connection union = this.conectar();
						PreparedStatement ps  = union.prepareStatement(busqueda)){
							ps.setString(1, "%"+c+"%");
							ResultSet respuesta = ps.executeQuery();
								String grupo = respuesta.getString("Grupo");
								String valor= respuesta.getString("Nombre");
								String total= "Grupo "+grupo+"   "+valor;
								
								lisres.add(new JPanel());
								
								numero.add(new JLabel(String.valueOf(pulsa+1)+") "));
								lisres.get(pulsa).add(numero.get(pulsa));
								
								contenido.add(new JLabel(total));
								lisres.get(pulsa).add(contenido.get(pulsa));
								
								add(lisres.get(pulsa));
								lisres.get(pulsa).setLayout(new FlowLayout(FlowLayout.LEFT, 5,0));
								pulsa++;
								
								revalidate();
						
				}catch (SQLException f) {
				     System.out.println(f.getMessage());
				 }
			}
		
	}
	private Connection conectar() {
		//Class.forName("org.sqlite.JDBC");
        String dir = "jdbc:sqlite:BasesDatos/GQuirurgicos.db";
        Connection union = null;
        try {
            union = DriverManager.getConnection(dir);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return union;
    }
	
}
