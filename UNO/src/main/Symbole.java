package main;

public enum Symbole {
	ZERO("  ___  \n / _ \\ \n| | | |\n| | | |\n| |_| |\n \\___/ "),UN("  __ \n /_ |\n  | |\n  | |\n  | |\n  |_|"), DEUX(" ___  \n|__ \\ \n   ) |\n  / / \n / /_ \n|____|"), TROIS(" ___  \n|__ \\ \n  _) |\n |_ < \n __) |\n|___/ "), QUATRE(" _  _   \n| || |  \n| || |_ \n|__   _|\n   | |  \n   |_|  "), CINQ(" ____ \n| ___|\n| |_  \n|__ \\ \n __) |\n|___/ "), SIX("   __  \n  / /  \n / /_  \n|  _ \\ \n| (-) |\n \\___/ "), SEPT(" _____ \n|___  |\n   / / \n  / /  \n /_/   "), HUIT("  ___  \n / _ \\ \n| (_) |\n > _ < \n| (_) |\n \\___/ "), NEUF("  ___  \n / _ \\ \n| (_) |\n \\__  |\n   / / \n  /_/  "), REVERSE("     __\n     \\ |\n     /\\|\n    (\n     )\n  |\\/\n  |_\\ "), PASSER("  ___/ \n /  /\\ \n|  /  |\n| /   |\n X___/ \n/      "), PLUS2("   ___ \n  |   |\n _|_  |\n|   |_|\n|   |  \n|___|  "), PLUS4("   ___    \n  |   |__ \n _|_  |  |\n|   |_|  |\n|   ||___|\n|___|  |  \n   |___|  "), JOKER("     ___  \n    / | \\ \n   /  /  |\n  /__|___|\n  |  /   /\n   \\_|__/ ");
	Symbole(String string) {
		affichage=string;
	}

	private String affichage;
	
	public String toString() {
		return affichage;
	}
}
