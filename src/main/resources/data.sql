-- INSERTION DES USERS
INSERT INTO USER VALUES (1, 'DAVID', '$2a$10$vsMf.RQM/cg3nUjoYU8WH.bB9abGYVeE/rmSPLZ3UAR6/WksudUUu', 'ADMIN', 1, 'David', 'Pomerlano', FALSE, NULL, 'dpomerlano@pomerleau.com');
INSERT INTO USER VALUES (2, '22211111', '$2a$10$vsMf.RQM/cg3nUjoYU8WH.bB9abGYVeE/rmSPLZ3UAR6/WksudUUu', 'USER', 1, 'User1FirstName', 'User1LastName', FALSE, NULL, 'user1@example.com');
INSERT INTO USER VALUES (3, '22233333', '$2a$10$vsMf.RQM/cg3nUjoYU8WH.bB9abGYVeE/rmSPLZ3UAR6/WksudUUu', 'USER', 1, 'Jean-Michel', 'Benoit', FALSE, NULL, 'jmb@tecsys.com'); -- qwerty
INSERT INTO USER VALUES (4, '22244444', '$2a$10$vsMf.RQM/cg3nUjoYU8WH.bB9abGYVeE/rmSPLZ3UAR6/WksudUUu', 'USER', 1, NULL, NULL, TRUE, 'A Company', 'comptesarecevoir@acompany.com'); -- querty

-- INSERTION DES CHALLENGES POUR LES USERS
INSERT INTO CHALLENGE VALUES (1, 'MENOUM', 'MENOUM', 'DAVID');
INSERT INTO CHALLENGE VALUES (2, 'BADOUM', 'BADOUM', 'DAVID');
INSERT INTO CHALLENGE VALUES (3, 'ALLO', 'ALLO', '22211111');
INSERT INTO CHALLENGE VALUES (4, 'BONJOUR', 'BONJOUR', '22233333');
INSERT INTO CHALLENGE VALUES (5, 'SALUT', 'SALUT', '22244444');

-- INSERTION DU BROKER DE EQUIPE PASSERELLE PAIEMENT
INSERT INTO PAYMENT_BROKER VALUES (1, '15489123311', 'Buenos933-accole');

-- INSERTION DU BROKER DE EQUIPE BANQUE1
INSERT INTO PARTNER_BANK VALUES (1, 'Dow4#esquint', 'Banque1', '111', 'https://theirHeroku/banktobank/domething', 'theApiKeyTheyGiveUs');

-- INSERTION CREDIT CARD
INSERT INTO CREDIT_CARD VALUES (5105139374862083, '14.96', 10000,1,2020,'123',2);
INSERT INTO CREDIT_CARD VALUES (5105749559146043, '20.00', 10000,1,2020,'123',3);
INSERT INTO CREDIT_CARD VALUES (5105823505096154, '30.00', 10000,1,2020,'123',4);

-- INSERTION CREDIT CARD TRANSACTION
INSERT INTO CREDIT_CARD_TRANSACTION VALUES (1, '14.96', 'Achat COCO LOCO Griffintown', CURRENT_TIMESTAMP(), 5105139374862083, false);
INSERT INTO CREDIT_CARD_TRANSACTION VALUES (2, '20.00', 'Achat ETSMTL', CURRENT_TIMESTAMP(), 5105749559146043, false);
INSERT INTO CREDIT_CARD_TRANSACTION VALUES (3, '30.00', 'Achat Boite à Pain', CURRENT_TIMESTAMP(), 5105823505096154, false);

-- INSERTION DEBIT CARD
INSERT INTO DEBIT_CARD VALUES (22212345, 100000, 2);
INSERT INTO DEBIT_CARD VALUES (22233333, 100000, 3);
INSERT INTO DEBIT_CARD VALUES (22244444, 100000, 4);

-- INSERTION DEBIT CARD TRANSACTION
INSERT INTO DEBIT_CARD_TRANSACTION VALUES(1, 100000, 'Virement reçu - Prime de bienvenue', CURRENT_TIMESTAMP(), 22212345);

COMMIT;