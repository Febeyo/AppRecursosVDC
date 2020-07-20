package bases;

import fondo.PanelBusquedaSimple;
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
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Base2  extends PanelBusquedaSimple implements ActionListener, ItemListener{
	private JComboBox<String>  lista; 
	private JButton e1;
	private String a, b, c; 
	private String busqueda= null;
	private int pulsa=0, valoir;
	private ArrayList<JPanel> lisres;
	
	public Base2() {
		
		lisres=new ArrayList<>();
		String [] lisespecialidades={"Seleccionar especialidad quir\u00fargica","Cirug\u00eda Vascular","Cirug\u00eda Cardiovascular","Cirug\u00eda Pedi\u00e1trica","Cirug\u00eda General y Aparato Digestivo","Cirug\u00eda Maxilofacial","Cirug\u00eda Pl\u00e1stica","Cirug\u00eda Tor\u00e1cica","Dermatolog\u00eda","Ginecolog\u00eda y Obstetricia","Neurocirug\u00eda","Oftalmolog\u00eda","ORL","Traumatolog\u00eda","Urolog\u00eda"};
		lista1 = new JComboBox<String>(lisespecialidades); 
		p1.add(lista1);
		lista1.addItemListener(this);
		
		lista = new JComboBox<String>(); 		
		p2.add(lista);
		e1 = Utilidades.nuevoBoton("Grupo quir\u00fargico", p2, this);		
		
		
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== lista1){
			a = (String)lista1.getSelectedItem();
			//esp=lista1.getSelectedIndex();
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
			
			
			if(e.getSource()==b1){
				lista.removeAllItems(); 
				b = tf1.getText();
				
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
			
			if(e.getSource()==nuevo){
				limpiar();
				
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
								p4.add(lisres.get(pulsa));
								pulsa++;
								revalidate();
						
				}catch (SQLException f) {
				     System.out.println(f.getMessage());
				 }
			}
		
	}
	private Connection conectar() {
        String dir = "jdbc:sqlite:src/BasesDatos/GQuirurgicos.db";
        Connection union = null;
        try {
            union = DriverManager.getConnection(dir);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return union;
    }
	
	public void limpiar() {
		valoir=pulsa;
		for(int i=0; i<valoir; i++){
			remove(lisres.get(i));
		}
		lista1.setSelectedIndex(0);
		lista.removeAllItems();
		lisres.clear();
		pulsa=0;
		tf1.setText("");
		p4.removeAll(); 
		revalidate();
		
	}
	
}


