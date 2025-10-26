public class Lista<E> {

	private Celula<E> primeiro;
	private Celula<E> ultimo;
	private int tamanho;
	
	public Lista() {
		
		Celula<E> sentinela = new Celula<>();
		
		this.primeiro = this.ultimo = sentinela;
		this.tamanho = 0;
	}
	
	public boolean vazia() {
		
		return (this.primeiro == this.ultimo);
	}
	
	public void inserir(E novo, int posicao) {
		
		Celula<E> anterior, novaCelula, proximaCelula;
		
		if ((posicao < 0) || (posicao > this.tamanho))
			throw new IndexOutOfBoundsException("Não foi possível inserir o item na lista: "
					+ "a posição informada é inválida!");
		
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		novaCelula = new Celula<>(novo);
			
		proximaCelula = anterior.getProximo();
			
		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);
			
		if (posicao == this.tamanho)  // a inserção ocorreu na última posição da lista
			this.ultimo = novaCelula;
			
		this.tamanho++;		
	}
	
	public E remover(int posicao) {
		
		Celula<E> anterior, celulaRemovida, proximaCelula;
		
		if (vazia())
			throw new IllegalStateException("Não foi possível remover o item da lista: "
					+ "a lista está vazia!");
		
		if ((posicao < 0) || (posicao >= this.tamanho ))
			throw new IndexOutOfBoundsException("Não foi possível remover o item da lista: "
					+ "a posição informada é inválida!");
			
		anterior = this.primeiro;
		for (int i = 0; i < posicao; i++)
			anterior = anterior.getProximo();
				
		celulaRemovida = anterior.getProximo();
				
		proximaCelula = celulaRemovida.getProximo();
				
		anterior.setProximo(proximaCelula);
		celulaRemovida.setProximo(null);
				
		if (celulaRemovida == this.ultimo)
			this.ultimo = anterior;
				
		this.tamanho--;
				
		return (celulaRemovida.getItem());	
	}
	
	/**
	 * Retorna o tamanho da lista
	 * @return tamanho da lista
	 */
	public int getTamanho() {
		return this.tamanho;
	}
	
	/**
	 * Obtém o item em uma determinada posição
	 * @param posicao posição do item
	 * @return item na posição especificada
	 */
	public E obter(int posicao) {
		if ((posicao < 0) || (posicao >= this.tamanho))
			throw new IndexOutOfBoundsException("Posição inválida!");
		
		Celula<E> atual = this.primeiro.getProximo();
		for (int i = 0; i < posicao; i++)
			atual = atual.getProximo();
		
		return atual.getItem();
	}
	
	/**
	 * Verifica se a lista contém um determinado item
	 * @param item item a ser verificado
	 * @return true se o item existe na lista, false caso contrário
	 */
	public boolean contem(E item) {
		Celula<E> atual = this.primeiro.getProximo();
		
		while (atual != null) {
			if (atual.getItem().equals(item))
				return true;
			atual = atual.getProximo();
		}
		
		return false;
	}
	
	/**
	 * Imprime o conteúdo da lista
	 */
	public void imprimir() {
		if (vazia()) {
			System.out.println("Lista vazia!");
			return;
		}
		
		System.out.print("Lista: [");
		Celula<E> atual = this.primeiro.getProximo();
		
		while (atual != null) {
			System.out.print(atual.getItem());
			if (atual.getProximo() != null)
				System.out.print(", ");
			atual = atual.getProximo();
		}
		
		System.out.println("]");
	}
	
	/**
	 * Conta quantas repetições de elementos atendem a uma condição específica
	 * @param condicional Predicado que define a condição para contar elementos
	 * @return Quantidade de elementos que atendem à condição (0 se lista vazia)
	 */
	public int contarRepeticoes(java.util.function.Predicate<E> condicional) {
		if (vazia()) {
			return 0;
		}
		
		int contador = 0;
		Celula<E> atual = this.primeiro.getProximo();
		
		while (atual != null) {
			if (condicional.test(atual.getItem())) {
				contador++;
			}
			atual = atual.getProximo();
		}
		
		return contador;
	}
}
