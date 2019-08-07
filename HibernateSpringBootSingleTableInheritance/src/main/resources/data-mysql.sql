DROP TRIGGER IF EXISTS ebook_format_trigger ^; 
DROP TRIGGER IF EXISTS paperback_weight_trigger ^; 
DROP TRIGGER IF EXISTS paperback_size_trigger ^; 

CREATE TRIGGER ebook_format_trigger
    BEFORE INSERT ON book
        FOR EACH ROW 
            BEGIN 
                IF NEW.DTYPE = 2 THEN 
                    IF NEW.format IS NULL THEN  
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT='The format of e-book cannot be null';
                    END IF;
                END IF;    
            END ^;

CREATE TRIGGER paperback_weight_trigger
    BEFORE INSERT ON book
        FOR EACH ROW 
            BEGIN 
                IF NEW.DTYPE = 3 THEN 
                    IF NEW.weight_lbs IS NULL THEN  
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT='The weight of paperback cannot be null';
                    END IF;
                END IF;    
            END ^;

CREATE TRIGGER paperback_size_trigger
    BEFORE INSERT ON book
        FOR EACH ROW 
            BEGIN 
                IF NEW.DTYPE = 3 THEN 
                    IF NEW.size_in IS NULL THEN  
                        SIGNAL SQLSTATE '45000'
                        SET MESSAGE_TEXT='The size of paperback cannot be null';
                    END IF;
                END IF;    
            END ^;
			