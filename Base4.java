
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
public class Base4 extends JPanel implements ActionListener, ItemListener {
	private JComboBox<String> especialidad, lista; 
	private JLabel uno;
	private JTextField dos; 
	private JButton ir, e1,b3;
	private String a, b, c, bcod; 
	private String busqueda= null;
	private int pulsa=0, valoir;

	public static float dinerocirugia;
	private ArrayList<JLabel> numero, contenido; 
	private ArrayList<JPanel> lisres;
	public Base4() {
		lisres=new ArrayList<>();
		numero = new ArrayList<>();
		contenido= new ArrayList<>();
		setLayout(new GridLayout(0,1,0,0));
		JPanel pa1= new JPanel();
		add(pa1);
		pa1.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
		
		especialidad = new JComboBox<String>(); 
		pa1.add(especialidad);
		especialidad.addItem("Seleccionar categor\u00eda");
		especialidad.addItem("Cualquier categor\u00eda");
		especialidad.addItem("Primera categor\u00eda");
		especialidad.addItem("Segunda categor\u00eda");
		especialidad.addItem("Tercera categor\u00eda");
		especialidad.addItem("Cuarta categor\u00eda");
		especialidad.addItem("Quinta categor\u00eda");
		especialidad.addItem("Sexta categor\u00eda");
		especialidad.addItem("S\u00e9ptima categor\u00eda");
		especialidad.addItem("Octava categor\u00eda");
		especialidad.addItem("Novena categor\u00eda");
		especialidad.addItem("D\u00e9cima categor\u00eda");
		especialidad.addItem("Und\u00e9cima categor\u00eda");
		especialidad.addItem("Duod\u00e9cima categor\u00eda");
		especialidad.addItem("Decimotercera categor\u00eda");
		especialidad.addItem("Decimocuarta categor\u00eda");
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
		e1 = new JButton ("A\u00f1adir");		
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
		int num=lista.getItemCount();
		if(num!=0){
		if(e.getSource()==lista){
			String prueb1=(String)lista.getSelectedItem();
			c = prueb1;
		}	}		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if(a =="Cualquier categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ?";
			}
			if(a =="Primera categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 1";
			}
			if(a =="Segunda categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 2";
			}
			if(a =="Tercera categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 3";
			}
			if(a =="Cuarta categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 4";
			}
			if(a =="Quinta categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 5";
			}
			if(a =="Sexta categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 6";
			}
			if(a =="S\u00e9ptima categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 7";
			}
			if(a =="Octava categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 8";
			}
			if(a =="Novena categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 9";
			}
			if(a =="D\u00e9cima categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 10";
			}
			if(a =="Und\u00e9cima categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 11";
			}
			if(a =="Duod\u00e9cima categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 12";
			}
			if(a =="Decimotercera categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 13";
			}
			if(a =="Decimocuarta categor\u00eda"){
				busqueda = "SELECT * FROM SOVI WHERE Lesion LIKE ? AND Categoria= 14";
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
								String valor = respuesta.getString("Lesion");
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
				revalidate();
				pulsa=0;
			}	
			if(e.getSource()==e1){
				
				try (Connection union = this.conectar();
						PreparedStatement ps  = union.prepareStatement(busqueda)){
							ps.setString(1, "%"+c+"%");
							ResultSet respuesta = ps.executeQuery();
								String categ = respuesta.getString("Categoria");
								String valor= respuesta.getString("Lesion");
								String dinero= respuesta.getString("Indemnizacion");
								String total= valor+"       Categor\u00eda "+categ+"   "+dinero+" Euros";
								
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
        String dir = "jdbc:sqlite:BasesDatos/SOVI.db";
        Connection union = null;
        try {
            union = DriverManager.getConnection(dir);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return union;
    }
	


}

