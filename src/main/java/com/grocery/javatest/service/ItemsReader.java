package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import com.grocery.javatest.model.Product;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ItemsReader {

    private static final String SPACE = " ";
    private static final String HELP_STRING =
            "Enter product and optionally amount separated by space or Enter to finish: ";

    public List<Item> readItems(InputStream inputStream) {
        List<Item> items = new ArrayList<>();

        Scanner scan = new Scanner(inputStream);

        String line = getNextLine(scan);
        while (!line.isEmpty()) {
            try {
                Item item = parseLine(line);
                items.add(item);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
            line = getNextLine(scan);
        }

        return items;
    }

    private String getNextLine(Scanner scan) {
        System.out.print(HELP_STRING);
        return scan.nextLine();
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
