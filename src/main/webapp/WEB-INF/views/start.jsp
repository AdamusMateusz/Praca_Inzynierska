<div class="page-header">
	<h1>Komiwojazer</h1>

</div>


<blockquote>
	Problem komiwojazera (ang. travelling salesman problem, TSP) -
	zagadnienie optymalizacyjne, polegajace na znalezieniu minimalnego
	cyklu Hamiltona w pelnym grafie wazonym. Nazwa pochodzi od typowej
	ilustracji problemu, przedstawiajacej go z punktu widzenia wedrownego
	sprzedawcy (komiwojazera): dane jest n miast, które komiwojazer ma
	odwiedzic, oraz odleglosc / cena podrozy / czas podrozy pomiedzy kazda
	para miast. Celem jest znalezienie najkrotszej / najtanszej /
	najszybszej drogi laczacej wszystkie miasta, zaczynajacej sie i
	konczacej sie w okreslonym punkcie. Symetryczny problem komiwojazera
	(STSP) polega na tym, ze dla dowolnych miast A i B odleglosc z A do B
	jest taka sama jak z B do A. W asymetrycznym problemie komiwojazera
	(ATSP) odleglosci te moga byc rozne. Rozwinieciem problemu komiwojazera
	jest problem marszrutyzacji.
	<footer>Wikipedia</footer>
</blockquote>

<h3>Historia</h3>
<div class="text-muted">Poczatek badan nad problemem komiwojazera
	nie jest jasny. Wspomina o nim podrecznik z 1832, który zawiera
	przykladowa trase po Niemczech i Szwajcarii, lecz nie zawiera zadnych
	matematycznych uzasadnien. W 1859 irlandzki matematyk William Rowan
	Hamilton sformulowal problem istnienia cyklu o dlugosci n w grafie
	n-wierzcholkowym. Za faktycznego tworce problemu komiwojazera uznaje
	sie austriackiego matematyka Karla Mengera, ktory go zdefiniowal w
	1930, zwracajac szczegolna uwage na stopien jego skomplikowania.
	Niezaleznie od niego ten sam problem poruszyl w 1934 Hassler Witney na
	wykladzie w Princeton University. Natomiast pierwsze praktyczne
	zastosowanie problemu mialo miejsce w 1937, gdy Merrill Flood pracowal
	nad rozwiazaniem wyznaczania tras dla autobusow szkolnych. Z uwagi na
	bardzo prosty opis problemu oraz opinii o bardzo trudnym obliczeniowo
	procesie optymalizacji, problem komiwojazera stal sie bardzo popularny.
	Fascynacja ta trwa od lat piecdziesiatych XX wieku do dzis, zarowno
	wsrod amatorow jak i profesjonalistow.</div>

<div class="row linki">
	<div class=" col-sm-offset-1 col-sm-3">
		<div class="well">
		
			<div class="svg-container-startpage-miniature">
				<svg version="1.1" style="margin-top: 15px;" viewBox="0 0 200 200"
					preserveAspectRatio="xMinYMin meet" class="svg-content startMiniature">

<rect id="drogi" x="8.667" y="8.667" style="stroke:#FFFFFF;stroke-width:3;stroke-miterlimit:10;" width="178.333" height="174.667"/>
<rect id="domek" x="8.667" y="8.667" style="stroke:#FFFFFF;stroke-width:3;stroke-miterlimit:10;" width="96.667" height="79.333"/>
<rect id="domek" x="115.372" y="8.667" style="stroke:#FFFFFF;stroke-width:3;stroke-miterlimit:10;" width="71.628" height="79.333"/>
<polyline id="domek" style="stroke:#FFFFFF;stroke-width:3;stroke-miterlimit:10;" points="135.607,183.333 
	187,183.333 187,97.993 80.333,97.993 80.333,183.333 135.607,183.333 "/>
<polyline id="woda" style="stroke:#FFFFFF;stroke-width:3;stroke-miterlimit:10;" points="39.585,183.333 
	68.333,183.333 68.333,97.993 8.667,97.993 8.667,183.333 39.585,183.333 "/>
