package mock;

import java.util.Map;

public class InventoryManager {
  private Seller seller;
  private Product product;
  public InventoryManager(Seller s, Product p) {
    this.seller = s;
    this.product = p;
  }

  public void addInventory(){
    seller.addProduct(this.product);
  }
  public int getInventory(){
    return seller.getCount(this.product);
  }
  public Order creatOrder(Map<String, Integer> prodMap){
    Map<String, Integer> orderItems = seller.updateProdCount(prodMap);
    return new Order(seller, orderItems);
  }

}
