package project2.ver02;

public class CustomSpecialRate {

	public int plusInterest;
	
	public CustomSpecialRate(String grade) {
		switch(grade) {
		case "A":
			plusInterest = 7;
			break;
		case "B":
			plusInterest = 4;
			break;
		case "C":
			plusInterest = 2;
			break;
		}
	}
}
