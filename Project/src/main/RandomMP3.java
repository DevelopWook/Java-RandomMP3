package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RandomMP3 extends JFrame {

	private JPanel contentPane;
	private JTextField txtSource;
	private JTextField txtDestination;
	private JTextField txtSize;
	
	private JFileChooser srcFileChooser; // src 파일 선택창
	private JFileChooser desFileChooser; // des 파일 선택 창
	private int mSize; // 복사 될 size
	private String srcPath; // src 경로
	private String desPath; // des 경로
	private String[] strFileList; // fileList String 버전
	private File[] fileList; // fileList File 버전
	private File srcFile; // src 폴더를 받아올 변수
	private File desFile; // des 폴더를 받아올 변수
	private boolean canRun = false;
	int[] selectedNumberList; // 선택된 리스트 넘버들 (fileList의 인덱스번호)
	String extension = "mp3"; // 확장자
	int maxSize; // 입력된 사이즈
	int nowSize; // 선택된 파일들 사이즈
	int percentNowSize; // progressBar 갱신용 사이즈
	
	// 파일 복사를 위해 필요한 변수들
	private File desFileName;
	private FileInputStream fi;
	private FileOutputStream fo;
	private BufferedInputStream in;
	private BufferedOutputStream out;
	int c;
	
	// 전역으로 사용하기 위해 빼둔 컴포넌트들
	private JList list;
	private JScrollPane scrollPane;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomMP3 frame = new RandomMP3();
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
	public RandomMP3() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSource = new JLabel("Source");
		lblSource.setBounds(12, 10, 57, 15);
		contentPane.add(lblSource);
		
		txtSource = new JTextField();
		txtSource.setEditable(false);
//		txtSource.setText("\uB178\uB798\uB97C \uBF51\uC744 \uACF3\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		txtSource.setText("노래를 뽑을 곳을 선택하세요.");
		txtSource.setBounds(81, 10, 289, 21);
		contentPane.add(txtSource);
		txtSource.setColumns(10);
		
		JLabel lblDestnation = new JLabel("Destination");
		lblDestnation.setBounds(12, 43, 69, 15);
		contentPane.add(lblDestnation);
		
		txtDestination = new JTextField();
		txtDestination.setEditable(false);
//		txtDestination.setText("\uBCF5\uC0AC\uB420 \uACF3\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		txtDestination.setText("복사될 곳을 선택하세요.");
		txtDestination.setBounds(81, 41, 289, 21);
		contentPane.add(txtDestination);
		txtDestination.setColumns(10);
		
//		JButton btnSource = new JButton("\uC120\uD0DD");
		JButton btnSource = new JButton("선택");
		// 소스 선택 이벤트
		btnSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				srcFileChooser = new JFileChooser();
				// 디렉터리만 선택 가능하게 모드 설정
				srcFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = srcFileChooser.showOpenDialog(null);
				if(JFileChooser.ERROR_OPTION == ret){
					// 에러처리
					System.out.println("에러");
				}
				else if(JFileChooser.APPROVE_OPTION == ret){
					// 열기 버튼
					srcPath = srcFileChooser.getSelectedFile().getPath().toString();
					txtSource.setText(srcPath);
				}
			}
		});
		btnSource.setBounds(382, 10, 97, 23);
		contentPane.add(btnSource);
		
//		JButton btnDestination = new JButton("\uC120\uD0DD");
		JButton btnDestination = new JButton("선택");
		// 목적지 선택 이벤트
		btnDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desFileChooser = new JFileChooser();
				// 디렉터리만 선택 가능하게 모드 설정
				desFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ret = desFileChooser.showOpenDialog(null);
				if(JFileChooser.ERROR_OPTION == ret){
					// 에러처리
					System.out.println("에러");
				}
				else if(JFileChooser.APPROVE_OPTION == ret){
					// 열기 버튼
					desPath = desFileChooser.getSelectedFile().getPath().toString();
					txtDestination.setText(desPath);
				}
			}
		});
		btnDestination.setBounds(382, 39, 97, 23);
		contentPane.add(btnDestination);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(12, 88, 57, 15);
		contentPane.add(lblSize);
		
		txtSize = new JTextField();
		txtSize.setBounds(81, 85, 116, 21);
		contentPane.add(txtSize);
		txtSize.setColumns(10);
		
		JLabel lblGb = new JLabel("MB");
		lblGb.setBounds(209, 88, 57, 15);
		contentPane.add(lblGb);
		
