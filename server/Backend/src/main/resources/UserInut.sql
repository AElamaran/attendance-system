INSERT INTO User (id, username, password) VALUES
                                              (1, 'testuser1', 'User@123'),
                                              (2, 'testuser2', 'User@321')
    ON DUPLICATE KEY UPDATE username=VALUES(username), password=VALUES(password);