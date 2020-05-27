
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
	private JComboBox<String> categorias, lista; 
	private JTextField dos; 
	private JButton ir, e1,b3;
	private String a, b, c, bcod; 
	private String busqueda= null;
	private int pulsa=0, valoir categ;
	private JScrollPane barra; 
	private ArrayList<JPanel> lisres;
	public Base4() {
		lisres=new ArrayList<>();

		setLayout(new GridLayout(0,1,0,0));
		JPanel todo =Utilidades.panelVertical(); 
		add(todo); 
		barra= new JScrollPane();
		add(barra);
		barra.setViewportView(todo);
		JPanel pa1= Utilidades.pLeft(todo, 20, 10);

		String [] liscategorias={"Seleccionar categor\u00eda","Primera categor\u00eda","Segunda categor\u00eda","Tercera categor\u00eda","Cuarta categor\u00eda","Quinta categor\u00eda","Sexta categor\u00eda","S\u00e9ptima categor\u00eda","Octava categor\u00eda","Novena categor\u00eda","D\u00e9cima categor\u00eda","Und\u00e9cima categor\u00eda","Duod\u00e9cima categor\u00eda","Decimotercera categor\u00eda","Decimocuarta categor\u00eda","Cualquier categor\u00eda"};
		categorias = new JComboBox<String>(liscategorias); 
		pa1.add(categorias);		
		categorias.addItemListener(this);
		
		Utilidades.nuevosLabel("B\u00fasqueda por palabras:", pa1);
		dos=Utilidades.nuevosTF(30, pa1);		
		ir =Utilidades.nuevoBoton("Buscar", pa1, this);
		
		JPanel pa2=  Utilidades.pLeft(todo, 40, 20);
		lista = new JComboBox<String>(); 		
		pa2.add(lista);
		e1 = Utilidades.nuevoBoton("A\u00f1adir", pa2, this);				
		b3= Utilidades.nuevoBoton("Borrar listado", pa2, this);

		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== especialidad){
			categ=especialidad.getSelectedIndex();
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
		if(categ==15) {
			busqueda=Utilidades.busquedaSimilar("SOVI","Lesion");
		}else {
			busqueda=Utilidades.busquedaMezcla("SOVI", "Lesion", "Categoria", String.valueOf(categ));
		}
			
			if(e.getSource()==ir){
				lista.removeAllItems(); 
				b = dos.getText();
				try (Connection union = this.conectar();
						PreparedStatement ps  = union.prepareStatement(busqueda)){
							ps.setString(1, "%"+b+"%");
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
								
								lisres.add(Utilidades.mostrarListado(pulsa,total));
								add(lisres.get(pulsa));
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

