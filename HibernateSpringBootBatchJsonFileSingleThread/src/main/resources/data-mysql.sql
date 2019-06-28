-- Drop
DROP TABLE IF EXISTS `citylotsdb`.`lots`;

-- Create the table 
CREATE TABLE `lots` (
  `lot` json DEFAULT NULL
);