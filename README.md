# data-collector-15

Программа, собирающая данные из разных источников

Line - Класс линии московского метро (имя и номер линии).

Station - Класс станции московского метро (имя станции и номер линии).

ParsingHTML - Класс парсинга веб-страницы.
•	получение HTML-кода страницы «Список станций Московского метрополитена» https://skillbox-java.github.io/ с помощью библиотеки jsoup
•	парсинг полученной страницы и получение из неё следующих данных (для каждого типа данных отдельные классы: Line и Station).

FileSearch - Класс поиска файлов в папках.
В методах этого класса необходимо реализовать обход папок, лежащих в архиве https://drive.google.com/file/d/1nb3NIfsIp7KLF0OfmZ_nzwYQLM9zfDUg/view?usp=sharing
Разархивируйте его и напишите код, который будет обходить все вложенные папки и искать в них файлы форматов JSON и CSV (с расширениями *.json и *.csv). 
Метод для обхода папок должен принимать путь до папки, в которой надо производить поиск

ParsingJSON - Класс парсинга файлов формата JSON.
Изучите структуру JSON-файлов, лежащих в папках, и создайте класс(ы) для хранения данных из этих файлов. 
Напишите код, который будет принимать JSON-данные и выдавать список соответствующих им объектов.

ParsingCSV - Класс парсинга файлов формата CSV.
Изучите структуру CSV-файлов, лежащих в папках, и создайте класс(ы) для хранения данных из этих файлов. 
Напишите код, который будет принимать CSV-данные и выдавать список соответствующих им объектов.