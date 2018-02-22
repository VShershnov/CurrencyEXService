# CurrencyEXService
Currency Exchange service : collect course, store and send to user by date param in url

# How to install

For store currency rate data CurrencyEXService use Postgresql DB.

Using app pgAdmin4 create CurrencyRate DB

And on it Restore menu choose "createDBstructure" dump file.


..Or use commandline 
pg_restore -U postgres -d CurrencyRate -C createDBstructure.dump


For install project use Maven :
From project folder ( you can use "cd CurrencyEXService to enter to project folder ) use maven command:
mvn clean package

It compiles, run package the project into a war file and put it in the project/target folder.
Deploy war file to Aplication Web Server like Apache Tomcat

# How to Use
Start Service by url (Spider collect data every 12H)
http://localhost:8080/CurrencyEXService/

Its star Spider that collect currency rate from NBU public API
https://bank.gov.ua/control/uk/publish/article?art_id=38441973


For get current currency rate json Use GET request
http://localhost:8080/CurrencyEXService/rate/eur/uah/

For get current currency rate json Use GET request
http://localhost:8080/CurrencyEXService/rate/eur/uah/2018-02-25/

If currency rate for date you choose doesnt exist you received rate for nearest date

#
You can also manually stop 
http://localhost:8080/CurrencyEXService/spiders/stop

and start Spider again
http://localhost:8080/CurrencyEXService/spiders/start

# Responce JSON format
