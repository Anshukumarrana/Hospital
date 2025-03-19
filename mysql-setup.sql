--Create  admin data table

CREATE TABLE admin (
    un varchar(10),
    pass int 
);
--Create  patients data table
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(120) NOT NULL,
    last_name VARCHAR(120) NOT NULL,
    gender VARCHAR(15) NOT NULL,
    dob DATE NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100) ,
    address TEXT,
    doctor_id INT,
    bed_number INT
);

--Create  doctors data table
CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) 
);

-- Create  appointments data table
CREATE TABLE appointments (
    appointment_id INT  PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);