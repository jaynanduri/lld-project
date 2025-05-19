package mock;

import java.util.HashMap;
import java.util.Map;

public class Seller {
  private String name;
  private Map<String, Integer> counts;
  public Seller(String name, Map<String, Integer> counts) {
    this.name = name;
    this.counts = counts;
  }
  public String getName() {return this.name;}
  public Map<String, Integer> getInventoryMap() {return this.counts;}
  public void addProduct(Product p){
    String name = p.getName();
    int count = p.getCount();
    if (counts.containsKey(name)){
      counts.put(name, counts.get(name) + count);
    }
    else{
      counts.put(name,count);
    }
  }
  public int getCount(Product p){
    try{
        return counts.get(p.getName());
    } catch (Exception e){
      throw new IllegalArgumentException("Inventory Empty");
    }
  
  }

  public Map<String, Integer> updateProdCount(Map<String, Integer> proMap){
    Map<String, Integer> res = new HashMap<>();
    for(String s: proMap.keySet()){
      int v = counts.getOrDefault(s, 0);
      res.put(s, Math.min(v,  proMap.get(s)));
      int val = Math.max(0, v - proMap.get(s));
      counts.put(s, val);
    }
    return res;
  }

}
