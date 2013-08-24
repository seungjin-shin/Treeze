package freemind.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import freemind.controller.FreemindManager;
import freemind.json.ArrayClass;
import freemind.json.FreemindGson;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;


public class LecturePageFrame extends JFrame {
	private Container ct;
	final String SERVERIP = "113.198.84.80";
	
	JPanel classPanel;
	private Image profileImg;
	private Image logo;
	ActionListener btnListener;
	MindMapController mc;
	private Image tmp;
	String lectureName;
	JScrollPane sPanel;
	String lectureId;
	public LecturePageFrame(MindMapController mc, String lectName, String lectureId) {
		this.mc = mc;
		this.lectureId = lectureId;
		lectureName = lectName;
		profileImg = Toolkit.getDefaultToolkit().getImage("images/minsuk.jpg");
		logo = Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png");
		btnListener = new BtnListener(this);
		classPanel = new ClassPanel(this, mc);
		
		setSize(950, 800);
		setLayout(null);
		
		getContentPane().setBackground(new Color(141, 198, 63));

		Font lagF = new Font("Serif", Font.BOLD, 30);
		Font midF = new Font("Serif", Font.BOLD, 16);
		
		JLabel name = new JLabel("Minsuk Lee");
		JLabel address = new JLabel("Department of Computer Engineering,");
		JLabel address2 = new JLabel("Hansung University, Professor");

		JLabel ltName = new JLabel(lectureName);
		ltName.setSize(300, 40);
		ltName.setLocation(400, 20);
		ltName.setFont(lagF);
		add(ltName);
		
		name.setSize(300, 40);
		name.setFont(lagF);
		name.setLocation(70, 300);
		add(name);
		
		address.setSize(300, 40);
		address.setFont(midF);
		address.setLocation(20, 350);
		add(address);
		
		address2.setSize(300, 40);
		address2.setFont(midF);
		address2.setLocation(50, 370);
		add(address2);
		
		tmp = Toolkit.getDefaultToolkit().getImage("images/CreateLecture.png");
		
		JButton tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(132, 45);
		tmpBtn.setLocation(450, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		tmp = Toolkit.getDefaultToolkit().getImage("images/profile.png");
		
		tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(130, 45);
		tmpBtn.setLocation(300, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		tmp = Toolkit.getDefaultToolkit().getImage("images/deleteLecture.png");
		
		tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(135, 42);
		tmpBtn.setLocation(600, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		tmp = Toolkit.getDefaultToolkit().getImage("images/logout.png");
		
		tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(132, 45);
		tmpBtn.setLocation(750, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		
		sPanel = new JScrollPane(classPanel);
		sPanel.setBounds(290, 130, 600, 498);
		add(sPanel);
		
		setTitle("Select your Class");
		setVisible(true);
		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(profileImg, 85, 150, 130, 170, null);
		g.drawImage(logo, 40, 50, 150, 30, null);
		g.drawLine(30, 90, 900, 90);
		
		g.setColor(Color.white);
		g.drawLine(40, 380, 270, 380);
		g.drawLine(40, 450, 270, 450);
		
		g.setColor(Color.black);
		g.drawRect(298, 158, 600, 500);
		g.drawRect(297, 157, 602, 502);
		repaint();
	}
	
	class ClassTopBarPanel extends JPanel implements ActionListener{
		ClassPanel frame;
		Image tmpURL;
		public ClassTopBarPanel(ClassPanel frame) {
			this.frame = frame;
			setSize(450, 500);
			setLayout(null);
			setBackground(new Color(141, 198, 63));
			
			tmpURL = Toolkit.getDefaultToolkit().getImage("images/addClass.png");
			JButton tmpBtn = new JButton(new ImageIcon(tmpURL));
			tmpBtn.setSize(88, 30);
			tmpBtn.setLocation(360, 10);
			tmpBtn.setFocusable(false);
			tmpBtn.addActionListener(this);
			add(tmpBtn);
			
			tmpURL = Toolkit.getDefaultToolkit().getImage("images/deleteClass.png");
			tmpBtn = new JButton(new ImageIcon(tmpURL));
			tmpBtn.setSize(89, 30);
			tmpBtn.setLocation(460, 10);
			tmpBtn.setFocusable(false);
			add(tmpBtn);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new InputClassFrame(frame);
		}
	}
	class InputClassFrame extends JFrame implements ActionListener{
		ClassPanel frame;
		JTextField classtf;
		
		public InputClassFrame(ClassPanel frame) {
			this.frame = frame;
			
			setSize(380, 100);
			setLayout(null);
			setTitle("Input your class title");
			setVisible(true);
			setLocation(350, 200);
			
			getContentPane().setBackground(new Color(141, 198, 63));
			JLabel inputLb = new JLabel("Title :");
			inputLb.setSize(50, 30);
			inputLb.setLocation(10, 10);

			classtf = new JTextField();
			classtf.setSize(150, 25);
			classtf.setLocation(60, 10);
			JButton input = new JButton("Create class");
			input.addActionListener(this);
			input.setSize(110, 25);
			input.setLocation(240, 10);
			add(inputLb);
			add(classtf);
			add(input);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String classTitle = classtf.getText();
			classTitle = classTitle.trim();
			JDialog dlg;
			
			if(classTitle.equals("")){
				dlg = new JDialog(this, "Error", true);
				JLabel errLb = new JLabel("Input your class!");
				dlg.setLayout(new FlowLayout());
				dlg.add(errLb);
				dlg.setBounds(150,200,200,100);
				dlg.setVisible(true);
				return;
			}
			else{
				UploadToServer uploadToServer = new UploadToServer();
				uploadToServer.classPost(lectureId, "minsuk@hansung.ac.kr", classTitle);

				frame.init();
				frame.update(frame.getGraphics());
				sPanel.updateUI();
				
				this.setVisible(false);
			}
		}
	}
	class ClassPanel extends JPanel implements ActionListener{
		JLabel tmpLb, professor, state;
		Image onBookMark, offBookMark;
		Image onState, offState;
		JFrame frame;
		Image slideShowURL, mindmapURL;
		
		MindMapController mc;
		final int TOPPADDING = 130;
		final int CLASSHGAP = 60;
		int classCnt = 0;
		Font lagf = new Font("Serif", Font.BOLD, 30);
		String[] latestDay = {"2013.4.11", "2013.5.21", "2013.1.22", "2012.12.23", "2012.1.3", "2013.4.23", "2013.3.3", "2012.11.23", "2012.10.1"
				, "2012.5.23", "2013.5.23", "2012.2.23", "2012.1.28", "2012.6.23", "2012.7.23", "2011.7.23", "2012.8.15", "2011.7.2"}; // 18개
		public ClassPanel(JFrame frame, MindMapController mc) {
			this.frame = frame;
			this.mc = mc;
			setBackground(Color.white);
			setSize(450, 500);
			setLayout(null);
			
			slideShowURL = Toolkit.getDefaultToolkit().getImage("images/slideShow.png");
			mindmapURL = Toolkit.getDefaultToolkit().getImage("images/mindmap.png");
			
			tmpLb = new JLabel("Class Title");
			tmpLb.setSize(200, 50);
			tmpLb.setFont(lagf);
			tmpLb.setLocation(60, 60);
			add(tmpLb);
			
			tmpLb = new JLabel("View");
			tmpLb.setSize(100, 50);
			tmpLb.setFont(lagf);
			tmpLb.setLocation(490, 60);
			add(tmpLb);
			
			tmpLb = new JLabel("Latest class day");
			tmpLb.setSize(250, 50);
			tmpLb.setFont(lagf);
			tmpLb.setLocation(240, 60);
			add(tmpLb);
			
			ClassTopBarPanel classToppn = new ClassTopBarPanel(this);
			classToppn.setSize(580, 50);
			classToppn.setLocation(10, 10);
			add(classToppn);
			
			init();
		}
			
		public void init(){	
			String sHtml = "";
			BufferedReader in = null;
			String buf = "";
			boolean isConnectErr = false;
			try
			{
				URL url = new URL("http://" + SERVERIP + ":8080/treeze/getClasses?lectureId=" + lectureId);
			    URLConnection urlconn = url.openConnection();
			    in = new BufferedReader(new InputStreamReader(urlconn.getInputStream(),"UTF-8"));

			    while((buf = in.readLine()) != null)
			    {
			        sHtml += buf;
			    }
			}
			catch(Exception e)
			{
				isConnectErr = true;
			    System.out.println("연결 에러");
			}
			finally
			{
			    if(sHtml.equals("")) sHtml = "Data가 존재하지 않습니다";
			    try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!isConnectErr) {
				System.out.println(sHtml);
				ArrayList<freemind.json.ClassInfo> classList = new ArrayList<freemind.json.ClassInfo>();
				freemind.json.ClassInfo tmpClass;
				FreemindGson myGson = new FreemindGson();
				Gson gson = new Gson();

				java.lang.reflect.Type type = new TypeToken<ArrayClass>() {
				}.getType();
				ArrayClass jonResultClasslist = (ArrayClass) gson.fromJson(
						sHtml, type);
				classList = jonResultClasslist.getClasses();

				JLabel tmpLb;
				JButton slideBtn;
				JButton mindmapBtn;
				Font midf = new Font("Serif", Font.BOLD, 20);
				for (classCnt = 0; classCnt < classList.size(); classCnt++) {
					tmpClass = classList.get(classCnt);
					tmpLb = new JLabel(tmpClass.getClassName());
					tmpLb.setFont(midf);
					tmpLb.setSize(240, 50);
					tmpLb.setLocation(40, TOPPADDING + classCnt * CLASSHGAP);
					add(tmpLb);

					tmpLb = new JLabel(latestDay[classCnt % 18]);
					tmpLb.setFont(midf);
					tmpLb.setSize(240, 50);
					tmpLb.setLocation(295, TOPPADDING + classCnt * CLASSHGAP);
					add(tmpLb);

					slideBtn = new JButton(new ImageIcon(slideShowURL));
					mindmapBtn = new JButton(tmpClass.getClassId() + "",
							new ImageIcon(mindmapURL));

					slideBtn.setSize(100, 24);
					slideBtn.setLocation(472, TOPPADDING + classCnt * CLASSHGAP);
					slideBtn.setFocusable(false);
					add(slideBtn);

					mindmapBtn.setSize(100, 24);
					mindmapBtn.setLocation(472, TOPPADDING + classCnt
							* CLASSHGAP + 25);
					mindmapBtn.setFocusable(false);
					mindmapBtn.addActionListener(this);
					add(mindmapBtn);
				}
				setPreferredSize(new Dimension(550, 20 + TOPPADDING + classCnt
						* CLASSHGAP));
			}
		}

		public void paint(Graphics g) {
			super.paint(g);
			g.drawLine(20, 120, 580, 120);
			if(classCnt != 0){
				g.drawLine(220, 140, 220, TOPPADDING + CLASSHGAP * classCnt);
				g.drawLine(450, 140, 450, TOPPADDING + CLASSHGAP * classCnt);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String event = e.getActionCommand();
			System.out.println("classId =" + event);
			mc.getController().setClassId(Integer.parseInt(event));
			mc.open();
			
			HttpClient httpClient = new DefaultHttpClient();  
	      	  HttpPost post = new HttpPost("http://" + SERVERIP + ":8080/treeze/setStateOfLecture");
	      	  MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
	      	  
	      	  
			try {
				StringBody lectureTitleBody = new StringBody("temp", Charset.forName("UTF-8"));
				StringBody profEmailBody = new StringBody("minsuk@hansung.ac.kr", Charset.forName("UTF-8"));
				StringBody lectureState = new StringBody("true", Charset.forName("UTF-8"));
				StringBody lectureIdBody = new StringBody(lectureId, Charset.forName("UTF-8"));
				
				multipart.addPart("lectureName", lectureTitleBody);  
				multipart.addPart("professorEmail", profEmailBody);
				multipart.addPart("stateOfLecture", lectureState);
				multipart.addPart("lectureId", lectureIdBody);
				
				post.setEntity(multipart);  
				HttpResponse response = httpClient.execute(post);  
				HttpEntity resEntity = response.getEntity();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	         
	      	  System.out.println("set state true");
			
			frame.setVisible(false);
		}
	}
}





	