<path id="szpilka" style="stroke-width:2;stroke-miterlimit:10;" d="M171.798,129.894
	c0-10.566-7.819-19.131-17.465-19.131s-17.465,8.565-17.465,19.131c0,3.578,0.897,6.926,2.457,9.79
	c3.048,5.594,15.008,23.886,15.008,23.886s11.612-18.062,14.716-23.369C170.79,137.226,171.798,133.689,171.798,129.894z"/>
<circle id="szpilkaSrodek" style="stroke-width:2;stroke-miterlimit:10;" cx="154.333" cy="128.403" r="9.257"/>

				</svg>
			</div>
			
			<h4>
				Lista map <a href="#/maps" class="btn btn-link btn-lg"><span
					class="glyphicon glyphicon-arrow-right"></span></a>

			</h4>
			<div class="text-muted">W tym miejscu znajdziesz liste
				wszysktich map</div>
		</div>
	</div>
	<div class="col-sm-3">
		<div class="well">
		
			<div class="svg-container-startpage-miniature">
				<svg version="1.1" style="margin-top: 15px;" viewBox="0 0 200 200"
					preserveAspectRatio="xMinYMin meet" class="svg-content startMiniature">

<line id="linia1Staty" style="fill:none;stroke-width:30;stroke-miterlimit:10;" x1="56" y1="176" x2="56" y2="34"/>
<line id="linia2Staty" style="fill:none;stroke-width:30;stroke-miterlimit:10;" x1="100" y1="176" x2="100" y2="127"/>
<line id="linia3Staty" style="fill:none;stroke-width:30;stroke-miterlimit:10;" x1="144" y1="176" x2="144" y2="57.333"/>
<line id="obrys" style="fill:none;stroke-width:5;stroke-miterlimit:10;" x1="23.333" y1="18.667" x2="23.333" y2="189.333"/>
<line id="obrys" style="fill:none;stroke-width:5;stroke-miterlimit:10;" x1="8.333" y1="176" x2="184" y2="176"/>
<rect id="etykieta" x="51.75" y="20.5" style="fill:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" width="8.5" height="8.5"/>
<circle id="etykieta" style="fill:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" cx="100" cy="118" r="4.833"/>
<polygon id="etykieta" style="fill:#FFFFFF;stroke-width:2;stroke-miterlimit:10;" points="144,43.903 
	139.654,51.431 148.346,51.431 "/>
				</svg>
			</div>
			
			<h4>
				Statystyki <a href="#/stats" class="btn btn-link btn-lg"><span
					class="glyphicon glyphicon-arrow-right"></span></a>
			</h4>
			<div class="text-muted">Statystyki zwiazane z dzialaniem
				serwera</div>
		</div>
	</div>
	<div class="col-sm-3">
		<div class="well">
		
			<div class="svg-container-startpage-miniature">
				<svg  version="1.1" style="margin-top: 15px;" viewBox="0 0 200 200"
					preserveAspectRatio="xMinYMin meet" class="svg-content startMiniature">

<path id="obrys" style="fill:#FFFFFF;stroke-width:5;stroke-miterlimit:10;" d="M192.564,88.742
	c0,38.612-41.442,69.913-92.564,69.913c-9.733,0-18.979-1.98-27.928-3.238c-18.319-2.577-50.331,25.753-50.331,25.753
	s19.503-34.894,2.783-51.946C13.554,118.038,7.436,103.83,7.436,88.742c0-38.612,41.442-69.913,92.564-69.913
	S192.564,50.13,192.564,88.742z"/>
<line id="linia1Chat" style="fill:none;stroke-width:6;stroke-miterlimit:10;" x1="45.575" y1="65.329" x2="149.76" y2="65.329"/>
<line id="linia2Chat" style="fill:none;stroke-width:6;stroke-miterlimit:10;" x1="45.575" y1="86.71" x2="149.76" y2="86.71"/>
<line id="linia3Chat" style="fill:none;stroke-width:6;stroke-miterlimit:10;" x1="45.575" y1="108.091" x2="149.76" y2="108.091"/>
				</svg>
			</div>
	
			
			<h4>
				Komunikacja <a href="#/chat" class="btn btn-link btn-lg"><span
					class="glyphicon glyphicon-arrow-right"></span></a>
			</h4>
			<div class="text-muted">Miejsce przeznaczone
				do komunikacji</div>
		</div>
	</div>
</div>
