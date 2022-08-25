drop table if exists patients;

CREATE TABLE patients
(
    patient_id    int          NOT NULL AUTO_INCREMENT,
    fio           varchar(250) NOT NULL,
    date_of_birth DATE         NOT NULL,
    sex           varchar(1)   NOT NULL,
    PRIMARY KEY (patient_id)
);

drop table if exists doctors;

CREATE TABLE doctors
(
    doctor_id  int          NOT NULL AUTO_INCREMENT,
    fio        varchar(250) NOT NULL,
    speciality varchar(250) NOT NULL,
    PRIMARY KEY (doctor_id)
);

CREATE TABLE visit
(
    visit_id   int          NOT NULL AUTO_INCREMENT,
    patient_id int          NOT NULL,
    doctor_id  int          NOT NULL,
    date       timestamp    NOT NULL,
    result     varchar(250) NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients,
    FOREIGN KEY (doctor_id) REFERENCES doctors,
    PRIMARY KEY (visit_id)
);