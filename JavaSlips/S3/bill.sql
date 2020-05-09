create table BillMaster(billno serial primary key, custname text, billdate date);
create table BillDetails(itemname text, qty int, rate float, billno int references BillMaster(billno));

insert into BillMaster(custname,billdate) values('Ram Gore','2015-01-01'),('Seeta Kale','2015-03-01');

insert into BillDetails values('Pen',2,30,1),('Pencil',10,10,1)('Eraser',5,5,1),('Santoor Soap',3,45,2),('Kissan Jam',2,110,2),('Pravin Mango Pickel',4,45,2);
