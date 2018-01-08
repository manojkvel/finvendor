CREATE TABLE IF NOT EXISTS `ven_rsrch_rpt_analyst_prof` (
  `product_id` varchar(45) NOT NULL,
  `analyst_name` varchar(200) NOT NULL,
  `analyst_awards` varchar(500) DEFAULT NULL,
  `anayst_cfa_charter` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ven_rsrch_rpt_analyst_prof`
--

#INSERT INTO `ven_rsrch_rpt_analyst_prof` VALUES ('d1752f52-576a-4a4f-acf3-628811f1426d','AnalystPro','','Y'),('558d34bf-cb64-44a7-9089-576015d15577','Amit Gaurav, Guarav Belanekar, Maninder Singh','Other','Y'),('2d46f4c7-fb83-42d9-a12e-4efac7b72d71','Amit Gaurav, Gaurav Belanekar','Other','Y'),('29ca17f7-642b-42d8-805b-65299b074b32','Amit G','Other','Y'),('986b1bf6-148b-4a2d-b27b-6fc7e033bd70','','Best Equities JP Morgan','');


--
-- Table structure for table `ven_rsrch_rpt_dtls`
--
CREATE TABLE IF NOT EXISTS `ven_rsrch_rpt_dtls` (
  `product_id` varchar(45) NOT NULL,
  `accessbility` varchar(150) DEFAULT NULL,
  `suitability` varchar(150) DEFAULT NULL,
  `sub_cost_py` float(10,2) DEFAULT NULL,
  `rep_format` varchar(20) DEFAULT NULL,
  `rsrch_report_for` varchar(45) DEFAULT NULL,
  `rep_date` varchar(45) DEFAULT NULL,
  `target_price` varchar(45) DEFAULT NULL,
  `rsrch_recomm_type` varchar(45) DEFAULT NULL,
  `rsrch_report_desc` varchar(200) DEFAULT NULL,
  `rsrch_report_access` varchar(45) DEFAULT NULL,
  `rsrch_upload_report` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ven_rsrch_rpt_dtls`
--

#INSERT INTO `ven_rsrch_rpt_dtls` VALUES ('d1752f52-576a-4a4f-acf3-628811f1426d','Email',',All Users,Fixed Income Fund Manager,',0.00,',PDF,',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('558d34bf-cb64-44a7-9089-576015d15577','Web-Browser Based',',All Users,',2000.00,',DOC,',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2d46f4c7-fb83-42d9-a12e-4efac7b72d71','Web-Browser Based',',All Users,',99.00,',PDF,',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('29ca17f7-642b-42d8-805b-65299b074b32','Web-Browser Based',',All Users,',0.00,',PDF,',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('986b1bf6-148b-4a2d-b27b-6fc7e033bd70','Web-Browser Based','',0.00,',PDF,',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

--
-- Alter table `ven_rsrch_rpt_offering` by adding new column to have research report offering path info
--
ALTER TABLE `ven_rsrch_rpt_offering` ADD `rsrch_rpt_offering_file_path` VARCHAR(100);

-- --------------------------------------------------------
