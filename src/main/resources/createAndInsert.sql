CREATE TABLE node
(
    node_id       SERIAL8 PRIMARY KEY,
    question_text TEXT,
    animal_name   TEXT,
    is_leaf       boolean
);

CREATE TABLE node_relations
(
    parent_id BIGINT REFERENCES node (node_id),
    yes_child BIGINT REFERENCES node (node_id),
    no_child  BIGINT REFERENCES node (node_id)
);


INSERT INTO node(question_text, animal_name, is_leaf)
VALUES ('живет на суше', null, false);

INSERT INTO node
(question_text, animal_name, is_leaf)
VALUES (null, 'кот', true), -- 1
       (null, 'кит', true); -- 2


INSERT INTO node_relations
values (1, (SELECT node_id FROM node where animal_name = 'кот'),
        (SELECT node_id FROM node where animal_name = 'кит'));

