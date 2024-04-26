use project;
SHOW TABLES;

show create table employee;

create table customer(customer_id varchar(10), credit_rating decimal(10,4) default 0, ssn int not null, name varchar(20) null, dob date not null, street varchar(20) not null, city varchar(20) not null, province varchar(20) not null, primary key(customer_id));
create table employee(employee_id varchar(10), salary int null default 0, join_date date null, manager_id varchar(10) not null, customer_id varchar(10), ssn int not null, name varchar(20) null, dob date not null, street varchar(20) not null, city varchar(20) not null, province varchar(20) not null, foreign key(customer_id) references customer(customer_id), primary key(employee_id));
create table person_phone(ssn int, phone int, primary key(ssn,phone));
create table branch(branch_id varchar(10), branch_city varchar(20) not null, assets decimal(10,4) null, primary key(branch_id));
create table savings_account(account_number varchar(10), balance int null, branch_id varchar(10), interest_rate decimal(10,4) null, foreign key(branch_id) references branch(branch_id), primary key(account_number));
create table current_account(account_number varchar(10), balance int null, branch_id varchar(10), overdrafts decimal(10,4) null, foreign key(branch_id) references branch(branch_id), primary key(account_number));
create table savings_account_customer(account_number varchar(10), customer_id varchar(10), last_access_date date not null);
create table loan(loan_number varchar(10), loan_amount int null, branch_id varchar(10), foreign key(branch_id) references branch(branch_id), primary key(loan_number));
create table current_account_customer(account_number varchar(10), customer_id varchar(10), last_access_date date not null);
create table payment(loan_number varchar(10), loan_payment_number int not null, payment_date date null, amount int null, primary key(loan_number, loan_payment_number));
create table customer_loan(customer_id varchar(10), loan_number varchar(10), primary key(customer_id, loan_number));




SELECT * FROM project.employee;
SELECT * FROM project.customer;
SELECT * FROM project.person_phone;
SELECT * FROM savings_account;
SELECT * FROM current_account;
SELECT * FROM loan;
SELECT * FROM payment;
SELECT * FROM project.branch;
SELECT * FROM savings_account_customer;
SELECT * FROM current_account_customer;
SELECT * FROM customer_loan;




drop table employee;
drop table customer;
drop table branch;
drop table project.person_phone;
drop table savings_account_customer;
drop table current_account_customer;
drop table savings_account;
drop table current_account;
drop table loan;
drop table payment;
drop table customer_loan;
drop table employee_dependents;
create table employee_dependents(employee_id varchar(10), dependents varchar(20), primary key(employee_id, dependents));






ALTER TABLE employee
DROP COLUMN customer_id;

DELETE FROM branch;

INSERT INTO Branch (branch_id, branch_city, assets)
VALUES
    ('Ha101', 'Hanoi', 100000),
    ('Ha102', 'Hanoi', 50000),
    ('HCM101', 'Ho Chi Minh', 100000),
    ('HCM102', 'Ho Chi Minh', 35000),
    ('HCM103', 'Ho Chi Minh', 85000),
    ('TDM101', 'Thu Dau Mot', 70000);


ALTER TABLE savings_account
DROP FOREIGN KEY savings_account_ibfk_1;

ALTER TABLE current_account
DROP FOREIGN KEY current_account_ibfk_1;

ALTER TABLE loan
DROP FOREIGN KEY loan_ibfk_1;

ALTER TABLE person_phone
DROP PRIMARY KEY;

INSERT INTO customer_loan (customer_id, loan_number) VALUES
('Emp102', 'LN898936');
INSERT INTO employee_dependents (employee_id, dependents) VALUES
('Emp101', 'Pham Le Gia Kiet');
INSERT INTO employee_dependents (employee_id, dependents) VALUES
('Emp102', 'Phan Ngoc Dieu My');
