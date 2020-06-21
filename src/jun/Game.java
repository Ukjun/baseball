package jun;

import java.util.*;

public class Game {
	int strike;
	int ball;
	int count=0;
	int start;
	int check_count = 1;
	int [] com;
	int [] player;
	Random ran = new Random();
	Scanner scanner = new Scanner(System.in);
	public Game() {
		
	}
	public Game(int strike, int ball) {
		super();
		this.strike = strike;
		this.ball = ball;
	}
	
	
	public int start() {
		System.out.print("맞추실 숫자 갯수를 정하세요: ");
		String str = scanner.next();
		start = Integer.parseInt(str);
		com = new int[start];
		player = new int[start];
		comNum(com,start);
		run();
		return start;	
	}
	
	
	public int[] check(int[]arr, int a) {
		for(int i=0; i<a; i++) {
			if(arr[i]==arr[a]) {
				System.out.println(arr[i]);
				i--;
			}
		}
		return arr;
	}
	public int[] comNum(int[]arr, int a) {
		for(int i=0; i<a; i++) {
			arr[i] = ran.nextInt(9)+1;
			check(arr,i);
			System.out.println(arr[i]);
		}
		return arr;
	}
	
	public int[] Input(int[] arr,int a) {
		for(int i=0; i<a; i++) {
			arr[i] = scanner.nextInt();
			check(arr,i);
			
		}
		return arr;
	}
	public void check_strike() {
		strike = ball =0;
		for(int i=0; i<start; i++) {
			for(int j=0; j<start; j++) {
				if(com[i]==player[j]) {
					if(i==j) {
						strikeUp();
					}
					else {
						ballUp();
					}
				}
			}
		}
	}
	public void run() {
		while(true) {
			if(strike==start) {
				end();
				break;
			}
			System.out.println(check_count+"번째");
			System.out.print("숫자를 입력하세요!: ");
			Input(player,start);
			check_strike();
			System.out.printf("%d스트라이크 %d볼 %d아웃",strike, ball, start-strike-ball);
			System.out.println();
			check_strike();
			check_count++;
		}
	}
	
	public void end() {
		System.out.println("축하합니다 컴퓨터의 숫자는");
		for(int i=0; i<start; i++) {
			System.out.print(com[i]+" ");
		}
	}
	public void strikeUp() {
		strike++;
	}
	public void ballUp() {
		ball++;
	}
}
