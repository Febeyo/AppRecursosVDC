package fondo;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import bases.Utilidades;

@SuppressWarnings("serial")
public class PanelBase extends JPanel {
	public static JScrollPane barra;
	public static JPanel todo,p1,p2,p3,p4;

	
	public  PanelBase (){
		setLayout(new GridLayout(0,1,0,0));
		todo =Utilidades.panelVertical(); 
		add(todo); 
		barra= new JScrollPane();
		add(barra);
		barra.setViewportView(todo);

		
		p1= Utilidades.pLeft(todo, 10, 20);
	
		
		p2= Utilidades.pLeft(todo, 40, 20);
		
		p3= Utilidades.panelVertical();
		todo.add(p3);
		
		p4= Utilidades.panelVertical();
		todo.add(p4);
		
		
		
		
	}


}
