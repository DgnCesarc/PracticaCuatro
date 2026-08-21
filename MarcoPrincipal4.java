package practicascuadrodialogo4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MarcoPrincipal4 extends JFrame {
	
	private JButton boton;
	private JPanel lienzoInferior;
	public MarcoPrincipal4()
	{
		this.setLayout(new BorderLayout());
		Toolkit ventana=Toolkit.getDefaultToolkit();
		Dimension tamagno=ventana.getScreenSize();
		int anchoVentana=tamagno.width;
		int altoVentana=tamagno.height;
		this.setSize(anchoVentana/2, altoVentana/2);
		this.setLocation(anchoVentana/4, altoVentana/4);
		
				
		lienzoInferior=new JPanel();
		boton=new JButton("Mostrar");
		lienzoInferior.add(boton);
		this.add(lienzoInferior,BorderLayout.SOUTH);
		
		LienzoPrincipal4 miLienzo=new LienzoPrincipal4(boton);
		this.add(miLienzo,BorderLayout.CENTER);
	}
	
	

}

class LienzoPrincipal4 extends JPanel
{
	
	private JButton miBoton;
	private LaminaBotones4 laminaTipo,laminaTipoMensaje,laminaMensaje,laminaConfirmar,laminaOpcion,laminaEntrada;
	private String cadena;
	private Icon icono;
	private LienzoComponente panel;
	private Date miFecha;
	
	
	public LienzoPrincipal4(JButton bot)
	{
		this.setLayout(new GridLayout(2,3));
		cadena="Hola Bienvenido";
		icono=new ImageIcon("src/imagenes/amarillo.jpg");
		panel=new LienzoComponente();
		miFecha=new Date();
		
		miBoton=bot;
		laminaTipo=new LaminaBotones4("Tipo",new String[] {"Mensaje","Confirmar","Opcion","Entrada"});
		laminaTipoMensaje=new LaminaBotones4("Tipo de Mensaje",new String[] {"ERROR_MESSAGE","INFORMATION_MESSAGE","WARNING_MESSAGE","QUESTION_MESSAGE","PLAIN_MESSAGE"});
		laminaMensaje=new LaminaBotones4("Mensaje",new String[] {"Cadena","Icono","Componente","Otros","Object[]"});
		laminaConfirmar=new LaminaBotones4("Confirmar",new String[] {"DEFAULT_OPTION","YES_NO_OPTION","YES_NO_CANCEL_OPTION","OK_CANCEL_OPTION"});
		laminaOpcion=new LaminaBotones4("Opcion",new String[] {"String[]","Icon[]","Object[]"});
		laminaEntrada=new LaminaBotones4("Entrada",new String[] {"Campo de Texto","Combo"});
		this.add(laminaTipo);
		this.add(laminaTipoMensaje);
		this.add(laminaMensaje);
		this.add(laminaConfirmar);
		this.add(laminaOpcion);
		this.add(laminaEntrada);
		
		miBoton.addActionListener(new MostrarCuadrosDialogo());
		
	}
	
	public Object devuelveMensaje()
	{
		if(laminaMensaje.mostrarTextoBotones().equalsIgnoreCase("Cadena"))
		{
			return cadena;
		}
		else if(laminaMensaje.mostrarTextoBotones().equalsIgnoreCase("Icono"))
		{
			return icono;
		}
		else if(laminaMensaje.mostrarTextoBotones().equalsIgnoreCase("Componente"))
		{
			return panel;
		}
		else if(laminaMensaje.mostrarTextoBotones().equalsIgnoreCase("Otros"))
		{
			return miFecha;
		}
		else if(laminaMensaje.mostrarTextoBotones().equalsIgnoreCase("Object[]"))
		{
			return new Object[] {
					cadena,icono,panel,miFecha
			};
		}
		else
		{
			return null;
		}
	}
	
	public int devuelveTipo(LaminaBotones4 miLamina)
	{
		String s=miLamina.mostrarTextoBotones();
		if(s.equalsIgnoreCase("ERROR_MESSAGE") || s.equalsIgnoreCase("YES_NO_OPTION"))
		{
			return 0;
		}
		else if(s.equalsIgnoreCase("INFORMATION_MESSAGE") || s.equalsIgnoreCase("YES_NO_CANCEL_OPTION"))
		{
			return 1;
		}
		else if(s.equalsIgnoreCase("WARNING_MESSAGE") || s.equalsIgnoreCase("OK_CANCEL_OPTION"))
		{
			return 2;
		}
		else if(s.equalsIgnoreCase("QUESTION_MESSAGE"))
		{
			return 3;
		}
		else if(s.equalsIgnoreCase("PLAIN_MESSAGE") || s.equalsIgnoreCase("DEFAULT_OPTION"))
		{
			return -1;
		}
		else
		{
			return 9;
		}
	}
	
	public Object[] devolverOpciones()
	{
		String s=laminaOpcion.mostrarTextoBotones();
		if(s.equalsIgnoreCase("String[]"))
		{
			return new String[] {"Amarillo","Rojo","Verde"};
		}
		else if(s.equalsIgnoreCase("Icon[]"))
		{
			return new Icon[] {new ImageIcon("src/imagenes/amarillo.jpg"),new ImageIcon("src/imagenes/rojo.jpg"),new ImageIcon("src/imagenes/verde.jpg")};
		}
		else if(s.equalsIgnoreCase("Object[]"))
		{
			return new Object[]
					{
						cadena,icono,panel,miFecha	
					};
		}
		else
		{
			return null;
		}
	}
	
	private class LienzoComponente extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Graphics2D g2=(Graphics2D) g;
			Rectangle2D miRectangulo=new Rectangle2D.Double(0,0,this.getWidth(),this.getHeight());
			g2.setPaint(Color.YELLOW);
			g2.fill(miRectangulo);
		}
	}
	
	private class MostrarCuadrosDialogo implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			
			if(laminaTipo.mostrarTextoBotones().equalsIgnoreCase("Mensaje"))
			{
				JOptionPane.showMessageDialog(LienzoPrincipal4.this, devuelveMensaje(),"Titulo", devuelveTipo(laminaTipoMensaje));
			}
			else if(laminaTipo.mostrarTextoBotones().equalsIgnoreCase("Confirmar"))
			{
				JOptionPane.showConfirmDialog(LienzoPrincipal4.this,devuelveMensaje(),"Titulo", devuelveTipo(laminaConfirmar), devuelveTipo(laminaTipoMensaje));
			}
			else if(laminaTipo.mostrarTextoBotones().equalsIgnoreCase("Opcion"))
			{
				JOptionPane.showOptionDialog(LienzoPrincipal4.this,devuelveMensaje(),"Titulo", 0, devuelveTipo(laminaTipoMensaje),null,devolverOpciones(),null);
			}
			else if(laminaTipo.mostrarTextoBotones().equalsIgnoreCase("Entrada"))
			{
				if(laminaEntrada.mostrarTextoBotones().equalsIgnoreCase("Campo de Texto"))
				{
					JOptionPane.showInputDialog(LienzoPrincipal4.this,devuelveMensaje(),"Titulo",devuelveTipo(laminaTipoMensaje), null, null, null);
				}
				else if(laminaEntrada.mostrarTextoBotones().equalsIgnoreCase("Combo"))
				{
					JOptionPane.showInputDialog(LienzoPrincipal4.this,devuelveMensaje(),"Titulo",devuelveTipo(laminaTipoMensaje), null, new String[] {"Amarillo","Rojo","Verde"}, "Rojo");
				}
				
			}
		}
		
	}
}


