public class Field {
	String[] field = {" ", " ", " "," ", " ", " "," ", " ", " "};
	String player1_X = "";
	String player2_O = "";
	
	public void getPlayers(String player1_X, String player2_O) {
		this.player1_X = player1_X;
		this.player2_O = player2_O;
	}
	
	public void draw() {
		System.out.println(this.field[0] + "|" + this.field[1] + "|" + this.field[2] + "\t1|2|3");
		System.out.println("-----\t-----\t" + player1_X + "=X");
		System.out.println(this.field[3] + "|" + this.field[4] + "|" + this.field[5] + "\t4|5|6");
		System.out.println("-----\t-----\t" + player2_O + "=O");
		System.out.println(this.field[6] + "|" + this.field[7] + "|" + this.field[8] + "\t7|8|9");
	}
	//\t\t" + player1_X + "=X"
	public void set(String player1_X, String player2_O, String currentPlayer, int pos) {			
		if(currentPlayer.equals(player1_X))
			this.field[pos-1] = "X";
		else
			this.field[pos-1] = "O";
	}
	
	public boolean isEmpty(int pos) {
		if(this.field[pos-1] == " ") 
		return true;
		return false;
	}
	
	public String getString(int pos) {
		return this.field[pos-1];
	}
	
	public boolean isFull() {
		int counter = 0;		
		for(String i : this.field) {
			if(i != " ") 
				counter++;
			
		}
		return counter>=this.field.length;
	}
	
	public String[] getFieldArray() {
		return this.field;
	}
	
}
