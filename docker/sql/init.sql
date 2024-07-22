create table pet_categories
(
    id   serial primary key,
    name varchar(255) not null
);

create table pets
(
    id          serial primary key,
    name        varchar(255) not null,
    status      varchar(255) not null,
    category_id bigint       not null
        constraint pets_category_id_fkey
            references pet_categories,
    description varchar(255),
    birth_date  date,
    image_url   varchar(255)
);

INSERT INTO pet_categories (id, name)
VALUES (1, 'Dog');
INSERT INTO pet_categories (id, name)
VALUES (2, 'Cat');

INSERT INTO pets (birth_date, description, image_url, name, status, category_id)
VALUES ('2010-01-01', 'Golden Retriever',
        'https://love.doghero.com.br/wp-content/uploads/2018/03/shutterstock_272874407.jpg', 'Buddy', 'AVAILABLE', 1),
       ('2015-01-01', 'Siamese',
        'https://cdn.pixabay.com/photo/2024/02/28/07/42/european-shorthair-8601492_1280.jpg', 'Kitty', 'ADOPTED', 2);