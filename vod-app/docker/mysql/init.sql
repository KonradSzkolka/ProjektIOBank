CREATE TABLE IF NOT EXISTS guild_balance (
                                             id BIGINT NOT NULL AUTO_INCREMENT,
                                             account_name VARCHAR(255) NOT NULL,
    total_coins BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_guild_balance_account (account_name)
    );

INSERT INTO guild_balance(account_name, total_coins)
VALUES ('Korcyk.1234', 123456),
       ('Guildie.5678', 987654);
