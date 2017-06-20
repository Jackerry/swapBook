CREATE TABLE `studentcertification` (
  `objectId` varchar(30) NOT NULL PRIMARY KEY,
  `school` varchar(40) NOT NULL,
  `studentNum` varchar(30) NOT NULL,
  `schoolCard` varchar(300) NOT NULL,
  `creatAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `objectId` varchar(30) NOT NULL PRIMARY KEY,
  `wxOpenId` varchar(40) ,
  `nickName` varchar(30) ,
  `realname` varchar(30) ,
  `password` varchar(30) ,
  `account` varchar(30) ,
  `avatar` varchar(200) ,
  `email` varchar(30) ,
  `phone` varchar(20) ,
  `studentCertification` varchar(30) ,
  `bookPoints` varchar(20) NOT NULL DEFAULT '0',
  `deposit` varchar(20) NOT NULL DEFAULT '0',
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`studentCertification`) REFERENCES `studentcertification` (`objectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `booklist` (
  `objectId` varchar(30) NOT NULL PRIMARY KEY,
  `tags` varchar(1000) ,
  `price` varchar(20) ,
  `title` varchar(50) ,
  `origin_title` varchar(50) ,
  `author` varchar(50) ,
  `publisher` varchar(50) ,
  `image` varchar(300) ,
  `mumber` varchar(20) NOT NULL DEFAULT '0',
  `swapedNum` varchar(20) NOT NULL DEFAULT '0',
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `book` (
  `objectId` varchar(30) NOT NULL PRIMARY KEY,
  `isbn` varchar(30) ,
  `userId` varchar(30) ,
  `state` varchar(4) NOT NULL DEFAULT '0',
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`isbn`) REFERENCES `booklist` (`objectId`),
  FOREIGN KEY (`userId`) REFERENCES `user` (`objectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `bookcar` (
  `objectId` varchar(30) NOT NULL PRIMARY KEY,
  `isbn` varchar(30) ,
  `bookId` varchar(30) ,
  `userId` varchar(30) ,
  `state` varchar(4) NOT NULL DEFAULT '0',
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upDateAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`isbn`) REFERENCES `booklist` (`objectId`),
  FOREIGN KEY (`bookId`) REFERENCES `book` (`objectId`),
  FOREIGN KEY (`userId`) REFERENCES `user` (`objectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `swapbook` (
  `objectId` varchar(30) NOT NULL PRIMARY KEY,
  `seller` varchar(30) ,
  `buyer` varchar(30) ,
  `bookId` varchar(30) ,
  `logistic` varchar(30) ,
  `state` varchar(4) NOT NULL DEFAULT '0',
  `createAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`seller`) REFERENCES `user` (`objectId`),
  FOREIGN KEY (`buyer`) REFERENCES `user` (`objectId`),
  FOREIGN KEY (`bookId`) REFERENCES `book` (`objectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
