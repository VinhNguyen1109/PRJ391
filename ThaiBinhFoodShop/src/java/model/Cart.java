
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author MTDT
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(String text, List<Product> list, String CustomerID) {
        items = new ArrayList<>();

        try {
            if (text != null && text.length() != 0) {
                String[] s = text.split("/");
                for (String string : s) {
                    if (string != "" && string != null) {
                        String[] n = string.split(":");
                        String id = n[1];
                        String CusID = n[0];
                        int quantity = Integer.parseInt(n[2]);
                        if (CusID.equals(CustomerID)) {
                            Product product = getProductByID(id, list);
                            Item t = new Item(product, quantity, product.getPrice());
                            addItem(t);
                        }
                    }

                }
            }
        } catch (NumberFormatException e) {
        }

    }

    private Product getProductByID(String id, List<Product> list) {
        for (Product product : list) {
            if (product.getProductID().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuantityByID(String id) {
        return getItemById(id).getQuantity();
    }

    private Item getItemById(String id) {
        for (Item item : items) {
            if (item.getProduct().getProductID().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item) {
        if (getItemById(item.getProduct().getProductID()) != null) {
            Item m = getItemById(item.getProduct().getProductID());
            m.setQuantity(m.getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }
    }

    public void removeItem(String id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public float getTotalMoney() {
        float money = 0;
        for (Item item : items) {
            money += item.getQuantity() * item.getPrice();
        }

        return money;
    }

}
