package Fase2;

import java.util.Calendar;
import java.util.List;

/**
 * Representa objectos de regioes que podem ser alvo de simulacao de fogos
 * 
 * @author Grupo 14 | Claudia Palmeiro 50429 | Joao Farrajota 47141 | Pedro Luis
 *         53469
 * 
 * @date Novembro 2020
 */
public class Regiao {

	private String nome;
	private Calendar ultFogo;
	private int largura;
	private int altura;
	private List<Par<Integer, Integer>> casas;
	private List<Par<Integer, Integer>> estradas;
	private List<Par<Integer, Integer>> agua;

	/**
	 * Inicializa os atributos do novo objeto
	 * 
	 * @param nome     O nome da nova regiao
	 * @param ultFogo  A data do ultimo fogo
	 * @param largura  A dimensao do ambiente
	 * @param altura   A dimensao do ambiente
	 * @param casas    As casas que se encontram num determinada posicao da lista
	 * @param estradas As estrads que se encontram num determinada posicao da lista
	 * @param agua     A agua que se encontram num determinada posicao da lista
	 * @requires largura > 0 && altura > 00 &&
	 */
	public Regiao(String nome, Calendar ultFogo, int largura, int altura, List<Par<Integer, Integer>> casas,
			List<Par<Integer, Integer>> estradas, List<Par<Integer, Integer>> agua) {

		this.nome = nome;
		this.ultFogo = ultFogo;
		this.largura = largura;
		this.altura = altura;
		this.casas = casas;
		this.estradas = estradas;
		this.agua = agua;
	}

	/**
	 * O nome desta regiao
	 * 
	 * @return o nome desta regiao em String
	 */
	public String nome() {

		return this.nome;
	}

	/**
	 * O número de elementos passíveis de arder nesta regiao, seja casas ou
	 * terrenos que ainda não foram ardidos
	 * 
	 * @return um inteiro que representa o numero possvel de elementos ardiveis
	 */
	public int ardiveis() {
		// o que é ardivel = casas e terrenos

		int areaTerreno = this.altura * this.largura;
		int ardiveis = areaTerreno - estradas.size() - agua.size();

		// falta completar o metodo com os terrenos que já estão queimados!!!

		return ardiveis;
	}

	/**
	 * Regista um novo fogo para esta regiao que acontenceu na Data data, onde
	 * arderam os elementos do ambiente desta regiao que se encontram nas posicoes
	 * dadas pela lista sitios
	 * 
	 * @param data   A data do novo fogo
	 * @param sitios A lista onde esta registada as posicoes do ambiente que foram
	 *               ardidos
	 */
	public void registaFogo(Calendar data, List<Par<Integer, Integer>> sitios) {

	}

	/**
	 * Permite validar se os dados recebidos são validos para uma regiao
	 * 
	 * @param largura  A dimensao do ambiente
	 * @param altura   A dimensao do ambiente
	 * @param casas    Se encontram num determinada posicao da lista
	 * @param estradas Se encontram num determinada posicao da lista
	 * @param agua     Se encontram num determinada posicao da lista
	 * @return devolve true se os dados recebido nos parametros constituem uma
	 *         regiao valida
	 */
	public static boolean dadosValidos(int largura, int altura, List<Par<Integer, Integer>> casas,
			List<Par<Integer, Integer>> estradas, List<Par<Integer, Integer>> agua) {

		boolean flag = false;

		// Confirmar a lista da posicao das casas
		for (Par c : casas) {

			if ((int) c.primeiro() < largura && (int) c.segundo() < altura) {

				flag = true;

			} else {

				flag = false;

				return false;
			}
		}

		// Confirmar a lista da posicao das estradas
		for (Par e : estradas) {

			if ((int) e.primeiro() < largura && (int) e.segundo() < altura) {

				flag = true;

			} else {

				flag = false;

				return false;
			}
		}

		// Confirmar a lista da posicao da agua
		for (Par a : agua) {

			if ((int) a.primeiro() < largura && (int) a.segundo() < altura) {

				flag = true;

			} else {

				flag = false;

				return false;
			}
		}

		return true;
	}

