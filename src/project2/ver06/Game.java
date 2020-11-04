package project2.ver06;

import java.util.Random;
import java.util.Scanner;

public class Game {
	
	
	int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};		
	Scanner sc = new Scanner(System.in);
	int curX=2; int curY=2;
	boolean endGame = true;
	
	public Game() {
		shuffle(5);			// 3X3 배열 초기화하고 프린트
		

		while(endGame) {
			gameStart();		// 섞기 시작
			// 123456789 되면 게임 종료.

			System.out.println("재시작하시겠습니까?(Y : 재시작, N : 종료)");
			System.out.print(">>> ");
			String a = sc.nextLine();
			if(a.equals("y") || a.equals("Y")) {
				shuffle(100);
				gameStart();
				continue;
			}
			else if(a.equals("n") || a.equals("N")) {
				break;
			}
			else {
				System.out.println("Y와 N중에 입력하세요.");
				continue;
			}
			
		}
		
		System.out.println("\n게임종료\n");
	}

	
	
	public void shuffle(int num){
		Random random = new Random();
		
		for(int i = 0; i < num; i++) {
			int k = random.nextInt(4);
			if(canMove(k))
				swap(k);
			else
				i--;
		}
		
	}
	
	// 프린트
	public void printBoard() {
		
		System.out.println("\n==============");
		for(int i = 0; i < board.length; i++) {
			System.out.print("  ");
			for(int j = 0; j < board[i].length;j++) {
				if(board[i][j] == 9) {
					System.out.print(" X ");		// 9 대신 X 움직임.
				}
				else
					System.out.print(" " + board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("==============\n");
	}
	
	// 게임 진행
	public void gameStart() {
		
		System.out.println("\n*****게임 시작*****\n");
		printBoard();
		System.out.println("\n[이동] A:←   D:→   W:↑   S:↓");
		System.out.println("[종료] X:exit");
		
		while(!rule()) {
			
			// 섞기
			System.out.print("키 입력 >> ");
			String key = sc.nextLine();
			
			switch(key) {
			case "x": case "X":
				return;
			case "a": case "A":
				if(canMove(0))	// canMove() : ture=>이동 가능.
				{
					swap(0);
					printBoard();
				}
				else
					System.out.println("\nxxxxxxxxxxx이동불가xxxxxxxxxx");
				break;
			case "d": case "D":
				if(canMove(1))
				{
					swap(1);
					printBoard();
				}
				else
					System.out.println("\nxxxxxxxxxxx이동불가xxxxxxxxxx");
				break;
			case "w": case "W":
				if(canMove(2))
				{
					swap(2);
					printBoard();
				}
				else
					System.out.println("\nxxxxxxxxxxx이동불가xxxxxxxxxx");
				break;
			case "s": case "S":
				if(canMove(3))
				{
					swap(3);
					printBoard();
				}
				else
					System.out.println("\nxxxxxxxxxxx이동불가xxxxxxxxxx");
				break;
			default:
				System.out.println("잘못된 값을 입력하셨습니다.\n");
				break;
			}
			
			// rule()검사 결과 false면 123456789이므로 반복문 탈출.
		}
		System.out.println("\n게임이 종료되었습니다.\n");
	}
	
		
	// X위치 바꾸기.
	public void swap(int num) {
		int [][]direction 
			= {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};	// ←, →, ↑, ↓
		
		// 바뀔 좌표 설정.
		int nextX = curX + direction[num][0];
	    int nextY = curY + direction[num][1];
	 
	    int tmp = board[curX][curY];
	    board[curX][curY] = board[nextX][nextY];
	    board[nextX][nextY] = tmp;
	    
	    
	    // 현재 위치를 next로 설정.
	    curX = nextX;
	    curY = nextY;
	}
	
	// 움직일 수 있는지 검사
	public boolean canMove(int key) {		// a=1 이면 이동불가 표시. 0이면 표시안함.
		
		boolean flag = true;
		switch(key) {
		case 0:		// ←
			if(curY==0) 
				flag = false;					
			break;
		case 1:		// →
			if(curY==2) 
				flag = false;
			break;
		case 2:		// ↑
			if(curX==0) 
				flag = false;
			break;
		case 3:		// ↓
			if(curX==2) 
				flag = false;
			break;
		}
		
		return flag;
	}
	
	// 123456789인지 검사.
	public boolean rule() {
		
		int k = 1;
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length;j++) {
				if(board[i][j] != k++) {
					return false;	// 아니면 false 반환
				}
			}
		}
		
		return true;
	}

}
