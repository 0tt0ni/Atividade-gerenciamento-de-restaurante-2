
import java.util.ArrayList;
import java.util.List;

class Cliente {
    private String nome;
    private String contato;

    public Cliente(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", Contato: " + contato;
    }
}

class Garcom {
    private String nome;

    public Garcom(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Garçom: " + nome;
    }
}

class ItemMenu {
    private String nome;
    private String descricao;
    private double preco;

    public ItemMenu(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " - " + descricao + " (R$ " + preco + ")";
    }
}

class Pedido {
    private Cliente cliente;
    private Garcom garcom;
    private List<ItemMenu> itens;
    private List<Integer> quantidades;

    public Pedido(Cliente cliente, Garcom garcom) {
        this.cliente = cliente;
        this.garcom = garcom;
        this.itens = new ArrayList<>();
        this.quantidades = new ArrayList<>();
    }

    public void adicionarItem(ItemMenu item, int quantidade) {
        itens.add(item);
        quantidades.add(quantidade);
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < itens.size(); i++) {
            total += itens.get(i).getPreco() * quantidades.get(i);
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder detalhesPedido = new StringBuilder();
        detalhesPedido.append(cliente.toString()).append("\n");
        detalhesPedido.append(garcom.toString()).append("\n");
        detalhesPedido.append("Itens do Pedido:\n");
        for (int i = 0; i < itens.size(); i++) {
            detalhesPedido.append(itens.get(i).getNome())
                          .append(" (x")
                          .append(quantidades.get(i))
                          .append(") - R$ ")
                          .append(itens.get(i).getPreco() * quantidades.get(i))
                          .append("\n");
        }
        detalhesPedido.append("Total: R$ ").append(calcularTotal());
        return detalhesPedido.toString();
    }
}

public class SistemaDeGerenciamento {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Garcom> garcons = new ArrayList<>();
    private List<ItemMenu> menu = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    public void cadastrarCliente(String nome, String contato) {
        clientes.add(new Cliente(nome, contato));
    }

    public void cadastrarGarcom(String nome) {
        garcons.add(new Garcom(nome));
    }

    public void cadastrarItemMenu(String nome, String descricao, double preco) {
        menu.add(new ItemMenu(nome, descricao, preco));
    }

    public Cliente selecionarCliente(int indice) {
        return clientes.get(indice);
    }

    public Garcom selecionarGarcom(int indice) {
        return garcons.get(indice);
    }

    public ItemMenu selecionarItemMenu(int indice) {
        return menu.get(indice);
    }

    public void criarPedido(Cliente cliente, Garcom garcom) {
        Pedido pedido = new Pedido(cliente, garcom);
        pedidos.add(pedido);
    }

    public Pedido selecionarPedido(int indice) {
        return pedidos.get(indice);
    }

    public void visualizarPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.toString());
            System.out.println("-------------------------------");
        }
    }

    public static void main(String[] args) {
        SistemaDeGerenciamento sistema = new SistemaDeGerenciamento();

        sistema.cadastrarCliente("João Silva", "1234-5678");
        sistema.cadastrarCliente("Maria Oliveira", "8765-4321");

        sistema.cadastrarGarcom("Carlos");
        sistema.cadastrarGarcom("Ana");

        sistema.cadastrarItemMenu("Pizza", "Pizza de Calabresa", 40.00);
        sistema.cadastrarItemMenu("Refrigerante", "Coca-Cola 2L", 8.00);

        Cliente cliente1 = sistema.selecionarCliente(0);
        Garcom garcom1 = sistema.selecionarGarcom(0);
        Pedido pedido1 = new Pedido(cliente1, garcom1);
        pedido1.adicionarItem(sistema.selecionarItemMenu(0), 2);
        pedido1.adicionarItem(sistema.selecionarItemMenu(1), 1);
        sistema.pedidos.add(pedido1);

        sistema.visualizarPedidos();
    }
}
