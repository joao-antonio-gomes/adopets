create table pet_categories
(
    id   bigint       not null
        primary key,
    name varchar(255) not null
);

create table pets
(
    birth_date  date,
    category_id bigint
        constraint fkbggeiiigyrj3oiui7ep7arhdo
            references pet_categories,
    id          bigint not null
        primary key,
    description varchar(255),
    image_url   varchar(255),
    name        varchar(255),
    status      varchar(255)
        constraint pets_status_check
            check ((status)::text = ANY ((ARRAY ['AVAILABLE'::character varying, 'ADOPTED'::character varying])::text[]))
    );

INSERT INTO pet_categories (id, name)
VALUES (1, 'Dog');
INSERT INTO pet_categories (id, name)
VALUES (2, 'Cat');

INSERT INTO pets (id, birth_date, description, image_url, name, status, category_id)
VALUES (1, '2010-01-01', 'Golden Retriever',
        'https://love.doghero.com.br/wp-content/uploads/2018/03/shutterstock_272874407.jpg', 'Buddy', 'AVAILABLE', 1),
       (2, '2015-01-01', 'Siamese',
        'https://cdn.pixabay.com/photo/2024/02/28/07/42/european-shorthair-8601492_1280.jpg', 'Kitty', 'ADOPTED', 2);