	/**
	 * Representa uma matriz Terrenos e casas nao ardidos = EstadoSimulacao.LIVRE
	 * Agua, estradas e elementos já ardidos = EstadoSimulacao.OBSTACULO
	 * 
	 * @return uma matriz que corresponde ao ambiente desta regiao
	 */
	public EstadoSimulacao[][] alvoSimulacao() {

	}

	/**
	 * Calcula o nivel de perigo desta regiao
	 * 
	 * @param data         A data do ultimo fogo
	 * @param tempoLimites
	 * @return O nivel de perigo desta Regiao atraves de alguns calculos (será o
	 *         elemento do enumerado)
	 */
	public NivelPerigo nivelPerigo(Calendar data, int[] tempoLimites) {

		// ano atual
		// 2020
		int anoAtual = data.getInstance().get(Calendar.YEAR);
		// 2015
		// utilizar o built Calendar
		int ultAnoFogo = this.ultFogo.get(Calendar.YEAR);

		// 5
		int difAnoFogo = anoAtual - ultAnoFogo;

		// int [] riscos = Instituicao.RISCO_ANOS;

		// RISCO_ANOS = {2, 3, 5, 8}
		// se anoAtual 2020 e ultAnoFogo 2015 entao difAnoFogo = 5
		int posRisco = 0;

		for (int i = 0; i < Instituicao.RISCO_ANOS.length; i++) {
			if (Instituicao.RISCO_ANOS[i] == difAnoFogo) {
				posRisco = i; //
			}

		}
		// to be completed. Confirmar como vamos definir os intervalos de risco

		// formula (ardiveis - obstaculos) // todos os elemts (total de ardiveis) chamar
		// func ardiveis

		// System.out.println();

		// System.out.println(this.nome);

		// System.out.println("altura: " + this.altura);
		// System.out.println("largura: " + this.largura);

		int areaTerreno = this.altura * this.largura;
		// System.out.println("areaTerreno: " + areaTerreno);

		int ardiveis = areaTerreno - estradas.size() - agua.size();
		// System.out.println("ardiveis: "+ardiveis);

		int obstaculos = estradas.size() + agua.size();
		// System.out.println("obstaculos: "+obstaculos);

		double racio = ((double) ardiveis - (double) obstaculos) / (double) areaTerreno;
		// System.out.println("racio: "+racio);

		double nivelPerigo = posRisco * (1 + racio);
		// System.out.println("nivelPerigo: "+nivelPerigo);

		int arredondado = (int) Math.round(nivelPerigo);
		// System.out.println("arredondado: "+arredondado);

		NivelPerigo nivelPerigoFinal = NivelPerigo.values()[arredondado];

		return nivelPerigoFinal;

	}

	/*
	 * Representacao textual desta regiao
	 */
	public String toString() {

		// this.ultFogo.get(Calendar.YEAR)
		String texto = "Nome: " + this.nome + "  Data ult.fogo: " + this.ultFogo.get(Calendar.YEAR) + "/"
				+ (this.ultFogo.get(Calendar.MONTH) + 1) + "/" + this.ultFogo.get(Calendar.DAY_OF_MONTH);
		char[][] array2D = new char[altura][largura];
		transf(array2D, this.casas, this.estradas, this.agua);
		System.out.println("1� array");
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {

				System.out.print(array2D[i][j]);

			}
			System.out.println();
		}

		// System.out.print(array2D);

		return texto;

	}

	private static char[][] transf(char[][] array2D, List<Par<Integer, Integer>> casas,
			List<Par<Integer, Integer>> estradas, List<Par<Integer, Integer>> agua) {

		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				Par<Integer, Integer> p = new Par<Integer, Integer>(i, j);
				for (int k = 0; k < casas.size(); k++) {

					System.out.println(p);

					if (casas.contains(p)) {
						System.out.println("entrou");
						array2D[i][j] = 'c';
						System.out.print(array2D[i][j]);
					}
				}

				for (int k = 0; k < estradas.size(); k++) {

					if (estradas.contains(p)) {

						array2D[i][j] = 'e';
						System.out.print(array2D[i][j]);
					}
				}

				for (int k = 0; k < agua.size(); k++) {

					if (agua.contains(p)) {

						array2D[i][j] = 'a';
						System.out.print(array2D[i][j]);
					}
				}

			}
			System.out.println();
		}

		return array2D;
	}

}
