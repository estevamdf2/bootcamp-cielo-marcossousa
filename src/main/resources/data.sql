insert into pessoa_juridica (uuid, mcc, cpf, cnpj, razao_social, nome_contato, email_contato) values
    ('619d01e5-8b54-4067-948b-97b720e17453'	, '1711', '63944134028', '59048385000100', 'Tudo frio Ar condicionado',  'Marcelino Freitas', 'marcelino.freitas@gmail.com');

insert into pessoa_fisica (uuid, mcc, cpf, nome_pessoa, email_pessoa)
values ('c2de247d-1249-4866-93ec-0f427af605fb', '0763', '58795241086', 'Ana Moreira Froes',	'ana.moreira@gmail.com');
insert into usuario(username, email, password, role, account_expired, account_locked, credentials_expired, disabled) values ('ayrton.senna', 'senna@ada.tech', '$2a$10$JZaIcxUXT.IICzeLuIZCreXX.eGu6j4wkUFzAAz7hn3ZY15eqJKgu', 'ADMIN', false, false, false, false);
insert into usuario(username, email, password, role, account_expired, account_locked, credentials_expired, disabled) values ('alain.prost', 'prof.prost@ada.tech', '$2a$10$WDI2n9D/noOFmatDYrc98eZK8mYyWvwc9X5OzmP62se/sqOEkDoEu', 'USER', false, false, false, false);