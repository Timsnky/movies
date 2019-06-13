INSERT INTO `role` (`id`, `name`)
VALUES
	(1, 'Admin'),
	(2, 'Manager'),
	(3, 'User');

INSERT INTO `rating` (`id`, `rating`)
VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5);

INSERT INTO `user` (`id`, `email`, `firstname`, `lastname`, `password`)
VALUES
	(1, 'timsnky@gmail.com', 'Timothy', 'Kimathi', '$2a$10$jMK6AfI5f0kRkLDExnj20eJhomDsBmZRvnW1xYd0Zyn8PrPjYvJiq');

INSERT INTO `user_roles` (`user_id`, `roles_id`)
VALUES
	(1, 3);
