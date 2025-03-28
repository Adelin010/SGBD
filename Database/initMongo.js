/**
 * Executing file for initiate the database
 * Consider that the  Database exists already
 */
const URL_MONGO = "mongodb://localhost:27017/BankSystem"
db = connect(URL_MONGO)

db.users.insertMany([{
    "name": "Carolin",
    "country_code": "FR",
    "birthday": new Date("01-10-1990"),
    "age": 34
}, {
    "name": "Alexander Bolsky",
    "country_code": "GE",
    "birthday": new Date("21-02-19"),
    "age": 27
}, {
    "name": "Ivanov",
    "country_code": "RU",
    "birthday": new Date("01-01-1980"),
    "age": 45

}, {
    "name": "Mahdi",
    "country_code": "ID",
    "birthday": new Date("23-06-1999"),
    "age": 25
}])


