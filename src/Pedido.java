import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {

	/** Lista para armazenar os produtos do pedido */
	private Lista<Produto> produtos;
	
	public Lista<Produto> getProdutos() {
		return produtos;
	}

	/** Armazena a data de criação do pedido */
	private LocalDate dataPedido;

	/** Código sequencial único do pedido (atribuído ao finalizar). Valor 0 indica 'não finalizado/ainda sem código' */
	private int codigo;

	/** Contador estático para gerar códigos sequenciais */
	private static int contadorCodigo = 1;

	/** Construtor do Pedido. Deve criar a lista e armazenar a data atual do sistema na data do pedido */
	public Pedido() {
		produtos = new Lista<Produto>();
		this.dataPedido = LocalDate.now();
	}

	

    /**
     * Inclui um produto neste pedido e aumenta a quantidade de produtos armazenados.
     * @param novo O produto a ser incluído no pedido
     * @return A quantidade de produtos no pedido após a inclusão
     */
	public int incluirProduto(Produto novo) {
		if(novo != null) {
			produtos.inserir(novo, produtos.getTamanho());
		} else {
			System.out.println("Produto nulo não pode ser adicionado.");
		}
		return produtos.getTamanho();
	}

    /**
     * Calcula e retorna o valor final do pedido (soma do valor de venda de todos os produtos)
     * @return Valor a ser pago pelo pedido (double)
     */
	public double valorFinal() {
		double total = 0d;
		for (int i = 0; i < produtos.getTamanho(); i++) {
			total += produtos.obter(i).valorDeVenda();
		}
		return total;
	}

    /**
     * Representação em String do produto. Contém um cabeçalho com sua data e depois, em cada linha,
     * a descrição de cada produto. Ao final, mostra o valor a pagar pelo pedido.
     * @return Uma string contendo dados de um pedido conforme especificado (cabeçalho, detalhes, valor a pagar)
     */
	public String toString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder retorno = new StringBuilder();
		if (codigo > 0) {
			retorno.append("Pedido #" + codigo + " na data " + formato.format(dataPedido) + "\n");
		} else {
			retorno.append("Pedido na data " + formato.format(dataPedido) + "\n");
		}
		for (int i = 0; i < produtos.getTamanho(); i++) {
			retorno.append(produtos.obter(i).toString());
			retorno.append("\n");
		}
		retorno.append(String.format("Valor a pagar: R$ %.2f", valorFinal()));
		return retorno.toString();
				
	}

	/**
	 * Deve retornar uma descrição resumida do Pedido em uma única linha. O formato deve ser
	 * "Pedido com XX produtos em DD/MM/AAAA, valor total de R$ XX,XX"
	 * @return Uma string como especificada acima
	 */
	public String resumo() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String valorFinalFormatado = String.format("%.2f", this.valorFinal());
		if (codigo > 0) {
			return "Pedido #" + codigo + " com " + produtos.getTamanho() + " produtos em " + formato.format(dataPedido) + ", valor total de R$ " + valorFinalFormatado;
		}
		return "Pedido com " + produtos.getTamanho() + " produtos em " + formato.format(dataPedido) + ", valor total de R$ " + valorFinalFormatado;

	}

	/**
	 * Atribui um código sequencial ao pedido. Deve ser chamado apenas quando o pedido for finalizado.
	 */
	public void atribuirCodigoSequencial() {
		if (this.codigo == 0) {
			this.codigo = contadorCodigo++;
		}
	}

	public int getCodigo() {
		return this.codigo;
	}

}
