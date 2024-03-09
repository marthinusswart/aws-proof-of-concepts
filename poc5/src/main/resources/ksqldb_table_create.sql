create TABLE gdp_detail_record_table(
  country_name varchar, country_code varchar PRIMARY KEY, indicator_name varchar, indicator_code varchar,
  y1991 decimal(8,8), y1992 decimal(8,8), y1993 decimal(8,8), y1994 decimal(8,8), y1995 decimal(8,8), y1996 decimal(8,8),
  y1997 decimal(8,8), y1998 decimal(8,8), y1999 decimal(8,8), y2000 decimal(8,8), y2001 decimal(8,8), y2002 decimal(8,8),
  y2003 decimal(8,8), y2004 decimal(8,8), y2005 decimal(8,8), y2006 decimal(8,8), y2007 decimal(8,8), y2008 decimal(8,8),
  y2009 decimal(8,8), y2010 decimal(8,8), y2011 decimal(8,8), y2012 decimal(8,8), y2013 decimal(8,8), y2014 decimal(8,8),
  y2015 decimal(8,8), y2016 decimal(8,8), y2017 decimal(8,8), y2018 decimal(8,8), y2019 decimal(8,8), y2020 decimal(8,8),
  y2021 decimal(8,8), y2022 decimal(8,8)
 ) WITH (
     KAFKA_TOPIC = 'gdp_detail_record', 
     VALUE_FORMAT = 'JSON'
   );