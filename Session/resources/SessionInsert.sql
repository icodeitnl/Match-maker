USE Session;
INSERT INTO `Muzikant` VALUES 
(101,'Joe Cocker','10', 'gitaar'),(102,'Mark Knopfler','2','gitaar'),
(103,'Jimmy Page','3','gitaar'),(104,'Ludovico Einaudi','20','piano'),
(105,'Diana Crall','15','saxophone'),(106,'Norah Jones','12','piano'),
(107,'David Gilmour','7','bas'),(108,'Roger Waters','11','bas'),
(109,'Dave Grohl','5','drums'),(110,'Eric Clapton','30','bas');

 INSERT INTO `Session` VALUES 
(211,101, '2021-01-12','ja','7.2'),(212,102, '2021-01-14','ja','3.5'),
(213,103, '2021-01-16','nee','1.3'),(214,104, '2021-02-18','ja','8.1'),
(215,105, '2021-02-20','ja','4.6'),(216,104, '2021-02-12','ja','3.3'),
(217,104, '2021-03-14','ja','2.0'),(218,108, '2021-03-16','ja','4.3'),
(219,108, '2021-03-18','ja','2.7'),(220,108, '2021-03-20','nee','2.3');

INSERT INTO `OpenSession` VALUES 
(211,3,427),(212,4,532),(214,10,1300),(215,2,300),(217,2,300),
(218,2,650);

INSERT INTO `GeslotenSession` VALUES 
(213,3),(216,2),(219,3),(220,4);

INSERT INTO `Deelname` VALUES 
(211,101,30),(211,109,30),(211,110,30),(217,104,60),(217,108,60),
(220,108,60),(220,106,60),(220,104,60),(214,104,15),(216,106,60);

INSERT INTO `Technicus` VALUES 
(301,"a@hva.com",0610275682,"Wouter Visser"),(302, "b@hva.com",0610344687,"Jeroen Jonkers"),
(303,"c@hva.com", 0612985600, "Vincent Van Gogh"),(304,"d@hva.com",0609876553,"Dik Autoverhuur"),
(305,"e@hva.com",0600984582,"Albert Hein"),(306,"f@hva.com",0698245839,"Bart Van De Broek"),
(307, "g@hva.com",0612345621,"Tom Bakker"),(308,"h@hva.com",0612879634, "Willem Van Dorp"),
(309,"i@hva.com",0612345534,"Jan Met De Korte Achternaam"),(310,"j@hva.com",0611111122,"Valentijn Not-Null-Janssen");

INSERT INTO `TechnicheOndersteuning` VALUES 
(212,401,"audiotechniek"),(214,402,"audiotechniek"),(220,403,"audiotechniek"),(212,404,"beeldtechniek"),
(214,405,"beeldtechniek"),(220,406,"beeldtechniek"),(214,407,"internettechniek"),(216,408,"internettechniek"),
(214,409,"lighttechniek"),(213,410,"lighttechniek");