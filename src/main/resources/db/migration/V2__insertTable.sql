INSERT INTO users(id,email,is_account_non_expired,is_account_non_locked,
                  is_credentials_non_expired, is_enabled,password,role) values(1,'admin@gmail.com',true ,true, true,true,'1234567','ADMIN');

INSERT INTO admins(id,first_name,last_name,user_id) VALUES (1,'Nurisa','Mamiraimova',1);