
public class Abp {
	public class No {
		private Item dados;
		private No fd, fe, pai;

		public No(Item aux) {
			dados = aux;
			fd = fe = pai = null;
		}
	}

	private No raiz;
	private int tamanho;

	public Abp() {
		raiz = null; // inicializa a raiz com null
	}

	public int getTamanho() {
		return tamanho;
	}

	public boolean vazia() {
		return (raiz == null); // retorna true caso raiz == null
	}

	private No consultar(Item obj) {
		No aux = raiz;
		while (aux != null) {
			if (obj.getNome().compareToIgnoreCase(aux.dados.getNome()) < 0)
				aux = aux.fe;
			else if (obj.getNome().compareToIgnoreCase(aux.dados.getNome()) > 0)
				aux = aux.fd;
			else
				return aux; // Sucesso(encontrou)
		}
		return null; // Insucesso --> null
	}

	public Item pesquisar(Item obj) {
		No aux = consultar(obj);
		if (aux == null) {
			return null; // Insucesso à não encontrou
		}
		return (new Item(aux.dados.getNome(), aux.dados.getFone())); // Ssucesso --> encontrou
	}

	public boolean inserir(Item obj) {
		tamanho++;
		// cria-se um novo nó
		No aux = new No(obj);
		// verifica-se a árvore está vazia e caso afirmativo faz do nó aux a raiz da
		// árvore
		if (vazia()) {
			raiz = aux;
			return true;
		}
		// encontra o local de inserção que é sempre numa folha
		No ptr = raiz;
		No ant = raiz;
		while (ptr != null) {
			if (obj.getNome().compareToIgnoreCase(ptr.dados.getNome()) < 0) {
				ant = ptr;
				ptr = ptr.fe;
			} else if (obj.getNome().compareToIgnoreCase(ptr.dados.getNome()) > 0) {
				ant = ptr;
				ptr = ptr.fd;
			} else
				return false; // Insucesso --> item já está na árvore
		}

		// faz o nó referenciado por ant o pai do nó aux
		aux.pai = ant;
		// verifica-se é filho a esquerda ou a direita
		if (obj.getNome().compareTo(ant.dados.getNome()) < 0) {
			ant.fe = aux;
		} else {
			ant.fd = aux;
		}

		return true; // Sucesso
	}

	private No maximo(No obj) {
		if (obj == null)
			return null;
		// é necessário usar outra referencia
		// para não alterar a referencia passada no paramentro
		No atual = obj;
		// laço para encontrar o máximo
		while (atual.fd != null) {
			atual = atual.fd;
		}
		return atual; // maior valor na árvore

	}

	private No minimo(No obj) {
		if (obj == null)
			return null;
		// é necessário usar outra referencia
		// para não alterar a referencia passada no paramentro
		No atual = obj;
		while (atual.fe != null) {
			atual = atual.fe;
		}
		return atual; // maior valor na árvore
	}

	private No antecessor(No obj) {
		if (obj == null)
			return null;
		// Se tem filho a esquerda o antecessor é o máximo da sub-árvore da esquerda
		if (obj.fe != null)
			return (maximo(obj.fe));
		// Caso contrário o antecessor pode estar nos ancestrais
		// O antecessor pode ser o primeiro ancestral do qual o nó é filho a direita
		// Pode não ter antecessor
		No atual = obj.pai;
		No ant = obj;
		while (atual != null && ant == atual.fe) {
			ant = atual;
			atual = atual.pai;
		}

		// Se atual é nulo então não existe antecessor
		// Sucessor caso seja diferente de nulo
		return atual;
	}

	private No sucessor(No obj) {
		if (obj == null)
			return null;
		// Se tem filho a direito o sucessor é o mínimo da sub-árvore da direita
		if (obj.fd != null)
			return (minimo(obj.fd));
		// Caso contrário o sucessor pode estar nos ancestrais
		// O sucessor pode ser o primeiro ancestral do qual o nó é filho a esquerda
		// Pode não ter sucessor
		No atual = obj.pai;
		No ant = obj;
		while (atual != null && ant == atual.fd) {
			ant = atual;
			atual = atual.pai;
		}
		// Se atual é nulo então não existe sucessor
		// Sucessor caso seja diferente de nulo
		return atual;
	}

