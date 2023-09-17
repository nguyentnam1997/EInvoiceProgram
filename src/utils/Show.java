package utils;

import entities.Seller;

public class Show {
    public static void showInfoSellerCompany(Seller seller) {
        TableList tableList = new TableList(5, "Tax code", "Seller name", "Address", "Email", "Hotline").sortBy(0).withUnicode(true);
// from a list
        tableList.addRow(String.valueOf(seller.getTaxCode()), seller.getName(), seller.getAddress(), seller.getEmail(),  String.valueOf(seller.getHotline()));
// or manually
        tableList.print();
    }
}
