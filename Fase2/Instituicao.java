package Fase2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Representa objectos de instituicoes que colecionam e estudam informacoes sobre fogo
 * @author Grupo 14 | Claudia Palmeiro 50429 | Joao Farrajota 47141 | Pedro Luis 53469
 * @date Novembro 2020
 */
public class Instituicao {
	
	private String designacao;
	
	// define a relacao entre o n. de anos que passaram desde o ultimo fogo e o nivel de perigo
	public static final int[] RISCO_ANOS = {2, 3, 5, 8}; 
	
	// define a relacao entre a forca do vento e n. de elementos vizinhos no ambiente de uma regiao que sao afetados
	public static final int[] VENTOS_LIMITES = {0, 1, 3, 5, 7, 9 , 11, 13, 15, 17, 19, 21}; 
	
	//adicionado por nos
	private ArrayList<Regiao>listaRegioes = new ArrayList<Regiao>(); 
	
	
   /**
	* Inicializa os atributos do novo objeto
	* @param designacao Inicializa uma instituicao 
	*/
	public Instituicao(String designacao) {
		
		this.designacao = designacao;	
	}
	
	
   /**
	* Adiciona uma nova regiao assumindo que ainda nÃ£o existe uma regiao com este nome nesta instituicao 
	* e que os valores dos parametros sao validos para definir
	* @param nome O nome da nova regiao
	* @param ultFogo A data do ultimo fogo 
	* @param largura A dimensao do ambiente
	* @param altura A dimensao do ambiente
	* @param casas As casas que se encontram num determinada posicao da lista
	* @param estradas As estradas que se encontram num determinada posicao da lista
	* @param agua A agua que se encontram num determinada posicao da lista
	*/
	public void adicionaRegiao(String nome, Calendar ultFogo, int largura, int altura, List <Par<Integer,Integer>> casas, List<Par<Integer,Integer>> estradas, List<Par<Integer,Integer>> agua) {
					
		Regiao regiao = new Regiao(nome, ultFogo, largura, altura, casas, estradas, agua);
				
		listaRegioes.add(regiao);
		
//		for (Regiao i: listaRegioes) {
//			
//			System.out.println(i);
//		}
	}
	
	
   /**
	* Verifica se existe uma regiao com um determinado nome nesta instituicao
	* @param nome O nome da regiao
	* @return devolve true se existe uma regiao com este nome nesta instituicao
	*/
	public boolean existeRegiao(String nome) {
		
		for (Regiao elem: listaRegioes) {
			
			if (elem.nome().equals(nome)) {
				
				//System.out.println("passou aqui");
				return true;
			}			
		}
		
		return false;		
	}
	
	
   /**
	* @return Os nomes e os niveis de perigo das regioes desta instituicao.
	*/
	public List <Par<String, NivelPerigo>> niveisDePerigo() {
		
		List <Par<String, NivelPerigo>> lista = new ArrayList<>() ;
		
		Calendar data  = Calendar.getInstance();
		
		

		for (Regiao elem: listaRegioes) {
			
			Par<String,NivelPerigo> p = new Par<String,NivelPerigo>(
					 elem.nome(),elem.nivelPerigo(data, RISCO_ANOS)
					);
		
			lista.add(p);
			
		}
		return lista;
		
	}
	
	
   /**
    * + perigoso é o 3 
    * 
	* @return a matriz correspondente alvo de simulacao da regiao de maior nivel de perigo nesta instituicao
	*/
	public EstadoSimulacao[][] alvoSimulacao() {
		
		List <Par<String, NivelPerigo>> lista = niveisDePerigo();
		int indice;
		int indiceMaior = 0;
		Par<String, NivelPerigo> parRegiaoMaiorPerigo = null;
		
		
		
		//for para verificar qual é a regiao desta instituicao com o perigo + alto

		for (int j = 0; j < lista.size(); j++) {
			
			Par<String, NivelPerigo> parAtual = lista.get(j);
			System.out.println(parAtual);
			
			indice = NivelPerigo.valueOf(parAtual.segundo().name()).ordinal();
			System.out.println(indice);
			
			if (j >= 0 && indice >= indiceMaior) {
				indiceMaior = indice;
				parRegiaoMaiorPerigo = parAtual;
			}
		}	
		
		System.out.println("Par região com maior perigo: " + parRegiaoMaiorPerigo);	
		
			
		
		
		//falta fazer a criação do array 2D da tal região selecionada no for anterior. Só cria, o display é feito na classe Simulador.
		
		
		
		EstadoSimulacao[][] ambiente = null  ;
		return  ambiente ;
	}
	
	
	/**
	 * @return Devolve true se existe pelo menos uma regiao nesta instituicao que tenha elementos ardiveis
	 */
	 public boolean podeAtuar() {
		 
		 for (Regiao elem: listaRegioes) {
			 
			 if(elem.ardiveis() > 0) {
				 
				 return true;
			 
			 }
			 
		}
		 
		return false;
	  
	  }
	 
	
   /**
	* Regista na regiao um fogo que aconteceu na data. Apos este registo pode acontecer mudar a 
	* regiao com maior nivel perigo 
	* @param regiao O nome de uma regiao
	* @param data Data de um fogo acontecido
	* @param sitios 
	*/
	public void registaFogo(String regiao, Calendar data, List <Par<Integer,Integer>> sitios) {
		
	}
	
	
   /**
	* @return Representacao textual desta instituicao
	*/
	public String toString() {
		
		for (Regiao elem: listaRegioes) {
			
			
			
			System.out.println(elem.toString());
			 
		 }
		
		String designa = "*****************\nDesignação: " + this.designacao;
		
		return designa;
		
	}
	
	
	
}
