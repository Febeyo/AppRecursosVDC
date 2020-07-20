package bases;

import java.awt.Font;
import fondo.*;

import javax.swing.JTabbedPane;



@SuppressWarnings("serial")
public class InicioApp extends AppVentana{
	//public Base1 bdatos1;
	public Base2 bdatos2;
	//public Base3 bdatos3;
	public Base4 bdatos4;
	public Base6 bdatos6;
	

	public InicioApp() {
		// TODO Auto-generated constructor stub
		setTitle("Calculadoras VDC");
		JTabbedPane partes = new JTabbedPane(); 
		partes.setFont(new Font("Impact",Font.ITALIC, 16));
		
		//bdatos1 =new Base1();
		//partes.addTab("Secuelas concurrentes", bdatos1);
		bdatos2 =new Base2();
		partes.addTab("Grupos Quir\u00fargicos OMC", bdatos2);
		//bdatos3 =new Base3();
		//partes.addTab("Listado de prótesis, ortesis y ayudas técnicas", bdatos3);
		bdatos4 =new Base4();
		partes.addTab("Baremo Seguro Obligatorio de Viajeros", bdatos4);
	
		bdatos6= new Base6(); 
		partes.addTab("Pr\u00f3tesis", bdatos6);
		//bdatos5 = new Base5();
		//partes.addTab("Asistencia Sanitaria", bdatos5);
		getContentPane().add(partes);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InicioApp a= new InicioApp();
		a.setBounds(10,10,1024, 500);
		a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		a.setVisible(true);

	}

}
