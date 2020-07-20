package fondo;

import bases.Utilidades;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelBusquedaSimple extends JPanel implements ActionListener, ItemListener{
	public static JScrollPane barra;
	public static JPanel todo,p1,p1a,p2,p3,p4;
	public static JComboBox<String> lista1;
	public static JTextField tf1;
	public static JButton b1, nuevo;
	
	public  PanelBusquedaSimple (){
		setLayout(new GridLayout(0,1,0,0));
		todo =Utilidades.panelVertical(); 
		add(todo); 
		barra= new JScrollPane();
		add(barra);
		barra.setViewportView(todo);

		JPanel pinicial1= Utilidades.pLeft(todo,  5, 5);
		p1= Utilidades.pLeft(pinicial1, 10, 20);
		p1a= Utilidades.pLeft(pinicial1, 10, 20);	
		
		p2= Utilidades.pLeft(todo, 40, 20);
		
		p3= Utilidades.pLeft(todo, 40, 20);
		
		p4= Utilidades.panelVertical();
		todo.add(p4);
		
		Utilidades.nuevosLabel("B\u00fasqueda por palabras:", p1a);	
		tf1= Utilidades.nuevosTF(30,p1a);
		b1 = Utilidades.nuevoBoton("Buscar",p1a, this);
		
		nuevo= Utilidades.nuevoBoton("Limpiar", p3, this);
		
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
