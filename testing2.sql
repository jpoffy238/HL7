update person set update_number = 0 where update_number is null;


select update_number, count(*) from person group by update_number;

select state, count(*) from audit group by state

select update_number, count(*) from person group by update_number


select 	min(id), max(id) ,
		to_date(to_char(create_timestamp, 'YYYY/MM/DD'), 'YYYY/MM/DD')  as date ,
		to_char(create_timestamp::time, 'HH24:MI')  as tod,
		sum(case when state = 'COMPLETED' then 1 else 0 end) as COMPLETED,
		sum(case when state = 'DECODED' then 1 else 0 end) as DECODED,
		sum(case when state = 'DEQUEUED' then 1 else 0 end) as DEQUEUED,
		SUM(CASE WHEN STATE = 'INSERT' THEN 1 ELSE 0 END)  AS "INSERT",
		SUM(CASE WHEN STATE = 'SENT' THEN 1 ELSE 0 END) AS SENT,
		SUM(CASE WHEN STATE = 'UPDATE' THEN 1 ELSE 0 END) AS "UPDATE",	
		count(*)  AS TOTAL from audit 
group  by 
		to_date(to_char(create_timestamp, 'YYYY/MM/DD'), 'YYYY/MM/DD') ,
		to_char(create_timestamp::time, 'HH24:MI') 
order by  to_date(to_char(create_timestamp, 'YYYY/MM/DD'), 'YYYY/MM/DD') ,
		to_char(create_timestamp::time, 'HH24:MI');