package model;

public class ItemOrder {
    private Integer id;
    private Product product;
    private Order order;
    private Integer quantity;
    private Double sum;

    public ItemOrder(Integer id, Product product, Order order, Integer quantity, Double sum) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
