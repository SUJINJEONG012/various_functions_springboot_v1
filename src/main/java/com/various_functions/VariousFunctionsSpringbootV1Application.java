package com.various_functions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VariousFunctionsSpringbootV1Application {

	public static void main(String[] args) {
		SpringApplication.run(VariousFunctionsSpringbootV1Application.class, args);
		
//		//10부터 1까지 반복문 이용하여 출력
//		for(int i = 10; i >= 1; i--) {
//			System.out.println(i);
//		};
//		
//		// 1부터 10까지 모든 수를 더한 값을 result 변수에 담은 후 result 출력 
//		int result = 0;
//		for(int i=1; i<=10; i++) {
//			result += i;
//		}
//		System.out.println("합:"+  result);
//		
//		// 1~30 중에 2의 배수만 반복문을 이용하여
//		// 1.증감식에서만 2의 배수를 해결
//		for(int i=2; i<=30; i+=2) {
//			System.out.println("증감식에서만 2의 배수를 해결 :" + i);
//		}
//		// 2.If 문으로 2의 배수 해결
//		for(int i =1; i<=30; i++) {
//			if(i % 2 == 0) {
//				System.out.println("if문 2의배수 :"+i);
//				}
//			}
//		
//		int i, j;
//		for(i=1; i<=5; i++) {
//			for(j=1; j<=5; j++) {
//				System.out.print("* ");
//			}
//			System.out.println();
//		}
		
		char[][] board = {
				{'X','O'},
				{'O','X'}
		};
		
		for(int i =0; i< board.length; i++) {
			for(int j =0; j < board[i].length; j++ ) {
				System.out.println(board[i][j]);
			}
			System.out.println();
		}
		
		
		
		
	}

}
