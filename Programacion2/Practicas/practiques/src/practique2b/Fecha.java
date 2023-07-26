package practique2b;

public class Fecha {
	private int day;
	private int month;
	private int year;
	
	public Fecha(int day, int month, int year) {
		if(day > 31 || day > 31)
			System.out.println("The day is invalide");
		if(month < 1 || month > 12)
			System.out.println("The month is invalide");
		if(year <= 1900)
			System.out.println("Sorry, the year must be after 1900");
		this.day = day;
		this.month = month;
		this.year = year;
	}	
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}
	
	
	public int howManyDays(Fecha other) {
		if(other.getYear() < this.year)
			return -1;
		int years = other.getYear() - this.year;
		int months = (other.month > this.month)? other.month - this.month : this.month - other.month;
		int days = other.getDay() + this.day + (months * 31) + (years * 365); 
		
		return days;
	}
	
	public int whatDate(int days) {
		int day = this.day + days;
		while (day > 365) {
			
		}
		
		
//		int years = days / 365;
//		int months = days / 12;
//		int day = (days > 31)? this.day + 31 : this.day + days;
		return 0;
	}
	
}