	public Item retirar(Item obj) {
		Item aux = null;
		No z = consultar(obj);
		if (z != null) {
			aux = new Item(z.dados.getNome(), z.dados.getFone());
			No y = null;
			No x = null;

			// o no z tem 1 filho só ou nenhum filho
			if (z.fd == null || z.fe == null) {
				y = z;
			}

			// o no z tem dois filhos
			else {
				y = sucessor(z);
			}
			if (y.fe != null) {
				x = y.fe;
			} else {
				x = y.fd;
			}

			// pois y tem um filho
			if (x != null) {
				x.pai = y.pai;
			}

			// y é a raiz
			if (y.pai == null) {
				raiz = x;

				// pois y tem um filho
				if (x != null) {
					x.pai = null;
				}

				// y não é raiz
			} else {
				if (y == y.pai.fe) {
					y.pai.fe = x;
				} else {
					y.pai.fd = x;
				}
			}

			// y é o sucessor de z --> copia dados de y para z
			if (y != z) {
				z.dados = y.dados;
			}
			tamanho--;
		}
		return aux;
	}

	public void visitaEmOrdem(StringBuffer aux) {
		// se a árvore estiver vazia não faz as chamadas recursivas
		if (vazia()) {
			aux.append(" Árvore vazia!");
		}
		// chamar método recursivo
		else {
			visitaEmOrdem(aux, raiz);
		}
	}

	private void visitaEmOrdem(StringBuffer aux, No obj) {
		if (obj != null) {
			visitaEmOrdem(aux, obj.fe);
			aux.append(obj.dados.toString());

			visitaEmOrdem(aux, obj.fd);
		}
		System.out.println("\n");
		System.out.println(aux);
	}

	public void visitaEmPreOrdem(StringBuffer aux) {
		// se a árvore estiver vazia não faz as chamadas recursivas
		if (vazia()) {
			aux.append("Ärvore vazia!");
		}
		// chamar método recursivo
		else {
			visitaEmPreOrdem(aux, raiz);
		}

	}

	private void visitaEmPreOrdem(StringBuffer aux, No obj) {
		if (obj != null) {
			aux.append(obj.dados.toString());
			visitaEmPreOrdem(aux, obj.fe);
			visitaEmPreOrdem(aux, obj.fd);
		}
		System.out.println("\n");
		System.out.println(aux);
	}

	public void visitaEmPosOrdem(StringBuffer aux) {
		// se a árvore estiver vazia não faz as chamadas recursivas
		if (vazia()) {
			aux.append(" Árvore vazia!");
		}
		// chamar método recursivo
		else {
			visitaEmPosOrdem(aux, raiz);
		}
	}

	private void visitaEmPosOrdem(StringBuffer aux, No obj) {
		if (obj != null) {
			visitaEmPosOrdem(aux, obj.fe);
			visitaEmPosOrdem(aux, obj.fd);
			aux.append(obj.dados.toString());
		}
		System.out.println("\n");
		System.out.println(aux);
	}

	public void testaIntegridade(StringBuffer aux) {
		// se a árvore estiver vazia não faz as chamadas recursivas
		if (vazia()) {
			aux.append(" - Árvore vazia!\n");
		}
		// chamar método recursivo
		else {
			testaIntegridade(aux, raiz);
			aux.append("- Árvore é provavelmente Integra!\n");
		}

	}

	private void testaIntegridade(StringBuffer aux, No obj) {
		if (obj == null) {
			return;
		}
		if (obj.fe != null) {
			if (obj.dados.getNome().compareToIgnoreCase(obj.fe.dados.getNome()) < 0) {
				aux.append(" Não é uma Árvore Binária. Pai menor que filho da esquerda.\n");
				aux.append(" Pai --> " + obj.dados.getNome() + "\n");
				aux.append(" Filho da esquerda --> " + obj.fe.dados.getNome() + "\n");
			}
		}
		if (obj.fd != null) {
			if (obj.dados.getNome().compareToIgnoreCase(obj.fd.dados.getNome()) > 0) {
				aux.append(" Não é uma Árvore Binária. Pai maior que filho da direita.\n");
				aux.append(" Pai --> " + obj.dados.getNome() + "\n");
				aux.append(" Filho da direita --> " + obj.fd.dados.getNome() + "\n");
			}
		}
		testaIntegridade(aux, obj.fe);
		testaIntegridade(aux, obj.fd);
		System.out.println("\n");
		System.out.println(aux);
	}

