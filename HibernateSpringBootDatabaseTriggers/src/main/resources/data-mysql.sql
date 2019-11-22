insert into book (book_title, book_isbn, book_pages, id) values ("A History of Ancient Prague", "001-JN", 400, 1) ^;

insert into chapter (chapter_title, chapter_pages, book_id, id) values ("Chapter 1", 54, 1, 1) ^;
insert into chapter (chapter_title, chapter_pages, book_id, id) values ("Chapter 2", 90, 1, 2) ^;
insert into chapter (chapter_title, chapter_pages, book_id, id) values ("Chapter 3", 34, 1, 3) ^;
insert into chapter (chapter_title, chapter_pages, book_id, id) values ("Chapter 4", 84, 1, 4) ^;
insert into chapter (chapter_title, chapter_pages, book_id, id) values ("Chapter 5", 104, 1, 5) ^;

DROP TRIGGER IF EXISTS check_book_pages ^; 

CREATE TRIGGER check_book_pages    
    AFTER INSERT ON chapter
        FOR EACH ROW 
            BEGIN 
                DECLARE total_pages INTEGER;
                DECLARE new_pages INTEGER;

                SET @total_pages := (SELECT book_pages FROM book WHERE id = NEW.book_id);
                SET @new_pages := (SELECT SUM(chapter_pages) FROM chapter WHERE book_id = NEW.book_id);                

                IF @new_pages > @total_pages THEN                    
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT='Adding this chapter is not allowed! The total number of pages is too high!';                    
                END IF;    
            END ^;