package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Arrays;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void newAuthor() throws IOException {

        Author mt = new Author();
        mt.setName("Martin Ticher");
        mt.setAge(43);
        mt.setGenre("Horror");

        mt.setAvatar(BlobProxy.generateProxy(
                Files.readAllBytes(new File("avatars/mt_avatar.png").toPath())));
        mt.setBiography(ClobProxy.generateProxy(
                Files.readString(new File("biography/mt_bio.txt").toPath())));

        authorRepository.save(mt);
    }

    public void fetchAuthor() throws SQLException, IOException {

        Author author = authorRepository.findByName("Martin Ticher");
        System.out.println("Author bio: "
                + readBiography(author.getBiography()));
        System.out.println("Author avatar: "
                + Arrays.toString(readAvatar(author.getAvatar())));
    }

    private byte[] readAvatar(Blob avatar) throws SQLException, IOException {

        try ( InputStream is = avatar.getBinaryStream()) {
            return is.readAllBytes();
        }
    }

    private String readBiography(Clob bio) throws SQLException, IOException {

        StringBuilder sb = new StringBuilder();
        try ( Reader reader = bio.getCharacterStream()) {

            char[] buffer = new char[2048];
            for (int i = reader.read(buffer); i > 0; i = reader.read(buffer)) {
                sb.append(buffer, 0, i);
            }
        }

        return sb.toString();
    }
}
