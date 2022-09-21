-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Сен 21 2022 г., 10:05
-- Версия сервера: 10.4.22-MariaDB
-- Версия PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `springproj`
--

-- --------------------------------------------------------

--
-- Структура таблицы `actor`
--

CREATE TABLE `actor` (
  `id` bigint(20) NOT NULL,
  `actor_name` varchar(100) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `performance_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `actor`
--

INSERT INTO `actor` (`id`, `actor_name`, `role`, `performance_id`) VALUES
(20, 'Vaaan', 'Slav', 11);

-- --------------------------------------------------------

--
-- Структура таблицы `coupon`
--

CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL,
  `active_until` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `coupon`
--

INSERT INTO `coupon` (`id`, `active_until`, `status`, `user_id`) VALUES
(34, '2222-02-22', 'ACTIVE', 27);

-- --------------------------------------------------------

--
-- Структура таблицы `director`
--

CREATE TABLE `director` (
  `id` bigint(20) NOT NULL,
  `birth_date` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `patronymic` varchar(255) DEFAULT NULL,
  `surname` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `director`
--

INSERT INTO `director` (`id`, `birth_date`, `name`, `patronymic`, `surname`) VALUES
(5, '5678-12-31', 'sssss', '', 'aaaaa');

-- --------------------------------------------------------

--
-- Структура таблицы `equipment`
--

CREATE TABLE `equipment` (
  `id` bigint(20) NOT NULL,
  `equipment_name` varchar(100) DEFAULT NULL,
  `performance_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `equipment`
--

INSERT INTO `equipment` (`id`, `equipment_name`, `performance_id`) VALUES
(22, 'Палка', 6);

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE `genre` (
  `id` bigint(20) NOT NULL,
  `genre_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `genre`
--

INSERT INTO `genre` (`id`, `genre_name`) VALUES
(4, '11');

-- --------------------------------------------------------

--
-- Структура таблицы `hall`
--

CREATE TABLE `hall` (
  `id` bigint(20) NOT NULL,
  `hall_number` int(11) NOT NULL,
  `spots_amount` int(11) NOT NULL,
  `hall_type_id` bigint(20) NOT NULL,
  `theatre_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `hall`
--

INSERT INTO `hall` (`id`, `hall_number`, `spots_amount`, `hall_type_id`, `theatre_id`) VALUES
(18, 6, 100, 15, 16);

-- --------------------------------------------------------

--
-- Структура таблицы `hall_type`
--

CREATE TABLE `hall_type` (
  `id` bigint(20) NOT NULL,
  `hall_type_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `hall_type`
--

INSERT INTO `hall_type` (`id`, `hall_type_name`) VALUES
(15, '12');

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(35);

-- --------------------------------------------------------

--
-- Структура таблицы `performance`
--

CREATE TABLE `performance` (
  `id` bigint(20) NOT NULL,
  `duration` int(11) NOT NULL,
  `performance_name` varchar(255) DEFAULT NULL,
  `director_id` bigint(20) NOT NULL,
  `genre_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `performance`
--

INSERT INTO `performance` (`id`, `duration`, `performance_name`, `director_id`, `genre_id`) VALUES
(6, 12, '121', 5, 4),
(7, 111, '1212121212', 5, 4),
(11, 111, 'as', 5, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `session`
--

CREATE TABLE `session` (
  `id` bigint(20) NOT NULL,
  `cost` int(11) NOT NULL,
  `datetime` varchar(255) NOT NULL,
  `hall_id` bigint(20) NOT NULL,
  `performance_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `session`
--

INSERT INTO `session` (`id`, `cost`, `datetime`, `hall_id`, `performance_id`) VALUES
(25, 123, '1111-11-11T11:11', 18, 11);

-- --------------------------------------------------------

--
-- Структура таблицы `theatre`
--

CREATE TABLE `theatre` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `halls_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `theatre`
--

INSERT INTO `theatre` (`id`, `address`, `halls_amount`) VALUES
(16, 'asd', 12);

-- --------------------------------------------------------

--
-- Структура таблицы `ticket`
--

CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL,
  `row` int(11) NOT NULL,
  `spot` int(11) NOT NULL,
  `session_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `ticket`
--

INSERT INTO `ticket` (`id`, `row`, `spot`, `session_id`, `user_id`) VALUES
(32, 2, 1, 25, 27);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `password`, `username`, `balance`) VALUES
(27, b'1', '1', 'Alex', NULL),
(28, b'1', '5', '5', NULL),
(29, b'1', '$2a$08$ssJRTs71vMqZJXhzeOTt9exi0fCgQtGF9bb8G6Wy5uZ4azGzGBsfK', '', NULL),
(30, b'1', '$2a$08$ZZ3zT62mqza5XZvsGkc8HOdQXwnsIgGe2XLjDdthYPjWIomlltX8S', '1', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
(27, 'USER'),
(28, 'USER'),
(29, 'USER'),
(30, 'USER');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeotr15qrs03ybe3oogx0xhqyl` (`performance_id`);

--
-- Индексы таблицы `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmfuic7ht7p0xvyoxhq9oydhal` (`user_id`);

--
-- Индексы таблицы `director`
--
ALTER TABLE `director`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaj8hicsa7b2ufujstebq9ive4` (`performance_id`);

--
-- Индексы таблицы `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `hall`
--
ALTER TABLE `hall`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhr3ni8i6j3mmde9d41ng4qybn` (`hall_type_id`),
  ADD KEY `FKrec44284isyq7riq9u6dbdsje` (`theatre_id`);

--
-- Индексы таблицы `hall_type`
--
ALTER TABLE `hall_type`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `performance`
--
ALTER TABLE `performance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4ke0we0mc5ct1dwsk5yxh7lvn` (`director_id`),
  ADD KEY `FKb9ssna4blqa6jlfxbqc9fpylr` (`genre_id`);

--
-- Индексы таблицы `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdkbjq18dsn41qptljosa4cxk1` (`hall_id`),
  ADD KEY `FKt978i3v7f9cm53o3qrsu94yy6` (`performance_id`);

--
-- Индексы таблицы `theatre`
--
ALTER TABLE `theatre`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5x0dvk4itqbdpu2dxdo02b5f7` (`session_id`),
  ADD KEY `FKdvt57mcco3ogsosi97odw563o` (`user_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `actor`
--
ALTER TABLE `actor`
  ADD CONSTRAINT `FKeotr15qrs03ybe3oogx0xhqyl` FOREIGN KEY (`performance_id`) REFERENCES `performance` (`id`);

--
-- Ограничения внешнего ключа таблицы `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `FKmfuic7ht7p0xvyoxhq9oydhal` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `FKaj8hicsa7b2ufujstebq9ive4` FOREIGN KEY (`performance_id`) REFERENCES `performance` (`id`);

--
-- Ограничения внешнего ключа таблицы `hall`
--
ALTER TABLE `hall`
  ADD CONSTRAINT `FKhr3ni8i6j3mmde9d41ng4qybn` FOREIGN KEY (`hall_type_id`) REFERENCES `hall_type` (`id`),
  ADD CONSTRAINT `FKrec44284isyq7riq9u6dbdsje` FOREIGN KEY (`theatre_id`) REFERENCES `theatre` (`id`);

--
-- Ограничения внешнего ключа таблицы `performance`
--
ALTER TABLE `performance`
  ADD CONSTRAINT `FK4ke0we0mc5ct1dwsk5yxh7lvn` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`),
  ADD CONSTRAINT `FKb9ssna4blqa6jlfxbqc9fpylr` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`);

--
-- Ограничения внешнего ключа таблицы `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `FKdkbjq18dsn41qptljosa4cxk1` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`),
  ADD CONSTRAINT `FKt978i3v7f9cm53o3qrsu94yy6` FOREIGN KEY (`performance_id`) REFERENCES `performance` (`id`);

--
-- Ограничения внешнего ключа таблицы `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK5x0dvk4itqbdpu2dxdo02b5f7` FOREIGN KEY (`session_id`) REFERENCES `session` (`id`),
  ADD CONSTRAINT `FKdvt57mcco3ogsosi97odw563o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
