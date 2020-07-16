package giris;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class kitapListe extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	DefaultTableModel modelim = new DefaultTableModel();
	Object[] kolonlar = {"kitapAdi","baskiSayisi","basimYili","satisadedi","stokMiktari","fiyat"};
	Object[] satirlar = new Object[6];
	private JButton btnNewButton;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtkitapAdi;
	private JTextField txtbaskiSayisi;
	private JTextField txtbasimYili;
	private JTextField txtsatisAdedi;
	private JTextField txtstokMiktari;
	private JTextField txtfiyat;
	private JButton Guncelle;
	private JButton btnSil;
	private JTextField adSorgu;
	private JLabel lblNewLabel;
	private JButton butonSorgu;
	private static JLabel kullanici;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kitapListe frame = new kitapListe();
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
	public kitapListe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(0, 0, 585, 342);
		contentPane.add(scrollPane);
		
		table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);
		
		
		
		table.setBounds(158, 219, 256, 123);
		scrollPane.setViewportView(table);

		
		btnNewButton = new JButton("Listele");
		scrollPane.setColumnHeaderView(btnNewButton);
		
		JButton btnListele = new JButton("Listele");
		scrollPane.setRowHeaderView(btnListele);
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modelim.setRowCount(0);
				
				ResultSet myRs = baglanti.baglan();
				kullanici.setText(giris.ad); //kullanici adini ekranda gösterme
				
				
				try {
					while(myRs.next()) {
						satirlar[0] = myRs.getString("kitapAdi");
						satirlar[1] = myRs.getString("baskiSayisi");
						satirlar[2] = myRs.getString("basimYili");
						satirlar[3] = myRs.getString("satisAdedi");
						satirlar[4] = myRs.getString("stokMiktari");
						satirlar[5] = myRs.getString("fiyat");
						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(modelim);
			}
		});
		btnListele.setBounds(542, 51, 97, 25);
		
		txtkitapAdi = new JTextField();
		txtkitapAdi.setBounds(714, 45, 116, 22);
		contentPane.add(txtkitapAdi);
		txtkitapAdi.setColumns(10);
		
		txtbaskiSayisi = new JTextField();
		txtbaskiSayisi.setBounds(714, 80, 116, 22);
		contentPane.add(txtbaskiSayisi);
		txtbaskiSayisi.setColumns(10);
		
		txtbasimYili = new JTextField();
		txtbasimYili.setBounds(714, 115, 116, 22);
		contentPane.add(txtbasimYili);
		txtbasimYili.setColumns(10);
		
		txtsatisAdedi = new JTextField();
		txtsatisAdedi.setBounds(714, 168, 116, 22);
		contentPane.add(txtsatisAdedi);
		txtsatisAdedi.setColumns(10);
		
		txtstokMiktari = new JTextField();
		txtstokMiktari.setBounds(714, 203, 116, 22);
		contentPane.add(txtstokMiktari);
		txtstokMiktari.setColumns(10);
		
		txtfiyat = new JTextField();
		txtfiyat.setBounds(714, 238, 116, 22);
		contentPane.add(txtfiyat);
		txtfiyat.setColumns(10);
		
		//veri tabanýna veri kaydetme
		JButton kaydet = new JButton("Kaydet");
		kaydet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				String kitapAdi,baskiSayisi, basimYili,satisAdedi,stokMiktari,fiyat,sql_sorgu;
				
				kitapAdi = txtkitapAdi.getText();
				baskiSayisi = txtbaskiSayisi.getText();
				basimYili = txtbasimYili.getText();
				satisAdedi = txtsatisAdedi.getText();
				stokMiktari = txtstokMiktari.getText();
				fiyat = txtfiyat.getText();
				
				sql_sorgu = "INSERT INTO kitaplar (kitapAdi,baskiSayisi,basimYili,satisAdedi,stokMiktari,fiyat) VALUES ("+
				"'" + kitapAdi + "'" + "," + baskiSayisi + "," + basimYili + "," + satisAdedi + "," + stokMiktari + "," + fiyat +")" ;
			
				
				baglanti.ekle(sql_sorgu);
				
			}
			
		});
		
		
		
		kaydet.setBounds(597, 316, 87, 25);
		contentPane.add(kaydet);
		
		JLabel label1 = new JLabel("K\u0130TAP ADI");
		label1.setBounds(613, 48, 97, 16);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("BASKI SAYISI");
		label2.setBounds(613, 83, 87, 16);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("BASIM YILI");
		label3.setBounds(613, 118, 97, 16);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("SATI\u015E ADED\u0130");
		label4.setBounds(613, 171, 98, 16);
		contentPane.add(label4);
		
		JLabel label5 = new JLabel("STOK M\u0130KTARI");
		label5.setBounds(613, 206, 106, 16);
		contentPane.add(label5);
		
		JLabel label6 = new JLabel("F\u0130YAT");
		label6.setBounds(613, 241, 56, 16);
		contentPane.add(label6);
		
		Guncelle = new JButton("G\u00FCncelle");
		Guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                String Adi,Sayisi, Yili,Adedi,Miktari,Fiyat,guncelle_sorgu;
				
				Adi = txtkitapAdi.getText();
				Sayisi = txtbaskiSayisi.getText();
				Yili = txtbasimYili.getText();
				Adedi = txtsatisAdedi.getText();
			    Miktari = txtstokMiktari.getText();
				Fiyat = txtfiyat.getText();
				
				guncelle_sorgu = "UPDATE kitaplar SET "+ "kitapAdi = " + "'" + Adi + "'" + "," +
				"baskiSayisi =" + Sayisi + "," + "basimYili =" +Yili + ","
						+ "satisAdedi =" + Adedi + "," +  "stokMiktari =" + Miktari + "," +
						"fiyat =" + Fiyat +  " WHERE kitapAdi = " + "'" + Adi + "'" ;
				
				baglanti.guncelle(guncelle_sorgu);
				
				
			}
		});
		Guncelle.setBounds(696, 316, 87, 25);
		contentPane.add(Guncelle);
		
		btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                String Adi,sil_sorgu;
				
				Adi = txtkitapAdi.getText();
				sil_sorgu = "DELETE FROM kitaplar Where kitapAdi =" + "'" + Adi + "'";
				
				baglanti.sil(sil_sorgu);
				
			}
		});
		btnSil.setBounds(794, 316, 71, 25);
		contentPane.add(btnSil);
		
		adSorgu = new JTextField();
		adSorgu.setBounds(12, 383, 116, 22);
		contentPane.add(adSorgu);
		adSorgu.setColumns(10);
		
		lblNewLabel = new JLabel("K\u0130TAP ADI");
		lblNewLabel.setBounds(10, 354, 71, 16);
		contentPane.add(lblNewLabel);
		
		butonSorgu = new JButton("Sorgula");
		butonSorgu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modelim.setRowCount(0);
				String ad = adSorgu.getText();
				
				ResultSet myRs = null;
				
				String sql_sorgu = "select * from kitaplar where kitapAdi like '" + ad + "%'" ;
				myRs = baglanti.sorgula(sql_sorgu);
				
				try {
					while(myRs.next()) {
						satirlar[0] = myRs.getString("kitapAdi");
						satirlar[1] = myRs.getString("baskiSayisi");
						satirlar[2] = myRs.getString("basimYili");
						satirlar[3] = myRs.getString("satisAdedi");
						satirlar[4] = myRs.getString("stokMiktari");
						satirlar[5] = myRs.getString("fiyat");
						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(modelim);
			}
		});
		butonSorgu.setBounds(155, 382, 97, 25);
		contentPane.add(butonSorgu);
		
		kullanici = new JLabel("");
		kullanici.setBounds(743, 13, 87, 19);
		contentPane.add(kullanici);
		
		
		//Veritabanýný güncelleme(Satýr seçme)
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				txtkitapAdi.setText((String) modelim.getValueAt(table.getSelectedRow(), 0));
				txtbaskiSayisi.setText((String) modelim.getValueAt(table.getSelectedRow(), 1));
				txtbasimYili.setText((String) modelim.getValueAt(table.getSelectedRow(), 2));
				txtsatisAdedi.setText((String) modelim.getValueAt(table.getSelectedRow(), 3));
				txtstokMiktari.setText((String) modelim.getValueAt(table.getSelectedRow(), 4));
				txtfiyat.setText((String) modelim.getValueAt(table.getSelectedRow(), 5));
				
			}
		});
		

		

		
	}
}
