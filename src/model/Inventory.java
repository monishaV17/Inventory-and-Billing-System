package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
public class Inventory{
    private final ArrayList<InventoryItem> items;
    public Inventory(){
        items=new ArrayList<>();
    }
    public void addItem(InventoryItem item){
        items.add(item);
    }
    public boolean updateItem(String id,int quantity,double price){
        InventoryItem item=getItem(id);
        if(item!=null){
            item.setQuantity(quantity);
            item.setPrice(price);
            return true;
        }
        return false;
    }
    public boolean removeItem(String id){
        InventoryItem item=getItem(id);
        if(item!=null){
            items.remove(item);
            return true;
        }
        return false;
    }
    public InventoryItem getItem(String id){
        for(InventoryItem item:items){
            if(item.getId().equals(id)){
                return item;
            }
        }
            return null;
        }
    public void displayItems(){
        for(InventoryItem item:items){
            System.out.println(item);
        }
    }

    public void saveToFile(String filename) {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(filename))) {
            for(InventoryItem item:items) {
                String line=item.getId() + "," + item.getName() + "," + item.getQuantity() + "," + item.getPrice();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Inventory saved to " + filename);
        } catch(IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        items.clear();
        try(BufferedReader br=new BufferedReader(new FileReader(filename))) {
            String line;
            while((line=br.readLine())!=null) {
                String[] parts=line.split(",");
                if (parts.length == 4) {
                    String id=parts[0];
                    String name=parts[1];
                    int quantity=Integer.parseInt(parts[2]);
                    double price=Double.parseDouble(parts[3]);
                    InventoryItem item=new InventoryItem(id,name,quantity,price);
                    addItem(item);
                }
            }
            System.out.println("Inventory loaded from " +filename);
        } catch(IOException e) {
            System.out.println("Error loading inventory: " +e.getMessage());
        }
    }
}