
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

import javax.swing.JScrollPane;
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
	private int pulsa=0, valoir, esp;
	private JScrollPane barra;
	private ArrayList<JPanel> lisres;
	public Base2() {
		lisres=new ArrayList<>();
		setLayout(new GridLayout(0,1,0,0));
		
		JPanel todo =Utilidades.panelVertical(); 
		add(todo); 
		barra= new JScrollPane();
		add(barra);
		barra.setViewportView(todo);
		
		JPanel pa1= Utilidades.pLeft(todo, 10, 20);
				
		
		String [] lisespecialidades={"Seleccionar especialidad quir\u00fargica","Cirug\u00eda Vascular","Cirug\u00eda Cardiovascular","Cirug\u00eda Pedi\u00e1trica","Cirug\u00eda General y Aparato Digestivo","Cirug\u00eda Maxilofacial","Cirug\u00eda Pl\u00e1stica","Cirug\u00eda Tor\u00e1cica","Dermatolog\u00eda","Ginecolog\u00eda y Obstetricia","Neurocirug\u00eda","Oftalmolog\u00eda","ORL","Traumatolog\u00eda","Urolog\u00eda"};
		especialidad = new JComboBox<String>(lisespecialidades); 
		pa1.add(especialidad);
		especialidad.addItemListener(this);
		
		Utilidades.nuevosLabel("B\u00fasqueda por palabras:", pa1);
		
		dos= Utilidades.nuevosTF(30,pa1);
		ir = Utilidades.nuevoBoton("Buscar",pa1, this);
		
		
		JPanel pa2=Utilidades.pLeft(todo, 40, 20);
		

		lista = new JComboBox<String>(); 		
		pa2.add(lista);
		
		JPanel pa3= Utilidades.pLeft(todo, 40, 20);
		
		
		e1 = Utilidades.nuevoBoton("Grupo quir\u00fargico", pa3, this);		
		
		
		b3= Utilidades.nuevoBoton("Borrar listado", pa3, this);
		
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== especialidad){
			a = (String)especialidad.getSelectedItem();
			esp=especialidad.getSelectedIndex();
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
		if(a =="Ginecolog\u00eda y Obstetricia"){
				busqueda = "SELECT * FROM 'Ginecolog\u00eda' WHERE Nombre LIKE ?";
		}else{
			busqueda=Utilidades.busquedaSimilar("'"+a+"'", "Nombre");}
			
			
			if(e.getSource()==ir){
				lista.removeAllItems(); 
				b = dos.getText();
				
				try (Connection union = this.conectar();
						PreparedStatement ps  = union.prepareStatement(busqueda)){
							ps.setString(1, "%"+b+"%");
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
								
								lisres.add(Utilidades.mostrarListado(pulsa, total));
								add(lisres.get(pulsa));
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
