package project2.ver04;

import java.io.Serializable;

public class CustomSpecialRate implements Serializable{

	public int plusInterest;
	
	public CustomSpecialRate(String grade) {
		switch(grade) {
		case "A": case "a":
			plusInterest = 7;
			break;
		case "B": case "b":
			plusInterest = 4;
			break;
		case "C": case "c":
			plusInterest = 2;
			break;
		}
	}
}
