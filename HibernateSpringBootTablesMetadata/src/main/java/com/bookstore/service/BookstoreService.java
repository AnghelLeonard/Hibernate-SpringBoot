package com.bookstore.service;

import com.bookstore.config.DatabaseTableMetadataExtractor;
import java.util.Iterator;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.mapping.Table;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    public void extractTablesMetadata() {
        for (Namespace namespace : DatabaseTableMetadataExtractor.EXTRACTOR
                .getDatabase()
                .getNamespaces()) {

            namespace.getTables().forEach(this::displayTablesMetdata);
        }
    }

    private void displayTablesMetdata(Table table) {

        System.out.println("\nTable: " + table);
        Iterator it = table.getColumnIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
