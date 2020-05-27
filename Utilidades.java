import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

public class Utilidades {

	public Utilidades() {
		// TODO Auto-generated constructor stub
	}
	public static JPanel mostrarListado(int v, String s){
		JPanel p = new JPanel();
		p.add(new JLabel(String.valueOf(v+1)+") "));
		p.add(new JLabel(s));
		p.setLayout(new FlowLayout(FlowLayout.LEFT, 5,0));
		return p;
		
	}
	public static String busquedaSimilar(String tabla, String variable) {
		String a ="SELECT * FROM "+ tabla+" WHERE "+ variable+" LIKE ?";
		return a;
	}
	public static String busquedaExacta(String tabla,String varexacta, String valorbuscado) {
		String a ="SELECT * FROM "+ tabla+" WHERE "+ varexacta+"= "+valorbuscado;
		return a;
	}
	public static String busquedaMezcla(String tabla, String varsimilar, String varexacta, String valorbuscado) {
		String a ="SELECT * FROM "+ tabla+" WHERE "+ varsimilar+" LIKE ? AND "+varexacta+"= "+valorbuscado;
		return a;
	}

	
	public static JLabel nuevosLabel(String texto, JPanel panel) {
		JLabel nuevoLabel=new JLabel(texto);
		panel.add(nuevoLabel);
		return nuevoLabel;
	}
	public static JLabel labelTitulo(String texto, JPanel panel, Font fuente) {
		JLabel nuevoLabel=new JLabel(texto);
		panel.add(nuevoLabel);
		nuevoLabel.setFont(fuente);
		return nuevoLabel;
	}
	public  static JTextField nuevosTF(int longitud, JPanel panel) {
		JTextField nuevoTF=new JTextField(longitud);
		panel.add(nuevoTF);
		nuevoTF.setHorizontalAlignment(JTextField.CENTER);
		return nuevoTF;
	}
	public static JRadioButton nuevoRB(ButtonGroup g, String texto, JPanel panel, ActionListener l) {
		JRadioButton rb= new JRadioButton(texto);
		panel.add(rb);
		g.add(rb);
		rb.addActionListener(l);
		return rb;	
	}
	
	public static JCheckBox nuevoCB(String texto, JPanel panel, ChangeListener l) {
		JCheckBox cb=new JCheckBox(texto);
		panel.add(cb);
		cb.addChangeListener(l);
		return cb;
		
	}
	public static JButton nuevoBoton(String texto, JPanel panel, ActionListener l) {
		JButton b=new JButton(texto);
		panel.add(b);
		b.addActionListener(l);
		return b;
	}
	public static JPanel pCentro(JPanel prin, int a, int b) {
		JPanel p= new JPanel(); 	
		p.setLayout(new FlowLayout(FlowLayout.CENTER,a,b));	
		prin.add(p);
		return p;
	}
	public static JPanel pLeft(JPanel prin,int a, int b) {
		JPanel p= new JPanel(); 	
		p.setLayout(new FlowLayout(FlowLayout.LEFT,a,b));	
		prin.add(p);
		return p;
	}
	public static JPanel panelBorde(JPanel tipopanel, String titulo, Font fuente, Border b) {
		JPanel p= tipopanel; 
		b= BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), titulo, TitledBorder.CENTER, TitledBorder.TOP, fuente);
		p.setBorder(b);
		return p;
	}

	
	public static JPanel panelVertical() {
		JPanel p= new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		return p;
	}
	

}
