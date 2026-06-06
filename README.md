# Проект по автоматизации тестирования сервиса [DaData](https://dadata.ru/)

<!-- здесь будет баннер проекта -->
<!-- <p align="center">
<img src="images/logos/dadata_banner.png" alt="DaData" width="950"/>
</p> -->

# 📝 Содержание:

- [Стек](#стек)
- [Реализованные проверки](#реализованные-проверки)
- [Структура проекта](#структура-проекта)
- [Запуск тестов из терминала](#запуск-тестов-из-терминала)
- [Allure Report](#allure-report)
- [Уведомление в Telegram](#уведомление-в-telegram)

<a id="стек"></a>

## ☕ Стек:

![Java](images/logos/Java.png)![Gradle](images/logos/Gradle.png)![Rest-Assured](images/logos/Rest-Assured.png)![Selenide](images/logos/Selenide.png)![Intelij_IDEA](images/logos/Intelij_IDEA.png)![JUnit5](images/logos/JUnit5.png)![Jenkins](images/logos/Jenkins.png)![Selenoid](images/logos/Selenoid.png)![Allure_Report](images/logos/Allure_Report.png)![AllureTestOps](images/logos/AllureTestOps.png)

В проекте автотесты написаны на **Java**. Сборка — **Gradle**, тесты — **JUnit 5**.

Для UI используется **Selenide**, для API — **Rest Assured**. Отчёты формируются в **Allure Report**. Проверки в тестах — через **AssertJ**, тестовые данные генерируются **DataFaker**.

<a id="реализованные-проверки"></a>

## 📠 Реализованные проверки:

### UI-тесты

### Главная страница и кнопки на ней

### Меню в шапке сайта

### Форма входа и регистрации

### Меню «Продукты»

### API-тесты (Cleaner)

### Очистка ФИО

### Очистка телефона

### Запись с ФИО

### Запрос без авторизации

### API-тесты (Suggestions)

### Подсказки по адресу

### Подсказки по email

### Ручные тест-кейсы

Описание ручных проверок — в файле [manual-test-cases.md](src/test/manual-test-cases.md).

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

<!-- здесь будет скрин успешного запуска тестов из терминала -->
<!-- <p align="center">
<img src="images/screenshots/terminal_test_run.jpg" alt="Запуск тестов" width="850"/>
</p> -->

Для API-тестов нужны ключи DaData. Они лежат в файле `dadata-secret.properties` в домашней папке:

```
dadata.api.token=...
dadata.api.secret=...
```

Если ключей нет — api-тесты не запускаются.

<a id="allure-report"></a>

## 📊 Allure Report

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

<a id="уведомление-в-telegram"></a>

## <img alt="Telegram" height="25" src="images/logos/Telegram.png" width="25"/> Уведомление в Telegram

После прогона тестов в Jenkins в Telegram приходит сообщение с результатом сборки.

<p align="center">
<img src="images/screenshots/telegram_notification.png" alt="Telegram" width="850"/>
</p>
