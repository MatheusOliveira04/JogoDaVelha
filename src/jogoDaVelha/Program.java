package jogoDaVelha;

import java.util.Scanner;

public class Program {

	private static Scanner ler = new Scanner(System.in);

	private static char[][] jogoDaVelha = new char[3][3];
	private static char valorXO = 'X';
	private static int rodada = 1;
	private static int somaRodadas = 0;
	private static boolean empate;
	
	public static void main(String[] args) {

		boolean fim = false;
		while (fim == false) {
			mostrar();
			if (logicaLinha() == true || logicaColuna() == true || logicaDiagonal() == true) {
				fim = true;
			} else {
				empate = logicaEmpate();
				if(empate == true) {
					break;
				}
			}
		}
		if(empate == false) {
		int jogador = rodada % 2; // ou é jogador 0 ou é jogador 1
		if (jogador == 0) { // Jogador 0 corresponde ao jogador 2, //jogador 1 corresponde ao jogador 1
			jogador = 1;
		} else {
			jogador = 2;
		}
		System.out.println("Jogador " + jogador + " venceu!!!");
		}
	}

	private static int turnoDoJogador() {
		if (rodada % 2 == 1) {
			rodada = 1;
			valorXO = 'X';
		} else {
			rodada = 2;
			valorXO = 'O';
		}
		return rodada;
	}

	private static void mesa() {
		int jogadorVar = turnoDoJogador();
		int linha = 0, coluna = 0;

		boolean jaExiste = false;
		while (jaExiste == false) {

			System.out.println("Vez do jogador " + jogadorVar);

			boolean posicaoInvalida = true;
			while (posicaoInvalida == true) {

				System.out.print("Digite uma posição da linha(1-3): ");
				linha = ler.nextInt();
				System.out.print("Digite uma posição da coluna(1-3): ");
				coluna = ler.nextInt();

				if ((linha >= 1 && linha <= 3) && (coluna >= 1 && coluna <= 3)) {
					posicaoInvalida = false;
				} else {
					System.out.println("Posição inválida. Tente novamente!");
				}
			}
			char x = 'X', o = 'O';
			if (jogoDaVelha[linha - 1][coluna - 1] == x || jogoDaVelha[linha - 1][coluna - 1] == o) {
				System.out.println("Já existe um valor nessa posição. Tente novamente");
			} else {
				linha--;
				coluna--;
				jogoDaVelha[linha][coluna] = valorXO;
				jaExiste = true;
			}
		}

		rodada++;
	}

	private static void mostrar() {
		mesa();
		for (int i = 0; i < jogoDaVelha.length; i++) {
			for (int j = 0; j < jogoDaVelha[i].length; j++) {
				System.out.print(jogoDaVelha[i][j] + " | ");
			}
			System.out.println();
		}
	}

	private static boolean logicaLinha() {
		char x = 'X', o = 'O';
		int verifica = 0;
		for (int j = 0; j < jogoDaVelha.length; j++) {
			if (jogoDaVelha[0][j] == x && jogoDaVelha[1][j] == x && jogoDaVelha[2][j] == x) {
				verifica = 1;
			} else if (jogoDaVelha[0][j] == o && jogoDaVelha[1][j] == o && jogoDaVelha[2][j] == o) {
				verifica = 1;
			}
		}
		if (verifica == 1) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean logicaColuna() {
		char x = 'X', o = 'O';
		int verifica = 0;
		for (int i = 0; i < jogoDaVelha.length; i++) {
			if (jogoDaVelha[i][0] == x && jogoDaVelha[i][1] == x && jogoDaVelha[i][2] == x) {
				verifica = 1;
			} else if (jogoDaVelha[i][0] == o && jogoDaVelha[i][1] == o && jogoDaVelha[i][2] == o) {
				verifica = 1;
			}
		}
		if (verifica == 1) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean logicaDiagonal() {
		char x = 'X', o = 'O';
		int verificaX = 0,verificaY = 0;
		for (int i = 0; i < jogoDaVelha.length; i++) {
			for (int j = 0; j < jogoDaVelha.length; j++) {
				if (i == j) {
					if (jogoDaVelha[i][j] == x) {
						verificaX += 1;
					} else if (jogoDaVelha[i][j] == o) {
						verificaY += 1;
					}
				}

			}
		}
		if (verificaX == 3 || verificaY == 3) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean logicaEmpate() {
		somaRodadas++;
		if(somaRodadas == 9) {
			System.out.println("Fim de jogo. Empate!");
			return true;
		}
		return false;
	}
}
