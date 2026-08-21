package practicascuadrodialogo4;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LaminaBotones4 extends JPanel {
	
	private Box cajaVertical;
	private ButtonGroup grupo;
	
	public LaminaBotones4(String titulo, String[] opciones)
	{
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));
		cajaVertical=Box.createVerticalBox();
		grupo=new ButtonGroup();
		for(int i=0;i<opciones.length;i++)
		{
			JRadioButton boton=new JRadioButton(opciones[i]);
			boton.setActionCommand(opciones[i]);
			grupo.add(boton);
			cajaVertical.add(boton);
			boton.setSelected(i==0);
			this.add(cajaVertical);
			
		}
	}
	
	public String mostrarTextoBotones()
	{
		return grupo.getSelection().getActionCommand();
	}

}
