
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


@SuppressWarnings("serial")
public class Base2 extends JPanel implements ActionListener, ItemListener {
	private JComboBox<String> especialidad, lista; 
	private JLabel uno;
	private JTextField dos; 
	private JButton ir, e1,b3;
	private String a, b, c, bcod; 
	private String busqueda= null;
	private int pulsa=0, valoir;
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
		
		String [] lisespecialidades={"Seleccionar especialidad quir\u00fargica","Cirug\u00eda Vascular","Cirug\u00eda Cardiovascular","Cirug\u00eda Pedi\u00e1trica","Cirug\u00eda General y del Aparato Digestivo","Cirug\u00eda Maxilofacial","Cirug\u00eda Pl\u00e1stica","Cirug\u00eda Tor\u00e1cica","Dermatolog\u00eda","Ginecolog\u00eda y Obstetricia","Neurocirug\u00eda","Oftalmolog\u00eda","ORL","Traumatolog\u00eda","Urolog\u00eda"};
		especialidad = new JComboBox<String>(lisespecialidades); 
		pa1.add(especialidad);
		especialidad.addItemListener(this);
		
		uno = new JLabel("B\u00fasqueda por palabras:");
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
		
		JPanel pa3= new JPanel();
		add(pa3);
		pa3.setLayout(new FlowLayout(FlowLayout.LEFT,40,20));
		
		e1 = new JButton ("Grupo quir\u00fargico");		
		pa3.add(e1);
		e1.addActionListener(this);
		
		b3= new JButton("Borrar listado");
		pa3.add(b3);
		b3.addActionListener(this);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== especialidad){
			a = (String)especialidad.getSelectedItem();
		}
		if(e.getSource()==lista){
			int num=lista.getItemCount();
			if(num!=0){
			String prueb1=(String)lista.getSelectedItem();
			c = prueb1;}
		}			
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if(a =="Cirug\u00eda Vascular"){
				busqueda = "SELECT * FROM 'Cirug\u00eda Vascular' WHERE Nombre LIKE ?";
			}
			if(a =="Cirug\u00eda Cardiovascular"){
				busqueda = "SELECT * FROM 'Cirug\u00eda Cardiovascular' WHERE Nombre LIKE ?";
			}
			if(a =="Cirug\u00eda General y del Aparato Digestivo"){
				busqueda = "SELECT * FROM 'Cirug\u00eda General y Aparato Digestivo' WHERE Nombre LIKE ?";
			}
			if(a =="Cirug\u00eda Maxilofacial"){
				busqueda = "SELECT * FROM 'Cirug\u00eda Maxilofacial' WHERE Nombre LIKE ?";
			}
			if(a =="Cirug\u00eda Pedi\u00e1trica"){
				busqueda = "SELECT * FROM 'Cirug\u00eda Pedi\u00e1trica' WHERE Nombre LIKE ?";
			}
			if(a =="Cirug\u00eda Pl\u00e1stica"){
				busqueda = "SELECT * FROM 'Cirug\u00eda Pl\u00e1stica' WHERE Nombre LIKE ?";
			}
			if(a =="Cirug\u00eda Tor\u00e1cica"){
				busqueda = "SELECT * FROM 'Cirug\u00eda Tor\u00e1cica' WHERE Nombre LIKE ?";
			}
			if(a =="Dermatolog\u00eda"){
				busqueda = "SELECT * FROM 'Dermatolog\u00eda' WHERE Nombre LIKE ?";
			}
			if(a =="Ginecolog\u00eda y Obstetricia"){
				busqueda = "SELECT * FROM 'Ginecolog\u00eda' WHERE Nombre LIKE ?";
			}
			if(a =="Neurocirug\u00eda"){
				busqueda = "SELECT * FROM 'Neurocirug\u00eda' WHERE Nombre LIKE ?";
			}
			if(a =="Oftalmolog\u00eda"){
				busqueda = "SELECT * FROM 'Oftalmolog\u00eda' WHERE Nombre LIKE ?";
			}
			if(a =="ORL"){
				busqueda = "SELECT * FROM 'ORL' WHERE Nombre LIKE ?";
			}
			if(a =="Traumatolog\u00eda"){
				busqueda = "SELECT * FROM 'Traumatolog\u00eda' WHERE Nombre LIKE ?";
			}
			if(a =="Urolog\u00eda"){
				busqueda = "SELECT * FROM 'Urolog\u00eda' WHERE Nombre LIKE ?";
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
								lista.addItem(valor);	            }
							lista.addItemListener(this);
				
				}catch (SQLException f) {
				     System.out.println(f.getMessage());
				 }
			}
			
			if(e.getSource()==b3){
				valoir=pulsa;
				for(int i=0; i<valoir; i++){
					remove(lisres.get(i));
				}
				especialidad.setSelectedIndex(0);
				lista.removeAllItems();
				lisres.clear();
				numero.clear();
				contenido.clear();
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