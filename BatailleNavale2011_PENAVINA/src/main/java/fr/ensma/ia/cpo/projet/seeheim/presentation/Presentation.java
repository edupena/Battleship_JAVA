package fr.ensma.ia.cpo.projet.seeheim.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.ensma.ia.cpo.projet.seeheim.controller.Automate;

public class Presentation extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_j1,textField_j2,textField_ip,textField_port;
	private JButton lagrille1[];
	private JButton lagrille2[];
	private JPanel panel_actions;
	private JLabel info[];
	private JLabel att; 
	private JButton bouton[];
	private Automate aut;
	private String[] str;
	private Integer[] integ;
	private JCheckBox trichbox;
	private Boolean[] bools;
	
	public Presentation(Automate aut, String ip){
		
		this.aut=aut;
		setTitle("Selection mode de jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 462, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBatailleNavale = new JLabel("Bataille Navale 2011");
		lblBatailleNavale.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		lblBatailleNavale.setForeground(Color.WHITE);
		lblBatailleNavale.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBatailleNavale, BorderLayout.NORTH);
		
		JPanel panel_bouton = new JPanel();
		contentPane.add(panel_bouton, BorderLayout.CENTER);
		panel_bouton.setLayout(new GridLayout(3,1));
		bouton = new JButton[3];
		
		bouton[0] = new JButton("Jouer en réseau");
		bouton[0].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[0].addActionListener(this);
		panel_bouton.add(bouton[0]);
		
		bouton[1] = new JButton("Jouer en local: Joueur1 VS Joueur2");
		bouton[1].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[1].addActionListener(this);
		panel_bouton.add(bouton[1]);
		
		bouton[2] = new JButton("Jouer en local: Joueur1 VS Ordi");
		bouton[2].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[2].addActionListener(this);
		panel_bouton.add(bouton[2]);

		JPanel panel_reseau = new JPanel(); 
		JLabel label_ip = new JLabel("Ton IP: "+ip);

		panel_reseau.add(label_ip);
		textField_ip = new JTextField("IP de l'adversaire");
		panel_reseau.add(textField_ip);
		JLabel port = new JLabel("Port:");

		port.setBackground(Color.DARK_GRAY);
		panel_reseau.add(port);
		textField_port = new JTextField("23");
		panel_reseau.add(textField_port);

		

		trichbox = new JCheckBox("Tricher?");				
		panel_reseau.add(trichbox);
		contentPane.add(panel_reseau,BorderLayout.PAGE_END);

		
		JPanel panel_image = new JPanel();
		contentPane.add(panel_image, BorderLayout.NORTH);
		panel_image.setBackground(Color.black);
		ImageIcon icone = new ImageIcon("image/tir.jpeg");
		JLabel image = new JLabel(icone);
		panel_image.add(image);
				
		
		setVisible(true);
		
		
		
	}
	
	public void Jeu() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 800);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.black);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_j1 = new JPanel();
		contentPane.add(panel_j1, BorderLayout.WEST);
		panel_j1.setLayout(new GridLayout(10,10));
		lagrille1 = new JButton[100];
		for (int i=0;i<100;i++) {
			lagrille1[i]=new JButton("~");
			lagrille1[i].setBackground(Color.blue);
			panel_j1.add(lagrille1[i]);
		}
		panel_j1.setPreferredSize(new Dimension(530, 550));
		
		JPanel panel_j2 = new JPanel();
		contentPane.add(panel_j2, BorderLayout.EAST);
		panel_j2.setLayout(new GridLayout(10,10));
		lagrille2 = new JButton[100];
		for (int i=0;i<100;i++) {
			lagrille2[i]=new JButton("?");
			lagrille2[i].addActionListener(this);
			panel_j2.add(lagrille2[i]);
		}
		panel_j2.setPreferredSize(new Dimension(530, 550));
		
		JPanel panel_score = new JPanel();
		panel_score.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_score, BorderLayout.NORTH);
		
		JLabel lblJ = new JLabel("J1");
		lblJ.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 15));
		lblJ.setForeground(Color.RED);
		panel_score.add(lblJ);
		
		textField_j1 = new JTextField();
		textField_j1.setBackground(Color.LIGHT_GRAY);
		textField_j1.setToolTipText("Joueur1");
		panel_score.add(textField_j1);
		textField_j1.setColumns(10);
		
		JLabel lblJ_1 = new JLabel("J2");
		lblJ_1.setForeground(Color.GREEN);
		lblJ_1.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 15));
		panel_score.add(lblJ_1);
		
		textField_j2 = new JTextField();
		textField_j2.setBackground(Color.LIGHT_GRAY);
		textField_j2.setToolTipText("Joueur2");
		panel_score.add(textField_j2);
		textField_j2.setColumns(10);
		
		JPanel panel_central = new JPanel();
		panel_central.setBackground(Color.GRAY);
		contentPane.add(panel_central, BorderLayout.CENTER);
		panel_actions = new JPanel();
		panel_central.add(panel_actions);
		
		jeuEnCours();
		
		ImageIcon icone = new ImageIcon("image/tir.jpeg");
		JLabel image = new JLabel(icone);
		panel_central.add(image);
	}
	
		
	public void showAttaque(int bat,int att){
		switch(bat){
		case 0:
			info[3].setText(String.valueOf(att)+" d'attaque");
			break;
		case 1:
			info[1].setText(String.valueOf(att)+" d'attaque");
			break;
		case 2:
			info[0].setText(String.valueOf(att)+" d'attaque");
			break;
		case 3:
			info[2].setText(String.valueOf(att)+" d'attaque");
			break;
		case 4:
			info[4].setText(String.valueOf(att)+" d'attaque");
			break;		
		}		
	}
	
	public void jeuEnCours(){
		setTitle("Bataille navale 2011");
		textField_j1.setEditable(false);
		textField_j2.setEditable(false);
	
		panel_actions.removeAll();
		panel_actions.setBackground(Color.GRAY);
		panel_actions.setLayout(new GridLayout(17,1));
		info = new JLabel[6];
		
		Box horizontalBox = Box.createHorizontalBox();
		panel_actions.add(horizontalBox);
		bouton = new JButton[6];

		bouton[0] = new JButton("Rejouer");
		panel_actions.add(bouton[0]);
		bouton[0].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[0].addActionListener(this);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panel_actions.add(horizontalBox_1);
		
		JLabel linfo = new JLabel(" Attaquer avec:");
		linfo.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
		linfo.setForeground(Color.white);
		panel_actions.add(linfo);
		
		bouton[1] = new JButton("Porte avion");
		panel_actions.add(bouton[1]);
		bouton[1].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[1].addActionListener(this);
		
		info[0] = new JLabel("5 d'attaque");
		info[0].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		panel_actions.add(info[0]);
		
		bouton[2] = new JButton("Croiseur");
		panel_actions.add(bouton[2]);
		bouton[2].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[2].addActionListener(this);
		
		info[1] = new JLabel("4 d'attaque");
		info[1].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		panel_actions.add(info[1]);
		
		bouton[3] = new JButton("Sous marin");
		panel_actions.add(bouton[3]);
		bouton[3].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[3].addActionListener(this);
		
		info[2] = new JLabel("3 d'attaque");
		info[2].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		panel_actions.add(info[2]);
		
		bouton[4] = new JButton("Contre torpilleur");
		panel_actions.add(bouton[4]);
		bouton[4].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[4].addActionListener(this);
		
		info[3] = new JLabel("3 d'attaque");
		info[3].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		panel_actions.add(info[3]);
		
		bouton[5] = new JButton("Torpilleur");
		panel_actions.add(bouton[5]);
		bouton[5].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		bouton[5].addActionListener(this);
		
		info[4] = new JLabel("2 d'attaque");
		info[4].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		panel_actions.add(info[4]);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		panel_actions.add(horizontalBox_2);
		
		att = new JLabel("Bateau qui attaque:");
		att.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		panel_actions.add(att);
		
		info[5] = new JLabel("Porte avion");
		info[5].setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		panel_actions.add(info[5]);
	}
	
	public void setBateauQuiAttaque(String bateau){
		
		info[5].setText(bateau);
		
	}
	
	
	
	public void desactiverGrille(int joueur) {
		if(joueur==1) {
			for (int i=0;i<100;i++) {
				lagrille1[i].removeActionListener(this);
			}
		}else{
			for (int i=0;i<100;i++) {
				lagrille2[i].removeActionListener(this);
			}
		}
	}
	
	
	public void activerGrille(int joueur) {
		if(joueur==1) {				
			this.desactiverGrille(2);
			for (int i=0;i<100;i++) {
				lagrille1[i].addActionListener(this);				
			}
		}else{
			this.desactiverGrille(1);
			for (int i=0;i<100;i++) {
				lagrille2[i].addActionListener(this);
			}
		}
	}
	
	
	public void cacherGrille(int joueur) {
		if(joueur==1) {			
			for (int i=0;i<100;i++) {
				lagrille1[i].setText("?");
				lagrille1[i].setBackground(null);
			}
		}else{
			for (int i=0;i<100;i++) {
				lagrille2[i].setText("?");
				lagrille2[i].setBackground(null);
			}
		}
	}
	
	
	public void actionPerformed(ActionEvent ev) {
		
		if (str==null) {str=new String[3];}
		if(integ==null) {integ=new Integer[2];}
		if (bools==null) {bools=new Boolean[1]; bools[0]=false;}
		JButton pressed;						
		
		if(ev.getSource().getClass().equals(JButton.class)){
			pressed = (JButton) ev.getSource();	
			if (pressed.getText()=="Porte avion" || pressed.getText()=="Croiseur" || pressed.getText()=="Sous marin" || 
					pressed.getText()=="Contre torpilleur" || pressed.getText()=="Torpilleur"){
				str[1]=pressed.getText();
				this.setBateauQuiAttaque(pressed.getText());
			}
			else if (pressed.getText()=="Rejouer"){	this.setVisible(false); aut=new Automate();	}
			else{
				str[0]=pressed.getText();
				str[2]=textField_ip.getText();
				try{
					integ[1]=Integer.valueOf(textField_port.getText());
				} catch (Exception e) {integ[1]=23; }
				integ[0]=getCasePos(pressed);
				bools[0]=trichbox.isSelected();
				aut.click(str, integ, bools);
			}
		}
				
			
	}
	
	
	
	public Integer getCasePos(JButton but){
		
		if (lagrille1!=null && lagrille2!=null){
			for (int i=0; i<100;i++){
				if (lagrille1[i]==but){ return i;}
				else if (lagrille2[i]==but){ return i;}
			}
		}
		return -1;
	}
	
	
	public void showQuiAttaque(int joueur){
		if (joueur==1) { textField_j1.setText("Attaque"); textField_j2.setText("Defende");}
		else if (joueur==2) { textField_j1.setText("Defende"); textField_j2.setText("Attaque");}
	}

	public void fin(Integer gagnant){
		if (gagnant==1) { textField_j1.setText("WINNER"); textField_j2.setText("LOSER");}
		else if (gagnant==2) { textField_j1.setText("LOOSER"); textField_j2.setText("WINNER");}		
		
	}
	public void showOnlyText(Integer pos, Integer grille, String value){
		JButton but;
		if (grille==1){
			but=lagrille1[pos];
		}
		else {but=lagrille2[pos];}
		but.setText(value);
		
		
		if (Integer.valueOf(value)>0){		
			but.setText(value);
		}
		else if (Integer.valueOf(value)==-1){			
			but.setText("O");
		}

	}

	public void showValue(Integer pos, Integer grille, String value) {
		JButton but;
		if (grille==1){
			but=lagrille1[pos];
		}
		else {but=lagrille2[pos];}
		but.setText(value);
		
		if (value=="?"){
			but.setBackground(new Color(200,200,255));
			}
		else if (Integer.valueOf(value)>0){
			if((255-51*Integer.valueOf(value))>=0){ but.setBackground(new Color(255-51*Integer.valueOf(value),0,0));}
			else {but.setBackground(new Color(0,0,0));}
			but.setText(value);
		}
		else if (Integer.valueOf(value)==-1){
			but.setBackground(new Color(0,0,255));
			but.setText("O");
		}
		else{
			but.setBackground(new Color(255,0,0));
			but.setText("X");
		}
			
		
	}
	
	
}
