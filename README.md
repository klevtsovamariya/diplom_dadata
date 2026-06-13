# Проект по автоматизации тестирования сервиса [DaData](https://dadata.ru/)

<p align="center">
<img src="images/logos/dadata_banner.svg" alt="DaData autotests" width="650"/>
</p>

# 📝 Содержание:

- [Стек](#стек)
- [Реализованные проверки](#реализованные-проверки)
- [Структура проекта](#структура-проекта)
- [Запуск тестов из терминала](#запуск-тестов-из-терминала)
- [Сборка в Jenkins](#сборка-в-jenkins)
- [Allure Report](#allure-report)
- [Allure TestOps](#allure-testops)
- [Уведомление в Telegram](#уведомление-в-telegram)

<a id="стек"></a>

## ☕ Стек:

[![Java](images/logos/Java.png)](https://www.java.com/)
[![Gradle](images/logos/Gradle.png)](https://gradle.org/)
[![Rest-Assured](images/logos/Rest-Assured.png)](https://rest-assured.io/)
[![Selenide](images/logos/Selenide.png)](https://selenide.org/)
[![Intelij_IDEA](images/logos/Intelij_IDEA.png)](https://www.jetbrains.com/idea/)
[![JUnit5](images/logos/JUnit5.png)](https://junit.org/junit5/)
[![Jenkins](images/logos/Jenkins.png)](https://www.jenkins.io/)
[![Selenoid](images/logos/Selenoid.png)](https://aerokube.com/selenoid/)
[![Allure_Report](images/logos/Allure_Report.png)](https://allurereport.org/)
[![AllureTestOps](images/logos/AllureTestOps.png)](https://qameta.io/)

В проекте автотесты написаны на **Java**. Сборка — **Gradle**, тесты — **JUnit 5**.

Для UI используется **Selenide**, для API — **Rest Assured**. Отчёты формируются в **Allure Report** и передаются в **Allure TestOps**. Проверки в тестах — через **AssertJ**, тестовые данные генерируются **DataFaker**.

<a id="реализованные-проверки"></a>

## 📠 Реализованные проверки:

### UI

- главная страница: заголовок и кнопки «Подключиться», «Присоединиться»
- меню в шапке и иконка у пункта «Поддержка»
- переключение вкладок подписок в разделе «Цены»
- поиск статей в разделе «Блог»
- форма входа по кнопке «Войти»
- формы регистрации по кнопкам «Подключиться» и «Присоединиться»
- список продуктов в меню «Продукты»

### API

- Cleaner: нормализация ФИО, телефона и email, AS_IS, запись с ФИО, запрос без авторизации
- Suggestions: подсказки по адресу и email

Ручные сценарии — в [manual-test-cases.md](src/test/manual-test-cases.md).

<a id="структура-проекта"></a>

## 📁 Структура проекта

```text
src/test/java
├── allure       # вложения и listener для Allure
├── api          # клиенты к API DaData
├── config       # настройки (ключи, url)
├── models       # модели запросов и ответов
├── pages        # page object'ы для UI
├── specs        # общие настройки REST Assured
└── tests
    ├── api      # API-тесты
    └── ui       # UI-тесты

src/test/resources
├── schemas      # json-схемы ответов API
├── tpl          # шаблоны request/response в Allure
└── application.properties
```

<a id="запуск-тестов-из-терминала"></a>

## 💻 Запуск тестов из терминала

Команды для Windows, запускать из корня проекта.

Все тесты:

```
gradlew.bat test
```

Только UI:

```
gradlew.bat test --tests "tests.ui.*"
```

Только API:

```
gradlew.bat test --tests "tests.api.*"
```

Для API-тестов нужны ключи DaData. Они лежат в файле `dadata-secret.properties` в домашней папке:

```
dadata.api.token=...
dadata.api.secret=...
```

Если ключей нет — api-тесты не запускаются.

<a id="сборка-в-jenkins"></a>

## [🔧 Сборка в Jenkins](https://jenkins.autotests.cloud/job/dadata_tests/)

После настройки Jenkins тесты можно запускать удалённо с параметрами браузера, версии браузера, размера окна и адреса Selenoid.

<a id="allure-report"></a>

## [📊 Пример Allure-отчёта](https://jenkins.autotests.cloud/job/dadata_tests/4/allure/)

Собрать отчёт:

```
gradlew.bat allureReport
```

Открыть в браузере:

```
gradlew.bat allureServe
```

### Основная страница отчёта

<p align="center">
<img src="images/screenshots/allure_main.jpg" alt="Allure Overview" width="850"/>
</p>

### Тест-кейсы

<p align="center">
<img src="images/screenshots/allure_tests.jpg" alt="Allure Tests" width="850"/>
</p>

### Графики

<p align="center">
<img src="images/screenshots/allure_graph.jpg" alt="Allure Graphs" width="850"/>
</p>

### Пример API-теста в отчёте

<p align="center">
<img src="images/screenshots/allure_api_request.jpg" alt="Allure API" width="850"/>
</p>

### Пример UI-теста в отчёте

<p align="center">
<img src="images/screenshots/allure_ui_test.png" alt="Allure UI" width="850"/>
</p>

<a id="allure-testops"></a>

## [<img alt="Allure TestOps" height="25" src="images/logos/AllureTestOps.png" width="25"/> Allure TestOps](https://allure.autotests.cloud/project/5248/launches)

Результаты автотестов передаются из Jenkins в Allure TestOps.

<p align="center">
<img src="images/screenshots/allure_testops.jpg" alt="Allure TestOps" width="850"/>
</p>

### Результаты тестов в Allure TestOps

<p align="center">
<img src="images/screenshots/allure_testops_results.jpg" alt="Allure TestOps Results" width="850"/>
</p>

<a id="уведомление-в-telegram"></a>

## <img alt="Telegram" height="25" src="images/logos/Telegram.png" width="25"/> Уведомление в Telegram

После прогона тестов в Jenkins в Telegram приходит сообщение с результатом сборки.

<p align="center">
<img src="images/screenshots/telegram_notification.png" alt="Telegram" width="450"/>
</p>
