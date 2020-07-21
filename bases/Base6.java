package bases;

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
import fondo.PanelBase;

public class Base6 extends PanelBase implements ActionListener, ItemListener{
	private JComboBox<String> grupo, funcion, subgrupo, cu,  d1, d2, d3, d4, d5, d6, d7, d8, d9; 
	private ArrayList<String> c1, e1, c2, c3, c4, c5, c6, c7, c8, c9;
	private JButton buscar, aceptar, nuevo; 
	private int n1,pulsa, tu, t1, t2, t3, t4, t5, t6, t7, t8, t9; 
	private String gr,tipo1, sb; 
	private JPanel pa2, pc1, pc2, pc3, pc4;
	private double precio=0.0; 
	public Base6() {
		// TODO Auto-generated constructor stub
		
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
		
		grupo=Utilidades.listado(new String[] {"Selecciona pr\u00f3tesis", "Pr\u00f3tesis miembro superior", "Pr\u00f3tesis miembro inferior"}, p1, this);
		grupo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rellenaCombo(grupo.getSelectedIndex()); 
			}
		});
		funcion=new JComboBox<String>(); 
		rellenaCombo(grupo.getSelectedIndex());
		p1.add(funcion); 
		
		funcion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rellenaCombo2(grupo.getSelectedIndex(), funcion.getSelectedIndex()); 
			}
		});
		subgrupo=new JComboBox<String>(); 
		rellenaCombo2(grupo.getSelectedIndex(),funcion.getSelectedIndex());
		p1.add(subgrupo);
		subgrupo.addItemListener(this);
		
		buscar=Utilidades.nuevoBoton("Buscar", p1, this);
		
		pa2=Utilidades.pLeft(p3, 5, 5);	
		pc1=Utilidades.pLeft(p3, 5, 5);	
		pc2=Utilidades.pLeft(p3, 5, 5);	
		pc3=Utilidades.pLeft(p3, 5, 5);	
		pc4=Utilidades.pLeft(p3, 5, 5);	
		
		
		JPanel pa21=Utilidades.pCentro(p3, 5, 5);
		aceptar=Utilidades.nuevoBoton("Aceptar", pa21, this);
		nuevo=Utilidades.nuevoBoton("Limpiar", pa21, this);
		
		p4=Utilidades.panelVertical();
		todo.add(p4);	

	}
	
	public void completar(String a, String b, String[] c, boolean d) {
		gr=a;
		sb=b; 
		if(d) {
			rellenaComboGeneral(subgrupo, c);
		}
		subgrupo.setEnabled(d);
	}
	
	public void rellenaCombo2(int v, int vv){
		String[]n2= {"Selecciona pr\u00f3tesis", "Pr\u00f3tesis parciales de mano","Pr\u00f3tesis desarticulaci\u00f3n mu\u00f1eca","Pr\u00f3tesis de antebrazo","Pr\u00f3tesis desarticulaci\u00f3n codo", "Pr\u00f3tesis de brazo", "Pr\u00f3tesis desarticulaci\u00f3n hombro", "Pr\u00f3tesis amputaci\u00f3n interescapulotor\u00e1cica"};
		String[]n3= {"Selecciona pr\u00f3tesis", "Pr\u00f3tesis parciales de pie", "Pr\u00f3tesis desarticulaci\u00f3n tobillo","Pr\u00f3tesis transtibial", "Pr\u00f3tesis desarticulaci\u00f3n rodilla","Pr\u00f3tesis transfemoral","Pr\u00f3tesis desarticulaci\u00f3n cadera","Pr\u00f3tesis hemipelvectom\u00eda"};
			subgrupo.removeAllItems();
			if(v==1 && vv==1){
				completar("0618", "0", n2, true);
			}else if(v==1 && vv==2){
				completar("0621", "062100", n2, false);
			}else if (v==2 && vv==1){
				completar("0624", "062448", n3, false);
			}else if(v==2 && vv==2){
				completar("0624", "0", n3, true);
				
			}
	}
	
	public void rellenaComboGeneral(JComboBox<String> lis, String[]a) {
		for(String i: a) {
			lis.addItem(i);
		}
	}
	
	
	public void rellenaCombo(int v){
			funcion.removeAllItems();
			if(v==1){
				gr="0618";
				rellenaComboGeneral(funcion, new  String[] {"Selecciona pr\u00f3tesis","Funcional","Est\u00e9tica"});
				
			}else if(v==2){
				gr="0624";
				rellenaComboGeneral(funcion,new  String[] {"Selecciona pr\u00f3tesis","Provisional","Definitiva"});
			}
	}
	
	public Integer sel1(JComboBox<String> a, int b, ItemEvent c) {
		if(c.getSource()==a) {
			if(a.getItemCount()>1) {
				b=a.getSelectedIndex();
			}
		}
		return b;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==subgrupo) {
			if(subgrupo.getItemCount()!=0){
			n1=subgrupo.getSelectedIndex();
			tipo1=String.valueOf(n1);}
		}
		t1= sel1(d1, t1, arg0);
		tu= sel1(cu, tu, arg0);
		t2= sel1(d2, t2, arg0);
		t3= sel1(d3, t3, arg0);
		t4= sel1(d4, t4, arg0);
		t5= sel1(d5, t5, arg0);
		t6= sel1(d6, t6, arg0);
		t7= sel1(d7, t7, arg0);
		t8= sel1(d8, t8, arg0);
		t9= sel1(d9, t9, arg0);

	}
	public void limpiarPanel() {
		pa2.removeAll();
		pc1.removeAll();
		pc2.removeAll();
		pc3.removeAll();
		pc4.removeAll();
		p4.removeAll();
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
				colocarComponentes (cu, tu, e1);
				revalidate();
			}else {
				colocarComponentes (d1, t1, c1);
				colocarComponentes (d2, t2, c2);
				colocarComponentes (d3, t3, c3);
				colocarComponentes (d4, t4, c4);
				colocarComponentes (d5, t5, c5);
				colocarComponentes (d6, t6, c6);
				colocarComponentes (d7, t7, c7);
				colocarComponentes (d8, t8, c8);
				colocarComponentes (d9, t9, c9);
	
			
			Utilidades.nuevosLabel("Precio total(IMF): "+String.format("%.3f", precio)+ " Euros", p4);
			revalidate();
		}
			}
		if(arg0.getSource()==nuevo) {
			limpiarPanel(); 
			grupo.setSelectedIndex(0);
			Utilidades.limpiar(d1,d2, d3, d4, d5, d6, d7, d8, d9, cu);
			Utilidades.vaciar(e1, c1, c2, c3, c4, c5, c6, c7, c8, c9); 
			precio=0.0;
		}
	}
	public void colocarComponentes (JComboBox<String> a, int b, ArrayList<String> c) {
		if(a.getItemCount()>1 && b-1!=-1) {
			Utilidades.nuevosLabel(a.getSelectedItem().toString()+"  "+c.get(b-1).toString(), Utilidades.pLeft(p4, 5,5));
			precio=precio+precios(c, b-1);
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
	 public void buscarValores(String busqueda,  ArrayList<String> al, JComboBox<String> jbc) {
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
	public void busqComponentes( String grupo, String tipo, String comp, ArrayList<String> al, JComboBox<String> jbc) {
		String busqueda="SELECT * FROM  Protesis WHERE Grupo = "+"'"+grupo+"'"+"AND Tipo = "+"'"+tipo+"'"+"AND Componente = "+"'"+comp+"'";
		buscarValores(busqueda, al, jbc); 
	}
	
	public void busqCompSimple( String grupo, String comp, ArrayList<String> al, JComboBox<String> jbc) {
		String busqueda="SELECT * FROM  Protesis WHERE Grupo = "+"'"+grupo+"'"+"AND Componente = "+"'"+comp+"'";
		buscarValores(busqueda, al, jbc); 

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