	// 2.Escreva um método em Java para determinar o número de nós em uma árvore
	// binária de pesquisa.
	static int getNos(No raiz) {
		if (raiz == null)
			return 0;

		int res = 0;
		if (raiz.fe != null && raiz.fd != null)
			res++;

		res += (getNos(raiz.fe) + getNos(raiz.fd));
		return res;
	}

	public int getNos() {
		return getNos(raiz);
	}

	// 3.Escreva um método que conta o número de folhas de uma Árvores binária de pesquisa (ABP).
	
	int getFolhas() {
		return getFolhas(raiz);
	}

	int getFolhas(No no) {
		if (no == null)
			return 0;
		if (no.fe == null && no.fd == null)
			return 1;
		else
			return getFolhas(no.fe) + getFolhas(no.fd);
	}

	

	public void info(Abp arvoreA, Abp arvoreB) {

		if (arvoreA.vazia() && arvoreB.vazia())
			System.out.println(" As Árvores A e B são iguais! Ambas estão vazias!");
		if (arvoreA.raiz != null && arvoreB.raiz != null) {
			
			if (arvoreA.getNos() != arvoreB.getNos())
				System.out.println(" As Árvores A e B podem ser diferentes, não possuem a mesma quantidade de Nós \n");
			else
				System.out.println(" As Árvores A e B podem ser iguais, possuem a mesma quantidade de Nós \n");

			if (arvoreA.getFolhas() != arvoreB.getFolhas())
				System.out.println(" As Árvores A e B podem ser diferentes, não possuem a mesma quantidade de Folhas \n");
			else
				System.out.println(" As Árvores A e B podem ser iguais, possuem a mesma quantidade de Folhas \n");

			if (arvoreA.raiz.dados.getNome().equalsIgnoreCase(arvoreB.raiz.dados.getNome()))
				System.out.println(" As Árvores A e B possuem Raizes iguais \n");
			else
				System.out.println(" As Árvores A e B têm raizes diferentes, por isso são diferentes entre si \n");
			
			if (saoEstrBinarias(arvoreA.raiz) == true)
				System.out.println(" Arvore A é Estritamente Binária \n");
			else
				System.out.println(" Arvore A não é Estritamente Binária \n");

			if (saoEstrBinarias(arvoreB.raiz) == true)
				System.out.println(" Arvore B é Estritamente Binária \n");
			else
				System.out.println(" Arvore B não é Estritamente Binária \n");
			
			if (saoIguais(arvoreA.raiz, arvoreB.raiz))
				System.out.println(" As Árvores A e B são iguais \n");
			else
				System.out.println(" As Árvores A e B são diferentes \n");

		}
		else System.out.println(  "\n   ----------------------------------------"
								+ "\n  | Uma das Árvores(ou ambas) estão vazias |"
								+ "\n  | preencha antes de usar essa opção para |"
								+ "\n  |       ver os outros resultados         |"
								+ "\n   ----------------------------------------");
		

	}
	
	// 4. Duas Árvores binárias de pesquisa(ABP) são IGUAIS (ou similares) se são
	// ambas vazias ou então se armazenam valores iguais  em  suas  raízes,  suas 
	// sub-árvores esquerdas são iguais, e suas sub-árvores direitas  são  iguais. 
	// Implemente um método que verifica se duas a árvores são iguais (ou similares).
	
	private boolean saoIguais(No A, No B) {
		 // tive problema com essa função! tentei de varias formas e não funcionou
		 if(A==null && B==null) return true;
	        if(A==null || B==null) return false;
	       
	        if(A.dados.getNome()!=B.dados.getNome()) return false;
	        
	        if (saoIguais(A.fe,B.fe) && saoIguais(A.fd,B.fd)) return true;
			return false;
	}

	// 5.Uma ABP é estritamente binária se todos os nós da árvore tem 2 filhos.
	// Implemente um método que verifica se uma  ABP  é  estritamente  binária.

	boolean saoEstrBinarias(No aux) {
		
		if (aux == null)
			return true;

		if (aux.fe == null && aux.fd == null)
			return true;

		if ((aux.fe != null) && (aux.fd != null))
			return (saoEstrBinarias(aux.fe) && saoEstrBinarias(aux.fd));

		return false;
	}

	// 6.Implemente um método para testar se uma a Árvore binária é uma ABP.
	
	public void isBinary(StringBuffer aux) {
		
		if (vazia()) {
			aux.append(" - Não foi possível verificar se é uma ABP");
		}
		
		else {
			testaIntegridade(aux, raiz);
			aux.append("- Árvore Binária de Pesquisa!");
		}

	}

}
