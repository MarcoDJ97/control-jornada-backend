DROP TABLE IF EXISTS register;

CREATE TABLE register (
	id SERIAL  NOT NULL
	, employee_id INTEGER NOT NULL
	,start_of_the_day time NOT null
	, entry time null
	, exit time null
	, break_time time  null
	, back_to_work time  null
	, day date NOT NULL
	, justification_id INTEGER NULL

 	, PRIMARY KEY (id)
 	, UNIQUE(id)
 	, FOREIGN KEY (employee_id) REFERENCES employee(id)
 	, FOREIGN KEY (justification_id) REFERENCES justification(id)
);

-- REGISTER (ejemplo)
INSERT INTO register (
    id, employee_id, start_of_the_day, entry, exit,
    break_time, back_to_work, day, justification_id
) VALUES
  (1, 1, '08:00:00', '08:00:00', NULL,
   NULL, NULL, CURRENT_DATE, 1);