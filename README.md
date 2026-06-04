# Проект по автоматизации тестирования DaData

Учебный дипломный проект с UI- и API-автотестами для сервиса [DaData](https://dadata.ru/).

Проект демонстрирует автоматизацию проверок публичного сайта DaData и реальных API-методов DaData Cleaner API и Suggestions API. API-тесты используют REST Assured, модели request/response, переиспользуемые specifications, JSON Schema validation и кастомные шаблоны Allure REST Assured listener.

## Содержание

- [Технологии и инструменты](#технологии-и-инструменты)
- [Что проверяется](#что-проверяется)
- [Покрытие требований диплома](#покрытие-требований-диплома)
- [Структура проекта](#структура-проекта)
- [Запуск тестов](#запуск-тестов)
- [Параметры запуска](#параметры-запуска)
- [Allure отчет](#allure-отчет)
- [Ручные тест-кейсы](#ручные-тест-кейсы)
- [GitHub](#github)

## Технологии и инструменты

| Инструмент | Назначение |
| --- | --- |
| Java | язык автотестов |
| Gradle | сборка и запуск тестов |
| JUnit 5 | тестовый фреймворк |
| Selenide | UI-автоматизация |
| REST Assured | API-автоматизация |
| Allure Report | отчетность |
| Allure REST Assured | вложения request/response в отчет |
| Lombok | подключен для моделей и сокращения шаблонного кода |
| AssertJ | fluent assertions |
| JSON Schema Validator | проверка контрактов API |
| DataFaker | генерация тестовых данных |

Модели request/response реализованы через Java `record`: это компактный immutable-формат DTO. Lombok подключен в проекте и доступен для расширения моделей, но для текущих DTO `record` закрывает ту же задачу без boilerplate-кода.

## Что проверяется

### UI-тесты

- Главная страница отображает основной заголовок и CTA-кнопки.
- Хедер содержит основные пункты меню и иконку поддержки.
- Кнопка `Войти` открывает форму авторизации.
- Кнопка `Подключиться` открывает форму регистрации.
- Кнопка `Присоединиться` открывает форму регистрации.
- Меню `Продукты` содержит основные продукты DaData.

### API-тесты

- `POST /api/v1/clean/NAME` нормализует ФИО.
- `POST /api/v1/clean/NAME` без авторизационных заголовков возвращает ошибку авторизации.
- `POST /api/v1/clean/PHONE` приводит телефон к международному формату.
- `POST /api/v1/clean` возвращает структурированную запись с очищенным ФИО.
- `POST /api/4_1/rs/suggest/address` возвращает релевантные подсказки адресов.
- `POST /api/4_1/rs/suggest/email` возвращает варианты email по неполному адресу.

## Покрытие требований диплома

| Требование | Где реализовано |
| --- | --- |
| API-проект с README | `README.md` |
| Модели для body POST-запросов | `src/test/java/models/cleaner/RecordBodyModel.java`, `src/test/java/models/suggestions/SuggestRequestModel.java` |
| Модели для десериализации ответа | `src/test/java/models/cleaner`, `src/test/java/models/suggestions` |
| REST Assured specifications | `src/test/java/specs` |
| Allure REST Assured listener | `src/test/java/allure/CustomAllureListener.java` |
| Custom templates для Allure | `src/test/resources/tpl/request.ftl`, `src/test/resources/tpl/response.ftl` |
| Авторизация API | API-клиенты передают `Authorization` и `X-Secret` из системных параметров |
| API-тесты | `src/test/java/tests/api` |
| UI-тесты | `src/test/java/tests/ui` |
| Ручные тесты | `src/test/manual-test-cases.md` |

## Структура проекта

```text
src/test/java
├── allure       # Allure attachments и REST Assured listener
├── api          # API-клиенты DaData
├── models       # request/response модели
├── pages        # page objects и компоненты UI
├── specs        # REST Assured specifications
└── tests
    ├── api      # API-тесты
    └── ui       # UI-тесты

src/test/resources
├── schemas      # JSON-схемы ответов API
└── tpl          # custom templates для Allure REST Assured
```

## Запуск тестов

Запуск всех тестов:

```bash
./gradlew test
```

Запуск только UI-тестов:

```bash
./gradlew uiTests
```

Запуск только API-тестов:

```bash
./gradlew apiTests -Ddadata.api.token=TOKEN -Ddadata.api.secret=SECRET
```

API-тесты используют реальные вызовы к DaData. Если обязательные параметры не переданы, тесты пропускаются через JUnit assumptions:

- Cleaner API требует `dadata.api.token` и `dadata.api.secret`.
- Suggestions API требует `dadata.api.token`.

## Параметры запуска

### UI

Локальный запуск:

```bash
./gradlew uiTests -Dbrowser=chrome -DbrowserSize=1920x1080 -Dheadless=true
```

Удаленный запуск через Selenoid:

```bash
./gradlew uiTests \
  -DloginSelenoid=LOGIN \
  -DpasswordSelenoid=PASSWORD \
  -DurlSelenoid=HOST/wd/hub
```

### API

```bash
./gradlew apiTests \
  -Ddadata.api.token=TOKEN \
  -Ddadata.api.secret=SECRET
```

## Allure отчет

Сформировать отчет:

```bash
./gradlew allureReport
```

Открыть отчет:

```bash
./gradlew allureServe
```

В Allure-отчет добавляются:

- названия и описания тестов;
- severity;
- шаги проверок;
- HTTP request/response для API-тестов через кастомные шаблоны;
- UI attachments при падениях тестов.

## Ручные тест-кейсы

Ручные тест-кейсы вынесены в отдельный файл: [src/test/manual-test-cases.md](src/test/manual-test-cases.md).

В документе описаны UI- и API-проверки с предусловиями, шагами и ожидаемыми результатами.

## GitHub

Профиль автора: [klevtsovamariya](https://github.com/klevtsovamariya)

Рекомендованный блок для профильного README GitHub:

```markdown
### My projects

- [DaData UI and API autotests](https://github.com/klevtsovamariya/diplom_dadata) - дипломный проект с UI- и API-автотестами для DaData
- [UI autotests project](ССЫЛКА_НА_UI_ПРОЕКТ) - проект UI-автоматизации
- [Manual testing documentation](ССЫЛКА_НА_РУЧНЫЕ_ТЕСТЫ) - тестовая документация и ручные тест-кейсы
```
