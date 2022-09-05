package main;

public enum Couleur {
	
	ROUGE('R'), VERT('V'), BLEU('B'), JAUNE('J'), SPECIAL('S');
	
	char initiale;
	
	Couleur(char i) {
		initiale=i;
	}
	
	public String toString() {
		String res="";
		res=this.name();
		return res ;
	}
	
}
