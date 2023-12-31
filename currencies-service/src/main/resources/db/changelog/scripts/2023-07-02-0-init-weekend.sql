DROP TABLE IF EXISTS WEEKENDS cascade;

CREATE TABLE WEEKENDS (
                          ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                          CALENDAR_DATE DATE NULL,
                          IS_DAY_OFF BOOLEAN NULL,
                          CONSTRAINT PK_WEEKEND12345687 PRIMARY KEY (ID)
);

INSERT INTO WEEKENDS (CALENDAR_DATE, IS_DAY_OFF)
VALUES
    ('2022-12-01', false),
    ('2022-12-02', true),
    ('2022-12-03', true),
    ('2022-12-04', false),
    ('2022-12-05', false),
    ('2022-12-06', false),
    ('2022-12-07', false),
    ('2022-12-08', false),
    ('2022-12-09', false),
    ('2022-12-10', true),
    ('2022-12-11', true),
    ('2022-12-12', false),
    ('2022-12-13', false),
    ('2022-12-14', false),
    ('2022-12-15', false),
    ('2022-12-16', false),
    ('2022-12-17', true),
    ('2022-12-18', true),
    ('2022-12-19', false),
    ('2022-12-20', false),
    ('2022-12-21', false),
    ('2022-12-22', false),
    ('2022-12-23', false),
    ('2022-12-24', true),
    ('2022-12-25', true),
    ('2022-12-26', false),
    ('2022-12-27', false),
    ('2022-12-28', false),
    ('2022-12-29', false),
    ('2022-12-30', false),
    ('2022-12-31', true),
    ('2023-01-01', true),
    ('2023-01-02', true),
    ('2023-01-03', false),
    ('2023-01-04', false),
    ('2023-01-05', false),
    ('2023-01-06', false),
    ('2023-01-07', true),
    ('2023-01-08', true),
    ('2023-01-09', false),
    ('2023-01-10', false),
    ('2023-01-11', false),
    ('2023-01-12', false),
    ('2023-01-13', false),
    ('2023-01-14', true),
    ('2023-01-15', true),
    ('2023-01-16', false),
    ('2023-01-17', false),
    ('2023-01-18', false),
    ('2023-01-19', false),
    ('2023-01-20', false),
    ('2023-01-21', true),
    ('2023-01-22', true),
    ('2023-01-23', false),
    ('2023-01-24', false),
    ('2023-01-25', false),
    ('2023-01-26', false),
    ('2023-01-27', false),
    ('2023-01-28', true),
    ('2023-01-29', true),
    ('2023-01-30', false),
    ('2023-01-31', false),
    ('2023-02-01', false),
    ('2023-02-02', false),
    ('2023-02-03', false),
    ('2023-02-04', true),
    ('2023-02-05', true),
    ('2023-02-06', false),
    ('2023-02-07', false),
    ('2023-02-08', false),
    ('2023-02-09', false),
    ('2023-02-10', false),
    ('2023-02-11', true),
    ('2023-02-12', true),
    ('2023-02-13', false),
    ('2023-02-14', false),
    ('2023-02-15', false),
    ('2023-02-16', false),
    ('2023-02-17', false),
    ('2023-02-18', true),
    ('2023-02-19', true),
    ('2023-02-20', false),
    ('2023-02-21', false),
    ('2023-02-22', false),
    ('2023-02-23', false),
    ('2023-02-24', false),
    ('2023-02-25', true),
    ('2023-02-26', true),
    ('2023-02-27', false),
    ('2023-02-28', false),
    ('2023-03-01', false),
    ('2023-03-02', false),
    ('2023-03-03', false),
    ('2023-03-04', true),
    ('2023-03-05', true),
    ('2023-03-06', false),
    ('2023-03-07', false),
    ('2023-03-08', false),
    ('2023-03-09', false),
    ('2023-03-10', false),
    ('2023-03-11', true),
    ('2023-03-12', true),
    ('2023-03-13', false),
    ('2023-03-14', false),
    ('2023-03-15', false),
    ('2023-03-16', false),
    ('2023-03-17', false),
    ('2023-03-18', true),
    ('2023-03-19', true),
    ('2023-03-20', false),
    ('2023-03-21', false),
    ('2023-03-22', false),
    ('2023-03-23', false),
    ('2023-03-24', false),
    ('2023-03-25', true),
    ('2023-03-26', true),
    ('2023-03-27', false),
    ('2023-03-28', false),
    ('2023-03-29', false),
    ('2023-03-30', false),
    ('2023-03-31', false),
    ('2023-04-01', true),
    ('2023-04-02', true),
    ('2023-04-03', false),
    ('2023-04-04', false),
    ('2023-04-05', false),
    ('2023-04-06', false),
    ('2023-04-07', false),
    ('2023-04-08', true),
    ('2023-04-09', true),
    ('2023-04-10', false),
    ('2023-04-11', false),
    ('2023-04-12', false),
    ('2023-04-13', false),
    ('2023-04-14', false),
    ('2023-04-15', true),
    ('2023-04-16', true),
    ('2023-04-17', false),
    ('2023-04-18', false),
    ('2023-04-19', false),
    ('2023-04-20', false),
    ('2023-04-21', false),
    ('2023-04-22', true),
    ('2023-04-23', true),
    ('2023-04-24', false),
    ('2023-04-25', false),
    ('2023-04-26', false),
    ('2023-04-27', false),
    ('2023-04-28', false),
    ('2023-04-29', true),
    ('2023-04-30', true),
    ('2023-05-01', true),
    ('2023-05-02', false),
    ('2023-05-03', false),
    ('2023-05-04', false),
    ('2023-05-05', false),
    ('2023-05-06', true),
    ('2023-05-07', true),
    ('2023-05-08', true),
    ('2023-05-09', true),
    ('2023-05-10', false),
    ('2023-05-11', false),
    ('2023-05-12', false),
    ('2023-05-13', false),
    ('2023-05-14', true),
    ('2023-05-15', false),
    ('2023-05-16', false),
    ('2023-05-17', false),
    ('2023-05-18', false),
    ('2023-05-19', false),
    ('2023-05-20', true),
    ('2023-05-21', true),
    ('2023-05-22', false),
    ('2023-05-23', false),
    ('2023-05-24', false),
    ('2023-05-25', false),
    ('2023-05-26', false),
    ('2023-05-27', true),
    ('2023-05-28', true),
    ('2023-05-29', false),
    ('2023-05-30', false),
    ('2023-05-31', false);