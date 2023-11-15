-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema quiz9.8
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quiz9.8
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quiz9.8` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `quiz9.8` ;

-- -----------------------------------------------------
-- Table `quiz9.8`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`role` (
  `Role_ID` INT NOT NULL,
  `RoleName` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`Role_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`account` (
  `Account_ID` VARCHAR(50) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL DEFAULT 'user123',
  `Role_ID` INT NOT NULL DEFAULT '3',
  `Status` INT NOT NULL DEFAULT '1',
  `Gender` FLOAT NULL DEFAULT NULL,
  `PhoneNumber` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`Account_ID`),
  INDEX `Role_ID_idx` (`Role_ID` ASC) VISIBLE,
  CONSTRAINT `Role_ID`
    FOREIGN KEY (`Role_ID`)
    REFERENCES `quiz9.8`.`role` (`Role_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`question` (
  `Question_ID` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(45) NULL DEFAULT NULL,
  `QuestionContent` VARCHAR(250) NULL DEFAULT NULL,
  `Type` INT NULL DEFAULT NULL,
  `Mark` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`Question_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`choicesofquestion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`choicesofquestion` (
  `Choice_ID` INT NOT NULL AUTO_INCREMENT,
  `Question_ID` INT NULL DEFAULT NULL,
  `ChoiceContent` VARCHAR(100) NULL DEFAULT NULL,
  `ScorePercentage` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Choice_ID`),
  INDEX `choice_question_idx` (`Question_ID` ASC) VISIBLE,
  CONSTRAINT `choice_question`
    FOREIGN KEY (`Question_ID`)
    REFERENCES `quiz9.8`.`question` (`Question_ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`course` (
  `Course_ID` VARCHAR(50) NOT NULL,
  `CourseName` VARCHAR(45) NULL DEFAULT NULL,
  `Semester` VARCHAR(45) NULL DEFAULT NULL,
  `StartDate` VARCHAR(45) NULL DEFAULT NULL,
  `EndDate` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Course_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`class` (
  `Class_ID` VARCHAR(50) NOT NULL,
  `ClassName` VARCHAR(45) NULL DEFAULT NULL,
  `Course_ID` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Class_ID`),
  INDEX `Class_Course_idx` (`Course_ID` ASC) VISIBLE,
  CONSTRAINT `Class_Course`
    FOREIGN KEY (`Course_ID`)
    REFERENCES `quiz9.8`.`course` (`Course_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`exam`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`exam` (
  `Exam_ID` INT NOT NULL AUTO_INCREMENT,
  `Class_ID` VARCHAR(50) NOT NULL,
  `ExamName` VARCHAR(45) NULL DEFAULT NULL,
  `QuestionNumber` INT NULL DEFAULT NULL,
  `StartDate` VARCHAR(45) NULL DEFAULT NULL,
  `EndDate` VARCHAR(45) NULL DEFAULT NULL,
  `TimeLimit` VARCHAR(45) NULL DEFAULT NULL,
  `ExamDetail` VARCHAR(300) NULL DEFAULT NULL,
  `MaxScore` FLOAT NULL DEFAULT NULL,
  `Permission` INT NULL DEFAULT '0',
  `CreatedBy` VARCHAR(50) NULL,
  PRIMARY KEY (`Exam_ID`),
  INDEX `Exam_Class` (`Class_ID` ASC) VISIBLE,
  CONSTRAINT `Exam_Class`
    FOREIGN KEY (`Class_ID`)
    REFERENCES `quiz9.8`.`class` (`Class_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`lecturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`lecturer` (
  `Lecturer_ID` VARCHAR(50) NOT NULL,
  `Department` VARCHAR(45) NULL DEFAULT NULL,
  `MeetLink` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Lecturer_ID`),
  CONSTRAINT `Lecturer_ID`
    FOREIGN KEY (`Lecturer_ID`)
    REFERENCES `quiz9.8`.`account` (`Account_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`lecturerinwhichclass`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`lecturerinwhichclass` (
  `Class_ID` VARCHAR(50) NULL DEFAULT NULL,
  `Lecturer_ID` VARCHAR(50) NULL DEFAULT NULL,
  UNIQUE INDEX `onelecturer` (`Class_ID` ASC, `Lecturer_ID` ASC) VISIBLE,
  INDEX `class_idx` (`Class_ID` ASC) VISIBLE,
  INDEX `lecturer_idx` (`Lecturer_ID` ASC) VISIBLE,
  CONSTRAINT `class`
    FOREIGN KEY (`Class_ID`)
    REFERENCES `quiz9.8`.`class` (`Class_ID`)
    ON DELETE CASCADE
    on update cascade,
  CONSTRAINT `lecturer`
    FOREIGN KEY (`Lecturer_ID`)
    REFERENCES `quiz9.8`.`lecturer` (`Lecturer_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`questioninwhichexam`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`questioninwhichexam` (
  `Exam_ID` INT NULL DEFAULT NULL,
  `Question_ID` INT NULL DEFAULT NULL,
  INDEX `Bank_Exam_idx` (`Exam_ID` ASC) VISIBLE,
  INDEX `Bank_Question_idx` (`Question_ID` ASC) VISIBLE,
  CONSTRAINT `Bank_Exam`
    FOREIGN KEY (`Exam_ID`)
    REFERENCES `quiz9.8`.`exam` (`Exam_ID`)
    ON DELETE CASCADE
    on update cascade,
  CONSTRAINT `Bank_Question`
    FOREIGN KEY (`Question_ID`)
    REFERENCES `quiz9.8`.`question` (`Question_ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`student` (
  `Student_ID` VARCHAR(50) NOT NULL,
  `Major` VARCHAR(45) NULL DEFAULT NULL,
  `SchoolYear` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Student_ID`),
  CONSTRAINT `Student_ID`
    FOREIGN KEY (`Student_ID`)
    REFERENCES `quiz9.8`.`account` (`Account_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`studentresult`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`studentresult` (
  `Result_ID` VARCHAR(100) NOT NULL,
  `Exam_ID` INT NULL DEFAULT NULL,
  `Student_ID` VARCHAR(50) NOT NULL,
  `TotalScore` FLOAT NULL DEFAULT NULL,
  `TotalTime` VARCHAR(45) NULL DEFAULT NULL,
  `State` INT NULL DEFAULT '0',
  PRIMARY KEY (`Result_ID`),
  UNIQUE INDEX `oneresultperexam` (`Exam_ID` ASC, `Student_ID` ASC) VISIBLE,
  INDEX `Result_Exam_idx` (`Exam_ID` ASC) VISIBLE,
  INDEX `Result_Student_idx` (`Student_ID` ASC) VISIBLE,
  CONSTRAINT `Result_Exam`
    FOREIGN KEY (`Exam_ID`)
    REFERENCES `quiz9.8`.`exam` (`Exam_ID`)
    ON DELETE CASCADE
    on update cascade,
  CONSTRAINT `ResultStudent`
    FOREIGN KEY (`Student_ID`)
    REFERENCES `quiz9.8`.`student` (`Student_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`studentanswer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`studentanswer` (
  `StudentAnswer_ID` VARCHAR(100) NOT NULL,
  `Question_ID` INT NULL DEFAULT NULL,
  `Choice_ID` INT NULL DEFAULT NULL,
  `Exam_ID` int NULL DEFAULT NULL,
  `Student_ID` VARCHAR(50) not null,
  PRIMARY KEY (`StudentAnswer_ID`),
  INDEX `answer_question_idx` (`Question_ID` ASC) VISIBLE,
  INDEX `answer_choice_idx` (`Choice_ID` ASC) VISIBLE,
  INDEX `answer_exam_idx` (`Exam_ID` ASC) VISIBLE,
  INDEX `answer_student_idx` (`Student_ID` ASC) VISIBLE,
  CONSTRAINT `answer_choice`
    FOREIGN KEY (`Choice_ID`)
    REFERENCES `quiz9.8`.`choicesofquestion` (`Choice_ID`)
    ON DELETE CASCADE,
  CONSTRAINT `answer_question`
    FOREIGN KEY (`Question_ID`)
    REFERENCES `quiz9.8`.`question` (`Question_ID`)
    ON DELETE CASCADE,
  CONSTRAINT `answer_exam`
    FOREIGN KEY (`Exam_ID`)
    REFERENCES `quiz9.8`.`exam` (`Exam_ID`)
    ON DELETE CASCADE
    on update cascade,
    CONSTRAINT `answer_student`
    FOREIGN KEY (`Student_ID`)
    REFERENCES `quiz9.8`.`student` (`Student_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`studentinwhichclass`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`studentinwhichclass` (
  `Student_ID` VARCHAR(50) NOT NULL,
  `Class_ID` VARCHAR(50) NOT NULL,
  UNIQUE INDEX `onestudent` (`Class_ID` ASC, `Student_ID` ASC) VISIBLE,
  INDEX `Class_ID_idx` (`Class_ID` ASC) VISIBLE,
  INDEX `Student_ID_idx` (`Student_ID` ASC) VISIBLE,
  CONSTRAINT `Class_ID`
    FOREIGN KEY (`Class_ID`)
    REFERENCES `quiz9.8`.`class` (`Class_ID`)
    ON DELETE CASCADE
    on update cascade,
  CONSTRAINT `Student_ID_In_Student`
    FOREIGN KEY (`Student_ID`)
    REFERENCES `quiz9.8`.`student` (`Student_ID`)
    ON DELETE CASCADE
    on update cascade)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz9.8`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`bank` (
  `Bank_ID` INT NOT NULL AUTO_INCREMENT,
  `Course_ID` VARCHAR(50) NULL,
  `Lecturer_ID` VARCHAR(50) NULL,
  `BankName` VARCHAR(45) NULL,
  PRIMARY KEY (`Bank_ID`),
  INDEX `bank_course_idx` (`Course_ID` ASC) VISIBLE,
  INDEX `bank_lecturer_idx` (`Lecturer_ID` ASC) VISIBLE,
  CONSTRAINT `bank_course`
    FOREIGN KEY (`Course_ID`)
    REFERENCES `quiz9.8`.`course` (`Course_ID`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `bank_lecturer`
    FOREIGN KEY (`Lecturer_ID`)
    REFERENCES `quiz9.8`.`lecturer` (`Lecturer_ID`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `quiz9.8`.`questioninwhichbank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz9.8`.`questioninwhichbank` (
  `Bank_ID` INT NULL,
  `Question_ID` INT NULL,
  UNIQUE INDEX `uniquequestion` (`Bank_ID` ASC, `Question_ID` ASC) VISIBLE,
  INDEX `bank1_idx` (`Bank_ID` ASC) VISIBLE,
  INDEX `bank2_idx` (`Question_ID` ASC) VISIBLE,
  CONSTRAINT `bank1`
    FOREIGN KEY (`Bank_ID`)
    REFERENCES `quiz9.8`.`bank` (`Bank_ID`)
    ON DELETE cascade
    ON UPDATE NO ACTION,
  CONSTRAINT `bank2`
    FOREIGN KEY (`Question_ID`)
    REFERENCES `quiz9.8`.`question` (`Question_ID`)
    ON DELETE cascade
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `quiz9.8`;
INSERT INTO role (`Role_ID`, `RoleName`) VALUES (0, 'Admin');
INSERT INTO role (`Role_ID`, `RoleName`) VALUES (1, 'Manager');
INSERT INTO role (`Role_ID`, `RoleName`) VALUES (2, 'Lecturer');
INSERT INTO role (`Role_ID`, `RoleName`) VALUES (3, 'Student');

START TRANSACTION;
USE `quiz9.8`;
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('nampt_he_171400', 'Admin1', 'nampthe171400@fpt.edu.vn', '123', 0, 1, NULL, '0123456789');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('dungnt_he_176358', 'Student A', 'dungnthe176358@fpt.edu.vn', '234', 3, 1, NULL, '12345670');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('minhvnt_he_176043', 'Lecturer A', 'minhvnthe176043@fpt.edu.vn', '345', 2, 1, NULL, '190009');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('thinhnv_he_163468', 'Manager1', 'thinhnvhe163468@fpt.edu.vn', '456', 1, 1, NULL, '1114115');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('thinhvd_se_06148', 'Student B', 'thinhvdse06148@fpt.edu.vn', '567', 3, 1, NULL, '76543210');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('C', 'Student C', 'xuandan@fpt.edu.vn', '3', 3, 1, NULL, '0000000002');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('D', 'Student D', 'notsorry@fpt.edu.vn', '3', 3, 1, NULL, '0000000003');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('E', 'Student E', 'hungry@fpt.edu.vn', '3', 3, 1, NULL, '0000000004');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('F', 'Student F', 'beta@fpt.edu.vn', '3', 3, 1, NULL, '0000000005');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('G', 'Lecturer G', 'gg@fe.edu.vn', '2', 2, 1, NULL, '0000000006');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('H', 'Lecturer H', 'hh@fe.edu.vn', '2', 2, 1, NULL, '0000000007');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('I', 'Student I', 'ii@fpt.edu.vn', '3', 3, 1, NULL, '0000000008');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('J', 'Lecturer J', 'jk@fe.edu.vn', '2', 2, 1, NULL, '0000000009');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('K', 'Lecturer K', 'gg@fe.edu.vn', '2', 2, 1, NULL, '0000000010');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('L', 'Student L', 'loser@fpt.edu.vn', '3', 3, 1, NULL, '0000000011');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('M', 'Student M', 'mm@fpt.edu.vn', '3', 3, 1, NULL, '0000000012');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('N', 'Student N', 'nn@fpt.edu.vn', '3', 3, 1, NULL, '0000000013');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('O', 'Lecturer O', 'oo@fe.edu.vn', '3', 3, 1, NULL, '0000000014');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('P', 'Student P', 'peepee@fpt.edu.vn', '3', 3, 1, NULL, '0000000015');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('Q', 'Student Q', 'qq@fpt.edu.vn', '3', 3, 1, NULL, '0000000016');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('R', 'Student R', 'rr@fpt.edu.vn', '3', 3, 1, NULL, '0000000017');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('S', 'Student S', 'ss@fpt.edu.vn', '3', 3, 1, NULL, '0000000018');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('T', 'Student T', 'tt@fpt.edu.vn', '3', 3, 1, NULL, '0000000019');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('U', 'Student U', 'uu@fpt.edu.vn', '3', 3, 1, NULL, '0000000020');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('V', 'Student V', 'vv@fpt.edu.vn', '3', 3, 1, NULL, '0000000021');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('W', 'Student W', 'ww@fpt.edu.vn', '3', 3, 1, NULL, '0000000022');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('X', 'Student X', 'xx@fpt.edu.vn', '3', 3, 1, NULL, '0000000023');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('Y', 'Student Y', 'yy@fpt.edu.vn', '3', 3, 1, NULL, '0000000024');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('Z', 'Student Z', 'zz@fpt.edu.vn', '3', 3, 1, NULL, '0000000025');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('AB', 'Student AB', 'ab@fpt.edu.vn', '3', 3, 1, NULL, '0000000026');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('BC', 'Student BC', 'bc@fpt.edu.vn', '3', 3, 1, NULL, '0000000027');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('CD', 'Student CD', 'cd@fpt.edu.vn', '3', 3, 1, NULL, '0000000028');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('DE', 'Student DE', 'de@fpt.edu.vn', '3', 3, 1, NULL, '0000000029');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('EF', 'Student EF', 'ef@fpt.edu.vn', '3', 3, 1, NULL, '0000000030');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('FG', 'Student FG', 'fg@fpt.edu.vn', '3', 3, 1, NULL, '0000000031');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('GH', 'Student GH', 'gh@fpt.edu.vn', '3', 3, 1, NULL, '0000000032');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('HI', 'Student HI', 'hi@fpt.edu.vn', '3', 3, 1, NULL, '0000000033');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('IJ', 'Student IJ', 'ij@fpt.edu.vn', '3', 3, 1, NULL, '0000000034');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('JK', 'Student JK', 'jk@fpt.edu.vn', '3', 3, 1, NULL, '0000000035');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('KL', 'Student KL', 'kl@fpt.edu.vn', '3', 3, 1, NULL, '0000000036');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('LM', 'Admin LM', 'lm@fpt.edu.vn', '3', 0, 1, NULL, '0000000037');
INSERT INTO account (`Account_ID`, `Name`, `Email`, `Password`, `Role_ID`, `Status`, `Gender`, `PhoneNumber`) VALUES ('MN', 'Manager MN', 'mn@fpt.edu.vn', '3', 1, 1, NULL, '0000000038');

COMMIT;
-- -----------------------------------------------------
-- Data for table `quiz7`.`course`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('MAS291', 'Mathematics and Statistics', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('EXE101', 'Elicit Xperiences for Elites', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('SWP491', 'Exit Extrance', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('SSL102', 'Random Keystroke Simulation', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('NWC203', 'Random Keystroke Simulation 2', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('ITE302', 'Random Keystroke Simulation 3', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('SSG102', 'Charity Simulation', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('JPD134', 'Japanese 3', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('ACC101', 'Accountant 101', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('CNH123', 'Chinese 2', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('ENW224', 'Economics 2', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('MKT101', 'Marketing 101', 'Fall 2023', '2023/09/03', '2023/12/02');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('MAE112', 'Mathematics for Engineer', 'Summer 2023', '2023/06/05', '2023/07/19');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('DBI101', 'Database 101', 'Summer 2023', '2023/06/05', '2023/07/19');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('DTG102', 'Drawing to Geath', 'Summer 2023', '2023/06/05', '2023/07/19');
INSERT INTO course (`Course_ID`, `CourseName`, `Semester`, `StartDate`, `EndDate`) VALUES ('VCM202', 'Visual Com', 'Summer 2023', '2023/06/05', '2023/07/19');

COMMIT;


-- -----------------------------------------------------
-- Data for table `quiz7`.`class`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO class (`Class_ID`, `ClassName`, `Course_ID`) VALUES ('SE1732_MAS291', 'SE1732', 'MAS291');
INSERT INTO class (`Class_ID`, `ClassName`, `Course_ID`) VALUES ('SE1732_NWC203', 'SE1732', 'NWC203');

COMMIT;

-- -----------------------------------------------------
-- Data for table `quiz9`.`exam`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO `quiz9.8`.`exam` (`Class_ID`, `ExamName`, `QuestionNumber`, `StartDate`, `EndDate`, `TimeLimit`, `ExamDetail`, `MaxScore`, `Permission`,`CreatedBy`) VALUES ('SE1732_MAS291', 'Progress Test 1', 10, '2023-09-20T09:00:00', '2023-11-20T11:00:00', '00:45:00', 'Do this or die', 16, 1,'minhvnt_he_176043');
INSERT INTO `quiz9.8`.`exam` (`Class_ID`, `ExamName`, `QuestionNumber`, `StartDate`, `EndDate`, `TimeLimit`, `ExamDetail`, `MaxScore`, `Permission`,`CreatedBy`) VALUES ('SE1732_MAS291', 'Progress Test 2', 0, '2023-09-20T09:10:00', '2023-09-20T11:10:00', '00:30:23', 'Do or not to do', 0, 0,'minhvnt_he_176043');
INSERT INTO `quiz9.8`.`exam` (`Class_ID`, `ExamName`, `QuestionNumber`, `StartDate`, `EndDate`, `TimeLimit`, `ExamDetail`, `MaxScore`, `Permission`,`CreatedBy`) VALUES ('SE1732_MAS291', 'Progress Test 3', 0, '2023-09-20T09:30:00', '2023-09-20T11:30:00', '01:00:02', 'Welcome gosujinsama', 0, 0,'minhvnt_he_176043');
INSERT INTO `quiz9.8`.`exam` (`Class_ID`, `ExamName`, `QuestionNumber`, `StartDate`, `EndDate`, `TimeLimit`, `ExamDetail`, `MaxScore`, `Permission`,`CreatedBy`) VALUES ('SE1732_MAS291', 'Final Trial', 0, '2023-09-20T09:00:00', '2023-10-20T09:00:00', '00:00:30', 'Die yet', 0, 1,'minhvnt_he_176043');

COMMIT;

-- Data for table `quiz9.8`.`question`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 1', '1 + 0 =', 1, 5.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 2', '1 - 0 =', 1, 3.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 3', '1 : 0 =', 1, 3.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 4', 'Egg comes first or human comes first', 1, 5.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 12', '12 : 1 =', 1, 3.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 18', 'When was you molestered', 1, 3.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 21', 'What is before 22', 1, 3.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 22', 'Who is Taylor Swift', 1, 3.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 23', 'What is after 22', 1, 3.0);
INSERT INTO question (`Title`, `QuestionContent`, `Type`, `Mark`) VALUES ('Cau 31', 'Do you want to nail this test', 1, 3.0);

COMMIT;

-- -----------------------------------------------------
-- Data for table `quiz9`.`questioninwhichexam`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 1);
INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 2);
INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 3);
INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 4);
-- INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 5);
-- INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 6);
-- INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 7);
-- INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 8);
-- INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 9);
-- INSERT INTO `quiz9.8`.`questioninwhichexam` (`Exam_ID`, `Question_ID`) VALUES ('1', 10);

COMMIT;

-- -----------------------------------------------------
-- Data for table `quiz9`.`choicesofquestion`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('1', '1', 100);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('1', '2', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('1', '9', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('1', '0', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('2', '1', 50);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('2', '2', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('2', '0', 50);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('2', '3', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('3', '0', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('3', '1', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('3', 'How?', 100);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('4', 'Chicken', 33);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('4', 'Dog', 33);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('4', 'Rabbit', 33);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('4', 'J.Man', 0);
INSERT INTO `quiz9.8`.`choicesofquestion` (`Question_ID`, `ChoiceContent`, `ScorePercentage`) VALUES ('4', 'Don\'t know', 0);


COMMIT;

-- -----------------------------------------------------
-- Data for table `quiz7`.`lecturer`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO lecturer (`Lecturer_ID`, `Department`, `MeetLink`) VALUES ('minhvnt_he_176043', 'IT', 'thisismeetlink');
INSERT INTO lecturer (`Lecturer_ID`, `Department`, `MeetLink`) VALUES ('G', 'IT', 'thisismeetlink');
INSERT INTO lecturer (`Lecturer_ID`, `Department`, `MeetLink`) VALUES ('H', 'IT', 'thisismeetlink');
INSERT INTO lecturer (`Lecturer_ID`, `Department`, `MeetLink`) VALUES ('J', 'IT', 'thisismeetlink');
INSERT INTO lecturer (`Lecturer_ID`, `Department`, `MeetLink`) VALUES ('K', 'IT', 'thisismeetlink');
INSERT INTO lecturer (`Lecturer_ID`, `Department`, `MeetLink`) VALUES ('O', 'IT', 'thisismeetlink');

COMMIT;


-- -----------------------------------------------------
-- Data for table `quiz7`.`lecturerinwhichclass`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO lecturerinwhichclass (`Class_ID`, `Lecturer_ID`) VALUES ('SE1732_MAS291', 'minhvnt_he_176043');
INSERT INTO lecturerinwhichclass (`Class_ID`, `Lecturer_ID`) VALUES ('SE1732_NWC203', 'minhvnt_he_176043');

COMMIT;


-- -----------------------------------------------------
-- Data for table `quiz7`.`student`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('dungnt_he_176358', 'Software Engneering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('thinhvd_se_06148', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('A', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('B', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('C', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('D', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('E', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('F', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('I', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('L', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('M', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('N', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('P', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('Q', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('R', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('S', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('T', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('U', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('V', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('W', 'Software Engineeringexam', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('X', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('Y', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('Z', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('AB', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('BC', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('CD', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('DE', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('EF', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('FG', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('GH', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('HI', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('IJ', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('JK', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('KL', 'Software Engineering', 'K17');
INSERT INTO student (`Student_ID`, `Major`, `SchoolYear`) VALUES ('LM', 'Software Engineering', 'K17');

COMMIT;


-- -----------------------------------------------------
-- Data for table `quiz7`.`studentinwhichclass`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('dungnt_he_176358', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('dungnt_he_176358', 'SE1732_NWC203');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('A', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('B', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('C', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('D', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('E', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('F', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('I', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('L', 'SE1732_MAS291');
INSERT INTO studentinwhichclass (`Student_ID`, `Class_ID`) VALUES ('M', 'SE1732_MAS291');

COMMIT;

-- -----------------------------------------------------
-- Data for table `quiz9.8`.`bank`
-- -----------------------------------------------------
START TRANSACTION;
USE `quiz9.8`;
INSERT INTO `quiz9.8`.`bank` (`Bank_ID`, `Course_ID`, `Lecturer_ID`, `BankName`) VALUES (1, 'MAS291', 'minhvnt_he_176043', 'MAS291_SE1732_minhvnthe176043');

START TRANSACTION;
USE `quiz9.8`;
INSERT INTO `quiz9.8`.`questioninwhichbank` (`Bank_ID`, `Question_ID`) VALUES (1, 1);
INSERT INTO `quiz9.8`.`questioninwhichbank` (`Bank_ID`, `Question_ID`) VALUES (1, 2);

COMMIT;

-- START TRANSACTION;
-- USE `quiz9.8`;
-- INSERT INTO `quiz9.8`.`studentresult` (`Result_ID`, `Exam_ID`, `Student_ID`, `TotalScore`, `TotalTime`, `State`) VALUES ('1', 1, 'Z', 1, '1', 1);
-- INSERT INTO `quiz9.8`.`studentresult` (`Result_ID`, `Exam_ID`, `Student_ID`, `TotalScore`, `TotalTime`, `State`) VALUES ('2', 1, 'Z', 3, '4', 5);

-- COMMIT;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
