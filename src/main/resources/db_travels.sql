CREATE SCHEMA `db_travels` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;



CREATE TABLE `coordinates` (
  `id` bigint(20) NOT NULL,
  `latitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `longitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `places` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  `sub_place_id` int(11) NOT NULL,
  `coordinates_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbec1sdhw7xy3kas7rs9ag07te` (`coordinates_id`),
  CONSTRAINT `FKbec1sdhw7xy3kas7rs9ag07te` FOREIGN KEY (`coordinates_id`) REFERENCES `coordinates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `topics` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sub_topics_id` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `create_at` date DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `articles` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `places_id` bigint(20) DEFAULT NULL,
  `topics_id` bigint(20) DEFAULT NULL,
  `users_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7b843ueq7u8o2jbbpnnl3knqk` (`places_id`),
  KEY `FKi6prltuwudqmd7lo0tdqaodvs` (`topics_id`),
  KEY `FKltflfjxhtxmxchtvgsl0avvf9` (`users_id`),
  CONSTRAINT `FK7b843ueq7u8o2jbbpnnl3knqk` FOREIGN KEY (`places_id`) REFERENCES `places` (`id`),
  CONSTRAINT `FKi6prltuwudqmd7lo0tdqaodvs` FOREIGN KEY (`topics_id`) REFERENCES `topics` (`id`),
  CONSTRAINT `FKltflfjxhtxmxchtvgsl0avvf9` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci



CREATE TABLE `feedbacks` (
  `id` bigint(20) NOT NULL,
  `creat_at` date DEFAULT NULL,
  `heart` int(11) NOT NULL,
  `review` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `share` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `articles_id` bigint(20) DEFAULT NULL,
  `users_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1vl4r2dllohlt0bl09m9rs5pn` (`articles_id`),
  KEY `FKdf0gygvywalbg1a6lta0u8g7m` (`users_id`),
  CONSTRAINT `FK1vl4r2dllohlt0bl09m9rs5pn` FOREIGN KEY (`articles_id`) REFERENCES `articles` (`id`),
  CONSTRAINT `FKdf0gygvywalbg1a6lta0u8g7m` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `itineraries` (
  `id` bigint(20) NOT NULL,
  `date_end` date DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `position` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `articles_id` bigint(20) DEFAULT NULL,
  `users_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcow9ni7cfqqtlaeirm2so2igr` (`articles_id`),
  KEY `FKcme9in7hwm64xnbigvnw4wuu5` (`users_id`),
  CONSTRAINT `FKcme9in7hwm64xnbigvnw4wuu5` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKcow9ni7cfqqtlaeirm2so2igr` FOREIGN KEY (`articles_id`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `likes` (
  `id` bigint(20) NOT NULL,
  `creat_at` date DEFAULT NULL,
  `status` int(11) NOT NULL,
  `articles_id` bigint(20) DEFAULT NULL,
  `users_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9l18kiqkb3fip1d6ejyq2nr2i` (`articles_id`),
  KEY `FKhdc210nkt0hdt4e8242qr08c` (`users_id`),
  CONSTRAINT `FK9l18kiqkb3fip1d6ejyq2nr2i` FOREIGN KEY (`articles_id`) REFERENCES `articles` (`id`),
  CONSTRAINT `FKhdc210nkt0hdt4e8242qr08c` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `creat_at` date DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `topics_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ms58abmos751qf580ciy3tu` (`topics_id`),
  CONSTRAINT `FK4ms58abmos751qf580ciy3tu` FOREIGN KEY (`topics_id`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `itinerary_articles` (
  `id` bigint(20) NOT NULL,
  `articles_id` bigint(20) DEFAULT NULL,
  `itineraries_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKox7qjjy6e9gg1nt20vdm1650r` (`articles_id`),
  KEY `FKjtxi18dg78jwtffdecekppksa` (`itineraries_id`),
  CONSTRAINT `FKjtxi18dg78jwtffdecekppksa` FOREIGN KEY (`itineraries_id`) REFERENCES `itineraries` (`id`),
  CONSTRAINT `FKox7qjjy6e9gg1nt20vdm1650r` FOREIGN KEY (`articles_id`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci







