package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import main.Main;
import main.Runner;
import util.Sort;
import util.Time;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GUI {

	public JFrame frmLiveRedditWallpapers;
	private JTextField sub;
	private JTextField folder;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLiveRedditWallpapers = new JFrame();
		frmLiveRedditWallpapers.setTitle("Live Reddit Wallpapers");
		frmLiveRedditWallpapers.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
		frmLiveRedditWallpapers.setBounds(100, 100, 356, 456);
		frmLiveRedditWallpapers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLiveRedditWallpapers.getContentPane().setLayout(null);
		
		sub = new JTextField();
		sub.setText("EarthPorn+SpacePorn");
		sub.setBounds(187, 30, 133, 20);
		frmLiveRedditWallpapers.getContentPane().add(sub);
		sub.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("http://www.reddit.com/r/");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(21, 33, 156, 14);
		frmLiveRedditWallpapers.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Reddit URL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 11, 123, 20);
		frmLiveRedditWallpapers.getContentPane().add(lblNewLabel_1);
		
		JLabel lblWallpaperDownloadLocation = new JLabel("Wallpaper Download Folder Location");
		lblWallpaperDownloadLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWallpaperDownloadLocation.setBounds(21, 71, 299, 20);
		frmLiveRedditWallpapers.getContentPane().add(lblWallpaperDownloadLocation);
		
		folder = new JTextField();
		folder.setText("C:\\Wallpapers");
		folder.setBounds(21, 96, 299, 20);
		frmLiveRedditWallpapers.getContentPane().add(folder);
		folder.setColumns(10);
		
		final JComboBox sort = new JComboBox();
		sort.setModel(new DefaultComboBoxModel(Sort.values()));
		sort.setBounds(21, 176, 123, 20);
		frmLiveRedditWallpapers.getContentPane().add(sort);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 58, 300, 2);
		frmLiveRedditWallpapers.getContentPane().add(separator);
		
		JLabel lblSortMethod = new JLabel("Sort Method");
		lblSortMethod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSortMethod.setBounds(21, 145, 123, 20);
		frmLiveRedditWallpapers.getContentPane().add(lblSortMethod);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 132, 300, 2);
		frmLiveRedditWallpapers.getContentPane().add(separator_1);
		
		JLabel lblTimeBracket = new JLabel("Time Bracket");
		lblTimeBracket.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTimeBracket.setBounds(197, 145, 123, 20);
		frmLiveRedditWallpapers.getContentPane().add(lblTimeBracket);
		
		final JComboBox time = new JComboBox();
		time.setModel(new DefaultComboBoxModel(Time.values()));
		time.setBounds(197, 176, 123, 20);
		frmLiveRedditWallpapers.getContentPane().add(time);
		
		final JLabel lblNewLabel_2 = new JLabel("10 mins");
		lblNewLabel_2.setBounds(258, 254, 52, 20);
		frmLiveRedditWallpapers.getContentPane().add(lblNewLabel_2);
		
		final JSlider interval = new JSlider();
		interval.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblNewLabel_2.setText(interval.getValue()+" mins");
			}
		});
		interval.setPaintLabels(true);
		interval.setValue(10);
		interval.setMaximum(120);
		interval.setMinimum(1);
		interval.setBounds(21, 252, 227, 26);
		frmLiveRedditWallpapers.getContentPane().add(interval);
		
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(21, 207, 300, 2);
		frmLiveRedditWallpapers.getContentPane().add(separator_2);
		
		JLabel lblTransitionInterval = new JLabel("Transition Interval");
		lblTransitionInterval.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTransitionInterval.setBounds(21, 220, 179, 20);
		frmLiveRedditWallpapers.getContentPane().add(lblTransitionInterval);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(20, 289, 300, 2);
		frmLiveRedditWallpapers.getContentPane().add(separator_3);
		
		JLabel lblPositionRange = new JLabel("Number of Links Read");
		lblPositionRange.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPositionRange.setBounds(21, 302, 179, 20);
		frmLiveRedditWallpapers.getContentPane().add(lblPositionRange);
		
		final JSpinner n = new JSpinner();
		n.setModel(new SpinnerNumberModel(10, 1, 1000, 1));
		n.setBounds(64, 333, 80, 20);
		frmLiveRedditWallpapers.getContentPane().add(n);
		
		final JButton stop = new JButton("Stop");
		final JButton start = new JButton("Start");
		stop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.proceed = false;
				start.setEnabled(true);
				stop.setEnabled(false);
			}
		});
		
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Main.proceed = true;
				Runnable r = new Runner(sub.getText(),(int) n.getValue(), (Sort) sort.getSelectedItem(),(Time) time.getSelectedItem(), folder.getText(), interval.getValue());
				new Thread(r).start();
				start.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		start.setBounds(231, 383, 89, 23);
		frmLiveRedditWallpapers.getContentPane().add(start);
		
		
		stop.setEnabled(false);
		stop.setBounds(132, 383, 89, 23);
		frmLiveRedditWallpapers.getContentPane().add(stop);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(21, 370, 300, 2);
		frmLiveRedditWallpapers.getContentPane().add(separator_4);
		
		JLabel lblFirst = new JLabel("First");
		lblFirst.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFirst.setBounds(21, 336, 42, 14);
		frmLiveRedditWallpapers.getContentPane().add(lblFirst);
		
		JLabel lblLinks = new JLabel("Links");
		lblLinks.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLinks.setBounds(154, 336, 42, 14);
		frmLiveRedditWallpapers.getContentPane().add(lblLinks);
	}
}
