GeekHubFeedReader
GeekHubAndroidFeedReader

https://docs.google.com/file/d/0Bx9uMmtF66MWWXJGbFJmdmJNZ1U/edit

Не реализовано: 
1) Твиттер 
2) Удаление из базы данных (по этому поводу будет вопрос)

Глюки: 
1) Могут быть глюки с поворотом экрана (по этому поводу будет вопрос) 2) При первом старте нету спинера (индикатора загрузки), поэтому нужно подождать 3) Есть момент с перезапуском потока обновления (по этому поводу тоже будет вопрос)

Реализовано: 1) Сервис живет все время, раз в час проверяет обновление по ETag 2) БродкастРесивер, при поднятии ВайФая, если приложение запушенно - посылает запрос на проверку обновлений 3) База данных, добавление уникальны. Лайкнутые статьи можно просматривать отдельно как и было в задании. 4) На девайсах с размерами large и xlarge в landscape показывается два фрагмента 5) Фейсбук, регистрация и пост на стену. 6) Нотификация, если приложение запущенно, а сервис нашел обновления. 7) Если нет ВайФая - сообщение "No connection" 8) АкшенбарШерлок, на Андроид 2.2 приложение работает.

В планах: 1) Пофиксить баги и глюки. 2) Добавить твиттер. 3) Добавить удаление из БД. 4) Прикрутить слайдингМеню (Думаю, что с ним будет удобнее работать).
