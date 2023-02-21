import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.TimerTask;

import javax.swing.*;

public class Stopwatch extends JFrame implements ActionListener {
	private JButton startButton, resetButton;
	private JLabel timeLabel;
	private int elapsedTime, seconds, minutes, hours;
	private boolean started;
	private String seconds_string = String.format("%02d", seconds);
	private String minutes_string = String.format("%02d", minutes);
	private String hours_string = String.format("%02d", hours);
	private Timer timer;
	private TimerTask timerTask;
	public Stopwatch() {		
		timeLabel = new JLabel();
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		timeLabel.setBounds(150,100,200,100);
		timeLabel.setBackground(Color.black);
		timeLabel.setForeground(Color.green);
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
		timeLabel.setBorder(BorderFactory.createLineBorder(Color.green));
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setOpaque(true);
		
		startButton = new JButton("Start");
		startButton.setBounds(150, 200, 100, 50);
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.green);
		startButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		startButton.setBorder(BorderFactory.createLineBorder(Color.green));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(250, 200, 100, 50);
		resetButton.setBackground(Color.black);
		resetButton.setForeground(Color.green);
		resetButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		resetButton.setBorder(BorderFactory.createLineBorder(Color.green));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elapsedTime += 1000;
				hours = (elapsedTime/3600000);
				minutes = (elapsedTime/60000)%60;
				seconds = (elapsedTime/1000)%60;
				hours_string = String.format("%02d", hours);
				minutes_string = String.format("%02d", minutes);
				seconds_string = String.format("%02d", seconds);
				timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
			}			
		});
		
		this.add(timeLabel);
		this.add(startButton);
		this.add(resetButton);
		this.getContentPane().setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startButton) {
			if(started==false) {
				started = true;
				startButton.setText("Stop");
				start();
			}
			else {
				started = false;
				startButton.setText("Start");
				stop();
			}
		}
		if(e.getSource()==resetButton) {
			started = false;
			startButton.setText("Start");
			reset();
		}
	}
	public void start() {
		timer.start();
	}
	public void stop() {
		timer.stop();
	}
	public void reset() { 
		timer.stop();
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		hours_string = String.format("%02d", hours);
		minutes_string = String.format("%02d", minutes);
		seconds_string = String.format("%02d", seconds);
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
	}
}
