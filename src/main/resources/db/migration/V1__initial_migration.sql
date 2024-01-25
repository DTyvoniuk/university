CREATE TABLE IF NOT EXISTS university.department
(
    creation_timestamp timestamp with time zone,
    id                 uuid not null primary key,
    name               varchar
);

CREATE TABLE IF NOT EXISTS university.lector
(
    degree             varchar,
    creation_timestamp timestamp with time zone,
    id                 uuid not null primary key,
    full_name          varchar
);

CREATE TABLE IF NOT EXISTS university.lector_department
(
    department_id uuid not null constraint fkgpj93euy0mmypskoe1f45dtpv references department,
    lector_id     uuid not null constraint fkkjoq79x3504irbh2a9waay382 references lector,
    creation_timestamp timestamp with time zone,
    primary key (department_id, lector_id)
);
