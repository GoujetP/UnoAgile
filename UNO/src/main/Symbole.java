package main;

public enum Symbole {
	ZERO("  ___  a\n / _ \\ a\n| | | |a\n| | | |a\n| |_| |a\n \\___/ a\n"),UN("  __ a\n /_ |a\n  | |a\n  | |a\n  | |a\n  |_|a\n"), DEUX(" ___  a\n|__ \\ a\n   ) |a\n  / / a\n / /_ a\n|____|a"), TROIS(" ___  a\n|__ \\ a\n  _) |a\n |_ < a\n __) |a\n|___/ a"), QUATRE(" _  _   a\n| || |  a\n| || |_ a\n|__   _|a\n   | |  a\n   |_|  a"), CINQ(" ____ a\n| ___|a\n| |_  a\n|__ \\ a\n __) |a\n|___/ a"), SIX("   __  a\n  / /  a\n / /_  a\n|  _ \\ a\n| (-) |a\n \\___/ a"), SEPT(" _____ a\n|___  |a\n   / / a\n  / /  a\n /_/   a"), HUIT("  ___  a\n / _ \\ a\n| (_) |a\n > _ < a\n| (_) |a\n \\___/ a"), NEUF("  ___  a\n / _ \\ a\n| (_) |a\n \\__  |a\n   / / a\n  /_/  a"), REVERSE("     __\n     \\ |\n     /\\|\n    (\n     )\n  |\\/\n  |_\\ "), PASSER("  ___/ a\n /  /\\ a\n|  /  |a\n| /   |a\n X___/ a\n/      a"), PLUS2("   ___ a\n  |   |a\n _|_  |a\n|   |_|a\n|   |  a\n|___|  a"), PLUS4("   ___    a\n  |   |__ a\n _|_  |  |a\n|   |_|  |a\n|   ||___|a\n|___|  |  a\n   |___|  a"), JOKER("     ___  a\n    / | \\ a\n   /  /  |a\n  /__|___|a\n  |  /   /a\n   \\_|__/ a");
	Symbole(String string) {
		affichage=string;
	}

	private String affichage;
	/*
	public String toString() {
		return affichage;
	}
	*/
	
}
