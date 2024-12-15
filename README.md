 # MFAlpha
#### Приложение, призванное сохранить здоровый сон на парах

## Инструкция по установке
#### 1. Перейдите в [Releases](https://github.com/mynameisasskiss/MFAlpha/releases)
#### 2. Установите jar-файл в любое удобное место
#### 3. Установите WebDriver(инструкция ниже)
#### 4. Откройте терминал и введите команду:
```java -jar <Путь-к-файлу>/MoodleFaqer.java```
#### Если что, можно вместо прописывания пути к файлу просто перетащить его в терминал

## ~~Установка под Intellij IDEA~~ Теперь установка гораздо проще. Инструкция выше
#### 0. Процесс установки может *слегка* отличаться в зависимости от вашей системы
#### 1. В меню IDE(на Windows выглядит так: ≡) выберите *File* -> *New* -> *Project from Version Control...*
<img width="655" alt="Снимок экрана 2024-12-12 в 17 14 27" src="https://github.com/user-attachments/assets/320e400c-695a-4eea-9335-a2f134c57ba5" />

#### 2. В открывшемся меню введите ссылку на этот репозиторий как показано ниже и нажмите *Clone*
<img width="912" alt="Снимок экрана 2024-12-12 в 17 20 09" src="https://github.com/user-attachments/assets/09baba7b-aff2-42db-8e29-268c72202440" />

#### 2.1. Если вылезет предупреждение - смело жмите *This window*
#### 3. В меню IDE(см. п.1) выберите *File* -> *Project Structure...*
<img width="389" alt="Снимок экрана 2024-12-12 в 17 22 56" src="https://github.com/user-attachments/assets/76468212-4555-4a2c-bbd4-054cd4ab72c6" />

#### 4. В открывшемся окне слева выберите *Libraries*, нажмите на плюсик и выберите *From Maven*
<img width="1019" alt="Снимок экрана 2024-12-12 в 17 24 59" src="https://github.com/user-attachments/assets/523ecc2c-a1ca-4e5f-93a2-cd6d60e88dbc" />
<img width="318" alt="Снимок экрана 2024-12-12 в 17 28 19" src="https://github.com/user-attachments/assets/8a73e98e-3bea-4049-b3eb-015d2bc63e7d" />

#### 5. Перед вами поле ввода. Введите ```google.code.gson```, нажмите на лупу и выберите самый верхний пункт(номер версии может отличаться от той, что на скриншоте). Дважды надмите *ОК*. Пока окно не закрывайте - дальше оно вам понадобится.
<img width="621" alt="Снимок экрана 2024-12-12 в 17 33 30" src="https://github.com/user-attachments/assets/f9d1e230-a32b-49a3-ae39-5d91681c625a" />

#### 6. Повторите действия из пункта 4, но теперь выберите *Java*. В открывшемся меню выбора файла выберите папку Selenium Java 4.27.0 в корне папки проекта
<img width="345" alt="Снимок экрана 2024-12-12 в 17 40 11" src="https://github.com/user-attachments/assets/ba3e0839-bd67-4c65-8beb-3ea2ed89b056" />

#### 7. Намите ОК и закройте окно *Project Structure*
<img width="549" alt="Снимок экрана 2024-12-12 в 17 41 56" src="https://github.com/user-attachments/assets/e20d8eaa-2ed9-46be-af0c-8ff301a67191" />

#### 8. Установите WebDriver(инструкция ниже)

## Установка WebDriver

#### 1. Скачайте WebDriver с [сайта](https://googlechromelabs.github.io/chrome-for-testing/) и нажмите на кнопку *Stable* и ищите пункт *Chromedriver* под вашу ОС
#### Для Windows
<img width="272" alt="Снимок экрана 2024-12-12 в 17 54 08" src="https://github.com/user-attachments/assets/f9fef640-1190-45d9-9857-200e56cfe4c1" />


#### Для Mac на M-процессорах
<img width="274" alt="Снимок экрана 2024-12-12 в 17 53 52" src="https://github.com/user-attachments/assets/738d4158-312a-4e2f-9a5e-31d5afc6fed3" />


##### Справа от надписей как на рисунке должна быть ссылка(чтобы выделить ее кликните мышью). Вставьте ее в новую вкладку.

#### 2. У вас загрузится архив, в котором будет файл chromedriver
#### Для Windows
##### Переместите этот файл в директорию C:/Windows/System32
##### Для удобства есть команда ``` move chromedriver.exe C:/Windows/System32``` (для cmd от имени администратора)
#### Для Mac
##### Переместите этот файл в /usr/local/bin
##### Для удобства есть команда ``` mv chromedriver /usr/local/bin```

#### 3. Вот и все. Если у вас все получилось - вы умничка, если не получилось - перечитайте эту инструкцию и попробуйте снова.

## Требования
#### - Человек с развивающимися навыками ручного труда
#### – JDK 21
#### ~~– IDE(желательно Intellij IDEA)~~
#### - Наличие доступа в интернет
