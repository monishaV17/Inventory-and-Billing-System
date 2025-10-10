package model;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private static class PurchasedItem {
        InventoryItem item;
        int quantity;

        PurchasedItem(InventoryItem item,int quantity) {
            this.item=item;
            this.quantity=quantity;
        }
    }

    private List<PurchasedItem> purchasedItems;

    public Bill() {
        purchasedItems=new ArrayList<>();
    }

    public void addItem(InventoryItem item,int quantity) {
        for(PurchasedItem pItem:purchasedItems) {
            if(pItem.item.getId().equals(item.getId())) {
                pItem.quantity+=quantity;
                return;
            }
        }
        purchasedItems.add(new PurchasedItem(item, quantity));
    }

    public double calculateTotal() {
        double total=0.0;
        for(PurchasedItem pItem:purchasedItems) {
            total+=pItem.item.getPrice() * pItem.quantity;
        }
        return total;
    }

    public String generateBill() {
        StringBuilder sb=new StringBuilder();
        sb.append("Bill Summary:\n");
        for(PurchasedItem pItem:purchasedItems) {
            sb.append(pItem.item.getName())
                    .append(" x ")
                    .append(pItem.quantity)
                    .append(" = ")
                    .append(pItem.item.getPrice() * pItem.quantity)
                    .append("\n");
        }
        sb.append("Total: ").append(calculateTotal()).append("\n");
        return sb.toString();
    }
}
