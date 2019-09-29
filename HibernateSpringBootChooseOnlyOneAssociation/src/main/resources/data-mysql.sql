INSERT INTO article (title, id) VALUES ("CSS3 Tricks", 1) ^;
INSERT INTO magazine (title, id) VALUES ("Java QA", 1) ^;
INSERT INTO book (title, id) VALUES ("Java Coding Problems", 1) ^;

DROP TRIGGER IF EXISTS Just_One_Of_Many ^; 

CREATE TRIGGER Just_One_Of_Many
    BEFORE INSERT ON review
        FOR EACH ROW 
            BEGIN 
                IF (NEW.article_id IS NOT NULL AND NEW.magazine_id IS NOT NULL) OR 
                   (NEW.article_id IS NOT NULL AND NEW.book_id IS NOT NULL) OR
                   (NEW.book_id IS NOT NULL AND NEW.magazine_id IS NOT NULL) THEN                    
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT='A review can be associated with either a book, a magazine or an article';                    
                END IF;    
            END ^;