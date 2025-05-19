package mock;
import java.util.*;

public class Order {
    private Seller seller;
    private Map<String, Integer> prodMap;
    public Order(Seller s, Map<String, Integer> prodNames){
        this.seller = s;
        this.prodMap = prodNames;
    }
}
