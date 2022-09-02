package main;

public enum Symbole {
	ZERO("  ___\n / _ \\\n| | | |\n| | | |\n| |_| |\n \\___/\n"), UN(" __\n/_ |\n | |\n | |\n |_|\n"), DEUX(" ___\n|__ \\\n   ) |\n  / /\n / /_\n|____|"), TROIS(" ___\n|__ \\\n  _) |\n |_ <\n __) |\n|___/"), QUATRE(" _  _\n| || |\n| || |_\n|__   _|\n   | |\n   |_|\n"), CINQ(" ____\n| ___|\n| |_\n|__ \\\n __) |\n|___/\n"), SIX("   __\n  / /\n / /_\n|  _ \\\n| (-) |\n \\___/\n"), SEPT(" _____\n|___  |\n   / /\n  / /\n /_/\n"), HUIT("  ___\n / _ \\\n| (_) |\n > _ <\n| (_) |\n \\___/"), NEUF("  ___\n / _ \\\n| (_) |\n \\__  |\n   / /\n  /_/\n"), REVERSE("     __\n     \\ |\n     /\\|\n    (\n     )\n  |\\/\n  |_\\"), PASSER("  ___/\n /  /\\\n|  /  |\n| /   |\n X___/\n"), PLUS2("   ___\n  |   |\n _|_  |\n|   |_|\n|   |\n|___|\n"), PLUS4("   ___\n  |   |__\n _|_  |  |\n|   |_|  |\n|   ||___|\n|___|  |\n   |___|"), JOKER("     ___\n    / | \\\n   /  /  |\n  /__|___|\n  |  /   /\n   \\_|__/\n");
	Symbole(String string) {
		affichage=string;
	}

	private String affichage;
	
	public String toString() {
		return "\n"+affichage+"\n";
	}
}