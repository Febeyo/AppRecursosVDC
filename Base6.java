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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Base6 extends JPanel implements ActionListener, ItemListener{
	private JComboBox<String> grupo, funcion, subgrupo, cu,  d1, d2, d3, d4, d5, d6, d7, d8, d9; 
	private ArrayList<String> c1, e1, c2, c3, c4, c5, c6, c7, c8, c9;
	private JButton buscar, aceptar, nuevo; 
	private JScrollPane barra;
	private int n1,pulsa, tu, t1, t2, t3, t4, t5, t6, t7, t8, t9; 
	private String gr,tipo1, sb; 
	private JPanel pa2, pa3, pc1, pc2, pc3, pc4;
	private double precio=0.0; 
	public Base6() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(0,1,0,0));
		pulsa=0; 
		e1= new ArrayList<String>(); 
		c1= new ArrayList<String>(); 
		c2=new ArrayList<String>(); 
		c3=new ArrayList<String>(); 
		c4=new ArrayList<String>(); 
		c5=new ArrayList<String>(); 
		c6=new ArrayList<String>(); 
		c7=new ArrayList<String>(); 
		c8 =new ArrayList<String>(); 
		c9 =new ArrayList<String>(); 
		JPanel todo =Utilidades.panelVertical(); 
		add(todo); 
		barra= new JScrollPane();
		add(barra);
		barra.setViewportView(todo);
		JPanel pa1= Utilidades.pLeft(todo, 10, 20);
		String[] n1= {"Selecciona pr\u00f3tesis", "Pr\u00f3tesis miembro superior", "Pr\u00f3tesis miembro inferior"};
		grupo=Utilidades.listado(n1, pa1, this);
		grupo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rellenaCombo(grupo.getSelectedIndex()); 
			}
		});
		funcion=new JComboBox<String>(); 
		rellenaCombo(grupo.getSelectedIndex());
		pa1.add(funcion); 
		
		funcion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rellenaCombo2(grupo.getSelectedIndex(), funcion.getSelectedIndex()); 
			}
		});
		subgrupo=new JComboBox<String>(); 
		rellenaCombo2(grupo.getSelectedIndex(),funcion.getSelectedIndex());
		pa1.add(subgrupo);
		subgrupo.addItemListener(this);
		
		buscar=Utilidades.nuevoBoton("Buscar", pa1, this);
		
		pa2=Utilidades.pLeft(todo, 5, 5);	
		pc1=Utilidades.pLeft(todo, 5, 5);	
		pc2=Utilidades.pLeft(todo, 5, 5);	
		pc3=Utilidades.pLeft(todo, 5, 5);	
		pc4=Utilidades.pLeft(todo, 5, 5);	
		
		
		JPanel pa21=Utilidades.pCentro(todo, 5, 5);
		aceptar=Utilidades.nuevoBoton("Aceptar", pa21, this);
		nuevo=Utilidades.nuevoBoton("Limpiar", pa21, this);
		
		pa3=Utilidades.panelVertical();
		todo.add(pa3);	

	}

	public void rellenaCombo2(int v, int vv){
		String[]n2= {"Selecciona pr\u00f3tesis", "Pr\u00f3tesis parciales de mano","Pr\u00f3tesis desarticulaci\u00f3n mu\u00f1eca","Pr\u00f3tesis de antebrazo","Pr\u00f3tesis desarticulaci\u00f3n codo", "Pr\u00f3tesis de brazo", "Pr\u00f3tesis desarticulaci\u00f3n hombro", "Pr\u00f3tesis amputaci\u00f3n interescapulotor\u00e1cica"};
		String[]n3= {"Selecciona pr\u00f3tesis", "Pr\u00f3tesis parciales de pie", "Pr\u00f3tesis desarticulaci\u00f3n tobillo","Pr\u00f3tesis transtibial", "Pr\u00f3tesis desarticulaci\u00f3n rodilla","Pr\u00f3tesis transfemoral","Pr\u00f3tesis desarticulaci\u00f3n cadera","Pr\u00f3tesis hemipelvectom\u00eda"};
			subgrupo.removeAllItems();
			if(v==1 && vv==1){
				sb="0";
				rellenaComboGeneral(subgrupo, n2);
				subgrupo.setEnabled(true);
			}else if(v==1 && vv==2){
				gr="0621";
				sb="062100"; 
				subgrupo.setEnabled(false);
			}else if (v==2 && vv==1){
				sb="062448"; 
				gr="0624";
				subgrupo.setEnabled(false);
			}else if(v==2 && vv==2){
				sb="0";
				rellenaComboGeneral(subgrupo, n3);
				subgrupo.setEnabled(true);
				
			}
	}
	
	public void rellenaComboGeneral(JComboBox<String> lis, String[]a) {
		for(String i: a) {
			lis.addItem(i);
		}
	}
	
	
	public void rellenaCombo(int v){
		String[] n4= {"Selecciona pr\u00f3tesis","Funcional","Est\u00e9tica"};
		String[] n5= {"Selecciona pr\u00f3tesis","Provisional", "Definitiva"};
			funcion.removeAllItems();
			if(v==1){
				gr="0618";
				rellenaComboGeneral(funcion, n4);
				
			}else if(v==2){
				gr="0624";
				rellenaComboGeneral(funcion,n5);
			}
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==subgrupo) {
			n1=subgrupo.getSelectedIndex();
			tipo1=String.valueOf(n1);
		}
		if(arg0.getSource()==d1) {
			if(d1.getItemCount()>1) {
				t1=d1.getSelectedIndex();
			}
		}
		if(arg0.getSource()==cu) {
			if(cu.getItemCount()>1) {
				tu=cu.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d2) {
			if(d2.getItemCount()>1) {
				t2=d2.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d3) {
			if(d3.getItemCount()>1) {
				t3=d3.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d4) {
			if(d4.getItemCount()>1) {
				t4=d4.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d5) {
			if(d5.getItemCount()>1) {
				t5=d5.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d6) {
			if(d6.getItemCount()>1) {
				t6=d6.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d7) {
			if(d7.getItemCount()>1) {
				t7=d7.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d8) {
			if(d8.getItemCount()>1) {
				t8=d8.getSelectedIndex();
			}
		}
		if(arg0.getSource()==d9) {
			if(d9.getItemCount()>1) {
				t9=d9.getSelectedIndex();
			}
		}

	}
	public void limpiarPanel() {
		pa2.removeAll();
		pc1.removeAll();
		pc2.removeAll();
		pc3.removeAll();
		pc4.removeAll();
		pa3.removeAll();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==buscar) {
			if(pulsa>=1) {
				limpiarPanel();
			}
			
			if(sb.equals("062100")) {
				Utilidades.nuevosLabel("Componente \u00fanico", pa2);
				cu=Utilidades.nuevoListado(pa2, this);
				revalidate();
				busqCompSimple(gr, "Unico", e1, cu);
			}else if(sb.equals("062448")){
				Utilidades.nuevosLabel("Componente \u00fanico", pa2);
				cu=Utilidades.nuevoListado(pa2, this);
				revalidate();
				busqCompSimple(gr, "Inicio", e1, cu);
			}else if (sb.equals("0")){
				if(tipo1.equals("1")) {
					Utilidades.nuevosLabel("Componente \u00fanico", pa2);
					cu=Utilidades.nuevoListado(pa2, this);
					revalidate();
					busqCompSimple(gr, "Unico", e1, cu);
				}else {
					componentes();
					if(gr.equals("0618")) {
						busqComponentes("0618", tipo1, "P1", c1, d1);
						busqComponentes("0618", tipo1, "P2", c2, d2);
						busqComponentes("0618", tipo1, "P3", c3, d3);
						busqCompSimple("0618", "P4", c4, d4);
						busqCompSimple("0618", "Art1", c5, d5);
						busqComponentes("0618", tipo1, "Ext", c8, d8);
						busqComponentes("0618", tipo1, "Est", c9, d9);
						if(tipo1.equals("4")||tipo1.equals("5")){
							busqCompSimple("0618", "Art2", c6, d6);
						}else if (tipo1.equals("6")||tipo1.equals("7")){
							busqCompSimple("0618", "Art2", c6, d6);
							busqCompSimple("0618", "Art3", c7, d7);
						}
						deshabilita(d1,d2, d3, d4, d5, d6,d7, d8, d9);

					}else {
						busqComponentes("0624", tipo1, "P1", c1, d1);
						busqComponentes("0624", tipo1, "P2", c2, d2);
						busqCompSimple("0624", "P4", c4, d4);
						busqComponentes("0624", tipo1, "Ext", c8, d8);
						busqComponentes("0624", tipo1, "Est", c9, d9);
						if(tipo1.equals("4")||tipo1.equals("5")){
							busqCompSimple("0624", "Art1", c5, d5);
						}else if (tipo1.equals("6")||tipo1.equals("7")){
							busqCompSimple("0624", "Art1", c5, d5);
							busqCompSimple("0624", "Art2", c6, d6);
						}
						deshabilita(d1,d2, d3, d4, d5, d6,d7, d8, d9);
					
					}
				}
			}
			pulsa++; 
		}
		if(arg0.getSource()==aceptar) {
			if(sb.equals("062100")|| sb.equals("062448")|| tipo1.equals("1")) {
				if(cu.getItemCount()>1 && (tu-1)!=-1) {
					JPanel pa3u=Utilidades.pLeft(pa3, 5,5);
					Utilidades.nuevosLabel(cu.getSelectedItem().toString()+"  "+e1.get(tu-1).toString(), pa3u);
					revalidate();
				}
			}else {
			if(d1.getItemCount()>1 && t1-1!=-1) {
				JPanel pa31=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d1.getSelectedItem().toString()+"  "+c1.get(t1-1).toString(), pa31);
				precio=precio+precios(c1, t1-1);
			}
			
			if(d2.getItemCount()>1 && t2-1!=-1) {
				JPanel pa32=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d2.getSelectedItem().toString()+"  "+c2.get(t2-1).toString(), pa32);
				precio=precio+precios(c2, t2-1);
			}
			if(d3.getItemCount()>1 && t3-1!=-1) {
				JPanel pa33=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d3.getSelectedItem().toString()+"  "+c3.get(t3-1).toString(), pa33);
				precio=precio+precios(c3, t3-1);
			}
			if(d4.getItemCount()>1 && t4-1!=-1) {
				JPanel pa34=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d4.getSelectedItem().toString()+"  "+c4.get(t4-1).toString(), pa34);
				precio=precio+precios(c4, t4-1);
			}if(d5.getItemCount()>1 && t5-1!=-1) {
				JPanel pa35=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d5.getSelectedItem().toString()+"  "+c5.get(t5-1).toString(), pa35);
				precio=precio+precios(c5, t5-1);
			}
			if(d6.getItemCount()>1 && t6-1!=-1) {
				JPanel pa36=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d6.getSelectedItem().toString()+"  "+c6.get(t6-1).toString(), pa36);
				precio=precio+precios(c6, t6-1);
			}
			if(d7.getItemCount()>1 && t7-1!=-1) {
				JPanel pa37=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d7.getSelectedItem().toString()+"  "+c7.get(t7-1).toString(), pa37);
				precio=precio+precios(c7, t7-1);
			}
			if(d8.getItemCount()>1 && t8-1!=-1) {
				JPanel pa38=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d8.getSelectedItem().toString()+"  "+c8.get(t8-1).toString(), pa38);
				precio=precio+precios(c8, t8-1);
			}
			if(d9.getItemCount()>1 && t9-1!=-1) {
				JPanel pa39=Utilidades.pLeft(pa3, 5,5);
				Utilidades.nuevosLabel(d9.getSelectedItem().toString()+"  "+c9.get(t9-1).toString(), pa39);
				precio=precio+precios(c9, t9-1);
			}
			
			Utilidades.nuevosLabel("Precio total(IMF): "+String.format("%.3f", precio)+ " Euros", pa3);
			revalidate();
		}
			}
		if(arg0.getSource()==nuevo) {
			limpiarPanel(); 
			grupo.setSelectedIndex(0);
			Utilidades.limpiar(cu, d1,d2, d3, d4, d5, d6, d7, d8, d9);
			Utilidades.vaciar(e1, c1, c2, c3, c4, c5, c6, c7, c8, c9); 
		}
	}

	public Double precios(ArrayList<String> a, int tt) {
		String[] partes= a.get(tt).split(":");
		String v= partes[3].replace(" Euros", ""). trim(); 
		precio=Double.parseDouble(v.replace(",", "."));
		return precio;
	}
	
	@SafeVarargs
	public static void deshabilita(JComboBox<String>...jbc) {
		for(JComboBox<String> j:jbc) {
			if(j.getItemCount()<=1) {
				j.setEnabled(false);
			}
		}
	}
	public String busqueda2(String tipo, String comp) {
		String b ="SELECT * FROM Protesis WHERE Grupo = '0624' AND Tipo = "+"'"+tipo+"'"+"AND Componente = "+"'"+comp+"'";
		return b;
	}
	public void busqComponentes( String grupo, String tipo, String comp, ArrayList<String> al, JComboBox<String> jbc) {
		String busqueda="SELECT * FROM  Protesis WHERE Grupo = "+"'"+grupo+"'"+"AND Tipo = "+"'"+tipo+"'"+"AND Componente = "+"'"+comp+"'";
		try (Connection union = this.conectar();
				PreparedStatement ps  = union.prepareStatement(busqueda)){
					ResultSet respuesta = ps.executeQuery();
					while (respuesta.next()) {
						String valor = respuesta.getString("Descripcion");
						String v2=respuesta.getString("Subgrupo");
						String v3=respuesta.getString("Codigo");
						String v4=respuesta.getString("Renovacion");
						String v5=respuesta.getString("IMF");
						String inf="Codigo: "+v2+v3+" Vida Media: "+v4+" meses  IMF: "+v5+" Euros";
						al.add(inf);
						jbc.addItem(valor);
						}
		}catch (SQLException f) {
		     System.out.println(f.getMessage());
		 }
	}
	
	public void busqCompSimple( String grupo, String comp, ArrayList<String> al, JComboBox<String> jbc) {
		String busqueda="SELECT * FROM  Protesis WHERE Grupo = "+"'"+grupo+"'"+"AND Componente = "+"'"+comp+"'";
		try (Connection union = this.conectar();
				PreparedStatement ps  = union.prepareStatement(busqueda)){
					ResultSet respuesta = ps.executeQuery();
					while (respuesta.next()) {
						String valor = respuesta.getString("Descripcion");
						String v2=respuesta.getString("Subgrupo");
						String v3=respuesta.getString("Codigo");
						String v4=respuesta.getString("Renovacion");
						String v5=respuesta.getString("IMF");
						String inf="Codigo: "+v2+v3+" Vida Media: "+v4+" meses  IMF: "+v5+" Euros";
						al.add(inf);
						jbc.addItem(valor);
						}
		}catch (SQLException f) {
		     System.out.println(f.getMessage());
		 }
	}

	public void componentes() {
		Utilidades.nuevosLabel("Encaje", pa2);
		d1=Utilidades.nuevoListado(pa2, this);
		Utilidades.nuevosLabel("Sistema de suspensi\u00f3n", pc1);
		d2=Utilidades.nuevoListado(pc1, this);
		Utilidades.nuevosLabel("Fuente de energ\u00eda", pc1);
		d3=Utilidades.nuevoListado(pc1, this);
		Utilidades.nuevosLabel("Dispositivo terminal", pc2);
		d4=Utilidades.nuevoListado(pc2, this);
		Utilidades.nuevosLabel("Articulaci\u00f3n 1", pc3);
		d5=Utilidades.nuevoListado(pc3, this);
		Utilidades.nuevosLabel("Articulaci\u00f3n 2", pc3);
		d6=Utilidades.nuevoListado(pc3, this);
		Utilidades.nuevosLabel("Articulaci\u00f3n 3", pc3);
		d7=Utilidades.nuevoListado(pc3, this);
		
		Utilidades.nuevosLabel("Estructura externa", pc4);
		d8=Utilidades.nuevoListado(pc4, this);
		Utilidades.nuevosLabel("Funda est\u00e9tica", pc4);
		d9=Utilidades.nuevoListado(pc4, this);
		revalidate();
	}
	private Connection conectar() {
        String dir = "jdbc:sqlite:BasesDatos/CatalogoProtesis.db";
        Connection union = null;
        try {
            union = DriverManager.getConnection(dir);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return union;
    }

}
