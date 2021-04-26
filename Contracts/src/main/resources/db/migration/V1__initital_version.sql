CREATE TABLE agreement
(
    id              integer NOT NULL,
    start_date      date,
    end_date        date,
    contract_num    integer,
    person_id       integer,
    name            character varying(50),
    birthday        date,
    gender          character varying(10),
    pass_num_series integer,
    type            character varying(20),
    addition_info   text,
    CONSTRAINT contract PRIMARY KEY (id)
);
