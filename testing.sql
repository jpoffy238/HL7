select  *  from audit  where hcpid = '100';

select * from person where hcpid='100';

select 'person', cast(hcpid as text),  count(*) from person group by hcpid
union all
select 'audit', hcpid, count(*) from audit group by hcpid;


select state, count(*) from audit group by state;

select count(*) from audit;



select 	min(id), max(id) ,
		to_date(to_char(create_timestamp::time, 'YYYY/MM/DD'), 'YYYY/MM/DD')  as date ,
		to_char(create_timestamp::time, 'HH24:MI')  as tod,
		count(*) from audit
group  by 
		to_date(to_char(create_timestamp::time, 'YYYY/MM/DD'), 'YYYY/MM/DD') ,
		to_char(create_timestamp::time, 'HH24:MI') 
order by  to_date(to_char(create_timestamp::time, 'YYYY/MM/DD'), 'YYYY/MM/DD') ,
		to_char(create_timestamp::time, 'HH24:MI');

		select hcpid, count(*)  from person where create_timestamp <> update_timestamp  group by hcpid;