//		JButton btnRunList = new JButton("\uB9AC\uC2A4\uD2B8 \uBF51\uAE30");
		JButton btnRunList = new JButton("리스트 뽑기");
		// 리스트 뽑기 이벤트
		btnRunList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 진입전에  canRun을 false로 변경
				canRun = false;
				// 텍스트공간 모두 채웠나 검사
				if(txtSource.getText().toString().equals("노래를 뽑을 곳을 선택하세요.")){
					JOptionPane.showMessageDialog(contentPane, "노래를 뽑을 곳을 선택하세요.", "Message",
																JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(txtDestination.getText().toString().equals("복사될 곳을 선택하세요.")){
					JOptionPane.showMessageDialog(contentPane, "복사될 곳을 선택하세요.", "Message",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(txtSize.getText().toString().equals("")){
					JOptionPane.showMessageDialog(contentPane, "Size를 입력해주세요.", "Message",
							JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					// 사이즈가 100MB 이상인지 검사
					int iSize = Integer.parseInt(txtSize.getText());
					if(iSize < 100){
						JOptionPane.showMessageDialog(contentPane, "Size를 최소 100MB는 해주셔야합니다.", "Message",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				srcFile = new File(srcPath);
				fileList = srcFile.listFiles(new FilenameFilter() {
					// 파일 리스트 넘겨줄때 필터 설정 (File)
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return name.toLowerCase().endsWith("." + extension);
					}
				});
				strFileList = srcFile.list(new FilenameFilter() {
					// 파일 리스트 넘겨줄때 필터 설정 (String)
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return name.toLowerCase().endsWith("." + extension);
					}
				});
				
				// desFile도 일단 여기서 열어준다.
				desFile = new File(desPath);
				
				// Size 만큼만 랜덤으로 뽑기 처리
				int randMax = fileList.length;
				int tmp_size = Integer.parseInt(txtSize.getText());
				maxSize = tmp_size * 1024 * 1024;
				nowSize = 0;
				int[] tmpSelectedNumberList = new int[fileList.length];
				Random rand = new Random();
				int cnt = 0;
				while(true){
					// 사이즈만큼만
					if(maxSize <= nowSize){
						break;
					}
					// 중복검사
					for(int i=0; i<cnt; i++){
						if(tmpSelectedNumberList[cnt] == tmpSelectedNumberList[i]){
							cnt--;
						}
					}
					int n = (Math.abs(rand.nextInt()) % randMax);
					tmpSelectedNumberList[cnt] = n;
					cnt++;
					nowSize += fileList[n].length();
				}
				// 진짜 리스트 넣기 (length때문에 어쩔 수 없음)
				selectedNumberList = new int[cnt];
				for(int i=0; i<cnt; i++){
					selectedNumberList[i] = tmpSelectedNumberList[i];
				}
				
				//
				{
					JOptionPane.showMessageDialog(
						contentPane, 
						"입력한 Size : " + (maxSize/1024/1024) + "MB\n선택된 Size : " + (nowSize/1024/1024) + "MB\n선택된 노래 갯수 : " + cnt + "개", 
						"Message",
						JOptionPane.INFORMATION_MESSAGE
					);
				}
				//
				strFileList = new String[cnt];
				for(int i=0; i<cnt; i++){
					strFileList[i] = fileList[selectedNumberList[i]].getName();
				}
				
				// 리스트에 갱신
				list = new JList(strFileList);
				scrollPane.setViewportView(list);
				
				// 리스트 뽑기가 완료되면 canRun true로 변경
				canRun = true;
			}
		});
		btnRunList.setBounds(382, 179, 115, 21);
		contentPane.add(btnRunList);
		
//		JButton btnRunCopy = new JButton("\uBCF5\uC0AC \uC9C4\uD589");
		JButton btnRunCopy = new JButton("복사 진행");
		// 복사 진행 버튼 이벤트
		btnRunCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 복사 진행시 사용자 조작 못하게 방지
				btnSource.setEnabled(false);
				btnDestination.setEnabled(false);
				btnRunList.setEnabled(false);
				btnRunCopy.setEnabled(false);
				txtSize.setEnabled(false);
				
				// 문제없나 검사
				if(false == canRun){
					JOptionPane.showMessageDialog(
            contentPane, 
            "파일 복사를 진행 할 수 없습니다." + "\n 빈 곳이 있나 확인해주십시오.", 
            "Message",
            JOptionPane.ERROR_MESSAGE);
          
          // 다시 enable 시키기
					btnSource.setEnabled(true);
					btnDestination.setEnabled(true);
					btnRunList.setEnabled(true);
					btnRunCopy.setEnabled(true);
					txtSize.setEnabled(true);
					return;
				}
				
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						// percentNowSize 초기화
						percentNowSize = 0;
						// 스레드 동작시키기
						ProgressThread prthread = new ProgressThread(progressBar);
						prthread.start();
						
						try{
							for(int i=0; i<selectedNumberList.length; i++){
								File f = fileList[selectedNumberList[i]];
								desFileName = new File(desFile.getPath() + "\\" + f.getName()); // 파일 이름 정하기
								fi = new FileInputStream(f);
								fo = new FileOutputStream(desFileName);
								in = new BufferedInputStream(fi);
								out = new BufferedOutputStream(fo);
								
								while(-1 != (c = in.read())){
									out.write(c);
								}
								
								// percentNowSize 갱신
								percentNowSize += f.length();
								System.out.println("복사완료 : " + f.getName());
								
								// 파일 복사 완료되었으면 변수들 닫기 (파일 한개당 수행해야함)
								in.close();
								out.close();
								fi.close();
								fo.close();
							}
							
							// 스레드 중지
							prthread.setStop();
							
							System.out.println("파일 복사 완료");
							JOptionPane.showMessageDialog(contentPane, "파일 복사가 완료되었습니다.", "Message",
									JOptionPane.INFORMATION_MESSAGE);
							
							// 버튼 원상복구
							btnSource.setEnabled(true);
							btnDestination.setEnabled(true);
							btnRunList.setEnabled(true);
							btnRunCopy.setEnabled(true);
							txtSize.setEnabled(true);
							
						}catch(Exception exception){
							System.out.println("파일 복사 오류");
							exception.printStackTrace();
							JOptionPane.showMessageDialog(contentPane, "파일 복사 도중 오류가 발생했습니다."
									+ "\n 파일 복사를 중지합니다.", "Message",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}).start();
			}
		});
		btnRunCopy.setBounds(382, 268, 115, 23);
		contentPane.add(btnRunCopy);
		
		JLabel lblgbmb = new JLabel("1GB = 1024MB");
		lblgbmb.setBounds(209, 114, 90, 15);
		contentPane.add(lblgbmb);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 139, 342, 346);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(366, 301, 146, 14);
		contentPane.add(progressBar);
	}
	
	class ProgressThread extends Thread{
		JProgressBar progressBar;
		boolean isRun = true;
		
		public ProgressThread(JProgressBar progressBar){
			this.progressBar = progressBar;
		}
		public void setStop(){
			isRun = false;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			
			isRun = true;
			int min = 0; // progressBar의 최소값
			int max = 100; // progressBar의 최대값
			double t;
			int nowPercent;
			
			progressBar.setMinimum(min);
			progressBar.setMaximum(max);
			progressBar.setValue(min);
			
			while(true){
				if(false == isRun){
					break;
				}
				progressBar.setValue((int)((double)percentNowSize/nowSize*100)+2);
				System.out.println("현재 진행도 : " + progressBar.getValue());
				try{
					Thread.sleep(100);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
