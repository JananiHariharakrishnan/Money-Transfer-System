Commands to be executed in DB :

1. delete transaction_logs;
2. ALTER TABLE transaction_logs MODIFY COLUMN id BINARY(16);
3. ALTER TABLE accounts
   MODIFY COLUMN last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
4. The following trigger is to auto update the version:
DELIMITER $$

CREATE TRIGGER update_version_on_balance_change
BEFORE UPDATE ON accounts
FOR EACH ROW
BEGIN
IF NEW.balance != OLD.balance THEN
SET NEW.version = OLD.version + 1;
END IF;
END $$