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


*** UTVÄRDERING ***


*************************************************************************************************************************************
Anders Nilsson:

Vi valde från början att försöka göra ett spel som skulle vara lite roligt och utmanande att både skapa, men framförallt att spela.
Vi valde att skapa en enkel spelmotor / spel-loop, som skulle vara enkel att bygga vidare på med nya funktioner och spelobjekt.Det är nu enkelt att skapa ny levels för den som är interesserad, och i slutversionen har vi skapat 11 levels.
Då spelprogrammering är en passion för mig tog jag initiativ till att skapa denna spelmotor/spel-loop, och under denna inledande process måste jag erkänna att, även om jag testade min kod så var det mer under agila förhållanden än ren TDD. För att allt ska flyta på ordentligt kör vi spelet i 60 fps.
När väl grunden var lagd har vi jobbat tillsammans med att skapa tester och spelobjekt så som Player, Laser, Timer osv.
Vi hade kommit ganska långt på projektet när vi träffades och presenterade vad vi åstadkommit i Piteå. 
Sedan dess har vi lagt till ett highscore-system i form av tidtagning och om man får en bättre tid än föregående på aktuell level.
Vidare har vi istället för att använda oss av rektanglar nu lagt till grafik istället för att göra spelet mer visuellt tilltalande.
Spelet är nu helt i fullscreen mode, samt hänsyn tas till rådande skärmupplösning, så att det blir samma upplevelse oavsett upplösning på skärm.
Det känns faktiskt som om vi har ett fullt fungerande spel nu.
Det har varit ett kul och lärorikt projekt, men TDD är svårt tycker jag. Jag har mycket att lära där av mina gruppkamrater som kämpat tappert med tester, när jag inte förstått vad vi ska testa. Jag känner att jag har en stor utvecklingspotential där.
***************************************************************************************************************************************
Nicklas Holmberg:

För att säga det direkt: spelet blev tokbra! Det flyter fint, det finns många banor, highscore och adaptiv fullscreen är en skön krydda.

Angående utvecklingsprocessen tror jag vi stundvis hamnat ur fas av olika anledningar. Huvudfokus på TDD har varit svårt att hålla. Dels för att vi, precis som i förra kursen, ville sjösätta projektet snabbt och därefter gradvis förbättra, dels för att det är svårt att skapa en helhet utan att först prova sig fram agilt. Dessutom har vi alla olika stilar när vi kodar, vilket gjort att en testmetod först har skrivits av en person, sedan ändrats av en annan och slutligen implementerats på ett helt annat sätt av en tredje (varpå vi fått ändra i efterhand).

Men det är, i mitt tycke, väldigt lärorikt att ha detta projekt i bagaget ändå. För om man vänder på det har man fått experimentera väldigt mycket med testklasserna, och därmed också upptäckt nya sätt att skriva tester på.
***************************************************************************************************************************************
Joakim Olofsson:

Hur spelet blev är jag supernöjd med, blev riktigt snyggt gjort och hade flera nivåer och funktioner. Att få in test driven utveckling blev stundtals svårt att hålla sig efter, mycket tror jag för att man fastnade i själva kodandet och kom av sig från att faktiskt fokusera utifrån testerna. Men också för att det var väldigt svårt att faktiskt få till att skriva vissa tester och förstå hur vissa delar ska testas. Så projektet kommer jag definitivt ha ikvar och utgå ifrån för det finns fortfarande en hel del delar inom TDD som jag behöver utvecklas inom.

Det har dock varit super lärorikt att göra den uppgift för som sagt så det blir lite av ett annat tänkande när man utvecklar programmet.
***************************************************************************************************************************************

Dennis Larsson:

Vi alla är väldigt överens om att spelet är riktigt bra, vi har utvecklat det från att gå rakt igenom väggar till att det tar stopp mot väggar. Från att spelaren varit en rektangel till en bild på en minion, även annan grafik har lagts till. Men hur vi jobbat med TDD finns det mycket att lära. För de flesta av oss, jag utgår främst från mig själv, är vi inte på den nivån av programmering att det går att skriva ett test innan koden. Inte för detta projekt i alla fall. Jag vet till exempel inte alltid vilket sorts resultat eller variabel jag vill ha eller kan få tillbaka i slutändan eller hur det kan testas, i många fall har det varit enkelt att sätta in en boolean för som ändras om en tex if-sats körs. I och med detta har vi nog varit lite ivriga att få ett resultat som fungerar fysist istället för ett kodat test. Jag känner och tror att om vi hade jobbat uteslutande med TDD hade vi stått stilla och trampat mycket mer än vad vi gjort och. Alla metoder är ju testade, men mer på ett användarområde och inte alltid i kod.
Många saker är svåra att testa med TDD, i alla fall min begränskade kunskap inom programmering. Hur man ska testa en tid som är beroende på hur länge en tråd körs går absolut att testa, men i nuläget har jag inte kunnat sätta mig in i hur man gör det.
Jag har fått en bra inblick i hur TDD kan användas och jag vill absolut gå tillbaka till det när jag kan mer om programmering eller jobba mer paralellt med den vanliga kodningen. Det är ett bra område att ta med sig och jag vill jobba mer med tester. Jag tror att i slutändan kommer jag att hitta en balans för att jobba med TDD för att upprätthålla en fungerande kod. Om jag går mer åt att göra tester innan självka kodningen eller inte, det får framtiden utvisa. Men jag är i alla fall öppen för möjligheten. 
***************************************************************************************************************************************
