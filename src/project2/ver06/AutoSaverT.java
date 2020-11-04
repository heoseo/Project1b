package project2.ver06;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class AutoSaverT extends Thread{
	public HashSet<Object> accountSet;
	static int num=0;
	
	public AutoSaverT(HashSet<Object> accountSet) {
		this.accountSet = accountSet;
		num++;
	}
	
	@Override
	public void run() {

		try {
			while(true) {
				System.out.println(">>txt파일 저장 완료." + num);
				BufferedWriter out = new BufferedWriter(
						new FileWriter("src/project2/AutoSaveAccount.txt")
					);
				
				Iterator itr = accountSet.iterator();
//				System.out.println("현재 accountSet 크기 : " + accountSet.size());
				while(itr.hasNext()) {
					Account find = (Account)itr.next();
					String str = find.getInfo(); 
					out.write(str);
					out.newLine();
				}
				
				// 5초마다 저장
				out.close();
				sleep(5000);
			}
			
		}
		catch (InterruptedException e) {
			// interrupt
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
