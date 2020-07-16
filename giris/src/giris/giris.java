package giris;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class giris extends JFrame {

	private JPanel contentPane;
	private JTextField adGiris;
	private JTextField sifreGiris;
	
	static String ad;
	private static String sifre;
	private JLabel uyari;
	
	kitapListe kitap = new kitapListe();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giris frame = new giris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public giris() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel.setBounds(86, 57, 108, 25);
		contentPane.add(lblNewLabel);
		
		adGiris = new JTextField();
		adGiris.setBounds(235, 57, 116, 22);
		contentPane.add(adGiris);
		adGiris.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Sifre");
		lblNewLabel_1.setBounds(86, 126, 108, 25);
		contentPane.add(lblNewLabel_1);
		
		sifreGiris = new JTextField();
		sifreGiris.setBounds(235, 127, 116, 22);
		contentPane.add(sifreGiris);
		sifreGiris.setColumns(10);
		
		 //Giris yapma (VeriTabaný)
		JButton btnGiris = new JButton("Giris");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ad = adGiris.getText();
				sifre = sifreGiris.getText();
				
				String sql_sorgu = " Select Count(kullaniciAdi) as giris From kitapci.kullanicilar Where kullaniciAdi = '" + ad + "'"
						+ " and kullaniciSifre = '" + sifre + "'";
				
				ResultSet myRs = baglanti.baglan();
				myRs = baglanti.sorgula(sql_sorgu);
				
				try {
					while(myRs.next()){
						if(myRs.getInt("giris") == 1) {

							kitap.setVisible(true); //KitapListeleme ekranýný açma
							setVisible(false); //Giris ekranýný kapatma
						}
						else {
							uyari.setText("HATALI GÝRÝÞ");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnGiris.setBounds(156, 203, 97, 33);
		contentPane.add(btnGiris);
		
		uyari = new JLabel("");
		uyari.setBounds(179, 249, 229, 36);
		contentPane.add(uyari);
	}

}
