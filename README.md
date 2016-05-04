uruchomienie aplikacji:

mvn jetty:run

aplikacja dostepna pod adresem:
http://localhost:8080/application/

Moim zdaniem w specyfikacji jest sprzecznosc - z jednej strony podana jest mozliwosc przegladana wnioskow z paginacja - typowa funkcja GUI, zas z drugiej strony podano, zeby nie tworzyc GUI.
W zwiazku z tym przygotowalem GUI (w minimalnej formie przy wykorzystaniu gotowych komponentow). Uproscilo to takze tworzenie wnioskow.

Dodatkowe zalozenia:

1. Nie podano jak ma byc generowany unikalny numer opublikowanego wniosku, dlatego ustawiany jest ten sam co id wniosku (unikalnosc id jest zagwarantowana przez baze danych/jpa)

2. Nie podano, ze tytul wniosku nie moze byc modyfikowany, dlatego jest edytowalny w kazdym stanie (za wyjatkiem koncowych)

3. Podanie powodu odrzucenia i usuniecia jest mozliwe w dowolnym momencie, zas w podanych zmianach stanu jest obowiazkowe.

4. Wnioski w stanach koncowych (PUBLISHED, REJECTED, DELETED) sa calkowicie niemodyfikowalne

Endpointy REST sa dostepne jako REST i XML - dla prostoty podgladu/testu
Testow nie przygotowalem z braku czasu

