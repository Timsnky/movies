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

INSERT INTO `movie` (`id`, `description`, `recommendation`, `title`, `watched`, `rating_id`, `user_id`)
VALUES
	(1, 'Avengers Description', 'Highly recomended', 'Avengers', 1, 1, 1),
	(2, 'Avengers Description1', 'Medium recomended', 'Avengers2', 0, 2, 1),
	(3, 'Avengers Description2', 'Lowly recomended', 'Avengers3', 1, 3, 1),
	(4, 'Avengers Description4', 'Lowly recomended', 'Avengers4', 0, 4, 1),
	(5, 'Avengers Description5', 'Lowly recomended', 'Avengers5', 1, 5, 1);

