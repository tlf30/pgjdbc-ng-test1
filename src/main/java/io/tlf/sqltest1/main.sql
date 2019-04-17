/**
 * Author:  Trevor Flynn
 * Created: Apr 17, 2019
 */
CREATE TYPE RANGE AS (
    min BIGINT,
    max BIGINT,
    type VARCHAR(100),
    constructors INT[]
);

CREATE TYPE STAT AS (
    name VARCHAR(100),
    display VARCHAR(100),
    xp BIGINT,
    value REAL,
    min REAL,
    max REAL,
    ranges RANGE[],
    visible BOOLEAN
);

CREATE TYPE CHARA AS (
    stats STAT[]
);

CREATE TABLE users (
    id SERIAL,
    data CHARA,
    PRIMARY KEY (id)
);

ALTER TABLE users OWNER TO test1;

/* create test data */
INSERT INTO users (data) VALUES (
    ROW(
        array[              
            ROW('a', 'AA', 0, 1.0, 0.0, 1.0, array[ROW(0::BIGINT, 1000::BIGINT, 'aaa'::VARCHAR, array[1, 0]::INT[])::RANGE], true)::STAT,
            ROW('b', 'BB', 0, 1.0, 0.0, 1.0, array[ROW(0::BIGINT, 1000::BIGINT, 'bbb'::VARCHAR, array[1, 0]::INT[])::RANGE], true)::STAT,
            ROW('c', 'CC', 0, 1.0, 0.0, 1.0, array[ROW(0::BIGINT, 1000::BIGINT, 'ccc'::VARCHAR, array[1, 0]::INT[])::RANGE], true)::STAT
        ]  /* stats STAT[]   */
    )
);
