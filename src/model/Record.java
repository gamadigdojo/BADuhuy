package model;

public abstract class Record {
	 private final String Name;
	 private final Double Total;
	 private final String Date;
	 
	public Record(String name, Double total, String date) {
		super();
		Name = name;
		Total = total;
		Date = date;
	}
	public String getName() {
		return Name;
	}
	public Double getTotal() {
		return Total;
	}
	public String getDate() {
		return Date;
	}	

	 

}
