/**
 * CREATE Script for init of DB
 */

-- Car database entires
insert into car (id, date_created, deleted, licenseplate, seatcount, convertible, engine_type, manufacturer) values (1, now(), false, 'RAJ23232',
'4', false, 'Gas', 'BMW');

insert into car (id, date_created, deleted, licenseplate, seatcount, convertible, engine_type, manufacturer) values (2, now(), false, 'RAJ1317',
'7', false, 'Petrol', 'AUdi');

insert into car (id, date_created, deleted, licenseplate, seatcount, convertible, engine_type, manufacturer) values (3, now(), false, 'RAJ1983',
'4', true, 'Gas', 'Mercedes');
