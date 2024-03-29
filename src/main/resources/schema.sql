create table if not exists clothing (
    id uuid primary key default gen_random_uuid(),
    status varchar,
    brand varchar,
    color varchar,
    condition varchar,
    created timestamp,
    updated timestamp
);