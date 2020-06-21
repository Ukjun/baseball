package jun;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main implements Runnable{
	public static final int ServerPort = 9999;
	public static final String ServerIP = "192.168.10.5";
	public static void main(String[] args) throws IOException{
		Scanner scanner = new Scanner(System.in);
		ServerSocket serverSocket = null;
		Socket socket = null;
		PrintWriter printwriter = null;
		BufferedReader br = null;
		FileInputStream fis;
		String filePath = "C:\\Users\\corn1\\source\\repos\\Opencv(2)\\Opencv(2)";
		byte[] buf = new byte[1024];
		int len;
		serverSocket = new ServerSocket(ServerPort);
		
		try {
			socket = serverSocket.accept();
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			System.out.println("연결");
			File files = new File(filePath);
			System.out.println("파일 열기 ");
			dos.writeInt(files.listFiles().length);
			System.out.println(files.listFiles().length);
			int a=0;
			for(File file: files.listFiles()) {
				System.out.println(file.isFile());
				if(file.isFile()) {
					dos.writeUTF(file.getName());
					System.out.println(file.getName());
					fis = new FileInputStream(file);
					dos.writeLong(file.length());
					//System.out.println(files.getName());
					while((len = fis.read(buf))!=-1) {
						dos.write(buf, 0, len);
						dos.flush();
					}
				}else
					break;
			}
			printwriter = new PrintWriter(socket.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				
				String line = null;
				line = br.readLine();
				System.out.println("받은 글자 : "+line);
				printwriter.println(line);
				//System.out.println("약 "+ datas);
				if(line.equals("quit"))
					break;
			}
				printwriter.close();
				br.close();
				socket.close();
				serverSocket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}