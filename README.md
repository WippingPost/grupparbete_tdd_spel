# grupparbete_tdd_spel
Grupparbete i testdriven utveckling

Vad skall vi göra? 
Ni skall skapa ett spel där en tjuv går in i ett museum och tar skatter/föremål för att sedan gå in i mål. Det finns lasrar som är aktiva vissa tider/perioder  som måste undvikas. 
Ni skall alltså skapa ett litet spel som går ut på att en karaktär kan röra sig på ett fält 25x25 (eller valfritt) stort där spelplanen omsluts av väggar.
Karaktärens får en startposition som alltid skall förhålla sig till någonstans längst åt vänster på spelplanen, alltså där x=1 (där x=0 återfinns en vägg).

Målet är att karaktären skall ta sig till en slutpunkt (dörr) längst åt höger på spelplanen, där exempelvis  x = 24. Men för att kunna gå in i denna ”dörr” måste karaktären samlat minst 10 skatter/museiföremål som placerats ut på kartan. 
På spelplanen finns det väggar som inte kan passeras och karaktären måste gå runt dessa, detta måste kontrolleras av ett ”collision detect” system. Alltså när karaktärens x,y position är densamma som väggens x,y position.
På spelplanen finns det även lasrar som endast är aktiva vid vissa tider. Skulle karaktären gå in i en laser utlöses ett larm och spelet är över.
Ni kan välja själva hur det systemet är utformat, exempel:
Laser på alla tid mellan 20:00 – 07:00. 
Laser på varje jämn/ojämn minut
Laser på valfria tider på dygnet
Målet med spelet är att karaktären skall ta alla skatter och gå in i dörren. Ni får gärna ha fler än 1 bana (level).
Hur?
Denna del är ett förslag och det är ni som styr hur ni vill utveckla vidare detta. Det viktigaste är att jobba med TDD-metodik och succesivt bygga upp ert program. Fördelen med TDD i ett sådant här projekt är att risken för fel minskar betydligt, då vi har noggranna tester som testar t.ex. olagliga rörelser, är spelaren träffad av laser, kontroll av antal skatter på spelplanen osv.
