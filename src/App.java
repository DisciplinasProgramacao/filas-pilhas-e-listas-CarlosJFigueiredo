/**
 * Classe de aplicação para testar a estrutura de dados Lista
 * Tarefa 1: Testes preliminares da Lista flexível
 */
public class App {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("TAREFA 1: Testes Preliminares - Lista Flexível");
        System.out.println("========================================\n");
        
        // Teste 1: Inserir dígitos de matrícula sem repetição
        testeMatricula();
        
        System.out.println("\n========================================");
        System.out.println("TESTES ADICIONAIS: Inserir e Remover");
        System.out.println("========================================\n");
        
        // Teste 2: Testar inserções em diferentes posições
        testeInsercoes();
        
        // Teste 3: Testar remoções em diferentes posições
        testeRemocoes();
        
        // Teste 4: Testar casos de exceção
        testeExcecoes();
        
        System.out.println("\n========================================");
        System.out.println("TODOS OS TESTES CONCLUÍDOS!");
        System.out.println("========================================");
    }
    
    /**
     * Teste 1: Insere dígitos de uma matrícula na lista sem repetição
     * Usando uma matrícula exemplo: 2024123456
     */
    private static void testeMatricula() {
        System.out.println("Teste 1: Inserindo dígitos da matrícula");
        System.out.println("Matrícula exemplo: 2024123456");
        
        Lista<Integer> listaMatricula = new Lista<>();
        
        // Array com os dígitos da matrícula
        int[] digitos = {2, 0, 2, 4, 1, 2, 3, 4, 5, 6};
        
        System.out.println("\nInserindo dígitos sem repetição:");
        for (int digito : digitos) {
            // Verifica se o dígito já existe na lista
            if (!listaMatricula.contem(digito)) {
                listaMatricula.inserir(digito, listaMatricula.getTamanho());
                System.out.println("  - Dígito " + digito + " inserido");
            } else {
                System.out.println("  - Dígito " + digito + " já existe (ignorado)");
            }
        }
        
        System.out.println("\nConteúdo final da lista:");
        listaMatricula.imprimir();
        System.out.println("Tamanho da lista: " + listaMatricula.getTamanho());
    }
    
    /**
     * Teste 2: Testa inserções em diferentes posições
     */
    private static void testeInsercoes() {
        System.out.println("Teste 2: Inserções em diferentes posições");
        
        Lista<String> lista = new Lista<>();
        
        // Inserir no início (posição 0)
        lista.inserir("A", 0);
        System.out.print("Após inserir 'A' no início: ");
        lista.imprimir();
        
        // Inserir no final
        lista.inserir("C", lista.getTamanho());
        System.out.print("Após inserir 'C' no final: ");
        lista.imprimir();
        
        // Inserir no meio (posição 1)
        lista.inserir("B", 1);
        System.out.print("Após inserir 'B' na posição 1: ");
        lista.imprimir();
        
        // Inserir mais elementos
        lista.inserir("D", lista.getTamanho());
        lista.inserir("E", lista.getTamanho());
        System.out.print("Após inserir 'D' e 'E' no final: ");
        lista.imprimir();
    }
    
    /**
     * Teste 3: Testa remoções em diferentes posições
     */
    private static void testeRemocoes() {
        System.out.println("\nTeste 3: Remoções em diferentes posições");
        
        Lista<Integer> lista = new Lista<>();
        
        // Criar lista inicial: [10, 20, 30, 40, 50]
        for (int i = 1; i <= 5; i++) {
            lista.inserir(i * 10, lista.getTamanho());
        }
        
        System.out.print("Lista inicial: ");
        lista.imprimir();
        
        // Remover do meio (posição 2)
        int removido = lista.remover(2);
        System.out.println("Removido da posição 2: " + removido);
        System.out.print("Lista após remoção: ");
        lista.imprimir();
        
        // Remover do início (posição 0)
        removido = lista.remover(0);
        System.out.println("Removido da posição 0: " + removido);
        System.out.print("Lista após remoção: ");
        lista.imprimir();
        
        // Remover do final
        removido = lista.remover(lista.getTamanho() - 1);
        System.out.println("Removido do final: " + removido);
        System.out.print("Lista após remoção: ");
        lista.imprimir();
    }
    
    /**
     * Teste 4: Testa casos de exceção
     */
    private static void testeExcecoes() {
        System.out.println("\nTeste 4: Testando casos de exceção");
        
        Lista<Integer> lista = new Lista<>();
        
        // Teste 1: Tentar remover de lista vazia
        System.out.println("\nTentando remover de lista vazia:");
        try {
            lista.remover(0);
        } catch (IllegalStateException e) {
            System.out.println("✓ Exceção capturada: " + e.getMessage());
        }
        
        // Adicionar alguns elementos
        lista.inserir(100, 0);
        lista.inserir(200, 1);
        lista.inserir(300, 2);
        
        // Teste 2: Tentar inserir em posição inválida
        System.out.println("\nTentando inserir em posição inválida (10):");
        try {
            lista.inserir(400, 10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Exceção capturada: " + e.getMessage());
        }
        
        // Teste 3: Tentar remover de posição inválida
        System.out.println("\nTentando remover de posição inválida (-1):");
        try {
            lista.remover(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Exceção capturada: " + e.getMessage());
        }
        
        System.out.print("\nLista final: ");
        lista.imprimir();
    }
}
