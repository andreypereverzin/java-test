package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import com.grocery.javatest.model.Product;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;

class ItemsReader {

    private static final String SPACE = " ";
    private static final String HELP_STRING =
            "Enter product and optionally amount separated by space or Enter to finish:";

    List<Item> readItems(InputStream inputStream) {
        System.out.println(HELP_STRING);

        List<Item> items = new ArrayList<>();

        Scanner scan = new Scanner(inputStream);

        String line = scan.nextLine();
        while (!line.isEmpty()) {
            try {
                Item item = parseLine(line);
                items.add(item);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
            line = scan.nextLine();
        }

        return items;
    }

    private Item parseLine(String line) throws ParseException {
        StringTokenizer st = new StringTokenizer(line, SPACE);

        String name = st.nextToken();
        Optional<Product> productOpt = Product.findByName(name);
        if (!productOpt.isPresent()) {
            throw new ParseException(String.format("Product %s not found", name));
        }
        if (!st.hasMoreTokens()) {
            return new Item(productOpt.get(), 1);
        }

        try {
            int amount = Integer.parseInt(st.nextToken());
            return new Item(productOpt.get(), amount);
        } catch (NumberFormatException ex) {
            throw new ParseException("Cannot read amount");
        }
    }
}
