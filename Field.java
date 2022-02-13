public class Field {
	String[] field = {" ", " ", " "," ", " ", " "," ", " ", " "};  //3x3 player field
	
	public void draw(String player1_X, String player2_O) {	//draw the field with its corresponding values
		System.out.println(this.field[0] + "|" + this.field[1] + "|" + this.field[2] + "\t1|2|3");
		System.out.println("-----\t-----\t" + player1_X + "=X");
		System.out.println(this.field[3] + "|" + this.field[4] + "|" + this.field[5] + "\t4|5|6");
		System.out.println("-----\t-----\t" + player2_O + "=O");
		System.out.println(this.field[6] + "|" + this.field[7] + "|" + this.field[8] + "\t7|8|9");
	}
	
	public void set(String player1_X, String player2_O, String currentPlayer, int pos) {	//sets the symbol of the curent player
		if(currentPlayer.equals(player1_X))													//to the given position on the field
			this.field[pos-1] = "X";
		else
			this.field[pos-1] = "O";
	}
	
	public boolean isEmpty(int pos) {	//checks if the field with the given position is empty
		if(this.field[pos-1] == " ") 
		return true;
		return false;
	}
	
	public String getString(int pos) {	//get the String that is located at the given position on the field
		return this.field[pos-1];		//possible returns: " ", "X", "O"
	} 
	
	public boolean isFull() {			//checks if the field is full or if there are spaces left where the
		int counter = 0;				//player could set his symbol
		for(String i : this.field) {
			if(i != " ") 
				counter++;
			
		}
		return counter >= this.field.length;
	}
	
	public String[] getFieldArray() {	//returns the field array
		return this.field;
	}
	
}
