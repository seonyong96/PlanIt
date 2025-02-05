CREATE TABLE `User` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(50) NOT NULL UNIQUE,
    pw VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phoneNum VARCHAR(20) NOT NULL,  -- 길이 확장
    birth VARCHAR(20) NOT NULL,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifyTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Plan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    startTime DATETIME NOT NULL,
    endTime DATETIME NOT NULL,
    createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifyTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES `User`(id) ON DELETE CASCADE ON UPDATE CASCADE
);