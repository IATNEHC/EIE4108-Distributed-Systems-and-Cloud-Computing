import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class StopWatch extends JFrame implements Runnable, ActionListener {

	private TextField tfSecond, tfMinute;
	private Label lbSecond, lbMinute;
	private Button btStart, btStop, btReset, btMyName;
	private Thread clockThread = null;
	private int second, minute;
	private Panel p1, p2;
	
	public static void main(String[] args) {
		StopWatch frame = new StopWatch();
		frame.setTitle("StopWatch!");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	// Constructor for setting up UI and add event listeners
	public StopWatch() {
		setLayout(new GridLayout(2, 1, 0, 0));
		p1 = new Panel();
		p2 = new Panel();
		add(p1);
		add(p2);
		lbMinute = new Label("Minute:");
		lbSecond = new Label("Second:");
		tfMinute = new TextField(2);
		tfSecond = new TextField(2);
		btStart = new Button("Start");
		btStop = new Button("Stop");
		btReset = new Button("Reset");
		btMyName = new Button("SZE Kin Sang");
		p1.add(lbMinute);
		p1.add(tfMinute);
		p1.add(lbSecond);
		p1.add(tfSecond);
		p1.add(btStart);
		p1.add(btStop);
		p1.add(btReset);
		p2.add(btMyName);
		btStart.addActionListener(this);
		btStop.addActionListener(this);
		btReset.addActionListener(this);
		tfMinute.setText(String.valueOf(minute));
		tfSecond.setText(String.valueOf(second));
	} 
	// End of StopWatch’s constructor
	
	public void run() {
		// Putting the current running thread to sleep for 1 second.
		// Wake up the thread immediately if the “Reset” button has
		// been pressed.
		// If neither the “Reset” button nor the “Stop” button has been
		// pressed while the thread is sleeping, increment the
		// clock by 1 second when the thread wakes up.
		// Put your code here
		while(true) {
			try {
					Thread.sleep(1000);
					timeTick();
					tfMinute.setText(Integer.toString(minute));
					tfSecond.setText(Integer.toString(second));
			}
			catch(InterruptedException e) {
				break;
			}
		}
	}
	
	// This method will be called by when any of
	// the buttons in the GUI is pressed. So, we take action according to
	// which button has been pressed here.
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btStart) {
		// Create and start the clock thread so that it can increment
		// the clock every second
		// Put your code here
			clockThread = new Thread(this);
			clockThread.start();
		}
		if (e.getSource()==btStop) {
		// Put statement(s) here so that clockThread will not
		// increment the clock anymore
			if(clockThread != null) {
				clockThread.interrupt();
				clockThread = null;
			}
		}
		if (e.getSource()==btReset) {
			if(clockThread != null) {
				clockThread.interrupt();
				reset();
				clockThread = new Thread(this);
				clockThread.start();
			}
		}
	}
	
	private void timeTick() {
		second = (second + 1) % 60;
		if (second == 0)
		minute = (minute + 1) % 60;
	}
	
	private void reset() {
		second = 0;
		minute = 0;
		tfMinute.setText("0");
		tfSecond.setText("0");
		// Put statement(s) here so that the sleeping clockThread will
		// be woken up immediately. You should make sure that the clock
		// will continue to increment for every second. Also make sure
		// that the time taken from 0s to 1s really takes one second.
		// Hints: Use the interrupt() method of the Thread class.
	}
}
